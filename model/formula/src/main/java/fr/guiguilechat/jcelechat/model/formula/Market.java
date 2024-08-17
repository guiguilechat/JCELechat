package fr.guiguilechat.jcelechat.model.formula;

import fr.guiguilechat.jcelechat.model.formula.market.BrokerFee;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerRelist;
import fr.guiguilechat.jcelechat.model.formula.market.Tax;

public class Market {

	public static void main(String[] args) {
		System.out.println("minimum : market tax is " + Tax.MINIMUM
		    + " , broker fee is " + BrokerFee.MINIMUM
		    + " , relist fee is " + BrokerRelist.MINIMUM);

	}

}
