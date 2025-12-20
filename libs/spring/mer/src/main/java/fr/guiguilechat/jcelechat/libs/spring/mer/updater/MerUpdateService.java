package fr.guiguilechat.jcelechat.libs.spring.mer.updater;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.mer.MER;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.Kill;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerUpdateService {

	final private KillService killService;

	final private LoadedMerService loadedMerService;

	final private CacheManager cacheManager;

	public interface MerUpdateListener {

		default List<String> listMerCaches() {
			return List.of();
		}

		default void onMerUpdate() {

		}

	}

	private final List<MerUpdateListener> updateListeners;

	@Async
	@Transactional
	public CompletableFuture<Void> loadMer(MERFetch merfetch) {
		Instant start = Instant.now();
		LocalDate localdate = merfetch.date();
		if (merfetch.data() != null) {
			LoadedMer loadedMer = loadedMerService.save(
					LoadedMer.builder()
							.periodMonth(localdate)
							.startLoad(start)
							.url(merfetch.url())
							.build());
			MER mer = new MER(merfetch).load();
			killService.saveAll(mer.getKillDumpEntries().stream()
					.map(kde -> Kill.from(
							kde,
							loadedMer))
					.toList());
			loadedMer.setEndLoad(Instant.now());
			loadedMerService.save(loadedMer);

			updateListeners.stream().flatMap(l -> l.listMerCaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			updateListeners.stream().forEach(MerUpdateListener::onMerUpdate);

			log.info(" loaded MER for date " + localdate);
		} else if (merfetch.error() == null) {
			log.debug("" + localdate + " url=" + merfetch.url());
		} else if (merfetch.error() instanceof FileNotFoundException) {
			log.debug("{} url= {} no file", localdate, merfetch.url());
		} else {
			log.debug("" + localdate + " url=" + merfetch.url(), merfetch.error());
		}
		return CompletableFuture.completedFuture(null);
	}

	public List<LocalDate> nextFetches() {
		Set<LocalDate> loaded = loadedMerService.loadedDates();
		return MERFetcher.streamPossibleMERs().filter(ld -> !loaded.contains(ld)).toList();
	}

}
