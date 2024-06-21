package fr.guiguilechat.jcelechat.libs.spring.fetchers.manager;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.IFetchedResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * automatically updates the data for service handling remote resource caching.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RemoteResourceUpdaterService {

	private final Optional<List<AFetchedResourceService<?, ?, ?>>> fetchedServices;

	//
	// those are mostly used for debuging and stopping
	//

	public static interface IRemoteResourceUpdateListener {

		public default void onNoUpdateRemain() {

		}
	}

	private final Optional<List<IRemoteResourceUpdateListener>> listeners;

	@Value("${esi.updater.skip:false}")
	private boolean skip;

	@Scheduled(fixedRateString = "${esi.updater.period:10000}", initialDelayString = "${esi.updater.delay:1000}")
	public void updateRemoteResources() throws IOException {
		boolean remain = false;
		if (!skip && fetchedServices.isPresent()) {
			List<AFetchedResourceService<?, ?, ?>> services = fetchedServices.get();
			log.debug("updating " + services.size() + " services");
			remain = services.stream().filter(this::updateService).count() > 0;
		} else {
			log.info("skipping update of {} services with skip={}",
			    fetchedServices.isPresent() ? fetchedServices.get().size() : 0, skip);
		}
		if (!remain && listeners.isPresent()) {
			for (IRemoteResourceUpdateListener l : listeners.get()) {
				l.onNoUpdateRemain();
			}
		}
	}

	@Value("${esi.updater.default.skip:false}")
	private boolean defaultSkip;

	/**
	 * @param <Entity>
	 * @param <Id>
	 * @param <Fetched>
	 * @param <Repository>
	 * @param fetchedService the updater service to fetch
	 * @return true if the given service requires more fetch after this pass
	 */
	protected <Entity extends AFetchedResource<Id>, Id, Repository extends IFetchedResourceRepository<Entity, Id>> boolean updateService(
	    AFetchedResourceService<Entity, Id, Repository> fetchedService) {
		boolean skipService = Optional.ofNullable(fetchedService.getUpdate().getSkip()).orElse(defaultSkip);
		if (skipService) {
			return false;
		}
		log.trace("start fetching {}", fetchedService.fetcherName());
		return fetchedService.fetch();
	}

}
