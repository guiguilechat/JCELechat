package fr.guiguilechat.jcelechat.libs.spring.remotefetching.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.repositories.IRemoteFetchedResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically updates the data for service handling remote resource caching.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RemoteResourceUpdaterService {

	private final Optional<List<ARemoteFetchedResourceService<?, ?, ?, ?>>> updateServices;

	@Value("${esiconnect.resourceudpater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esiconnect.resourceudpater.fetchperiod:10000}", initialDelayString = "${esiconnect.resourceudpater.fetchdelay:5000}")
	public void updateChars() throws IOException {
		if (!skip && updateServices.isPresent()) {
			updateServices.get().parallelStream().forEach(this::updateService);
		}

	}

	protected <Entity extends ARemoteFetchedResource<Id, Fetched>, Id, Fetched, Repository extends IRemoteFetchedResourceRepository<Entity, Id>> void updateService(
	    ARemoteFetchedResourceService<Entity, Id, Fetched, Repository> updateServices) {
		Map<Entity, CompletableFuture<Entity>> futures = updateServices.isSupportsBatchUpdate()
		    ? updateServices.batchUpdate(updateServices.streamToUpdate().toList())
		    : updateServices.streamToUpdate().collect(Collectors.toMap(e -> e, updateServices::update));
		futures.entrySet().forEach(f -> {
			try {
				f.getValue().join();
			} catch (CompletionException e) {
				log.error(
				    "while updating entity " + f.getKey().getClass().getSimpleName() + " id=" + f.getKey().getRemoteId(), e);
			}
		});
	}

}
