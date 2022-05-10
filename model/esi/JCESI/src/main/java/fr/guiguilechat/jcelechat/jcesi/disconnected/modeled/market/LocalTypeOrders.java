package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * holds the local orders for a type. Caches the price of items.
 */
@RequiredArgsConstructor
public class LocalTypeOrders {

	@Getter
	private final ListHolder<R_get_markets_region_id_orders> orders;

	/**
	 * the observable list of buy orders, sorted by decreasing price
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> buyOrders = orders
	.filter(order -> order.is_buy_order && order.min_volume == 1)
	.sorted((o1, o2) -> -Double.compare(o1.price, o2.price));

	/**
	 * the observable list of sell orders, sorted by increasing price
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> sellOrders = orders
	.filter(order -> !order.is_buy_order && order.min_volume == 1)
	.sorted((o1, o2) -> Double.compare(o1.price, o2.price));

	private HashMap<Long, DoubleHolder> cachedBuyPrice = new HashMap<>();

	public DoubleHolder getBuyPrice(long qtty) {
		DoubleHolder ret = cachedBuyPrice.get(qtty);
		if (ret == null) {
			ListHolder<R_get_markets_region_id_orders> source = getBuyOrders();
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

	private HashMap<Long, DoubleHolder> cachedSellPrice = new HashMap<>();

	public DoubleHolder getSellPrice(long qtty) {
		DoubleHolder ret = cachedSellPrice.get(qtty);
		if (ret == null) {
			ListHolder<R_get_markets_region_id_orders> source = getSellOrders();
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

	public DoubleHolder getPrice(boolean buy, long qtty) {
		return buy ? getBuyPrice(qtty) : getSellPrice(qtty);
	}

	/**
	 * @return the quantity of items on sale lower than given SO value
	 * @param maxvalue
	 *          maximum price of the sell orders.
	 */
	public long getSOLower(double maxvalue) {
		long qtty = 0;
		for (R_get_markets_region_id_orders order : getSellOrders().get()) {
			if (order.price <= maxvalue) {
				qtty += order.volume_remain;
			} else {
				return qtty;
			}
		}
		return qtty;
	}

}
