package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;

public class ShowModulesPublishedNoMarket {

	public static void main(String[] args) {
		for (Entry<Integer, EtypeIDs> e : EtypeIDs.load().entrySet()) {
			EtypeIDs t = e.getValue();
			if (t.published) {
				EgroupIDs group = EgroupIDs.load().get(t.groupID);
				if (group.categoryID == 7 && t.marketGroupID == 0) {
					System.err
							.println("type " + t.enName() + " (" + e.getKey() + ") group " + group.enName() + "(" + t.groupID
									+ ") is published Module(7) but no market group");
				}

			}
		}
	}

}
