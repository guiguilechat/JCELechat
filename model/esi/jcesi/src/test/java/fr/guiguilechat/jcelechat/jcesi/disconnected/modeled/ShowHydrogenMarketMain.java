package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import fr.guiguilechat.tools.FormatTools;

public class ShowHydrogenMarketMain {

	public static void main(String[] args) {
		int region_id = 10000002;
		int type_id = 4246;

		System.out.println("modeled");
		for (R_get_markets_region_id_orders order : ESIAccess.INSTANCE.markets.getMarket(region_id)
				.getMarketOrders(type_id, false)
				.getFilteredOrders().get()) {
			System.out.println(order.volume_remain + "@" + FormatTools.formatPrice(order.price));
		}
		System.out.println("raw");
		for (R_get_markets_region_id_orders order : ESIStatic.INSTANCE
				.get_markets_orders(order_type.sell, 1, region_id, type_id, null).getOK()) {
			System.out.println(order.volume_remain + "@" + FormatTools.formatPrice(order.price));
		}

	}

}
