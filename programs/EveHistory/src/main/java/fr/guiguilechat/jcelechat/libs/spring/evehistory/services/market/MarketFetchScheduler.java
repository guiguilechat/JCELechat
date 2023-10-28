package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.MarketFetchResultService.TwoFetchResults;
import lombok.extern.slf4j.Slf4j;

/**
 * schedule the retrieval and analyzis of market data
 * <p>
 * The retrieval operation lists the regions requested, as well as their last
 * Etag received if any. Then it async get all of the corresponding resources
 * :<br />
 * Once the list of market orders associated to a region are fetched, a new
 * {@link MarketFetchResult} is created with the result of the operation ; then
 * the corresponding market lines, if any, are safety checked and inserted in
 * {@link MarketFetchLine}. If data are invalid, the line is marked as such
 * {@link MarketFetchLine#setInvalid(boolean)}. <br />
 * Once inserted, the {@link MarketOrder} that do not exist yet for those
 * lines's order id are created.
 * </p>
 * <p>
 * The analyzis operation fetches all the fetchresults that are not analyzed
 * yet and for which there exist another later fetchresult on the region. Then
 * for each of those (result, following) couple, it updates the lines of the
 * former:<br />
 * each line is matched to its previous one using the
 * {@link MarketOrder#getLastLine()}. Those which can't are thus
 * {@link MarketFetchLine#setCreation(boolean)} true
 * </p>
 * <p>
 * It is a different service class that calls other services' async methods : if
 * the async call is in the same class, then async is ignored.
 * </p>
 */
@Service
@Slf4j
public class MarketFetchScheduler {

	@Autowired
	private MarketFetchResultService resultService;

	@Autowired
	MarketFetchService fetchService;

	@Autowired
	private MarketObservedRegionService observedService;

	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelay = 30 * 1000)
	public void fetchMarkets() {
		List<MarketFetchResult> last_fetch = resultService.findLastResults();
		Map<Integer, String> existingEtags = last_fetch.stream()
				.filter(mfr -> mfr.getEtag() != null)
				.collect(Collectors.toMap(MarketFetchResult::getRegionId, MarketFetchResult::getEtag));
		List<Integer> requiredRegionIds = observedService.observedRegions();

		List<CompletableFuture<Void>> futures = requiredRegionIds.stream()
				.map(regionId -> fetchService.fetchMarket(regionId, existingEtags.get(regionId))).toList();
		futures.stream().forEach(CompletableFuture::join);
	}

	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelay = 90 * 1000)
	public void analyzeResults() {
		List<TwoFetchResults> toAnalyze = resultService.findAnalyzable();
		log.info("analyzing " + toAnalyze.size() + " results");
		// we can't analyze several fetch of same region at the same time. So we make
		// several pass, with each pass having at most one result to analyze per region
// List<Map<Integer, TwoFetchResults>> analysePasses = new ArrayList<>();
// for (TwoFetchResults toAdd : toAnalyze) {
// for (Map<Integer, TwoFetchResults> m : analysePasses) {
// if (m.putIfAbsent(toAdd.first().getRegionId(), toAdd) == null) {
// toAdd = null;
// break;
// }
// }
// if (toAdd != null) {
// HashMap<Integer, TwoFetchResults> m = new HashMap<>();
// m.put(toAdd.first().getRegionId(), toAdd);
// analysePasses.add(m);
// }
// }
// for (Map<Integer, TwoFetchResults> pass : analysePasses) {
// List<CompletableFuture<Void>> awaiting = pass.values().stream()
// .map(tfr -> resultService.analyze(tfr.first(), tfr.second())).toList();
// awaiting.stream().forEach(CompletableFuture::join);
// }

		for (TwoFetchResults r : toAnalyze) {
			resultService.analyze(r.first(), r.second()).join();
		}
	}
}
