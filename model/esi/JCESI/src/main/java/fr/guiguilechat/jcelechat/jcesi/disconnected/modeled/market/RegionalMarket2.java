package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class RegionalMarket2 {

	private final ObsListHolder<R_get_markets_region_id_orders> orders;

	public RegionalMarket2(CacheStatic cache, int regionID) {
		orders = cache.markets.orders(order_type.all, regionID, null);
	}

	// typeid-> cached orders
	private Map<Integer, CachedOrdersList2> cachedOrders = new HashMap<>();

	public CachedOrdersList2 getMarketOrders(int typeID) {
		CachedOrdersList2 ret = cachedOrders.get(typeID);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedOrders, () -> {
				CachedOrdersList2 ret2 = cachedOrders.get(typeID);
				if (ret2 == null) {
					ret2 = new CachedOrdersList2(orders, typeID);
					cachedOrders.put(typeID, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

}
