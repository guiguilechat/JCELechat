package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketFetchLineRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchLineService {

	@Autowired
	private MarketFetchLineRepository repo;

	@Autowired
	private MarketOrderService orderService;

	/**
	 * update the values of a line that are computed from existing values, and clean
	 * useless ones.
	 *
	 * @param line
	 */
	protected void updatevalues(MarketFetchLine line) {
		if (line.getIssuedDate() == null) {
			if (line.getOrder().issued != null) {
				line.setIssuedDate(ESITools.convertDate(line.getOrder().issued).toInstant());
			}
			line.getOrder().issued = null;
		}
		if (line.getOrder().volume_remain == 0 || line.getOrder().duration == 0) {
			line.setInvalid(true);
		}
	}

	public List<MarketFetchLine> saveAll(List<MarketFetchLine> list) {
		for (MarketFetchLine mfl : list) {
			updatevalues(mfl);
		}
		return repo.saveAll(list);
	}

	public void save(MarketFetchLine line) {
		updatevalues(line);
		repo.save(line);
	}

	/**
	 * analyze the lines linked to a given market fetch result. To make sense, that
	 * result must be followed by a successful one.
	 * The lines are checked to find a previous one and older one for same order id,
	 * and if previous one exists if the volume or price has changed.
	 * Then the lines that meet none of those criterias are deleted, while those
	 * which do are udpated.
	 */
	public void analyzeLines(MarketFetchResult result, MarketFetchResult follow) {
		long start = System.currentTimeMillis();
		int nbUpdated = repo.analyzePreviousLines(result.getId(), result.getLastModified());
		long postUpdated = System.currentTimeMillis();
		int nbRemoval = repo.analyzeRemovalLines(result.getId(), result.getLastModified(), follow.getId(),
				follow.getLastModified());
		long postRemoval = System.currentTimeMillis();
		int nbDeleted = repo.removeNoEffectLines(result);
		orderService.updateLastLine(result);
		long end = System.currentTimeMillis();
		log.info(
				"analyze of marketfetch=" + result.getId() + " regionid=" + result.getRegionId() + " with orders= " + nbUpdated
						+ "updated, " + nbRemoval + "removal, " + nbDeleted + "deleted ; time= "
						+ (end - start)
						+ "ms : updated=" + (postUpdated - start) + " removal="
						+ (postRemoval - postUpdated) + " delete=" + (end - postRemoval));
	}

	public void analyzeLines_old(MarketFetchResult result, MarketFetchResult follow) {
		long start = System.currentTimeMillis();
		List<MarketFetchLine> updated = new ArrayList<>();
		List<MarketFetchLine> deleted = new ArrayList<>();
		Map<MarketFetchLine, MarketFetchLine> result2Previous = new HashMap<>();
		List<Object[]> changes = repo.listOrderChanges(result);
		long fetched = System.currentTimeMillis();
		int created = 0;
		int last = 0;
		int changed = 0;
		for (Object[] o : changes) {
			MarketFetchLine before = (MarketFetchLine) o[0],
					line = (MarketFetchLine) o[1],
					after = (MarketFetchLine) o[2];

			line.setSoldTo(result.getLastModified());
			if (before != null) {
				result2Previous.put(line, before);
				line.setPriceChg(before.getOrder().price != line.getOrder().price);
				int sold = before.getOrder().volume_remain - line.getOrder().volume_remain;
				line.setSold(sold);
				line.setSoldFrom(before.getSoldTo().isAfter(line.getIssuedDate())
						? before.getSoldTo()
								: line.getIssuedDate());
				if (sold > 0 || line.isPriceChg()) {
					changed++;
				}
			} else {
				line.setCreation(true);
				line.setSold(line.getOrder().volume_total - line.getOrder().volume_remain);
				line.setSoldFrom(line.getIssuedDate());
				created++;
			}
			if (after == null) {
				line.setRemoval(true);
				line.setRemovalTo(follow.getLastModified());
				line.setRemovalFrom(result.getLastModified());
				Instant eol = line.getIssuedDate().plus(Duration.ofDays(line.getOrder().duration));
				// use !isBefore to accept equality . Same for !isAfter
				if (!eol.isBefore(line.getRemovalFrom()) && !eol.isAfter(line.getRemovalTo())) {
					line.setEol(true);
					line.setRemovalDate(eol);
				} else {
					line.setEol(false);
					line.setRemovalDate(
							Instant.ofEpochMilli((line.getRemovalFrom().toEpochMilli() + line.getRemovalTo().toEpochMilli()) / 2));
				}
				last++;
			}
			if (line.isRemoval() || line.isCreation() || line.isPriceChg() || line.getSold() > 0) {
				updated.add(line);
			} else {
				deleted.add(line);
			}
		}
		// set the previous to a non-deleted one
		for (MarketFetchLine line : updated) {
			MarketFetchLine previous = result2Previous.get(line);
			while (previous != null && deleted.contains(previous)) {
				previous = result2Previous.get(previous);
			}
			line.setPreviousLine(previous);
		}
		long analyzed = System.currentTimeMillis();
		saveAll(updated);
		repo.deleteAll(deleted);
		long end = System.currentTimeMillis();
		log.info(
				"analyze of marketfetch=" + result.getId() + " regionid=" + result.getRegionId() + " in " + (end - start)
				+ "ms (fetch=" + (fetched - start) + " analyze=" + (analyzed - fetched) + " save=" + (end - analyzed)
				+ ") : created:" + created + " changed:" + changed + " last:" + last + " delete:" + deleted.size());
	}

	/**
	 * @return the number of orders linked to the result
	 */
	public int countOrders(MarketFetchResult result) {
		return repo.countByFetchResult(result);
	}

	public List<MarketFetchLine> listUpdates(int regionId, int typeId, Instant from, Instant to) {
		List<MarketFetchLine> ret = repo.listPriceChanges(regionId, typeId, from, to);
// log.info("fetching changes for type " + typeId + " region " + regionId + "
// from " + from + " to " + to
// + " : retrieved " + ret.size() + " items");
		return ret;
	}

	record AggregatedPrice(Instant sale_day,
			Number bo_saleVol, double bo_saleTotValue, Double bo_saleAvgPrice,
			Number so_saleVol, double so_saleTotValue, Double so_saleAvgPrice,
			Number bo_createdNb, Number bo_createdVol, Number so_createdNb, Number so_createdVol,
			Number bo_endedVol, Number bo_endedAvgPrice, Number so_endedVol, Number so_endedAvgPrice) {
		public AggregatedPrice(Object[] line) {
			this((Instant) line[0],
					(Number) line[1], (double) line[2], (Double) line[3],
					(Number) line[4], (double) line[5], (Double) line[6],
					(Number) line[7], (Number) line[8], (Number) line[9], (Number) line[10],
					(Number) line[11], (Number) line[12], (Number) line[13], (Number) line[14]
			);
		}
	}

	public List<AggregatedPrice> listDailyOnTypeRegionFromTo(int regionId, int typeId, Instant fromDate, Instant toDate) {
		return repo.listDailyOnTypeRegionFromTo(regionId, typeId, fromDate, toDate).stream().map(AggregatedPrice::new)
				.toList();
	}

	record RegionErrors(Number regionId, Number vol0, Number dur0, Number total) {
		public RegionErrors(Object[] line) {
			this((Number) line[0], (Number) line[1], (Number) line[2], (Number) line[3]);
		}
	}

	public List<RegionErrors> listDailyLineErrorsGroupeByRegion() {
		return repo.listDailyLineErrorsGroupeByRegion().stream().map(RegionErrors::new).toList();
	}

	record LineHourStats(Instant modifiedHour, Number createdNb, Number priceChgNb, Number soldNb, Number removalNb,
			Number total) {
		public LineHourStats(Object[] line) {
			this((Instant) line[0], (Number) line[1], (Number) line[2], (Number) line[3], (Number) line[4], (Number) line[5]);
		}
	}

	public List<LineHourStats> getLinesHourStatsForRegion(Number regionId, Instant dateFrom, Instant dateTo) {
		return repo.getLinesHourStatsForRegion(regionId, dateFrom, dateTo).stream().map(LineHourStats::new).toList();
	}

}
