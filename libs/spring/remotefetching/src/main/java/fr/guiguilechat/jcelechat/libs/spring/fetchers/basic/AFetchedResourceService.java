package fr.guiguilechat.jcelechat.libs.spring.fetchers.basic;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.status.ESIStatusService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.tools.ExecutionService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class AFetchedResourceService<
		Entity extends AFetchedResource<Id>,
		Id,
		Repository extends IFetchedResourceRepository<Entity, Id>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private ExecutionService executionService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter
	private ESIStatusService esiStatusService;

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *           service
	 */
	public String fetcherName() {
		return getClass().getSimpleName();
	}

	protected abstract Entity create(Id entityId);

	protected Entity createMinimal(Id entityId) {
		Entity e = create(entityId);
		log.trace("create entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
		return e;
	}

	protected void preSave(Entity data) {
		if (data.getCreated() == null) {
			data.setCreated(Instant.now());
		}
		data.setLastUpdate(Instant.now());
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
	public Map<Id, Entity> createIfAbsent(Collection<Id> entityIds) {
		Map<Id, Entity> storedEntities = repo().findAllById(entityIds).stream()
		    .collect(Collectors.toMap(AFetchedResource::getId,
		        e -> e));
		List<Entity> newEntities = saveAll(entityIds.stream().filter(id -> !storedEntities.containsKey(id)).distinct()
		    .map(this::createMinimal).toList());

		return Stream.concat(storedEntities.values().stream(), newEntities.stream())
		    .collect(Collectors.toMap(AFetchedResource::getId, e -> e));
	}

	//
	// update management
	//

	@Getter
	@Setter
	@ToString()
	public static class UpdateConfig {

		/**
		 * if true, skip the fetch. If false, never skip. if null, use
		 * RemoteResourceUpdaterService value
		 */
		private Boolean skip = null;

		/** max number of fetch each cycle */
		private int max = 1000;

		/** if we have this number or more remain errors, use max updates */
		private int errorsForMax = 90;

		/** if we have this number or less remaining errors, we skip the fetching */
		private int errorsMin = 10;

		/** minimum delay, in s, between two fetch cycles. Ignored if &lt;0 */
		private int delay = 0;

		/** maximum queries per second for this service. */
		private float rate = 1000;

		/**
		 * delay to wait for next fetch cycle when there is no update. Ignored if lower
		 * than {@link #getDelay()}
		 */
		private int delayUpdated = 60;
	}

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	/** return the number of items that are still to be updated */
	public abstract long nbToUpdate();

	private Instant nextUpdateTime = null;

	/** stored here to avoid counting when delay not reached */
	private boolean lastMoreToUpdate = true;

	@Transactional
	public boolean fetch() {
		// skip if delay not met
		if (nextUpdateTime != null && nextUpdateTime.isAfter(Instant.now())) {
			return lastMoreToUpdate;
		}

		preUpdate();
		fetchUpdate();
		postUpdate();

		long nbToUpdate = nbToUpdate();
		// create delay to next
		int delay = Math.max(getUpdate().getDelay(), 0);
		if (getUpdate().getDelayUpdated() > delay && nbToUpdate == 0) {
			delay = getUpdate().getDelayUpdated();
			log.debug(" {} no more data to update({}), extended delay {}s", fetcherName(), nbToUpdate, delay);
		}
		nextUpdateTime = Instant.now().plusSeconds(delay);
		lastMoreToUpdate = nbToUpdate > 0;
		return lastMoreToUpdate;
	}

	// cleanup, fetch if exists new elements
	protected void preUpdate() {

	}

	// actually update the udpatable data
	protected abstract void fetchUpdate();

	// save stats
	protected void postUpdate() {
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
