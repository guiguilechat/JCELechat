package fr.guiguilechat.eveonline.sde.fsd;

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

	public LinkedHashMap<Long, Object> planets;
	public Object star;
	public int sunTypeID;
	public LinkedHashMap<Integer, Stargate> stargates = new LinkedHashMap<>();

	public static class Stargate {

		public int destination;
		public ArrayList<Double> position;
		public int typeID;
	}

}
