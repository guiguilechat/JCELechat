package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;

public class Universe {

	private final static Logger logger = LoggerFactory.getLogger(Universe.class);

	private final ESIAccount parent;

	public Universe(ESIAccount esiAccount) {
		parent = esiAccount;
	}

	private HashMap<Long, String> cachedLocationSystems = new HashMap<>();

	public String locationName(long locationid) {
		String ret = cachedLocationSystems.get(locationid);
		if (ret == null) {
			if (locationid < Integer.MAX_VALUE) {
				switch ((int) locationid / 1000000) {
				case 1://region
					ret = ESIStatic.INSTANCE.cache.universe.regions((int) locationid).get().name;
					break;
				case 2://constellation
					ret = ESIStatic.INSTANCE.cache.universe.constellations((int) locationid).get().name;
					break;
				case 30:
				case 31:
				case 32:// system
					ret = ESIStatic.INSTANCE.cache.universe.systems((int) locationid).get().name;
					break;
				case 60:
				case 61:
				case 62:
				case 63:
				case 64:// station
					ret = ESIStatic.INSTANCE.cache.universe.stations((int) locationid).get().name;
					break;
				case 66:// office id
					ret = ESIStatic.INSTANCE.cache.universe.stations((int) locationid - 6000001).get().name;
				case 67:// conquerable office
					ret = ESIStatic.INSTANCE.cache.universe.stations((int) locationid - 6000000).get().name;
				}
			} else {
				Requested<R_get_universe_structures_structure_id> req = parent.raw.get_universe_structures(locationid, null);
				if (req.isOk()) {
					ret = req.getOK().name;
				} else {
					logger.warn("location " + locationid + " = unknown");
				}
			}
			if (ret == null) {
				ret = "" + locationid;
			}
			cachedLocationSystems.put(locationid, ret);
		}
		return ret;
	}

}
