package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.services;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.repositories.MarketFetchResultRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchResultService {

	@Autowired
	private MarketFetchResultRepository repo;

	public MarketFetchResult save(MarketFetchResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

	public List<MarketFetchResult> list() {
		return repo.findAll();
	}

	public List<MarketFetchResult> listByStatus(STATUS status) {
		return repo.findByStatus(status);
	}

	public List<MarketFetchResult> listOnStatusWithPreviousAfter(STATUS status) {
		return repo.listWithStatusAndPreviousNullOrStatusIn(status, status.after());
	}

	record TwoFetchResults(MarketFetchResult first, MarketFetchResult second) {
		TwoFetchResults(Object[] arr) {
			this((MarketFetchResult) arr[0], (MarketFetchResult) arr[1]);
		}
	};

	public List<TwoFetchResults> listToAnalyze() {
		return repo
				.listWithStatusAndPreviousNullOrStatusInAndNextStatusIn(STATUS.ORDERSEXIST, STATUS.ORDERSEXIST.after(),
						STATUS.FETCHING.after())
				.stream()
				.map(TwoFetchResults::new).toList();
	}

	record LineOrder(MarketFetchLine line, MarketOrder order) {
		public LineOrder(Object[] arr) {
			this((MarketFetchLine) arr[0], (MarketOrder) arr[1]);
		}
	};

	public List<LineOrder> listLinesAndOrders(MarketFetchResult mfr){
		return repo.listLinesOrders(mfr).stream().map(LineOrder::new).toList();
	}

	public void purgeOldEntries() {
		// keep cached at least 1d
		List<MarketFetchResult> cachedExpired = repo
				.findByCreatedDateLessThanAndStatus(Instant.now().minus(Duration.ofDays(1)), STATUS.CACHED);
		repo.deleteAllInBatch(cachedExpired);
		log.info("purged " + cachedExpired.size() + " cached results");

		// keep failed at least 7d
		List<MarketFetchResult> failedExpired = repo
				.findByCreatedDateLessThanAndStatus(Instant.now().minus(Duration.ofDays(7)), STATUS.FAIL);
		repo.deleteAllInBatch(failedExpired);
		log.info("purged " + failedExpired.size() + " failed results");
	}

	public List<MarketFetchResult> listInRegionAndStatus(ObservedRegion region, STATUS status) {
		return repo.findByRegionAndStatusOrderByCreatedDateDesc(region, status);
	}

}
