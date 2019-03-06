package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;

public class MarketsMain {

	public static void main(String[] args) throws InterruptedException {

		int region = 10000002;
		ESIAccess acc = ESIAccess.INSTANCE;
		RegionalMarket market = acc.markets.getMarket(region);
		System.err.println("SO for " + 3200 + " of item " + 25591 + " is " + market.getSO(25591, 3200).get());
		System.err.println("BO for " + 3200 + " of item " + 25591 + " is " + market.getBO(25591, 3200).get());
		System.err.println("best volumes desc" + market.getHistory(25591).getSortedVolumes().copy());
		System.err.println("best 2 days offset 0 : " + market.getHistory(25591).getBestVolume(2, 0).get());
		System.err.println("best 1 days offset 0 : " + market.getHistory(25591).getBestVolume(1, 0).get());
		System.err.println("best 2 days offset 2 : " + market.getHistory(25591).getBestVolume(2, 2).get());

		while (true) {
			Thread.sleep(1000);
		}
	}

}
