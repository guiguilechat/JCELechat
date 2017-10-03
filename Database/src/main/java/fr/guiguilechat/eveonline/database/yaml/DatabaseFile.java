package fr.guiguilechat.eveonline.database.yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * a file for database loading from yaml
 *
 */
public class DatabaseFile {

	public LinkedHashMap<String, Hull> hulls = new LinkedHashMap<>();

	public LinkedHashMap<String, Module> modules = new LinkedHashMap<>();

	public LinkedHashMap<String, Asteroid> asteroids = new LinkedHashMap<>();

	public LinkedHashMap<String, Blueprint> blueprints = new LinkedHashMap<>();

	public LinkedHashMap<String, MetaInf> metaInfs = new LinkedHashMap<>();

	public LinkedHashMap<String, Location> locations = new LinkedHashMap<>();

	public ArrayList<LPOffer> lpoffers = new ArrayList<>();

	public LinkedHashMap<String, Agent> agents = new LinkedHashMap<>();

	public LinkedHashMap<String, Station> stations = new LinkedHashMap<>();

	/**
	 * assimilates another DB. replaces values already stored as X if X is present
	 * in db
	 *
	 * @param db
	 */
	public void merge(DatabaseFile db) {
		hulls.putAll(db.hulls);
		modules.putAll(db.modules);
		asteroids.putAll(db.asteroids);
		blueprints.putAll(db.blueprints);
		metaInfs.putAll(db.metaInfs);
		locations.putAll(db.locations);
		lpoffers.addAll(db.lpoffers);
		agents.putAll(db.agents);
		stations.putAll(db.stations);
	}


}
