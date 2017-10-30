package fr.guiguilechat.eveonline.model.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Char {

	private static final Logger logger = LoggerFactory.getLogger(Char.class);

	private final APIRoot parent;

	public Char(APIRoot parent) {
		this.parent = parent;
	}

	public final String BASEURL = APIRoot.BASEURL + "char/";

	public static class Content {
		/**
		 * bigint Unique ID for this item. This is only guaranteed to be unique
		 * within this page load. IDs are recycled over time and it is possible for
		 * this to happen. Also, items are not guaranteed to maintain the same
		 * itemID over time. When they are repackaged, stacks are split or merged,
		 * when they're assembled, and other actions can cause itemIDs to change.
		 * Please note that the type of this value has been changed from int to
		 * bigint with Tyrannis 1.2
		 */
		public long itemID;
		/** The type of this item. References the invTypes table. */
		public int typeID;
		public long quantity = 1;
		public int flag = 0;
		public boolean singleton = false;
		public int rawQuantity;
	}

	public static class StationContent {

	}

	public LinkedHashMap<Long, ArrayList<Content>> assetList(long charID) {
		return url2assets(BASEURL + "AssetList.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

	static LinkedHashMap<Long, ArrayList<Content>> url2assets(String url) {
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
				locCtt.add(extractContent(el));
				for (Element ct : el.select("rowset row")) {
					locCtt.add(extractContent(ct));
				}
			}
		} catch (IOException ex) {
			logger.debug("while fetching " + url, ex);
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

	public List<BPEntry> blueprints(long charID) {
		return url2bps(BASEURL + "Blueprints.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

	public static List<BPEntry> url2bps(String url) {
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
		return APIRoot.convertElement(el, BPEntry.class);
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

	/**
	 * http://eveonline-third-party-documentation.readthedocs.io/en/latest/xmlapi/character/char_industryjobs.html
	 */

	public static class JobEntry {
		public long jobID;
		public long installerID;
		public String installerName;
		public long facilityID;
		public long solarSystemID;
		public String solarSystemName;
		public long stationID;
		public long activityID;
		public long blueprintID;
		public int blueprintTypeID;
		public String blueprintTypeName;
		public long blueprintLocationID;
		public long outputLocationID;
		public int runs;
		public double cost;
		public int licensedRuns;
		public double probability;
		public int productTypeID;
		public String productTypeName;
		public int status;
		public int timeInSeconds;
		public Date startDate;
		public Date endDate;
		public Date pauseDate;
		public Date completedDate;
		public long completedCharacterID;
		public int successfulRuns;
	}

	public static final String ACTIVITY_PROD = "prod";
	public static final String ACTIVITY_TE = "TE";
	public static final String ACTIVITY_ME = "ME";
	public static final String ACTIVITY_COPY = "CP";

	public static final Set<String> activityNamesSet = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList(ACTIVITY_PROD, ACTIVITY_TE, ACTIVITY_ME, ACTIVITY_COPY)));

	public static String activityName(long actividyID) {
		switch ((int) actividyID) {
		case 1:
			return ACTIVITY_PROD;
		case 3:
			return ACTIVITY_TE;
		case 4:
			return ACTIVITY_ME;
		case 5:
			return ACTIVITY_COPY;
		default:
			return "unknown" + actividyID;
		}

	}

	public ArrayList<JobEntry> industryJobs(long charID) {
		return url2industryJobs(BASEURL + "IndustryJobs.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

	static ArrayList<JobEntry> url2industryJobs(String url) {
		ArrayList<JobEntry> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.add(extractJobEntry(el));
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return ret;
	}

	protected static JobEntry extractJobEntry(Element el) {
		return APIRoot.convertElement(el, JobEntry.class);
	}

	/**
	 * http://eveonline-third-party-documentation.readthedocs.io/en/latest/xmlapi/character/char_marketorders.html
	 */
	public static class OrderEntry {
		public long orderID;
		public long charID;
		public long stationID;
		public int volEntered;
		public int volRemaining;
		public int minVolume;
		public int orderState;
		public int typeID;
		/**
		 * <ul>
		 * <li>-1 : station</li>
		 * <li>0 : system</li>
		 * <li>5,10,20,40 : region jumps</li>
		 * <li>>40 : region or SO</li>
		 * </ul>
		 */
		public int range;
		public int accountKey;
		public int duration;
		public double escrow;
		public double price;
		public boolean bid;
		public Date datetime;

		public boolean isBuyOrder() {
			return bid;
		}

		public boolean isOpen() {
			return orderState == 0;
		}
	}

	/** Returns a list of market orders for your character. */
	public List<OrderEntry> marketOrders(long charID) {
		return url2marketOrders(BASEURL + "MarketOrders.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

	static List<OrderEntry> url2marketOrders(String url) {
		ArrayList<OrderEntry> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.add(extractMarketOrder(el));
			}
		} catch (IOException e) {
			logger.debug("while fetching " + url, e);
			return Collections.emptyList();
		}
		return ret;

	}

	protected static OrderEntry extractMarketOrder(Element el) {
		return APIRoot.convertElement(el, OrderEntry.class);
	}

}
