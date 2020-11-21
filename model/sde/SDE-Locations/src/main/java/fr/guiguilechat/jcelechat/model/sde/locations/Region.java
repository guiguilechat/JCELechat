package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

	public static final Set<String> EMPIRE_ANGELS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("Heimatar", "Metropolis", "Molden Heath")));

	public static final Set<String> EMPIRE_BLOODS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("Aridia", "Genesis", "Kador", "Khanid", "Kor-Azor", "The Bleak Lands")));

	public static final Set<String> EMPIRE_GURISTAS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("Black Rise", "The Citadel", "The Forge", "Lonetrek")));

	public static final Set<String> EMPIRE_SANSHAS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("Derelik", "Devoid", "Domain", "Tash-Murkon")));

	public static final Set<String> EMPIRE_SERPENTIS = Collections.unmodifiableSet(
			new HashSet<>(Arrays.asList("Essence", "Everyshore", "Placid", "Sinq Laison", "Solitude", "Verge Vendor")));

	public static enum EMPIRE_FACTIONS {
		all {
			@Override
			public Set<String> regions() {
				return EMPIRE;
			}
		},
		angels {
			@Override
			public Set<String> regions() {
				return EMPIRE_ANGELS;
			}
		},
		bloods {
			@Override
			public Set<String> regions() {
				return EMPIRE_BLOODS;
			}
		},
		guristas {
			@Override
			public Set<String> regions() {
				return EMPIRE_GURISTAS;
			}
		},
		sanshas {
			@Override
			public Set<String> regions() {
				return EMPIRE_SANSHAS;
			}
		},
		serpentis {
			@Override
			public Set<String> regions() {
				return EMPIRE_SERPENTIS;
			}
		};

		public abstract Set<String> regions();

		private Set<SolarSystem> systems = null;

		public Set<SolarSystem> systems() {
			if (systems == null) {
				systems = regions().stream().flatMap(r -> getRegion(r).systems()).map(SolarSystem::getSystem)
						.filter(ss -> ss.isHS()).collect(Collectors.toUnmodifiableSet());
			}
			return systems;
		}

		public static EMPIRE_FACTIONS of(String regionName) {
			for (EMPIRE_FACTIONS f : EMPIRE_FACTIONS.values()) {
				if (f.regions().contains(regionName)) {
					return f;
				}
			}
			return null;
		}

		public static EMPIRE_FACTIONS of(SolarSystem sys) {
			return of(sys.region);
		}
	}

	public static final Set<String> EMPIRE = Collections.unmodifiableSet(Stream
			.concat(Stream.concat(EMPIRE_ANGELS.stream(), EMPIRE_BLOODS.stream()),
					Stream.concat(EMPIRE_GURISTAS.stream(), Stream.concat(EMPIRE_SANSHAS.stream(), EMPIRE_SERPENTIS.stream())))
			.collect(Collectors.toSet()));

}
