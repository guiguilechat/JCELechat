package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically updates the data for service handling remote resource caching.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ResourceUpdaterService {

	private final Optional<List<IEntityUpdater>> fetchedServices;

	//
	// those are mostly used for debuging and stopping
	//

	public static interface IRemoteResourceUpdateListener {

		public default void onNoUpdateRemain() {

		}
	}

	private final Optional<List<IRemoteResourceUpdateListener>> listeners;

	//
	// fetch management
	//

	@Value("${jcesi.manager.skip:false}")
	private boolean skip;

	@Value("${jcesi.manager.updateddelay:60}")
	private int updatedDelay;

	private Instant nextUpdate = Instant.now();

	private Map<String, Instant> fetcherNameToNextUpdate = new HashMap<>();

	@Scheduled(fixedRateString = "${jcesi.manager.period:1000}", initialDelayString = "${jcesi.manager.delay:1000}")
	public void fetchResources() throws IOException {
		if (skip) {
			return;
		}
		Instant start = Instant.now();
		if (start.isBefore(nextUpdate)) {
			return;
		}
		boolean remainService = false;
		List<IEntityUpdater> registeredServices = fetchedServices.orElse(List.of());
		List<IEntityUpdater> activeServices = registeredServices.stream().filter(s -> !skipService(s))
		    .toList();
		List<IEntityUpdater> readyServices = activeServices.stream().filter(s -> {
			Instant next = fetcherNameToNextUpdate.get(s.fetcherName());
			return next == null || !next.isAfter(start);
		}).toList();
		log.debug("updating : {} ready / {} active / {} registered", readyServices.size(), activeServices.size(),
		    registeredServices.size());
		for (IEntityUpdater s : readyServices) {
			log.debug("updating {}", s.fetcherName());
			boolean remain = s.fetch();
			remainService |= remain;
			Instant serviceNextUpdate = s.nextUpdate(remain, start);
			fetcherNameToNextUpdate.put(s.fetcherName(), serviceNextUpdate);
			log.debug(" updated {} remaining={} next={}", s.fetcherName(), remain, format(serviceNextUpdate));
		}

		if (!remainService) {
			if (listeners.isPresent()) {
				for (IRemoteResourceUpdateListener l : listeners.get()) {
					l.onNoUpdateRemain();
				}
			}
			nextUpdate = Instant.now().plusSeconds(updatedDelay);
		} else {
			nextUpdate = Instant.now().plusSeconds(1);
		}
	}

	protected String format(Instant instant) {
		if (instant == null) {
			return "null";
		}
		return instant.atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS)
		    .format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	@Value("${jcesi.manager.default.skip:false}")
	private boolean defaultSkip;

	protected boolean skipService(IEntityUpdater updater) {
		return Optional.ofNullable(updater.getUpdate().getSkip()).orElse(defaultSkip);
	}

	@PostConstruct
	protected void debugConfig() {
		log.debug("configuration of {} registered updaters : ", fetchedServices.orElse(List.of()).size());
		for (IEntityUpdater l : fetchedServices.orElse(List.of())) {
			log.debug("{} ({}): {}", l.fetcherName(), skipService(l) ? "skiped" : "active", l.getUpdate());
		}
	}

}
