package fr.guiguilechat.jcelechat.libs.spring.update.fetched;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.IEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.status.ESIStatusService;
import fr.guiguilechat.jcelechat.libs.spring.update.tools.ExecutionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class AFetchedResourceService<Entity extends AFetchedResource<Id>, Id extends Number, Repository extends IFetchedResourceRepository<Entity, Id>>
implements IEntityUpdater {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ExecutionService executionService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ESIStatusService esiStatusService;

	protected abstract Entity create(Id entityId);

	/**
	 * create a minimal entity
	 *
	 * @param entityId
	 * @return
	 */
	public Entity createMinimal(Id entityId) {
		Entity e = create(entityId);
		return e;
	}

	protected void preSave(Entity data) {
		Instant now = Instant.now();
		if (data.getCreated() == null) {
			data.setCreated(now);
		}
		data.setLastUpdate(now);
	}

	public Entity save(Entity data) {
		preSave(data);
		return repo().saveAndFlush(data);
	}

	public List<Entity> saveAll(Iterable<Entity> data) {
		data.forEach(this::preSave);
		return repo().saveAllAndFlush(data);
	}

	/**
	 * ensure an entity for given ids exist. If missing creates them with
	 * {@link #createMinimal(Number)}
	 *
	 * @param entityIds list of ids we need to exist in the DB
	 * @return the set of ids that have been created
	 */
	public Set<Id> insertIfAbsent(List<Id> entityIds) {
		Set<Id> toCreate = new HashSet<>(entityIds);
		partitionInList(entityIds)
		.map(repo()::findExistingIds)
		.flatMap(List::stream)
		.forEach(toCreate::remove);
		saveAll(toCreate.stream()
				.map(this::createMinimal)
				.toList());
		return toCreate;
	}

	/**
	 * ensure an entity for given id exists, does not start fetch
	 *
	 * @param entityId new Id for the entity
	 * @return entity for corresponding id
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Entity createIfAbsent(Id entityId) {
		Entity e = repo().findById(entityId).orElse(null);
		if (e == null) {
			e = save(createMinimal(entityId));
		}
		return e;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Id, Entity> createIfAbsent(List<Id> entityIds) {
		long start = System.currentTimeMillis();
		Map<Id, Entity> storedEntities = new HashMap<>();
		if (entityIds.isEmpty()) {
			return storedEntities;
		}
		log.trace("{} createIfAbsent {} entities", fetcherName(), entityIds.size());
		partitionInList(entityIds)
		    .map(repo()::findAllById)
		    .flatMap(List::stream)
		.forEach(r -> storedEntities.put(r.getId(), r));
		long postRetrieved = System.currentTimeMillis();
		log.trace(" {} createIfAbsent retrieved {} stored entities in {} ms @ {}/s", fetcherName(), storedEntities.size(),
				postRetrieved - start, storedEntities.size() * 1000 / Math.max(1, postRetrieved - start));
		List<Entity> newEntities = saveAll(entityIds.stream()
				.filter(id -> !storedEntities.containsKey(id)).distinct()
				.map(this::createMinimal)
				.toList());
		log.trace(" {} createIfAbsent created {} new entities", fetcherName(), newEntities.size());
		return Stream.concat(storedEntities.values().stream(), newEntities.stream())
				.collect(Collectors.toMap(AFetchedResource::getId, e -> e));
	}

	//
	// update management
	//

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	/**
	 * return the number of items that are still to be updated. This is used to
	 * decide if all the entities are updated.
	 */
	public abstract long nbToUpdate();

	/**
	 * request to fetch the resources that need it.
	 * <ol>
	 * <li>verifies that its own delays are respected ;</li>
	 * <li>checks if new resource exists in {@link #preUpdate()}</li>
	 * <li>perform the actual fetch in {@link #fetchUpdate()}</li>
	 * <li>update corresponding data, stats, cache in {@link #postUpdate()}</li>
	 * <li>update the delay and own status</li>
	 * </ol>
	 *
	 * @return true if more resource need fetching (ie if this service is not fully
	 *           updated). Note that in case of error, the resources not fetched may
	 *           be saved with a future expires (to avoid frequent errors) and as
	 *           such, this may return false even if resources are still not fetched
	 */
	@Override
	@Transactional
	public boolean fetch() {
		preUpdate();
		if (fetchUpdate()) {
			postUpdate();
		}
		return nbToUpdate() > 0;
	}

	/**
	 * cleanup, fetch if exists new elements, default implementation resets the
	 * {@link #nextUpdate}
	 */
	protected void preUpdate() {
		setNextUpdate(null);
	}

	/**
	 * actually update the updatable data
	 *
	 * @return true if at least an item was updated
	 */
	protected abstract boolean fetchUpdate();

	/**
	 * following fetch date, if any. If not set, default method to use the
	 * {@link UpdateConfig}. It is reset during the {@link #preUpdate()}
	 */
	@Setter(value = AccessLevel.PROTECTED)
	private Instant nextUpdate = null;

	@Override
	public Instant nextUpdate(boolean remain, Instant now) {
		if (nextUpdate != null) {
			return nextUpdate;
		}
		return IEntityUpdater.super.nextUpdate(remain, now);
	}

	//
	// cache management
	//

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private CacheManager cacheManager;

	/**
	 * react to updates. to use it, define your own interface, and create a field
	 * with getter to have {@link #getListeners}
	 *
	 * <pre>{@code
	 * public static interface XListener extends EntityUpdateListener {
	 * }
	 *
	 * @Getter
	 * private final Optional<List<XListener>> listeners;
	 * }</pre>
	 */
	public interface EntityUpdateListener {

		/** triggered when at least an item is updated */
		default void onUpdate() {
		}

		/**
		 * list the caches that should be invalidated on entity update.
		 * <p>
		 * can be implemented with eg
		 *
		 * <pre>{@code
		 * @Getter(lazy = true)
		 * private final List<String> cacheList = List.of(
		 *     "cache1",
		 *     "cache2");
		 * }</pre>
		 * </p>
		 */
		default List<String> getCacheList() {
			return List.of();
		}
	}

	/**
	 * override this to provide your own list of listeners, eg
	 *
	 * <pre>{@code
	 * @Getter
	 * @Lazy
	 * private final Optional<List<MyListener>> listeners;
	 * }</pre>
	 */
	protected Optional<? extends List<? extends EntityUpdateListener>> getListeners() {
		return null;
	}

	/**
	 * override this to return true, and make the class implement
	 * {@link EntityUpdateListener}, to have its own caches invalidated on entity
	 * update.Code to override :
	 *
	 * <pre>{@code
	 * @Getter(lazy = true)
	 * private final boolean selfInvalidate = true;
	 * }</pre>
	 */
	protected boolean isSelfInvalidate() {
		return false;
	}

	protected void postUpdate() {
		Optional<? extends List<? extends EntityUpdateListener>> listeners = getListeners();
		Stream<EntityUpdateListener> ls = Stream.empty();
		if (isSelfInvalidate() && this instanceof EntityUpdateListener) {
			ls = Stream.concat(ls, Stream.of((EntityUpdateListener) this));
		}
		if (listeners != null && listeners.isPresent()) {
			ls = Stream.concat(ls, listeners.get().stream());
		}
		ls.forEach(eul -> {
			eul.getCacheList().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			eul.onUpdate();
		});
	}

	//
	// general access
	//

	public Map<Id, Entity> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(Entity::getId, c -> c));
	}

	public Entity byId(Id id) {
		return repo().findById(id).orElse(null);
	}

	public Optional<Entity> findById(Id id) {
		return repo().findById(id);
	}

	public List<Entity> findById(Iterable<Id> ids) {
		return repo().findAllById(ids);
	}

}
