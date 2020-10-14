package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.tools.locations.Location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

public class Universe {

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(Universe.class);

	private final ESIAccount parent;

	public Universe(ESIAccount esiAccount) {
		parent = esiAccount;
	}

	private final HashMap<Long, Location> cachedLocationSystems = new HashMap<>();

	public Location location(long locationid) {
		Location ret = cachedLocationSystems.get(locationid);
		if (ret == null) {
			synchronized (cachedLocationSystems) {
				ret = cachedLocationSystems.get(locationid);
				if (ret == null) {
					ret = Location.resolve(parent, locationid);
					cachedLocationSystems.put(locationid, ret);
				}
			}
		}
		return ret;
	}

	public String locationName(long locationid) {
		return location(locationid).name;
	}

	//
	// public structures market
	//

	private final Map<filter, ObsMapHolder<Long, R_get_universe_structures_structure_id>> cachedPublicStructuresByFilter = new HashMap<>();

	/**
	 * get the resolved list of public structure for this character.<br />
	 * Though the public structure ids are visible to all, resolving them require
	 * to be connected #logic
	 */
	public ObsMapHolder<Long, R_get_universe_structures_structure_id> publicStructures(filter f) {
		ObsMapHolder<Long, R_get_universe_structures_structure_id> ret = cachedPublicStructuresByFilter.get(f);
		if (ret == null) {
			synchronized (cachedPublicStructuresByFilter) {
				ret = cachedPublicStructuresByFilter.get(f);
				if (ret == null) {
					ret = ESIAccess.INSTANCE.universe.cache.structures(null).mapItems(sid -> {
						parent.raw.cache.universe.structures(sid);
						return sid;
					}).toMap(sid -> sid, sid -> parent.raw.cache.universe.structures(sid).get());
					cachedPublicStructuresByFilter.put(f, ret);
				}
			}
		}
		return ret;
	}

}
