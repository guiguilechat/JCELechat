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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.update.limits.GlobalErrors;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.TokenBucketResolver;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.tools.ExecutionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class FetchedEntityService<
		Entity extends FetchedEntity<Id>,
		Id extends Number,
		Repository extends FetchedEntityRepository<Entity, Id>>
	implements EntityService {

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
	private TokenBucketResolver tokensBucket;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private GlobalErrors globalErrors;

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
	 * ensure an entity for given id exists.
	 *
	 * @return true if new entity was created
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean createMissing(Id id) {
		Id found = repo().findExistingId(id);
		if (found == null) {
			save(createMinimal(id));
			return true;
		}
		return false;
	}

	/**
	 * ensure an entity for given ids exist. If missing creates them with
	 * {@link #createMinimal(Number)}.<br />
	 * The difference with {@link #getOrCreate(List)} is that it does not fetch
	 * the data if corresponding entity already exists
	 *
	 * @param entityIds list of ids we need to exist in the DB
	 * @return the set of ids that have been created
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Set<Id> createMissing(List<Id> entityIds) {
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
	public Entity getOrCreate(Id entityId) {
		Entity e = repo().findById(entityId).orElse(null);
		if (e == null) {
			e = save(createMinimal(entityId));
		}
		return e;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Id, Entity> getOrCreate(List<Id> entityIds) {
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
				.collect(Collectors.toMap(FetchedEntity::getId, e -> e));
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
