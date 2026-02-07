package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;

/**
 * interface to get a pricing of items
 */
public interface IPricing {

	/**
	 * @param typeID    type to get price of.
	 * @param buyOrders if true, return values from buy order. Else from sell orders
	 * @return cached data
	 */
	LocalTypeOrders getMarketOrders(int typeID, boolean buyOrders);

	/**
	 * @return a holder on the buy orders for given type, sorted by price desc
	 */
	default ListHolder<R_get_markets_region_id_orders> listBuyOrders(int typeId) {
		return getMarketOrders(typeId, true).getFilteredOrders();
	}

	default ListHolder<R_get_markets_region_id_orders> listBuyOrdersDiscarding(int typeId, double discardedValue) {
		return listBuyOrders(typeId)
				.mapList(l -> discardValue(l, discardedValue));
	}

	/**
	 * @return a holder on the sell orders for given type, sorted by price inc
	 */
	default ListHolder<R_get_markets_region_id_orders> listSellOrders(int typeId) {
		return getMarketOrders(typeId, false).getFilteredOrders();
	}

	default ListHolder<R_get_markets_region_id_orders> listSellOrdersDiscarding(int typeId, double discardedValue) {
		return listSellOrders(typeId)
				.mapList(l -> discardValue(l, discardedValue));
	}

	/**
	 * discards the first orders until enough price×volume_remain has been removed.
	 * for example if first element has 1 total value, second has 100 , and we are
	 * requested to discard 50, then this will return the same list, except the
	 * first element
	 *
	 * @param orders         list of orders. Their sorting is conserved on the
	 *                       result.
	 * @param discardedValue total(max) amount to be discarded from the first orders
	 * @return a list with same orders, except the first ones are removed until
	 *         removing up to the dsicarded value.
	 */
	default List<R_get_markets_region_id_orders> discardValue(List<R_get_markets_region_id_orders> orders,
			double discardedValue) {
		if(discardedValue<=0.0) {
			return orders;
		}
		List<R_get_markets_region_id_orders> ret = new ArrayList<>(orders);
		while(discardedValue>0 && !ret.isEmpty()) {
			R_get_markets_region_id_orders order = ret.get(0);
			discardedValue-=order.volume_remain*order.price;
			if(discardedValue>=0) {
				ret.remove(0);
			}
		}
		return ret;
	}

	default DoubleHolder getValue(int typeID, long qtty, boolean buy) {
		LocalTypeOrders lto = getMarketOrders(typeID, buy);
		DoubleHolder ret = lto.getValue(qtty);
		return ret;
	}

	default DoubleHolder getSOValue(int typeID, long qtty) {
		return getValue(typeID, qtty, false);
	}

	default DoubleHolder getBOValue(int typeID, long qtty) {
		DoubleHolder ret = getValue(typeID, qtty, true);
		return ret;
	}

	default Instant lastTypeIssued(int typeId) {
		Optional<String> lastTimeStamp = Stream.concat(
				getMarketOrders(typeId, true).getFilteredOrders().get().stream(),
				getMarketOrders(typeId, false).getFilteredOrders().get().stream()
			).map(o -> o.issued)
				.sorted().reduce((_, b) -> b);
		if (lastTimeStamp.isEmpty()) {
			return null;
		}
		return ESIDateTools.fieldInstant(lastTimeStamp.get());
	}

	void onUpdate(Runnable r);

	void delOnUpdate(Runnable r);

}
