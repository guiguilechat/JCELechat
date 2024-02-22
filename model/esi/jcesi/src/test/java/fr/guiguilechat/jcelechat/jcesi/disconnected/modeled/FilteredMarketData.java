package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.IPricing;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;

public class FilteredMarketData {

	public static void main(String[] args) {
		int theforge = 10000002;
		int jita = 30000142;
		int scorchbomb = 27916;
		RegionalMarket basemarket = ESIAccess.INSTANCE.markets.getMarket(theforge);
		IPricing jitamarket = basemarket.filter(jita, 0, false);
		System.err
				.println(
						"sell orders in theforge" + basemarket.getMarketOrders(scorchbomb, false).getFilteredOrders().size().get());
		System.err.println(
				"sell orders in jita" + jitamarket.getMarketOrders(scorchbomb, false).getFilteredOrders().size().get());
	}

}
