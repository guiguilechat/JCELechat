package fr.guiguilechat.jcelechat.libs.spring.update.entities.remote;

import java.time.Instant;
import java.util.stream.StreamSupport;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.DeducedEntityService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * abstract service that updates local entities from a remote server with one
 * call per entity
 * <p>
 * to make the service list the existing entities from the remote, override
 * {@link #listFetcher()} ; the default implementation is to return null, so to
 * ignore the listing phase.
 * </p>
 *
 * @param <Entity>     local entity we update from the remote
 * @param <IdType>     class of the id for the local entity
 * @param <Fetched>    remote representation of the local entity
 * @param <Repository> repo to list and save the entities
 */
@Slf4j
@NoArgsConstructor
public abstract class RemoteEntityService<Entity extends RemoteEntity<IdType, ?>, IdType extends Number, Repository extends RemoteEntityRepository<Entity, IdType>>
		extends DeducedEntityService<Entity, IdType, Repository> {

	//
	// entity create & save
	//

	/**
	 * if new entries should be activated when created. Default true.<br />
	 * Can be changed with eg
	 *
	 * <pre>{@code
	 * @Getter(lazy = true)
	 * private final boolean activateNewEntry = false;
	 * }</pre>
	 */
	protected boolean isActivateNewEntry() {
		return true;
	}

	@Override
	public Entity createMinimal(IdType entityId) {
		Entity e = super.createMinimal(entityId);
		e.setFetchActive(isActivateNewEntry());
		return e;
	}

	@Override
	protected void preSave(Entity data) {
		super.preSave(data);
		if (data.getExpires() == null) {
			data.setExpires(Instant.now());
		}
	}

	//
	// priority management
	//

	/**
	 * set the priority of the data that need fetching and are lower priority. This
	 * ensures they will be prioritized on the next update pulse. Note that this
	 * does not make their fetch active if they are not already ; on the contrary,
	 * those with fetch inactive won't be updated.
	 */
	public void prioritizeIds(int priority, Iterable<IdType> datas) {
		repo().updateActivePriority(priority, datas, Instant.now());
	}

	public void prioritizeIds(Iterable<IdType> datas) {
		prioritizeIds(100, datas);
	}

	public void prioritize(int priority, Iterable<Entity> entities) {
		prioritizeIds(priority,
				StreamSupport.stream(entities.spliterator(), false)
						.map(RemoteEntity::getId)
						.distinct().toList());
	}

	public void prioritize(Iterable<Entity> entities) {
		prioritizeIds(
				StreamSupport.stream(entities.spliterator(), false)
						.map(RemoteEntity::getId)
						.distinct().toList());
	}

}
