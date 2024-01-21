package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MerUpdateScheduler {

	@Autowired
	private MerUpdateService merUpdateService;

	@Value("${mer.updater.fetchsize:10}")
	private int maxFetches;

	@Scheduled(fixedRateString = "${mer.updater.fetchperiod:600000}", initialDelayString = "${mer.updater.fetchdelay:20000}")
	public void updateMers() {
		long startMs = System.currentTimeMillis();
		List<LocalDate> localdates = new ArrayList<>(merUpdateService.nextFetches());
		Random rand = new Random();
		while (localdates.size() > maxFetches) {
			localdates.remove(rand.nextInt(localdates.size()));
		}
		log.info("updating " + localdates.size() + " mers out of " + merUpdateService.nextFetches().size());
		Map<LocalDate, CompletableFuture<Void>> futures = localdates.stream()
				.collect(Collectors.toMap(ld -> ld, ld -> merUpdateService.loadMer(ld).orTimeout(5, TimeUnit.MINUTES)));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error("while fetching mer for date " + f.getKey(), e);
			}
		});
		long endMs = System.currentTimeMillis();
		log.info(" updated " + localdates.size() + " mers in " + (int) Math.ceil(0.001 * (endMs - startMs))
				+ "s , remaining " + merUpdateService.nextFetches().size());
	}

}
