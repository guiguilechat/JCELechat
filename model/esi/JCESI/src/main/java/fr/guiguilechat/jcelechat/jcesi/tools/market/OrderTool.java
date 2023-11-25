package fr.guiguilechat.jcelechat.jcesi.tools.market;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;

public class OrderTool {

	/**
	 * @return the max SYSTEM distance a buy order can be filled. -1 for same
	 *           station, 0 for same system, 1 for 1-range, max_int for region.
	 */
	public static int maxDist(R_get_markets_region_id_orders order) {
		return switch (order.range) {
			case station -> -1;
			case solarsystem -> 0;
			case _1 -> 1;
			case _2 -> 2;
			case _3 -> 3;
			case _4 -> 4;
			case _5 -> 5;
			case _10 -> 10;
			case _20 -> 20;
			case _30 -> 30;
			case _40 -> 40;
			case region -> Integer.MAX_VALUE;
			default -> throw new UnsupportedOperationException("range " + order.range + " not handled");
		};
	}

}
