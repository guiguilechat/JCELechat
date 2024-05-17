package fr.guiguilechat.jcelechat.libs.spring.templates.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.IRemoteFetchedResourceRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class ARemoteFetchedResourceService<
	Entity extends ARemoteFetchedResource<Id, Fetched>,
	Id,
	Fetched,
	Repository extends IRemoteFetchedResourceRepository<Entity, Id>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private Repository repo;

	public Entity save(Entity data) {
		if (data.getExpires() == null) {
			data.setExpires(Instant.now());
		}
		if (data.getCreated() == null) {
			data.setCreated(Instant.now());
		}
		data.setLastUpdate(Instant.now());
		return repo().saveAndFlush(data);
	}

	protected boolean isActivateNewEntry() {
		return true;
	}

	protected abstract Entity create(Id entityId);

	protected CompletableFuture<Entity> createFetchIfNeeded(Entity e, Id entityId, boolean createOnAbsent, boolean startFetch) {
		if (e == null) {
			if (!createOnAbsent) {
				return null;
			}
			e = create(entityId);
			e.setFetchActive(isActivateNewEntry());
			save(e);
			log.trace("create entry of class {} for id {}", e.getClass().getSimpleName(), entityId);

		}
		if (!e.isFetched() && e.isFetchActive() && startFetch) {
			log.trace("entry of class {} for id {} needs fetching", e.getClass().getSimpleName(), entityId);
			return update(e);
		} else {
			log.trace("no need to update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
			return CompletableFuture.completedFuture(e);
		}
	}

	/**
	 * ensure an entity for given id exists, and starts the update if required
	 * 
	 * @param entityId   new Id for the entity
	 * @param startFetch if true, and the entity is created active, and not already
	 *                     fetched, start the fetch instead of waiting for the
	 *                     manager to handle it. If false, return the entry and
	 *                     return it without fetching.
	 * @return a future that already holds the entity if it was already present and
	 *           does not require fetch, or that will hold the entity once it is
	 *           fetched.
	 */
	@Async
	@Transactional
	public CompletableFuture<Entity> createIfAbsent(Id entityId, boolean startFetch) {
		synchronized (repo()) {
			return createFetchIfNeeded(repo().findById(entityId).orElse(null), entityId, true, startFetch);
		}
	}

	@Transactional
	public Map<Id, CompletableFuture<Entity>> createIfAbsent(Collection<Id> entityIds,
	    boolean startFetch) {
		synchronized (repo()) {
			Map<Id, Entity> storedEntities = repo().findAllById(entityIds).stream()
		    .collect(Collectors.toMap(ARemoteFetchedResource::getRemoteId, e -> e));
		return entityIds.stream().distinct().collect(Collectors.toMap(ei -> ei,
		    entityId -> createFetchIfNeeded(storedEntities.get(entityId), entityId, true, startFetch)));
	}
	}


	/**
	 * create the entity if needed, then fetch it and return it once fetched or
	 * failed.
	 * 
	 * @param entityId id for the entity we want
	 * @return a managed entity, fetched if it should, or null if exception caught.
	 *           It may not be fetched if {@link #isActivateNewEntry()} is false
	 */
	public Entity createFetch(Id entityId) {
		try {
			return createIfAbsent(entityId, true).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("while fetching id " + entityId, e);
			return null;
		}
	}

	protected abstract Requested<Fetched> fetchData(Id id, Map<String, String> properties);

	/**
	 * if an entity exists for an id, update it if needed, then return it.
	 * Use {@link #createFetch(Object)} to create it if absent.
	 * 
	 * @return entity for given id, at least fetched once if needed. May not be
	 *           fetched if the {@link #isActivateNewEntry()} is false ; may be
	 *           empty if not created.
	 */
	public Optional<Entity> getExistingFetched(Id id) {
		Optional<Entity> ret = repo().findById(id);
		if (ret.isEmpty() || ret.get().isFetched() || !ret.get().isFetchActive()) {
			return ret;
		}
		try {
			update(ret.get()).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("while fetching " + ret.get().getClass() + " for data id" + id, e);
		}
		return ret;
	}

	/**
	 * perform an update of an entity using its remote representation. If the
	 * entity is updated in any way, it is also saved already.
	 * 
	 * @param data the entity to update
	 * @return empty completable future to synchronize over.
	 */
	@Transactional
	@Async
	public CompletableFuture<Entity> update(Entity data) {
		String lastEtag = data.getLastEtag();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		try {
			Requested<Fetched> response = fetchData(data.getRemoteId(), properties);
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				updateResponseOk(data, response);
				log.trace(" updated data " + data.getClass().getSimpleName() + " for id " + data.getRemoteId()
				    + " with expires=" + data.getExpires());
				break;
			case 304:
				updateNoChange(data, response);
				break;
			default:
				log.error("while updating data remoteid {} info class {}, received response code {} and error {}",
				    data.getRemoteId(), data.getClass().getSimpleName(), responseCode, response.getError());
				switch (responseCode / 100) {
				case 4:
					updateRequestError(data, response);
					break;
				case 5:
					updateServerError(data, response);
					break;
				default:
					throw new UnsupportedOperationException("case " + responseCode + " not handled");
				}
			}
			data = save(data);
		} catch (Exception e) {
			log.error("while updating " + data.getClass().getSimpleName() + " for data remoteid " + data.getRemoteId(), e);
		}
		return CompletableFuture.completedFuture(data);
	}

	/**
	 * called when an ok is received for given data. data is saved after that call.
	 * 
	 * @param data     data that should be updated
	 * @param response remote response for that data update.
	 */
	protected void updateResponseOk(Entity data, Requested<Fetched> response) {
		updateMetaOk(data, response);
		data.update(response.getOK());
	}

	/**
	 * update meta data from an ok response
	 */
	protected void updateMetaOk(Entity data, Requested<?> response) {
		data.updateMetaOk(response.getLastModifiedInstant(), extractExpires(response), response.getETag());
	}

	/**
	 * number of seconds we add to now() to create the expires, if missing
	 * can be changed with
	 * {@code  @Getter(lazy=true) private final int defaultExpiresDelaySeconds=1000;}
	 */
	protected int getDefaultExpiresDelaySeconds() {
		return 3600;
	}

	/**
	 * extract the expires
	 * 
	 * @param headers mutable map
	 * @return the map.
	 */
	protected Instant extractExpires(Requested<?> response) {
		Instant ret = response.getExpiresInstant();
		if (ret != null) {
			return ret;
		}
		Instant date = Instant.now();
		if (response.getHeaders().containsKey(Requested.DATE_PROP)) {
			String datestr = response.getHeaders().get(Requested.DATE_PROP).stream().findFirst().orElse(null);
			if (datestr != null) {
				date = ESITools.headerInstant(datestr);
			}
		}
		return date.plusSeconds(getDefaultExpiresDelaySeconds());
	}

	/**
	 * Called when the request returned a 304
	 */
	protected void updateNoChange(Entity data, Requested<Fetched> response) {
		data.setExpires(response.getExpiresInstant());
	}

	/**
	 * Called when the request returned a 4xx
	 */
	protected void updateRequestError(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		switch (response.getResponseCode()) {
		case 401: // banned. Wait a day
			data.setExpires(Instant.now().plus(nbErrors * 12, ChronoUnit.HOURS));
			return;
		default:
			data.setExpires(Instant.now().plus(nbErrors, ChronoUnit.HOURS));
		}

	}

	/**
	 * Called when the request returned a 5xx
	 */
	protected void updateServerError(Entity data, Requested<Fetched> response) {
		data.setExpires(Instant.now().plus(5, ChronoUnit.MINUTES));
	}

	/**
	 * default 1000 max updates to do at once. Override to change, eg <br />
	 * {@code @Getter(lazy=true) private final int maxUpdates=50;}
	 * 
	 * @return maximum number of entities to update at once
	 */
	public int getMaxUpdates() {
		return 1000;
	}

	/**
	 * you can override that to use a dedicated method in the jpa repostiory, eg to
	 * allow more than 1000 entities to update at once.
	 * 
	 * @return the next entities that are to be updated, limited to
	 *           {@link #getMaxUpdates()} and 1000 from implementation limit,
	 *           whichever is lowest.
	 */
	public Stream<Entity> streamToUpdate() {
		return repo().findTop1000ByFetchActiveTrueAndExpiresLessThan(Instant.now()).stream().limit(getMaxUpdates());
	}

	/**
	 * overidable default false
	 * 
	 * @return true only when {@link #batchUpdate(List)} has built-in better
	 *           performances than calling an update on each element of the list
	 *           sequentially.
	 */
	public boolean isSupportsBatchUpdate() {
		return false;
	}

	/**
	 * batch update. Default is to call each in parallel stream, if you use a
	 * better implementation don't forget to replace
	 * {@link #isSupportsBatchUpdate()} as true eg with <br />
	 * {@code @Getter(lazy=true) private final boolean supportsBatchUpdate=true;}
	 * 
	 * @param data list of entities we want to update
	 * @return for each entity updated, the future to wait for the update to be
	 *           done. If they are all updated once the call is done, return empty
	 *           map instead.
	 */
	public Map<Entity, CompletableFuture<Entity>> batchUpdate(List<Entity> data) {
		log.trace(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		return data.parallelStream().limit(getMaxUpdates()).collect(Collectors.toMap(e -> e, this::update));
	}
}
