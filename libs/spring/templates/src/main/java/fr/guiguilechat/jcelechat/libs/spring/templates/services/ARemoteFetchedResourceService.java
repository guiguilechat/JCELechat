package fr.guiguilechat.jcelechat.libs.spring.templates.services;

import java.time.Instant;
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
	Repository extends IRemoteFetchedResourceRepository<Entity, Id>
> {

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
		return repo().save(data);
	}

	protected boolean isActivateNewEntry() {
		return true;
	}

	protected abstract Entity create(Id entityId);

	/**
	 * ensure an entity for given id exists, and starts the update if required
	 * 
	 * @param entityId new Id for the entity
	 * @return a future that already hold the entity if it was already present and
	 *           does not require fetch, or that will hold the entity once it is
	 *           fetched.
	 */
	@Async
	@Transactional
	protected CompletableFuture<Entity> createIfMissing(Id entityId) {
		Optional<Entity> op = repo().findById(entityId);
		if (op.isEmpty()) {
			Entity e = create(entityId);
			e.setActive(isActivateNewEntry());
			repo().save(e);
			log.trace("create and update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
			if (e.isActive()) {
				return update(e);
			} else {
				log.trace("creat entry of class {} for id {}, no fetching", e.getClass().getSimpleName(), entityId);
				return CompletableFuture.completedFuture(e);
			}
		} else {
			Entity e = op.get();
			if (!e.isFetched() && e.isActive()) {
				log.trace("entry of class {} for id {} present but needs fetching", e.getClass().getSimpleName(), entityId);
				return update(e);
			} else {
				log.trace("no need to create or update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
				return CompletableFuture.completedFuture(e);
			}
		}
	}

	public Entity fetched(Id entityId) {
		try {
			return createIfMissing(entityId).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("while fetching id " + entityId, e);
			return null;
		}
	}

	protected abstract Requested<Fetched> fetchData(Id id, Map<String, String> properties);

	protected Optional<Entity> forId(Id id) {
		return repo().findById(id);
	}

	public Optional<Entity> getFetched(Id id) {
		Optional<Entity> ret = forId(id);
		if (ret.isEmpty() || ret.get().isFetched() || !ret.get().isActive()) {
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
				updateFromResponseOk(data, response);
				data = save(data);
				log.debug(" updated data " + data.getClass().getSimpleName() + " for id " + data.getRemoteId()
				    + " with expires=" + data.getExpires());
				break;
			case 304:
				data.setExpires(response.getExpiresInstant());
				data = save(data);
				break;
			default:
				log.error("while updating data remoteid {} info class {}, received response code {} and error {}",
				    data.getRemoteId(), data.getClass().getSimpleName(), responseCode, response.getError());
			}
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
	protected void updateFromResponseOk(Entity data, Requested<Fetched> response) {
		data.updateMeta(response);
		data.update(response.getOK());
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
		return repo().findTop1000ByActiveTrueAndExpiresLessThan(Instant.now()).stream().limit(getMaxUpdates());
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
		log.debug(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		return data.parallelStream().limit(getMaxUpdates()).collect(Collectors.toMap(e -> e, this::update));
	}
}
