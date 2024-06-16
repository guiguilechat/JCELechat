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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache.SDEDownload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SDEUpdateService {

	final private CacheManager cacheManager;

	final private UpdateResultService updateresultService;

	/** interface for the beans that should react to SDE update */
	public static interface SdeUpdateListener {

		public default List<String> listSDECaches() {
			return List.of();
		}

		public default void beforeSdeUpdate() {

		}

		public default void onSdeFile(String entryName, Supplier<InputStream> fileContent) {

		}

		public default void afterSdeUpdate() {

		}
	}

	private final Optional<List<SdeUpdateListener>> updateListeners;

	@Value("${sde.updater.forcereinsert:false}")
	private boolean forceReinsert;

	@Transactional
	public void updateSDE() throws IOException {
		Instant startDate = Instant.now();
		UpdateResult ur = UpdateResult.builder().startedDate(startDate).build();
		UpdateResult lastSuccess = updateresultService.lastSuccess();
		SDEDownload fetch = SDECache.getSDE(lastSuccess != null ? lastSuccess.getEtag() : null);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		if (!forceReinsert && lastSuccess != null && fetch.etag().equals(lastSuccess.getEtag())) {
			ur.setStatus(STATUS.CACHED);
			// skip the update
		} else if (fetch.channel() != null) {
			File newFile = fetch.toTempFile();
			try {
				updateFromFile(newFile);
				ur.setStatus(STATUS.SUCCESS);
				ur.setEtag(fetch.etag());
			} catch (Exception e) {
				ur.setStatus(STATUS.FAIL);
				ur.setError(e.getMessage());
				log.error("while updating from SDE file " + newFile.getAbsolutePath(), e);
			}
		} else if (fetch.error() != null) {
			ur.setStatus(STATUS.FAIL);
			ur.setError(fetch.error().getMessage());
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());
		updateresultService.save(ur);
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
