package fr.guiguilechat.jcelechat.libs.spring.mer.updater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.mer.MERFetcher;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerUpdateScheduler {

	final private MerUpdateService merUpdateService;

	@Value("${mer.updater.fetchsize:10}")
	private int maxFetches;

	@Value("${mer.updater.skip:false}")
	private boolean skip;

	@Value("${mer.updater.timeoutsec:300}")
	private int timeout_seconds;

	@Scheduled(fixedRateString = "${mer.updater.fetchperiod:600000}", initialDelayString = "${mer.updater.fetchdelay:20000}")
	public void updateMers() {
		if (skip) {
			return;
		}
		long startMs = System.currentTimeMillis();
		List<MERFetch> merFetches = new ArrayList<>(
				merUpdateService.nextFetches().stream().map(ld -> MERFetcher.INSTANCE.forDate(ld))
						.filter(mf -> mf.url() != null).toList());
		Random rand = new Random();
		while (merFetches.size() > maxFetches) {
			merFetches.remove(rand.nextInt(merFetches.size()));
		}
		log.info("updating " + merFetches.size() + " mers out of "
				+ merUpdateService.nextFetches().stream().map(ld -> MERFetcher.INSTANCE.forDate(ld))
						.filter(mf -> mf.url() != null).count());
		Map<MERFetch, CompletableFuture<Void>> futures = merFetches.stream()
				.collect(Collectors.toMap(mf -> mf,
						mf -> merUpdateService.loadMer(mf).orTimeout(timeout_seconds, TimeUnit.SECONDS)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching mer for date " + f.getKey(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + merFetches.size() + " mers in " + (int) Math.ceil(0.001 * (endMs - startMs))
				+ "s , remaining " + merUpdateService.nextFetches().stream().map(ld -> MERFetcher.INSTANCE.forDate(ld))
						.filter(mf -> mf.url() != null).count());
	}

}
