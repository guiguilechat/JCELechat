package fr.guiguilechat.jcelechat.sde.items;

import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;

public class ShowUnpublishedOnMarket {

	public static void main(String[] args) {
		for (Entry<Integer, Etypes> e : Etypes.load().entrySet()) {
			Etypes t = e.getValue();
			if (!t.published && t.marketGroupID != 0) {
				System.err
				.println("type " + t.enName() + " (" + e.getKey() + ") is unpublished and market group " + t.marketGroupID);
			}
		}
	}

}
