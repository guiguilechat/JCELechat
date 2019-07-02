package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.List;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsCollectionHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import javafx.collections.ListChangeListener;

public class CachedOrdersList2 {

	private final ObsCollectionHolder<R_get_markets_region_id_orders, List<R_get_markets_region_id_orders>, ListChangeListener<? super R_get_markets_region_id_orders>> sellOrders;
	private final ObsCollectionHolder<R_get_markets_region_id_orders, List<R_get_markets_region_id_orders>, ListChangeListener<? super R_get_markets_region_id_orders>> buyOrders;

	public CachedOrdersList2(ObsListHolder<R_get_markets_region_id_orders> orders, int typeID) {
		sellOrders = orders.filter(order -> order.type_id == typeID && !order.is_buy_order)
				.sorted((o1, o2) -> Double.compare(o1.price, o2.price));
		buyOrders = orders.filter(order -> order.type_id == typeID && order.is_buy_order)
				.sorted((o1, o2) -> -Double.compare(o1.price, o2.price));
	}

}
