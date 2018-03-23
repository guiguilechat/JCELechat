package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_names;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_names;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;

public class Names {

	private static final Logger logger = LoggerFactory.getLogger(Names.class);

	protected final ESIConnection raw;

	public Names(ESIConnection raw) {
		this.raw = raw;
	}

	/**
	 * assuming a request takes at max 100 characters, and a max url size of 2083,
	 * each int will use this number of characters(+1 for the comma)
	 */
	public static final int MAXINTIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Integer.MAX_VALUE)));
	public static final int MAXLONGIDPERREQUEST = (2083 - 100) / (1 + (int) Math.ceil(Math.log10(Long.MAX_VALUE)));

	private HashMap<Integer, R_get_characters_names> cachedCharacterNames = new HashMap<>();

	public R_get_characters_names[] characterNames(int... ids) {
		if (ids == null || ids.length == 0) {
			return new R_get_characters_names[0];
		}
		synchronized (cachedCharacterNames) {
			// have to work with long, because CCP bug.
			long[] lids = IntStream.of(ids).filter(i -> !cachedCharacterNames.containsKey(i)).mapToLong(i -> i).toArray();
			long[] fullbuffer = new long[MAXLONGIDPERREQUEST];
			for (int start = 0; start < lids.length; start += MAXLONGIDPERREQUEST) {
				if (start + MAXLONGIDPERREQUEST >= lids.length) {
					fullbuffer = new long[lids.length - start];
				}
				System.arraycopy(lids, start, fullbuffer, 0, fullbuffer.length);
				Stream.of(raw.get_characters_names(fullbuffer, null))
				.forEachOrdered(n -> cachedCharacterNames.put((int) n.character_id, n));
			}
			return IntStream.of(ids).mapToObj(cachedCharacterNames::get).toArray(R_get_characters_names[]::new);
		}
	}

	protected Map<Long, String> cachedLocationNames = Collections.synchronizedMap(new HashMap<>());

	public String getLocationName(long location) {
		String ret = cachedLocationNames.get(location);
		if (ret == null) {
			if (location >= 1000000000000l) {
				R_get_universe_structures_structure_id res = raw.get_universe_structures_structure_id(location, null);
				if (res == null) {
					logger.warn("can't acces location " + location + " from character " + raw.verify().CharacterName);
				}
				ret = res == null ? "forbidden_" + location : res.name;
			} else {
				R_post_universe_names[] res = raw.post_universe_names(new int[] { (int) location }, null);
				ret = res == null || res.length == 0 ? "unknown_" + location : res[0].name;
			}
			cachedLocationNames.put(location, ret);
		}
		return ret;
	}

}
