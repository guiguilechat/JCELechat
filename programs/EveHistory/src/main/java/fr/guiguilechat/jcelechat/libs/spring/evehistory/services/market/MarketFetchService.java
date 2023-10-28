package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchService {

	/**
	 * convert a date in {@link DateTimeFormatter#RFC_1123_DATE_TIME} format
	 */
	public static Instant convertDate1123(String formated) {
		return ZonedDateTime.parse(formated, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant();
	}


	@Autowired
	private MarketFetchLineService lineService;

	@Autowired
	private MarketOrderService orderService;

	@Autowired
	private MarketFetchResultService resultService;

	private ForkJoinPool highParrallelPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 10);

	@Async
	public CompletableFuture<Void> fetchMarket(int regionId, String lastEtag) {
		long start = System.currentTimeMillis();
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
				regionId, null, properties);
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
							.mapToObj(p -> ESIStatic.INSTANCE.get_markets_orders(null, p, regionId, null, null))
							.toList())
							.get();
				} catch (InterruptedException | ExecutionException e) {
					failed = true;
					log.error("while fetching next page for region " + regionId, e);
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
		MarketFetchResult fetchResult = resultService.save(MarketFetchResult.builder()
				.errors(errors.isEmpty() ? null : errors.toString())
				.etag(failed || noChange ? null : newEtag)
				.failed(failed)
				.cached(noChange)
				.lastModified(failed || noChange ? null : convertDate1123(firstResult.getLastModified()))
				.linesFetched(failed || noChange ? null : fetchedLines.size())
				.pagesFetched(failed || noChange ? null : pages)
				.regionId(regionId)
				.responseCode(responseCode)
				.build());

		if (!failed && !noChange) {
			lineService.saveAll(fetchedLines.stream()
					.map(order -> MarketFetchLine.builder()
							.order(order)
							.fetchResult(fetchResult)
							.build())
					.toList());
			orderService.createMissingOrders(fetchResult);
		}
		long endSave = System.currentTimeMillis();
		log.info("region id=" + regionId + " result="
				+ (failed ? "fail:" + errors : noChange ? "noChange" : fetchedLines.size() + "lines") + " times(ms)= total:"
				+ (endSave - start) + " firstPage:" + (firstPageTime - start) + (allPagesTime == null ? ""
						: " nextPages:" + (allPagesTime - firstPageTime) + " save:" + (endSave - allPagesTime)));
		return CompletableFuture.completedFuture(null);
	}

}
