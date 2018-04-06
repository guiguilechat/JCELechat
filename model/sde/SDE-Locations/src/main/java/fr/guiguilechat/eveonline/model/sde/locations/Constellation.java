package fr.guiguilechat.eveonline.model.sde.locations;

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

import fr.guiguilechat.eveonline.model.sde.yaml.CleanRepresenter;
import fr.guiguilechat.eveonline.model.sde.yaml.Tools;

public class Constellation extends ALocation {

	private static final Logger logger = LoggerFactory.getLogger(Constellation.class);

	// loading/dumping

	private static LinkedHashMap<String, Constellation> cache = null;

	public static final String RESOURCE_PATH = "SDE/locations/constellations.yaml";

	public static synchronized LinkedHashMap<String, Constellation> load() {
		if (cache == null) {
			try {
				cache = new Yaml().loadAs(
						new InputStreamReader(Constellation.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)),
						Container.class).locations;
			} catch (ClassCastException cce) {
				logger.warn(cce.getLocalizedMessage() + "constellation cl is " + Constellation.class.getClassLoader(),
						cce.getCause());
				throw new UnsupportedOperationException("catch this", cce);
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
			new Yaml(new CleanRepresenter(), Tools.blockDumper()).dump(c, new FileWriter(output));
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

	public ArrayList<String> systems = new ArrayList<>();

	public boolean hasCorridor = false;
	public boolean hasBorder = false;
	public boolean hasFringe = false;
	public boolean hasHub = false;

}
