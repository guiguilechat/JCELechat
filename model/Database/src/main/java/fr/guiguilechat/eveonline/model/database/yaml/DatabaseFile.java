package fr.guiguilechat.eveonline.model.database.yaml;

import java.util.LinkedHashMap;


/**
 * a file for database loading from yaml
 *
 */
public class DatabaseFile {

	public LinkedHashMap<String, Hull> hulls = new LinkedHashMap<>();

	public LinkedHashMap<String, Module> modules = new LinkedHashMap<>();

	/**
	 * assimilates another DB. replaces values already stored as X if X is present
	 * in db
	 *
	 * @param db
	 */
	public void merge(DatabaseFile db) {
		hulls.putAll(db.hulls);
		modules.putAll(db.modules);
	}


}
