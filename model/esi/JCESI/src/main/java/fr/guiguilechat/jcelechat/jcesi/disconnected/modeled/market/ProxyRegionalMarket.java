package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

/**
 * implement pricing over a filtered list of market orders. also shows a history
 * but this is delegated to root market. Basically just keeps the filtered order
 * and a cache of the type orders based on those filtered orders.
 *
 */
public class ProxyRegionalMarket implements IPricing {

	private final RegionalMarket source;
	private final ObsMapHolderImpl<Integer, List<R_get_markets_region_id_orders>> ordersByTypeID;

	/**
	 *
	 * @param unfiltered
	 *          the root regional market.
	 * @param orders
	 *          the filtered set of orders.
	 */
	public ProxyRegionalMarket(RegionalMarket unfiltered, ObsListHolder<R_get_markets_region_id_orders> orders) {
		source = unfiltered;
		ordersByTypeID = new ObsMapHolderImpl<>();
		ObsObjHolder<Map<Integer, List<R_get_markets_region_id_orders>>> mapped = orders
				.map(l -> l.stream().collect(Collectors.groupingBy(order -> order.type_id)));
		mapped.follow(m -> {
			ordersByTypeID.set(m);
		});
	}

	// typeid-> cached orders
	private Map<Integer, LocalTypeOrders> cachedOrders = new HashMap<>();

	@Override
	public LocalTypeOrders getMarketOrders(int typeID) {
		LocalTypeOrders ret = cachedOrders.get(typeID);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedOrders, () -> {
				LocalTypeOrders ret2 = cachedOrders.get(typeID);
				if (ret2 == null) {
					ret2 = new LocalTypeOrders(ordersByTypeID.at(typeID, Collections.emptyList()).toList(l -> l));
					cachedOrders.put(typeID, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

	@Override
	public RegionTypeHistory getHistory(int typeID) {
		return source.getHistory(typeID);
	}

}
