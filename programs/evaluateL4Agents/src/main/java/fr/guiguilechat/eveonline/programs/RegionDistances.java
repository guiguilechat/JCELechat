package fr.guiguilechat.eveonline.programs;

import fr.guiguilechat.eveonline.database.DataBase;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

/** distances of systems in a region */
public class RegionDistances {

	protected DataBase db;
	public final String regionName;

	public RegionDistances(DataBase db, String regionName) {
		this.db = db;
		this.regionName = regionName;
	}

	public RegionDistances(String regionName) {
		this(new YamlDatabase(), regionName);
	}

}
