package fr.guiguilechat.eveonline.model.sde.industry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.eveonline.model.sde.yaml.Tools;

public class Blueprint {

	// loading/dumping

	private static LinkedHashMap<String, Blueprint> cache = null;

	public static final String RESOURCE_PATH = "SDE/industry/blueprints.yaml";

	public static synchronized LinkedHashMap<String, Blueprint> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Blueprint.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).locations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	public static void export(LinkedHashMap<String, Blueprint> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), Tools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, Blueprint> locations;
	}

	// structure


	/**
	 * used in the blueprints as requirement, or products
	 */
	public static class Material {
		public int quantity;
		public String name;
		/**
		 * probability is !=1 if this is a produc.
		 */
		public float probability = 1.0f;

		public Material() {
		}
	}

	public static class Activity {
		public ArrayList<Material> materials = new ArrayList<>();
		public ArrayList<Material> products = new ArrayList<>();
		public LinkedHashMap<String, Integer> skills = new LinkedHashMap<>();
		public int time;

		public Activity() {
		}
	}

	public Activity copying, invention, manufacturing, research_material, research_time, reaction;

	public int id;

	public String name;

}
