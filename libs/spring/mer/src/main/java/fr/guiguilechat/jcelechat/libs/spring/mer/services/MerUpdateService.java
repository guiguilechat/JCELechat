package fr.guiguilechat.jcelechat.libs.spring.mer.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.mer.MER;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;
import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.mer.model.LoadedMer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MerUpdateService {

	@Autowired
	private KillService killService;
	@Autowired
	private LoadedMerService loadedMerService;

	@Async
	@Transactional
	public CompletableFuture<Void> loadMer(LocalDate localdate) {
		Instant start = Instant.now();
		MERFetch merfetch = MERFetcher.INSTANCE.forDate(localdate);
		if (merfetch.data() != null) {
			LoadedMer loadedmer = loadedMerService.save(
					LoadedMer.builder()
							.periodMonth(localdate)
							.startLoad(start)
							.build());
			MER mer = new MER(merfetch).load();
			killService.saveAll(mer.getKillDumpEntries().stream().map(kde -> Kill.from(kde, loadedmer)).toList());
			loadedmer.setEndLoad(Instant.now());
			loadedMerService.save(loadedmer);
			log.info(" loaded MER for date " + localdate);
		} else {
			if (merfetch.error() == null) {
				log.debug(""+localdate+" url="+merfetch.url());
			} else {
				log.debug(""+localdate+" url="+merfetch.url(), merfetch.error());
			}
		}
		return CompletableFuture.completedFuture(null);
	}

	public List<LocalDate> nextFetches() {
		Set<LocalDate> loaded = loadedMerService.loadedDates();
		return MERFetcher.streamPossibleMERs().filter(ld -> !loaded.contains(ld)).toList();
	}

}
