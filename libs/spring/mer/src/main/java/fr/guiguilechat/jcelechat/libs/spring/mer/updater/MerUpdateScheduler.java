package fr.guiguilechat.jcelechat.libs.spring.mer.updater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.mer.MERFetcher;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "mer.update")
@Getter
@Setter
public class MerUpdateScheduler {

	@Lazy
	final private MerUpdateService merUpdateService;

	private int max = 3;

	private boolean skip = false;

	private int timeoutsec = 300;

	@Scheduled(fixedRateString = "${mer.update.delayms:600000}", initialDelayString = "${mer.updater.waitms:20000}")
	public void updateMers() {
		if (skip) {
			return;
		}
		long startMs = System.currentTimeMillis();
		List<MERFetch> merFetches = new ArrayList<>(
				merUpdateService.nextFetches().stream().map(ld ->
						MERFetcher.INSTANCE.forDate(ld))
						.filter(mf -> mf.url() != null)
						.toList());
		Random rand = new Random();
		while (merFetches.size() > max) {
			merFetches.remove(rand.nextInt(merFetches.size()));
		}
		log.info("updating " + merFetches.size() + " mers out of "
				+ merUpdateService.nextFetches().stream().map(ld -> MERFetcher.INSTANCE.forDate(ld))
						.filter(mf -> mf.url() != null).count());
		Map<MERFetch, CompletableFuture<Void>> futures = merFetches.stream()
				.collect(Collectors.toMap(mf -> mf,
						mf -> merUpdateService.loadMer(mf).orTimeout(timeoutsec, TimeUnit.SECONDS)));
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
