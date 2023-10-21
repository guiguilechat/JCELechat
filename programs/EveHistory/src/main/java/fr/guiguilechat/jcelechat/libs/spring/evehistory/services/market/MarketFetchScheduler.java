package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.MarketFetchResultService.TwoFetchResults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
/**
 * A different service class to call other service using async methods. If the
 * async call is in the same class, then async is ignored.
 */
public class MarketFetchScheduler {

	@Autowired
	private MarketFetchResultService resultService;

	@Autowired
	MarketFetchService fetchService;

	@Autowired
	private MarketObservedRegionService observedService;

	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelay = 5 * 1000)
	public void fetchMarkets() {
		List<MarketFetchResult> last_fetch = resultService.findLastResults();
		Map<Integer, String> existingEtags = last_fetch.stream()
				.filter(mfr -> mfr.getEtag() != null)
				.collect(Collectors.toMap(MarketFetchResult::getRegionId, MarketFetchResult::getEtag));
		List<Integer> requiredRegionIds = observedService.observedRegions();
		for (int regionId : requiredRegionIds) {
			fetchService.fetchMarket(regionId, existingEtags.get(regionId));
		}
	}

	@Scheduled(fixedRate = 6 * 60 * 1000, initialDelay = 30 * 1000)
	public void analyzeResults() {
		List<TwoFetchResults> toAnalyze = resultService.findAnalyzable();
		log.info("analyzing " + toAnalyze.size() + " results");
		for (TwoFetchResults r : toAnalyze) {
			resultService.analyze(r.first(), r.second());
		}
	}
}
