package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.esi.ESIMarket;
import fr.guiguilechat.eveonline.database.esi.ESIRegion;
import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.Location;
import fr.guiguilechat.eveonline.database.yaml.MetaInf;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.Type;

public abstract class DataBase {

	public abstract LinkedHashMap<String, Hull> getHulls();

	public abstract LinkedHashMap<String, Module> getModules();

	public abstract LinkedHashMap<String, Asteroid> getAsteroids();

	public abstract LinkedHashMap<String, Blueprint> getBlueprints();

	public abstract LinkedHashMap<String, MetaInf> getMetaInfs();

	public abstract ArrayList<LPOffer> getLPOffers();

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

	public abstract LinkedHashMap<String, Location> getLocations();

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

	protected ESIMarket esi = new ESIMarket();

	public ESIMarket ESIMarket() {
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
		Location location = getLocations().get(limit);
		if (location == null || location.getLocationType() < 1 || location.getLocationType() > 3) {
			return central(0);
		}
		if (location.getLocationType() == 2) {
			location = getLocations().get(location.parentRegion);
		}
		return central(location.locationID);
	}

	protected HashMap<Integer, ESIRegion> esiregions = new HashMap<>();

	public ESIRegion ESIRegion(int regionID) {
		ESIRegion ret = esiregions.get(regionID);
		if (ret == null) {
			ret = new ESIRegion(regionID);
			esiregions.put(regionID, ret);
		}
		return ret;
	}

	public ESIRegion ESIRegion(String region) {
		Location location = getLocations().get(region);
		if (location == null || location.getLocationType() != 1) {
			return null;
		}
		return ESIRegion(location.locationID);
	}

}
