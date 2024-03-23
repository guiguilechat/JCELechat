package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateScheduler {

	final private CacheManager cacheManager;

	final private CorporationOfferUpdateService corporationOfferUpdateService;

	final private LPStoreCorporationService lpStoreCorporationService;

	@Value("${npc.corporations.skip:false}")
	private boolean skipCorporations;

	@Scheduled(fixedRateString = "${npc.corporations.fetchperiod:600000}", initialDelayString = "${npc.corporations.fetchdelay:20000}")
	public void updateCorporations() {
		if (skipCorporations) {
			return;
		}
		long startMs = System.currentTimeMillis();
		Requested<Integer[]> ret = lpStoreCorporationService.fetchCorporations();
		long endMs = System.currentTimeMillis();
		if (ret.isOk()) {
			for (String cacheName : LPStoreCorporationService.CORPORATIONS_CACHES) {
				cacheManager.getCache(cacheName).clear();
			}
			long cacheEndMs = System.currentTimeMillis();
			log.info(" updated corporations to " + ret.getOK().length + " active in"
					+ " change=" + (int) Math.ceil(0.001 * (endMs - startMs)) + "s"
					+ " cachevict=" + (int) Math.ceil(0.001 * (cacheEndMs - endMs)));
		} else if (ret.getResponseCode() == 304) {
//
		} else {
			log.error("while fetching NPC corporations " + ret.getResponseCode());
		}
	}

	@Value("${npc.lpoffers.skip:false}")
	private boolean skipOffers;

	@Scheduled(fixedRateString = "${npc.lpoffers.fetchperiod:600000}", initialDelayString = "${npc.lpoffers.fetchdelay:30000}")
	public void updateOffers() {
		if (skipOffers) {
			log.info("skip");
			return;
		}
		long startMs = System.currentTimeMillis();
		List<LPStoreCorporation> fetches = lpStoreCorporationService.nextFetch();
		if (fetches == null || fetches.isEmpty()) {
			return;
		}
		log.info("updating the offers of " + fetches.size() + " corporations");
		long listMs = System.currentTimeMillis();
		Map<LPStoreCorporation, CompletableFuture<Void>> futures = fetches.stream()
				.collect(
						Collectors.toMap(hr -> hr,
								lpcorp -> corporationOfferUpdateService.updateOffers(lpcorp).orTimeout(30, TimeUnit.SECONDS)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error(
						"while fetching offers for corporation=" + f.getKey().getCorporationId(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated offers of " + fetches.size() + " corporations"
				+ " list=" + (listMs - startMs) + "ms"
				+ " update=" + (endMs - listMs) + "ms");
	}

}
