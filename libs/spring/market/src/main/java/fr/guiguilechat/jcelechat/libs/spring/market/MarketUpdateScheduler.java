package fr.guiguilechat.jcelechat.libs.spring.market;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.market.contract.RegionContract;
import fr.guiguilechat.jcelechat.libs.spring.market.contract.RegionContractService;
import fr.guiguilechat.jcelechat.libs.spring.market.contract.RegionContractUpdateService;
import fr.guiguilechat.jcelechat.libs.spring.market.history.HistoryReq;
import fr.guiguilechat.jcelechat.libs.spring.market.history.HistoryReqService;
import fr.guiguilechat.jcelechat.libs.spring.market.history.HistoryUpdateService;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.ObservedRegionService;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.RegionMarketUpdateService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.status.ESIStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketUpdateScheduler {

	final private ESIStatusService esiStatusService;

	final private HistoryReqService hrService;

	final private HistoryUpdateService huService;

	final private ObservedRegionService orService;

	private final RegionContractService regionContractService;

	final private RegionContractUpdateService regionContractUpdateService;

	final private RegionMarketUpdateService mService;

	// markets

	@Value("${market.updater.skip:false}")
	private boolean skipMarketUpdate;

	@Scheduled(fixedRateString = "${market.updater.fetchperiod:120000}", initialDelayString = "${market.updater.fetchdelay:20000}")
	public void updateMarket() {
		if (skipMarketUpdate) {
			return;
		}
		long startMs = System.currentTimeMillis();
		log.info("updating markets");
		List<ObservedRegion> active = orService.listActiveMarket();
		Map<ObservedRegion, CompletableFuture<Void>> futures = active.stream()
				.collect(Collectors.toMap(r -> r, r -> mService.updateMarketLines(r).orTimeout(3, TimeUnit.MINUTES)));
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

	// histories

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
		int availErrors = esiStatusService.availErrors();
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

	// base contracts info

	@Value("${contracts.updater.skip:false}")
	private boolean skipContractsUpdate;

	@Scheduled(fixedRateString = "${contracts.updater.fetchperiod:900000}", initialDelayString = "${contracts.updater.fetchdelay:20000}")
	public void updateContracts() {
		if (skipContractsUpdate) {
			return;
		}
		long startMs = System.currentTimeMillis();
		log.info("updating contracts");
		List<ObservedRegion> active = orService.listActiveContracts();
		Map<ObservedRegion, CompletableFuture<Void>> futures = active.stream()
				.collect(Collectors.toMap(r -> r,
						r -> regionContractUpdateService.updateContractLines(r).orTimeout(3, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching contracts for region " + f.getKey().getRegionId(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + active.size() + " contract regions in " + (int) Math.ceil(0.001 * (endMs - startMs)) + "s");
	}

	// contracts items infos

	@Value("${contracts.items.updater.skip:false}")
	private boolean skipContractsItemsUpdate;

	@Value("${contracts.items.updater.availmult:3}")
	private int availMult;

	@Scheduled(fixedRateString = "${contracts.items.updater.fetchperiod:10000}", initialDelayString = "${contracts.items.updater.fetchdelay:30000}")
	public void updateContractsItems() {
		if (skipContractsItemsUpdate) {
			return;
		}
		long startMs = System.currentTimeMillis();
		int limit = availMult * esiStatusService.availErrors();
		List<RegionContract> toFetch = regionContractService.nextFetch();
		if (toFetch.isEmpty()) {
			return;
		}
		log.info("updating contracts items");
		Map<RegionContract, CompletableFuture<Void>> futures = toFetch.stream()
				.limit(limit)
				.collect(Collectors.toMap(r -> r,
						r -> regionContractUpdateService.updateContractItems(r).orTimeout(3, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching items for contractId : " + f.getKey().getContractId(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + futures.size() + " contracts items in " + (int) Math.ceil(0.001 * (endMs - startMs))
				+ "s, remaining " + regionContractService.countMissingFetch());
	}

}
