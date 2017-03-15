package fr.guiguilechat.eveonline.database.retrieval;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.Parser;
import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;
import fr.guiguilechat.eveonline.database.elements.ModuleActivation;

public class ChrukerDumper {

	public static final String SHIPS_PAGE = "http://games.chruker.dk/eve_online/market.php?group_id=4";
	public static final String MODULES_PAGE = "http://games.chruker.dk/eve_online/market.php?group_id=9";
	public static final String HERETIC_PAGE = "http://games.chruker.dk/eve_online/market.php?group_id=826";
	public static final String CHRUKER_HULLS_RES = "chrukerdump/hulls.yml";
	public static final String CHRUKER_HULLS_FILE = "src/main/resources/" + CHRUKER_HULLS_RES;
	public static final String CHRUKER_MODULES_RES = "chrukerdump/modules.yml";
	public static final String CHRUKER_MODULES_FILE = "src/main/resources/" + CHRUKER_MODULES_RES;

	public static final File CHRUKER_CACHE = new File("target/chruker/");

	public static void main(String[] args) throws IOException {
		Database db = dumpChrukerHulls();
		Parser.write(db, new File(CHRUKER_HULLS_FILE));
		db = dumpChrukerModules();
		Parser.write(db, new File(CHRUKER_MODULES_FILE));
	}

	public static Database dumpChrukerHulls() throws IOException {
		CHRUKER_CACHE.mkdirs();
		Database db = new Database();
		updateDatabase(SHIPS_PAGE, db, ChrukerDumper::addShipData);
		return db;
	}

	public static Database dumpChrukerModules() throws IOException {
		CHRUKER_CACHE.mkdirs();
		Database db = new Database();
		updateDatabase(MODULES_PAGE, db, ChrukerDumper::addModuleData);
		return db;

	}

	public static Document getCached(String id, String url) {
		Document page = null;
		File cached = new File(CHRUKER_CACHE, id + ".html");
		if (cached.exists()) {
			try {
				page = Jsoup.parse(cached, "UTF-8", url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (page == null) {
			System.err.println("caching element " + id);
			try {
				page = Jsoup.connect(url).get();
				FileWriter w = new FileWriter(cached);
				w.write(page.outerHtml());
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	protected static void updateDatabase(String url, Database db, BiConsumer<String, Database> handler)
			throws IOException {
		try {
			int gid = Integer.parseInt(url.replaceAll(".*group_id=", ""));
			Document page = getCached("g_" + gid, url);
			Set<String> subgroups = page.select("td[align=center] a[href]").stream()
					.map(e -> e.absUrl("href"))
					.collect(Collectors.toSet());
			for (String subURL : subgroups) {
				updateDatabase(subURL, db, handler);
			}
			Set<String> s = page.select("a[href~=.*type_id.*]").stream().map(e -> e.absUrl("href")).collect(Collectors.toSet());
			for (String u : s) {
				handler.accept(u, db);
			}
		} catch (IOException | RuntimeException e) {
			System.err.println("while loading group " + url + " : " + e);
			throw e;
		}
	}

	protected final static String[] SENSOR_TYPES = { "RADAR", "LADAR", "Magnetometric", "Gravimetric" };

	protected static void addShipData(String url, Database db) {
		Hull hull = new Hull();
		int id = Integer.parseInt(url.replaceAll(".*type_id=", ""));
		db.hulls.put(id, hull);

		Document page = getCached("i_" + id, url);
		hull.name = page.select("h1").get(0).html();
		hull.attributes.highSlots = getAttributeInt(page, "High Slots:");
		hull.attributes.mediumSlots = getAttributeInt(page, "Medium Slots:");
		hull.attributes.lowSlots = getAttributeInt(page, "Low Slots:");
		hull.attributes.launcherHardPoints = getAttributeInt(page, "Launcher Hardpoints:");
		hull.attributes.turretHardPoints = getAttributeInt(page, "Turret Hardpoints:");

		hull.attributes.cpu = getAttributeInt(page, "CPU:");
		hull.attributes.powergrid = getAttributeInt(page, "Powergrid:");
		hull.attributes.capacitor = getAttributeFloat(page, "Capacitor Capacity:");
		hull.attributes.capacitorTime = getAttributeFloat(page, "Capacitor Recharge Time:");

		hull.attributes.rigSlots = getAttributeInt(page, "Rig Slots:");
		hull.attributes.rigCalibration = getAttributeInt(page, "Rig Calibration:");
		hull.attributes.rigSize = getAttribute(page, "Rig Size:");

		hull.attributes.droneCapa = getAttributeInt(page, "Drone Capacity:");
		hull.attributes.droneBandwidth = getAttributeInt(page, "Drone Bandwidth:");

		hull.attributes.velocity = getAttributeInt(page, "Maximum Velocity:");
		hull.attributes.warpSpeed = getAttributeFloat(page, "Warp Speed:");
		hull.attributes.inertiaModifier = getAttributeFloat(page, "Inertia Modifier:");

		hull.attributes.targetRange = getAttributeInt(page, "Maximum Targeting Range:");
		hull.attributes.scanRes = getAttributeInt(page, "Scan Resolution:");
		hull.attributes.maxTargets = getAttributeInt(page, "Maximum Locked Targets:");

		for (String sensorType : SENSOR_TYPES) {
			int str = getAttributeInt(page, sensorType + " Sensor Strength:");
			if (str != 0) {
				hull.attributes.scanType = sensorType;
				hull.attributes.scanStr = str;
				break;
			}
		}
		System.err.println(hull.name);
	}

	protected static void addModuleData(String url, Database db) {
		Module module = new Module();
		int id = Integer.parseInt(url.replaceAll(".*type_id=", ""));
		db.modules.put(id, module);

		Document page = getCached("i_" + id, url);
		module.name = page.select("h1").get(0).html();
		module.attributes.cpu = getAttributeInt(page, "CPU:");
		module.attributes.powergrid = getAttributeInt(page, "PowerGrid:");
		String slot = getAttribute(page, "Slot:");
		if (slot != null) {
			module.attributes.slot = slot.replace("Requires a ", "").replace(" power slot", "");
		}
		String hardpoint = getAttribute(page, "Hardpoint:");
		if (hardpoint != null) {
			module.attributes.hardpoint=hardpoint.replace("Requires a ", "").replace(" hardpoint", "");
		}
		float activationCost = getAttributeFloat(page, "Activation Cost:");
		if (activationCost > 0) {
			ModuleActivation activ = module.activation= new ModuleActivation();
			activ.cost=activationCost;
			float modDuration = getAttributeFloat(page, "Duration:");
			float weapDuration = getAttributeFloat(page, "Rate of Fire:");
			activ.duration = Math.max(modDuration, weapDuration);
		}


		System.err.println(module.name);
	}

	/**
	 * find the text of an element which contains another element with given
	 * text<br />
	 * eg
	 *
	 * <pre>
	 * <td><b>bla</b>text
	 * </pre>
	 *
	 * should return "text" when requested "bla".
	 *
	 * @param page
	 *          the page data
	 * @param attribute
	 *          the attribute we want the data
	 * @return the data of the attribute if exists, null otherwise
	 */
	protected static String getAttribute(Document page, String attribute) {
		Elements els = page.select(":containsOwn(" + attribute + ")");
		if (els.isEmpty()) {
			return null;
		} else {
			return els.get(0).parent().ownText();
		}
	}

	/**
	 * convert {@link #getAttribute(Document, String)} to int, after removing the
	 * possible unit
	 *
	 * @param page
	 * @param attribute
	 * @return
	 */
	protected static int getAttributeInt(Document page, String attribute) {
		String val = getAttribute(page, attribute);
		if (val == null) {
			return 0;
		}
		return Integer.parseInt(val.split(" ")[0].replaceAll(",", ""));
	}

	/**
	 * convert {@link #getAttribute(Document, String)} to int, after removing the
	 * possible unit
	 *
	 * @param page
	 * @param attribute
	 * @return
	 */
	protected static float getAttributeFloat(Document page, String attribute) {
		String val = getAttribute(page, attribute);
		if (val == null) {
			return 0;
		}
		return Float.parseFloat(val.split(" ")[0].replaceAll("[,a-zA-Z]", ""));
	}
}
