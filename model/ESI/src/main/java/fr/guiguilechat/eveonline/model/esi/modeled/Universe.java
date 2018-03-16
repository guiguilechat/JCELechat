package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import net.evetech.esi.Swagger.language;
import net.evetech.esi.responses.R_get_universe_structures_structure_id;

public class Universe {

	@SuppressWarnings("unused")
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
					ret=parent.raw.get_universe_regions_region_id(language.en_us, (int) locationid	,null).name;
					break;
				case 2://constellation
					ret = parent.raw.get_universe_constellations_constellation_id((int) locationid, language.en_us, null).name;
					break;
				case 30:
				case 31:
				case 32:// system
					ret = parent.raw.get_universe_systems_system_id(language.en_us, (int) locationid, null).name;
					break;
				case 60:
				case 61:
				case 62:
				case 63:
				case 64:// station
					ret = parent.raw.get_universe_stations_station_id((int) locationid, null).name;
					break;
				case 66:// office id
					ret = parent.raw.get_universe_stations_station_id((int) locationid - 6000001, null).name;
				case 67:// conquerable office
					ret = parent.raw.get_universe_stations_station_id((int) locationid - 6000000, null).name;
				}
			} else {
				R_get_universe_structures_structure_id struct = parent.raw.get_universe_structures_structure_id(locationid,
						null);
				if (struct != null) {
					System.err.println("" + locationid + " = structure");
					ret = struct.name;
				} else {
					System.err.println("" + locationid + " = unknown");
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
