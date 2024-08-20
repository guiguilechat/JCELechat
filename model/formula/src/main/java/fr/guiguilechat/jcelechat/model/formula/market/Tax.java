package fr.guiguilechat.jcelechat.model.formula.market;

import java.util.Map;

public class Tax {

	public static final double BASE = 4.5 * 0.01;

	protected static final double ACCOUNTING_REDUCE = 11 * 0.01;

	public static double multiplier(int accounting_level) {
		return BASE * (1.0 - ACCOUNTING_REDUCE * accounting_level);
	}

	public static double multiplier(Map<Integer, Integer> skillIdToLevel) {
		return multiplier(skillIdToLevel.getOrDefault(16622, 0));
	}

	/**
	 * minimum multiplier of sell price.
	 */
	public static final double MINIMUM = multiplier(5);

	/** value removed from the sale gain when concluding a sale at given price */
	public static double cost(double sell_price, int accounting_level) {
		return sell_price * multiplier(accounting_level);
	}

}