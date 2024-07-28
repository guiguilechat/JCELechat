package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically updates the data for service handling remote resource caching.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RemoteResourceUpdaterService {

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

	@Value("${esi.updater.skip:false}")
	private boolean skip;

	@Value("${esi.updater.updateddelay:60}")
	private int updatedDelay;

	private Instant nextUpdate = Instant.now();

	@Scheduled(fixedRateString = "${esi.updater.period:1000}", initialDelayString = "${esi.updater.delay:1000}")
	public void fetchResources() throws IOException {
		if (Instant.now().isBefore(nextUpdate)) {
			return;
		}
		boolean remain = false;
		if (!skip && fetchedServices.isPresent()) {
			List<IEntityUpdater> registeredServices = fetchedServices.get();
			List<IEntityUpdater> activeServices = registeredServices.stream().filter(s -> !skipService(s))
			    .toList();
			log.debug("updating services : {} active / {} registered", activeServices.size(), registeredServices.size());
			remain = activeServices.stream().filter(IEntityUpdater::fetch).count() > 0;
		} else {
			log.debug("skipping update of {} services with skip={}",
			    fetchedServices.isPresent() ? fetchedServices.get().size() : 0, skip);
		}

		if (!remain) {
			if ( listeners.isPresent()) {
				for (IRemoteResourceUpdateListener l : listeners.get()) {
					l.onNoUpdateRemain();
				}
			}
			nextUpdate = Instant.now().plusSeconds(updatedDelay);
		} else {
			nextUpdate = Instant.now().plusSeconds(1);
		}
	}

	@Value("${esi.updater.default.skip:false}")
	private boolean defaultSkip;

	protected boolean skipService(IEntityUpdater updater) {
		return Optional.ofNullable(updater.getUpdate().getSkip()).orElse(defaultSkip);
	}

}
