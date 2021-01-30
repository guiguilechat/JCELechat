package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

/**
 * holds the local orders for a type. Caches the price of items.
 */
public class LocalTypeOrders {

	private final ObsListHolder<R_get_markets_region_id_orders> orders;

	public LocalTypeOrders(ObsListHolder<R_get_markets_region_id_orders> orders) {
		this.orders = orders;
	}

	private ObsListHolder<R_get_markets_region_id_orders> cachedBuyOrders;

	/**
	 *
	 * @return the observable list of buy orders, sorted by decreasing price
	 */
	public ObsListHolder<R_get_markets_region_id_orders> listBuyOrders() {
		if (cachedBuyOrders == null) {
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (cachedBuyOrders == null) {
					cachedBuyOrders = orders
							.filter(order -> order.is_buy_order && order.min_volume == 1)
							.sorted((o1, o2) -> -Double.compare(o1.price, o2.price));
				}
			});
		}
		return cachedBuyOrders;
	}

	private ObsListHolder<R_get_markets_region_id_orders> cachedSellOrders;

	/**
	 *
	 * @return the observable list of sell orders, sorted by increasing price
	 */
	public ObsListHolder<R_get_markets_region_id_orders> listSellOrders() {
		if (cachedSellOrders == null) {
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (cachedSellOrders == null) {
					cachedSellOrders = orders
							.filter(order -> !order.is_buy_order && order.min_volume == 1)
							.sorted((o1, o2) -> Double.compare(o1.price, o2.price));
				}
			});
		}
		return cachedSellOrders;
	}

	private HashMap<Long, ObsDoubleHolder> cachedBuyPrice = new HashMap<>();

	public ObsDoubleHolder getBuyPrice(long qtty) {
		ObsDoubleHolder ret = cachedBuyPrice.get(qtty);
		if (ret == null) {
			ObsListHolder<R_get_markets_region_id_orders> source = listBuyOrders();
			synchronized (cachedBuyPrice) {
				ret = cachedBuyPrice.get(qtty);
				if (ret == null) {
					ret = source.mapDouble(l -> {
						double total = 0.0;
						long remain = qtty;
						for (R_get_markets_region_id_orders o : l) {
							if (o.volume_remain >= remain) {
								total += remain * o.price;
								return total;
							} else {
								total += o.price * o.volume_remain;
								remain -= o.volume_remain;
							}
						}
						return total;
					});
					cachedBuyPrice.put(qtty, ret);
				}
			}
		}
		return ret;
	}

	private HashMap<Long, ObsDoubleHolder> cachedSellPrice = new HashMap<>();

	public ObsDoubleHolder getSellPrice(long qtty) {
		ObsDoubleHolder ret = cachedSellPrice.get(qtty);
		if (ret == null) {
			ObsListHolder<R_get_markets_region_id_orders> source = listSellOrders();
			synchronized (cachedSellPrice) {
				ret = cachedSellPrice.get(qtty);
				if (ret == null) {
					ret = source.mapDouble(l -> {
						double total = 0.0;
						long remain = qtty;
						for (R_get_markets_region_id_orders o : l) {
							if (o.volume_remain >= remain) {
								total += remain * o.price;
								return total;
							} else {
								total += o.price * o.volume_remain;
								remain -= o.volume_remain;
							}
						}
						return Double.POSITIVE_INFINITY;
					});
					cachedSellPrice.put(qtty, ret);
				}
			}
		}
		return ret;
	}

	public ObsDoubleHolder getPrice(boolean buy, long qtty) {
		return buy ? getBuyPrice(qtty) : getSellPrice(qtty);
	}

	/**
	 * @return the quantity of items on sale lower than given SO value
	 * @param maxvalue
	 *          maximum price of the sell orders.
	 */
	public long getSOLower(double maxvalue) {
		long qtty = 0;
		for (R_get_markets_region_id_orders order : listSellOrders().get()) {
			if (order.price <= maxvalue) {
				qtty += order.volume_remain;
			} else {
				return qtty;
			}
		}
		return qtty;
	}

}
