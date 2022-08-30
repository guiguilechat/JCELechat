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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected.Dogma;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.jcelacht.libs.dbmaker.TableBuilder;

public class AttributeBuilder implements TableBuilder {

	public static final AttributeBuilder INSTANCE = new AttributeBuilder();

	public static final String TABLENAME = "BASE_ATTRIBUTES";
	public static final List<String> PRIMARY_KEY = List.of("attribute_id");
	public static final Map<List<String>, FKRef> FOREIGN_KEYS = null;
	public static final Set<String> IGNORED = Set.of("description");

	@Override
	public boolean execute(File outDir) {
		File outFile = new File(outDir, priority() + "_" + TABLENAME + ".sql");
		Dogma dogma = ESIStatic.INSTANCE.cache().dogma;

		StringBuilder request = new StringBuilder();
		initScript(request);
		List<Field> columns = new ArrayList<>();
		createTable(request, R_get_dogma_attributes_attribute_id.class, TABLENAME, columns, PRIMARY_KEY, FOREIGN_KEYS,
				IGNORED);

		List<Integer> ids = dogma.attributes().get();
		for (int id : ids) {
			dogma.attributes(id);
		}
		List<R_get_dogma_attributes_attribute_id> items = ids.stream().sorted().map(id -> dogma.attributes(id).get())
				.collect(Collectors.toList());
		insertEachValues(request, TABLENAME, items, columns);

		try (FileWriter writer = new FileWriter(outFile)) {
			writer.write(request.toString());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
