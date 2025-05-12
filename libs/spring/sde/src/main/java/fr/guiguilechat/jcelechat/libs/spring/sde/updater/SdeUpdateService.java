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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdate.Status;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.IEntityUpdater;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache.SDEDownload;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "sde.fetcher")
public class SdeUpdateService implements IEntityUpdater {

	final private CacheManager cacheManager;

	final private SdeUpdateRepository repo;

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

	protected SdeUpdate findLastSuccess() {
		return repo.findTop1ByStatusOrderByStartedDateDesc(Status.SUCCESS);
	}

	public SdeUpdate save(SdeUpdate entity) {
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
		SdeUpdate ur = SdeUpdate.builder().startedDate(startDate).build();
		SdeUpdate lastSuccess = findLastSuccess();
		SDEDownload fetch = SDECache.getSDE(!force && lastSuccess != null ? lastSuccess.getEtag() : null);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		if (!force && lastSuccess != null && fetch.etag().equals(lastSuccess.getEtag())) {
			// skip the update
			ur.setStatus(Status.CACHED);
		} else if (fetch.channel() != null) {
			try {
				File newFile = fetch.toTempFile();
				updateFromFile(newFile);
				ur.setStatus(Status.SUCCESS);
				ur.setEtag(fetch.etag());
			} catch (Exception e) {
				ur.setStatus(Status.FAIL);
				log.error("while updting sde", e);
				ur.setError(e.getMessage());
			}
		} else if (fetch.error() != null) {
			ur.setStatus(Status.FAIL);
			ur.setError(fetch.error().getMessage());
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());

		save(ur);
		log.debug(" sde udpate result is {}", ur);

		force = false;
		nextFetch = startDate.plusSeconds(getUpdate().getDelay());
		return ur.getStatus() != Status.SUCCESS;
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
