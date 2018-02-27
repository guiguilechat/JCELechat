package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import is.ccp.tech.esi.responses.R_get_characters_names;
import is.ccp.tech.esi.responses.R_get_universe_structures_structure_id;

public class Names {

	protected final ESIConnection raw;

	public Names(ESIConnection raw) {
		this.raw = raw;
	}

	/**
	 * assuming a request takes at max 100 characters, and a max url size of 2083,
	 * each int will use this number of characters(+1 for the comma)
	 */
	public static final int MAXINTIDPERREQUEST = (2083 - 100) / (1 + (int) Math.log10(Integer.MAX_VALUE));
	public static final int MAXLONGIDPERREQUEST = (2083 - 100) / (1 + (int) Math.log10(Long.MAX_VALUE));

	public R_get_characters_names[] characterNames(int... ids) {
		if (ids == null || ids.length == 0) {
			return new R_get_characters_names[0];
		}
		// have to work with long, because CCP bug.
		long[] lids = IntStream.of(ids).mapToLong(i -> i).toArray();
		ArrayList<R_get_characters_names> ret = new ArrayList<>();
		long[] fullbuffer = new long[MAXLONGIDPERREQUEST];
		for (int start = 0; start < lids.length; start += MAXLONGIDPERREQUEST) {
			if (start + MAXLONGIDPERREQUEST >= lids.length) {
				fullbuffer = new long[lids.length - start];
			}
			System.arraycopy(lids, start, fullbuffer, 0, fullbuffer.length);
			Stream.of(raw.get_characters_names(fullbuffer, null)).forEachOrdered(ret::add);
		}
		return ret.toArray(new R_get_characters_names[0]);
	}

	protected HashMap<Long, String> cachedLocationNames = new HashMap<>();

	public String getLocationName(long location) {
		String ret = cachedLocationNames.get(location);
		if (ret == null) {
			if (location >= 1000000000000l) {
				R_get_universe_structures_structure_id res = raw.get_universe_structures_structure_id(location, null);
				ret = res == null ? "forbidden_" + location : res.name;
			} else {
				ret = raw.post_universe_names(new int[] { (int) location }, null)[0].name;
			}
			cachedLocationNames.put(location, ret);
		}
		return ret;
	}

}
