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

	public final long region;

	public EveCentral(long region) {
		this.region = region;
	}

	public EveCentral() {
		this(30000142);
	}

	public static class BOSO {
		public double bo = 0.0;
		public double so = Double.POSITIVE_INFINITY;
	}

	private HashMap<Long, BOSO> cachedValues = new HashMap<>();

	protected BOSO boso(long itemID) {
		BOSO ret = cachedValues.get(itemID);
		if (ret == null) {
			String url = "https://api.eve-central.com/api/marketstat?typeid=" + itemID + "&usesystem=" + region;
			ret = new BOSO();
			cachedValues.put(itemID, ret);
			try {
				Document page = Jsoup.connect(url).get();
				Elements bos = page.select("buy max");
				if (!bos.isEmpty())
					ret.bo = Double.parseDouble(bos.get(0).ownText());
				Elements sos = page.select("sell min");
				if (!sos.isEmpty())
					ret.so = Double.parseDouble(sos.get(0).ownText());
			} catch (IOException e) {
			}
		}
		return ret;
	}

	public double getBO(long itemID) {
		return boso(itemID).bo;
	}

	public double getSO(long itemID) {
		return boso(itemID).so;
	}

}
