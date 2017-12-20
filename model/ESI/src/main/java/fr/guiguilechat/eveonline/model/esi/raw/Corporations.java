package fr.guiguilechat.eveonline.model.esi.raw;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import fr.guiguilechat.eveonline.model.esi.connect.ConnectedCall;
import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;

public class Corporations extends ConnectedCall {

	public Corporations(ESIConnection connection) {
		super(connection);
	}

	private static final Logger logger = LoggerFactory.getLogger(Corporations.class);

	// list npc corporations
	//
	// https://esi.tech.ccp.is/ui/#/Corporation/get_corporations_npccorps
	//

	private static final String CORPORATIONS_LIST_URL = "https://esi.tech.ccp.is/latest/corporations/npccorps";

	private static final ObjectMapper om = new ObjectMapper();
	private static final ObjectReader intArrReader = om.readerFor(int[].class);

	int[] ids = null;

	/**
	 *
	 * @return the ids of npc corporations
	 */
	public int[] getIDs() {
		if (ids == null) {
			IOException error = null;
			for (int retry = 0; retry < 10 && ids == null; retry++) {
				try {
					ids = intArrReader.readValue(new URL(CORPORATIONS_LIST_URL));
				} catch (IOException e) {
					error = e;
				}
			}
			if (error != null) {
				logger.debug("while loading npc corps",error);
			}
		}
		return ids;
	}

	// corporation public informations
	//
	// https://esi.tech.ccp.is/ui/#/Corporation/get_corporations_corporation_id
	//

	public static class Corporation {
		public String name;
		public String ticker;
		public int member_count;
		public long ceo_id;
		public long alliance_id;
		public String corporation_description;
		public double tax_rate;
		public String creation_date;
		public long creator_id;
		public String url;
		public String description;
	}

	private static final String CORPORATIONS_DETAIL_URL = "https://esi.tech.ccp.is/latest/corporations/";

	private static final ObjectReader corpReader = om.readerFor(Corporation.class);

	public static Corporation corporation(int id) {
		IOException error = null;
		Corporation ret = null;
		for (int retry = 0; retry < 10 && ret == null; retry++) {
			try {
				ret = corpReader.readValue(new URL(CORPORATIONS_DETAIL_URL + id));
			} catch (IOException e) {
				error = e;
			}
		}
		if (error != null) {
			logger.debug("while loading corp " + id, error);
		}
		return ret;
	}

	private final LinkedHashMap<Integer, Corporation> cachedCorpos = new LinkedHashMap<>();

	/** set to true when all corporations are loaded */
	private boolean fullLoad = false;

	public LinkedHashMap<Integer, Corporation> getCorpos() {
		// first case : we loaded NO corporation. load all.
		if (cachedCorpos.isEmpty()) {
			Map<Integer, Corporation> syncCache = Collections.synchronizedMap(cachedCorpos);
			IntStream.of(getIDs()).parallel().forEach(i -> syncCache.put(i, corporation(i)));
			fullLoad = true;
		}
		// second case : we got some corporations already. load all the other ones.
		if (!fullLoad) {
			Map<Integer, Corporation> syncCache = Collections.synchronizedMap(cachedCorpos);
			IntStream.of(getIDs()).filter(i -> !syncCache.containsKey(i)).parallel()
			.forEach(i -> syncCache.put(i, corporation(i)));
			fullLoad = true;
		}
		return cachedCorpos;
	}

	/**
	 * get corporation informations
	 *
	 * @param id
	 *          the corporation id
	 * @return the cached data. if not in cache, cache it.
	 */
	public Corporation getCorpo(int id) {
		Corporation corpo = cachedCorpos.get(id);
		if (corpo == null) {
			corpo = corporation(id);
			cachedCorpos.put(id, corpo);
		}
		return corpo;
	}

}
