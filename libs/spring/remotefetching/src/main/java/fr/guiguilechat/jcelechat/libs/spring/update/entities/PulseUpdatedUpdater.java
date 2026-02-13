package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class PulseUpdatedUpdater<
		Entity extends PulseUpdated<Id>,
		Id,
		Repository extends PulseUpdatedRepository<Entity, Id>,
		Service extends PulseUpdatedService<Entity, Id, Repository>
> extends ManagedEntityUpdater<Entity> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Service service;

	@Override
	public long nbToUpdate() {
		return repo.countByUpdateActiveTrueAndUpdateNextBefore(Instant.now());
	}

}
