package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.Comparator;
import java.util.HashMap;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * holds the local orders for a type, either for buy or for sell . Caches the
 * values of items per quantity.
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
	private final ListHolder<R_get_markets_region_id_orders> filteredOrders = allOrders
	    .filter(order -> order.is_buy_order == buy && order.min_volume == 1)
	    .sorted(buy ? Comparator.comparing(o -> -o.price) : Comparator.comparing(o -> o.price));


	private final HashMap<Long, DoubleHolder> qttyToValue = new HashMap<>();

	/**
	 * @param qtty
	 * @return holder on the total value for given quantity. missing quantity is
	 *           ignored for buy order, makes value infinite for sell order
	 */
	public DoubleHolder getValue(long qtty) {
		DoubleHolder ret = qttyToValue.get(qtty);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(qttyToValue,
			    () -> qttyToValue.computeIfAbsent(qtty, this::makeValue));
		}
		return ret;
	}

	protected DoubleHolder makeValue(long qtty) {
		ListHolder<R_get_markets_region_id_orders> source = getFilteredOrders();
		DoubleHolder ret2 = source.mapDouble(l -> {
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
		return ret2;
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
