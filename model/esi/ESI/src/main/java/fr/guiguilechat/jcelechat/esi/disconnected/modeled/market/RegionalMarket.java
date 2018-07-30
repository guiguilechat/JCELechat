package fr.guiguilechat.jcelechat.esi.disconnected.modeled.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.esi.ConnectedImpl;
import fr.guiguilechat.jcelechat.esi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.esi.compiled.G_ITransfer;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_region_id_orders;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;

public class RegionalMarket {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RegionalMarket.class);

	public final int regionID;
	public final CacheStatic cache;

	public RegionalMarket(CacheStatic cache, int regionID) {
		this.regionID = regionID;
		this.cache=cache;
		ConnectedImpl.listenL(cache.markets.orders(G_ITransfer.order_type.all, regionID, null),
				(ListChangeListener<R_get_markets_region_id_orders>) this::handleNewCache);
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
		synchronized (cachedOrders) {
			for (Integer e : cachedOrders.keySet()) {
				newItemLists.put(e, new ArrayList<>());
			}
		}
		change.getAddedSubList().forEach(order -> {
			List<R_get_markets_region_id_orders> l = newItemLists.get(order.type_id);
			if (l != null) {
				l.add(order);
			}
		});
		lastOrders.clear();
		lastOrders.addAll(change.getAddedSubList());
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
					ret = new CachedHistory(cache, regionID, typeID);
					historiesByTypeID.put(typeID, ret);
				}
			}
		}
		return ret;
	}

}