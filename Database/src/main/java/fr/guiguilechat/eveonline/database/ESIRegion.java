package fr.guiguilechat.eveonline.database;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ESIRegion {

	private final String baseURL;

	public ESIRegion(int regionID) {
		baseURL = "https://esi.tech.ccp.is/dev/markets/" + regionID + "/history/?";
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
			String url = baseURL + "type_id=" + itemId;
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
	public double getTotalPrice(int itemId) {
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

	public int getTotalOrders(int itemId) {
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

	public static void main(String[] args) {
		ESIRegion esir = new ESIRegion(10000046);
		int totalOrders = esir.getTotalOrders(31644);
		double totalValue = esir.getTotalPrice(31644);
		System.err.println(totalOrders + " avg=" + totalValue / totalOrders);
	}

}
