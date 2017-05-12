package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * get data from the ESI market interface
 *
 */
public class ESIMarket {

	protected HashMap<Integer, Double> cachedAverage = null;
	protected HashMap<Integer, Double> cachedAdjusted = null;

	@SuppressWarnings("unchecked")
	public void dl() {
		if (cachedAdjusted != null && cachedAverage != null) {
			return;
		}
		cachedAverage = new HashMap<>();
		cachedAdjusted = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper(); // just need one
		try {
			List<Map<String, ?>> l = mapper.readValue(new URL("https://esi.tech.ccp.is/latest/markets/prices/"), List.class);
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

	public double getAdjusted(int itemId) {
		dl();
		return cachedAdjusted.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

	public double getAverage(int itemId) {
		dl();
		return cachedAverage.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}
}
