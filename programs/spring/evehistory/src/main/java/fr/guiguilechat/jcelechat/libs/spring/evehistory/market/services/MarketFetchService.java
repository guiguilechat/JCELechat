package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.services;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketFetchService {

	final private MarketFetchLineService lineService;

	final private ObservedRegionService regionService;

	final private MarketFetchResultService resultService;

	private ForkJoinPool highParrallelPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 10);

	/**
	 * create the data for a fetch, without any access to the DB. Used for test.
	 *
	 * @param regionId id of the region to fetch the data for
	 * @param lastEtag etag of the last fetch, if any.
	 * @return fetch result and list of corresponding lines already mapped to that
	 *           result, if any.
	 */
	Map.Entry<MarketFetchResult, List<MarketFetchLine>> fetchMarketNoDB(ObservedRegion region, String lastEtag) {
		long startTime = System.currentTimeMillis();
		boolean failed = false;
		boolean noChange = false;
		Set<String> errors = new HashSet<>();
		Integer pages = null;
		List<R_get_markets_region_id_orders> fetchedLines = new ArrayList<>();

		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_markets_region_id_orders[]> firstResult = ESIStatic.INSTANCE.get_markets_orders(null, null,
				region.getRegionId(), null, properties);
		long firstPageTime = System.currentTimeMillis();
		String newEtag = firstResult.getETag();
		int responseCode = firstResult.getResponseCode();
		switch (responseCode) {
			case 200:
				pages = firstResult.getNbPages();
			break;
			case 304:
				noChange = true;
			break;
			default:
				failed = true;
				errors.add(firstResult.getError());
		}
		Long allPagesTime = null;
		if (!failed && !noChange) {
			fetchedLines.addAll(Arrays.asList(firstResult.getOK()));
			boolean expireMismatch = false;
			if (pages != null && pages > 1) {
				IntStream is = IntStream.rangeClosed(2, pages);
				List<Requested<R_get_markets_region_id_orders[]>> nextPages = null;
				try {
					nextPages = highParrallelPool.submit(() -> is.parallel()
							.mapToObj(p -> ESIStatic.INSTANCE.get_markets_orders(null, p, region.getRegionId(), null, null))
							.toList())
							.get();
				} catch (InterruptedException | ExecutionException e) {
					failed = true;
					log.error("while fetching next page for region " + region.getRegionId(), e);
					errors.add(e.getMessage());
				}
				allPagesTime = System.currentTimeMillis();
				if (!failed) {
					for (Requested<R_get_markets_region_id_orders[]> pageResult : nextPages) {
						if (!pageResult.isOk()) {
							failed = true;
							responseCode = pageResult.getResponseCode();
							errors.add(pageResult.getError());
						} else if (!Objects.equals(firstResult.getExpires(), pageResult.getExpires())) {
							log.error("mismatching expire for " + pageResult.getURL());
							expireMismatch = true;
						} else {
							fetchedLines.addAll(Arrays.asList(pageResult.getOK()));
						}
					}
					if (expireMismatch) {
						failed = true;
						errors.add("Expires mismatch");
					}
				}
			}
		}
		MarketFetchResult fetchResult = MarketFetchResult.builder()
				.etag(failed || noChange ? null : newEtag)
				.lastModified(failed || noChange ? null : ESITools.headerInstant(firstResult.getLastModified()))
				.linesFetched(failed || noChange ? null : fetchedLines.size())
				.pagesFetched(failed || noChange ? null : pages)
				.region(region)
				.responseCode(responseCode)
				.status(failed ? STATUS.FAIL : noChange ? STATUS.CACHED : STATUS.FETCHING)
				.build();
		if (failed) {
			fetchResult.error(errors);
		}
		List<MarketFetchLine> lines = fetchedLines.stream()
				.map(order -> MarketFetchLine.builder()
						.order(order)
						.fetchResult(fetchResult)
						.build())
				.toList();
		long endTime = System.currentTimeMillis();
		fetchResult.setDurationFetchMS(endTime - startTime);
		log.info(" fetched for region(" + region.getRegionId() + ") result="
				+ (failed ? "fail:" + errors : noChange ? "noChange" : fetchedLines.size() + "lines") + " in "
				+ (endTime - startTime) + " ms(firstPage=" + (firstPageTime - startTime) + (allPagesTime == null ? ""
						: " next" + pages + "Pages=" + (allPagesTime - firstPageTime) + " process=" + (endTime - allPagesTime))
				+ ")");
		return new AbstractMap.SimpleImmutableEntry<>(fetchResult, lines);
	}

	/**
	 * request the orders for a region and save it.
	 *
	 * @see #fetchMarketNoDB(ObservedRegion, String) for implementation
	 */
	@Async
	@Transactional
	public CompletableFuture<Void> fetchMarket(ObservedRegion region, MarketFetchResult previousResult) {
		try {
			log.trace(" + start fetch market regionId=" + region.getRegionId());
			Entry<MarketFetchResult, List<MarketFetchLine>> e = fetchMarketNoDB(region,
					previousResult == null ? null : previousResult.getEtag());
			MarketFetchResult fetchResult = resultService.save(e.getKey());
			if (fetchResult.getStatus() == STATUS.FETCHING) {
				lineService.saveAll(e.getValue());
				region.setLastFetchSuccess(fetchResult);
				regionService.save(region);
				if (previousResult != null) {
					previousResult.setNextResult(fetchResult);
					resultService.save(previousResult);
				}
				fetchResult.setStatus(STATUS.FETCHED);
				fetchResult.setPreviousResult(previousResult);
				resultService.save(fetchResult);
			}
			log.trace(" - finished fetch market regionId=" + region.getRegionId());
		} catch (Exception e) {
			log.error("while fetching market for region " + region.getRegionId(), e);
		}
		return CompletableFuture.completedFuture(null);
	}

}
