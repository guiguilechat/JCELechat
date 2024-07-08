package fr.guiguilechat.jcelechat.libs.spring.trade.regional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryReqService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionMarketUpdateService {

	final private CacheManager cacheManager;

	final private HistoryReqService htService;

	final private RegionLineService rlService;

	final private ObservedRegionService orService;

	private ForkJoinPool highParrallelPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 10);

	// market management

	public static interface MarketUpdateListener {

		public default List<String> listMarketCaches(int regionId) {
			return List.of();
		}

		public default void onMarketUpdate(ObservedRegion region, List<RegionLine> newLines) {

		}

	}

	private final List<MarketUpdateListener> updateMarketListeners;

	@Async
	@Transactional
	public CompletableFuture<Void> updateMarketLines(ObservedRegion region) {
		long startMs = System.currentTimeMillis();
		List<RegionLine> newLines = fetchMarketNoDB(region);
		long retrievedMs = System.currentTimeMillis();
		if (newLines != null) {
			rlService.clearRegion(region);
			long postClearMs = System.currentTimeMillis();
			rlService.saveAll(newLines);
			long postSaveMs = System.currentTimeMillis();
			orService.save(region);
			long saveMs = System.currentTimeMillis();
			List<Integer> missingHistoryTypeIds = htService.findMissingTypeIds(region);
			long fetchHistoryTypes = System.currentTimeMillis();
			List<HistoryReq> hts = missingHistoryTypeIds.stream()
					.map(typeId -> HistoryReq.builder().typeId(typeId).regionId(region.getRegionId()).nextFetch(Instant.now())
							.build())
					.toList();
			if (!hts.isEmpty()) {
				log.debug("  adding " + hts.size() + " types to monitor history for region " + region.getRegionId());
			}
			htService.saveAll(hts);
			long insertedHistoryTypes = System.currentTimeMillis();
			log.debug(" updated market for region " + region.getRegionId()
					+ " for " + newLines.size() + " lines"
					+ " fetch=" + (int) Math.ceil(0.001 * (retrievedMs - startMs)) + "s"
					+ " clearlines=" + (int) Math.ceil(0.001 * (postClearMs - retrievedMs)) + "s"
					+ " savelines=" + (int) Math.ceil(0.001 * (postSaveMs - postClearMs)) + "s"
					+ " save=" + (int) Math.ceil(0.001 * (saveMs - postSaveMs)) + "s"
					+ " fetchHT=" + (int) Math.ceil(0.001 * (fetchHistoryTypes - saveMs)) + "s"
					+ " saveHT=" + (int) Math.ceil(0.001 * (insertedHistoryTypes - fetchHistoryTypes)) + "s");

			updateMarketListeners.stream().flatMap(l -> l.listMarketCaches(region.getRegionId()).stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			updateMarketListeners.stream().forEach(l -> l.onMarketUpdate(region, newLines));
		}

		return CompletableFuture.completedFuture(null);
	}

	/**
	 * create the data for a fetch, without any access to the DB. Used for test.
	 */
	List<RegionLine> fetchMarketNoDB(ObservedRegion region) {
		long startTime = System.currentTimeMillis();
		boolean failed = false;
		boolean noChange = false;
		Set<String> errors = new HashSet<>();
		Integer pages = null;
		List<R_get_markets_region_id_orders> fetchedLines = new ArrayList<>();

		Map<String, String> properties = new HashMap<>();
		String lastEtag = region.getLastMarketEtag();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_markets_region_id_orders[]> firstResult = ESIRawPublic.INSTANCE.get_markets_orders(null, null,
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
							.mapToObj(p -> ESIRawPublic.INSTANCE.get_markets_orders(null, p, region.getRegionId(), null, null))
							.toList())
							.get();
				} catch (InterruptedException | ExecutionException e) {
					failed = true;
					log.error("while fetching next page for market of region " + region.getRegionId(), e);
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
		if (failed || noChange) {
			return null;
		}
		List<RegionLine> lines = fetchedLines.stream()
				.map(order -> RegionLine.of(region, order))
				.filter(RegionLine::isValid)
				.toList();
		long endTime = System.currentTimeMillis();
		log.debug(" fetched market for region(" + region.getRegionId() + ") result="
				+ (failed ? "fail:" + errors : noChange ? "noChange" : fetchedLines.size() + "lines") + " in "
				+ (endTime - startTime) + " ms(firstPage=" + (firstPageTime - startTime) + (allPagesTime == null ? ""
						: " next" + pages + "Pages=" + (allPagesTime - firstPageTime) + " process=" + (endTime - allPagesTime))
				+ ")");
		region.setLastMarketEtag(newEtag);
		return lines;
	}

}
