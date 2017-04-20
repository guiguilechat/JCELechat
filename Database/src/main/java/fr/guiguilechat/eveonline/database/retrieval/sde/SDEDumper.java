package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.database.yaml.Asteroid;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Blueprint.Activity;
import fr.guiguilechat.eveonline.database.yaml.DatabaseFile;
import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.Module;
import fr.guiguilechat.eveonline.database.yaml.Type;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeAttributes;
import fr.guiguilechat.eveonline.sde.bsd.EdgmTypeEffects;
import fr.guiguilechat.eveonline.sde.cache.SDECache;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.sde.fsd.Eblueprints.Material;
import fr.guiguilechat.eveonline.sde.fsd.EcategoryIDs;
import fr.guiguilechat.eveonline.sde.fsd.EgroupIDs;
import fr.guiguilechat.eveonline.sde.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.sde.model.IndustryUsages;

public class SDEDumper {

	public static final File DB_DIR = new File("src/main/resources/SDEDump/");

	public static final String DB_HULLS_RES = "SDEDump/hulls.yaml";
	public static final File DB_HULLS_FILE = new File("src/main/resources/", DB_HULLS_RES);

	public static final String DB_MODULES_RES = "SDEDump/modules.yaml";
	public static final File DB_MODULES_FILE = new File("src/main/resources", DB_MODULES_RES);

	public static final String DB_ASTEROIDS_RES = "SDEDump/asteroids.yaml";
	public static final File DB_ASTEROIDS_FILE = new File("src/main/resources", DB_ASTEROIDS_RES);

	public static final String DB_BLUEPRINT_RES = "SDEDump/blueprints.yaml";
	public static final File DB_BLUEPRINT_FILE = new File("src/main/resources", DB_BLUEPRINT_RES);

	public static final String DB_IDS_RES = "SDEDump/ids.yaml";
	public static final File DB_IDS_FILE = new File("src/main/resources", DB_IDS_RES);

	public static void main(String[] args) throws IOException {
		DatabaseFile db = loadDb();
		DB_DIR.mkdirs();

		DatabaseFile dbModules = new DatabaseFile();
		dbModules.modules = db.modules;
		db.modules = new LinkedHashMap<>();
		YamlDatabase.write(dbModules, DB_MODULES_FILE);

		DatabaseFile dbAsteroids = new DatabaseFile();
		dbAsteroids.asteroids = db.asteroids;
		db.asteroids = new LinkedHashMap<>();
		YamlDatabase.write(dbAsteroids, DB_ASTEROIDS_FILE);

		DatabaseFile dbBlueprints = new DatabaseFile();
		dbBlueprints.blueprints = db.blueprints;
		db.blueprints = new LinkedHashMap<>();
		YamlDatabase.write(dbBlueprints, DB_BLUEPRINT_FILE);

		DatabaseFile dbIDs = new DatabaseFile();
		dbIDs.eveIDs = db.eveIDs;
		db.eveIDs = new LinkedHashMap<>();
		YamlDatabase.write(dbIDs, DB_IDS_FILE);

		YamlDatabase.write(db, DB_HULLS_FILE);
	}

	public static DatabaseFile loadDb() throws FileNotFoundException {
		SDEData sde = new SDEData();
		DatabaseFile db = new DatabaseFile();
		Set<Integer> shipGroups = new HashSet<>();
		Set<Integer> modulesGroups = new HashSet<>();
		findShipModuleGroups(shipGroups, modulesGroups);

		// first pass to create the modules and the hulls
		// the attributes will be stored afterwards, once we know which attributes
		// we actually need
		for (Entry<Integer, EtypeIDs> e : sde.getTypeIDs().entrySet()) {
			EtypeIDs elem = e.getValue();
			if (elem.published) {
				Integer groupId = elem.groupID;
				if (shipGroups.contains(groupId)) {
					Hull h = new Hull();
					db.hulls.put(elem.enName(), h);
					loadTypeInformations(h, sde, e.getKey());
					h.attributes.mass = (long) elem.mass;
					h.attributes.volume = (long) elem.volume;
					HashMap<Integer, EdgmTypeEffects> m_effects = sde.getTypeEffects().get(e.getKey());
					if (m_effects != null) {
						for (Integer effect_id : m_effects.keySet()) {
							h.effects.add(sde.getEffects().get(effect_id).effectName);
						}
					}
				} else if (modulesGroups.contains(groupId)) {
					Module m = new Module();
					db.modules.put(elem.enName(), m);
					loadTypeInformations(m, sde, e.getKey());
					HashMap<Integer, EdgmTypeEffects> m_effects = sde.getTypeEffects().get(e.getKey());
					if (m_effects != null) {
						for (Integer effect_id : m_effects.keySet()) {
							m.effects.add(sde.getEffects().get(effect_id).effectName);
						}
					}
				}
			}
		}

		// for each hull, its map of attributeName->
		// attributeValue
		// skip anything that is not hull nor module to free memory
		for (Hull h : db.hulls.values()) {
			LinkedHashMap<String, Object> atts = new LinkedHashMap<>();
			for (Entry<Integer, EdgmTypeAttributes> e : sde.getTypeAttributes().get(h.id).entrySet()) {
				String attributename = sde.getAttributeTypes().get(e.getKey()).attributeName;
				EdgmTypeAttributes affect = e.getValue();
				atts.put(attributename, affect.valueFloat != 0 ? affect.valueFloat : affect.valueInt);
			}
			addHullAttributes(h, atts);
		}
		// same for modules
		for (Module m : db.modules.values()) {
			LinkedHashMap<String, Object> atts = new LinkedHashMap<>();
			for (Entry<Integer, EdgmTypeAttributes> e : sde.getTypeAttributes().get(m.id).entrySet()) {
				String attributename = sde.getAttributeTypes().get(e.getKey()).attributeName;
				EdgmTypeAttributes affect = e.getValue();
				atts.put(attributename, affect.valueFloat != 0 ? affect.valueFloat : affect.valueInt);
			}
			addModuleAttributes(m, atts);
		}

		loadAsteroids(sde, db);

		loadBlueprints(sde, db);

		loadIDs(sde, db);

		System.err.println("missings ids " + sde.missings);

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
	public static void findShipModuleGroups(Set<Integer> shipGroups, Set<Integer> modulesGroups)
			throws FileNotFoundException {
		// find category for modules and ships
		int shipCat = -1, moduleCat = -1;
		SDECache.donwloadSDE();
		File CategoryYaml = new File(SDECache.CHECKDIR, "fsd/categoryIDs.yaml");
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
		File groupYaml = new File(SDECache.CHECKDIR, "fsd/groupIDs.yaml");
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

	/**
	 * store common informations in a type
	 *
	 * @param type
	 *          the actual type to store data into
	 * @param sde
	 *          the database from sde
	 * @param id
	 *          the int id of the type we want to store data
	 */
	public static void loadTypeInformations(Type type, SDEData sde, int id) {
		EtypeIDs elem = sde.getType(id);
		type.id = id;
		if (elem == null) {
			type.name = "unknown_" + id;
			return;
		}
		type.name = elem.enName();
		EgroupIDs group = sde.getGroupIDs().get(elem.groupID);
		EcategoryIDs cat = sde.getCategoryIDs().get(group.categoryID);
		type.groupName = group.enName();
		type.catName = cat.enName();
		type.volume = elem.volume;
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
		module.attributes.metaLevel = getelemInt(attributes, "metaLevel", 0);
		module.rawAttributes = attributes;
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

	public static void loadAsteroids(SDEData sde, DatabaseFile db) {
		sde.getTypeIDsForCategory(25).forEach(i -> {
			EtypeIDs type = sde.getType(i);
			Asteroid a = new Asteroid();
			loadTypeInformations(a, sde, i);
			db.asteroids.put(a.name, a);
			String desc = type.description.getOrDefault("en", "");
			if (desc.contains("Available")) {
				String availables = desc.replaceAll("\\n|\\r", "").replaceAll(".*'>", "").replaceAll("</.*", "");
				a.maxSecurity = Float.parseFloat(availables);
			} else {
				a.maxSecurity = -1.0;
			}
		});
		for (Entry<String, Asteroid> e : db.asteroids.entrySet()) {
			Asteroid a = e.getValue();
			IndustryUsages usages = sde.getIndustryUsages().get(a.id);
			if (usages != null) {
				for (Eblueprints l : usages.asMaterial) {
					Material product = l.activities.manufacturing.products.get(0);
					EtypeIDs prodType = sde.getType(product.typeID);
					String prodName = prodType.enName();
					a.compressedTo = prodName;
					Asteroid astProduct = db.asteroids.get(prodName);
					astProduct.compressedFrom = e.getKey();
					astProduct.compressRatio = prodType.groupID == 465 ? 1 : 100;
				}
			}
		}
	}

	public static void loadBlueprints(SDEData sde, DatabaseFile db) {
		for (Entry<Integer, Eblueprints> e : sde.getBlueprints().entrySet()) {
			int id = e.getKey();
			Eblueprints bp = e.getValue();
			Blueprint bp2 = new Blueprint();
			EtypeIDs item = sde.getType(id);
			if (item == null || !item.published) {
				// System.err.println("skip generating data for bp " + id);
				continue;
			}
			loadTypeInformations(bp2, sde, id);
			db.blueprints.put(bp2.name, bp2);
			bp2.copying = new Activity(bp.activities.copying, sde);
			bp2.invention = new Activity(bp.activities.invention, sde);
			bp2.manufacturing = new Activity(bp.activities.manufacturing, sde);
			bp2.research_material = new Activity(bp.activities.research_material, sde);
			bp2.research_time = new Activity(bp.activities.research_time, sde);
		}
	}

	public static void loadIDs(SDEData sde, DatabaseFile db) {
		for (Entry<Integer, EtypeIDs> e : sde.getTypeIDs().entrySet()) {
			EtypeIDs item = e.getValue();
			if (item.published) {
				db.eveIDs.put(item.enName(), e.getKey());
			}
		}
	}

}
