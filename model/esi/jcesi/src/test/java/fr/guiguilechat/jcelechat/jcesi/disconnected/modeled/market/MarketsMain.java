package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;

public class MarketsMain {

	public static void main(String[] args) throws InterruptedException {

		int region = 10000002;
		ESIAccess acc = ESIAccess.INSTANCE;
		RegionalMarket market = acc.markets.getMarket(region);
		// 25591 is contaminated lorentz fluid.
		System.err.println("SO for " + 3200 + " of item " + 25591 + " is " + market.getSO(25591, 3200).get());
		System.err.println("BO for " + 3200 + " of item " + 25591 + " is " + market.getBO(25591, 3200).get());

		while (true) {
			Thread.sleep(1000);
		}
	}

}
