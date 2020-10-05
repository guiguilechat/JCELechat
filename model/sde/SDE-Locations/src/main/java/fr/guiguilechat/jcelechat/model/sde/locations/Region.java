package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;

public class Region extends ALocation {

	private static final Logger logger = LoggerFactory.getLogger(Region.class);

	// loading/dumping

	private static LinkedHashMap<String, Region> cache = null;

	public static final String RESOURCE_PATH = "SDE/locations/regions.yaml";

	public static synchronized LinkedHashMap<String, Region> load() {
		if (cache == null) {
			try (InputStreamReader reader = new InputStreamReader(
					Region.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
				cache = new Yaml().loadAs(reader, Container.class).locations;
			} catch (Exception exception) {
				throw new UnsupportedOperationException("catch this", exception);
			}
		}
		return cache;
	}

	private static Map<Integer, String> loadNameById = null;

	public static Map<Integer, String> loadNameById() {
		if (loadNameById == null) {
			LinkedHashMap<String, Region> mcache = load();
			synchronized (mcache) {
				if (loadNameById == null) {
					loadNameById = mcache.entrySet().stream().collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
				}
			}
		}
		return loadNameById;
	}

	private static Map<Integer, Region> loadById = null;

	public static Map<Integer, Region> loadById() {
		if (loadById == null) {
			LinkedHashMap<String, Region> mcache = load();
			synchronized (mcache) {
				if (loadById == null) {
					loadById = mcache.values().stream().collect(Collectors.toMap(e -> e.id, e -> e));
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
			logger.warn("can't load region for name " + name);
		}
		return ret;
	}

	// structure

	public ArrayList<String> constellations = new ArrayList<>();

	/** stream the systems names in a region */
	public Stream<String> systems() {
		return constellations.stream().map(Constellation::getConstellation).flatMap(c -> c.systems.stream());
	}

	public static final Collection<String> EMPIRE_ANGELS = Collections
			.unmodifiableCollection(Arrays.asList("Heimatar", "Metropolis", "Molden Heath"));

	public static final Collection<String> EMPIRE_BLOODS = Collections
			.unmodifiableCollection(Arrays.asList("Aridia", "Genesis", "Kador", "Khanid", "Kor-Azor", "The Bleak Lands"));

	public static final Collection<String> EMPIRE_GURISTAS = Collections
			.unmodifiableCollection(Arrays.asList("Black Rise", "The Citadel", "The Forge", "Lonetrek"));

	public static final Collection<String> EMPIRE_SANSHAS = Collections
			.unmodifiableCollection(Arrays.asList("Derelik", "Devoid", "Domain", "Tash-Murkon"));

	public static final Collection<String> EMPIRE_SERPENTIS = Collections.unmodifiableCollection(
			Arrays.asList("Essence", "Everyshore", "Placid", "Sinq Laison", "Solitude", "Verge Vendor"));

	public static final Collection<String> EMPIRE = Collections.unmodifiableCollection(Stream
			.concat(Stream.concat(EMPIRE_ANGELS.stream(), EMPIRE_BLOODS.stream()),
					Stream.concat(EMPIRE_GURISTAS.stream(), Stream.concat(EMPIRE_SANSHAS.stream(), EMPIRE_SERPENTIS.stream())))
			.collect(Collectors.toList()));

	public static Collection<String> piratesHSRegion(SolarSystem ss) {
		for (Collection<String> c : Arrays.asList(EMPIRE_ANGELS, EMPIRE_BLOODS, EMPIRE_GURISTAS, EMPIRE_SANSHAS,
				EMPIRE_SERPENTIS)) {
			if (c.contains(ss.region)) {
				return c;
			}
		}
		return Collections.emptyList();
	}

}
