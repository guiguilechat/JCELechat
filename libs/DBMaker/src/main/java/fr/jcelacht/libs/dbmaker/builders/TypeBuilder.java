package fr.jcelacht.libs.dbmaker.builders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.jcelacht.libs.dbmaker.TableBuilder;

public class TypeBuilder implements TableBuilder {

	public static final TypeBuilder INSTANCE = new TypeBuilder();

	public static final String TABLENAME = "BASE_TYPES";
	public static final Set<String> IGNORED = Set.of("description");
	public static final List<String> PRIMARY_KEY = List.of("type_id");
	public static final Map<List<String>, FKRef> FOREIGN_KEYS = Map.of(List.of("group_id"),
			FKRef.of(GroupBuilder.TABLENAME));

	@Override
	public boolean execute(File outDir) {
		File outFile = new File(outDir, priority() + "_" + TABLENAME + ".sql");
		Universe uni = ESIStatic.INSTANCE.cache().universe;

		StringBuilder request = new StringBuilder();
		List<Field> columns = new ArrayList<>();
		createTable(request, R_get_universe_types_type_id.class, TABLENAME, columns, PRIMARY_KEY, FOREIGN_KEYS, IGNORED);

		List<Integer> types = uni.types().get();
		for (int typeid : types) {
			uni.types(typeid);
		}
		List<R_get_universe_types_type_id> items = types.stream().map(typeid -> uni.types(typeid).get())
				.collect(Collectors.toList());
		insertEachValues(request, TABLENAME, items, columns);

		try (FileWriter writer = new FileWriter(outFile)) {
			writer.write(request.toString());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<TableBuilder> dependsOn() {
		return List.of(GroupBuilder.INSTANCE);
	}

}
