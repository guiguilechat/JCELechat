package fr.guiguilechat.jcelechat.model.sde;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;

public class ListExchangeRates {

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
		EnpcCorporations.LOADER.load().entrySet().forEach(e -> {
			EnpcCorporations corp = e.getValue();
			if (!corp.exchangeRates.isEmpty()) {
				System.err.println(""+e.getKey()+"\t"+corp.enName());
			}
		});

	}

}
