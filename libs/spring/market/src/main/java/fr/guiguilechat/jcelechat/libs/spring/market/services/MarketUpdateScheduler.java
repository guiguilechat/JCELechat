package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarketUpdateScheduler {

	@Autowired
	private HistoryReqService hrService;

	@Autowired
	private HistoryUpdateService huService;

	@Autowired
	private MarketUpdateService mService;

	@Autowired
	private ObservedRegionService orService;

	@Value("${market.updater.skip:false}")
	private boolean skipMarketUpdate;

	@Scheduled(fixedRateString = "${market.updater.fetchperiod:120000}", initialDelayString = "${market.updater.fetchdelay:20000}")
	public void updateMarket() {
		if (skipMarketUpdate) {
			return;
		}
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

	@Value("${market.history.skip:false}")
	private boolean skipHistoryUpdate;

	@Scheduled(fixedRateString = "${market.history.fetchperiod:60000}", initialDelayString = "${market.history.fetchdelay:20000}")
	public void updateHistory() {
		if (skipHistoryUpdate) {
		return;
	}
		long startMs = System.currentTimeMillis();
		List<HistoryReq> requests = hrService.listNextRequests();
		long remain = hrService.countRemainingRequests();
		if (requests.isEmpty()) {
			log.debug("no region history to update");
			return;
		}
		long listMs = System.currentTimeMillis();
		int availErrors = availErrors();
		if (availErrors > 0) {
			int maxRequests = Math.min(Math.max((int) (0.01 * availErrors * hrService.getQueriesPerFetch()), availErrors),
					requests.size());
			log.info("updating " + Math.min(requests.size(), maxRequests)
					+ (maxRequests < requests.size() ? "(limited by errors)" : "") + " region histories, out of total " + remain
					+ " remaining");
			Map<HistoryReq, CompletableFuture<Void>> futures = requests.stream().limit(maxRequests)
					.collect(Collectors.toMap(hr -> hr, hr -> huService.update(hr).orTimeout(30, TimeUnit.SECONDS)));
			futures.entrySet().forEach(f -> {
				try {
					f.getValue().join();
				} catch (CompletionException e) {
					log.error(
							"while fetching history for region=" + f.getKey().getRegionId() + " type=" + f.getKey().getTypeId(), e);
				}
			});
			long endMs = System.currentTimeMillis();
			log.info(" updated " + requests.size() + " histories"
					+ " list=" + (listMs - startMs) + "ms"
					+ " update=" + (endMs - listMs) + "ms");

		}
	}

	public static int availErrors() {
		Requested<R_get_status> esiAccessReq = ESIStatic.INSTANCE.get_status(null);
		if (esiAccessReq.isOk()) {
			R_get_status esiAccess = esiAccessReq.getOK();
			if (!esiAccess.vip) {
				return esiAccessReq.getRemainingErrors();
			}
			log.info(" ESI is in VIP mode, skipping");
			return 0;
		}
		log.info(" could not get ESI status, skipping");
		return 0;
	}
}
