package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.YamlCache;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Cached;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Errored;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Success;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateResult.Status;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.IEntityUpdater;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "sde.fetcher")
public class SdeUpdateResultService implements IEntityUpdater {

	final private CacheManager cacheManager;

	final private SdeUpdateResultRepository repo;

	private final Optional<List<SdeUpdateListener>> updateListeners;

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

	protected SdeUpdateResult findLastSuccess() {
		return repo.findTop1ByStatusOrderByStartedDateDesc(Status.SUCCESS);
	}

	public SdeUpdateResult save(SdeUpdateResult entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public boolean fetch() {
		Instant startDate = Instant.now();
		if (nextFetch != null && nextFetch.isAfter(startDate)) {
			return false;
		}
		log.debug("updating SDE, force={}", force);
		SdeUpdateResult ur = SdeUpdateResult.builder().startedDate(startDate).build();
		SdeUpdateResult lastSuccess = findLastSuccess();
		DLResult fetch = YamlCache.INSTANCE.dl(!force && lastSuccess != null ? lastSuccess.getReleaseDate() : null);
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
				updateNewSDE(s);
			} catch (Exception e) {
				ur.setStatus(Status.FAIL);
				log.error("while updting sde", e);
				ur.setError(e.getMessage());
			}
			break;
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());

		save(ur);
		log.debug(" sde udpate result is {}", ur);

		force = false;
		nextFetch = startDate.plusSeconds(getUpdate().getDelay());
		return ur.getStatus() != Status.SUCCESS;
	}

	protected void updateNewSDE(Success s) throws IOException {
		log.info("update SDE released " + s.meta().releaseDate);
		long startUpdate = System.currentTimeMillis();
		if (updateListeners.isPresent()) {
			List<SdeUpdateListener> listeners = updateListeners.get();
			listeners.forEach(SdeUpdateListener::beforeSdeUpdate);
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
			listeners.forEach(SdeUpdateListener::afterSdeUpdate);
		}
		log.info(" finished updating SDE DB in {} ms", System.currentTimeMillis() - startUpdate);
	}

	/**
	 * update the DB from a new zip SDE to apply.
	 */
	protected void updateFromFile(File newZipFile) throws ZipException, IOException {
		log.info("start update SDE DB from " + newZipFile.getAbsolutePath());
		if (updateListeners.isPresent()) {
			List<SdeUpdateListener> listeners = updateListeners.get();
			listeners.forEach(SdeUpdateListener::beforeSdeUpdate);
			try (ZipFile zipFile = new ZipFile(newZipFile)) {
				for (ZipEntry zipentry : Collections.list(zipFile.entries())) {
					if (!zipentry.isDirectory()) {
						String name = zipentry.getName();
						Supplier<InputStream> sup = () -> {
							try {
								return zipFile.getInputStream(zipentry);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						};
						listeners.forEach(l -> l.onSdeFile(name, sup));
					}
				}
			}
			listeners.stream().flatMap(l -> l.listSDECaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			listeners.forEach(SdeUpdateListener::afterSdeUpdate);
		}
		log.info(" finished updating SDE DB.");
	}

}
