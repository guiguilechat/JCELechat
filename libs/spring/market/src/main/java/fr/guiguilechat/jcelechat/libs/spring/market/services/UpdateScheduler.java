package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateScheduler {

	@Autowired
	private HistoryReqService hrService;

	@Autowired
	private HistoryUpdateService huService;

	@Autowired
	private MarketUpdateService mService;

	@Autowired
	private ObservedRegionService orService;

	@Scheduled(fixedRateString = "${market.updater.fetchperiod:120000}", initialDelayString = "${market.updater.fetchdelay:60000}")
	public void updateMarket() {
		long startMs = System.currentTimeMillis();
		log.info("updating markets");
		List<ObservedRegion> active = orService.listActive();
		Map<ObservedRegion, CompletableFuture<Void>> futures = active.stream()
				.collect(Collectors.toMap(r -> r, r -> mService.updateLines(r).orTimeout(3, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching market for region " + f.getKey().getRegionId(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + active.size() + " markets in " + (int) Math.ceil(0.001 * (endMs - startMs)) + "s");
	}

	@Scheduled(fixedRateString = "${market.history.fetchperiod:60000}", initialDelayString = "${market.history.fetchdelay:6000}")
	public void updateHistory() {
		long startMs = System.currentTimeMillis();
		List<HistoryReq> requests = hrService.listNextRequests();
		long remain = hrService.countRemainingRequests();
		if (requests.isEmpty()) {
			log.debug("no region history to update");
			return;
		}
		long listMs = System.currentTimeMillis();
		log.info("updating " + requests.size() + " region histories, out of total " + remain + " remaining");
		Map<HistoryReq, CompletableFuture<Void>> futures = requests.stream()
				.collect(Collectors.toMap(hr -> hr, hr -> huService.update(hr).orTimeout(2, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching history for region=" + f.getKey().getRegionId() + " type=" + f.getKey().getTypeId(),
						e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + requests.size() + " histories"
				+ " list=" + (listMs - startMs) + "ms"
				+ " update=" + (endMs - listMs) + "ms");
	}
}
