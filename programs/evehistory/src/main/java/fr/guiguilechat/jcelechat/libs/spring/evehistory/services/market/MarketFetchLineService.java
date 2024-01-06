package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketFetchLineRepository;

@Service
public class MarketFetchLineService {

	@Autowired
	private MarketFetchLineRepository repo;

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
	 * @return the number of orders linked to the result
	 */
	public int countOrders(MarketFetchResult result) {
		return repo.countByFetchResult(result);
	}

	public List<MarketFetchLine> listUpdates(int regionId, int typeId, Instant from, Instant to) {
		List<MarketFetchLine> ret = repo.listPriceChanges(regionId, typeId, from, to);
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

	public List<Object[]> listOrderChanges(MarketFetchResult result, MarketFetchResult follow) {
		return repo.listOrderChanges(result, follow);
	}

	public void deleteAll(List<MarketFetchLine> deletedLines) {
		repo.deleteAll(deletedLines);
	}

}
