package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class SdeEntityService<Entity extends SdeEntity<IdType>, IdType extends Number, Repository extends SdeEntityRepository<Entity, IdType>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	private final Supplier<Entity> creator;

	/**
	 * create an empty entity for given id, and save it. The entity returned is the
	 * one saved already.
	 *
	 * @param entityId new entity's id
	 * @return
	 */
	public Entity create(IdType entityId) {
		Entity create = creator.get();
		create.setId(entityId);
		return repo().save(create);
	}

	/**
	 * mark all entities as removed
	 */
	protected void setAllRemoved() {
		repo().setAllRemoved();
	}

	public Map<IdType, Entity> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(SdeEntity::getId, o -> o));
	}

	public List<Entity> saveAll(Iterable<Entity> entities) {
		return repo().saveAllAndFlush(entities);
	}

	public Entity byId(IdType id) {
		return repo().findById(id).orElse(null);
	}

	public Collection<Entity> byId(Iterable<IdType> regionIds) {
		return repo().findAllById(regionIds);
	}

	public Entity createIfAbsent(IdType id) {
		var op = repo().findById(id);
		if (op.isEmpty()) {
			Entity ret = create(id);
			return ret;
		} else {
			return op.get();
		}
	}

	public Map<IdType, Entity> createIfAbsent(Collection<IdType> ids) {
		var l = repo().findAllById(ids);
		if (l.size() == ids.size()) {
			return l.stream().collect(Collectors.toMap(Entity::getId, o -> o));
		}
		Map<IdType, Entity> m = l.stream().collect(Collectors.toMap(Entity::getId, o -> o));
		Map<IdType, Entity> ret = new HashMap<>();
		List<Entity> created = new ArrayList<>();
		for (IdType id : ids) {
			Entity added = m.get(id);
			if (added == null) {
				added = create(id);
				created.add(added);
			}
			ret.put(id, added);
		}
		return ret;
	}

	/**
	 * list all items and make a getter on them
	 *
	 * @return a function that has the list of internal items and return from it, or
	 *         create a new one if absent
	 */
	public Function<IdType, Entity> getterAll() {
		Map<IdType, Entity> items = new HashMap<>(allById());
		return i -> i == null ? null : items.computeIfAbsent(i, this::create);
	}

	/**
	 * list items for given ids, creating the missing, and return a mapper from
	 * those ids to the corresponding item
	 *
	 * @param ids stream of ids. They are concatenated as distinct
	 * @return a function that memorizes the items for those ids, after creating the
	 *         missingones.
	 */
	public Function<IdType, Entity> getter(Stream<IdType> ids) {
		return createIfAbsent(ids.distinct().toList())::get;
	}

}
