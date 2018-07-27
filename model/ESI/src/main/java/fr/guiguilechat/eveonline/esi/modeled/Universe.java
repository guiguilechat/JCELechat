package fr.guiguilechat.eveonline.esi.modeled;

import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.esi.connected.modeled.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_names;

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
					ret = parent.raw.get_universe_regions((int) locationid, null).name;
					break;
				case 2://constellation
					ret = parent.raw.get_universe_constellations((int) locationid, null).name;
					break;
				case 30:
				case 31:
				case 32:// system
					ret = parent.raw.get_universe_systems((int) locationid, null).name;
					break;
				case 60:
				case 61:
				case 62:
				case 63:
				case 64:// station
					ret = parent.raw.get_universe_stations((int) locationid, null).name;
					break;
				case 66:// office id
					ret = parent.raw.get_universe_stations((int) locationid - 6000001, null).name;
				case 67:// conquerable office
					ret = parent.raw.get_universe_stations((int) locationid - 6000000, null).name;
				}
			} else {
				R_get_universe_structures_structure_id struct = parent.raw.get_universe_structures(locationid,
						null);
				if (struct != null) {
					ret = struct.name;
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

	/**
	 * assuming a request takes at max 100 characters, and a max url size of 2083,
	 * each int will use this number of characters(+1 for the comma)
	 */
	public static final int MAXINTIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Integer.MAX_VALUE)));
	public static final int MAXLONGIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Long.MAX_VALUE)));

	private HashMap<Integer, R_post_universe_names> cachedNames = new HashMap<>();

	public R_post_universe_names[] names(int... ids) {
		if (ids == null || ids.length == 0) {
			return new R_post_universe_names[0];
		}
		synchronized (cachedNames) {
			// have to work with long, because CCP bug.
			int[] missingIds = IntStream.of(ids).filter(i -> !cachedNames.containsKey(i)).toArray();
			int[] fullbuffer = new int[MAXLONGIDPERREQUEST];
			for (int start = 0; start < missingIds.length; start += MAXLONGIDPERREQUEST) {
				if (start + MAXLONGIDPERREQUEST >= missingIds.length) {
					fullbuffer = new int[missingIds.length - start];
				}
				System.arraycopy(missingIds, start, fullbuffer, 0, fullbuffer.length);
				Stream.of(parent.raw.post_universe_names(fullbuffer, null)).forEachOrdered(n -> cachedNames.put(n.id, n));
			}
			return IntStream.of(ids).mapToObj(cachedNames::get).toArray(R_post_universe_names[]::new);
		}
	}

}
