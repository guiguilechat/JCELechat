package fr.guiguilechat.eveonline.model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.model.apiv2.Eve;
import fr.guiguilechat.eveonline.model.database.yaml.Agent;
import fr.guiguilechat.eveonline.model.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.model.database.yaml.Hull;
import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.model.database.yaml.Location;
import fr.guiguilechat.eveonline.model.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.model.database.yaml.Module;
import fr.guiguilechat.eveonline.model.database.yaml.Station;
import fr.guiguilechat.eveonline.model.database.yaml.Type;
import fr.guiguilechat.eveonline.model.esi.ESIBasePrices;
import fr.guiguilechat.eveonline.model.esi.ESIMarket;

public abstract class EveDatabase {

	public abstract LinkedHashMap<String, Hull> getHulls();

	public abstract LinkedHashMap<String, Module> getModules();

	public abstract LinkedHashMap<String, Asteroid> getAsteroids();

	public abstract LinkedHashMap<String, Blueprint> getBlueprints();

	public abstract LinkedHashMap<String, Location> getLocations();

	public abstract LinkedHashMap<String, Station> getStations();

	public String normalizeLocName(String name) {
		return name.replaceAll(" ", "").toLowerCase();
	}

	public Location getLocation(String name) {
		if (name == null) {
			return null;
		}
		return getLocations().get(normalizeLocName(name));
	}

	protected HashSet<String> hubsNames = null;

	/**
	 *
	 * @param locName
	 *          name of a location
	 * @return true iff corresponding system/constel/region has a hub. for system,
	 *         true iff system IS hub.
	 */
	public boolean containsHub(String locName) {
		locName = normalizeLocName(locName);
		if (hubsNames == null) {
			hubsNames = new HashSet<>();
			for (String sysName : new String[] { "hek", "jita", "amarr", "dodixie" }) {
				hubsNames.add(sysName);
				String constName = normalizeLocName(getLocation(sysName).parentConstellation);
				hubsNames.add(constName);
				String regName = normalizeLocName(getLocation(constName).parentRegion);
				hubsNames.add(regName);
			}
		}
		return hubsNames.contains(normalizeLocName(locName));
	}

	public abstract LinkedHashMap<String, MetaInf> getMetaInfs();

	public int getId(String name) {
		return getMetaInfs().get(name).id;
	}

	public abstract ArrayList<LPOffer> getLPOffers();

	public abstract LinkedHashMap<String, Agent> getAgents();

	protected HashMap<Integer, String> elementById = null;

	public String getElementById(int id) {
		LinkedHashMap<String, MetaInf> mi = getMetaInfs();
		synchronized (this) {
			if (elementById == null) {
				elementById = new HashMap<>();
				for (Entry<String, MetaInf> e : mi.entrySet()) {
					elementById.put(e.getValue().id, e.getKey());
				}
			}
		}
		return elementById.get(id);
	}

	public Type getTypeByName(String name) {
		Type ret = getHulls().get(name);
		if (ret == null) {
			ret = getModules().get(name);
		}
		if (ret == null) {
			ret = getAsteroids().get(name);
		}
		if (ret == null) {
			ret = getBlueprints().get(name);
		}
		return ret;
	}

	public Type getTypeById(int id) {
		return getTypeByName(getElementById(id));
	}

	protected ESIBasePrices esi = new ESIBasePrices();

	public ESIBasePrices ESIBasePrices() {
		return esi;
	}

	// market cache

	protected HashMap<Integer, ESIMarket> esiregions = new HashMap<>();

	public ESIMarket ESIRegion(int regionID) {
		synchronized (esiregions) {
			ESIMarket ret = esiregions.get(regionID);
			if (ret == null) {
				ret = new ESIMarket(regionID);
				esiregions.put(regionID, ret);
			}
			return ret;
		}
	}

	public ESIMarket ESIRegion(String region) {
		region = region.replaceAll(" ", "");
		Location location = getLocation(region);
		if (location == null || location.getLocationType() != 1) {
			return null;
		}
		return ESIRegion(location.locationID);
	}

	// locations

	protected HashMap<Integer, Location> locationsById = null;

	public HashMap<Integer, Location> getLocationById() {
		LinkedHashMap<String, Location> locations = getLocations();
		synchronized (this) {
			if (locationsById == null) {
				locationsById = new HashMap<>();
				for (Entry<String, Location> e : locations.entrySet()) {
					locationsById.put(e.getValue().locationID, e.getValue());
				}
			}
		}
		return locationsById;
	}

	protected HashMap<Long, Station> stationsById = null;

	public HashMap<Long, Station> getStationById() {
		LinkedHashMap<String, Station> stations = getStations();
		synchronized (this) {
			if (stationsById == null) {
				stationsById = new HashMap<>();
				for (Entry<String, Station> e : stations.entrySet()) {
					stationsById.put(e.getValue().stationId, e.getValue());
				}
			}
		}
		return stationsById;
	}

	protected final Eve eve = new Eve();

	/** get the {@link Eve} apiv2 access */
	public Eve eve() {
		return eve;
	}

	public static enum InventionDecryptor {

		None("No Decryptor", 0, 0, 0, 0, 1.0)
		, Accelerant("Accelerant Decryptor", 34201, 1, 2, 10, 1.2)
		, Attainment("Attainment Decryptor", 34202, 4, -1, +4, 1.8)
		, Augmentation("Augmentation Decryptor", 34203, 9, -2, +2, 0.6)
		, Parity("Parity Decryptor", 34204, 3, 1, -2, 1.5)
		, Process("Process Decryptor", 34205, 0, 3, 6, 1.1)
		, Symmetry("Symmetry Decryptor", 34206, 2,1, 8, 1.0)
		, OptimizedAttainment("Optimized Attainment Decryptor", 34207, 2, 1, -2, 1.9)
		, OptimizedAugmentation("Optimized Augmentation Decryptor", 34208, 7, 2, 0, 0.9);

		public final String name;
		public final int maxrun, me, te;
		public final double probmult;
		public final int id;

		private InventionDecryptor(String name, int id, int runs, int me, int te, double successMult) {
			this.name = name;
			this.id = id;
			maxrun = runs;
			this.me = me;
			this.te = te;
			probmult = successMult;
		}
	}

	public List<InventionDecryptor> decryptors() {
		return Arrays.asList(InventionDecryptor.values());
	}

}
