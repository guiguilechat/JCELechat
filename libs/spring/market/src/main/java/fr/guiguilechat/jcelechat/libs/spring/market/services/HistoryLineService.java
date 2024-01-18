package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryLine;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.repositories.HistoryLineRepository;
import lombok.RequiredArgsConstructor;

@Service
public class HistoryLineService {

	@Autowired
	private HistoryLineRepository repo;

	public void saveAll(Iterable<HistoryLine> entities) {
		for (HistoryLine entity : entities) {
			entity.affectFields();
		}
		repo.saveAll(entities);
	}

	public HistoryLine save(HistoryLine entity) {
		entity.affectFields();
		return repo.save(entity);
	}

	public void clearFor(HistoryReq req) {
		repo.deleteByReq(req);
	}

	public List<HistoryLine> byRegionType(int regionId, int typeId) {
		return repo.findByReqRegionIdAndReqTypeId(regionId, typeId);
	}

	//
	// analyzis
	//

	static record DayWeight(double weight, double low, double lowQtty, double high, double highQtty) {

		public static DayWeight of(HistoryLine line, WeightStrategy weighter) {
			double weight = weighter.weight(line);
			if (line.getDaily().lowest == line.getDaily().highest) {
				return new DayWeight(weight, line.getDaily().lowest, line.getDaily().volume, 0.0, 0.0);
			}
			double nh = line.getDaily().volume * (line.getDaily().average - line.getDaily().lowest)
					/ (line.getDaily().highest - line.getDaily().lowest);
			return new DayWeight(weight, line.getDaily().lowest, line.getDaily().volume - nh, line.getDaily().highest, nh);
		}

	}

	/**
	 * give weights to history lines. Also total weight to take missing lines into
	 * account.
	 */
	public static interface WeightStrategy {
		public double totalWeight();

		public double weight(HistoryLine line);

		public static WeightStrategy of(String name) {
			return switch (name == null ? "" : name.toLowerCase()) {
				case "uni" -> HistoryLineService.uni();
				case "uni30" -> HistoryLineService.uni30();
				case "geo10" -> HistoryLineService.geo10();
				default -> HistoryLineService.geo10();
			};
		}

		public static long daysBetween(Instant first, Instant second) {
			return ChronoUnit.DAYS.between(first, second);
		}

		default long days(Instant first, Instant second) {
			return daysBetween(first, second);
		}

	}

	/**
	 * weight 1 for each daily entry. So total of weights of 365 days is 365, minus
	 * one as we don't consider today
	 */
	public static WeightStrategy uni() {
		return new WeightStrategy() {

			Instant refDate = Instant.now().truncatedTo(ChronoUnit.DAYS);

			@Override
			public double weight(HistoryLine line) {
				long days = days(line.getDateDate(), refDate);
				return days > 0 ? 1 : 0;
			}

			@Override
			public double totalWeight() {
				return 365.0 - 1;
			}

			@Override
			public String toString() {
				return "uni";
			}
		};
	}

	/**
	 * weight 1 for each daily entry, limited to last 30 days. So total of weights
	 * is 30 -1 for today
	 */
	public static WeightStrategy uni30() {
		return new WeightStrategy() {

			Instant refDate = Instant.now().truncatedTo(ChronoUnit.DAYS);

			@Override
			public double weight(HistoryLine line) {
				long days = days(line.getDateDate(), refDate);
				return days > 0 && days < 30 ? 1 : 0;
			}

			@Override
			public double totalWeight() {
				return 29.0;
			}

			@Override
			public String toString() {
				return "uni30";
			}
		};
	}

	/**
	 * geometric weight with weight(n)=(9/10)^n, therefore sumweight=10 for infinite
	 * history (more precisely 10-0.9^364 for 365 data). Then we remove today so
	 * actually 9.
	 */
	public static WeightStrategy geo10() {
		return new WeightStrategy() {

			Instant refDate = Instant.now().truncatedTo(ChronoUnit.DAYS);

			@Override
			public double weight(HistoryLine line) {
				long days = days(line.getDateDate(), refDate);
				return days > 0 ? Math.pow(0.9, days) : 0;
			}

			@Override
			public double totalWeight() {
				return 10.0 - 1;
			}

			@Override
			public String toString() {
				return "geo10";
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
				min = Math.min(min, hl.getDaily().lowest);
				max = Math.max(max, hl.getDaily().highest);
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
