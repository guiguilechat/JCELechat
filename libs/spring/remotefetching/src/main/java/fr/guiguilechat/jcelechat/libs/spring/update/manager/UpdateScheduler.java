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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically call services that update local resources.
 * <p>
 * default configuration (yaml) is
 *
 * <pre>
jcesi.manager:
  default.skip: true # true : disable all updaters that are not explicitly skip=false
                     # false: enable all updaters that are not explicitly skip=true
  delay: 1000 # initial delay before starting
  period: 1000 # delay between the end of an update and the start of the next
  skip: false # set to true to not trigger the updaters
  updatedelay: 30 # when no more service to update, how long to wait for the next update loop
 * </pre>
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
public class UpdateScheduler {

	@Lazy
	private final Optional<List<EntityUpdater>> fetchedServices;

	//
	// those are mostly used for debuging and stopping
	//

	public interface IRemoteResourceUpdateListener {

		default void onNoUpdateRemain() {

		}
	}

	private final Optional<List<IRemoteResourceUpdateListener>> listeners;

	//
	// fetch management
	//

	@Value("${jcesi.manager.skip:false}")
	private boolean skip;

	/**
	 * should we skip services that don't have their skip value set ?
	 */
	@Value("${jcesi.manager.default.skip:true}")
	private boolean defaultSkip;

	@Value("${jcesi.manager.updateddelay:30}")
	private int updatedDelay;

	/** when not null, time before which we skip the update cycles */
	private Instant nextUpdate = Instant.now();

	private Map<String, Instant> fetcherNameToNextUpdate = new HashMap<>();

	@Scheduled(fixedRateString = "${jcesi.manager.period:1000}", initialDelayString = "${jcesi.manager.delay:1000}")
	public void fetchResources() throws IOException {
		if (skip) {
			return;
		}
		Instant start = Instant.now();
		if (nextUpdate != null && start.isBefore(nextUpdate)) {
			return;
		}
		boolean remainService = false;
		List<EntityUpdater> registeredServices = fetchedServices.orElse(List.of());
		List<EntityUpdater> activeServices = registeredServices.stream().filter(s -> !shouldSkip(s))
		    .toList();
		List<EntityUpdater> readyServices = activeServices.stream().filter(s -> {
			Instant next = fetcherNameToNextUpdate.get(s.serviceName());
			return next == null || !next.isAfter(start);
		}).toList();
		log.debug("updating : {} ready / {} active / {} registered", readyServices.size(), activeServices.size(),
		    registeredServices.size());
		for (EntityUpdater s : readyServices) {
			log.debug("updating {}", s.serviceName());
			boolean remain = s.fetch();
			remainService |= remain;
			Instant serviceNextUpdate = s.nextUpdate(remain, start);
			fetcherNameToNextUpdate.put(s.serviceName(), serviceNextUpdate);
			log.debug(" updated {} remaining={} next={}", s.serviceName(), remain, format(serviceNextUpdate));
		}

		if (!remainService) {
			if (listeners.isPresent()) {
				for (IRemoteResourceUpdateListener l : listeners.get()) {
					l.onNoUpdateRemain();
				}
			}
			nextUpdate = Instant.now().plusSeconds(updatedDelay);
		} else {
			nextUpdate = null;
		}
	}

	protected String format(Instant instant) {
		if (instant == null) {
			return "null";
		}
		return instant.atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS)
		    .format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	/**
	 * tells whether an existing updater service should be skipped
	 *
	 * @param updater
	 * @return
	 */
	public boolean shouldSkip(EntityUpdater updater) {
		return Optional.ofNullable(updater.getUpdate().getSkip()).orElse(defaultSkip);
	}

}
