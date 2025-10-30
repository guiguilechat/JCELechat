package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.YamlCache;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Cached;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Errored;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Success;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeResult.Status;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties(prefix = "sde.fetcher")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Order(1)
public class SdeUpdater implements EntityUpdater {

	private final SdeResultService service;

	private final CacheManager cacheManager;

	private final Optional<List<SdeListener>> updateListeners;

	@Getter
	private UpdateConfig update = new UpdateConfig();

	@Getter
	// setter required to set from properties
	@Setter
	private boolean force = false;

	/** moment after which we actually try to fetch */
	private Instant nextFetch = null;

	/**
	 * request to force the next update, typically because our model changed.
	 */
	public void forceNext() {
		force = true;
		nextFetch = null;
	}

	@Override
	@Transactional
	public boolean fetch() {
		Instant startDate = Instant.now();
		if (nextFetch != null && nextFetch.isAfter(startDate)) {
			return false;
		}
		log.debug("updating SDE, force={}", force);
		SdeResult ur = SdeResult.builder().startedDate(startDate).build();
		SdeResult lastSuccess = service.findLastSuccess();
		String lastRelease = lastSuccess != null ? lastSuccess.getReleaseDate() : null;
		DLResult fetch = YamlCache.INSTANCE.dl(force ? null : lastRelease);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		switch (fetch) {
		case Cached _:
			ur.setStatus(Status.CACHED);
			break;
		case Errored e:
			ur.setStatus(Status.FAIL);
			ur.setError(e.error().getMessage());
			break;
		case Success s:
			ur.setStatus(Status.SUCCESS);
			ur.setBuildNumber(s.meta().buildNumber);
			ur.setReleaseDate(s.meta().releaseDate);
			try {
				updateNewSDE(s, lastRelease);
			} catch (Exception e) {
				ur.setStatus(Status.FAIL);
				log.error("while updating sde", e);
				ur.setError(e.getMessage());
			}
			break;
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());

		service.save(ur);
		log.debug(" sde udpate result is {}, previous was {}", ur, lastRelease);

		force = false;
		nextFetch = startDate.plusSeconds(getUpdate().getDelay());
		return ur.getStatus() != Status.SUCCESS;
	}

	protected void updateNewSDE(Success s, String lastRelease) throws IOException {
		log.info("update SDE build={} released={} previous={}, builNumber={}", s.meta().buildNumber,
				s.meta().releaseDate, lastRelease);
		long startUpdate = System.currentTimeMillis();
		if (updateListeners.isPresent()) {
			List<SdeListener> listeners = updateListeners.get();
			listeners.forEach(SdeListener::beforeSdeUpdate);
			ZipInputStream zip = s.zipputSteam();
			ZipEntry zipentry;
			while ((zipentry = zip.getNextEntry()) != null) {
				if (!zipentry.isDirectory()) {
					String name = zipentry.getName();
					Supplier<InputStream> sup = new DuplicatingInputStream(zip);
					listeners.forEach(l -> l.onSdeFile(name, sup));
				}
			}
			listeners.stream().flatMap(l -> l.listSDECaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			listeners.forEach(SdeListener::afterSdeUpdate);
		}
		log.info(" finished updating SDE DB in {} ms", System.currentTimeMillis() - startUpdate);
	}


}
