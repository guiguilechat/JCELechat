package fr.guiguilechat.jcelechat.libs.spring.fetchers.batch;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.IFetchedResourceRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * update entities in a batch
 * 
 * @param <Entity>
 * @param <Id>
 * @param <Repository>
 */
@Slf4j
public abstract class ABatchResourceFetcher<
		Entity extends AFetchedResource<Id>,
		Id,
    Fetched,
    Repository extends IFetchedResourceRepository<Entity, Id>>
    extends AFetchedResourceService<Entity, Id, Repository> {

	@Override
	public long nbToUpdate() {
		return lastListEtag == null ? 1 : 0;
	}

	protected abstract Id extractId(Fetched fetched);

	protected abstract Requested<List<Fetched>> fetchList(Map<String, String> properties);

	private String lastListEtag = null;
	private Instant nextUpdate = null;

	/** check new entries */

	@Override
	protected void fetchUpdate() {
		if (nextUpdate != null && nextUpdate.isAfter(Instant.now())) {
			return;
		}
		long startTimeMs = System.currentTimeMillis();
		Map<String, String> properties = new HashMap<>();
		if (lastListEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
		}
		Requested<List<Fetched>> resp = fetchList(properties);
		if (resp != null) {
			switch (resp.getResponseCode()) {
			case 200:
				List<Fetched> fetched = resp.getOK();
				updateFromFetched(fetched);
				lastListEtag = resp.getETag();
				nextUpdate = resp.getExpiresInstant();
				long endTimeMs = System.currentTimeMillis();
				log.info("{} updated {} values in {} ms",
				    fetcherName(),
				    fetched.size(),
				    endTimeMs - startTimeMs);
				break;
			case 304:
				nextUpdate = resp.getExpiresInstant();
				break;
			default:
				log.warn("update service {} received invalid response {} when requesting list of entities",
				    getClass().getSimpleName(), resp);
			}
		} else {
			log.warn("update service {} received null list of entities",
			    getClass().getSimpleName());
		}
		Instant minDelay = Instant.now().plusSeconds(getUpdate().getDelay());
		if (nextUpdate == null || minDelay.isAfter(nextUpdate)) {
			nextUpdate=minDelay;
		}
	}

	/** called when the list has been updated */
	protected void updateFromFetched(List<Fetched> list) {
		log.debug(" {} listed {} new entries", fetcherName(), list.size());
		Map<Id, Entity> idToEntities = createIfAbsent(list.stream().map(this::extractId).toList());
		updateEntities(list.stream().collect(Collectors.toMap(e -> idToEntities.get(extractId(e)), e -> e)));
		saveAll(idToEntities.values());
	}

	protected abstract void updateEntities(Map<Entity, Fetched> collect);

}
