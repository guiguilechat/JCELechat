package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

	@Autowired
	private MarketOrderService marketOrderService;

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
	@Async
	@Transactional(propagation = Propagation.NESTED, isolation = Isolation.SERIALIZABLE)
	public CompletableFuture<Void> analyze(MarketFetchResult result, MarketFetchResult follow) {
		long start = System.currentTimeMillis();
		lineService.analyzeLines(result, follow);
		long postLinesUpdate = System.currentTimeMillis();
		marketOrderService.updateLastLine(result);
		long postOrderUpdate = System.currentTimeMillis();
		result.setLinesUpdated(lineService.countOrders(result));
		result.setAnalyzed(true);
		repo.save(result);
		long postSave = System.currentTimeMillis();
		log.info("analyzed result " + result.getId() + " followed by " + (follow == null ? "null" : follow.getId())
				+ " in " + (postSave - start) + "ms : lines=" + (postLinesUpdate - start) + " orders="
				+ (postOrderUpdate - postLinesUpdate)
				+ " save=" + (postSave - postOrderUpdate));
		return CompletableFuture.completedFuture(null);
	}

	@Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 1 * 60 * 1000)
	public void purgeOldEntries() {
		// keep cached at least 1d
		List<MarketFetchResult> cachedExpired = repo
				.findByCreatedDateLessThanAndCachedTrue(Instant.now().minus(Duration.ofDays(1)));
		repo.deleteAllInBatch(cachedExpired);
		log.info("purged " + cachedExpired.size() + " cached results");

		// keep failed at least 7d
		List<MarketFetchResult> failedExpired = repo
				.findByCreatedDateLessThanAndFailedTrue(Instant.now().minus(Duration.ofDays(7)));
		repo.deleteAllInBatch(failedExpired);
		log.info("purged " + failedExpired.size() + " failed results");

		// keep etags at least 6 hour
		List<MarketFetchResult> removeEtag = repo
				.findByCreatedDateLessThanAndEtagNotNull(Instant.now().minus(Duration.ofHours(6)));
		for (MarketFetchResult r : removeEtag) {
			r.setEtag(null);
		}
		repo.saveAllAndFlush(removeEtag);
		log.info("removed " + removeEtag.size() + " etags");
	}

}
