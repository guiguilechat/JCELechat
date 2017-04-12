package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Collections;

import fr.guiguilechat.eveonline.database.yaml.Hull;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class SortShipsByScanRes {

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();
		ArrayList<Hull> hulls = new ArrayList<>(db.getHulls().values());
		Collections.sort(hulls, (h1, h2) -> h2.attributes.scanRes - h1.attributes.scanRes);
		for (Hull h : hulls) {
			if (h.attributes.mediumSlots > 2 && h.attributes.scanRes > 0) {
				System.out.println(h.name + " : " + h.attributes.scanRes);
			}
		}
	}

}
