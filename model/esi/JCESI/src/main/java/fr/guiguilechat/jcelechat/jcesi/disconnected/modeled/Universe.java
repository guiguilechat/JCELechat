package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_post_universe_names;

public class Universe {

	protected final ESIStatic con;

	public Universe(ESIStatic connection) {
		con = connection;
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
				Stream.of(ESIStatic.INSTANCE.post_universe_names(fullbuffer, null))
				.forEachOrdered(n -> cachedNames.put(n.id, n));
			}
			return IntStream.of(ids).mapToObj(cachedNames::get).toArray(R_post_universe_names[]::new);
		}
	}

}
