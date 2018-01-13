package fr.guiguilechat.eveonline.model.sde.load.fsd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SolarSystemStaticData {

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

	public boolean border;
	public boolean corridor;
	public boolean fringe;
	public boolean hub;
	public boolean international;
	public boolean regional;

	public ArrayList<String> disallowedAnchorCategories = new ArrayList<>();

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

}
