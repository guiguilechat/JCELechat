package fr.guiguilechat.jcelechat.libs.spring.update.entities.number;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.ManagedEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.GlobalErrors;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.TokenBucketResolver;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class NumberEntityUpdater<
		Entity extends NumberEntity<Id>,
		Id extends Number,
		Repository extends NumberEntityRepository<Entity, Id>,
		Service extends NumberEntityService<Entity, Id, Repository>
> extends ManagedEntityUpdater<Entity> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private GlobalErrors globalErrors;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Service service;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TokenBucketResolver tokensBucket;

	protected Entity save(Entity data) {
		return service().save(data);
	}

	protected List<Entity> saveAll(Iterable<Entity> data) {
		return service().saveAll(data);
	}

	protected Set<Id> createMissing(List<Id> entityIds) {
		return service().createMissing(entityIds);
	}

	protected Entity createMinimal(Id entityId) {
		return service().createMinimal(entityId);
	}

	//
	// update management
	//



	/**
	 * transmit a single received response to the global error service and the
	 * tokens bucket, to update them.
	 */
	protected void processEsiResponse(Requested<?> response) {
		globalErrors().processResponse(response);
		tokensBucket().processResponse(response);
	}

	/**
	 * transmit the last response of a list to the global error service and the
	 * tokens bucket, to update them.
	 */
	protected void processEsiResponses(Iterable<Requested<?>> responses) {
		globalErrors().processResponse(responses);
		tokensBucket().processResponse(responses);
	}




}
