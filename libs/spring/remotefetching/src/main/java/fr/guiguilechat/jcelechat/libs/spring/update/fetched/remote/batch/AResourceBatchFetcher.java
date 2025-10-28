package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.batch;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntity;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntityRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntityService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * abstract service to fetch all entities in a batch. Such a service does not
 * fetch each entities by its id, but instead fetches all the entities at
 * once.
 *
 * @param <Entity>
 * @param <Id>
 * @param <Repository>
 */
@Slf4j
public abstract class AResourceBatchFetcher<
		Entity extends FetchedEntity<Id>,
    Id extends Number,
    Fetched,
    Repository extends FetchedEntityRepository<Entity, Id>>
    extends FetchedEntityService<Entity, Id, Repository> {

	@Override
	public long nbToUpdate() {
		return lastListEtag == null ? 1 : 0;
	}

	protected abstract Id extractId(Fetched fetched);

	protected abstract Requested<List<Fetched>> fetchList(Map<String, String> properties);

	private String lastListEtag = null;

	@Setter
	private Instant nextUpdate = null;

	/** check new entries */

	@Override
	protected boolean fetchUpdate() {
		int remainErrors = globalErrors().availErrors();
		if (remainErrors <= getUpdate().getErrorsMin()) {
			log.trace("{} skip updates as only {} remaining errors", fetcherName(), remainErrors);
			return false;
		}
		boolean updated = false;
		long startTimeMs = System.currentTimeMillis();
		Map<String, String> properties = new HashMap<>();
		if (lastListEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
		}
		Requested<List<Fetched>> resp = fetchList(properties);
		nextUpdate = null;
		if (resp != null) {
			processResponse(resp);
			switch (resp.getResponseCode()) {
			case 200:
				List<Fetched> fetched = resp.getOK();
				updateFromFetched(fetched);
				lastListEtag = resp.getETag();
				nextUpdate = resp.getExpiresInstant();
				long endTimeMs = System.currentTimeMillis();
				log.debug("{} updated {} values in {} ms",
				    fetcherName(),
				    fetched.size(),
				    endTimeMs - startTimeMs);
				updated = true;
				break;
			case 304:
				nextUpdate = resp.getExpiresInstant();
				break;
			default:
				log.warn("update {} received invalid response {} when requesting list of entities",
				    getClass().getSimpleName(), resp);
			}
		} else {
			log.warn("update {} received null list of entities",
			    fetcherName());
		}
		return updated;
	}

	@Override
	public Instant nextUpdate(boolean remain, Instant now) {
		if (nextUpdate != null) {
			return nextUpdate;
		}
		return super.nextUpdate(remain, now);
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
