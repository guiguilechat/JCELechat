package fr.guiguilechat.jcelechat.model.formula.market;

public class BrokerFee {

	/** base multiplier of the price to remove as broker fee */
	public static final double BASE = 3.0 * 0.01;

	/**
	 * raw reduction (base 1) of the broker fee multiplier per broker relation level
	 * (additive).
	 * If base is 20% and this is 1%, at level 5 broker relation the broker fee
	 * multiplier would become 20-5 = 15%.
	 */
	protected static final double BROKERRELATIONS_REDUCE = 0.3 * 0.01;

	protected static final double FACTION_REDUCE = 0.03 * 0.01;

	protected static final double CORP_REDUCE = 0.02 * 0.010;

	public static double multiplier(int brokerrelation_level, float faction_base_standing,
	    float corporation_base_standing) {
		return BASE
		    - brokerrelation_level * BROKERRELATIONS_REDUCE
		    - faction_base_standing * FACTION_REDUCE
		    - corporation_base_standing * CORP_REDUCE;
	}

	/**
	 * minimum broker multiplier of a price to remove from it
	 */
	public static final double MINIMUM = multiplier(5, 10, 10);

	/** broker fee paid to place an order at given buy or sell price */
	public static double cost(double sell_price, int brokerrelation_level,
	    float faction_base_standing,
	    float corporation_base_standing) {
		return sell_price * multiplier(brokerrelation_level, faction_base_standing, corporation_base_standing);
	}

}