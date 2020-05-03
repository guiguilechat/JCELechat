package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

/** The bp names a type is used into, for each activity. */
public class IndustryUsage {

	// loading/dumping

	private static LinkedHashMap<Integer, IndustryUsage> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/usages.yaml";

	public static synchronized LinkedHashMap<Integer, IndustryUsage> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					IndustryUsage.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).usages;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<Integer, IndustryUsage> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.usages = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<Integer, IndustryUsage> usages;
	}

	// structure

	public LinkedHashSet<Integer> materialInCopy = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInInvention = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfInvention = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInManuf = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfManuf = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInME = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInTE = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInReaction = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfReaction = new LinkedHashSet<>();

	/**
	 * map of the type ids to their quantities that are resulting of reprocessing
	 * this.Quantities are the average for one item.
	 */
	public LinkedHashMap<Integer, Double> reprocessInto = new LinkedHashMap<>();

	/**
	 * ids of the types that can be reprocessed into this
	 */
	public LinkedHashSet<Integer> reprocessedFrom = new LinkedHashSet<>();

	public int compressTo = 0;

	public int compressFrom = 0;

}
