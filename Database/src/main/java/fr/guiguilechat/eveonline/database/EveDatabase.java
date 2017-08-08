package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.esi.ESIBasePrices;
import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.yaml.Agent;
import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.Location;
import fr.guiguilechat.eveonline.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.Type;

public abstract class EveDatabase {

	public abstract LinkedHashMap<String, Hull> getHulls();

	public abstract LinkedHashMap<String, Module> getModules();

	public abstract LinkedHashMap<String, Asteroid> getAsteroids();

	public abstract LinkedHashMap<String, Blueprint> getBlueprints();

	public abstract LinkedHashMap<String, Location> getLocations();

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

	public abstract ArrayList<LPOffer> getLPOffers();

	public abstract LinkedHashMap<String, Agent> getAgents();

	protected HashMap<Integer, String> elementById = null;

	public String getElementById(int id) {
		if (elementById == null) {
			elementById = new HashMap<>();
			for (Entry<String, MetaInf> e : getMetaInfs().entrySet()) {
				elementById.put(e.getValue().id, e.getKey());
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

	// central cache

	protected HashMap<Integer, EveCentral> centrals = new HashMap<>();

	/**
	 * get a cached central for given system|region.
	 *
	 * @param systemID
	 *          ID of system, Jita by default, or a region.
	 * @return the internal cached eve-central proxy
	 */
	public EveCentral central(int systemID) {
		EveCentral ret = centrals.get(systemID);
		if (ret == null) {
			ret = new EveCentral(systemID);
			centrals.put(systemID, ret);
		}
		return ret;
	}

	public EveCentral central(String limit) {
		Location location = getLocation(limit);
		if (location == null || location.getLocationType() < 1 || location.getLocationType() > 3) {
			return central(0);
		}
		if (location.getLocationType() == 2) {
			location = getLocation(location.parentRegion);
		}
		return central(location.locationID);
	}

	protected HashMap<Integer, ESIMarket> esiregions = new HashMap<>();

	public ESIMarket ESIRegion(int regionID) {
		ESIMarket ret = esiregions.get(regionID);
		if (ret == null) {
			ret = new ESIMarket(regionID);
			esiregions.put(regionID, ret);
		}
		return ret;
	}

	public ESIMarket ESIRegion(String region) {
		region = region.replaceAll(" ", "");
		Location location = getLocation(region);
		if (location == null || location.getLocationType() != 1) {
			return null;
		}
		return ESIRegion(location.locationID);
	}

	protected HashMap<Integer, Location> locationsById = null;

	public HashMap<Integer, Location> getLocationById() {
		if (locationsById == null) {
			locationsById = new HashMap<>();
			for (Entry<String, Location> e : getLocations().entrySet()) {
				locationsById.put(e.getValue().locationID, e.getValue());
			}
		}
		return locationsById;
	}

}
