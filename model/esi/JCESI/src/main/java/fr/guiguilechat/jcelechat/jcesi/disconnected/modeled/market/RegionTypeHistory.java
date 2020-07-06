package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsLongHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * contains the orders history of an item in a region<br />
 * The ESI gives a list of older orders first, we switch it backward
 */
public class RegionTypeHistory {

	private static final Logger logger = LoggerFactory.getLogger(RegionTypeHistory.class);

	public final CacheStatic caches;

	private final ObsListHolder<R_get_markets_region_id_history> history;

	public final int typeID;

	public RegionTypeHistory(CacheStatic cache, int regionID, int typeID) {
		caches = cache;
		this.typeID = typeID;
		history = caches.markets.history(regionID, typeID).sorted((a, b) -> b.date.compareTo(a.date));
		// history.peek(l -> logger.warn("received new list for item " + typeID + "
		// size=" + l.size()));
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

	// limited history

	public class LimitedHistory {

		public final int days;

		public LimitedHistory(int days) {
			this.days = days;
		}

		private ObsListHolder<R_get_markets_region_id_history> cacheValues = null;

		public ObsListHolder<R_get_markets_region_id_history> getValues() {
			if (cacheValues == null) {
				synchronized (this) {
					if (cacheValues == null) {
						cacheValues = limitData(days);
					}
				}
			}
			return cacheValues;
		}

		private ObsDoubleHolder cacheAverage = null;

		public ObsDoubleHolder getAverage() {
			if (cacheAverage == null) {
				ObsListHolder<R_get_markets_region_id_history> values = getValues();
				synchronized (values) {
					if (cacheAverage == null) {
						cacheAverage = values.reduceDouble(l -> l.stream().mapToDouble(h -> h.average * h.volume).sum()
								/ l.stream().mapToLong(h -> h.volume).sum());
					}
				}
			}
			return cacheAverage;
		}

		private ObsLongHolder cacheVolume = null;

		public ObsLongHolder getVolume() {
			if (cacheVolume == null) {
				ObsListHolder<R_get_markets_region_id_history> values = getValues();
				synchronized (values) {
					if (cacheVolume == null) {
						cacheVolume = values.reduceLong(l -> l.stream().mapToLong(h -> h.volume).sum());
					}
				}
			}
			return cacheVolume;
		}

		private ObsDoubleHolder cacheTotalValue = null;

		public ObsDoubleHolder getTotalValue() {
			if (cacheTotalValue == null) {
				ObsListHolder<R_get_markets_region_id_history> values = getValues();
				synchronized (values) {
					if (cacheTotalValue == null) {
						cacheTotalValue = values.reduceDouble(l -> l.stream().mapToDouble(h -> h.average * h.volume).sum());
					}
				}
			}
			return cacheTotalValue;
		}

		//

		private ObsListHolderImpl<Long> sortedVolumes = null;

		/** get the list of volumes over last X days, sorted by volume descending */
		public ObsListHolder<Long> getSortedVolumes() {
			if (sortedVolumes == null) {
				ObsListHolder<R_get_markets_region_id_history> values = getValues();
				synchronized (this) {
					if (sortedVolumes == null) {
						ObservableList<Long> internal = FXCollections.observableArrayList();
						ObsListHolderImpl<Long> ret = new ObsListHolderImpl<>(internal);
						values.follow((l) -> {
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

		// best percentile volume

		/** cached offsetpct => volume */
		private HashMap<Integer, ObsObjHolder<Long>> cachedBestVolumes = new HashMap<>();

		/**
		 * get best daily of sale, excluding the first percent.<br />
		 * eg if I set percent 0, I will have the highest daily volume of sales in
		 * the last year, if percent is 50 I will have median, and if percent is 100
		 * I will have lowest
		 *
		 * @param offsetPct
		 *          percent of the best days values
		 * @return
		 */
		public ObsObjHolder<Long> getBestVolume(int offsetPct) {
			ObsObjHolder<Long> ret = cachedBestVolumes.get(offsetPct);
			if (ret == null) {
				ObsListHolder<Long> volumes = getSortedVolumes();
				ret = LockWatchDog.BARKER.syncExecute(cachedBestVolumes, () -> {
					ObsObjHolder<Long> ret2 = cachedBestVolumes.get(offsetPct);
					if (ret2 == null) {
						ret2 = volumes.reduce(l -> {
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

		/** cached offsetpct => bestSO */
		private HashMap<Integer, ObsObjHolder<Long>> cachedBestSO = new HashMap<>();

		/**
		 * get best daily SO completed, excluding the first centile.<br />
		 * eg if I set centile 0, I will have the highest daily volume of SO
		 * completed in the last year, if centile is 50 I will have median, and if
		 * centile is 100 I will have lowest<br />
		 * The volume of SO completed for a day is evaluated as : SO = volume *
		 * (avg-min) / (max-min)
		 *
		 *
		 * @param offsetCnt
		 *          centile of the best values
		 * @return
		 */
		public ObsObjHolder<Long> getBestSO(int offsetCnt) {
			ObsObjHolder<Long> ret = cachedBestSO.get(offsetCnt);
			if (ret == null) {
				ret = LockWatchDog.BARKER.syncExecute(cachedBestSO, () -> {
					ObsObjHolder<Long> ret2 = cachedBestSO.get(offsetCnt);
					if (ret2 == null) {
						ret2 = history.map(l -> {
							if (l.size() == 0) {
								return 0l;
							}
							R_get_markets_region_id_history first = l.get(l.size() - 1);
							LocalDate firstDate = DateTimeFormatter.ISO_LOCAL_DATE.parse(first.date, LocalDate::from);
							LocalDate now = LocalDate.now(Clock.systemUTC());
							int nbDays = (int) (now.toEpochDay() - firstDate.toEpochDay()) + 1;
							int missingdays = nbDays - l.size();
							// the index of the centile value, over the total number of days
							double resultIndex = nbDays - 0.01 * offsetCnt * nbDays;
							if (resultIndex < missingdays || resultIndex < 0) {
								return 0l;
							}
							// get the existing so volumes, per day, sorted increasing
							double[] dailySoVolumesInc = l.stream().mapToDouble(daily -> {
								if (daily.highest == daily.lowest) {
									return 0.5 * daily.volume;
								}
								return daily.volume * (daily.average - daily.lowest) / (daily.highest - daily.lowest);
							}).sorted().toArray();
							long volume = 0l;
							if (resultIndex >= nbDays) {
								// bad percentile (eg -1) so worse case we return the highest
								// value
								volume = Math.round(dailySoVolumesInc[dailySoVolumesInc.length - 1]);
							} else if (Math.round(resultIndex) == resultIndex) {
								volume = Math.round(dailySoVolumesInc[(int) (resultIndex - missingdays)]);
							} else {
								int lowIndex = (int) Math.floor(resultIndex - missingdays);
								if (lowIndex == dailySoVolumesInc.length - 1) {
									System.err.println("typeID=" + typeID + " entries=" + dailySoVolumesInc.length + " over " + nbDays
											+ " days, " + " missingdays=" + missingdays + " resultindex=" + resultIndex);
									volume = Math.round(dailySoVolumesInc[dailySoVolumesInc.length - 1]);
								} else {
									double highBoundratio = resultIndex - missingdays - lowIndex;
									volume = Math.round(dailySoVolumesInc[lowIndex] * (1 - highBoundratio)
											+ dailySoVolumesInc[lowIndex + 1] * highBoundratio);
								}
							}
							logger.trace("type=" + typeID + " bestSOSorted[" + dailySoVolumesInc.length + "]="
									+ DoubleStream.of(dailySoVolumesInc).mapToLong(d -> (long) d).boxed().collect(Collectors.toList())
									+ " with missing " + missingdays + " days; percentile=" + offsetCnt + " index=" + resultIndex
									+ " volume=" + volume);
							return volume;
						});
						cachedBestSO.put(offsetCnt, ret2);
					}
					return ret2;
				});
			}
			// showLater();
			return ret;
		}

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

	/**
	 * limit to last one day
	 */
	public final LimitedHistory daily = new LimitedHistory(1);

	/**
	 * limit to last 7 days
	 */
	public final LimitedHistory weekly = new LimitedHistory(7);

	/**
	 * limit to 30 last days
	 */
	public final LimitedHistory monthly = new LimitedHistory(30);

	/**
	 * limit to 91 last days
	 */
	public final LimitedHistory quarterly = new LimitedHistory(91);

	/**
	 * limit to 365 last days
	 */
	public final LimitedHistory yearly = new LimitedHistory(365);

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

	/**
	 *
	 * @param percentile
	 *          a percentile of daily highest price to remove
	 * @return the highest daily price after percentile % . eg if the highest
	 *         prices for last days are 3,10,15,9, the prices are orders
	 *         decreasing : 15,10,9,3 and the first % of this is removed. the
	 *         percentile is then 25, the first quarter is removed and the highest
	 *         is returned, here 10
	 */
	public double maxDailyPrice(int percentile) {
		// highest prices, ordered increasing.
		double[] orderedPrices = getData().get().stream().mapToDouble(sales -> sales.highest).sorted().toArray();
		double ret = 0;
		int size = Math.max(360, orderedPrices.length);
		int indexFromEnd = (int) Math.ceil(1.0 * size * percentile / 100);
		if (orderedPrices.length > indexFromEnd) {
			ret = orderedPrices[orderedPrices.length - 1 - indexFromEnd];
		}
		return ret;
	}
}
