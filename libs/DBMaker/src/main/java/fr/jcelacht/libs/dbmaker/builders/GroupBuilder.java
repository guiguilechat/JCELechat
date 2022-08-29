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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.jcelacht.libs.dbmaker.TableBuilder;

public class GroupBuilder implements TableBuilder {

	public static final GroupBuilder INSTANCE = new GroupBuilder();

	public static final String TABLENAME = "BASE_GROUPS";
	public static final Set<String> IGNORED = null;
	public static final List<String> PRIMARY_KEY = List.of("group_id");
	public static final Map<List<String>, FKRef> FOREIGN_KEYS = Map.of(List.of("category_id"),
			FKRef.of(CatBuilder.TABLENAME));

	@Override
	public boolean execute(File outDir) {
		File outFile = new File(outDir, priority() + "_" + TABLENAME + ".sql");
		Universe uni = ESIStatic.INSTANCE.cache().universe;

		StringBuilder request = new StringBuilder();
		List<Field> columns = new ArrayList<>();
		createTable(request, R_get_universe_groups_group_id.class, TABLENAME, columns, PRIMARY_KEY, FOREIGN_KEYS, IGNORED);

		List<Integer> groups = uni.groups().get();
		for (int groupId : groups) {
			uni.groups(groupId);
		}
		List<R_get_universe_groups_group_id> items = groups.stream().map(typeid -> uni.groups(typeid).get())
				.collect(Collectors.toList());
		insertAllValues(request, TABLENAME, items, columns);

		try (FileWriter writer = new FileWriter(outFile)) {
			writer.write(request.toString());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<TableBuilder> dependsOn() {
		return List.of(CatBuilder.INSTANCE);
	}

}