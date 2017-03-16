package fr.guiguilechat.eveonline.database.retrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.Parser;
import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;

public class SDEDumper {

	/**
	 * where we want to extract the SDE
	 */
	public static final File CACHEDIR = new File("target/");

	/**
	 * the directory that should be present when we cached the sde
	 */
	public static final File CHECKDIR = new File(CACHEDIR, "sde");

	public static final File DB_DIR = new File("src/main/resources/SDEDump/");

	public static final File DB_FILE_HULLS = new File(DB_DIR, "hulls.yaml");
	public static final File DB_FILE_MODULES = new File(DB_DIR, "modules.yaml");

	/**
	 * where we want to download the SDE from
	 */
	public static final String SDE_URL = "https://cdn1.eveonline.com/data/sde/tranquility/sde-20170216-TRANQUILITY.zip";

	public static void donwloadSDE() {
		CACHEDIR.mkdirs();
		try {
			InputStream is = new URL(SDE_URL).openStream();
			ZipEntry e;
			try (ZipInputStream zis = new ZipInputStream(is)) {
				while ((e = zis.getNextEntry()) != null) {
					File out = new File(CACHEDIR, e.getName());
					out.getParentFile().mkdirs();
					FileWriter fw = new FileWriter(out);
					byte[] b = new byte[100];
					while (zis.available() > 0) {
						int r = zis.read(b, 0, b.length);
						if (r > -1) {
							fw.write(new String(b, 0, r));
						}
					}
					fw.close();
					System.err.println(e.getName());
				}
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("while downloading the SDE", e);
		}
	}

	public static void main(String[] args) throws IOException {
		Database db = loadDb();
		DB_DIR.mkdirs();
		Database dbModules = new Database();
		dbModules.modules = db.modules;
		db.modules = new LinkedHashMap<>();
		Parser.write(db, DB_FILE_HULLS);
		Parser.write(dbModules, DB_FILE_MODULES);
	}

	@SuppressWarnings({ "unchecked" })
	public static Database loadDb() throws FileNotFoundException {
		if (!CHECKDIR.isDirectory()) {
			System.err.println("can't find " + CHECKDIR + " directory, loading it");
			donwloadSDE();
		}
		Database db = new Database();
		Set<Integer> shipGroups = new LinkedHashSet<>();
		Set<Integer> modulesGroups = new LinkedHashSet<>();
		findShipModuleGroups(shipGroups, modulesGroups);

		File typesYaml = new File(CHECKDIR, "fsd/typeIDs.yaml");
		HashMap<Integer, Map<String, ?>> types = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(typesYaml));
		for (Entry<Integer, Map<String, ?>> e : types.entrySet()) {
			Map<String, ?> elem = e.getValue();
			if (Boolean.TRUE.equals(elem.get("published"))) {
				Integer groupId = (Integer) e.getValue().get("groupID");
				if (shipGroups.contains(groupId)) {
					Hull h = new Hull();
					db.hulls.put(e.getKey(), h);
					h.name = getElemName(elem);
					h.attributes.mass = getelemDouble(elem, "mass", null).longValue();
					h.attributes.volume = getelemDouble(elem, "volume", null).longValue();
				} else if (modulesGroups.contains(groupId)) {
					Module m = new Module();
					db.modules.put(e.getKey(), m);
					m.name = getElemName(e.getValue());
				}
			}
		}

		// for each attribute index, its corresponding name
		LinkedHashMap<Integer, String> attributesByIndex = new LinkedHashMap<>();
		File attDefYaml = new File(CHECKDIR, "bsd/dgmAttributeTypes.yaml");
		List<Map<String, ?>> attributesDefs = (List<Map<String, ?>>) new Yaml().load(new FileReader(attDefYaml));
		for (Map<String, ?> att : attributesDefs) {
			Integer id = (Integer) att.get("attributeID");
			attributesByIndex.put(id, (String) att.get("attributeName"));
		}

		// for each [ hull | module ] i, its map of attributeName-> attributeValue
		// skip anything that is not hull nor module to free memory
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> hullAttributes = new LinkedHashMap<>();
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> moduleAttributes = new LinkedHashMap<>();
		File attYaml = new File(CHECKDIR, "bsd/dgmTypeAttributes.yaml");
		List<Map<String, ?>> attributes = (List<Map<String, ?>>) new Yaml().load(new FileReader(attYaml));
		for (Map<String, ?> affect : attributes) {
			int itemId = (int) affect.get("typeID");
			// the map corresponding to the item id
			LinkedHashMap<String, Object> atts = null;
			if (db.hulls.containsKey(itemId)) {
				atts = hullAttributes.get(itemId);
				if (atts == null) {
					atts = new LinkedHashMap<>();
					hullAttributes.put(itemId, atts);
				}
			} else if (db.modules.containsKey(itemId)) {
				atts = moduleAttributes.get(itemId);
				if (atts == null) {
					atts = new LinkedHashMap<>();
					moduleAttributes.put(itemId, atts);
				}
			}
			// the id correspond to either a ship or a module: translate the affect
			// to name->value
			if (atts != null) {
				String attributename = attributesByIndex.get(affect.get("attributeID"));
				Object attValue = affect.get("valueInt");
				if (attValue == null) {
					attValue = affect.get("valueFloat");
				}
				if (attValue == null) {
					System.err.println("att type not handled in " + affect);
				}
				atts.put(attributename, attValue);
			}
		}

		// limit to X to print the X first ships
		hullAttributes.entrySet().stream().limit(1).forEach(
				e -> {
					System.err.println("\n" + db.hulls.get(e.getKey()).name);
					e.getValue().entrySet().forEach(eso -> System.err.println(eso.getKey() + " : " + eso.getValue()));
				});
		for (Entry<Integer, LinkedHashMap<String, Object>> hullAtts : hullAttributes.entrySet()) {
			addHullAttributes(db.hulls.get(hullAtts.getKey()), hullAtts.getValue());
		}
		for (Entry<Integer, LinkedHashMap<String, Object>> moduleAtts : moduleAttributes.entrySet()) {
			addModuleAttributes(db.modules.get(moduleAtts.getKey()), moduleAtts.getValue());
		}

		return db;
	}

	/**
	 * find the index of groups corresponding to ship category and the index of
	 * groupp corresponding to the module category.
	 *
	 * @param shipGroups
	 *          the ship groups
	 * @param modulesGroups
	 *          the modules groups
	 * @throws FileNotFoundException
	 *           if the database file are not found
	 */
	@SuppressWarnings("unchecked")
	public static final void findShipModuleGroups(Set<Integer> shipGroups, Set<Integer> modulesGroups)
			throws FileNotFoundException {
		// find category for modules and ships
		int shipCat = -1, moduleCat = -1;
		File CategoryYaml = new File(CHECKDIR, "fsd/categoryIDs.yaml");
		HashMap<Integer, Map<String, ?>> categorys = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(CategoryYaml));
		for (Entry<Integer, Map<String, ?>> e : categorys.entrySet()) {
			String catName = getElemName(e.getValue());
			if ("Module".equals(catName)) {
				moduleCat = e.getKey();
			}
			if ("Ship".equals(catName)) {
				shipCat = e.getKey();
			}
		}

		// find groups in modules and ships category
		File groupYaml = new File(CHECKDIR, "fsd/groupIDs.yaml");
		HashMap<Integer, Map<String, ?>> groups = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(groupYaml));
		for (Entry<Integer, Map<String, ?>> e : groups.entrySet()) {
			if (Boolean.TRUE.equals(e.getValue().get("published"))) {
				Integer groupCat = (Integer) e.getValue().get("categoryID");
				if (groupCat == shipCat) {
					shipGroups.add(e.getKey());
				}
				if (groupCat == moduleCat) {
					modulesGroups.add(e.getKey());
				}
			}
		}
	}

	public static void addHullAttributes(Hull hull, LinkedHashMap<String, Object> attributes) {

		hull.attributes.velocity = getelemInt(attributes, "maxVelocity", 0);
		hull.attributes.warpSpeed = getelemDouble(attributes, "baseWarpSpeed", 1.0)
				* getelemDouble(attributes, "warpSpeedMultiplier", 1.0);
		hull.attributes.agility = getelemDouble(attributes, "agility", 0.0);

		hull.attributes.highSlots = getelemInt(attributes, "hiSlots", 0);
		hull.attributes.mediumSlots = getelemInt(attributes, "medSlots", 0);
		hull.attributes.lowSlots = getelemInt(attributes, "lowSlots", 0);
		hull.attributes.launcherHardPoints = getelemInt(attributes, "launcherSlotsLeft", 0);
		hull.attributes.turretHardPoints = getelemInt(attributes, "turretSlotsLeft", 0);
		hull.attributes.cpu = getelemInt(attributes, "cpuOutput", 0);
		hull.attributes.powergrid = getelemInt(attributes, "powerOutput", 0);

		hull.attributes.rigSlots = getelemInt(attributes, "rigSlots", 0);
		hull.attributes.rigCalibration = getelemInt(attributes, "upgradeCapacity", 0);
		int rigSize = getelemInt(attributes, "rigSize", 0);
		switch (rigSize) {
		case 0:
			// no rig
			break;
		case 1:
			hull.attributes.rigSize = "small";
			break;
		case 2:
			hull.attributes.rigSize = "medium";
			break;
		case 3:
			hull.attributes.rigSize = "large";
			break;
		case 4:
			hull.attributes.rigSize = "capital";
			break;
		default:
			System.err.println("no rig size for " + rigSize);
			hull.attributes.rigSize = "unknown";
		}
	}

	public static void addModuleAttributes(Module module, LinkedHashMap<String, Object> attributes) {

	}

	@SuppressWarnings("unchecked")
	public static String getElemName(Map<String, ?> elem) {
		return ((Map<String, String>) elem.get("name")).get("en");
	}

	public static final Double getelemDouble(Map<String, ?> elem, String key, Double defaultValue) {
		Object ret = elem.get(key);
		if (ret == null) {
			return defaultValue;
		}
		return ((Number) ret).doubleValue();
	}

	public static final Integer getelemInt(Map<String, ?> elem, String key, Integer defaultValue) {
		Object ret = elem.get(key);
		if (ret == null) {
			return defaultValue;
		}
		return ((Number) ret).intValue();
	}

}
