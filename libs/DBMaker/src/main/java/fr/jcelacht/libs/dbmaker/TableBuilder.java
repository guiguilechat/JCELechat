package fr.jcelacht.libs.dbmaker;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.jcelacht.libs.dbmaker.builders.CatBuilder;
import fr.jcelacht.libs.dbmaker.builders.GroupBuilder;
import fr.jcelacht.libs.dbmaker.builders.TypeBuilder;

public interface TableBuilder {

	public boolean execute(File outDir);

	public static List<TableBuilder> instances() {
		return List.of(CatBuilder.INSTANCE, GroupBuilder.INSTANCE, TypeBuilder.INSTANCE);
	}

	public default String escapeOracle(String data) {
		String ret = "'"
				+ data.replaceAll("'", "''").replaceAll("\\r*\\n\\r*", "' || chr(10) || '").replaceAll("&", "' || CHR(38) || '")
				+ "'";
		if (ret.contains("\n")) {
			throw new UnsupportedOperationException(ret + " contains newline");
		}
		if (ret.contains("\r")) {
			throw new UnsupportedOperationException(ret + " contains carriage return");
		}
		// System.err.println(data + " => " + ret);
		return ret;
	}

	public default List<TableBuilder> dependsOn() {
		return List.of();
	}

	public default int computePriority() {
		int ret = 1;
		for (TableBuilder depends : dependsOn()) {
			ret = Math.max(ret, depends.computePriority() + 1);
		}
		return ret;
	}

	public default String priorityStr(int priority) {
		String ret = "" + priority;
		while (ret.length() < 3) {
			ret = '0' + ret;
		}
		return ret;
	}

	public default String priority() {
		return priorityStr(computePriority());
	}

	public static class FKRef {
		public final String table;
		public final List<String> field_override;

		public FKRef(String table, String... override) {
			this.table = table;
			field_override = override == null ? List.of() : List.of(override);
		}

		public static FKRef of(String table, String... override) {
			return new FKRef(table, override);
		}

		@Override
		public String toString() {
			return table + (field_override.isEmpty() ? "" : "." + field_override);
		}
	}

	public default boolean initScript(StringBuilder request) {
		return true;
	}

	// public default String user() {
	// return "lechat";
	// }

	public default boolean createTable(StringBuilder request, Class<?> clazz, String tableName, List<Field> columns,
			List<String> primary_key, Map<List<String>, FKRef> foreign_keys, Set<String> ignoredFields) {
		if (primary_key == null) {
			primary_key = List.of();
		}
		if (ignoredFields == null) {
			ignoredFields = Set.of();
		}
		if (foreign_keys == null) {
			foreign_keys = Map.of();
		}
		// tableName = user() + "." + tableName;
		request.append("create table " + tableName + "(");
		Field[] fields = clazz.getFields();
		Arrays.sort(fields, Comparator.comparing(Field::getName));
		boolean first = true;
		for (Field f : fields) {
			if (ignoredFields.contains(f.getName())) {
				continue;
			}
			String type = null;
			if (f.getType().equals(float.class) || f.getType().equals(Float.class)) {
				type = "FLOAT";
			} else if (f.getType().equals(int.class) || f.getType().equals(Integer.class)) {
				type = "NUMBER";
			} else if (f.getType().equals(String.class)) {
				type = "VARCHAR2(4000)";
			}
			if (type != null) {
				if (!first) {
					request.append(", ");
				}
				first = false;
				request.append(f.getName()).append(" ").append(type);
				if (f.getType().isPrimitive()) {
					request.append(" NOT NULL");
				}
				columns.add(f);
				f.setAccessible(true);
			}
		}
		if (!primary_key.isEmpty()) {
			request.append(", PRIMARY KEY (").append(primary_key.stream().collect(Collectors.joining(", "))).append(")");
		}
		for (Map.Entry<List<String>, FKRef> entry : foreign_keys.entrySet()) {
			FKRef ref = entry.getValue();
			if(!ref.field_override.isEmpty()&&entry.getKey().size()!=ref.field_override.size()) {
				throw new UnsupportedOperationException("different sizes of list for foreign key "+entry);
			}
			request.append(", FOREIGN KEY (").append(entry.getKey().stream().collect(Collectors.joining(", "))).append(")");
			List<String> refFields = ref.field_override.isEmpty() ? entry.getKey() : ref.field_override;
			request.append(" REFERENCES ").append(ref.table).append(" (")
			.append(refFields.stream().collect(Collectors.joining(", "))).append(")");
		}
		request.append(");\n");
		return true;
	}

	public default boolean insertAllValues(StringBuilder request, String tableName, List<? extends Object> items,
			List<Field> columns) {
		// tableName = user() + "." + tableName;
		request.append("\nINSERT ALL\n");

		StringBuilder into = new StringBuilder(" into " + tableName + " (");
		boolean first = true;
		for (Field f : columns) {
			if (!first) {
				into.append(", ");
			}
			first = false;
			into.append(f.getName());
		}
		into.append(") VALUES (");

		for (Object entry : items) {
			request.append(into);
			first = true;
			for (Field f : columns) {
				if (!first) {
					request.append(", ");
				}
				first = false;
				try {
					String dbValue = convertOracleInsert(f.get(entry));
					request.append(dbValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
			}
			request.append(")\n");
		}
		request.append("select 1 from DUAL;");
		return true;
	}

	public default boolean insertEachValues(StringBuilder request, String tableName, List<? extends Object> items,
			List<Field> columns) {
		// tableName = user() + "." + tableName;
		StringBuilder into = new StringBuilder("INSERT INTO " + tableName + " (");
		boolean first = true;
		for (Field f : columns) {
			if (!first) {
				into.append(", ");
			}
			first = false;
			into.append(f.getName());
		}
		into.append(") VALUES (");

		for (Object entry : items) {
			request.append(into);
			first = true;
			for (Field f : columns) {
				if (!first) {
					request.append(", ");
				}
				first = false;
				try {
					String dbValue = convertOracleInsert(f.get(entry));
					request.append(dbValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
			}
			request.append(");\n");
		}
		return true;
	}

	static final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	public default String convertOracleInsert(Object fieldValue) {
		if (fieldValue == null) {
			return "null";
		}
		if (fieldValue.getClass().equals(String.class)) {
			return escapeOracle((String) fieldValue);
		}
		if (fieldValue.getClass().equals(Float.class) || fieldValue.getClass().equals(float.class)) {
			df.setMaximumFractionDigits(340);
			return df.format((float) fieldValue);
		}
		if (fieldValue.getClass().equals(Double.class) || fieldValue.getClass().equals(double.class)) {
			df.setMaximumFractionDigits(340);
			return df.format((double) fieldValue);
		}
		return String.valueOf(fieldValue);
	}

}
