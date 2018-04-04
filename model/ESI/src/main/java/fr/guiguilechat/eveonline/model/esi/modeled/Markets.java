package fr.guiguilechat.eveonline.model.esi.modeled;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.Swagger.order_type;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Markets {

	public class RegionalMarket {

		public final int regionID;

		public RegionalMarket(int regionID) {
			this.regionID = regionID;
		}

		public class CachedOrdersList {

			public final int typeID;
			public final boolean buy;

			private long cacheEnd = 0;

			private ObservableList<R_get_markets_region_id_orders> orders = FXCollections.observableArrayList();

			public CachedOrdersList(int order_type, boolean buy) {
				typeID = order_type;
				this.buy = buy;
			}

			private synchronized void fetchPage() {
				if (System.currentTimeMillis() <= cacheEnd) {
					return;
				}
				List<R_get_markets_region_id_orders> neworders = ESIConnection
						.loadPages((p, h) -> esiConnection.raw.get_markets_region_id_orders(buy ? order_type.buy : order_type.sell,
								p, regionID, typeID, h), l -> cacheEnd = l)
						.filter(o -> o.min_volume == 1).collect(Collectors.toList());
				if (buy) {
					Collections.sort(neworders, (o1, o2) -> (int) Math.signum(o2.price - o1.price));
				} else {
					Collections.sort(neworders, (o1, o2) -> (int) Math.signum(o1.price - o2.price));
				}
				orders.setAll(neworders);
			}

			public ObservableList<R_get_markets_region_id_orders> listOrders() {
				fetchPage();
				return orders;
			}

			private HashMap<Integer, DoubleBinding> qttyPrice = new HashMap<>();

			/**
			 * get a double value representing the total sell orders for qtty given
			 * qtty. missing orders are represented with +infinity.
			 *
			 * @param qtty
			 * @return
			 */
			public ObservableDoubleValue getPrice(int qtty) {
				DoubleBinding ret = qttyPrice.get(qtty);
				if (ret == null) {
					synchronized (qttyPrice) {
						ret = qttyPrice.get(qtty);
						if (ret == null) {
							ret = new DoubleBinding() {
								@Override
								protected double computeValue() {
									double sumCost = 0;
									int qttyremain = qtty;
									for (R_get_markets_region_id_orders r : listOrders()) {
										int qtty = Math.min(qttyremain, r.volume_remain);
										sumCost += qtty * r.price;
										qttyremain -= qtty;
										if (qttyremain == 0) {
											return sumCost;
										}
									}
									return buy ? sumCost : Double.POSITIVE_INFINITY;
								}

								{
									bind(listOrders());
								}
							};
							qttyPrice.put(qtty, ret);
						}
					}
				}
				listOrders();
				return ret;
			}

		}

		// typeid-> cached orders
		private Map<Integer, CachedOrdersList> cachedBuyOrders = new HashMap<>();
		private Map<Integer, CachedOrdersList> cachedSellOrders = new HashMap<>();

		public CachedOrdersList getMarketOrders(int typeID, boolean buy) {
			Map<Integer, CachedOrdersList> cache = buy ? cachedBuyOrders : cachedSellOrders;
			CachedOrdersList ret = cache.get(typeID);
			if (ret == null) {
				synchronized (cache) {
					ret = cache.get(typeID);
					if (ret == null) {
						ret = new CachedOrdersList(typeID, buy);
						cache.put(typeID, ret);
					}
				}
			}
			return ret;
		}

		public int nbSellOrders() {
			return cachedSellOrders.size();
		}

		public int nbBuyOrders() {
			return cachedBuyOrders.size();
		}

		public double getSO(int typeID, int qtty) {
			return getMarketOrders(typeID, false).getPrice(qtty).doubleValue();
		}

		public double getBO(int typeID, int qtty) {
			return getMarketOrders(typeID, true).getPrice(qtty).doubleValue();
		}

	}

	protected final ESIAccount esiConnection;

	public Markets(ESIAccount esiConnection) {
		this.esiConnection = esiConnection;
	}

	// regionid-> regional market
	private HashMap<Integer, RegionalMarket> regionMarkets = new HashMap<>();

	/**
	 * get the regional market for given region ID.
	 *
	 * @param regionID
	 * @return
	 */
	public RegionalMarket getMarket(int regionID) {
		RegionalMarket rm = regionMarkets.get(regionID);
		if (rm == null) {
			synchronized (regionMarkets) {
				if (regionMarkets.get(regionID) == null) {
					rm = new RegionalMarket(regionID);
					regionMarkets.put(regionID, rm);
				} else {
					rm = regionMarkets.get(regionID);
				}
			}
		}
		return rm;
	}

	public ObservableList<R_get_markets_region_id_orders> getOrders(boolean buy, int regionID, int typeID) {
		return getMarket(regionID).getMarketOrders(typeID, buy).listOrders();
	}

	//
	// prices : adjusted and average
	//

	private static HashMap<Integer, Double> cachedAverage = null;
	private static HashMap<Integer, Double> cachedAdjusted = null;

	private static void dl() {
		if (cachedAdjusted == null || cachedAverage == null) {
			synchronized (Markets.class) {
				if (cachedAdjusted != null && cachedAverage != null) {
					return;
				}
				HashMap<Integer, Double> fcachedAverage = new HashMap<>();
				HashMap<Integer, Double> fcachedAdjusted = new HashMap<>();
				for (R_get_markets_prices p : ESIAccount.DISCONNECTED.raw.get_markets_prices(null)) {
					int id = p.type_id;
					fcachedAverage.put(id, p.average_price);
					fcachedAdjusted.put(id, p.adjusted_price);
				}
				cachedAverage = fcachedAverage;
				cachedAdjusted = fcachedAdjusted;
			}
		}
	}

	public double getAdjusted(int itemId) {
		dl();
		return cachedAdjusted.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

	public double getAverage(int itemId) {
		dl();
		return cachedAverage.getOrDefault(itemId, Double.POSITIVE_INFINITY);
	}

}
