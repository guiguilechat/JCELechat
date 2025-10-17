package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

	public Entity create(IdType entityId) {
		Entity create = creator.get();
		create.setId(entityId);
		return repo().save(create);
	}

	void setAllRemoved() {
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
			repo().save(ret);
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
		repo().saveAll(created);
		return ret;
	}

}
