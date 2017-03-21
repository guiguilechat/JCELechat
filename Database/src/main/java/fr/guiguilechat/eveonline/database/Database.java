package fr.guiguilechat.eveonline.database;

import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;

public class Database {

	public LinkedHashMap<Integer, Hull> hulls = new LinkedHashMap<>();

	public LinkedHashMap<Integer, Module> modules = new LinkedHashMap<>();

	/**
	 * assimilates another DB. replaces values already stored as X if X is present
	 * in db
	 * 
	 * @param db
	 */
	public void merge(Database db) {
		hulls.putAll(db.hulls);
		modules.putAll(db.modules);
	}

}
