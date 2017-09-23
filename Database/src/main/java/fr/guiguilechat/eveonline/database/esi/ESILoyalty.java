package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class ESILoyalty {

	private static final Logger logger = LoggerFactory.getLogger(ESILoyalty.class);

	private static final String baseURL = "https://esi.tech.ccp.is/latest/loyalty/stores/";

	public static class Offer {
		public int offer_id;
		public int type_id;
		public int quantity;
		public int lp_cost;
		public int isk_cost;
		public ItemReq[] required_items = {};

		public static class ItemReq {
			public int type_id;
			public int quantity;
		}

	}

	private ObjectMapper om = new ObjectMapper();
	private ObjectReader offerArrayReader = om.readerFor(Offer[].class);

	private final HashMap<Integer, Offer[]> cache = new HashMap<>();

	public Offer[] getOffers(int id) {
		Offer[] ret = cache.get(id);
		if (ret == null) {
			try {
				ret = offerArrayReader.readValue(new URL(baseURL + id + "/offers/"));
			} catch (IOException e) {
				logger.debug("can't get LP store for id " + id);
				ret = new Offer[] {};
			}
			cache.put(id, ret);
		}
		return ret != null ? ret : new Offer[] {};
	}

	/**
	 * show SoE items
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		for (Offer o : new ESILoyalty().getOffers(1000130)) {
			System.err.println(" " + o.type_id);
		}
	}

}
