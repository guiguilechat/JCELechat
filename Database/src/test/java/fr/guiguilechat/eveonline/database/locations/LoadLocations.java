package fr.guiguilechat.eveonline.database.locations;

import fr.guiguilechat.eveonline.database.retrieval.sde.SDEDumper;
import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.database.yaml.DatabaseFile;

public class LoadLocations {

	public static void main(String[] args) {

		SDEData sde = new SDEData();
		DatabaseFile db = new DatabaseFile();

		SDEDumper.loadLocations(sde, db);
	}

}
