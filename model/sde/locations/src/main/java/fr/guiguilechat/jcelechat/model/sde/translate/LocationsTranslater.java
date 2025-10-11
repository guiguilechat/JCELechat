package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EinvNames;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EstaStations;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EstationOperations;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EstationServices;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Universe;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.Moon;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.NPCStation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.Planet;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem.Stargate;
import fr.guiguilechat.jcelechat.model.sde.locations.Constellation;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.Station;

/**
 * translate the locations from the sde in specific collection.<br />
 * Don't call this class outside of the SDE package, as it relies on
 * SDE-compiler which is optional.<br />
 * If I could remove that class from package, that's would be better.
 */
public class LocationsTranslater {

	public static final Logger logger = LoggerFactory.getLogger(LocationsTranslater.class);

	public enum REGION_TYPE {
		ABYSSAL, KS, JOVIAN, PENALTY, POCHVEN, STARTER, WORMHOLE,
	}

	/**
	 *
	 * @param args
	 *          should be [database destination root], typically
	 *          src/generated/resources/
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		FileTools.delDir(folderOut);
		folderOut.mkdirs();

		LinkedHashMap<String, Region> regions = new LinkedHashMap<>();
		LinkedHashMap<String, Constellation> constellations = new LinkedHashMap<>();
		LinkedHashMap<String, SolarSystem> systems = new LinkedHashMap<>();
		LinkedHashMap<String, Station> stations = new LinkedHashMap<>();

		translate(regions, constellations, systems, stations);

		// sort

		Stream.of(regions, constellations, systems, stations).forEach(m -> {
			ArrayList<Entry<String, ?>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, Comparator.comparing(Entry<String, ?>::getKey));
			m.clear();
			for (Entry<String, ?> e : list) {
				((Map<String, Object>) m).put(e.getKey(), e.getValue());
			}
		});

		// save

		Region.export(regions, folderOut);
		Constellation.export(constellations, folderOut);
		SolarSystem.export(systems, folderOut);
		Station.export(stations, folderOut);

		logger.info("exported locations in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	public static void translate(
			LinkedHashMap<String, Region> regions,
			LinkedHashMap<String, Constellation> constellations,
			LinkedHashMap<String, SolarSystem> systems,
			LinkedHashMap<String, Station> stations) {
		Universe uni = Universe.load();

		uni.abyssal.entrySet().stream().forEach(
				e -> addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, REGION_TYPE.ABYSSAL));
		uni.eve.entrySet().stream()
		.forEach(e -> addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, REGION_TYPE.KS));
		uni.starter.entrySet().stream().forEach(
				e -> addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, REGION_TYPE.STARTER));
		uni.wormhole.entrySet().stream().forEach(
				e -> addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, REGION_TYPE.WORMHOLE));

		// fill all the adjacent systems, constellations, regions
		// translate all the gates to their system
		HashMap<Integer, String> gateToSystem = new HashMap<>();
		Stream.of(uni.eve, uni.wormhole).flatMap(e -> e.values().stream()).flatMap(r -> r.constellations.values().stream())
		.flatMap(c -> c.systems.entrySet().stream()).forEach(e -> {
			for (Integer sid : e.getValue().stargates.keySet()) {
				gateToSystem.put(sid, unFuckLocationName(e.getKey()));
			}
		});

		Stream.of(uni.eve, uni.wormhole).parallel().flatMap(e -> e.values().stream())
		.flatMap(r -> r.constellations.values().stream()).flatMap(c -> c.systems.entrySet().stream())
		.forEachOrdered(e -> {
			SolarSystem oSystem = systems.get(unFuckLocationName(e.getKey()));
			if (oSystem == null) {
				System.err.println("null syst for name " + e.getKey());
			}

			Constellation oConstel = constellations.get(oSystem.constellation);
			Region oRegion = regions.get(oSystem.region);
			for (Stargate st : e.getValue().stargates.values()) {
				String destoName = gateToSystem.get(st.destination);
				{
					oSystem.adjacentSystems.add(destoName);
				}
				SolarSystem destination = systems.get(destoName);
				if (destination == null) {
					System.err.println("null dest for syst name " + destoName);
				}
				if (!oSystem.constellation.equals(destination.constellation)) {
					if (!oSystem.adjacentConstellations.contains(destination.constellation)) {
						oSystem.adjacentConstellations.add(destination.constellation);
					}
					if (!oConstel.adjacentConstellations.contains(destination.constellation)) {
						oConstel.adjacentConstellations.add(destination.constellation);
					}
					if (!oConstel.adjacentSystems.contains(destoName)) {
						oConstel.adjacentSystems.add(destoName);
					}
				}
				if (!oSystem.region.equals(destination.region)) {
					if (!oSystem.adjacentRegions.contains(destination.region)) {
						oSystem.adjacentRegions.add(destination.region);
					}
					if (!oConstel.adjacentRegions.contains(destination.region)) {
						oConstel.adjacentRegions.add(destination.region);
					}
					if (!oRegion.adjacentSystems.contains(destoName)) {
						oRegion.adjacentSystems.add(destoName);
					}
					if (!oRegion.adjacentRegions.contains(destination.region)) {
						oRegion.adjacentRegions.add(destination.region);
					}
					if (!oRegion.adjacentConstellations.contains(destination.constellation)) {
						oRegion.adjacentConstellations.add(destination.constellation);
					}
				}
			}

		});
		Stream.concat(regions.values().stream(), Stream.concat(constellations.values().stream(), systems.values().stream()))
		.forEach(l -> {
			l.adjacentSystems.sort(Comparator.naturalOrder());
			l.adjacentConstellations.sort(Comparator.naturalOrder());
			l.adjacentRegions.sort(Comparator.naturalOrder());
		});
	}

	public static void addRegion(String regionName, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ERegion region,
			LinkedHashMap<String, Region> regions, LinkedHashMap<String, Constellation> constellations,
			LinkedHashMap<String, SolarSystem> systems, LinkedHashMap<String, Station> stations, REGION_TYPE rtype) {
		regionName = unFuckLocationName(regionName);
		Region r = new Region();
		regions.put(regionName, r);
		// set jovian KS to specific JOVIAN type
		if (rtype == REGION_TYPE.KS) {
			if (
					// A821-A
					region.regionID == 10000019
					// J7HZ-F is jove ?
					|| region.regionID == 10000017
					// UUA-F4
					|| region.regionID == 10000004) {
				rtype = REGION_TYPE.JOVIAN;
			}
			if (region.regionID == 10000070) {
				rtype=REGION_TYPE.POCHVEN;
			}
		}
		for (Entry<String, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.EConstellation> e : region.constellations
				.entrySet()) {
			Constellation constel = addConstellation(e.getKey(), e.getValue(), regionName, constellations, systems, stations,
					rtype);
			r.constellations.add(constel.name);
		}
		r.isAbyssal = rtype == REGION_TYPE.ABYSSAL;
		r.isJovian = rtype == REGION_TYPE.JOVIAN;
		r.isKS = rtype == REGION_TYPE.KS;
		r.isPochven = rtype == REGION_TYPE.POCHVEN;
		r.isWormhole = rtype == REGION_TYPE.WORMHOLE;
		r.name = regionName;
		r.id = region.regionID;
	}

	public static Constellation addConstellation(String constelName,
			fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.EConstellation constellation, String regionName,
			LinkedHashMap<String, Constellation> constellations, LinkedHashMap<String, SolarSystem> systems,
			LinkedHashMap<String, Station> stations, REGION_TYPE rtype) {
		constelName = unFuckLocationName(constelName);
		Constellation c = new Constellation();
		constellations.put(constelName, c);
		for (Entry<String, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem> e : constellation.systems
				.entrySet()) {
			SolarSystem sys = addSystem(e.getKey(), e.getValue(), constelName, regionName, systems, stations, rtype);
			c.hasBorder |= sys.isBorder;
			c.hasCorridor |= sys.isCorridor;
			c.hasFringe |= sys.isFringe;
			c.hasHub |= sys.isHub;
			c.systems.add(sys.name);
		}
		c.name = constelName;
		c.id = constellation.constellationID;
		c.region = regionName;
		c.isAbyssal = rtype == REGION_TYPE.ABYSSAL;
		c.isJovian = rtype == REGION_TYPE.JOVIAN;
		c.isKS = rtype == REGION_TYPE.KS;
		c.isPochven = rtype == REGION_TYPE.POCHVEN;
		c.isWormhole = rtype == REGION_TYPE.WORMHOLE;
		return c;
	}

	public static SolarSystem addSystem(String sysName,
			fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.ESolarSystem system, String ConstellationName,
			String regionName, LinkedHashMap<String, SolarSystem> systems, LinkedHashMap<String, Station> stations,
			REGION_TYPE rtype) {
		sysName = unFuckLocationName(sysName);
		SolarSystem s = new SolarSystem();
		systems.put(sysName, s);
		s.name = sysName;
		s.id = system.solarSystemID;
		s.constellation = ConstellationName;
		s.region = regionName;
		s.truesec = system.security;
		s.isAbyssal = rtype == REGION_TYPE.ABYSSAL;
		s.isJovian = rtype == REGION_TYPE.JOVIAN;
		s.isKS = rtype == REGION_TYPE.KS;
		s.isPochven = rtype == REGION_TYPE.POCHVEN;
		s.isWormhole = rtype == REGION_TYPE.WORMHOLE;
		s.isBorder = system.border;
		s.isCorridor = system.corridor;
		s.isFringe = system.fringe;
		s.isHub = system.hub;
		s.anchorStructures = system.disallowedAnchorCategories == null || system.disallowedAnchorCategories.isEmpty()
				|| !system.disallowedAnchorCategories.contains(65);

		for (Entry<Long, Planet> ePlanet : system.planets.entrySet()) {
			for (Entry<Integer, NPCStation> eSta : ePlanet.getValue().npcStations.entrySet()) {
				addStation(eSta.getValue(), eSta.getKey(), sysName, stations);
				if (addStation(eSta.getValue(), eSta.getKey(), sysName, stations) == null) {
					logger.error("can't create station sysName=" + sysName + " id=" + eSta.getKey());
				}
			}
			for (Entry<Integer, Moon> e4 : ePlanet.getValue().moons.entrySet()) {
				for (Entry<Integer, NPCStation> eSta : e4.getValue().npcStations.entrySet()) {
					if (addStation(eSta.getValue(), eSta.getKey(), sysName, stations) == null) {
						logger.error("can't create station name=" + sysName + " id=" + eSta.getKey());
					}
				}
			}
		}
		if (system.center != null) {
			s.centerX = system.center.x();
			s.centerY = system.center.y();
			s.centerZ = system.center.z();
		}
		if (system.max != null) {
			s.maxX = system.max.x();
			s.maxY = system.max.y();
			s.maxZ = system.max.z();
		}
		if (system.min != null) {
			s.minX = system.min.x();
			s.minY = system.min.y();
			s.minZ = system.min.z();
		}
		return s;
	}

	/**
	 * translates an existing NPC station entry from SDE into a station to export,
	 * add it to the stations lists
	 *
	 * @return null if an issue occurred. In this case, the station is not added
	 */
	public static Station addStation(NPCStation npcsta, int id, String solarSystemName,
			LinkedHashMap<String, Station> stations) {
		Station added = new Station();
		added.id = id;
		added.solarSystem = solarSystemName;
		added.services.addAll(operationServices().get(npcsta.operationID));
		added.services.removeIf(s -> s == null || s.length() == 0);
		Collections.sort(added.services);
		EstaStations esta = stationsByID().get(id);
		if (esta == null) {
			logger.error("station id " + id + " can't be retrieved from " + EstaStations.SDE_FILE);
			return null;
		}
		added.name = EinvNames.loadById().get(id);
		if (added.name == null) {
			added.name = solarSystemName + "_" + id;
		}
		stations.put(added.name, added);
		return added;
	}

	private static Map<Integer, Set<String>> operationServices = null;

	protected static synchronized Map<Integer, Set<String>> operationServices() {
		if (operationServices == null) {
			Map<Integer, String> servicesnames = EstationServices.LOADER.load().entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, e -> e.getValue().enName()));
			operationServices = EstationOperations.LOADER.load().entrySet().stream().collect(Collectors.toMap(
					Entry::getKey,
					e -> IntStream.of(e.getValue().services).mapToObj(servicesnames::get).collect(Collectors.toSet())));
		}
		return operationServices;
	}

	private static Map<Integer, EstaStations> stationsByID = null;

	protected static Map<Integer, EstaStations> stationsByID() {
		if (stationsByID == null) {
			stationsByID = EstaStations.loadById();
		}
		return stationsByID;
	}

	/**
	 * unfuck a location name provided by CCP in the SDE .
	 * <ul>
	 * <li>replace "aB" by "a B" because SDE location names miss the spaces.</li>
	 * </ul>
	 *
	 * @param name
	 * @return
	 */
	public static String unFuckLocationName(String name) {
		String ret = name.replaceAll("([a-z])([A-Z])", "$1\\ $2");
		ret = ret.replaceAll("ofthe", " of the");
		// if (!ret.equals(name)) {
		// System.err.println("unfucked [" + name + "] into [" + ret + "]");
		// }
		return ret;
	}
}
