package fr.guiguilechat.jcelechat.model.sde;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;

public class SdeFetchMain {

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
	}

}
