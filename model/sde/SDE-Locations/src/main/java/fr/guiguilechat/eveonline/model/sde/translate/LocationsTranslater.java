package fr.guiguilechat.eveonline.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.Tools;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EstaOperationServices;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EstaServices;
import fr.guiguilechat.eveonline.model.sde.load.bsd.EstaStations;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.model.sde.load.fsd.Universe;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem.Moon;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem.NPCStation;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem.Planet;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem.Stargate;
import fr.guiguilechat.eveonline.model.sde.locations.Constellation;
import fr.guiguilechat.eveonline.model.sde.locations.Region;
import fr.guiguilechat.eveonline.model.sde.locations.SolarSystem;
import fr.guiguilechat.eveonline.model.sde.locations.Station;

/**
 * translate the locations from the sde in specific collection.<br />
 * Don't call this class outside of the SDE package, as it relies on
 * SDE-compiler which is optional.<br />
 * If I could remove that class from package, that's would be better.
 */
public class LocationsTranslater {

	/**
	 *
	 * @param args
	 *          should be [database destination root], typically
	 *          src/generated/resources/
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		Tools.delDir(folderOut);
		folderOut.mkdirs();

		LinkedHashMap<String, Region> regions = new LinkedHashMap<>();
		LinkedHashMap<String, Constellation> constellations = new LinkedHashMap<>();
		LinkedHashMap<String, SolarSystem> systems = new LinkedHashMap<>();
		LinkedHashMap<String, Station> stations = new LinkedHashMap<>();

		translate(regions, constellations, systems, stations);

		// sort

		Stream.of(regions, constellations, systems, stations).forEach(m -> {
			ArrayList<Entry<String, ?>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
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

		System.err.println("exported locations in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");


	}

	public static void translate(LinkedHashMap<String, Region> regions,
			LinkedHashMap<String, Constellation> constellations, LinkedHashMap<String, SolarSystem> systems,
			LinkedHashMap<String, Station> stations) {
		Universe uni = Universe.load();
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region> e : uni.eve.entrySet()) {
			addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, false);
		}
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region> e : uni.wormhole.entrySet()) {
			addRegion(e.getKey(), e.getValue(), regions, constellations, systems, stations, true);
		}

		// fill all the adjacent systems, constellations, regions
		// translate all the gates to their system
		HashMap<Integer, String> gateToSystem = new HashMap<>();
		Stream.of(uni.eve, uni.wormhole).flatMap(e -> e.values().stream()).flatMap(r -> r.constellations.values().stream())
		.flatMap(c -> c.systems.entrySet().stream()).forEach(e -> {
			for (Integer sid : e.getValue().stargates.keySet()) {
				gateToSystem.put(sid, e.getKey());
			}
		});

		Stream.of(uni.eve, uni.wormhole).flatMap(e -> e.values().stream()).flatMap(r -> r.constellations.values().stream())
		.flatMap(c -> c.systems.entrySet().stream()).forEach(e -> {
			SolarSystem oSystem = systems.get(e.getKey());
			Constellation oConstel = constellations.get(oSystem.constellation);
			Region oRegion = regions.get(oSystem.region);
			for (Stargate st : e.getValue().stargates.values()) {
				String destoName = gateToSystem.get(st.destination);
				{
					oSystem.adjacentSystems.add(destoName);
				}
				SolarSystem destination = systems.get(destoName);
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

	}

	public static void addRegion(String name, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region region,
			LinkedHashMap<String, Region> regions, LinkedHashMap<String, Constellation> constellations,
			LinkedHashMap<String, SolarSystem> systems, LinkedHashMap<String, Station> stations, boolean isWormhole) {
		Region r = new Region();
		regions.put(name, r);
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Constellation> e : region.constellations
				.entrySet()) {
			r.constellations.add(e.getKey());
			addConstellation(e.getKey(), e.getValue(), name, constellations, systems, stations, isWormhole);
		}
		r.name = name;
		r.id = region.regionID;
		r.isWormhole = isWormhole;
	}

	public static Constellation addConstellation(String name,
			fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Constellation constellation, String regionName,
			LinkedHashMap<String, Constellation> constellations, LinkedHashMap<String, SolarSystem> systems,
			LinkedHashMap<String, Station> stations,
			boolean isWormhole) {
		Constellation c = new Constellation();
		constellations.put(name, c);
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem> e : constellation.systems
				.entrySet()) {
			c.systems.add(e.getKey());
			SolarSystem sys = addSystem(e.getKey(), e.getValue(), name, regionName, systems, stations, isWormhole);
			c.hasBorder |= sys.isBorder;
			c.hasCorridor |= sys.isCorridor;
			c.hasFringe |= sys.isFringe;
			c.hasHub |= sys.isHub;
		}
		c.name = name;
		c.id = constellation.constellationID;
		c.isWormhole = isWormhole;
		return c;
	}

	public static SolarSystem addSystem(String name,
			fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem system, String ConstellationName,
			String regionName, LinkedHashMap<String, SolarSystem> systems, LinkedHashMap<String, Station> stations,
			boolean isWormhole) {
		SolarSystem s = new SolarSystem();
		systems.put(name, s);
		s.name = name;
		s.id = system.solarSystemID;
		s.constellation = ConstellationName;
		s.region = regionName;
		s.truesec = system.security;
		s.isWormhole = isWormhole;
		s.isBorder = system.border;
		s.isCorridor = system.corridor;
		s.isFringe = system.fringe;
		s.isHub = system.hub;

		for (Entry<Long, Planet> ePlanet : system.planets.entrySet()) {
			for (Entry<Integer, NPCStation> eSta : ePlanet.getValue().npcStations.entrySet()) {
				addStation(eSta.getValue(), eSta.getKey(), name, stations);
			}
			for (Entry<Integer, Moon> e4 : ePlanet.getValue().moons.entrySet()) {
				for (Entry<Integer, NPCStation> eSta : e4.getValue().npcStations.entrySet()) {
					addStation(eSta.getValue(), eSta.getKey(), name, stations);
				}
			}
		}
		return s;
	}

	public static Station addStation(NPCStation npcsta, int id, String solarSystemName,
			LinkedHashMap<String, Station> stations) {
		Station added = new Station();
		added.id = id;
		added.solarSystem = solarSystemName;
		added.services.addAll(operationServices().get(npcsta.operationID));
		EtypeIDs type = EtypeIDs.load().get(id);
		added.name = type == null ? "missing_" + id : type.enName();
		stations.put(stationsByID().get(id).stationName, added);
		return added;
	}

	private static Map<Integer, Set<String>> operationServices = null;

	protected static Map<Integer, Set<String>> operationServices() {
		if (operationServices == null) {
			Map<Integer, String> servicesnames = EstaServices.loadById();
			operationServices = EstaOperationServices.loadByOperationId().entrySet().stream().collect(Collectors
					.toMap(e -> e.getKey(), e -> e.getValue().stream().map(servicesnames::get).collect(Collectors.toSet())));
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
}
