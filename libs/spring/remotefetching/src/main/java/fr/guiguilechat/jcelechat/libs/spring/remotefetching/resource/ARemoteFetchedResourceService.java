package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.status.ESIStatusService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class ARemoteFetchedResourceService<Entity extends ARemoteFetchedResource<Id, Fetched>, Id, Fetched, Repository extends IRemoteFetchedResourceRepository<Entity, Id>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private Repository repo;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private ESIStatusService esiStatusService;

	/**
	 * @return actual class name. Used to avoid proxy name when called from outside
	 *           service
	 */
	public String fetcherName() {
		return getClass().getSimpleName();
	}

	protected void preSave(Entity data) {
		if (data.getExpires() == null) {
			data.setExpires(Instant.now());
		}
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

	protected boolean isActivateNewEntry() {
		return true;
	}

	protected abstract Entity create(Id entityId);

	protected Entity createIfNeeded(Entity e, Id entityId) {
		if (e == null) {
			e = create(entityId);
			e.setFetchActive(isActivateNewEntry());
			e = save(e);
			log.trace("create entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
		}
		return e;
	}

	protected CompletableFuture<Entity> createFetchIfNeeded(Entity e, Id entityId) {
		e = createIfNeeded(e, entityId);
		if (!e.isFetched() && e.isFetchActive()) {
			log.trace("entry of class {} for id {} needs fetching", e.getClass().getSimpleName(), entityId);
			return update(e);
		} else {
			log.trace("no need to update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
			return CompletableFuture.completedFuture(e);
		}
	}

	/**
	 * ensure an entity for given id exists, does not start fetch
	 * 
	 * @param entityId new Id for the entity
	 * @return entity for corresponding id
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Entity createIfAbsent(Id entityId) {
			return createIfNeeded(repo().findById(entityId).orElse(null), entityId);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Id, Entity> createIfAbsent(Collection<Id> entityIds) {
			Map<Id, Entity> storedEntities = repo().findAllById(entityIds).stream()
			    .collect(Collectors.toMap(ARemoteFetchedResource::getId, e -> e));
			return entityIds.stream().distinct().collect(Collectors.toMap(ei -> ei,
			    entityId -> createIfNeeded(storedEntities.get(entityId), entityId)));
	}

	/**
	 * create the entity if needed, then fetch it and return it once fetched or
	 * failed.
	 * 
	 * @param entityId id for the entity we want
	 * @return a managed entity, fetched if it should, or null if exception caught.
	 *           It may not be fetched if {@link #isActivateNewEntry()} is false
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Entity createFetch(Id entityId) {
		try {
			return createFetchIfNeeded(repo().findById(entityId).orElse(null), entityId).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("while fetching id " + entityId, e);
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<Id, CompletableFuture<Entity>> createFetchIfNeeded(Collection<Id> entityIds) {
			Map<Id, Entity> storedEntities = repo().findAllById(entityIds).stream()
			    .collect(Collectors.toMap(ARemoteFetchedResource::getId, e -> e));
			return entityIds.stream().distinct().collect(Collectors.toMap(ei -> ei,
			    entityId -> createFetchIfNeeded(storedEntities.get(entityId), entityId)));
	}

	protected abstract Requested<Fetched> fetchData(Id id, Map<String, String> properties);

	/**
	 * if an entity exists for an id, update it if needed, then return it.
	 * Use {@link #createFetch(Object)} to create it if absent.
	 * 
	 * @return entity for given id, at least fetched once if needed. May not be
	 *           fetched if the {@link #isActivateNewEntry()} is false ; may be
	 *           empty if not created.
	 */
	public Optional<Entity> getExistingFetched(Id id) {
		Optional<Entity> ret = repo().findById(id);
		if (ret.isEmpty() || ret.get().isFetched() || !ret.get().isFetchActive()) {
			return ret;
		}
		try {
			update(ret.get()).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("while fetching " + ret.get().getClass() + " for data id" + id, e);
		}
		return ret;
	}

	AtomicInteger runningUpdates = new AtomicInteger(0);

	/**
	 * perform an update of an entity using its remote representation. If the
	 * entity is updated in any way, it is also saved already.
	 * 
	 * @param data the entity to update
	 * @return empty completable future to synchronize over.
	 */
	@Transactional
	@Async
	public CompletableFuture<Entity> update(Entity data) {
		int conc = runningUpdates.incrementAndGet();
		log.trace("requested to update {} {}, service concurrent {}", data.getClass().getSimpleName(), data.getId(), conc);
		try {
			String lastEtag = data.getLastEtag();
			Map<String, String> properties = new HashMap<>();
			if (lastEtag != null) {
				properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
			}
			try {
				Requested<Fetched> response = fetchData(data.getId(), properties);
				if (response == null) {
					updateNullResponse(data);
					return CompletableFuture.completedFuture(null);
				}
				int responseCode = response.getResponseCode();
				switch (responseCode) {
				case 200:
					updateResponseOk(data, response);
					log.debug(" updated data " + data.getClass().getSimpleName() + " for id " + data.getId()
					    + " with expires=" + data.getExpires());
					break;
				case 304:
					updateNoChange(data, response);
					break;
				default:
					log.error("while updating data remoteid {} info class {}, received response code {} and error {}",
					    data.getId(), data.getClass().getSimpleName(), responseCode, response.getError());
					switch (responseCode / 100) {
					case 4:
						updateRequestError(data, response);
						break;
					case 5:
						updateServerError(data, response);
						break;
					default:
						throw new UnsupportedOperationException("case " + responseCode + " not handled");
					}
				}
				data = save(data);
			} catch (Exception e) {
				log.error("while updating " + data.getClass().getSimpleName() + " for data remoteid " + data.getId(), e);
			}
			return CompletableFuture.completedFuture(data);
		} finally {
			conc = runningUpdates.decrementAndGet();
			log.trace(" updated {} {}, remaining service concurrent {}" + data.getClass().getSimpleName(), data.getId(),
			    conc);
		}
	}

	/**
	 * called when an ok is received for given data. data is saved after that call.
	 * 
	 * @param data     data that should be updated
	 * @param response remote response for that data update.
	 */
	protected void updateResponseOk(Entity data, Requested<Fetched> response) {
		updateMetaOk(data, response);
		data.update(response.getOK());
	}

	/**
	 * update meta data from an ok response
	 */
	protected void updateMetaOk(Entity data, Requested<?> response) {
		data.updateMetaOk(response.getLastModifiedInstant(), extractExpires(response), response.getETag());
	}

	/**
	 * number of seconds we add to now() to create the expires, if missing
	 * can be changed with
	 * {@code  @Getter(lazy=true) private final int defaultExpiresDelaySeconds=1000;}
	 */
	protected int getDefaultExpiresDelaySeconds() {
		return 3600;
	}

	/**
	 * extract the expires
	 * 
	 * @param headers map
	 * @return the next moment after which we can fetch an entity again.
	 */
	protected Instant extractExpires(Requested<?> response) {
		Instant ret = response.getExpiresInstant();
		if (ret != null && ret.getEpochSecond() != 0l) {
			return ret;
		}
		Instant date = Instant.now();
		if (response.getHeaders().containsKey(Requested.DATE_PROP)) {
			String datestr = response.getHeaders().get(Requested.DATE_PROP).stream().findFirst().orElse(null);
			if (datestr != null) {
				date = ESITools.headerInstant(datestr);
			}
		}
		return date.plusSeconds(getDefaultExpiresDelaySeconds());
	}

	/**
	 * Called when the request returned a 304
	 */
	protected void updateNoChange(Entity data, Requested<Fetched> response) {
		data.setExpires(response.getExpiresInstant());
	}

	/**
	 * Called when the request returned a 4xx
	 */
	protected void updateRequestError(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		switch (response.getResponseCode()) {
		case 401: // banned. Wait a day
			data.setExpires(Instant.now().plus(nbErrors * 12, ChronoUnit.HOURS));
			return;
		default:
			data.setExpires(Instant.now().plus(nbErrors, ChronoUnit.HOURS));
		}

	}

	/**
	 * Called when the request returned a 5xx
	 */
	protected void updateServerError(Entity data, Requested<Fetched> response) {
		data.setExpires(Instant.now().plus(5, ChronoUnit.MINUTES));
	}

	protected void updateNullResponse(Entity data) {
		log.warn("received null response when requesting update for {} id={}", data.getClass().getSimpleName(),
		    data.getId());
		data.increaseSuccessiveErrors();
		data.setExpiresInRandom(data.getSuccessiveErrors() * 60);
	}
	
	@Getter
	@Setter
	@ToString()
	public static class Update{

		/**
		 * if true, skip the fetch. If false, never skip. if null, use
		 * RemoteResourceUpdaterService value
		 */
		private Boolean skip=null;

		/** nax number of fetch each cycle */
		private int max = 500;

		/** if we have this number or more remain errors, use max updates */
		private int errorsForMax = 90;

		/** if we have this number or less remaining errors, we skip the fetching */
		private int errorsMin = 10;

		/** minimum delay, in s, between two fetch cycles. Ignored if &lt;0 */
		private int delay = 0;

		/**
		 * delay to wait for next fetch cycle when there is no update. Ignored if lower
		 * than {@link #getDelay()}
		 */
		private int delayUpdated = 60;
	}
	
	@Getter
	private final Update update = new Update();

	private Instant nextUpdateTime = null;

	/**
	 * @return number of remaining entities that could be updated
	 */
	public long nbToUpdate() {
		return repo().countByFetchActiveTrueAndExpiresLessThan(Instant.now());
	}

	/**
	 * you can override that to use a dedicated method in the jpa repostiory, eg to
	 * allow more than 1000 entities to update at once.
	 * 
	 * @return the next entities that are to be updated, limited to
	 *           {@link #getMaxUpdates()} and 1000 from implementation limit,
	 *           whichever is lowest.
	 */
	public Stream<Entity> streamToUpdate() {
		if (nextUpdateTime != null && nextUpdateTime.isAfter(Instant.now())) {
			return Stream.empty();
		}
		int delay = Math.max(update.delay, 0);
		if (update.delayUpdated > delay && nbToUpdate() == 0) {
			delay = update.delayUpdated;
		}
		nextUpdateTime = Instant.now().plusSeconds(delay);
		int maxFromErrors = getUpdate().getMax();
		int remainErrors = esiStatusService.availErrors();
		if (remainErrors <= update.errorsMin) {
			maxFromErrors = 0;
		} else if (remainErrors < update.errorsForMax) {
			maxFromErrors = (int) Math.ceil(1.0 * maxFromErrors * update.errorsForMax / remainErrors);
		}
		return maxFromErrors == 0
		    ? Stream.empty()
		    : repo.findByFetchActiveTrueAndExpiresLessThanOrderByExpiresAsc(Instant.now(), Limit.of(maxFromErrors)).stream();
	}

	/**
	 * overridable default false.
	 * 
	 * @return true only when {@link #batchUpdate(List)} has built-in better
	 *           performances than calling an update on each element of the list
	 *           sequentially.
	 */
	public boolean isSupportsBatchUpdate() {
		return false;
	}

	/**
	 * batch update. Default is to call each in parallel stream, if you use a
	 * better implementation don't forget to replace
	 * {@link #isSupportsBatchUpdate()} as true eg with <br />
	 * {@code @Getter(lazy=true) private final boolean supportsBatchUpdate=true;}
	 * 
	 * @param data list of entities we want to update
	 * @return for each entity updated, the future to wait for the update to be
	 *           done. If they are all updated once the call is done, return empty
	 *           map instead.
	 */
	public Map<Entity, CompletableFuture<Entity>> batchUpdate(List<Entity> data) {
		log.trace(" updating list of {} elements service {}", data.size(), getClass().getSimpleName());
		return data.parallelStream().limit(getUpdate().getMax()).collect(Collectors.toMap(e -> e, this::update));
	}

	//
	// list updating methods
	// those are only used for services whose entities are static and listed.
	// to implement, just override the listFetcher()
	//

	private String lastListEtag = null;
	private Instant listExpires = null;

	/** check new entries */
	public void checkNewEntries() {
		if (listExpires == null || listExpires.isBefore(Instant.now())) {
			Function<Map<String, String>, Requested<List<Id>>> fetcher = listFetcher();
			if (fetcher != null) {
				Map<String, String> properties = new HashMap<>();
				if (lastListEtag != null) {
					properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
				}
				Requested<List<Id>> resp = fetcher.apply(properties);
				if (resp == null || !resp.isOk()) {
					log.warn("update service {} received invalid response {} when requesting list of entities",
					    getClass().getSimpleName(), resp);
					return;
				} else {
					createIfAbsent(resp.getOK());
					lastListEtag = resp.getETag();
					listExpires = resp.getExpiresInstant();
				}
			}
		}
	}

	/** the method to overwrite to make it auto fetch entities */
	protected Function<Map<String, String>, Requested<List<Id>>> listFetcher() {
		return null;
	}

	@PostConstruct
	public void debugConfig() {
		log.debug("initialized {} with {}", getClass().getSimpleName(), getUpdate());
	}

}
