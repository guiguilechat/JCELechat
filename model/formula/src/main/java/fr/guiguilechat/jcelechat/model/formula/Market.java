package fr.guiguilechat.jcelechat.model.formula;

public class Market {

	public static class Tax {

		public static final double BASE = 4.5 * 0.01;

		protected static final double ACCOUNTING_REDUCE = 11 * 0.01;

		public static double multiplier(int accounting_level) {
			return BASE * (1.0 - ACCOUNTING_REDUCE * accounting_level);
		}

		public static final double MINIMUM = multiplier(5);

		/** value removed from the sale gain when concluding a sale at given price */
		public static double cost(double sell_price, int accounting_level) {
			return sell_price * multiplier(accounting_level);
		}

	}

	public static final Tax tax = new Tax();

	////

	public static class BrokerFee {

		public static final double BASE = 3.0 * 0.01;

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

		public static final double MINIMUM = multiplier(5, 10, 10);

		/** price to place an order at given buy or sell price */
		public static double cost(double sell_price, int brokerrelation_level,
		    float faction_base_standing,
		    float corporation_base_standing) {
			return sell_price * multiplier(brokerrelation_level, faction_base_standing, corporation_base_standing);
		}

	}

	public static final BrokerFee brokerFee = new BrokerFee();

	////

	public static class BrokerRelist {

		public static final double BASE = 50.0 * 0.01;

		protected static final double ADVBROKERRELATIONS_REDUCE = 6.0 * 0.01;

		public static final double MINIMUM = BrokerFee.MINIMUM
		    * (BASE - ADVBROKERRELATIONS_REDUCE * 5);

	}

	public static final BrokerRelist brokerRelist = new BrokerRelist();

	public static void main(String[] args) {
		System.out.println("minimum : market tax is " + Tax.MINIMUM
		    + " , broker fee is " + BrokerFee.MINIMUM
		    + " , relist fee is " + BrokerRelist.MINIMUM);

	}

}
