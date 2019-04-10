package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsListHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsObjHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
		caches.markets.history(regionID, typeID)
		.follow((ListChangeListener<R_get_markets_region_id_history>) this::handleHistory);
		caches.markets.history(regionID, typeID).addReceivedListener(this::handleReceived);
	}

	private final ObservableList<R_get_markets_region_id_history> cache = FXCollections.observableArrayList();

	public ObservableList<R_get_markets_region_id_history> getData() {
		return cache;
	}

	protected void handleHistory(Change<? extends R_get_markets_region_id_history> change) {
		while (change.next()) {
			for (R_get_markets_region_id_history it : change.getAddedSubList()) {
				// synchronized to avoid concurrent modification and iteration
				LockWatchDog.BARKER.tak(cache);
				synchronized (cache) {
					LockWatchDog.BARKER.hld(cache);
					if (cache.isEmpty() || cache.get(0).date.compareTo(it.date) < 0) {
						cache.add(0, it);
					}
					LockWatchDog.BARKER.rel(cache);
				}
			}
		}
	}

	protected void handleReceived(List<R_get_markets_region_id_history> l) {
		updateAggregates();
		getSortedVolumes().dataReceived();
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

	private SimpleDoubleProperty weeklyAverage = new SimpleDoubleProperty();

	public ObservableDoubleValue weeklyAverage() {
		return weeklyAverage;
	}

	private SimpleLongProperty weeklyVolume = new SimpleLongProperty();

	public ObservableLongValue weeklyVolume() {
		return weeklyVolume;
	}

	private SimpleDoubleProperty monthlyAverage = new SimpleDoubleProperty();

	public ObservableDoubleValue monthlyAverage() {
		return monthlyAverage;
	}

	private SimpleLongProperty monthlyVolume = new SimpleLongProperty();

	public ObservableLongValue monthlyVolume() {
		return monthlyVolume;
	}

	private ObservableList<Long> sortedVolumesUnderlying = FXCollections.observableArrayList();

	private ObsListHolderImpl<Long> sortedVolumes = new ObsListHolderImpl<>(sortedVolumesUnderlying);

	/** get the list of volumes over last 90 days, sorted by volume descending */
	public ObsListHolder<Long> getSortedVolumes() {
		return sortedVolumes;
	}

	/** cached days => offsetpct => volume */
	private HashMap<Integer, HashMap<Integer, ObsObjHolder<Long>>> cachedBestVolumes = new HashMap<>();

	/**
	 * get the total volume for given number of days, excluding the first percent
	 * of the highest volume days.
	 *
	 * @param days
	 *          number of days to sum up the volume
	 * @param offsetPct
	 *          percent of the best days we skip to start summing up
	 * @return
	 */
	public ObsObjHolder<Long> getBestVolume(int days, int offsetPct) {
		HashMap<Integer, ObsObjHolder<Long>> intermediateMap = cachedBestVolumes.get(days);
		if (intermediateMap == null) {
			LockWatchDog.BARKER.tak(cachedBestVolumes);
			synchronized (cachedBestVolumes) {
				LockWatchDog.BARKER.hld(cachedBestVolumes);
				intermediateMap = cachedBestVolumes.get(days);
				if (intermediateMap == null) {
					intermediateMap = new HashMap<>();
					cachedBestVolumes.put(days, intermediateMap);
				}
			}
			LockWatchDog.BARKER.rel(cachedBestVolumes);
		}
		ObsObjHolder<Long> ret = intermediateMap.get(offsetPct);
		if (ret == null) {
			LockWatchDog.BARKER.tak(intermediateMap);
			synchronized (intermediateMap) {
				LockWatchDog.BARKER.hld(intermediateMap);
				ret = intermediateMap.get(offsetPct);
				if (ret == null) {
					SimpleObjectProperty<Long> underlying = new SimpleObjectProperty<>();
					ret = new ObsObjHolderImpl<>(underlying);
					getSortedVolumes().addReceivedListener(l -> {
						int start = Math.min(offsetPct * l.size() / 100, l.size());
						int end = Math.min(start + days, l.size());
						underlying.set(l.subList(start, end).stream().collect(Collectors.reducing(0L, Long::sum)));
					});
					intermediateMap.put(offsetPct, ret);
				}
			}
			LockWatchDog.BARKER.rel(intermediateMap);
		}
		return ret;
	}

	protected void updateAggregates() {
		if (cache.size() > 0) {
			R_get_markets_region_id_history daily = cache.get(0);
			dailyAverage.set(daily.average);
			dailyVolume.set(daily.volume);
		}
		long volume = 0;
		double isks = 0.0;
		for (int i = 0; i < 7 && i < cache.size(); i++) {
			R_get_markets_region_id_history orders = cache.get(i);
			volume += orders.volume;
			isks += orders.volume * orders.average;
		}
		weeklyVolume.set(volume);
		weeklyAverage.set(isks / volume);
		for (int i = 7; i < 30 && i < cache.size(); i++) {
			R_get_markets_region_id_history orders = cache.get(i);
			volume += orders.volume;
			isks += orders.volume * orders.average;
		}
		monthlyVolume.set(volume);
		monthlyAverage.set(isks / volume);
		LockWatchDog.BARKER.tak(sortedVolumesUnderlying);
		synchronized (sortedVolumesUnderlying) {
			LockWatchDog.BARKER.hld(sortedVolumesUnderlying);
			List<Long> newList = cache.stream().map(data -> data.volume).sorted((l1, l2) -> Long.compare(l2, l1))
					.collect(Collectors.toList());
			sortedVolumesUnderlying.clear();
			sortedVolumesUnderlying.addAll(newList);
		}
		LockWatchDog.BARKER.rel(sortedVolumesUnderlying);
	}
}
