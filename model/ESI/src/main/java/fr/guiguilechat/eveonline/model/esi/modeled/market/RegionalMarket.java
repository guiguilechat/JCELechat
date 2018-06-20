package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;
import javafx.beans.value.ObservableDoubleValue;

public class RegionalMarket {

	private static final Logger logger = LoggerFactory.getLogger(RegionalMarket.class);

	/**
	 *
	 */
	final Markets markets;
	public final int regionID;

	public RegionalMarket(Markets markets, int regionID) {
		this.markets = markets;
		this.regionID = regionID;
		markets.esiConnection.raw.cache.addFetchCacheArray(
				markets.esiConnection.characterName() + ".regionalMarket_" + regionID,
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
						if (lastOrders != null) {
							ret.handleNewOrders(lastOrders);
						}
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

	protected void handleNewCache(List<R_get_markets_region_id_orders> cacheL) {
		if (cacheL == null) {
			return;
		}

		// ensure none is null. it can be null if a
		// page was not found. Thus we don't handle the data, as it
		// contains incomplete values.
		if (cacheL.contains(null)) {
			logger.warn("discarding market data as some entries are null");
			return;
		}

		// put all the orders in specific buy/sell maps
		HashMap<Integer, List<R_get_markets_region_id_orders>> newItemLists = new HashMap<>();
		synchronized (cachedOrders) {
			for (Integer e : cachedOrders.keySet()) {
				newItemLists.put(e, new ArrayList<>());
			}
		}
		cacheL.forEach(order -> {
			List<R_get_markets_region_id_orders> l = newItemLists.get(order.type_id);
			if (l != null) {
				l.add(order);
			}
		});
		lastOrders = cacheL;
		// here we synchronize to be the only one updating the values
		synchronized (cachedOrders) {
			for (Entry<Integer, CachedOrdersList> e : cachedOrders.entrySet()) {
				e.getValue().handleNewOrders(newItemLists.getOrDefault(e.getKey(), Collections.emptyList()));
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

	//
	// history
	//

	private final HashMap<Integer, CachedHistory> historiesByTypeID = new HashMap<>();

	public CachedHistory getHistory(int typeID) {
		CachedHistory ret = historiesByTypeID.get(typeID);
		if (ret == null) {
			synchronized (historiesByTypeID) {
				if (ret == null) {
					ret = new CachedHistory(this, typeID);
					historiesByTypeID.put(typeID, ret);
				}
			}
		}
		return ret;
	}

}