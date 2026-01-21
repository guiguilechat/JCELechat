package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.GlobalErrors;
import fr.guiguilechat.jcelechat.libs.spring.update.limits.TokenBucketResolver;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.tools.ExecutionService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class LocalEntityUpdater<
		Entity extends LocalEntity<Id>,
		Id extends Number,
		Repository extends LocalEntityRepository<Entity, Id>,
		Service extends LocalEntityService<Entity, Id, Repository>
	> implements EntityUpdater {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ExecutionService executionService;

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
	 *         updated). Note that in case of error, the resources not fetched may
	 *         be saved with a future expires (to avoid frequent errors) and as
	 *         such, this may return false even if resources are still not fetched
	 */
	@Override
	@Transactional
	public boolean fetch() {
		preUpdate();
		if (fetchUpdate()) {
			postUpdate();
		}
		long nbToUpdate = nbToUpdate();
		log.trace("  {} has {} to update",
				serviceName(),
				nbToUpdate);
		return nbToUpdate > 0;
	}

	/**
	 * cleanup, fetch if exists new elements, default implementation resets the
	 * {@link #nextUpdate}
	 */
	protected void preUpdate() {
		setNextUpdate(null);
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

	protected boolean nextUpdateReached() {
		return nextUpdate == null || nextUpdate.isAfter(Instant.now());
	}

	@Override
	public Instant nextUpdate(boolean remain, Instant now) {
		if (nextUpdate != null) {
			return nextUpdate;
		}
		return EntityUpdater.super.nextUpdate(remain, now);
	}

	/**
	 * deduce the limit we are allowed on the queries. This limits takes both the
	 * global error limiter and the entity-specific token bucket
	 *
	 * @return number of simultaneous queries we are allowed to send
	 */
	protected int maxAllowedQueries() {
		int errorsMin = getUpdate().getErrorsMin();
		int errorsMax = getUpdate().getErrorsForMax();
		int remainErrors = globalErrors().availErrors();
		int maxQueries = getUpdate().getMax();
		int errorQueries = maxQueries;
		if (remainErrors <= errorsMin) {
			errorQueries = 0;
		} else if (remainErrors < errorsMax) {
			errorQueries = (int) Math.ceil(1.0 * maxQueries * (remainErrors - errorsMin) / (errorsMax - errorsMin));
		}
		int bucketQueries = tokensBucket().availQueries();
		int ret = Math.min(errorQueries, bucketQueries);
		log.trace(" {} max queries is {} from remaining errors={}[{}:0;{}:{}] bucketQueries={}",
				serviceName(),
				ret,
				remainErrors,
				errorsMin,
				errorsMax,
				maxQueries,
				bucketQueries);
		return ret;
	}

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

	//
	// cache management
	//

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private CacheManager cacheManager;

	/**
	 * reacts to updates. to use it, define your own interface, and create a field
	 * with getter to have {@link #getListeners}, eg :
	 *
	 * <pre>{@code
	 * public static interface XListener extends EntityUpdateListener {
	 * }
	 *
	 * @Getter
	 * private final Optional&lt;List&lt;XListener>> listeners;
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
		 * 		"cache1",
		 * 		"cache2");
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
	 * @Getter @Lazy
	 * private final Optional<List<MyListener>> listeners;
	 * }</pre>
	 */
	protected Optional<? extends List<? extends EntityUpdateListener>> getListeners() {
		return null;
	}

	/**
	 * override this to return true, and make the class implement
	 * {@link EntityUpdateListener}, to have its own caches invalidated on entity
	 * update. Code to override :
	 *
	 * <pre>{@code
	 * @Getter(lazy = true)
	 * private final boolean selfInvalidate = true;
	 * }</pre>
	 */
	protected boolean isSelfInvalidate() {
		return false;
	}


}
