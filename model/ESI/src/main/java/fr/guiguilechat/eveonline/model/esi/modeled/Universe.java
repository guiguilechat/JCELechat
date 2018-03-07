package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.HashMap;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import is.ccp.tech.esi.Swagger.language;

public class Universe {

	private final ESIAccount parent;

	public Universe(ESIAccount esiAccount) {
		parent = esiAccount;
	}

	private HashMap<Long, String> cachedLocationSystems = new HashMap<>();

	public String locationSystem(long locationid) {
		String ret = cachedLocationSystems.get(locationid);
		if (ret == null) {
			if (locationid < Integer.MAX_VALUE) {
				switch ((int) locationid / 10000000) {
				case 1://region
					ret=parent.raw.get_universe_regions_region_id(language.en_us, (int) locationid	,null).name;
					break;
				case 2://constellation
					ret = parent.raw.get_universe_constellations_constellation_id((int) locationid, language.en_us, null).name;
					break;
				case 3://system
					ret = parent.raw.get_universe_systems_system_id(language.en_us, (int) locationid, null).name;
					break;
				case 6://station
					ret = parent.raw.get_universe_stations_station_id((int) locationid, null).name;
					break;
				}
			} else {

			}
			if (ret == null) {
				ret = "" + locationid;
			}
			cachedLocationSystems.put(locationid, ret);
		}
		return ret;
	}
}
