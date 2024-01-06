package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.CacheStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.holders.interfaces.numbers.LongHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * contains the orders history of an item in a region<br />
 * The ESI gives a list of older orders first, we switch it backward
 */
public class RegionTypeHistory {

	private static final Logger logger = LoggerFactory.getLogger(RegionTypeHistory.class);

	public final CacheStatic cachestatic;

	private final ListHolder<R_get_markets_region_id_history> history;

	public final int typeID;

	public RegionTypeHistory(CacheStatic cache, int regionID, int typeID) {
		cachestatic = cache;
		this.typeID = typeID;
		history = cachestatic.markets.history(regionID, typeID).sorted((a, b) -> b.date.compareTo(a.date));
	}

	private static Stream<R_get_markets_region_id_history> withinDays(List<R_get_markets_region_id_history> list,
			int days) {
		String lowerBound = daysAgo(days);
		return list.stream().filter(h -> h.date.compareTo(lowerBound) >= 0);
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

	// limited history over a max given number of days.

	@RequiredArgsConstructor
	public class LimitedHistory {

		public final int days;

		/**
		 *
		 * @return the list of day history, limited.
		 */
		@Getter(lazy = true)
		private final ListHolder<R_get_markets_region_id_history> data = limitData(days);

		/**
		 *
		 * the average sale value over the limit.
		 */
		@Getter(lazy = true)
		private final DoubleHolder average = getData().mapDouble(
				l -> l.stream().mapToDouble(h -> h.average * h.volume).sum() / l.stream().mapToLong(h -> h.volume).sum());

		/**
		 *
		 * the sale volume (quantity of items sold) over the limit.
		 */
		@Getter(lazy = true)
		private final LongHolder volume = getData().mapLong(l -> l.stream().mapToLong(h -> h.volume).sum());

		/**
		 *
		 * the sale value over the limit.
		 */
		@Getter(lazy = true)
		private final DoubleHolder totalValue = getData()
		.mapDouble(l -> l.stream().mapToDouble(h -> h.average * h.volume).sum());

		//

		@Getter(lazy = true)
		private final ListHolder<Long> sortedVolumes = makeSortedVolumes();

		/** get the list of volumes over the limit, sorted by volume descending */
		public ListHolder<Long> makeSortedVolumes() {
			return getData().toList(l -> {
				List<Long> list = l.stream().map(h -> h.volume)
						// reverse sort for long to have by volume DECREASING
						.sorted((l1, l2) -> Long.compare(l2, l1))
						.collect(Collectors.toList());
				// fill missing days with 0s
				while (list.size() < days) {
					list.add(0l);
				}
				return list;
			});
		}

		// best percentile volume

		/** cached offsetpct => volume */
		private HashMap<Integer, LongHolder> cachedBestVolumes = new HashMap<>();

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
		public LongHolder getBestVolume(int offsetPct) {
			LongHolder ret = cachedBestVolumes.get(offsetPct);
			if (ret == null) {
				ListHolder<Long> volumes = getSortedVolumes();
				ret = LockWatchDog.BARKER.syncExecute(cachedBestVolumes, () -> {
					LongHolder ret2 = cachedBestVolumes.get(offsetPct);
					if (ret2 == null) {
						ret2 = volumes.mapLong(l -> {
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
		private HashMap<Integer, ObjHolder<Long>> cachedBestSO = new HashMap<>();

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
		public ObjHolder<Long> getBestSO(int offsetCnt) {
			ObjHolder<Long> ret = cachedBestSO.get(offsetCnt);
			if (ret == null) {
				ret = LockWatchDog.BARKER.syncExecute(cachedBestSO, () -> {
					ObjHolder<Long> ret2 = cachedBestSO.get(offsetCnt);
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

		/**
		 *
		 * @param percentile
		 *          a percentile of daily highest price to remove
		 * @return the highest daily price after percentile % . eg if the highest
		 *         prices for last days are 3,10,15,9, the prices are orders
		 *         decreasing : 15,10,9,3 and the first % of this is removed. the
		 *         percentile is then 25, the first quarter is removed and the
		 *         highest is returned, here 10
		 */
		public double maxPrice(int percentile) {
			// highest prices per day, ordered decreasing.
			double[] orderedPrices = getData().get().stream().map(sales -> sales.highest)
					.sorted((d1, d2) -> -Double.compare(d1, d2)).mapToDouble(d -> d).toArray();
			int index = (int) Math.ceil(1.0 * (days + 1) * percentile / 100);
			if (index < orderedPrices.length) {
				return orderedPrices[index];
			}
			return 0;
		}

	}

	/**
	 * limit to max days. If max days is 1, then will limit to today
	 *
	 * @param maxDays
	 *          number of days to limit, including today
	 * @return a new observable list
	 */
	private ListHolder<R_get_markets_region_id_history> limitData(int maxDays) {
		ListHolderImpl<R_get_markets_region_id_history> ret = new ListHolderImpl<>();
		history.follow((l) -> {
			List<R_get_markets_region_id_history> list = withinDays(l, maxDays).collect(Collectors.toList());
			ret.set(list);
		}, ret);
		return ret;
	}

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

	public ListHolder<R_get_markets_region_id_history> history() {
		return history;
	}

	public List<R_get_markets_region_id_history> limit(String minDate, String maxDate) {
		return history.get().stream().filter(h -> h.date.compareTo(minDate) >= 0 && h.date.compareTo(maxDate) <= 0)
				.collect(Collectors.toList());
	}

	public List<R_get_markets_region_id_history> limit(LocalDate minDate, LocalDate maxDate) {
		return limit(minDate.format(DateTimeFormatter.ISO_LOCAL_DATE), maxDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

}
