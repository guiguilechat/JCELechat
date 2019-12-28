package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

/**
 * holds the regional orders for a type. Caches the price of items.
 */
public class RegionTypeOrders {

	private final ObsListHolder<R_get_markets_region_id_orders> orders;
	private final int typeID;

	private ObsListHolder<R_get_markets_region_id_orders> sellOrders;
	private ObsListHolder<R_get_markets_region_id_orders> buyOrders;

	public RegionTypeOrders(ObsListHolder<R_get_markets_region_id_orders> orders, int typeID) {
		this.orders = orders;
		this.typeID = typeID;
	}

	/**
	 *
	 * @return the observable list of buy orders, sorted by decreasing value
	 */
	public ObsListHolder<R_get_markets_region_id_orders> listBuyOrders() {
		if (buyOrders == null) {
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (buyOrders == null) {
					buyOrders = orders.filter(order -> order.type_id == typeID && order.is_buy_order && order.min_volume == 1)
							.sorted((o1, o2) -> -Double.compare(o1.price, o2.price));
				}
			});
		}
		return buyOrders;
	}

	/**
	 *
	 * @return the observable list of sell orders, sorted by increasing value
	 */
	public ObsListHolder<R_get_markets_region_id_orders> listSellOrders() {
		if (sellOrders == null) {
			LockWatchDog.BARKER.syncExecute(orders, () -> {
				if (sellOrders == null) {
					sellOrders = orders.filter(order -> order.type_id == typeID && !order.is_buy_order && order.min_volume == 1)
							.sorted((o1, o2) -> Double.compare(o1.price, o2.price));
				}
			});
		}
		return sellOrders;
	}

	private HashMap<Long, ObsDoubleHolder> qttyBuyPrice = new HashMap<>();

	private HashMap<Long, ObsDoubleHolder> qttySellPrice = new HashMap<>();

	public ObsDoubleHolder getBuyPrice(long qtty) {
		return getPrice(true, qtty);
	}

	public ObsDoubleHolder getSellPrice(long qtty) {
		return getPrice(false, qtty);
	}

	public ObsDoubleHolder getPrice(boolean buy, long qtty) {
		HashMap<Long, ObsDoubleHolder> map = buy ? qttyBuyPrice : qttySellPrice;
		ObsDoubleHolder ret = map.get(qtty);
		if (ret == null) {
			synchronized (map) {
				ret = map.get(qtty);
				if (ret == null) {
					ObsListHolder<R_get_markets_region_id_orders> source = buy ? listBuyOrders() : listSellOrders();
					ret = source.reduceDouble(l -> {
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
						return buy ? total : Double.POSITIVE_INFINITY;
					});
					map.put(qtty, ret);
				}
			}
		}
		return ret;
	}

	/**
	 * @return the quantity of items on sale lower than given SO value
	 * @param maxvalue maximum price of the sell orders.
	 */
	public long getSOLower(double maxvalue) {
		long qtty = 0;
		for (R_get_markets_region_id_orders order : listSellOrders().get()) {
			if (order.price <= maxvalue) {
				qtty+=order.volume_remain;
			} else {
				return qtty;
			}
		}
		return qtty;
	}

}
