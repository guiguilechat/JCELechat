package fr.guiguilechat.jcelechat.model.sde.load.fsd;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.Material;

public class BlueprintsWithInventionGT1 {

	public static void main(String[] args) {
		for (Eblueprints e : Eblueprints.LOADER.load().values()) {
			if (e.activities.invention != null) {
				Activity invention = e.activities.invention;
				if (invention.products != null) {
					for (Material m : invention.products) {
						if (m.quantity != 1) {
							System.out.println("bp2 " + m.typeID + " has qtty " + m.quantity);
						}
					}
				}
			}
		}
	}

}
