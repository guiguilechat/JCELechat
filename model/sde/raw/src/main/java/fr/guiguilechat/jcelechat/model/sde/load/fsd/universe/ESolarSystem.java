package fr.guiguilechat.jcelechat.model.sde.load.fsd.universe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class ESolarSystem {

	public boolean border = false;
	public Position center ;
	public boolean corridor = false;
	public int descriptionID;
	public ArrayList<Integer> disallowedAnchorCategories = new ArrayList<>();
	public ArrayList<Integer> disallowedAnchorGroups = new ArrayList<>();
	public int factionID;
	public boolean fringe = false;
	public boolean hub = false;
	public boolean international = false;
	public double luminosity;
	public Position max;
	public Position min;
	public LinkedHashMap<Long, Planet> planets = new LinkedHashMap<>();
	public double radius;
	public boolean regional = false;
	// for wormhole only ?
	public SecondarySun secondarySun;
	public double security;
	public String securityClass;
	public int solarSystemID;
	public int solarSystemNameID;
	public Object star;
	public LinkedHashMap<Integer, Stargate> stargates = new LinkedHashMap<>();
	public int sunTypeID;
	public String visualEffect;
	public int wormholeClassID;

	public static class Planet {
		public int typeID, celestialIndex, radius;
		public Object planetAttributes, statistics;
		public Position position;

		public int planetNameID;

		public LinkedHashMap<Integer, Moon> moons = new LinkedHashMap<>();

		public LinkedHashMap<Integer, AsteroidBelt> asteroidBelts = new LinkedHashMap<>();

		public LinkedHashMap<Integer, NPCStation> npcStations = new LinkedHashMap<>();
	}

	public static class Moon {
		public int typeID, celestialIndex;
		public double radius;
		public Object planetAttributes, statistics;
		public Position position;

		public int moonNameID;

		public LinkedHashMap<Integer, AsteroidBelt> asteroidBelts = new LinkedHashMap<>();

		public LinkedHashMap<Integer, NPCStation> npcStations = new LinkedHashMap<>();

	}

	public static class AsteroidBelt {
		public int typeID;
		public Position position;
		public Object statistics;

		public int asteroidBeltNameID;
	}

	/**
	 * existing NPC station. Has a type, a position, and specific attributes
	 */
	public static class NPCStation {
		public int typeID;
		public int graphicID;

		public int operationID;

		public int ownerID;

		public double reprocessingEfficiency;

		public int reprocessingHangarFlag;

		public double reprocessingStationsTake;

		public boolean useOperationName;

		public boolean isConquerable;

		public Position position;
		public Object statistics;
	}

	public static class Stargate {
		public int destination;
		public Position position;
		public int typeID;
	}

	public static class SecondarySun {
		public int effectBeaconTypeID;
		public int itemID;
		public Position position;
		public int typeID;
	}

	public double furthestCelestialAU() {
		return planets.values().stream()
				.flatMap(p -> Stream.concat(Stream.of(p.position), p.moons.values().stream().map(m -> m.position)))
				.mapToDouble(Position::distanceFromOriginInAU).max().orElse(0.0);
	}

	public static ESolarSystem load(File systemDir) {
		File[] data = systemDir.listFiles((d, name) -> name.equals("solarsystem.yaml"));
		if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
			throw new UnsupportedOperationException(
					"while looking for one file of system data, found " + Arrays.asList(data));
		}
		try {
			return new Yaml().loadAs(SDECache.fileReader(data[0]), ESolarSystem.class);
		} catch (Exception e) {
			throw new UnsupportedOperationException("while loading solarsystem from directory " + systemDir.getAbsolutePath(),
					e);
		}
	}

}
