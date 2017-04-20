package fr.guiguilechat.eveonline.database;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.Type;

public abstract class DataBase {

	public abstract LinkedHashMap<String, Hull> getHulls();

	public abstract LinkedHashMap<String, Module> getModules();

	public abstract LinkedHashMap<String, Asteroid> getAsteroids();

	public abstract LinkedHashMap<String, Blueprint> getBlueprints();

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

	protected ESIMarketPrices esi = new ESIMarketPrices();

	public ESIMarketPrices getESI() {
		return esi;
	}

	// central cache

	protected HashMap<Long, EveCentral> centrals = new HashMap<>();

	/**
	 * get a cached central for given system.
	 *
	 * @param systemID
	 *          ID of system, Jita by default.
	 * @return the internal cached eve-central proxy
	 */
	public EveCentral central(long systemID) {
		EveCentral ret = centrals.get(systemID);
		if (ret == null) {
			ret = new EveCentral(systemID);
			centrals.put(systemID, ret);
		}
		return ret;
	}

	public EveCentral central() {
		return central(EveCentral.JITA_SYSTEM);
	}

}
