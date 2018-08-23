package fr.guiguilechat.jcelechat.model.jcesi.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;

public class MarketsMain {

	public static void main(String[] args) throws InterruptedException {

		int region = 10000002;
		ESIAccess acc = ESIAccess.INSTANCE;
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.all, p, region, null, h),
		// s -> {
		// });
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.buy, p, region, null, h),
		// s -> {
		// });
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.sell, p, region, null,
		// h), s -> {
		// });
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.all, p, region, 34, h), s
		// -> {
		// });
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.buy, p, region, 34, h), s
		// -> {
		// });
		// acc.addFetchCache((p, h) ->
		// acc.raw.get_markets_region_id_orders(order_type.sell, p, region, 34, h),
		// s -> {
		// });
		System.err.println(acc.markets.getMarket(region).getSO(25591, 3200));
		System.err.println(acc.markets.getMarket(region).getBO(25591, 3200));

		while (true) {
			Thread.sleep(1000);
		}
	}

}
