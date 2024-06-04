package fr.guiguilechat.jcelechat.libs.spring.remotefetching;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
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

	@Value("${esiconnect.resourceudpater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esiconnect.resourceudpater.fetchperiod:10000}", initialDelayString = "${esiconnect.resourceudpater.fetchdelay:5000}")
	public void updateChars() throws IOException {
		if (!skip && fetchedServices.isPresent()) {
			List<ARemoteFetchedResourceService<?, ?, ?, ?>> services = fetchedServices.get();
			log.debug("updating " + services.size() + " services");
			services.stream().forEach(this::updateService);
		}
	}

	protected <Entity extends ARemoteFetchedResource<Id, Fetched>, Id, Fetched, Repository extends IRemoteFetchedResourceRepository<Entity, Id>> void updateService(
	    ARemoteFetchedResourceService<Entity, Id, Fetched, Repository> fetchedService) {
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
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().orTimeout(5, TimeUnit.SECONDS).join();
			} catch (CompletionException e) {
				log.error(
				    "while updating " + f.getKey().getClass().getSimpleName() + " " + f.getKey().getId(),
				    e);
			}
		});
		long endTimeMs = System.currentTimeMillis();
		if (nbUpdates > 0) {
			log.info("updated service {} for {} values in {} ms", fetchedService.getClass().getSimpleName(), nbUpdates,
			    endTimeMs - startTimeMs);
		}
	}

}
