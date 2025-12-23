package fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools.MaterialSourcing;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools.ProductValuator;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerFee;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerRelist;
import fr.guiguilechat.jcelechat.model.formula.market.Tax;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * parameters to evaluate the isk/LP of an offer
 */
@Getter
@Setter
@ToString
public class LPEvalParams {
	private double bpcost = 1000000.0;
	private double brpct = (BrokerFee.MINIMUM + 5 * BrokerRelist.MINIMUM) * 100;
	private long location = MarketLineService.JITAIV_ID;
	private int lp = 100000;
	private double margin = 5.0;
	private double marginPerHour = 0.5;
	private MaterialSourcing materialSourcing = MaterialSourcing.BUY_SO_MASS;
	private ProductValuator productValuator = ProductValuator.SELL_BO_MASS;
	private double taxpct = Tax.MINIMUM * 100;
}