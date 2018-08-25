package fr.guiguilechat.jcelechat.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

public class Usage {

	// loading/dumping

	private static LinkedHashMap<String, Usage> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/usages.yaml";

	public static synchronized LinkedHashMap<String, Usage> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).usages;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<String, Usage> data, File folderout) {
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
		public LinkedHashMap<String, Usage> usages;
	}

	// structure

	public LinkedHashSet<String> productManuf = new LinkedHashSet<>();

	public LinkedHashSet<String> materialManuf = new LinkedHashSet<>();

	public LinkedHashSet<String> materialCopy = new LinkedHashSet<>();

	public LinkedHashSet<String> materialInvention = new LinkedHashSet<>();

	public LinkedHashSet<String> productInvention = new LinkedHashSet<>();

	public LinkedHashSet<String> materialME = new LinkedHashSet<>();

	public LinkedHashSet<String> materialTE = new LinkedHashSet<>();

	public LinkedHashMap<String, Integer> reprocess = new LinkedHashMap<>();

	/**
	 * maximum reprocess mutliplier for the items. 0.55 for modules, 0.82 for
	 * asteroids.
	 */
	public double maxreprocess = 0.55;

}
