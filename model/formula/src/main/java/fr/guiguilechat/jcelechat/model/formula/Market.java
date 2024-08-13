package fr.guiguilechat.jcelechat.model.formula;

public class Market {

	public static final double MARKET_TAX_BASE = 4.5 * 0.01;

	protected static final double MARKET_TAX_ACCOUNTING_REDUCE = 11 * 0.01;

	public static final double MARKET_TAX_MINIMUM = MARKET_TAX_BASE * (1.0 - MARKET_TAX_ACCOUNTING_REDUCE * 5);


	public static final double BROKER_FEE_BASE = 3.0 * 0.01;

	protected static final double BROKER_FEE_BROKERRELATIONS_REDUCE = 0.3 * 0.01;

	protected static final double BROKER_FEE_FACTION_REDUCE = 0.03 * 0.01;

	protected static final double BROKER_FEE_CORP_REDUCE = 0.02 * 0.010;

	public static final double BROKER_FEE_MINIMUM = BROKER_FEE_BASE
	    - 5 * BROKER_FEE_BROKERRELATIONS_REDUCE
	    - 10 * BROKER_FEE_FACTION_REDUCE
	    - 10 * BROKER_FEE_CORP_REDUCE;


	public static final double BROKER_RELIST_BASE = 50.0 * 0.01;

	protected static final double BROKER_RELIST_ADVBROKERRELATIONS_REDUCE = 6.0 * 0.01;

	public static final double BROKER_RELIST_MINIMUM = BROKER_FEE_MINIMUM
	    * (BROKER_RELIST_BASE - BROKER_RELIST_ADVBROKERRELATIONS_REDUCE * 5);

	public static void main(String[] args) {
		System.out.println("minimum : market tax is " + MARKET_TAX_MINIMUM
		    + " , broker fee is " + BROKER_FEE_MINIMUM
		    + " , relist fee is " + BROKER_RELIST_MINIMUM);

	}

}
