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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.jcelacht.libs.dbmaker.TableBuilder;

public class CatBuilder implements TableBuilder {

	public static final CatBuilder INSTANCE = new CatBuilder();

	public static final String TABLENAME = "BASE_CATEGORIES";
	public static final List<String> PRIMARY_KEY = List.of("category_id");
	public static final Map<List<String>, FKRef> FOREIGN_KEYS = null;
	public static final Set<String> IGNORED = null;

	@Override
	public boolean execute(File outDir) {
		File outFile = new File(outDir, priority() + "_" + TABLENAME + ".sql");
		Universe uni = ESIStatic.INSTANCE.cache().universe;

		StringBuilder request = new StringBuilder();
		initScript(request);
		List<Field> columns = new ArrayList<>();
		createTable(request, R_get_universe_categories_category_id.class, TABLENAME, columns, PRIMARY_KEY, FOREIGN_KEYS,
				IGNORED);

		List<Integer> ids = uni.categories().get();
		for (int id : ids) {
			uni.categories(id);
		}
		List<R_get_universe_categories_category_id> items = ids.stream().sorted().map(id -> uni.categories(id).get())
				.collect(Collectors.toList());
		insertValues(request, TABLENAME, items, columns);

		try (FileWriter writer = new FileWriter(outFile)) {
			writer.write(request.toString());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}