package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.Parser;
import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;
import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmEffects;
import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.database.retrieval.sde.bsd.EdgmTypeEffects;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;

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

	public static final String DB_HULLS_RES = "SDEDump/hulls.yaml";
	public static final File DB_HULLS_FILE = new File("src/main/resources/", DB_HULLS_RES);
	public static final String DB_MODULES_RES = "SDEDump/modules.yaml";
	public static final File DB_MODULES_FILE = new File("src/main/resources", DB_MODULES_RES);

	/**
	 * where we want to download the SDE from
	 */
	public static final String SDE_URL = "https://cdn1.eveonline.com/data/sde/tranquility/sde-20170216-TRANQUILITY.zip";

	/**
	 * if {@link #CHECKDIR} is not a directory, download the full yaml from the
	 * sde . those files will be extracted and placed in {@link #CACHEDIR}
	 */
	public static void donwloadSDE() {
		if (CHECKDIR.isDirectory()) {
			return;
		}
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
		Parser.write(db, DB_HULLS_FILE);
		Parser.write(dbModules, DB_MODULES_FILE);
	}

	@SuppressWarnings({ "unchecked" })
	public static Database loadDb() throws FileNotFoundException {
		donwloadSDE();
		Database db = new Database();
		Map<Integer, String> shipGroups = new LinkedHashMap<>();
		Map<Integer, String> modulesGroups = new LinkedHashMap<>();
		findShipModuleGroups(shipGroups, modulesGroups);

		HashMap<Integer, EdgmEffects> effects = EdgmEffects.loadByIndex();
		HashMap<Integer, HashMap<Integer, EdgmTypeEffects>> typeEffectsByTypeIDEffectID = EdgmTypeEffects.loadByTypeIDEffectID();

		LinkedHashMap<Integer, EtypeIDs> types = EtypeIDs.load();
		for (Entry<Integer, EtypeIDs> e : types.entrySet()) {
			EtypeIDs elem = e.getValue();
			if (elem.published) {
				Integer groupId = elem.groupID;
				if (shipGroups.containsKey(groupId)) {
					Hull h = new Hull();
					db.hulls.put(e.getKey(), h);
					h.name = elem.name.get("en");
					h.group = shipGroups.get(groupId);
					h.attributes.mass = (long) elem.mass;
					h.attributes.volume = (long) elem.volume;
					HashMap<Integer, EdgmTypeEffects> m_effects = typeEffectsByTypeIDEffectID.get(e.getKey());
					if (m_effects != null) {
						for (Integer effect_id : m_effects.keySet()) {
							h.effects.add(effects.get(effect_id).effectName);
						}
					}
				} else if (modulesGroups.containsKey(groupId)) {
					Module m = new Module();
					db.modules.put(e.getKey(), m);
					m.name = elem.name.get("en");
					m.group = modulesGroups.get(groupId);
					HashMap<Integer, EdgmTypeEffects> m_effects = typeEffectsByTypeIDEffectID.get(e.getKey());
					if (m_effects != null) {
						for (Integer effect_id : m_effects.keySet()) {
							m.effects.add(effects.get(effect_id).effectName);
						}
					}
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

		// for each [ hull | module ] i, its map of attributeName->
		// attributeValue
		// skip anything that is not hull nor module to free memory
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> hullAttributes = new LinkedHashMap<>();
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> moduleAttributes = new LinkedHashMap<>();
		HashMap<Integer, HashMap<Integer, EdgmTypeAttributes>> attributesByTypeId = EdgmTypeAttributes
				.loadByTypeIDAttributeID();
		for (Entry<Integer, HashMap<Integer, EdgmTypeAttributes>> e : attributesByTypeId.entrySet()) {
			int typeID = e.getKey();
			// the map corresponding to the item id
			LinkedHashMap<String, Object> atts = null;
			if (db.hulls.containsKey(typeID)) {
				atts = hullAttributes.get(typeID);
				if (atts == null) {
					atts = new LinkedHashMap<>();
					hullAttributes.put(typeID, atts);
				}
			} else if (db.modules.containsKey(typeID)) {
				atts = moduleAttributes.get(typeID);
				if (atts == null) {
					atts = new LinkedHashMap<>();
					moduleAttributes.put(typeID, atts);
				}
			}
			// the id correspond to either a ship or a module: translate the
			// affect to name->value
			if (atts != null) {
				for (Entry<Integer, EdgmTypeAttributes> e2 : e.getValue().entrySet()) {
					String attributename = attributesByIndex.get(e2.getKey());
					EdgmTypeAttributes affect = e2.getValue();
					atts.put(attributename, affect.valueFloat != 0 ? affect.valueFloat : affect.valueInt);
				}
			}
		}

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
	 * group corresponding to the module category.
	 *
	 * @param shipGroups
	 *          the ship groups
	 * @param modulesGroups
	 *          the modules groups
	 * @throws FileNotFoundException
	 *           if the database file are not found
	 */
	@SuppressWarnings("unchecked")
	public static final void findShipModuleGroups(Map<Integer, String> shipGroups, Map<Integer, String> modulesGroups)
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
					shipGroups.put(e.getKey(), getElemName(e.getValue()));
				}
				if (groupCat == moduleCat) {
					modulesGroups.put(e.getKey(), getElemName(e.getValue()));
				}
			}
		}
	}

	public static void addHullAttributes(Hull hull, LinkedHashMap<String, Object> attributes) {

		hull.attributes.velocity = getelemInt(attributes, "maxVelocity", 0);
		hull.attributes.warpSpeed = getelemDouble(attributes, "baseWarpSpeed", 1.0)
				* getelemDouble(attributes, "warpSpeedMultiplier", 1.0);
		hull.attributes.agility = getelemDouble(attributes, "agility", 0.0);

		hull.attributes.targetRange = getelemInt(attributes, "maxTargetRange", 0);
		hull.attributes.scanRes = getelemInt(attributes, "scanResolution", 0);
		int scanLadar = getelemInt(attributes, "scanLadarStrength", 0);
		int scanRadar = getelemInt(attributes, "scanRadarStrength", 0);
		int scanGravi = getelemInt(attributes, "scanGravimetricStrength", 0);
		int scanMagne = getelemInt(attributes, "scanMagnetometricStrength", 0);
		hull.attributes.scanStr = Math.max(Math.max(scanMagne, scanGravi), Math.max(scanLadar, scanRadar));
		if (hull.attributes.scanStr == scanLadar) {
			hull.attributes.scanType = "LADAR";
		}
		if (hull.attributes.scanStr == scanRadar) {
			hull.attributes.scanType = "RADAR";
		}
		if (hull.attributes.scanStr == scanMagne) {
			hull.attributes.scanType = "Magnetometric";
		}
		if (hull.attributes.scanStr == scanGravi) {
			hull.attributes.scanType = "Gravimetric";
		}
		hull.attributes.maxTargets = getelemInt(attributes, "maxLockedTargets", 0);

		hull.attributes.highSlots = getelemInt(attributes, "hiSlots", 0);
		hull.attributes.mediumSlots = getelemInt(attributes, "medSlots", 0);
		hull.attributes.lowSlots = getelemInt(attributes, "lowSlots", 0);
		hull.attributes.launcherHardPoints = getelemInt(attributes, "launcherSlotsLeft", 0);
		hull.attributes.turretHardPoints = getelemInt(attributes, "turretSlotsLeft", 0);
		hull.attributes.cpu = getelemInt(attributes, "cpuOutput", 0);
		hull.attributes.powergrid = getelemInt(attributes, "powerOutput", 0);
		hull.attributes.capacitor = getelemInt(attributes, "capacitorCapacity", 0);
		hull.attributes.capacitorTime = getelemDouble(attributes, "rechargeRate", 0.0) / 1000;

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

		hull.attributes.droneCapa = getelemInt(attributes, "droneCapacity", 0);
		hull.attributes.droneBandwidth = getelemInt(attributes, "droneBandwidth", 0);

	}

	public static void addModuleAttributes(Module module, LinkedHashMap<String, Object> attributes) {
		module.attributes.cpu = getelemInt(attributes, "cpu", 0);
		module.attributes.powergrid = getelemInt(attributes, "power", 0);
		if (module.effects.contains("hiPower")) {
			module.attributes.slot = "high";
		}
		if (module.effects.contains("medPower")) {
			module.attributes.slot = "medium";
		}
		if (module.effects.contains("loPower")) {
			module.attributes.slot = "low";
		}
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
