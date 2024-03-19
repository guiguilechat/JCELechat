package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;

/**
 * strategy to source and sell items
 */
public enum SourceType {
	/** materials from BO, product to SO */
	boso {

		@Override
		public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct,
				boolean useProduct) {
			return productUnitPrice * quantity * (100 + (useProduct ? 0 : -taxPct - brkPct)) / 100;
		}

		@Override
		public double materialCost(long quantity, double taxPct, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos, boolean produceMaterial) {
			return (bos == null || bos.isEmpty() ? 0.01 : bos.get(0).getOrder().price) * quantity
					* (100 + (produceMaterial ? -taxPct : brokerPct)) / 100;
		}

		@Override
		public double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos, List<RegionLine> sos) {
			return sos == null || sos.isEmpty() ? Double.POSITIVE_INFINITY : sos.get(0).getOrder().price;
		}

	},
	/** material from SO, product to BO, using intermediate offer */
	sobo {

		@Override
		public double materialCost(long quantity, double taxPct, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos, boolean produceMaterial) {
			long remain = quantity;
			if (sos != null) {
				double ret = 0.0;
				for (RegionLine so : sos) {
					int remove = (int) Math.min(remain, so.getOrder().volume_remain);
					ret += so.getOrder().price * remove;
					remain -= remove;
					if (remain == 0) {
						return ret * (100 + (produceMaterial ? -taxPct - brokerPct : 0)) / 100;
					}
				}
			}
			return Double.POSITIVE_INFINITY;
		}

		@Override
		public double productUnitPrice(int typeId, long quantity, List<RegionLine> bos, List<RegionLine> sos) {
			long remain = quantity;
			double ret = 0.0;
			if (bos != null) {
				for (RegionLine bo : bos) {
					int remove = (int) Math.min(remain, bo.getOrder().volume_remain);
					ret += bo.getOrder().price * remove;
					remain -= remove;
					if (remain == 0) {
						return ret / quantity;
					}
				}
			}
			return ret / quantity;
		}

	},
	/** materials from SO, product to BO , using mass price each time */
	sobodump {

		@Override
		public double materialCost(long quantity, double taxPct, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos, boolean produceMaterial) {
			long remain = quantity;
			if (sos != null) {
				for (RegionLine so : sos) {
					int remove = (int) Math.min(remain, so.getOrder().volume_remain);
					remain -= remove;
					if (remain == 0) {
						return so.getOrder().price * quantity * (100 + (produceMaterial ? -taxPct - brokerPct : 0)) / 100;
					}
				}
			}
			return Double.POSITIVE_INFINITY;
		}

		@Override
		public double productUnitPrice(int typeId, long quantity, List<RegionLine> bos, List<RegionLine> sos) {
			long remain = quantity;
			if (bos != null) {
				for (RegionLine bo : bos) {
					int remove = (int) Math.min(remain, bo.getOrder().volume_remain);
					remain -= remove;
					if (remain == 0) {
						return bo.getOrder().price;
					}
				}
			}
			return 0.0;
		}

	};

	public abstract double materialCost(long quantity, double taxPct, double brokerPct, List<RegionLine> bos,
			List<RegionLine> sos, boolean produceMaterial);

	public double materialCost(Map<Integer, Long> requiredMats, double taxPct, double brokerPct, boolean produceMaterial,
			Map<Integer, List<RegionLine>> bos, Map<Integer, List<RegionLine>> sos) {
		return requiredMats.entrySet().stream()
				.mapToDouble(k -> materialCost(k.getValue(), taxPct, brokerPct, bos.get(k.getKey()), sos.get(k.getKey()),
						produceMaterial))
				.sum();
	}

	public abstract double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos,
			List<RegionLine> sos);

	/**
	 * actual gain for the product. Default is by using BO, so selling it directly
	 * to BO, or placing a BO when using that product
	 *
	 * @param productUnitPrice price deduced by
	 *                         {@link #productUnitPrice(int, long, List, List)}
	 * @param quantity         number produced
	 * @param taxPct           percent of sale that goes to tax
	 * @param brkPct           percent of sale that goes to broker fee
	 * @param useProduct       when true, the price of the product is the price to
	 *                         BUY it at given price
	 */
	public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct,
			boolean useProduct) {
		return productUnitPrice * quantity * (100 + (useProduct ? taxPct + brkPct : -taxPct)) / 100;
	}

	public double productIncome(Map<Integer, Long> products, double taxpct, double brokerPct, boolean useProduct,
			Map<Integer, List<RegionLine>> bos, Map<Integer, List<RegionLine>> sos) {
		double sum = 0.0;
		for (Entry<Integer, Long> e : products.entrySet()) {
			int typeId = e.getKey();
			double up = productUnitPrice(typeId, e.getValue(), bos.get(typeId), sos.get(typeId));
			sum += productIncome(up, e.getValue(), taxpct, brokerPct, useProduct);
		}
		return sum;
	}
}