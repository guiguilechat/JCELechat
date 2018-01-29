package fr.guiguilechat.eveonline.model.esi.raw.market;

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
@Deprecated
 public class Prices {

	 protected HashMap<Integer, Double> cachedAverage = null;
	 protected HashMap<Integer, Double> cachedAdjusted = null;

	 public void dl() {
		 if (cachedAdjusted == null || cachedAverage == null) {
			 synchronized (this) {
				 if (cachedAdjusted != null && cachedAverage != null) {
					 return;
				 }
				 HashMap<Integer, Double> fcachedAverage = new HashMap<>();
				 HashMap<Integer, Double> fcachedAdjusted = new HashMap<>();
				 ObjectMapper mapper = new ObjectMapper(); // just need one
				 try {
					 // need Map<String, ?> because this is actually numbers : doubles or
					 // longs.
					 List<Map<String, Number>> l = mapper.readerFor(new TypeReference<List<Map<String, Number>>>() {
					 }).readValue(new URL("https://esi.tech.ccp.is/latest/markets/prices/"));
					 for (Map<String, Number> m : l) {
						 Number oid = m.get("type_id");
						 int id = (Integer) oid;
						 fcachedAverage.put(id, (Double) m.get("average_price"));
						 fcachedAdjusted.put(id, (Double) m.get("adjusted_price"));
					 }
					 cachedAverage = fcachedAverage;
					 cachedAdjusted = fcachedAdjusted;
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
