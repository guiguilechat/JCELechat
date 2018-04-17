package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;

public class RegionalMarket {

	/**
	 *
	 */
	final Markets markets;
	public final int regionID;

	public RegionalMarket(Markets markets, int regionID) {
		this.markets = markets;
		this.regionID = regionID;
		markets.esiConnection.addFetchCache(
				(p, h) -> markets.esiConnection.raw.get_markets_region_id_orders(Swagger.order_type.all, p, regionID, null, h),
				this::handleNewCache);
	}

	// typeid-> cached orders
	private Map<Integer, CachedOrdersList> cachedOrders = new HashMap<>();

	public CachedOrdersList getMarketOrders(int typeID) {
		CachedOrdersList ret = cachedOrders.get(typeID);
		if (ret == null) {
			synchronized (cachedOrders) {
				ret = cachedOrders.get(typeID);
				if (ret == null) {
					ret = new CachedOrdersList(this, typeID);
					cachedOrders.put(typeID, ret);
				}
			}
		}
		return ret;
	}

	public double getSO(int typeID, int qtty) {
		return getMarketOrders(typeID).getPrice(false, qtty).doubleValue();
	}

	public double getBO(int typeID, int qtty) {
		return getMarketOrders(typeID).getPrice(true, qtty).doubleValue();
	}

	protected void handleNewCache(Stream<R_get_markets_region_id_orders> newCache) {

		// put all the orders in specific buy/sell maps
		HashMap<Integer, List<R_get_markets_region_id_orders>> newList = new HashMap<>();
		synchronized (cachedOrders) {
			for (Integer e : cachedOrders.keySet()) {
				newList.put(e, new ArrayList<>());
			}
		}
		newCache.forEachOrdered(order -> {
			List<R_get_markets_region_id_orders> l = newList.get(order.type_id);
			if (l != null) {
				l.add(order);
			}
		});
		synchronized (cachedOrders) {
			for (Entry<Integer, CachedOrdersList> e : cachedOrders.entrySet()) {
				e.getValue().handleNewOrders(newList.getOrDefault(e.getKey(), Collections.emptyList()));
			}
		}
	}

}