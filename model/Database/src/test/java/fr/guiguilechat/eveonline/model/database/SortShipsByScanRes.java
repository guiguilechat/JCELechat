package fr.guiguilechat.eveonline.model.database;

import java.util.ArrayList;
import java.util.Collections;

import fr.guiguilechat.eveonline.model.database.yaml.Hull;
import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;

public class SortShipsByScanRes {

	public static void main(String[] args) {
		YamlDatabase db = new YamlDatabase();
		ArrayList<Hull> hulls = new ArrayList<>(db.getHulls().values());
		Collections.sort(hulls, (h1, h2) -> h2.attributes.velocity - h1.attributes.velocity);
		for (Hull h : hulls) {
			if (h.attributes.mediumSlots > 0) {
				System.out.println(h.name + " : " + h.attributes.velocity);
			}
		}
	}

}
