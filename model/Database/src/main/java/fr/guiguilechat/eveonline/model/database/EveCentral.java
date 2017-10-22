package fr.guiguilechat.eveonline.model.database;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * get the price in Jita by default. prices are bo/so
 *
 */
public class EveCentral {

	//
	// find . -name "solarsystem.staticdata" | grep Dodixie | xargs grep -e
	// "solarSystemID"
	//
	public static final int JITA_SYSTEM = 30000142;
	public static final int AMARR_SYSTEM = 30002187;
	public static final int RENS_SYSTEM = 30002510;
	public static final int DODIXIE_SYSTEM = 30002659;
	public static final int HEK_SYSTEM = 30002053;

	// set to -1 to ignore system
	public final String baseurl;

	public static String makeBaseURL(int[] restrictions) {
		StringBuilder ret = new StringBuilder("https://api.eve-central.com/api/marketstat?");
		if (restrictions != null) {
			for (long r : restrictions) {
				switch ((int) r / 10000000) {
				case 1:// region request
					ret.append("&regionlimit=").append(r);
					break;
				case 3:// system request
					ret.append("&usesystem=").append(r);
					break;
				default:
					System.err.println("can't handle limit of id " + r);
				}
			}
		}
		return ret.toString();
	}

	/**
	 * create a new Evecentral, getting results for the given system. <1 means no
	 * system (eve-wide)
	 *
	 * @param restriction
	 *          system or region limits.
	 */
	public EveCentral(int... restriction) {
		baseurl = makeBaseURL(restriction);
	}

	/**
	 * create a new EveCentral for Jita data
	 */
	public EveCentral() {
		this(JITA_SYSTEM);
	}

	public static class BOSO {
		public double bo = 0.0;
		public double so = Double.POSITIVE_INFINITY;

		@Override
		public String toString() {
			return bo + ":" + so;
		}
	}

	long nbMiss = 0;

	/**
	 *
	 * @return number of cache miss
	 */
	public long nbMiss() {
		return nbMiss;
	}

	private HashMap<Integer, BOSO> cachedValues = new HashMap<>();

	protected BOSO boso(int itemID) {
		BOSO ret = cachedValues.get(itemID);
		if (ret == null) {
			nbMiss++;
			String url = baseurl + "&typeid=" + itemID;
			ret = new BOSO();
			cachedValues.put(itemID, ret);
			try {
				Document page = Jsoup.connect(url).get();
				Elements bos = page.select("buy max");
				if (!bos.isEmpty()) {
					ret.bo = Double.parseDouble(bos.get(0).ownText());
				}
				Elements sos = page.select("sell min");
				if (!sos.isEmpty()) {
					ret.so = Double.parseDouble(sos.get(0).ownText());
				}
			} catch (IOException e) {
			}
		}
		return ret;
	}

	public double getBO(int itemID) {
		return boso(itemID).bo;
	}

	public double getSO(int itemID) {
		return boso(itemID).so;
	}

	/**
	 * cache the data about several items
	 */
	public void cache(int... itemIDs) {
		if (itemIDs == null) {
			return;
		}
		// only keep the ids we don't already have cached
		itemIDs = IntStream.of(itemIDs).filter(i -> !cachedValues.containsKey(i)).toArray();
		if (itemIDs.length == 0) {
			return;// if all data is already cached, no reason to keep going
		}

		// evecentral can't have urls with more than 2048 characters.
		// base url = 60-65 cars
		// each item = +12 cars
		// so max ~ 160 items
		int maxNbItems = 130;
		if (itemIDs.length > maxNbItems) {
			for (int i = 0; i < itemIDs.length; i += maxNbItems) {
				cache(Arrays.copyOfRange(itemIDs, i, Math.min(i + maxNbItems, itemIDs.length)));
			}
			return;
		}
		StringBuilder sb = new StringBuilder(baseurl);
		for (int id : itemIDs) {
			sb.append("&typeid=").append(id);
		}
		try {
			Document page = Jsoup.connect(sb.toString()).get();
			for (int id : itemIDs) {
				BOSO boso = new BOSO();
				Elements bos = page.select("[id=" + id + "] buy max");
				if (!bos.isEmpty()) {
					boso.bo = Double.parseDouble(bos.get(0).ownText());
				}
				Elements sos = page.select("[id=" + id + "] sell min");
				if (!sos.isEmpty()) {
					boso.so = Double.parseDouble(sos.get(0).ownText());
				}
				cachedValues.put(id, boso);
			}
		} catch (IOException e) {
			System.err.println("while accessing " + sb.toString());
			e.printStackTrace(System.err);
		}
	}

}
