package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class SolarSystem extends ALocation {

	private static final Logger logger = LoggerFactory.getLogger(SolarSystem.class);

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
			logger.warn("can't load system for name " + name);
		}
		return ret;
	}

	public static SolarSystem getSystem(int id) {
		return getSystem(loadById().get(id));
	}

	// structure

	public double truesec;

	public String constellation, region;

	public Constellation constellation() {
		return Constellation.getConstellation(constellation);
	}

	public Region region() {
		return Region.getRegion(region);
	}

	public boolean isCorridor = false;
	public boolean isBorder = false;
	public boolean isFringe = false;
	public boolean isHub = false;
	public boolean anchorStructures = false;

	// helper

	/**
	 * sec status represents the speed of intervention from Concord. HS means
	 * concord will destroy you, LS means turrets will defend you, and NS means
	 * you gonna die helplessly
	 */
	public static enum SECSTATUS {
		HS, LS, NS;

		public static SECSTATUS of(double truesec) {
			return truesec > 0.45 ? SECSTATUS.HS : truesec <= 0 ? SECSTATUS.NS : SECSTATUS.LS;
		}
	}

	public SECSTATUS secStatus() {
		return SECSTATUS.of(truesec);
	}

	public boolean isHS() {
		return truesec > 0.45;
	}

	public boolean isLS() {
		return 0 < truesec && truesec <= 0.45;
	}

	public boolean isNS() {
		return 0 >= truesec;
	}

	@Override
	public String toString() {
		return name;
	}

}
