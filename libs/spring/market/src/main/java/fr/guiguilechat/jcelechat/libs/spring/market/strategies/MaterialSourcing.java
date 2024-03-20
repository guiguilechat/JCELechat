package fr.guiguilechat.jcelechat.libs.spring.market.strategies;

import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * How do we source the material ?
 */
@RequiredArgsConstructor
@Getter
public enum MaterialSourcing implements IMarketInteraction {

	BUY_SO_INDIVIDUAL(false, false, false, "buy from SO individual orders"),

	BUY_SO_MASS(false, true, false, "buy from SO mass order"),

	BUY_BO(true, false, false, "place buy order"),

	SELL_BO_INDIVIDUAL(true, false, true, "sell to BO individual orders"),

	SELL_BO_MASS(true, true, true, "sell to BO mass order"),

	SELL_SO(false, false, true, "place sell order");

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
	 * when true, we produce the materials. The cost of materials is therefore the
	 * opportunity cost of not selling them
	 */
	private final boolean producing;

	/**
	 * explanation of the strategy
	 */
	private final String explain;

	public double cost(long quantity, double taxPct, double brokerPct, List<RegionLine> bos,
			List<RegionLine> sos) {
		if (producing) {
			return useBo ? costSellBo(quantity, taxPct, bos) : costSellSo(quantity, taxPct, brokerPct, sos);
		} else {
			return useBo ? costBuyBo(quantity, brokerPct, bos) : costBuySo(quantity, sos);
		}
	}

	/**
	 * cost of a batch of items
	 *
	 * @param requiredMats
	 * @param taxPct
	 * @param brokerPct
	 * @param itemVolumePrice
	 * @param bos
	 * @param sos
	 * @return
	 */
	public double cost(Map<Integer, Long> requiredMats, double taxPct, double brokerPct,
			Map<Integer, List<RegionLine>> bos, Map<Integer, List<RegionLine>> sos) {
		return requiredMats.entrySet().stream()
				.mapToDouble(
						k -> cost(k.getValue(), taxPct, brokerPct, bos.get(k.getKey()), sos.get(k.getKey())))
				.sum();
	}

	/**
	 * @param quantity        number of items we want to sell
	 * @param itemVolumePrice item volume × volumic price
	 * @return hauling cost of moving a batch of item. If we produce it, then the
	 *           cost is opportunity cost, and therefore hauling cost is negative :
	 *           using materials means we gain the hauling cost.
	 */
	public double haulingCost(long quantity, double itemVolumePrice) {
		return itemVolumePrice * (producing ? -1 : 1) * quantity;
	}

}
