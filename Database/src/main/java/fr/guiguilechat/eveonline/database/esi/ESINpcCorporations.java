package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class ESINpcCorporations {

	private static final Logger logger = LoggerFactory.getLogger(ESINpcCorporations.class);

	public static class Corporation {
		public int ceo_id;
		public String corporation_description;
		public String corporation_name;
		public int creator_id;
		public String faction;
		public int member_count;
		public double tax_rate;
		public String ticker;
		public String url;
	}

	private final String CORPORATIONS_LIST_URL = "https://esi.tech.ccp.is/latest/corporations/npccorps";

	private final ObjectMapper om = new ObjectMapper();
	private final ObjectReader intArrReader = om.readerFor(int[].class);

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

	private final String CORPORATIONS_DETAIL_URL = "https://esi.tech.ccp.is/latest/corporations/";

	private final LinkedHashMap<Integer, Corporation> cachedCorpos = new LinkedHashMap<>();

	/** set to true when all corporations are loaded */
	private boolean fullLoad = false;

	public LinkedHashMap<Integer, Corporation> getCorpos() {
		// first case : we loaded NO corporation. load all.
		if (cachedCorpos.isEmpty()) {
			Map<Integer, Corporation> syncCache = Collections.synchronizedMap(cachedCorpos);
			IntStream.of(getIDs()).parallel().forEach(i -> syncCache.put(i, loadCorporation(i)));
			fullLoad = true;
		}
		// second case : we got some corporations already. load all the other ones.
		if (!fullLoad) {
			Map<Integer, Corporation> syncCache = Collections.synchronizedMap(cachedCorpos);
			IntStream.of(getIDs()).filter(i -> !syncCache.containsKey(i)).parallel()
			.forEach(i -> syncCache.put(i, loadCorporation(i)));
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
			corpo = loadCorporation(id);
			cachedCorpos.put(id, corpo);
		}
		return corpo;
	}

	private final ObjectReader corpReader = om.readerFor(Corporation.class);

	protected Corporation loadCorporation(int id) {
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
			logger.debug("while loading corp "+id, error);
		}
		return ret;
	}

	public static void main(String[] args) {
		for (Entry<Integer, Corporation> e : new ESINpcCorporations().getCorpos().entrySet()) {
			System.err.println(" " + e.getKey() + " :" + e.getValue().corporation_name);
		}
	}

}
