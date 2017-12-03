package fr.guiguilechat.eveonline.model.esi.raw;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class Loyalty {

	private static final Logger logger = LoggerFactory.getLogger(Loyalty.class);

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

	private static final ObjectMapper om = new ObjectMapper();
	private static final ObjectReader offerArrayReader = om.readerFor(Offer[].class);

	private final HashMap<Integer, Offer[]> cache = new HashMap<>();

	public void loadOffers(int... ids) {
		IntStream.of(ids).parallel().forEach(id -> {
			try {
				Offer[] ret = offerArrayReader.readValue(new URL(baseURL + id + "/offers/"));
				cache.put(id, ret);
			} catch (IOException e) {
				logger.debug("can't get LP store for id " + id);
				cache.put(id, new Offer[] {});
			}
		});
	}

	public Offer[] getOffers(int id) {
		Offer[] ret = cache.get(id);
		if (ret == null) {
			try {
				ret = storesOffers(id);
			} catch (IOException e) {
				logger.debug("can't get LP store for id " + id);
				ret = new Offer[] {};
			}
			cache.put(id, ret);
		}
		return ret != null ? ret : new Offer[] {};
	}

	/**
	 * https://esi.tech.ccp.is/ui/#/Loyalty/get_loyalty_stores_corporation_id_offers
	 *
	 * @throws IOException
	 *
	 */
	public static Offer[] storesOffers(int corpId) throws IOException {
		return offerArrayReader.readValue(new URL(baseURL + corpId + "/offers/"));
	}

	/**
	 * show SoE items
	 *
	 * @param args
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void main(String[] args) throws JsonProcessingException, IOException {
		for (Offer o : storesOffers(1000130)) {
			System.err.println(" " + o.type_id);
		}
	}

}
