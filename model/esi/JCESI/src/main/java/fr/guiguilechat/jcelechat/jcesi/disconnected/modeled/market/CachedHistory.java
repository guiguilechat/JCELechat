package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.lelouet.collectionholders.impl.ObsObjHolderImpl;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
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
		ObsListHolder<R_get_markets_region_id_history> history = caches.markets.history(regionID, typeID);
		history.follow((ListChangeListener<R_get_markets_region_id_history>) this::handleHistory);
		history.addReceivedListener(this::handleReceived);
	}

	private final ObservableList<R_get_markets_region_id_history> cache = FXCollections.observableArrayList();

	public ObservableList<R_get_markets_region_id_history> getData() {
		return cache;
	}

	protected void handleHistory(Change<? extends R_get_markets_region_id_history> change) {
		LockWatchDog.BARKER.syncExecute(cache, () -> {
			while (change.next()) {
				for (R_get_markets_region_id_history it : change.getAddedSubList()) {
					// synchronized to avoid concurrent modification and iteration
					if (cache.isEmpty() || cache.get(0).date.compareTo(it.date) < 0) {
						cache.add(0, it);
					}
				}
			}
		});
	}

	protected void handleReceived(List<R_get_markets_region_id_history> l) {
		updateAggregates();
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

	/** get the list of volumes over last 365 days, sorted by volume descending */
	public ObsListHolder<Long> getSortedVolumes() {
		return sortedVolumes;
	}

	/** cached offsetpct => volume */
	private HashMap<Integer, ObsObjHolder<Long>> cachedBestVolumes = new HashMap<>();

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
	public ObsObjHolder<Long> getBestVolume(int offsetPct) {
		ObsObjHolder<Long> ret = cachedBestVolumes.get(offsetPct);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedBestVolumes, () -> {
				ObsObjHolder<Long> ret2 = cachedBestVolumes.get(offsetPct);
				if (ret2 == null) {
					SimpleObjectProperty<Long> underlying = new SimpleObjectProperty<>();
					ret2 = new ObsObjHolderImpl<>(underlying);
					getSortedVolumes().addReceivedListener(l -> {
						if (l.size() > 0) {
							int idx = Math.min(offsetPct * (l.size() - 1) / 100, l.size() - 1);
							underlying.set(l.get(idx));
						} else {
							underlying.set(0l);
						}
					});
					cachedBestVolumes.put(offsetPct, ret2);
				}
				return ret2;
			});
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

		List<Long> newList = cache.stream().map(data -> data.volume).sorted((l1, l2) -> Long.compare(l2, l1))
				.collect(Collectors.toList());
		// pad with 0 for the days that do not appear.
		if (cache.size() > 1) {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date1 = myFormat.parse(cache.get(cache.size() - 1).date);
				Date date2 = myFormat.parse(cache.get(0).date);
				long periodDays = TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
				for (int i = newList.size(); i < periodDays + 1; i++) {
					newList.add(0l);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		LockWatchDog.BARKER.syncExecute(sortedVolumesUnderlying, () -> {
			sortedVolumesUnderlying.clear();
			sortedVolumesUnderlying.addAll(newList);
		});
		sortedVolumes.dataReceived();
	}
}
