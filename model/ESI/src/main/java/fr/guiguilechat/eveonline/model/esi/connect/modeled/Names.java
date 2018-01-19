package fr.guiguilechat.eveonline.model.esi.connect.modeled;

import java.util.ArrayList;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;
import is.ccp.tech.esi.responses.R_get_characters_names;

public class Names {

	protected final ESIRawConnection raw;

	public Names(ESIRawConnection raw) {
		this.raw = raw;
	}

	/**
	 * assuming a request takes at max 100 characters, and a max url size of 2083,
	 * each int will use this number of characters(+1 for the comma)
	 */
	public static final int MAXIDPERREQUEST = (2083 - 100) / (1 + (int) Math.log10(Integer.MAX_VALUE));

	public R_get_characters_names[] characterNames(long... ids) {
		if (ids == null || ids.length == 0) {
			return new R_get_characters_names[0];
		}
		ArrayList<R_get_characters_names> ret = new ArrayList<>();
		long[] fullbuffer = new long[MAXIDPERREQUEST];
		for (int start = 0; start < ids.length; start += MAXIDPERREQUEST) {
			if (start + MAXIDPERREQUEST >= ids.length) {
				fullbuffer = new long[ids.length - start];
			}
			System.arraycopy(ids, start, fullbuffer, 0, fullbuffer.length);
			Stream.of(raw.get_characters_names(fullbuffer)).forEachOrdered(ret::add);
		}
		return ret.toArray(new R_get_characters_names[0]);
	}

}
