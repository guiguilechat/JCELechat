package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketFetchResultRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchResultService {

	@Autowired
	private MarketFetchResultRepository repo;

	@Autowired
	private MarketFetchLineService lineService;

	public MarketFetchResult save(MarketFetchResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.save(entity);
	}

	public List<MarketFetchResult> findLastResults() {
		List<MarketFetchResult> ret = repo.findLastResults();
		return ret;
	}

	record TwoFetchResults(MarketFetchResult first, MarketFetchResult second) {
	};

	public List<TwoFetchResults> findAnalyzable() {
		return repo.findAnalyzable().stream()
				.map(arr -> new TwoFetchResults((MarketFetchResult) arr[0], (MarketFetchResult) arr[1])).toList();
	}

	/**
	 * analyze a market fetch result. Update all orders in that result to represent
	 * if they are newly created, if they are the last time this order appear, if
	 * the price of volume has been updated since previous result. Then remove all
	 * the orders which are none of those, and update the result to have the number
	 * of retained orders.
	 *
	 */
	@Transactional(propagation = Propagation.NESTED, isolation = Isolation.SERIALIZABLE)
	public void analyze(MarketFetchResult result, MarketFetchResult follow) {
		lineService.analyzeLines(result, follow);
		result.setLinesUpdated(lineService.countOrders(result));
		result.setAnalyzed(true);
		repo.save(result);
	}

	@Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 1 * 60 * 1000)
	public void purgeOldEntries() {
		// keep failed at least one day, cached at least one hour
		List<MarketFetchResult> cachedExpired = repo
				.findByCreatedDateLessThanAndCachedTrue(Instant.now().minus(Duration.ofHours(1)));
		repo.deleteAllInBatch(cachedExpired);
		log.info("purged " + cachedExpired.size() + " cached results");

		List<MarketFetchResult> failedExpired = repo
				.findByCreatedDateLessThanAndFailedTrue(Instant.now().minus(Duration.ofDays(1)));
		repo.deleteAllInBatch(failedExpired);
		log.info("purged " + failedExpired.size() + " failed results");
		List<MarketFetchResult> removeEtag = repo
				.findByCreatedDateLessThanAndEtagNotNull(Instant.now().minus(Duration.ofHours(1)));

		// keep etags at least one hour
		for (MarketFetchResult r : removeEtag) {
			r.setEtag(null);
		}
		repo.saveAllAndFlush(removeEtag);
		log.info("removed " + removeEtag.size() + " etags");
	}

}
