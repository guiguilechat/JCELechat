package fr.guiguilechat.eveonline.model.esi.modeled.market;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * contains the orders history of an item in a region<br />
 * The ESI gives a list of older orders first, we switch it backward
 */
public class CachedHistory {

	public final RegionalMarket regionalMarket;
	public final int typeID;

	public CachedHistory(RegionalMarket regionalMarket, int typeID) {
		this.regionalMarket = regionalMarket;
		this.typeID = typeID;
		ESIAccount acc = regionalMarket.markets.esiConnection;
		acc.raw.cache.addFetchCacheArray(acc.characterName() + ".history_r" + regionalMarket.regionID + "_i" + typeID,
				(p, h) -> acc.raw.get_markets_history(regionalMarket.regionID, typeID, h), this::handleHistory);
	}

	private final ObservableList<R_get_markets_region_id_history> cache = FXCollections.observableArrayList();

	public ObservableList<R_get_markets_region_id_history> getData() {
		return cache;
	}

	protected void handleHistory(List<R_get_markets_region_id_history> list) {
		boolean modif = false;
		for (R_get_markets_region_id_history it : list) {
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
