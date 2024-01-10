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

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarketUpdateScheduler {

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
				.collect(Collectors.toMap(r -> r, r -> mService.updateLines(r).orTimeout(2, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("fetch timeout for region " + f.getKey().getRegionId());
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + active.size() + " markets in " + (int) Math.ceil(0.001 * (endMs - startMs)) + "s");
	}
}
