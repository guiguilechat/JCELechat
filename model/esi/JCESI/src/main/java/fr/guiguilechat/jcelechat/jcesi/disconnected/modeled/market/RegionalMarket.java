package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;

/** represents a market in a region */
public class RegionalMarket {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RegionalMarket.class);

	public final int regionID;
	public final CacheStatic cache;

	public RegionalMarket(CacheStatic cache, int regionID) {
		this.regionID = regionID;
		this.cache = cache;
		cache.markets.orders(order_type.all, regionID, null)
		.follow((ListChangeListener<R_get_markets_region_id_orders>) this::handleNewCache);
	}

	// typeid-> cached orders
	private Map<Integer, CachedOrdersList> cachedOrders = new HashMap<>();

	public CachedOrdersList getMarketOrders(int typeID) {
		CachedOrdersList ret = cachedOrders.get(typeID);
		if (ret == null) {
			LockWatchDog.BARKER.tak(cachedOrders);
			synchronized (cachedOrders) {
				LockWatchDog.BARKER.hld(cachedOrders);
				ret = cachedOrders.get(typeID);
				if (ret == null) {
					ret = new CachedOrdersList(this, typeID);
					LockWatchDog.BARKER.tak(lastOrders);
					synchronized (lastOrders) {
						LockWatchDog.BARKER.hld(lastOrders);
						if (lastOrders != null) {
							ret.handleNewOrders(lastOrders);
						}
					}
					LockWatchDog.BARKER.rel(lastOrders);
					// ret.addFetcher();
					cachedOrders.put(typeID, ret);
				}
			}
			LockWatchDog.BARKER.rel(cachedOrders);
		}
		return ret;
	}

	public ObservableDoubleValue getSO(int typeID, long qtty) {
		return getMarketOrders(typeID).getPrice(false, qtty);
	}

	public ObservableDoubleValue getBO(int typeID, long qtty) {
		return getMarketOrders(typeID).getPrice(true, qtty);
	}

	private List<R_get_markets_region_id_orders> lastOrders = new ArrayList<>();

	private CountDownLatch lastOrdersAccess = new CountDownLatch(1);

	/**
	 * the fetch-cache method used always replace the cached list with a new list.
	 * So we erase previous data and recreate it every time.
	 *
	 * @param change
	 */
	protected void handleNewCache(Change<? extends R_get_markets_region_id_orders> change) {
		change.next();

		// put all the orders in specific buy/sell maps
		HashMap<Integer, List<R_get_markets_region_id_orders>> newItemLists = new HashMap<>();
		LockWatchDog.BARKER.tak(cachedOrders);
		synchronized (cachedOrders) {
			LockWatchDog.BARKER.hld(cachedOrders);
			for (Integer e : cachedOrders.keySet()) {
				newItemLists.put(e, new ArrayList<>());
			}
		}
		LockWatchDog.BARKER.rel(cachedOrders);
		change.getAddedSubList().forEach(order -> {
			List<R_get_markets_region_id_orders> l = newItemLists.get(order.type_id);
			if (l != null) {
				l.add(order);
			}
		});
		LockWatchDog.BARKER.tak(lastOrders);
		synchronized (lastOrders) {
			LockWatchDog.BARKER.hld(lastOrders);
			lastOrders.clear();
			lastOrders.addAll(change.getAddedSubList());
		}
		LockWatchDog.BARKER.rel(lastOrders);
		// here we synchronize to be the only one updating the values
		LockWatchDog.BARKER.tak(cachedOrders);
		synchronized (cachedOrders) {
			LockWatchDog.BARKER.hld(cachedOrders);
			for (Entry<Integer, CachedOrdersList> e : cachedOrders.entrySet()) {
				e.getValue().handleNewOrders(newItemLists.getOrDefault(e.getKey(), Collections.emptyList()));
			}
		}
		LockWatchDog.BARKER.rel(cachedOrders);
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
			LockWatchDog.BARKER.tak(historiesByTypeID);
			synchronized (historiesByTypeID) {
				LockWatchDog.BARKER.hld(historiesByTypeID);
				ret = historiesByTypeID.get(typeID);
				if (ret == null) {
					ret = new CachedHistory(cache, regionID, typeID);
					historiesByTypeID.put(typeID, ret);
				}
			}
			LockWatchDog.BARKER.rel(historiesByTypeID);
		}
		return ret;
	}

}