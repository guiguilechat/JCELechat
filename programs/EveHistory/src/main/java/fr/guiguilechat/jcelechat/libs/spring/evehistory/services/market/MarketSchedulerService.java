package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.ObservedRegion;
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
public class MarketSchedulerService {

	@Autowired
	MarketAnalyzeService analyzeService;

	@Autowired
	private MarketFetchResultService mfrService;

	@Autowired
	private MarketFetchService fetchService;

	@Autowired
	private MarketOrderService orderService;

	@Autowired
	private ObservedRegionService regionsService;

	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelayString = "${evehistory.market.fetchdelay:30000}")
	public void fetchMarkets() {
		long start = System.currentTimeMillis();
		Map<ObservedRegion, MarketFetchResult> requests = regionsService.listRequests();
		log.info("fetching markets for " + requests.size() + " regions");
		Map<ObservedRegion, CompletableFuture<Void>> futures = requests.entrySet().stream()
				.collect(Collectors.toMap(
						Entry::getKey,
						e -> fetchService.fetchMarket(e.getKey(), e.getValue()).orTimeout(2, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("fetch timeout for region " + f.getKey().getRegionId());
			}
		});
		long end = System.currentTimeMillis();
		log.info("fetched " + futures.size() + " markets in " + (int) Math.ceil(0.001 * (end - start)) + "s");
	}

	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelayString = "${evehistory.market.ordersdelay:60000}")
	public void createOrders() {
		long startTime = System.currentTimeMillis();
		List<MarketFetchResult> results = mfrService.listOnStatusWithPreviousAfter(STATUS.FETCHED);
		log.info("creating orders for " + results.size() + " results");
		for (MarketFetchResult result : results) {
			long startResult = System.currentTimeMillis();
			log.info(" creating orders result=" + result.getId());
			orderService.createMissingOrders(result);
			long endResult = System.currentTimeMillis();
			result.setDurationCreateOrderMS(endResult - startResult);
			result.setStatus(STATUS.ORDERSEXIST);
			mfrService.save(result);
		}
		long endTime = System.currentTimeMillis();
		log.info(
				"created orders for " + results.size() + " results in " + (int) Math.ceil(0.001 * (endTime - startTime)) + "s");
	}


	@Scheduled(fixedRate = 2 * 60 * 1000, initialDelayString = "${evehistory.market.analyzedelay:90000}")
	public void analyzeLines() {
		long start = System.currentTimeMillis();
		List<TwoFetchResults> mfrs = mfrService.listToAnalyze();
		long listed = System.currentTimeMillis();
		log.info("analyzing the lines of " + mfrs.size() + " fetch results");
		Map<MarketFetchResult, CompletableFuture<Void>> futures = mfrs.stream()
				.collect(Collectors.toMap(
						TwoFetchResults::first,
						m -> analyzeService.analyzeLines(m.first(), m.second()).orTimeout(4, TimeUnit.MINUTES)));
		long dispatched = System.currentTimeMillis();
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("analyze timeout for marketfetchresult " + f.getKey().getId());
			}
		});
		long end = System.currentTimeMillis();
		if (!mfrs.isEmpty()) {
			log.info("analyzed " + mfrs.size() + " lines in " + (int) Math.ceil(0.001 * (end - start)) + "s (list="
					+ (listed - start) + "ms dispatch=" + (dispatched - listed) + "ms join=" + (end - dispatched) + "ms)");
		}
	}


	@Scheduled(fixedRate = 10 * 60 * 1000, initialDelayString = "${evehistory.market.purgedelay:600000}")
	public void purgeOldEntries() {
		mfrService.purgeOldEntries();
	}
}
