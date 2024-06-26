package fr.guiguilechat.jcelechat.libs.spring.market.history;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryUpdateService {

	final private CacheManager cacheManager;

	final private HistoryLineService hlService;

	final private HistoryReqService hrService;

	public static interface HistoryUpdateListener {

		public default List<String> listHistoryCaches() {
			return List.of();
		}

		public default void onHistoryUpdate(HistoryReq req) {

		}

	}

	private final List<HistoryUpdateListener> updateListeners;

	@Async
	@Transactional
	public CompletableFuture<Void> update(HistoryReq req) {
		long start = System.currentTimeMillis();
		List<HistoryLine> newLines = fetchHistoryNoDB(req);
		long postFetch = System.currentTimeMillis();
		hrService.save(req);
		if (newLines != null) {
			hlService.clearFor(req);
		}
		long postClear = System.currentTimeMillis();
		if (newLines != null) {
			hlService.saveAll(newLines);

			updateListeners.stream().flatMap(l -> l.listHistoryCaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			updateListeners.stream().forEach(l -> l.onHistoryUpdate(req));
		}
		long postSave = System.currentTimeMillis();
		log.debug(" fetched history region=" + req.getRegionId() + " type=" + req.getTypeId()
				+ " retrieved " + (newLines == null ? "null" : newLines.size()) + " lines"
				+ " fetch=" + (int) Math.ceil(0.001 * (postFetch - start)) + "s"
				+ " clear=" + (int) Math.ceil(0.001 * (postClear - postFetch)) + "s"
				+ " save=" + (int) Math.ceil(0.001 * (postSave - postClear)) + "s");
		return CompletableFuture.completedFuture(null);
	}

	/**
	 * fetch the history and update the requirements accordingly. No saving is done
	 * on the DB.
	 */
	public List<HistoryLine> fetchHistoryNoDB(HistoryReq req) {
		String lastEtag = req.getLastEtag();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		req.setLastFetch(Instant.now());
		Requested<R_get_markets_region_id_history[]> response = ESIRawPublic.INSTANCE.get_markets_history(req.getRegionId(),
				req.getTypeId(), properties);

		// add a random 0-2 hours (granularity minute) to the next fetch
		Instant nextFetchRan = Instant.now().plus((int) (Math.random() * 2 * 60), ChronoUnit.MINUTES);
		R_get_markets_region_id_history[] ret = null;
		switch (response.getResponseCode()) {
			case 200:
				ret = response.getOK();
				if (ret == null) {
					ret = new R_get_markets_region_id_history[] {};
				}
				Instant.ofEpochMilli(response.getExpiresS());
				log.debug(
						" retrieved " + ret.length + " history data for region=" + req.getRegionId() + " type=" + req.getTypeId());
				req.setLastEtag(response.getETag());
				Instant expires = response.getExpiresInstant();
				if (expires == null) {
					expires = nextFetchRan.plus(12, ChronoUnit.HOURS);
				}
				req.setNextFetch(expires);
			break;
			// got a cache hit
			case 304:
				req.setNextFetch(nextFetchRan.plus(10, ChronoUnit.HOURS));
			break;
			// 400 happens when the game lists invalid types on the market
			// 404 happens when the type became unpublished.
			case 400:
			case 404:
				req.setLastError(response.getError());
				req.setNextFetch(nextFetchRan.plus(1, ChronoUnit.DAYS));
				ret = new R_get_markets_region_id_history[] {};
			break;
			// 420 when too many errors
			// 503 happens at DT
			// 504 randomly
			default:
				req.setLastError(response.getError());
				log.error("when fetching history region=" + req.getRegionId() + " type=" + req.getTypeId() + " : "
						+ response.getError());
				req.setNextFetch(nextFetchRan.plus(1, ChronoUnit.HOURS));
		}
		if (ret == null) {
			log.debug("return null for region" + req.getRegionId() + " type=" + req.getTypeId() + " response code"
					+ response.getResponseCode());
			return null;
		}
		if (ret.length == 0) {
			return Collections.emptyList();
		}
		return Stream.of(ret).map(daily -> HistoryLine.of(req, daily)).toList();
	}

}
