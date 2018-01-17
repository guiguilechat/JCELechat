package fr.guiguilechat.eveonline.model.sde.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.sde.load.fsd.Universe;
import fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem.Stargate;
import fr.guiguilechat.eveonline.model.sde.locations.ALocation;
import fr.guiguilechat.eveonline.model.sde.locations.Constellation;
import fr.guiguilechat.eveonline.model.sde.locations.Region;
import fr.guiguilechat.eveonline.model.sde.locations.SolarSystem;

/**
 * translate the locations from the sde in specific collection.<br />
 * Don't call this class outside of the SDE package, as it relies on
 * SDE-compiler which is optional.<br />
 * If I could remove that class from package, that's would be better.
 */
public class LocationTranslater {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File("src/generated/resources/");
		folderOut.mkdirs();

		Universe uni = Universe.load();
		LinkedHashMap<String, Region> regions = new LinkedHashMap<>();
		LinkedHashMap<String, Constellation> constellations = new LinkedHashMap<>();
		LinkedHashMap<String, SolarSystem> systems = new LinkedHashMap<>();

		translate(uni, regions, constellations, systems);

		// sort

		Stream.of(regions, constellations, systems).forEach(m -> {
			ArrayList<Entry<String, ? extends ALocation>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			m.clear();
			for (Entry<String, ? extends ALocation> e : list) {
				((Map<String, ALocation>) m).put(e.getKey(), e.getValue());
			}
		});

		// export those locations
		Region.export(regions, new File(folderOut, Region.RESOURCE_PATH));
		Constellation.export(constellations, new File(folderOut, Constellation.RESOURCE_PATH));
		SolarSystem.export(systems, new File(folderOut, SolarSystem.RESOURCE_PATH));

		System.err.println("exported locations in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");
	}

	public static void translate(Universe uni, LinkedHashMap<String, Region> regions,
			LinkedHashMap<String, Constellation> constellations, LinkedHashMap<String, SolarSystem> systems) {
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region> e : uni.eve.entrySet()) {
			addRegion(e.getKey(), e.getValue(), regions, constellations, systems, false);
		}
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Region> e : uni.wormhole.entrySet()) {
			addRegion(e.getKey(), e.getValue(), regions, constellations, systems, true);
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
		// Map<Integer, String> revRegions = regions.entrySet().stream()
		// .collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
		// Map<Integer, String> revConstels = constellations.entrySet().stream()
		// .collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
		// Map<Integer, String> resystems = systems.entrySet().stream()
		// .collect(Collectors.toMap(e -> e.getValue().id, e -> e.getKey()));
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
			LinkedHashMap<String, SolarSystem> systems, boolean isWormhole) {
		Region r = new Region();
		regions.put(name, r);
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Constellation> e : region.constellations
				.entrySet()) {
			r.constellations.add(e.getKey());
			addConstellation(e.getKey(), e.getValue(), name, constellations, systems, isWormhole);
		}
		r.id = region.regionID;
		r.isWormhole = isWormhole;
	}

	public static Constellation addConstellation(String name,
			fr.guiguilechat.eveonline.model.sde.load.fsd.universe.Constellation constellation, String regionName,
			LinkedHashMap<String, Constellation> constellations, LinkedHashMap<String, SolarSystem> systems,
			boolean isWormhole) {
		Constellation c = new Constellation();
		constellations.put(name, c);
		for (Entry<String, fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem> e : constellation.systems
				.entrySet()) {
			c.systems.add(e.getKey());
			SolarSystem sys = addSystem(e.getKey(), e.getValue(), name, regionName, systems, isWormhole);
			c.hasBorder |= sys.isBorder;
			c.hasCorridor |= sys.isCorridor;
			c.hasFringe |= sys.isFringe;
			c.hasHub |= sys.isHub;
		}
		c.id = constellation.constellationID;
		c.isWormhole = isWormhole;
		return c;
	}

	public static SolarSystem addSystem(String name,
			fr.guiguilechat.eveonline.model.sde.load.fsd.universe.SolarSystem system, String ConstellationName,
			String regionName, LinkedHashMap<String, SolarSystem> systems, boolean isWormhole) {
		SolarSystem s = new SolarSystem();
		systems.put(name, s);
		s.id = system.solarSystemID;
		s.constellation = ConstellationName;
		s.region = regionName;
		s.truesec = system.security;
		s.isWormhole = isWormhole;
		s.isBorder = system.border;
		s.isCorridor = system.corridor;
		s.isFringe = system.fringe;
		s.isHub = system.hub;

		return s;
	}

}
