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

	public static Region getRegion(int id) {
		return loadById().get(id);
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

	public static final Set<String> EMPIRE = Collections.unmodifiableSet(Stream
			.concat(Stream.concat(EMPIRE_ANGELS.stream(), EMPIRE_BLOODS.stream()),
					Stream.concat(EMPIRE_GURISTAS.stream(), Stream.concat(EMPIRE_SANSHAS.stream(), EMPIRE_SERPENTIS.stream())))
			.collect(Collectors.toSet()));

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

			@Override
			public int escalHideway() {
				return -1;
			}

			@Override
			public int escalRefuge() {
				return 5;
			}
		},
		bloods {
			@Override
			public Set<String> regions() {
				return EMPIRE_BLOODS;
			}

			@Override
			public int escalHideway() {
				return -1;
			}
		},
		guristas {
			@Override
			public Set<String> regions() {
				return EMPIRE_GURISTAS;
			}

			@Override
			public int escalHideway() {
				return 4;
			}
		},
		sanshas {
			@Override
			public Set<String> regions() {
				return EMPIRE_SANSHAS;
			}

			@Override
			public int escalRefuge() {
				return 3;
			}
		},
		serpentis {
			@Override
			public Set<String> regions() {
				return EMPIRE_SERPENTIS;
			}

			@Override
			public int escalRefuge() {
				return 3;
			}
		};

		// chances of anom per system (base 1)

		public double chanceDen() {
			return 0.4;
		}

		public double chanceRefuge() {
			return 1;
		}

		public double chanceHideway() {
			return 0.8;
		}

		public double chanceBurrow() {
			return 0.8;
		}

		// escalation level or -1 if none

		public int escalDen() {
			return 5;
		}

		public int escalRefuge() {
			return 4;
		}

		public int escalHideway() {
			return 3;
		}

		public int escalBurrow() {
			return -1;
		}

		// ded evaluation. Basically the average isk gain in M

		public double ded1Points() {
			return 10;
		}

		public double ded2Points() {
			return 20;
		}

		public double ded3Points() {
			return 50;
		}

		public double ded4Points() {
			return 100;
		}

		public double ded5Points() {
			return 250;
		}

		public float ded6Points() {
			return 10;
		}

		public float ded7Points() {
			return 10;
		}

		public float ded8Points() {
			return 10;
		}

		public float ded9Points() {
			return 10;
		}

		public float ded10Points() {
			return 10;
		}

		public double dedPoints(int dedLvl) {
			if (dedLvl == 1) {
				return ded1Points();
			}
			if (dedLvl == 2) {
				return ded2Points();
			}
			if (dedLvl == 3) {
				return ded3Points();
			}
			if (dedLvl == 4) {
				return ded4Points();
			}
			if (dedLvl == 5) {
				return ded5Points();
			}
			if (dedLvl == 6) {
				return ded6Points();
			}
			if (dedLvl == 7) {
				return ded7Points();
			}
			if (dedLvl == 8) {
				return ded8Points();
			}
			if (dedLvl == 9) {
				return ded9Points();
			}
			if (dedLvl == 10) {
				return ded10Points();
			}
			return 0.0f;
		}

		/**
		 *
		 * @return points for .5, .6, .7, .8, .9 and 1.0 ss
		 */
		public double[] escalHSPoints() {
			double[] points = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

			// dens are in .5 and .6
			double sitesPoints = chanceDen() * dedPoints(escalDen());
			if (sitesPoints > 0) {
				points[0] += sitesPoints;
				points[1] += sitesPoints;
			}

			// refuges are .5 to .9
			sitesPoints = chanceRefuge() * dedPoints(escalRefuge());
			if (sitesPoints > 0) {
				points[0] += sitesPoints;
				points[1] += sitesPoints;
				points[2] += sitesPoints;
				points[3] += sitesPoints;
				points[4] += sitesPoints;
			}

			// hideways are .6 to 1.0
			sitesPoints = chanceHideway() * dedPoints(escalHideway());
			if (sitesPoints > 0) {
				points[1] += sitesPoints;
				points[2] += sitesPoints;
				points[3] += sitesPoints;
				points[4] += sitesPoints;
				points[5] += sitesPoints;
			}

			// burrows are in .9 and 1.0
			sitesPoints = chanceBurrow() * dedPoints(escalBurrow());
			if (sitesPoints > 0) {
				points[4] += sitesPoints;
				points[5] += sitesPoints;
			}

			// double maxPoints = DoubleStream.of(points).max().getAsDouble();
			// for (int i = 0; i < points.length; i++) {
			// points[i] /= maxPoints;
			// }
			return points;
		}

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

}
