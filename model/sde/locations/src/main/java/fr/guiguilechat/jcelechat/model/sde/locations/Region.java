package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

import fr.lelouet.tools.application.yaml.CleanRepresenter;
import fr.lelouet.tools.application.yaml.YAMLTools;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Region extends ALocation {

	// loading/dumping

	public static final String RESOURCE_PATH = "SDE/locations/regions.yaml";

	private static final class Container {
		public LinkedHashMap<String, Region> locations;
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

	protected static LinkedHashMap<String, Region> loadFile() {
		try (InputStreamReader reader = new InputStreamReader(
		    Region.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
			return new Yaml().loadAs(reader, Container.class).locations;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final LinkedHashMap<String, Region> load = loadFile();

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<Integer, Region> loadById = load().values().stream()
	    .collect(Collectors.toMap(e -> e.id, e -> e));

	public static Region getRegion(int id) {
		return loadById().get(id);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Map<String, Region> loadLowerCase = load().values().stream().collect(Collectors.toMap(
	    r -> r.name().toLowerCase(),
	    r -> r));

	public static Region getRegion(String name) {
		if (name == null) {
			return null;
		}
		return loadLowerCase().get(name.toLowerCase());
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
			switch (dedLvl) {
			case 1:
				return ded1Points();
			case 2:
				return ded2Points();
			case 3:
				return ded3Points();
			case 4:
				return ded4Points();
			case 5:
				return ded5Points();
			case 6:
				return ded6Points();
			case 7:
				return ded7Points();
			case 8:
				return ded8Points();
			case 9:
				return ded9Points();
			case 10:
				return ded10Points();
			default:
				break;
			}
			return 0.0f;
		}

		/**
		 * @return points for .5, .6, .7, .8, .9 and 1.0 ss
		 */
		public double[] escalHSPoints() {
			double[] points = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

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
				    .filter(SolarSystem::isHS).collect(Collectors.toUnmodifiableSet());
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

	/**
	 * sorted list of all regions
	 */
	@Getter(lazy = true)
	private static final List<Region> all = load().values().stream()
	    .sorted(Comparator.comparing(Region::name))
	    .toList();

	/**
	 * sorted list of all regions with an added null first one
	 */
	@Getter(lazy = true)
	private static final List<Region> allNullLeading = Stream.concat(
	    Stream.of((Region) null),
	    load().values().stream()
	        .sorted(Comparator.comparing(Region::name)))
	    .toList();

	/**
	 * sorted list of known space regions
	 */
	@Getter(lazy = true)
	private static final List<Region> ks = load().values().stream()
	    .filter(r -> !r.isWormhole)
	    .sorted(Comparator.comparing(Region::name))
	    .toList();

	/**
	 * sorted list of known space regions with an added null first one
	 */
	@Getter(lazy = true)
	private static final List<Region> ksNullLeading = Stream.concat(
	    Stream.of((Region) null),
	    load().values().stream()
	        .filter(r -> !r.isWormhole)
	        .sorted(Comparator.comparing(Region::name)))
	    .toList();

}
