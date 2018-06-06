package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.eveonline.model.esi.ESIAccount.SelfExecutable;
import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CachedOrdersList {

	/**
	 *
	 */
	public final RegionalMarket regionalMarket;
	public final int typeID;

	private ObservableList<R_get_markets_region_id_orders> buyOrders = FXCollections.observableArrayList();
	private ObservableList<R_get_markets_region_id_orders> sellOrders = FXCollections.observableArrayList();

	public CachedOrdersList(RegionalMarket regionalMarket, int order_type) {
		this.regionalMarket = regionalMarket;
		typeID = order_type;
	}

	protected void handleNewOrders(List<R_get_markets_region_id_orders> neworders) {
		synchronized (buyOrders) {
			buyOrders.clear();
			if (neworders != null) {
				for (R_get_markets_region_id_orders o : neworders) {
					if (o.type_id == typeID && o.is_buy_order) {
						buyOrders.add(o);
					}
				}
				Collections.sort(buyOrders, (o1, o2) -> (int) Math.signum(o2.price - o1.price));
			}
		}
		synchronized (sellOrders) {
			sellOrders.clear();
			if (neworders != null) {
				for (R_get_markets_region_id_orders o : neworders) {
					if (o.type_id == typeID && !o.is_buy_order) {
						sellOrders.add(o);
					}
				}
				Collections.sort(sellOrders, (o1, o2) -> (int) Math.signum(o1.price - o2.price));
			}
		}
	}

	protected void handleNewCache(List<R_get_markets_region_id_orders> newCache) {
		handleNewOrders(newCache);
	}

	private Runnable selfOrdersStop = null;

	/**
	 * add a specific fetcher for this. this will increase the rate of fetches
	 */
	public synchronized void addFetcher() {
		if (selfOrdersStop != null) {
			return;
		}
		SelfExecutable exec = regionalMarket.markets.esiConnection.addFetchCacheArray(
				regionalMarket.markets.esiConnection.characterName() + ".orders_type" + typeID,
				(p, h) -> regionalMarket.markets.esiConnection.raw
				.get_markets_region_id_orders(Swagger.order_type.all, p, regionalMarket.regionID, typeID, h),
				this::handleNewCache);
		selfOrdersStop = exec::stop;
	}

	public synchronized void remFetcher() {
		if (selfOrdersStop == null) {
			return;
		}
		selfOrdersStop.run();
		selfOrdersStop = null;
	}

	public ObservableList<R_get_markets_region_id_orders> listBuyOrders() {
		return buyOrders;
	}

	public ObservableList<R_get_markets_region_id_orders> listSellOrders() {
		return sellOrders;
	}

	private HashMap<Integer, DoubleBinding> qttyBuyPrice = new HashMap<>();

	private HashMap<Integer, DoubleBinding> qttySellPrice = new HashMap<>();

	/**
	 * get a double value representing the total sell orders for qtty given
	 * qtty. missing orders are represented with +infinity.
	 *
	 * @param qtty
	 * @return
	 */
	public ObservableDoubleValue getPrice(boolean buy, int qtty) {
		HashMap<Integer, DoubleBinding> m = buy ? qttyBuyPrice : qttySellPrice;
		DoubleBinding ret = m.get(qtty);
		if (ret == null) {
			synchronized (m) {
				ret = m.get(qtty);
				if (ret == null) {
					ret = new DoubleBinding() {
						@Override
						protected double computeValue() {
							// ensure the regional market got its last orders, in case we are
							// just created this may not be true yet.
							regionalMarket.ensureFetched();
							double sumCost = 0;
							int qttyremain = qtty;
							ObservableList<R_get_markets_region_id_orders> orders = buy ? listBuyOrders() : listSellOrders();
							synchronized (orders) {
								for (R_get_markets_region_id_orders r : orders) {
									int qtty = Math.min(qttyremain, r.volume_remain);
									sumCost += qtty * r.price;
									qttyremain -= qtty;
									if (qttyremain == 0) {
										return sumCost;
									}
								}
							}
							return buy ? sumCost : Double.POSITIVE_INFINITY;
						}

						{
							bind(buy ? listBuyOrders() : listSellOrders());
						}
					};
					m.put(qtty, ret);
				}
			}
		}
		return ret;
	}

}