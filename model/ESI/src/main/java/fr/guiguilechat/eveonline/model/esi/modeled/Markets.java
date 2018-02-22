package fr.guiguilechat.eveonline.model.esi.modeled;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets.RegionalMarket.CachedOrdersList;
import is.ccp.tech.esi.Swagger.order_type;
import is.ccp.tech.esi.responses.R_get_markets_prices;
import is.ccp.tech.esi.responses.R_get_markets_region_id_orders;
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

			private long cacheEnd = 0;

			private ObservableList<R_get_markets_region_id_orders> buyOrders = FXCollections.observableArrayList();
			private ObservableList<R_get_markets_region_id_orders> sellOrders = FXCollections.observableArrayList();

			public CachedOrdersList(int order_type) {
				typeID = order_type;
			}

			private synchronized void fetch() {
				if (System.currentTimeMillis() <= cacheEnd) {
					return;
				}
				ArrayList<R_get_markets_region_id_orders> nbo = new ArrayList<>();
				ArrayList<R_get_markets_region_id_orders> nso = new ArrayList<>();
				int maxPages = 1;
				cacheEnd = Long.MAX_VALUE;
				for (int page = 1; page <= maxPages; page++) {
					Map<String, List<String>> headers = new HashMap<>();
					R_get_markets_region_id_orders[] orders = esiConnection.raw.get_markets_region_id_orders(order_type.all, page,
							regionID, typeID, headers);
					if (page == 1) {
						String pages = headers.containsKey("x-pages") ? headers.get("x-pages").get(0) : null;
						maxPages = pages == null ? 1 : Integer.parseInt(pages);
					}
					for (R_get_markets_region_id_orders o : orders) {
						if (o.min_volume == 1) {
							if (o.is_buy_order) {
								nbo.add(o);
							} else {
								nso.add(o);
							}
						}
					}
					cacheEnd = Math.min(cacheEnd,
							System.currentTimeMillis()
							+ 1000 * ZonedDateTime.parse(headers.get("Expires").get(0), ESIAccount.formatter).toEpochSecond()
							- 1000 * ZonedDateTime.parse(headers.get("Date").get(0), ESIAccount.formatter).toEpochSecond());
				}
				Collections.sort(nbo, (o1, o2) -> (int) Math.signum(o2.price - o1.price));
				Collections.sort(nso, (o1, o2) -> (int) Math.signum(o1.price - o2.price));
				buyOrders.setAll(nbo);
				sellOrders.setAll(nso);
			}

			public ObservableList<R_get_markets_region_id_orders> getBuyOrders() {
				fetch();
				return buyOrders;
			}

			public ObservableList<R_get_markets_region_id_orders> getSellOrders() {
				fetch();
				return sellOrders;
			}

			private HashMap<Integer, ObservableDoubleValue> qttyBuyPrice = new HashMap<>();

			/**
			 * get a double value representing the total buy orders for qtty given
			 * qtty. missing orders are represented with 0.
			 *
			 * @param qtty
			 * @return
			 */
			public ObservableDoubleValue getBuyPrice(int qtty) {
				ObservableDoubleValue ret = qttyBuyPrice.get(qtty);
				if (ret == null) {
					synchronized (qttyBuyPrice) {
						if (qttyBuyPrice.get(qtty) == null) {
							ret = new DoubleBinding() {
								@Override
								protected double computeValue() {
									double sumCost = 0;
									int qttyremain = qtty;
									for (R_get_markets_region_id_orders r : getBuyOrders()) {
										int qtty = Math.min(qttyremain, r.volume_remain);
										sumCost += qtty * r.price;
										qttyremain -= qtty;
										if (qttyremain == 0) {
											return sumCost;
										}
									}
									return sumCost;
								}

								{
									bind(getBuyOrders());
								}
							};
							qttyBuyPrice.put(qtty, ret);
						} else {
							ret = qttyBuyPrice.get(qtty);
						}
					}
				}
				return ret;
			}



			private HashMap<Integer, ObservableDoubleValue> qttySellPrice = new HashMap<>();

			/**
			 * get a double value representing the total sell orders for qtty given
			 * qtty. missing orders are represented with +infinity.
			 *
			 * @param qtty
			 * @return
			 */
			public ObservableDoubleValue getSellPrice(int qtty) {
				ObservableDoubleValue ret = qttySellPrice.get(qtty);
				if (ret == null) {
					synchronized (qttySellPrice) {
						if (qttySellPrice.get(qtty) == null) {
							ret = new DoubleBinding() {
								@Override
								protected double computeValue() {
									double sumCost = 0;
									int qttyremain = qtty;
									for (R_get_markets_region_id_orders r : getSellOrders()) {
										int qtty = Math.min(qttyremain, r.volume_remain);
										sumCost += qtty * r.price;
										qttyremain -= qtty;
										if (qttyremain == 0) {
											return sumCost;
										}
									}
									return Double.POSITIVE_INFINITY;
								}

								{
									bind(getSellOrders());
								}
							};
							qttySellPrice.put(qtty, ret);
						} else {
							ret = qttySellPrice.get(qtty);
						}
					}
				}
				return ret;
			}

		}

		// typeid-> cached orders
		private Map<Integer, CachedOrdersList> cachedOrders = new HashMap<>();

		public CachedOrdersList getOrders(int typeID) {
			CachedOrdersList ret = cachedOrders.get(typeID);
			if (ret == null) {
				synchronized (cachedOrders) {
					if (cachedOrders.get(typeID) == null) {
						ret = new CachedOrdersList(typeID);
						cachedOrders.put(typeID, ret);
					} else {
						ret = cachedOrders.get(typeID);
					}
				}
			}
			return ret;
		}

		public double getSO(int typeID, int qtty) {
			return getOrders(typeID).getSellPrice(qtty).doubleValue();
		}

		public double getBO(int typeID, int qtty) {
			return getOrders(typeID).getBuyPrice(qtty).doubleValue();
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
		CachedOrdersList col = getMarket(regionID).getOrders(typeID);
		return buy ? col.getBuyOrders() : col.getSellOrders();
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
