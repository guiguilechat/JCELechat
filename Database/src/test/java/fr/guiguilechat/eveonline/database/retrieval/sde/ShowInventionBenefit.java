package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.Eblueprints.BPActivities.Material;

public class ShowInventionBenefit {

	public static void main(String[] args) {
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.load();
		int skill1 = 3, skill2 = 3, encryptionSkill = 3;
		double multProb = (skill1 + skill2) * 1.0 / 30 + encryptionSkill * 1.0 / 40 + 1;
		for (Eblueprints ebp : bps.values()) {
			for (Material e : ebp.activities.invention.products) {

			}
		}

	}

}
