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
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Universe;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.jcelacht.libs.dbmaker.TableBuilder;

public class TypeAttributesBuilder implements TableBuilder {

	public static final TypeAttributesBuilder INSTANCE = new TypeAttributesBuilder();

	public static final String TABLENAME = "BASE_TYPE_ATTRIBUTES";
	public static final List<String> PRIMARY_KEY = null;
	public static final Map<List<String>, FKRef> FOREIGN_KEYS = Map.of(
			List.of("type_id"),	FKRef.of(TypeBuilder.TABLENAME),
			List.of("attribute_id"),	FKRef.of(AttributeBuilder.TABLENAME)
			);
	public static final Set<String> IGNORED = null;

	@Override
	public boolean execute(File outDir) {
		File outFile = new File(outDir, priority() + "_" + TABLENAME + ".sql");
		Universe uni = ESIStatic.INSTANCE.cache().universe;

		StringBuilder request = new StringBuilder();
		initScript(request);
		List<Field> columns = new ArrayList<>();
		createTable(request, TypeAtt.class, TABLENAME, columns, PRIMARY_KEY, FOREIGN_KEYS, IGNORED);

		List<Integer> ids = uni.types().get();
		for (int typeid : ids) {
			uni.types(typeid);
		}
		List<TypeAtt> items = ids.stream().map(typeid -> uni.types(typeid).get())
				.filter(type -> type.dogma_attributes != null)
				.flatMap(type -> Stream.of(type.dogma_attributes).map(att -> new TypeAtt(type.type_id, att)))
				.sorted()
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
		return List.of(TypeBuilder.INSTANCE, AttributeBuilder.INSTANCE);
	}

	public static class TypeAtt extends get_dogma_dynamic_items_type_id_item_id_dogma_attributes
	implements Comparable<TypeAtt> {
		public int type_id;

		public TypeAtt(int type_id, get_dogma_dynamic_items_type_id_item_id_dogma_attributes values) {
			this.type_id = type_id;
			attribute_id = values.attribute_id;
			value = values.value;
		}

		@Override
		public int compareTo(TypeAtt o) {
			if (o.type_id < type_id) {
				return -1;
			}
			if (o.type_id > type_id) {
				return 1;
			}
			if (o.attribute_id < attribute_id) {
				return -1;
			}
			if (o.attribute_id > attribute_id) {
				return 1;
			}
			return 0;
		}
	}
}
