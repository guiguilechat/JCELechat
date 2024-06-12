package fr.guiguilechat.jcelechat.libs.spring.remotefetching;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically updates the data for service handling remote resource caching.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RemoteResourceUpdaterService {

	private final Optional<List<ARemoteFetchedResourceService<?, ?, ?, ?>>> fetchedServices;

	public static interface IRemoteResourceUpdateListener {

		public default void onNoUpdateRemain() {

		}
	}

	private final Optional<List<IRemoteResourceUpdateListener>> listeners;

	@Value("${esi.updater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esi.updater.period:10000}", initialDelayString = "${esi.updater.delay:1000}")
	public void updateRemoteResources() throws IOException {
		long totRemain = 0l;
		if (!skip && fetchedServices.isPresent()) {
			List<ARemoteFetchedResourceService<?, ?, ?, ?>> services = fetchedServices.get();
			log.debug("updating " + services.size() + " services");
			totRemain = services.stream().mapToLong(this::updateService).sum();
		} else {
			log.info("skipping update of {} services with skip={}",
			    fetchedServices.isPresent() ? fetchedServices.get().size() : 0, skip);
		}
		if (totRemain == 0l && listeners.isPresent()) {
			for (IRemoteResourceUpdateListener l : listeners.get()) {
				l.onNoUpdateRemain();
			}
		}
	}

	@Value("${esi.updater.default.skip:false}")
	private boolean defaultSkip;

	protected <Entity extends ARemoteFetchedResource<Id, Fetched>, Id, Fetched, Repository extends IRemoteFetchedResourceRepository<Entity, Id>> long updateService(
	    ARemoteFetchedResourceService<Entity, Id, Fetched, Repository> fetchedService) {
		boolean skipService = Optional.ofNullable(fetchedService.getUpdate().getSkip()).orElse(defaultSkip);
		if (skipService) {
			return 0;
		}
		long startTimeMs=System.currentTimeMillis();
		fetchedService.checkNewEntries();
		int nbUpdates = 0;
		Map<Entity, CompletableFuture<Entity>> futures = null;
		if(fetchedService.isSupportsBatchUpdate()) {
			List<Entity> list = fetchedService.streamToUpdate().toList();
			futures = fetchedService.batchUpdate(list);
			nbUpdates = list.size();
		} else {
			futures = fetchedService.streamToUpdate().collect(Collectors.toMap(e -> e, fetchedService::update));
			nbUpdates = futures.size();
		}
		AtomicInteger nbSuccess = new AtomicInteger(0);
		futures.entrySet().forEach(f -> {
			try {
				Entity updated = f.getValue().orTimeout(5, TimeUnit.SECONDS).join();
				if (updated.getSuccessiveErrors() == 0) {
					nbSuccess.incrementAndGet();
				}
			} catch (CompletionException e) {
				log.warn(
				    "CompletionException while updating {} {} : {}", f.getKey().getClass().getSimpleName(), f.getKey().getId(),
				    e.getMessage());
			}
		});
		long endTimeMs = System.currentTimeMillis();
		long nbRemain = fetchedService.nbToUpdate();
		if (nbUpdates > 0) {
			log.info("{} updated {}/{} in {} ms, remain {}",
			    fetchedService.fetcherName(),
			    nbSuccess.get(), nbUpdates,
			    endTimeMs - startTimeMs, nbRemain);
		}
		return nbRemain;
	}

}
