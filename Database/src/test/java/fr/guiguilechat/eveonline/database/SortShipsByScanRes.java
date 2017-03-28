package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Collections;

import fr.guiguilechat.eveonline.database.elements.Hull;

public class SortShipsByScanRes {

	public static void main(String[] args) {
		Database db = Parser.getSDEDB();
		ArrayList<Hull> hulls = new ArrayList<>(db.hulls.values());
		Collections.sort(hulls, (h1, h2) -> h2.attributes.scanRes - h1.attributes.scanRes);
		for (Hull h : hulls) {
			if (h.attributes.mediumSlots > 2 && h.attributes.scanRes > 0) {
				System.out.println(h.name + " : " + h.attributes.scanRes);
			}
		}
	}

}
