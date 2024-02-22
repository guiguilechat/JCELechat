package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * holds the local orders for a type, either for buy or for sell . Caches the
 * price of items.
 */
@RequiredArgsConstructor
public class LocalTypeOrders {

	private final ListHolder<R_get_markets_region_id_orders> allOrders;

	@Getter
	private final boolean buy;

	/**
	 * filtered and sorted orders. buy orders are sorted by price DESC while sell
	 * orders are sorted by price ASC
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> filteredOrders = buy
			? allOrders.filter(order -> order.is_buy_order && order.min_volume == 1)
					.sorted((o1, o2) -> -Double.compare(o1.price, o2.price))
			: allOrders.filter(order -> !order.is_buy_order && order.min_volume == 1)
					.sorted((o1, o2) -> Double.compare(o1.price, o2.price));


	private HashMap<Long, DoubleHolder> cachedPrice = new HashMap<>();

	public DoubleHolder getPrice(long qtty) {
		DoubleHolder ret = cachedPrice.get(qtty);
		if (ret == null) {
			ListHolder<R_get_markets_region_id_orders> source = getFilteredOrders();
			synchronized (cachedPrice) {
				ret = cachedPrice.get(qtty);
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
						return buy ? total : Double.POSITIVE_INFINITY;
					});
					cachedPrice.put(qtty, ret);
				}

			}
		}
		return ret;
	}

	/**
	 * @return the sum of amount of the orders with a better price
	 */
	public long getAmountBetter(double price) {
		long qtty = 0;
		for (R_get_markets_region_id_orders order : getFilteredOrders().get()) {
			boolean better = buy ? order.price >= price : order.price <= price;
			if (better) {
				qtty += order.volume_remain;
			} else {
				return qtty;
			}
		}
		return qtty;
	}

}
