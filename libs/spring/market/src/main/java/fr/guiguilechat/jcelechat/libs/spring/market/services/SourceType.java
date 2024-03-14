package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;

/**
 * strategy to source and sell items
 */
public enum SourceType {
	/** buy using BO, sell using SO (long term) */
	boso {

		@Override
		public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
			return productUnitPrice * quantity * (100 - taxPct - brkPct) / 100;
		}

		@Override
		public double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos) {
			return (bos == null || bos.isEmpty() ? 0.01 : bos.get(0).getOrder().price) * (100 + brokerPct) / 100;
		}

		@Override
		public double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos, List<RegionLine> sos) {
			return sos == null || sos.isEmpty() ? Double.POSITIVE_INFINITY : sos.get(0).getOrder().price;
		}

	},
	/** buy from SO, sell to BO (direct), purchasing each intermediate offer */
	sobo {

		@Override
		public double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos) {
			long remain = quantity;
			if (sos != null) {
				double ret = 0.0;
				for (RegionLine so : sos) {
					int remove = (int) Math.min(remain, so.getOrder().volume_remain);
					ret += so.getOrder().price * remove;
					remain -= remove;
					if (remain == 0) {
						return ret;
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
	/** buy from SO, sell to BO (direct), using mass price each time */
	sobodump {

		@Override
		public double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
				List<RegionLine> sos) {
			long remain = quantity;
			if (sos != null) {
				for (RegionLine so : sos) {
					int remove = (int) Math.min(remain, so.getOrder().volume_remain);
					remain -= remove;
					if (remain == 0) {
						return so.getOrder().price * quantity;
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

	/** actual gain when selling the product */
	public double productIncome(double productUnitPrice, long quantity, double taxPct, double brkPct) {
		return productUnitPrice * quantity * (100 - taxPct) / 100;
	}

	public double materialCost(HashMap<Integer, Long> requiredMats, double brokerPct,
			Map<Integer, List<RegionLine>> bos, Map<Integer, List<RegionLine>> sos) {
		return requiredMats.entrySet().stream()
				.mapToDouble(k -> materialCost(k.getKey(), k.getValue(), brokerPct, bos.get(k.getKey()), sos.get(k.getKey())))
				.sum();
	}

	public abstract double materialCost(int key, long quantity, double brokerPct, List<RegionLine> bos,
			List<RegionLine> sos);

	public abstract double productUnitPrice(int typeId, long productQuantity, List<RegionLine> bos,
			List<RegionLine> sos);
}