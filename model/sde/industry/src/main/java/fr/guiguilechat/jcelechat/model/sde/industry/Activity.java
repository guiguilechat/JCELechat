package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.industry.activity.ArchivedActivityList;
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
public class Activity {

	// storage

	public static final String RESOURCE_PATH = "SDE/industry/activities.yaml";

	public static List<Activity> load(InputStream is) {
		try (InputStreamReader reader = new InputStreamReader(is)) {
			LoaderOptions options = new LoaderOptions();
			options.setCodePointLimit(Integer.MAX_VALUE);
			return new Yaml(options).loadAs(reader, Container.class).activities;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final List<Activity> load = load(
			Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH));

	public static File export(List<Activity> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.activities = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new RuntimeException("while exporting to " + output.getAbsolutePath(), e);
		}
		return output;
	}

	private static final class Container {
		public List<Activity> activities;
	}

	//
	// structure
	//

	public int activityId;
	public String activityName;
	public String description;

	//
	// access
	//

	public static Activity of(int id, Instant date) {
		Activity ret = (date == null ? load() : load(date)).stream()
				.filter(a -> a.activityId == id)
				.findAny().orElse(null);
		if (ret == null) {
			log.warn("unknown activity " + id);
		}
		return ret;
	}

	public static Activity of(int id) {
		return of(id, null);
	}

	@Getter(lazy = true)
	private static final List<ArchivedActivityList> archives = ArchivedActivityList.list();

	/**
	 * load the archived blueprint list for given date.
	 */
	public static List<Activity> load(Instant date) {
		return ArchiveTools.dichoSearch(getArchives(), date, load());
	}

}
