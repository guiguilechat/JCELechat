package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;


public class DeducedEntityService<Entity, Repository extends DeducedEntityRepository<Entity>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	public void delete() {
		repo().delete();
	}

	public Entity save(Entity entity) {
		return repo().saveAndFlush(entity);
	}

	public List<Entity> saveAll(Iterable<Entity> entities) {
		return repo().saveAllAndFlush(entities);
	}

}
