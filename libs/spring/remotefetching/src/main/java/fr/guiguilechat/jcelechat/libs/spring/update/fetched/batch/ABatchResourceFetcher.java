package fr.guiguilechat.jcelechat.libs.spring.update.fetched.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.IFetchedResourceRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * abstract service to fetch all entities in a batch. Such a service does not
 * update entities by their id, but instead fetch a list of all the entities at
 * once.
 * 
 * @param <Entity>
 * @param <Id>
 * @param <Repository>
 */
@Slf4j
public abstract class ABatchResourceFetcher<
		Entity extends AFetchedResource<Id>,
    Id extends Number,
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

	/** check new entries */

	@Override
	protected boolean fetchUpdate() {
		boolean updated = false;
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
				setNextUpdate(resp.getExpiresInstant());
				long endTimeMs = System.currentTimeMillis();
				log.info("{} updated {} values in {} ms",
				    fetcherName(),
				    fetched.size(),
				    endTimeMs - startTimeMs);
				updated = true;
				break;
			case 304:
				setNextUpdate(resp.getExpiresInstant());
				break;
			default:
				log.warn("update service {} received invalid response {} when requesting list of entities",
				    getClass().getSimpleName(), resp);
			}
		} else {
			log.warn("update service {} received null list of entities",
			    getClass().getSimpleName());
		}
		return updated;
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
