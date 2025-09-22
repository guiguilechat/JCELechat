package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Egroups;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;

public class ShowModulesPublishedNoMarket {

	public static void main(String[] args) {
		for (Entry<Integer, Etypes> e : Etypes.LOADER.load().entrySet()) {
			Etypes t = e.getValue();
			if (t.published) {
				Egroups group = Egroups.LOADER.load().get(t.groupID);
				if (group.categoryID == 7 && t.marketGroupID == 0) {
					System.err
							.println("type " + t.enName() + " (" + e.getKey() + ") group " + group.enName() + "(" + t.groupID
									+ ") is published Module(7) but no market group");
				}

			}
		}
	}

}
