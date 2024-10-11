package fr.guiguilechat.jcelechat.model.formula.market;

import java.util.Map;

public class Tax {

	/** base multiplier of the price to remove as tax */
	public static final double BASE = 4.5 * 0.01;

	/**
	 * accounting raw reduction from price multiplier, per level
	 */
	protected static final double ACCOUNTING_REDUCE = 11 * 0.01;

	/**
	 * multiplier of sell price to remove from it as tax, based on accounting level.
	 */
	public static double multiplier(int accounting_level) {
		return BASE * (1.0 - ACCOUNTING_REDUCE * accounting_level);
	}

	public static double multiplier(Map<Integer, Integer> skillIdToLevel) {
		return multiplier(skillIdToLevel.getOrDefault(16622, 0));
	}

	/**
	 * minimum multiplier of sell price to remove from it.
	 */
	public static final double MINIMUM = multiplier(5);

	/** value removed from the sale gain when concluding a sale at given price */
	public static double cost(double sell_price, int accounting_level) {
		return sell_price * multiplier(accounting_level);
	}

}