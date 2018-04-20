package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;
import javafx.beans.value.ObservableDoubleValue;

public class RegionalMarket {

	/**
	 *
	 */
	final Markets markets;
	public final int regionID;

	public RegionalMarket(Markets markets, int regionID) {
		this.markets = markets;
		this.regionID = regionID;
		markets.esiConnection.addFetchCacheArray(
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
					synchronized (lastOrders) {
						ret.handleNewOrders(lastOrders);
					}
					// ret.addFetcher();
					cachedOrders.put(typeID, ret);
				}
			}
		}
		return ret;
	}

	public ObservableDoubleValue getSO(int typeID, int qtty) {
		return getMarketOrders(typeID).getPrice(false, qtty);
	}

	public ObservableDoubleValue getBO(int typeID, int qtty) {
		return getMarketOrders(typeID).getPrice(true, qtty);
	}

	private List<R_get_markets_region_id_orders> lastOrders = new ArrayList<>();

	private CountDownLatch lastOrdersAccess = new CountDownLatch(1);

	protected void handleNewCache(Stream<R_get_markets_region_id_orders> newCache) {

		// put all the orders in specific buy/sell maps
		HashMap<Integer, List<R_get_markets_region_id_orders>> newList = new HashMap<>();
		// this is synchronized to avoid an exception while iterating over the
		// cachedOrders.
		synchronized (cachedOrders) {
			for (Integer e : cachedOrders.keySet()) {
				newList.put(e, new ArrayList<>());
			}
		}
		synchronized (lastOrders) {
			lastOrders.clear();
			newCache.forEachOrdered(order -> {
				List<R_get_markets_region_id_orders> l = newList.get(order.type_id);
				if (l != null) {
					l.add(order);
				}
				lastOrders.add(order);
			});
		}
		// here we synchronize to be the only one updating the values
		synchronized (cachedOrders) {
			for (Entry<Integer, CachedOrdersList> e : cachedOrders.entrySet()) {
				e.getValue().handleNewOrders(newList.getOrDefault(e.getKey(), Collections.emptyList()));
			}
		}
		lastOrdersAccess.countDown();
	}

	/**
	 * wait until data is fetched
	 */
	public void ensureFetched() {
		try {
			lastOrdersAccess.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}