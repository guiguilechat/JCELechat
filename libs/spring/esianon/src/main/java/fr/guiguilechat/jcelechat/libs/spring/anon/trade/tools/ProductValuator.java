package fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * how do we evaluate the value of a product ?
 */
@RequiredArgsConstructor
@Getter
public enum ProductValuator implements IMaterialValuator {

	BUY_SO_INDIVIDUAL(false, false, true, "buy from SO individual orders"),

	BUY_SO_MASS(false, true, true, "buy from SO mass order"),

	BUY_BO(true, false, true, "place buy order"),

	SELL_BO_INDIVIDUAL(true, false, false, "sell to BO individual orders"),

	SELL_BO_MASS(true, true, false, "sell to BO mass order"),

	SELL_SO(false, false, false, "place sell order");

	/**
	 * do we use BO or SO for base price ?
	 */
	private final boolean useBo;

	/**
	 * when true, and placing direct order, we only sell/buy at the worst
	 * corresponding order.
	 */
	private final boolean massPrice;

	/**
	 * when true, we consume the product. The value of product is therefore the
	 * cost of purchasing it
	 */
	private final boolean consuming;

	/**
	 * explanation of the strategy
	 */
	private final String explain;

	public double value(long productQuantity, double taxPct, double brokerPct,
			List<MarketLine> bos, List<MarketLine> sos) {
		if (useBo) {
			return consuming ? costBuyBo(productQuantity, brokerPct, bos)
					: costSellBo(productQuantity, taxPct, bos);
		} else {
			return consuming ? costBuySo(productQuantity, sos)
					: costSellSo(productQuantity, taxPct, brokerPct, sos);
		}
	}

	public double value(Map<Integer, Long> products, double taxpct, double brokerPct,
			Map<Integer, List<MarketLine>> bos, Map<Integer, List<MarketLine>> sos) {
		double sum = 0.0;
		for (Entry<Integer, Long> e : products.entrySet()) {
			int typeId = e.getKey();
			sum += value(e.getValue(), taxpct, brokerPct, bos.get(typeId), sos.get(typeId));
		}
		return sum;
	}

	public double unitPrice(long quantity, List<MarketLine> bos, List<MarketLine> sos) {
		if (useBo) {
			return consuming ? uPriceBuyBo(quantity, bos) : uPriceSellBo(quantity, bos);
		} else {
			return consuming ? uPriceBuySo(quantity, sos) : uPriceSellSo(quantity, sos);
		}
	}

	/**
	 * @param quantity        number of items we want to sell
	 * @param itemVolumePrice item volume × volumic price
	 * @return hauling cost of moving a batch of item. If we consume the product,
	 *           then its value is the cost we would pay to acquire it, and
	 *           therefore hauling cost is negative : consuming product means we
	 *           gain its hauling cost.
	 */
	public double haulingCost(long quantity, double itemVolumePrice) {
		return itemVolumePrice * (consuming ? -1 : 1) * quantity;
	}

}
