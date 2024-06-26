package fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResourceService;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class ARemoteResourceService<
			Entity extends ARemoteResource<Id, Fetched>,
			Id,
			Fetched,
			Repository extends IRemoteResourceRepository<Entity, Id>>
    extends AFetchedResourceService<Entity, Id, Repository> {


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
	protected Entity createMinimal(Id entityId) {
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

	protected CompletableFuture<Entity> createFetchIfNeeded(Entity e, Id entityId) {
		if (e == null) {
			e = save(createMinimal(entityId));
		}
		if (!e.isFetched() && e.isFetchActive()) {
			log.trace("entry of class {} for id {} needs fetching", e.getClass().getSimpleName(), entityId);
			return update(e);
		} else {
			log.trace("no need to update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
			return CompletableFuture.completedFuture(e);
		}
	}

	//
	// updating entity data
	//

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
		    .collect(Collectors.toMap(ARemoteResource::getId, e -> e));
		return entityIds.stream().distinct().collect(Collectors.toMap(ei -> ei,
		    entityId -> createFetchIfNeeded(storedEntities.get(entityId), entityId)));
	}

	protected abstract Requested<Fetched> fetchData(Id id, Map<String, String> properties);

	protected Requested<Fetched> fetchData(Id id, String lastEtag) {
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		return fetchData(id, properties);
	}

	/**
	 * do the fetching of a list of entities. <br />
	 * The entities that could not be fetched will be updated, but not saved, with
	 * corresponding (meta) values.
	 * 
	 * @param entities
	 * @return The map of successful fetch, from the entitiy to the fetch result
	 */
	protected Map<Entity, Fetched> fetchData(List<Entity> entities) {
		Map<Entity, Future<Requested<Fetched>>> dataToFuture = entities.stream()
		    .collect(Collectors.toMap(d -> d, d -> executionService().submit(() -> fetchData(d.getId(), d.getLastEtag()))));
		Map<Entity, Requested<Fetched>> dataToRequested = new HashMap<>();
		for (Entry<Entity, Future<Requested<Fetched>>> e : dataToFuture.entrySet()) {
			try {
				dataToRequested.put(e.getKey(), e.getValue().get(5000, TimeUnit.SECONDS));
			} catch (InterruptedException | ExecutionException | TimeoutException e1) {
				dataToRequested.put(e.getKey(), null);
			}
		}
		Map<Entity, Fetched> responseOk = new HashMap<>();
		for (Entry<Entity, Requested<Fetched>> e : dataToRequested.entrySet()) {
			Entity data = e.getKey();
			Requested<Fetched> response = e.getValue();
			if (response == null) {
				updateNullResponse(data);
				continue;
			}
			int responseCode = response.getResponseCode();
			switch (responseCode) {
			case 200:
				updateMetaOk(data, response);
				responseOk.put(data, response.getOK());
				break;
			case 304:
				updateNoChange(data, response);
				break;
			default:
				log.error("while updating {} id {}, received response code {} and error {}",
				    data.getClass().getSimpleName(), data.getId(), responseCode, response.getError());
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
		}
		return responseOk;

	}

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
		update(List.of(data));
		return CompletableFuture.completedFuture(data);
	}

	/**
	 * called when a batch of entity update has resulted in ok responses. Non-ok
	 * responses are filtered out.
	 * 
	 * @param responseOk map of entities updated to their ok response.
	 */
	protected void updateResponseOk(Map<Entity, Fetched> responseOk) {
		responseOk.entrySet().stream().forEach(e -> {
			e.getKey().update(e.getValue());
		});
	}

	/**
	 * update meta data from an ok response. Called before the
	 * {@link #updateResponseOk(Map)} is invoked.
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

	@PostConstruct
	public void debugConfig() {
		log.debug("initialized {} with {}", getClass().getSimpleName(), getUpdate());
	}

	//
	// pre update is fetching new elements if possible
	//


	//
	// list updating methods
	// those are only used for services whose entities are static and listed.
	// to implement, just override the listFetcher()
	//

	private String lastListEtag = null;
	private Instant listExpires = null;

	/** check new entries */

	@Override
	protected void preUpdate() {
		if (listExpires == null || listExpires.isBefore(Instant.now())) {
			Function<Map<String, String>, Requested<List<Id>>> fetcher = listFetcher();
			if (fetcher != null) {
				log.trace("{} started listing new entries", fetcherName());
				Map<String, String> properties = new HashMap<>();
				if (lastListEtag != null) {
					properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
				}
				Requested<List<Id>> resp = fetcher.apply(properties);
				if (resp != null) {
					switch (resp.getResponseCode()) {
					case 200:
						onNewListFetched(createIfAbsent(resp.getOK()));
						log.debug(" {} listed {} new entries", fetcherName(), resp.getOK().size());
						lastListEtag = resp.getETag();
						listExpires = resp.getExpiresInstant();
						break;
					case 304:
						listExpires = resp.getExpiresInstant();
						break;
					default:
						log.warn("update service {} received invalid response {} when requesting list of entities",
						    getClass().getSimpleName(), resp);
					}
				} else {
					log.warn("update service {} received null list of entities",
					    getClass().getSimpleName());
				}
				log.trace("{} finished listing new entries", fetcherName());
			}
		}
	}

	/** called when the list has been updated */
	protected void onNewListFetched(Map<Id, Entity> map) {

	}

	/** the method to overwrite to make it auto fetch entities */
	protected Function<Map<String, String>, Requested<List<Id>>> listFetcher() {
		return null;
	}

	//
	// actual update
	//

	private int lastBatchSize = 0;

	/**
	 * @return number of remaining entities that could be updated
	 */
	@Override
	public long nbToUpdate() {
		return repo().countByFetchActiveTrueAndExpiresLessThan(Instant.now());
	}

	@Override
	protected void fetchUpdate() {
		long startTimeMs = System.currentTimeMillis();
		List<Entity> list = listToUpdate();
		int nbUpdates = list.size();
		if (nbUpdates > 0) {
			int nbSuccess = update(list);
			long nbRemain = nbToUpdate();
			long endTimeMs = System.currentTimeMillis();
			log.info("{} updated {}/{} in {} ms, remain {}",
			    fetcherName(),
			    nbSuccess, nbUpdates,
			    endTimeMs - startTimeMs, nbRemain);
		}
	}

	private Instant lastUpdateTime = null;

	protected int nextBatchSize() {
		// define qtty to get from the cycle and errors
		int maxFromCycle = getUpdate().getMax();
		int remainErrors = esiStatusService().availErrors();
		if (remainErrors <= getUpdate().getErrorsMin()) {
			maxFromCycle = 0;
		} else if (remainErrors < getUpdate().getErrorsForMax()) {
			maxFromCycle = (int) Math.ceil(1.0 * maxFromCycle * getUpdate().getErrorsForMax() / remainErrors);
		}
		// define qtty to get from the rate
		int maxFromRate = getUpdate().getMax();
		if (lastUpdateTime != null) {
			long elapsedms = Instant.now().toEpochMilli() - lastUpdateTime.toEpochMilli();
			maxFromRate = (int) (getUpdate().getRate() * elapsedms / 1000 - lastBatchSize);
		}
		int ret = Math.min(maxFromCycle, maxFromRate);
		log.debug(" {} fetch={} limits: rate={} cycle={}", fetcherName(), ret, maxFromRate, maxFromCycle);
		return ret;
	}

	/**
	 * List the next entities to update.
	 * <p>
	 * This is using two limit on the number of returned entities, using the lowest
	 * one of :
	 * <ol>
	 * <li>a per-cycle limit that is then reduced as the errors allowed is
	 * reduced</li>
	 * <li>a per-second limit that is applied assuming instantaneous fetch and
	 * remember the start and batch size of the previous successful call</li>
	 * </ol>
	 * </p>
	 * 
	 * @return the next entities that are to be updated
	 */
	protected List<Entity> listToUpdate() {
		lastBatchSize = nextBatchSize();
		return lastBatchSize < 1
		    ? List.of()
		    : repo().findByFetchActiveTrueAndExpiresLessThanOrderByExpiresAsc(Instant.now(), Limit.of(lastBatchSize));
	}

	protected int update(List<Entity> data) {
		log.trace("{} updating list of {} elements}", fetcherName(), data.size());
		Map<Entity, Fetched> successes = fetchData(data);
		updateResponseOk(successes);
		int success = successes.size();
		saveAll(data);
		log.trace(" {} updated list of {} elements", fetcherName(), data.size());
		return success;
	}

	//
	// post update is saving end of fetch to use as base for rate limit.
	//

	@Override
	protected void postUpdate() {
		super.postUpdate();
		lastUpdateTime = Instant.now();
	}

}
