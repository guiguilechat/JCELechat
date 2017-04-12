package fr.guiguilechat.eveonline.database;

import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.Module;

public abstract class DataBase {

	public abstract LinkedHashMap<Integer, Hull> getHulls();

	public abstract LinkedHashMap<Integer, Module> getModules();

	public abstract LinkedHashMap<String, Asteroid> getAsteroids();

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
