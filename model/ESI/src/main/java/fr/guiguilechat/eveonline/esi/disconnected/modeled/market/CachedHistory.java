package fr.guiguilechat.eveonline.esi.disconnected.modeled.market;

import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.eveonline.esi.ConnectedImpl;
import fr.guiguilechat.eveonline.esi.disconnected.CacheStatic;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

/**
 * contains the orders history of an item in a region<br />
 * The ESI gives a list of older orders first, we switch it backward
 */
public class CachedHistory {

	public final CacheStatic caches;
	public final int regionalID;
	public final int typeID;

	public CachedHistory(CacheStatic cache, int regionID, int typeID) {
		caches = cache;
		regionalID = regionID;
		this.typeID = typeID;
		ConnectedImpl.listen(caches.markets.history(regionID, typeID), this::handleHistory);
	}

	private final ObservableList<R_get_markets_region_id_history> cache = FXCollections.observableArrayList();

	public ObservableList<R_get_markets_region_id_history> getData() {
		return cache;
	}

	protected void handleHistory(Change<? extends R_get_markets_region_id_history> change) {
		// we should only have new values
		boolean modif = false;
		change.next();
		for (R_get_markets_region_id_history it : change.getAddedSubList()) {
			if (cache.isEmpty() || cache.get(0).date.compareTo(it.date) < 0) {
				// synchronized to avoid concurrent modification and iteration
				synchronized (cache) {
					cache.add(0, it);
				}
			}
			modif = true;
		}
		if (modif) {
			updateAggregates();
		}
		dataLatch.countDown();
	}

	private final CountDownLatch dataLatch = new CountDownLatch(1);

	public void waitData() {
		try {
			dataLatch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	private SimpleDoubleProperty dailyAverage = new SimpleDoubleProperty();

	public ObservableDoubleValue dailyAverage() {
		return dailyAverage;
	}

	private SimpleLongProperty dailyVolume = new SimpleLongProperty();

	public ObservableLongValue dailyVolume() {
		return dailyVolume;
	}

	protected void updateAggregates() {
		R_get_markets_region_id_history daily = cache.get(0);
		dailyAverage.set(daily.average);
		dailyVolume.set(daily.volume);
	}
}
