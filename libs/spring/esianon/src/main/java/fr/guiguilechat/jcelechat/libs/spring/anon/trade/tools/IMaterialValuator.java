package fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;

/**
 * common interface for material and product pricing strategies. Basically
 * tools.
 */
public interface IMaterialValuator {

	/**
	 * when true, and placing direct order, we only sell/buy at the worst
	 * corresponding order.
	 */
	public boolean isMassPrice();

	/**
	 * @param quantity        number we want to purchase
	 * @param sos             sell orders to use, sorted by price increasing
	 * @return cost to purchase quantity from SOs
	 */
	public default double costBuySo(long quantity, List<MarketLine> sos) {
		long remain = quantity;
		double cumulated = 0.0;
		if (sos != null) {
			for (MarketLine so : sos) {
				int remove = (int) Math.min(remain, so.getVolumeRemain());
				cumulated += so.getPrice() * remove;
				remain -= remove;
				if (remain == 0) {
					return isMassPrice() ? so.getPrice() * quantity : cumulated;
				}
			}
		}
		return Double.POSITIVE_INFINITY;
	}

	public default double uPriceBuySo(long quantity, List<MarketLine> sos) {
		long remain = quantity;
		double cumulated = 0.0;
		if (sos != null) {
			for (MarketLine so : sos) {
				int remove = (int) Math.min(remain, so.getVolumeRemain());
				cumulated += so.getPrice() * remove;
				remain -= remove;
				if (remain == 0) {
					return isMassPrice() ? so.getPrice() : cumulated / quantity;
				}
			}
		}
		return Double.POSITIVE_INFINITY;
	}

	/**
	 * @param quantity        number we want to purchase
	 * @param itemVolumePrice price to move the item, from its unitary volume
	 * @param brokerPct       broker % of order price.
	 * @param bos             buy orders to fill, sorted by price decreasing
	 * @return cost to purchase quantity, placing BOs
	 */
	public default double costBuyBo(long quantity, double brokerPct, List<MarketLine> bos) {
		double unitPrice = 0.01;
		if (bos != null && !bos.isEmpty()) {
			unitPrice = bos.get(0).getPrice();
		}
		return unitPrice * (100 + brokerPct) / 100 * quantity;
	}

	public default double uPriceBuyBo(long quantity, List<MarketLine> bos) {
		double unitPrice = 0.01;
		if (bos != null && !bos.isEmpty()) {
			unitPrice = bos.get(0).getPrice();
		}
		return unitPrice;
	}

	/**
	 * @param quantity        number we want to sell
	 * @param itemVolumePrice price to move the item, from its unitary volume
	 * @param taxPct          sale tax % of order price
	 * @param bos             buy orders to fill, sorted by price decreasing
	 * @return value we would get from selling quantity directly to BOs
	 */
	public default double costSellBo(long quantity, double taxPct, List<MarketLine> bos) {
		long remain = quantity;
		double cumulated = 0.0;
		if (bos != null) {
			for (MarketLine bo : bos) {
				int remove = (int) Math.min(remain, bo.getVolumeRemain());
				cumulated += bo.getPrice() * remove;
				remain -= remove;
				if (remain == 0) {
					return (isMassPrice() ? bo.getPrice() * quantity : cumulated) * (100 - taxPct) / 100;
				}
			}
		}
		return isMassPrice() ? 0 : cumulated;
	}

	public default double uPriceSellBo(long quantity, List<MarketLine> bos) {
		long remain = quantity;
		double cumulated = 0.0;
		if (bos != null) {
			for (MarketLine bo : bos) {
				int remove = (int) Math.min(remain, bo.getVolumeRemain());
				cumulated += bo.getPrice() * remove;
				remain -= remove;
				if (remain == 0) {
					return isMassPrice() ? bo.getPrice() : cumulated / quantity;
				}
			}
		}
		return isMassPrice() ? 0 : cumulated / quantity;
	}

	/**
	 * @param quantity        number we want to sell
	 * @param itemVolumePrice price to move the item, from its unitary volume
	 * @param taxPct          sale tax % of order price
	 * @param brokerPct       broker % of order price.
	 * @param sos             sell orders to use, sorted by price increasing
	 * @return value we would get from selling quantity using long term SO orders
	 */
	public default double costSellSo(long quantity, double taxPct, double brokerPct,
			List<MarketLine> sos) {
		double unitPrice = Double.POSITIVE_INFINITY;
		if (sos != null && !sos.isEmpty()) {
			unitPrice = sos.get(0).getPrice();
		}
		return unitPrice * (100 - taxPct - brokerPct) / 100 * quantity;
	}

	public default double uPriceSellSo(long quantity, List<MarketLine> sos) {
		double unitPrice = Double.POSITIVE_INFINITY;
		if (sos != null && !sos.isEmpty()) {
			unitPrice = sos.get(0).getPrice();
		}
		return unitPrice;
	}

}
