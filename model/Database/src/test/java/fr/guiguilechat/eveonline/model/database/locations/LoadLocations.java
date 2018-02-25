package fr.guiguilechat.eveonline.model.database.locations;

import fr.guiguilechat.eveonline.model.database.retrieval.sde.SDEDumper;
import fr.guiguilechat.eveonline.model.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.model.database.yaml.DatabaseFile;

public class LoadLocations {

	public static void main(String[] args) {

		SDEData sde = new SDEData();
		DatabaseFile db = new DatabaseFile();

		SDEDumper.loadKSpace(sde, db);
		SDEDumper.loadWSpace(sde, db);
	}

}
