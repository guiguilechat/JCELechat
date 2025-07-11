package fr.guiguilechat.jcelechat.model.formula;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import fr.guiguilechat.jcelechat.model.formula.market.BrokerFee;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerRelist;
import fr.guiguilechat.jcelechat.model.formula.market.Tax;

public class Market {

	public static void main(String[] args) {
		System.out.println("minimum : market tax is " + Tax.MINIMUM
		    + " , broker fee is " + BrokerFee.MINIMUM
		    + " , relist fee is " + BrokerRelist.MINIMUM);

	}
	//
	// utilities for pricing
	//

	private static final int MAX_SIGNIFICATIVE_NUMBERS = 4;
	private static final int MAX_DECIMALS = 2;

	private final static MathContext UPPER_MC = new MathContext(MAX_SIGNIFICATIVE_NUMBERS, RoundingMode.HALF_DOWN);

	public static BigDecimal upperPrice(double price) {
		if (price == 0.0) {
			return new BigDecimal(0.02);
		}
		if (!Double.isFinite(price) || price == Double.MAX_VALUE) {
			// System.err.println("getting price non finite to increase ? " + price);
			return new BigDecimal(Double.MAX_VALUE);
		}
		BigDecimal bd = new BigDecimal(price, UPPER_MC);
		BigDecimal scaled = bd.setScale(MAX_DECIMALS, UPPER_MC.getRoundingMode());
		int exp = (int) Math.floor(Math.log10(price)) - MAX_SIGNIFICATIVE_NUMBERS + 1;
		if (exp < -MAX_DECIMALS) {
			exp = -MAX_DECIMALS;
		}
		double exponed = Math.pow(10, exp);
		if (!Double.isFinite(exponed)) {
			System.err.println("exponened price infinite ? param=" + price + " exponed=" + exponed + " exp=" + exp);
			exponed = Double.MAX_VALUE;
		}
		BigDecimal inc = new BigDecimal(exponed, new MathContext(1));
		BigDecimal ret = scaled.add(inc).round(UPPER_MC);
		return ret;
	}

	private final static MathContext LOWER_MC = new MathContext(MAX_SIGNIFICATIVE_NUMBERS, RoundingMode.HALF_DOWN);
	private final static MathContext LOWER_MC_DOWN = new MathContext(MAX_SIGNIFICATIVE_NUMBERS, RoundingMode.DOWN);

	public static BigDecimal lowerPrice(double price) {
		if(price<=0.0) {
			return new BigDecimal(0.0);
		}
		if (!Double.isFinite(price)) {
			throw new RuntimeException("requested lower price of " + price);
		}
		BigDecimal bd = new BigDecimal(price, LOWER_MC);
		BigDecimal scaled = bd.setScale(MAX_DECIMALS, LOWER_MC.getRoundingMode());
		int exp = (int) Math.floor(Math.log10(price)) - MAX_SIGNIFICATIVE_NUMBERS;
		if (exp < -MAX_DECIMALS) {
			exp = -MAX_DECIMALS;
		}
		try {
		BigDecimal inc = new BigDecimal(Math.pow(10, exp), new MathContext(1));

		BigDecimal ret = scaled.subtract(inc).round(LOWER_MC_DOWN);
		// System.err.println("base=" + price + " bd=" + bd + " scaled=" + scaled +
		// " exp=" + exp + " inc=" + inc + " ret=" + ret);
		return ret;
	} catch (NumberFormatException nfe) {
		throw new RuntimeException("while making lower of " + price + " with exp=" + exp + " ");
	}
	}

}
