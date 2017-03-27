package fr.guiguilechat.eveonline.database;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * get the price in Jita by default. prices are bo/so
 *
 */
public class EveCentral {

	public static final long JITA_SYSTEM=30000142;

	public final long system;

	public EveCentral(long system) {
		this.system = system;
	}

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

	private HashMap<Integer, BOSO> cachedValues = new HashMap<>();

	protected BOSO boso(int itemID) {
		BOSO ret = cachedValues.get(itemID);
		if (ret == null) {
			String url = "https://api.eve-central.com/api/marketstat?typeid=" + itemID + "&usesystem=" + system;
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
		if(itemIDs==null || itemIDs.length==0) {return ;}
		StringBuilder sb = new StringBuilder("https://api.eve-central.com/api/marketstat?usesystem"+system);
		for(long id : itemIDs) {
			sb.append("&typeid=").append(id);
		}
		Document page;
		try {
			page = Jsoup.connect(sb.toString()).get();
			for(long id : itemIDs) {
				BOSO boso = new BOSO();
				Elements bos = page.select("[id="+id+"] buy max");
				if (!bos.isEmpty()) {
					boso.bo = Double.parseDouble(bos.get(0).ownText());
				}
				Elements sos = page.select("[id="+id+"] sell min");
				if (!sos.isEmpty()) {
					boso.so = Double.parseDouble(sos.get(0).ownText());
				}
				// System.err.println("" + id + ": " + boso);
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

}
