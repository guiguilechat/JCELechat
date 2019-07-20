package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class ProxyRegionalMarket implements IPricing {

	private final RegionalMarket unfiltered;
	private final ObsListHolder<R_get_markets_region_id_orders> orders;

	public ProxyRegionalMarket(RegionalMarket unfiltered, ObsListHolder<R_get_markets_region_id_orders> orders) {
		this.unfiltered = unfiltered;
		this.orders = orders;
	}

	// typeid-> cached orders
	private Map<Integer, RegionTypeOrders> cachedOrders = new HashMap<>();

	@Override
	public RegionTypeOrders getMarketOrders(int typeID) {
		RegionTypeOrders ret = cachedOrders.get(typeID);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedOrders, () -> {
				RegionTypeOrders ret2 = cachedOrders.get(typeID);
				if (ret2 == null) {
					ret2 = new RegionTypeOrders(orders, typeID);
					cachedOrders.put(typeID, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

	@Override
	public RegionTypeHistory getHistory(int typeID) {
		return unfiltered.getHistory(typeID);
	}

}
