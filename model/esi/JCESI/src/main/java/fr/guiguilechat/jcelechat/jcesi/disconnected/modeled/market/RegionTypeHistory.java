package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsLongHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableLongValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * contains the orders history of an item in a region<br />
 * The ESI gives a list of older orders first, we switch it backward
 */
public class RegionTypeHistory {

	public final CacheStatic caches;

	private final ObsListHolder<R_get_markets_region_id_history> history;

	public RegionTypeHistory(CacheStatic cache, int regionID, int typeID) {
		caches = cache;
		history = caches.markets.history(regionID, typeID).sorted((a, b) -> b.date.compareTo(a.date));
	}

	public ObsListHolder<R_get_markets_region_id_history> getData() {
		return history;
	}

	private static Stream<R_get_markets_region_id_history> withinDays(List<R_get_markets_region_id_history> list,
			int days) {
		String lowerBound = daysAgo(days);
		return list.stream().limit(days).filter(h -> h.date.compareTo(lowerBound) <= 0);
	}

	/**
	 *
	 * @param days
	 * @return a new string representing the date x days before, in
	 *         {@link DateTimeFormatter#ISO_LOCAL_DATE} format.
	 */
	private static String daysAgo(int days) {
		return LocalDate.now(Clock.systemUTC()).minusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	private ObsListHolder<R_get_markets_region_id_history> limitData(int maxDays) {
		ObservableList<R_get_markets_region_id_history> internal = FXCollections.observableArrayList();
		ObsListHolderImpl<R_get_markets_region_id_history> ret = new ObsListHolderImpl<>(internal);
		history.follow((l) -> {
			internal.clear();
			withinDays(l, maxDays).forEach(internal::add);
			ret.dataReceived();
		});
		return ret;
	}


	// daily

	private ObsListHolder<R_get_markets_region_id_history> dailyValues = null;

	private ObsDoubleHolder dailyAverage = null;

	private ObsLongHolder dailyVolume = null;

	private void makeDailyVariables() {
		if (dailyValues == null) {
			synchronized (this) {
				if (dailyValues == null) {
					ObsListHolder<R_get_markets_region_id_history> dailyValues = limitData(1);
					dailyAverage = dailyValues.reduceDouble(
							l -> l.stream().mapToDouble(h -> h.average * h.volume).sum() / l.stream().mapToLong(h -> h.volume).sum());
					dailyVolume = dailyValues.reduceLong(l -> l.stream().mapToLong(h -> h.volume).sum());
					this.dailyValues = dailyValues;
				}
			}
		}
	}

	public ObservableDoubleValue dailyAverage() {
		makeDailyVariables();
		dailyAverage.get();
		return dailyAverage.asObservableNumber();
	}

	public ObservableLongValue dailyVolume() {
		makeDailyVariables();
		dailyVolume.get();
		return dailyVolume.asObservableNumber();
	}

	// weekly

	private ObsListHolder<R_get_markets_region_id_history> weeklyValues = null;

	private ObsDoubleHolder weeklyAverage = null;

	private ObsLongHolder weeklyVolume = null;

	private void makeWeeklyVariables() {
		if (weeklyValues == null) {
			synchronized (this) {
				if (weeklyValues == null) {
					ObsListHolder<R_get_markets_region_id_history> weeklyValues = limitData(7);
					weeklyAverage = weeklyValues.reduceDouble(
							l -> l.stream().mapToDouble(h -> h.average * h.volume).sum() / l.stream().mapToLong(h -> h.volume).sum());
					weeklyVolume = weeklyValues.reduceLong(l -> l.stream().mapToLong(h -> h.volume).sum());
					this.weeklyValues = weeklyValues;
				}
			}
		}
	}

	public ObservableDoubleValue weeklyAverage() {
		makeWeeklyVariables();
		weeklyAverage.get();
		return weeklyAverage.asObservableNumber();
	}

	public ObservableLongValue weeklyVolume() {
		makeWeeklyVariables();
		weeklyVolume.get();
		return weeklyVolume.asObservableNumber();
	}

	// monthly



	private ObsListHolder<R_get_markets_region_id_history> monthlyValues = null;

	private ObsDoubleHolder monthlyAverage = null;

	private ObsLongHolder monthlyVolume = null;

	private void makeMonthlyVariables() {
		if (monthlyValues == null) {
			synchronized (this) {
				if (monthlyValues == null) {
					ObsListHolder<R_get_markets_region_id_history> monthlyValues = limitData(30);
					monthlyAverage = monthlyValues.reduceDouble(
							l -> l.stream().mapToDouble(h -> h.average * h.volume).sum() / l.stream().mapToLong(h -> h.volume).sum());
					monthlyVolume = monthlyValues.reduceLong(l -> l.stream().mapToLong(h -> h.volume).sum());
					this.monthlyValues = monthlyValues;
				}
			}
		}
	}

	public ObservableDoubleValue monthlyAverage() {
		makeMonthlyVariables();
		monthlyAverage.get();
		return monthlyAverage.asObservableNumber();
	}

	public ObservableLongValue monthlyVolume() {
		makeMonthlyVariables();
		monthlyVolume.get();
		return monthlyVolume.asObservableNumber();
	}

	// ??


	private ObsListHolderImpl<Long> sortedVolumes = null;

	/** get the list of volumes over last X days, sorted by volume descending */
	public ObsListHolder<Long> getSortedVolumes() {
		if (sortedVolumes == null) {
			synchronized (this) {
				if (sortedVolumes == null) {
					ObservableList<Long> internal = FXCollections.observableArrayList();
					ObsListHolderImpl<Long> ret = new ObsListHolderImpl<>(internal);
					history.follow((l) -> {
						internal.clear();
						if (l.size() > 0) {
							R_get_markets_region_id_history first = l.get(l.size() - 1);
							LocalDate firstDate = DateTimeFormatter.ISO_LOCAL_DATE.parse(first.date, LocalDate::from);
							LocalDate now = LocalDate.now(Clock.systemUTC());
							int nbDays = (int) (now.toEpochDay() - firstDate.toEpochDay());
							long[] volumes = new long[nbDays];
							for (int i = 0; i < l.size() && i < nbDays; i++) {
								volumes[i] = l.get(i).volume;
							}
							Arrays.sort(volumes);
							internal.addAll(LongStream.of(volumes).mapToObj(lon -> lon).collect(Collectors.toList()));
						} else {
						}
						ret.dataReceived();
					});
					// reverse because Arrays.sort use increasing order
					sortedVolumes = ret.reverse();
				}
			}
		}
		return sortedVolumes;
	}

	/** cached offsetpct => volume */
	private HashMap<Integer, ObsObjHolder<Long>> cachedBestVolumes = new HashMap<>();

	/**
	 * get best daily of sale, excluding the first percent.<br />
	 * eg if I set percent 0, I will have the highest daily volume of sales in the
	 * last year, if percent is 50 I will have median, and if percent is 100 I
	 * will have lowest
	 * 
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
					ret2 = getSortedVolumes().reduce(l -> {
						if (l.size() > 0) {
							int idx = Math.min(offsetPct * (l.size() - 1) / 100, l.size() - 1);
							return l.get(idx);
						} else {
							return 0l;
						}
					});
					cachedBestVolumes.put(offsetPct, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}
}
