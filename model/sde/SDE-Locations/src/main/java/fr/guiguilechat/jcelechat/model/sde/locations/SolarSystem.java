package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.settings.yaml.CleanRepresenter;
import fr.lelouet.tools.settings.yaml.YAMLTools;

public class SolarSystem extends ALocation {

	// loading

	private static LinkedHashMap<String, SolarSystem> cache = null;

	public static final String RESOURCE_PATH = "SDE/locations/solarsystems.yaml";

	public static synchronized LinkedHashMap<String, SolarSystem> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					SolarSystem.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).locations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	private static Map<Integer, String> loadById = null;

	public static Map<Integer, String> loadById() {
		if (loadById == null) {
			LinkedHashMap<String, SolarSystem> mcache = load();
			synchronized (mcache) {
				if (loadById == null) {
					loadById = mcache.entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
				}
			}
		}
		return loadById;
	}

	public static void export(LinkedHashMap<String, SolarSystem> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting systems to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, SolarSystem> locations;
	}

	// normalizing

	private static Map<String, String> lowerCased = null;

	public static SolarSystem getSystem(String name) {
		if (name == null) {
			return null;
		}
		SolarSystem ret = load().get(name);
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

	public static SolarSystem getSystem(int id) {
		return getSystem(loadById().get(id));
	}

	// structure

	public double truesec;

	public String constellation, region;

	public boolean isCorridor = false;
	public boolean isBorder = false;
	public boolean isFringe = false;
	public boolean isHub = false;
	public boolean anchorStructures = false;

	// helper

	public boolean isHS() {
		return truesec > 0.45;
	}

	public boolean isLS() {
		return 0 < truesec && truesec <= 0.45;
	}

	public boolean isNS() {
		return 0 > truesec;
	}

	@Override
	public String toString() {
		return name;
	}

}
