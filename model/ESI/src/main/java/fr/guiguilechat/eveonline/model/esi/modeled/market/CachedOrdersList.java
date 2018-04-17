package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger.order_type;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CachedOrdersList {

	/**
	 *
	 */
	private final RegionalMarket regionalMarket;
	public final int typeID;

	private long cacheEnd = 0;

	private ObservableList<R_get_markets_region_id_orders> buyOrders = FXCollections.observableArrayList();
	private ObservableList<R_get_markets_region_id_orders> sellOrders = FXCollections.observableArrayList();

	public CachedOrdersList(RegionalMarket regionalMarket, int order_type) {
		this.regionalMarket = regionalMarket;
		typeID = order_type;
	}

	protected void handleNewOrders(List<R_get_markets_region_id_orders> neworders) {
		// System.err.println("updating orders for item " + typeID + " with " +
		// neworders.size() + " items");
		synchronized (buyOrders) {
			buyOrders.clear();
			for (R_get_markets_region_id_orders o : neworders) {
				if (o.is_buy_order) {
					buyOrders.add(o);
				}
			}
			Collections.sort(buyOrders, (o1, o2) -> (int) Math.signum(o2.price - o1.price));
		}
		synchronized (sellOrders) {
			sellOrders.clear();
			for (R_get_markets_region_id_orders o : neworders) {
				if (!o.is_buy_order) {
					sellOrders.add(o);
				}
			}
			Collections.sort(sellOrders, (o1, o2) -> (int) Math.signum(o1.price - o2.price));
		}
	}

	private synchronized void fetchPage() {
		if (System.currentTimeMillis() <= cacheEnd) {
			return;
		}
		List<R_get_markets_region_id_orders> neworders = ESIConnection
				.loadPages((p, h) -> regionalMarket.markets.esiConnection.raw.get_markets_region_id_orders(
						order_type.all,
						p, regionalMarket.regionID, typeID, h), l -> cacheEnd = l)
				.filter(o -> o.min_volume == 1).collect(Collectors.toList());
		handleNewOrders(neworders);
	}

	public ObservableList<R_get_markets_region_id_orders> listBuyOrders() {
		fetchPage();
		return buyOrders;
	}

	public ObservableList<R_get_markets_region_id_orders> listSellOrders() {
		fetchPage();
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
		if (buy) {
			listBuyOrders();
		} else {
			listSellOrders();
		}
		return ret;
	}

}