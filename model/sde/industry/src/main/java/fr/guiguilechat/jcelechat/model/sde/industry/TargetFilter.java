package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.industry.targetfilter.ArchivedTargetFilterList;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools;
import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TargetFilter {

	// storage

	public static final String RESOURCE_PATH = "SDE/industry/targetfilters.yaml";

	public static LinkedHashMap<Integer, TargetFilter> load(InputStream is) {
		try (InputStreamReader reader = new InputStreamReader(is)) {
			LoaderOptions options = new LoaderOptions();
			options.setCodePointLimit(Integer.MAX_VALUE);
			return new Yaml(options).loadAs(reader, Container.class).filters;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final LinkedHashMap<Integer, TargetFilter> load = load(
			TargetFilter.class.getClassLoader().getResourceAsStream(RESOURCE_PATH));

	public static File export(LinkedHashMap<Integer, TargetFilter> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.filters = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new RuntimeException("while exporting to " + output.getAbsolutePath(), e);
		}
		return output;
	}

	private static final class Container {
		public LinkedHashMap<Integer, TargetFilter> filters;
	}

	//
	// structure
	//

	public int id;
	public List<Integer> categoryIds = new ArrayList<>();
	public List<Integer> groupIds = new ArrayList<>();
	public String name;

	//
	// access
	//

	public static TargetFilter of(int id, Instant date) {
		TargetFilter ret = (date == null ? load() : load(date)).get(id);
		if (ret == null) {
			log.warn("unknown targetfilter " + id);
		}
		return ret;
	}

	public static TargetFilter of(int id) {
		return of(id, null);
	}

	@Getter(lazy = true)
	private static final List<ArchivedTargetFilterList> archives = ArchivedTargetFilterList.list();

	/**
	 * load the archived blueprint list for given date.
	 */
	public static LinkedHashMap<Integer, TargetFilter> load(Instant date) {
		return ArchiveTools.dichoSearch(getArchives(), date, load());
	}

}
