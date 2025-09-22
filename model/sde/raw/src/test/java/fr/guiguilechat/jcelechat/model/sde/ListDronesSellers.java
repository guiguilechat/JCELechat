package fr.guiguilechat.jcelechat.model.sde;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;

public class ListDronesSellers {

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
		EnpcCorporations.LOADER.load().entrySet().forEach(e -> {
			EnpcCorporations corp = e.getValue();
			Double dronePrice = corp.corporationTrades.get(3436);
			if (dronePrice != null) {
				System.err.println(e.getKey() + "\t" + corp.enName() + "\t" + dronePrice);
			}
		});
	}

}
