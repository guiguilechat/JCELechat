package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ESIMarket {

	private final String historyURL;
	private final String ordersURL;
	public final int region;

	public ESIMarket(int regionID) {
		region = regionID;
		historyURL = "https://esi.tech.ccp.is/dev/markets/" + regionID + "/history/?";
		ordersURL = "https://esi.tech.ccp.is/latest/markets/" + regionID + "/orders/?";
	}

	private HashMap<Integer, List<HistoryData>> cachedHistories = new HashMap<>();

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	public static class HistoryData {
		public Date date;
		public int order_count;
		public int volume;
		public double highest;
		public double average;
		public double lowest;
	}

	public List<HistoryData> getHistory(int itemId) {
		List<HistoryData> ret = cachedHistories.get(itemId);
		if (ret == null) {
			String url = historyURL + "type_id=" + itemId;
			ObjectMapper mapper = new ObjectMapper(); // just need one
			mapper.setDateFormat(DATE_FORMATTER);
			try {
				ret = mapper.readValue(new URL(url), new TypeReference<List<HistoryData>>() {
				});
				cachedHistories.put(itemId, ret);
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return ret;
	}

	public Stream<HistoryData> getHistoryAfter(int itemID, Date limit) {
		return getHistory(itemID).stream().filter(h -> h.date.after(limit));
	}

	/**
	 * get the total sell price of an item over last month
	 *
	 * @param itemId
	 *          the id of the item
	 * @param regionID
	 *          the id of the region
	 * @return
	 */
	public double getMonthTotalPrice(int itemId) {
		double totalPrice = 0;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date firstDate = cal.getTime();
		for (HistoryData d : getHistory(itemId)) {
			if (d.date.after(firstDate)) {
				totalPrice += d.volume * d.average;
			}
		}
		return totalPrice;
	}

	public int getMonthNBOrders(int itemId) {
		int totalOrders = 0;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date firstDate = cal.getTime();
		for (HistoryData d : getHistory(itemId)) {
			if (d.date.after(firstDate)) {
				totalOrders += d.volume;
			}
		}
		return totalOrders;
	}

	protected HashMap<Integer, MyOrder[]> cachedBOs = new HashMap<>();

	/**
	 * get the highest BO value for given item ID. eg if item 5 has 10 BO at 100,
	 * 10 BO at 90, requesting 10 will return 1000, requesting 20 will return
	 * 1900, requesting 11 will return 1090
	 *
	 * @param itemID
	 *          the id of the item
	 * @param quantity
	 *          number of items to consider
	 * @return the sum of highest quantity buy orders values.
	 */
	public double getBO(int itemID, long quantity) {
		MyOrder[] cached = cachedBOs.get(itemID);
		if (cached == null) {
			cached = loadOrders(itemID, true);
			cachedBOs.put(itemID, cached);
		}
		if (cached == null || cached.length == 0) {
			return 0.0;
		}
		// System.err.println(cached.length + " orders for id " + itemID + " on
		// region " + region);
		// iteratively add the quantity first volume price.
		double ret = 0;
		for (MyOrder mo : cached) {
			long vol = Math.min(mo.volume, quantity);
			ret += mo.price * vol;
			quantity -= vol;
			if (quantity == 0) {
				return ret;
			}
		}
		// if there was not enough quantity to feed the request, we cfan't sell it.
		// so the BO is 0
		return ret;
	}

	public void cacheBOs(int... itemIDs) {
		if (itemIDs == null) {
			return;
		}
		Map<Integer, MyOrder[]> syncBOs = Collections.synchronizedMap(cachedBOs);
		IntStream.of(itemIDs).distinct().filter(i -> !syncBOs.containsKey(i)).parallel()
		.forEach(i -> syncBOs.put(i, loadOrders(i, true)));
	}

	protected HashMap<Integer, MyOrder[]> cachedSOs = new HashMap<>();


	/**
	 * get the value of SO for given item ID and given quantity
	 *
	 * @param itemID
	 *          the id of the item
	 * @param quantity
	 *          the total quantity to consider for SO
	 * @return the sum of the quantity lowest SO values.
	 */
	public double getSO(int itemID, long quantity) {
		MyOrder[] cached = cachedSOs.get(itemID);
		if (cached == null) {
			cached = loadOrders(itemID, false);
			cachedSOs.put(itemID, cached);
		}
		if (cached == null || cached.length == 0) {
			return Double.POSITIVE_INFINITY;
		}
		double ret = 0;
		for (MyOrder mo : cached) {
			long vol = Math.min(mo.volume, quantity);
			ret += mo.price * vol;
			quantity -= vol;
			if (quantity == 0) {
				return ret;
			}
		}
		// if there was not enough quantity to feed the request, we set the price to
		// infinite. we CANT buy that many items
		return Double.POSITIVE_INFINITY;
	}

	public void cacheSOs(int... itemIDs) {
		if (itemIDs == null) {
			return;
		}
		Map<Integer, MyOrder[]> syncSOs = Collections.synchronizedMap(cachedSOs);
		IntStream.of(itemIDs).distinct().filter(i -> !syncSOs.containsKey(i)).parallel()
		.forEach(i -> syncSOs.put(i, loadOrders(i, false)));
	}

	protected static class MarketOrder {
		public long order_id;
		public int type_id;
		public long location_id;
		public long volume_total;
		public long volume_remain;
		public long min_volume;
		public double price;
		public boolean is_buy_order;
		public int duration;
		public String issued;
		public String range;
	}

	protected static class MyOrder {
		public long volume;
		public double price;

		public MyOrder() {
		}

		public MyOrder(long volume, double price) {
			this.volume = volume;
			this.price = price;
		}
	}

	protected final ObjectMapper mapper = new ObjectMapper();

	protected MyOrder[] loadOrders(int itemID, boolean buy) {
		// System.err.println("retrieving " + (buy ? "buy" : "sell") + "data for id
		// " + itemID);
		String url = ordersURL + "type_id=" + itemID + "&order_type=" + (buy ? "buy" : "sell");
		try {
			MarketOrder[] orders = mapper.readValue(new URL(url), MarketOrder[].class);
			MyOrder[] arr = Stream.of(orders).map(mo -> new MyOrder(mo.volume_remain, mo.price)).toArray(MyOrder[]::new);
			Arrays.sort(arr, buy ? (mo1, mo2) -> (int) Math.signum(mo2.price - mo1.price)
					: (mo1, mo2) -> (int) Math.signum(mo1.price - mo2.price));
			return arr;
		} catch (IOException e) {
			return new MyOrder[] {};
		}
	}

	public static void main(String[] args) {
		// jita region : the forge
		ESIMarket esir = new ESIMarket(10000002);
		MyOrder[] mos = esir.loadOrders(34, true);
		for (MyOrder mo : mos) {
			System.err.println(mo.price + " : " + mo.volume);
		}
		long firstOrderQtty = mos[0].volume;
		System.err.println("price of " + firstOrderQtty + " trita : " + esir.getBO(34, firstOrderQtty));
	}

}
