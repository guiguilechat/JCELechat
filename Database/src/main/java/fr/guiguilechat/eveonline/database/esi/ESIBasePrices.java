package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * get data from the ESI market interface
 *
 * <p>
 * adjusted value is used when producing an item from bp. this is the value
 * which is taken for tax, eg if an item is adjusted value 5000 and tax is 1%
 * then the production tax will be 50.
 * </p>
 *
 */
public class ESIBasePrices {

	protected HashMap<Integer, Double> cachedAverage = null;
	protected HashMap<Integer, Double> cachedAdjusted = null;

	public void dl() {
		if (cachedAdjusted == null || cachedAverage == null) {
			synchronized (this) {
				if (cachedAdjusted != null && cachedAverage != null) {
					return;
				}
				cachedAverage = new HashMap<>();
				cachedAdjusted = new HashMap<>();
				ObjectMapper mapper = new ObjectMapper(); // just need one
				try {
					List<Map<String, ?>> l = mapper.readerFor(new TypeReference<List<Map<String, ?>>>() {
					}).readValue(new URL("https://esi.tech.ccp.is/latest/markets/prices/"));
					for (Map<String, ?> m : l) {
						Object oid = m.get("type_id");
						int id = (Integer) oid;
						cachedAverage.put(id, (Double) m.get("average_price"));
						cachedAdjusted.put(id, (Double) m.get("adjusted_price"));
					}
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public double getAdjusted(int itemId) {
		dl();
		return cachedAdjusted.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

	public double getAverage(int itemId) {
		dl();
		return cachedAverage.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}
}
