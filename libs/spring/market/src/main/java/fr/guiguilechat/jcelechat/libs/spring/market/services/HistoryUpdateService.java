package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryLine;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryUpdateService {

	@Autowired
	private HistoryLineService hlService;

	@Autowired
	private HistoryReqService hrService;

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
		Requested<R_get_markets_region_id_history[]> response = ESIStatic.INSTANCE.get_markets_history(req.getRegionId(),
				req.getTypeId(), properties);
		R_get_markets_region_id_history[] ret = null;
		switch (response.getResponseCode()) {
			case 200:
				ret = response.getOK();
				if (ret == null) {
					ret=new R_get_markets_region_id_history[] {};
				}
				log.debug(
						" retrieved " + ret.length + " history data for region=" + req.getRegionId() + " type=" + req.getTypeId());
				req.setLastEtag(response.getETag());
				req.setNextFetch(Instant.now().plus(5, ChronoUnit.HOURS));
			break;
			// got a cache hit
			case 304:
				req.setNextFetch(Instant.now().plus(5, ChronoUnit.HOURS));
			break;
			// 400 happens when the game lists invalid types on the market
			// 404 happens when the type became unpublished.
			case 400:
			case 404:
				req.setLastError(response.getError());
				req.setNextFetch(Instant.now().plus(1, ChronoUnit.DAYS));
				ret = new R_get_markets_region_id_history[] {};
			break;
			default:
				req.setLastError(response.getError());
				log.error("when fetching history region=" + req.getRegionId() + " type=" + req.getTypeId() + " : "
						+ response.getError());
				req.setNextFetch(Instant.now().plus(5, ChronoUnit.MINUTES));
		}
		if (ret == null) {
			log.debug("return null for region" + req.getRegionId() + " type=" + req.getTypeId() + " response code"
					+ response.getResponseCode());
			return null;
		}
		if (ret.length == 0) {
			return Collections.emptyList();
		}
		return Stream.of(ret).map(daily -> HistoryLine.builder().daily(daily).req(req).build()).toList();
	}

}
