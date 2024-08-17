package fr.guiguilechat.jcelechat.model.formula.market;

public class BrokerRelist {

	public static final double BASE = 50.0 * 0.01;

	protected static final double ADVBROKERRELATIONS_REDUCE = 6.0 * 0.01;

	public static final double MINIMUM = BrokerFee.MINIMUM
	    * (BASE - ADVBROKERRELATIONS_REDUCE * 5);

}