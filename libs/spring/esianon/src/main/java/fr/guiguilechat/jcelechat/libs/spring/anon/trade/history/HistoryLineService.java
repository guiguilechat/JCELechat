package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.AggregatedTypeHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class HistoryLineService {

	final private HistoryLineRepository repo;

	@Lazy
	final private HistoryReqService hrService;

	public List<HistoryLine> saveAll(Iterable<HistoryLine> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public HistoryLine save(HistoryLine entity) {
		return repo.saveAndFlush(entity);
	}

	public List<HistoryLine> byRegionType(int regionId, int typeId) {
		return repo.findByFetchResourceRegionIdAndFetchResourceTypeId(regionId, typeId);
	}

	public List<HistoryLine> byReq(Iterable<HistoryReq> req) {
		return repo.findAllByFetchResourceIn(req);
	}

	public void delete(Iterable<HistoryLine> lines) {
		repo.deleteAll(lines);
	}

	/**
	 * prioritize the fetch of this type, and return the already known data.
	 *
	 * @param typeId
	 * @param from
	 * @return
	 */
	public List<AggregatedHL> byType(int typeId, Instant from) {
		hrService.prioritizeType(typeId);
		return repo.aggregated(typeId, from).stream().map(this::convert).toList();
	}

	protected AggregatedHL convert(Object[] line) {
		return new AggregatedHL(
		    ((Instant) line[0]).truncatedTo(ChronoUnit.DAYS),
				((Number) line[1]).longValue(),
				((Number) line[2]).doubleValue(),
				((Number) line[3]).doubleValue(),
				((Number) line[4]).doubleValue(),
				((Number) line[5]).intValue());
	}

	public Map<HistoryReq, Instant> findLastFetched(Iterable<HistoryReq> reqs) {
		return repo.findLastByReqIn(reqs).stream()
				.collect(Collectors.toMap(arr -> (HistoryReq) arr[0], arr -> (Instant) arr[1]));
	}

	//
	// highest sales since last X days
	//

	public List<AggregatedTypeHistory> aggregateHighestIskVolume(int days, int limit) {
		var now = Instant.now().truncatedTo(ChronoUnit.DAYS);
		var minDay = now.minus(days, ChronoUnit.DAYS);
		long start = System.currentTimeMillis();
		List<AggregatedTypeHistory> ret = repo.sortSalesByTotalValue(minDay, now, limit).stream()
				.map(arr -> {
					int typeId = ((Number)arr[2]).intValue();
					String typeName = (String) arr[3];
					double totalValue =  ((Number) arr[0]).doubleValue();
					long totalQuantity = ((Number) arr[1]).longValue();
					return new AggregatedTypeHistory(typeId, typeName, days,totalValue,
							totalQuantity);
				})
				.toList();
		long stop = System.currentTimeMillis();
		log.trace("fetched most sold over {} days in {} ms, returning {} records", days, stop - start, ret.size());
		return ret;
	}

	//
	// Analysis
	//

	static record DayWeight(double weight, double low, double lowQtty, double high, double highQtty) {

		public static DayWeight of(HistoryLine line, WeightStrategy weighter) {
			double weight = weighter.weight(line);
			if (line.getLowest() == line.getHighest()) {
				return new DayWeight(weight, line.getLowest(), line.getVolume(), 0.0, 0.0);
			}
			double nh = line.getVolume() * (line.getAverage() - line.getLowest())
					/ (line.getHighest() - line.getLowest());
			return new DayWeight(weight, line.getLowest(), line.getVolume() - nh, line.getHighest(), nh);
		}

	}

	/**
	 * give weights to history lines. Also total weight to take missing lines into
	 * account.
	 */
	public interface WeightStrategy {

		double totalWeight();

		double weight(HistoryLine line);

		static WeightStrategy of(String name) {
			return switch (name == null ? "" : name.toLowerCase()) {
			case "uni" -> HistoryLineService.uni(400);
			case "uni30" -> HistoryLineService.uni(30);
			case "uni365" -> HistoryLineService.uni(365);
			case "geo10" -> HistoryLineService.geo(10);
			default -> HistoryLineService.geo(10);
			};
		}

		static long daysBetween(Instant first, Instant second) {
			return ChronoUnit.DAYS.between(first, second);
		}

	}

	/**
	 * weight 1 for each daily entry. So total of weights of 365 days is 365, minus
	 * one as we don't consider today
	 */
	public static WeightStrategy uni(int maxDays) {
		return uni(maxDays, Instant.now().truncatedTo(ChronoUnit.DAYS));
	}

	static WeightStrategy uni(int maxDays, Instant refDate) {
		return new WeightStrategy() {

			@Override
			public double weight(HistoryLine line) {
				long days = WeightStrategy.daysBetween(line.getDate(), refDate);
				return days > 0 && days < maxDays ? 1 : 0;
			}

			@Override
			public double totalWeight() {
				return maxDays - 1;
			}

			@Override
			public String toString() {
				return "uni" + maxDays;
			}
		};
	}

	/**
	 * geometric weight with weight(n)=(1-1/totalWeight)^n,
	 * therefore sumweight=totalWeight for infinite history (more precisely
	 * totalWeight×( 1-(1-1/totalWeight)^364 ) for 365 data). <br />
	 * Then we remove today so actually totalWeight -1.
	 */
	public static WeightStrategy geo(int totalWeight) {
		return geo(totalWeight, Instant.now().truncatedTo(ChronoUnit.DAYS));
	}

	static WeightStrategy geo(int totalWeight, Instant refDate) {
		return new WeightStrategy() {

			@Override
			public double weight(HistoryLine line) {
				long days = WeightStrategy.daysBetween(line.getDate(), refDate);
				return days > 0 ? Math.pow(1.0 - 1.0 / totalWeight, days) : 0;
			}

			@Override
			public double totalWeight() {
				return totalWeight - 1;
			}

			@Override
			public String toString() {
				return "geo" + totalWeight;
			}
		};
	}

	@RequiredArgsConstructor
	public static class PriceVolumeAcc {
		public final double price;
		public double above = 0.0;
		public double below = 0.0;

		public PriceVolumeAcc accumulate(Iterable<DayWeight> dayWeights, double totalWeight) {
			for (DayWeight dw : dayWeights) {
				if (dw.low <= price) {
					below += dw.lowQtty * dw.weight / totalWeight;
				}
				if (dw.low >= price) {
					above += dw.lowQtty * dw.weight / totalWeight;
				}
				if (dw.high <= price) {
					below += dw.highQtty * dw.weight / totalWeight;
				}
				if (dw.high >= price) {
					above += dw.highQtty * dw.weight / totalWeight;
				}
			}
			return this;
		}
	}

	List<PriceVolumeAcc> groupPrices(double minPrice, double maxPrice, int nbSteps, List<DayWeight> dayWeights,
			double totalWeight) {
		double stepInc = 1.0 * (maxPrice - minPrice) / (nbSteps - 1);
		return IntStream.rangeClosed(0, nbSteps - 1).mapToDouble(step -> minPrice + stepInc * step)
				.mapToObj(PriceVolumeAcc::new)
				.map(pv -> pv.accumulate(dayWeights, totalWeight)).toList();
	}

	List<PriceVolumeAcc> groupPrices(List<HistoryLine> lines, WeightStrategy weighter, int nbSteps) {
		List<DayWeight> weights = new ArrayList<>();
		Double min = Double.POSITIVE_INFINITY;
		Double max = 0.0;
		for (HistoryLine hl : lines) {
			DayWeight dw = DayWeight.of(hl, weighter);
			if (dw.weight > 0.0) {
				min = Math.min(min, hl.getLowest());
				max = Math.max(max, hl.getHighest());
				weights.add(dw);
			}
		}
		return groupPrices(min, max, nbSteps, weights, weighter.totalWeight());
	}

	/**
	 * Group the prices following thresholds and weighter
	 *
	 * @param nbSteps must be >1
	 */
	public List<PriceVolumeAcc> groupPrices(int regionId, int typeId, WeightStrategy weighter, int nbSteps) {
		List<HistoryLine> lines = byRegionType(regionId, typeId);
		if (lines.isEmpty()) {
			return Collections.emptyList();
		}
		return groupPrices(lines, weighter, nbSteps);
	}

}
