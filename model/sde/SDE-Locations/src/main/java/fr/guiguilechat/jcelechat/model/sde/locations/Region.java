package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.jcelechat.model.sde.yaml.YAMLTools;

public class Region extends ALocation {

	// loading/dumping

	private static LinkedHashMap<String, Region> cache = null;

	public static final String RESOURCE_PATH = "SDE/locations/regions.yaml";

	public static synchronized LinkedHashMap<String, Region> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Region.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).locations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	private static Map<Integer, String> loadById = null;

	public static Map<Integer, String> loadById() {
		if (loadById == null) {
			LinkedHashMap<String, Region> mcache = load();
			synchronized (mcache) {
				if (loadById == null) {
					loadById = mcache.entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
				}
			}
		}
		return loadById;
	}

	public static void export(LinkedHashMap<String, Region> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting regions to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, Region> locations;
	}

	// normalizing

	private static Map<String, String> lowerCased = null;

	public static Region getRegion(String name) {
		if (name == null) {
			return null;
		}
		Region ret = load().get(name);
		if (ret != null) {
			return ret;
		}
		name = name.toLowerCase();
		if (lowerCased == null) {
			synchronized (cache) {
				if (lowerCased == null) {
					lowerCased = cache.keySet().stream().collect(Collectors.toMap(String::toLowerCase, s -> s));
				}
			}
		}
		return cache.get(lowerCased.get(name));
	}

	// structure

	public ArrayList<String> constellations = new ArrayList<>();

	/** stream the systems names in a region */
	public Stream<String> system() {
		return constellations.stream().map(Constellation.load()::get).flatMap(c -> c.systems.stream());
	}

}
