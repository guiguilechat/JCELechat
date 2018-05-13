package fr.guiguilechat.eveonline.model.sde.load.fsd.universe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.model.sde.load.SDECache;

public class SolarSystem {

	public ArrayList<Double> center = new ArrayList<>();
	public ArrayList<Double> max = new ArrayList<>();
	public ArrayList<Double> min = new ArrayList<>();
	public double radius;
	public double luminosity;
	public double security;
	public String securityClass;
	public int solarSystemID;
	public int solarSystemNameID;
	public int descriptionID;
	public int wormholeClassID;
	public int factionID;

	public boolean border = false;
	public boolean corridor = false;
	public boolean fringe = false;
	public boolean hub = false;
	public boolean international = false;
	public boolean regional = false;

	public ArrayList<Integer> disallowedAnchorCategories = new ArrayList<>();
	public ArrayList<Integer> disallowedAnchorGroups = new ArrayList<>();

	// for wormhole only ?
	public SecondarySun secondarySun;
	public String visualEffect;

	public static class Planet {
		public int typeID, celestialIndex, radius;
		public Object planetAttributes, position, statistics;

		public int planetNameID;

		public LinkedHashMap<Integer, Moon> moons = new LinkedHashMap<>();

		public LinkedHashMap<Integer, AsteroidBelt> asteroidBelts = new LinkedHashMap<>();

		public LinkedHashMap<Integer, NPCStation> npcStations = new LinkedHashMap<>();
	}

	public static class Moon {
		public int typeID, celestialIndex, radius;
		public Object planetAttributes, position, statistics;

		public int moonNameID;

		public LinkedHashMap<Integer, AsteroidBelt> asteroidBelts = new LinkedHashMap<>();

		public LinkedHashMap<Integer, NPCStation> npcStations = new LinkedHashMap<>();

	}

	public static class AsteroidBelt {
		public int typeID;
		public Object position, statistics;

		public int asteroidBeltNameID;
	}

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

		public Object position, statistics;
	}

	public LinkedHashMap<Long, Planet> planets = new LinkedHashMap<>();
	public Object star;
	public int sunTypeID;
	public LinkedHashMap<Integer, Stargate> stargates = new LinkedHashMap<>();

	public static class Stargate {
		public int destination;
		public ArrayList<Double> position;
		public int typeID;
	}

	public static class SecondarySun {
		public int effectBeaconTypeID;
		public int itemID;
		public double[] position;
		public int typeID;
	}

	public static SolarSystem load(File systemDir) {
		File[] data = systemDir.listFiles((d, name) -> name.equals("solarsystem.staticdata"));
		if (data == null || data.length != 1 || !data[0].exists() || !data[0].isFile()) {
			throw new UnsupportedOperationException(
					"while looking for one file of system data, found " + Arrays.asList(data));
		}
		try {
			return new Yaml().loadAs(SDECache.fileReader(data[0]), SolarSystem.class);
		} catch (Exception e) {
			throw new UnsupportedOperationException("while loading solarsystem from directory " + systemDir.getAbsolutePath(),
					e);
		}
	}

}
