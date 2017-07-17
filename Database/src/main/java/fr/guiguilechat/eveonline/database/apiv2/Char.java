package fr.guiguilechat.eveonline.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Char {

	private final APIRoot parent;

	public Char(APIRoot parent) {
		this.parent = parent;
	}

	public final String BASEURL = APIRoot.BASEURL + "char/";

	public static class Content {
		public long itemID;
		public int typeID;
		public int quantity = 1;
		public int flag = 0;
		public boolean singleton = false;
		public int rawQuantity;
	}

	public LinkedHashMap<Long, ArrayList<Content>> assetList(long charID) {
		String url = BASEURL + "AssetList.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID;
		LinkedHashMap<Long, ArrayList<Content>> ret = new LinkedHashMap<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result > rowset > row");
			for (Element el : elements) {
				long locId = Long.parseLong(el.attr("locationID"));
				ArrayList<Content> locCtt = ret.get(locId);
				if (locCtt == null) {
					locCtt = new ArrayList<>();
					ret.put(locId, locCtt);
				}
				for (Element ct : el.select("rowset row")) {
					locCtt.add(extractContent(ct));
					// is this needed ?
					for (Element ct2 : el.select("rowset row")) {
						locCtt.add(extractContent(ct2));
					}
				}
			}
		} catch (IOException ex) {
			return ret;
		}
		return ret;
	}

	protected static Content extractContent(Element ct) {
		Content c = new Content();
		c.itemID = APIRoot.getLong(ct, "itemID", 0);
		c.typeID = APIRoot.getInt(ct, "typeID", 0);
		c.quantity = APIRoot.getInt(ct, "quantity", 1);
		c.flag = APIRoot.getInt(ct, "flag", 0);
		c.singleton = APIRoot.getBool(ct, "singleton", false);
		c.rawQuantity = APIRoot.getInt(ct, "rawQuantity", 0);
		return c;
	}

	public static class BPEntry {
		public long itemID;
		public long locationID;
		public long typeID;
		public String typeName;
		public int flagID;
		public int quantity;
		public int timeEfficiency;
		public int materialEfficiency;
		public int runs;
	}

	public ArrayList<BPEntry> blueprints(long charID) {
		String url = BASEURL + "Blueprints.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID;
		ArrayList<BPEntry> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.add(extractBPEntry(el));
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return ret;
	}

	protected static BPEntry extractBPEntry(Element el) {
		BPEntry bpc = new BPEntry();
		bpc.itemID = APIRoot.getLong(el, "itemID", 0);
		bpc.locationID = APIRoot.getLong(el, "locationID", 0);
		bpc.typeID = APIRoot.getInt(el, "typeID", 0);
		bpc.typeName = el.attr("typeName");
		bpc.flagID = APIRoot.getInt(el, "flagID", 0);
		bpc.quantity = APIRoot.getInt(el, "quantity", 1);
		bpc.timeEfficiency = APIRoot.getInt(el, "timeEfficiency", 1);
		bpc.materialEfficiency = APIRoot.getInt(el, "materialEfficiency", 1);
		bpc.runs = APIRoot.getInt(el, "runs", 1);
		return bpc;
	}

	public LinkedHashMap<String, Integer> skillsByName(long charID) {
		String url = BASEURL + "Skills.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code + "&characterID="
				+ charID;
		LinkedHashMap<String, Integer> ret = new LinkedHashMap<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.put(el.attr("typeName"), Integer.parseInt(el.attr("level")));
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return ret;
	}

}
