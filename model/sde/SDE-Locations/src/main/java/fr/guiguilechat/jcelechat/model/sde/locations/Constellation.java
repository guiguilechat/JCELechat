package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class Constellation extends ALocation {

	private static final Logger logger = LoggerFactory.getLogger(Constellation.class);

	// loading/dumping

	private static LinkedHashMap<String, Constellation> cache = null;

	public static final String RESOURCE_PATH = "SDE/locations/constellations.yaml";

	public static synchronized LinkedHashMap<String, Constellation> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					Constellation.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(
						reader,
						Container.class).locations;
			} catch (ClassCastException cce) {
				logger.warn(cce.getLocalizedMessage() + "constellation cl is " + Constellation.class.getClassLoader(),
						cce.getCause());
				throw new UnsupportedOperationException("catch this", cce);
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return cache;
	}

	private static Map<Integer, String> loadById = null;

	public static Map<Integer, String> loadById() {
		if (loadById == null) {
			LinkedHashMap<String, Constellation> mcache = load();
			synchronized (mcache) {
				if (loadById == null) {
					loadById = mcache.entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
				}
			}
		}
		return loadById;
	}

	public static void export(LinkedHashMap<String, Constellation> data, File folderout) {
		File output = new File(folderout, RESOURCE_PATH);
		output.mkdirs();
		output.delete();
		Container c = new Container();
		c.locations = data;
		try {
			new Yaml(new CleanRepresenter(), YAMLTools.blockDumper()).dump(c, new FileWriter(output));
		} catch (IOException e) {
			throw new UnsupportedOperationException("while exporting constellations to " + output.getAbsolutePath(), e);
		}
	}

	private static final class Container {
		public LinkedHashMap<String, Constellation> locations;
	}

	// normalizing

	private static Map<String, String> lowerCased = null;

	public static Constellation getConstellation(String name) {
		if (name == null) {
			return null;
		}
		Constellation ret = load().get(name);
		if (ret == null) {
			name = name.toLowerCase();
			if (lowerCased == null) {
				synchronized (cache) {
					if (lowerCased == null) {
						lowerCased = cache.keySet().stream().collect(Collectors.toMap(String::toLowerCase, s -> s));
					}
				}
			}
			ret = cache.get(lowerCased.get(name));
		}
		if (ret == null) {
			logger.warn("can't load constel for name " + name);
		}
		return ret;
	}

	// structure

	public ArrayList<String> systems = new ArrayList<>();

	public boolean hasCorridor = false;
	public boolean hasBorder = false;
	public boolean hasFringe = false;
	public boolean hasHub = false;

}
