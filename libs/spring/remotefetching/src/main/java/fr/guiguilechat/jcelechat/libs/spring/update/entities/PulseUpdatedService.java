package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class PulseUpdatedService<
		Entity extends PulseUpdated<Id>,
		Id,
		Repository extends PulseUpdatedRepository<Entity, Id>
>implements EntityService {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

}
