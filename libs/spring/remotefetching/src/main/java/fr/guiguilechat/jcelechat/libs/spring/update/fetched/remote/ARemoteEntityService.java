package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResourceService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public abstract class ARemoteEntityService<Entity extends ARemoteEntity<IdType, Fetched>,
IdType extends Number,
Fetched,
Repository extends IRemoteEntityRepository<Entity, IdType>>
extends AFetchedResourceService<Entity, IdType, Repository> {

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
	// updating entity data
	//

	/**
	 * create the entity if needed, then fetch it and return it once fetched or
	 * failed.
	 * <p>
	 * Should not be called in response to user action, since it can force
	 * the ESI usage and produce a ban.
	 * </p>
	 *
	 * @param entityId id for the entity we want
	 * @return a managed entity, fetched if it should, or null if exception caught.
	 *           It may not be fetched if {@link #isActivateNewEntry()} is false
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Entity createFetch(IdType entityId) {
		Entity e = repo().findById(entityId).orElse(null);
		if (e == null) {
			e = save(createMinimal(entityId));
		}
		if (!e.isFetched() && e.isFetchActive()) {
			log.trace("entry of class {} for id {} needs fetching", e.getClass().getSimpleName(), entityId);
			try {
				return update(e).get();
			} catch (InterruptedException | ExecutionException ex) {
				log.error("while fetching id " + entityId, ex);
				return null;
			}
		} else {
			log.trace("no need to update entry of class {} for id {}", e.getClass().getSimpleName(), entityId);
			return e;
		}
	}

	protected abstract Requested<Fetched> fetchData(IdType id, Map<String, String> properties);

	protected Requested<Fetched> fetchData(IdType id, String lastEtag) {
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		return fetchData(id, properties);
	}

	/**
	 * do the fetching of a list of entities. <br />
	 * The entities that could not be fetched have their meta data updated.
	 *
	 * @param entities
	 * @return The map of successful fetch, from the entity to the fetch result .
	 *           entities that are fetched for 204 (no body) or 304 (no change) are
	 *           mapped to null so that they are still considered as a success,
	 *           but are not processed
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
			case 204: // no content => null
				updateNullBody(data, response);
				responseOk.put(data, null);
				break;
			case 304:
				updateNoChange(data, response);
				responseOk.put(data, null);
				break;
			default:
				switch (responseCode / 100) {
				case 4:
					updateRequestError(data, response);
					break;
				case 5:
					updateServerError(data, response);
					break;
				default:
					log.error("while updating {} id {}, received response code {} and error {}",
							data.getClass().getSimpleName(), data.getId(), responseCode, response.getError());
					throw new UnsupportedOperationException("case " + responseCode + " not handled for url" + response.getURL());
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
	public Optional<Entity> getExistingFetched(IdType id) {
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
	 * responses, as well as 304, are filtered out before this call.
	 *
	 * @param responseOk map of entities updated to their ok response.
	 */
	protected void updateResponseOk(Map<Entity, Fetched> responseOk) {
		responseOk.entrySet().stream()
		.forEach(e -> {
			e.getKey().update(e.getValue());
		});
	}

	/**
	 * update meta data from an ok response. Called before the
	 * {@link #updateResponseOk(Map)} is invoked.
	 */
	protected void updateMetaOk(Entity data, Requested<Fetched> response) {
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
		if (ret != null && ret.getEpochSecond() != 0L) {
			return ret;
		}
		Instant date = Instant.now();
		if (response.getHeaders().containsKey(Requested.DATE_PROP)) {
			String datestr = response.getHeaders().get(Requested.DATE_PROP).stream().findFirst().orElse(null);
			if (datestr != null) {
				date = ESIDateTools.headerInstant(datestr);
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
	 * Called when the request returned a 4xx .
	 * delegates to some known code, can be overridden to handle more.
	 */
	protected void updateRequestError(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		switch (response.getResponseCode()) {
		case 401:
			update401(data, response);
			return;
		case 403:
			update403(data, response);
			return;
		case 404:
			update404(data, response);
			return;
		case 420:
			update420(data, response);
			return;
		default:
			log.warn("unhandled error code {} for service {}", response.getResponseCode(), fetcherName());
			data.setExpires(Instant.now().plus(Math.min(nbErrors, 24), ChronoUnit.HOURS));
		}
	}

	/** banned. Wait a day */
	protected void update401(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		data.setExpires(Instant.now().plus(Math.min(nbErrors * 12, 48), ChronoUnit.HOURS));
	}

	/** data is not authorized ? Can happen when status changed. */
	protected void update403(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		data.setExpires(Instant.now().plus(Math.min(nbErrors, 24), ChronoUnit.HOURS));
	}

	/** data is removed. After 5 errors we deactivate fetch. */
	protected void update404(Entity data, Requested<Fetched> response) {
		int nbErrors = data.increaseSuccessiveErrors();
		data.setExpires(Instant.now().plus(Math.min(nbErrors, 24), ChronoUnit.HOURS));
		data.setRemoved(true);
		if (nbErrors > 4) {
			data.setFetchActive(false);
		}
	}

	/** error limit exceeded */
	protected void update420(Entity data, Requested<Fetched> response) {
		data.setExpires(response.getErrorsResetInstant());
	}

	/**
	 * Called when the request returned a 5xx
	 */
	protected void updateServerError(Entity data, Requested<Fetched> response) {
		data.setExpires(Instant.now().plus(5, ChronoUnit.MINUTES));
	}

	/**
	 * called when fetching given data returned null response
	 */
	protected void updateNullResponse(Entity data) {
		log.warn("received null response when requesting update for {} id={}", data.getClass().getSimpleName(),
				data.getId());
		data.increaseSuccessiveErrors();
		data.setExpiresInRandom(data.getSuccessiveErrors() * 60);
	}

	/**
	 * called when entity received 204 no content. Default implementation is to save
	 * the meta as OK then log a warning. Since the actual use case in this case
	 * heavily depends on the underlying updated entity, this method is expected to
	 * be overriden by services which are likly to call it.
	 */
	protected void updateNullBody(Entity data, Requested<Fetched> response) {
		updateMetaOk(data, response);
		log.warn("{} received null body (204) for entity {}", fetcherName(), data.getId());
	}

	//
	// pre update is fetching new elements if possible
	//

	//
	// list updating methods
	// those are only used for services whose entities are static and listed.
	// to implement, just override the listFetcher()
	//

	@Getter
	@Setter
	@ToString()
	public class ListConfig {

		private boolean skip = false;

		private List<IdType> init = null;

		/** minimum delay, in s, between two listing. Ignored if &lt;0 */
		private int delay = 10;

	}

	@Getter
	private final ListConfig list = new ListConfig();

	@Override
	public String propertiesAsString() {
		try {
			ObjectMapper om = JsonMapper.builder().configure(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES, true).build();
			return om.writeValueAsString(Map.of(
					"update", getUpdate(),
					"list", getList()));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private String lastListEtag = null;
	private Instant listExpires = null;

	/** check new entries */

	@Override
	protected void preUpdate() {
		super.preUpdate();
		if (!list.skip && (listExpires == null || listExpires.isBefore(Instant.now()))) {
			Function<Map<String, String>, Requested<List<IdType>>> fetcher = listFetcher();
			if (fetcher != null) {
				int nbRemainingErrors = esiStatusService().availErrors();
				if (nbRemainingErrors <= getUpdate().getErrorsMin()) {
					log.trace("{} skip pre update as only {} remaining errors", fetcherName(), nbRemainingErrors);
					return;
				}
				long startms = System.currentTimeMillis();
				log.trace("{} started listing new entries", fetcherName());
				Map<String, String> properties = new HashMap<>();
				if (lastListEtag != null) {
					properties.put(ConnectedImpl.IFNONEMATCH, lastListEtag);
				}
				Requested<List<IdType>> resp = fetcher.apply(properties);
				if (resp != null) {
					switch (resp.getResponseCode()) {
					case 200:
						long postFetch = System.currentTimeMillis();
						log.debug(" {} listed {} entries in {}s", fetcherName(), resp.getOK().size(), (postFetch - startms) / 1000);
						onNewListFetched(insertIfAbsent(resp.getOK()));
						lastListEtag = resp.getETag();
						listExpires = resp.getExpiresInstant();
						break;
					case 304:
						listExpires = resp.getExpiresInstant();
						log.trace(" {} received no list change", fetcherName());
						break;
					default:
						log.warn("update service {} received invalid response {} when requesting list of entities",
								getClass().getSimpleName(), resp);
					}
				} else {
					log.warn("update service {} received null list of entities",
							getClass().getSimpleName());
				}
				if (getList().getDelay() > 0) {
					Instant nextListFromDelay = Instant.now().plusSeconds(getList().getDelay());
					if (nextListFromDelay.isAfter(listExpires)) {
						listExpires = nextListFromDelay;
					}
				}
				long endms = System.currentTimeMillis();
				log.trace("{} finished listing new entries in {}s", fetcherName(), (endms - startms) / 1000);
			}
		}
	}

	/** called when new ids have been listed */
	protected void onNewListFetched(Set<IdType> newIds) {

	}

	/**
	 * function to list the ids from the remote
	 *
	 * @param properties should contain the etags
	 */
	protected Requested<List<IdType>> listRemoteIds(Map<String, String> properties) {
		return null;
	}

	/**
	 * the method to overwrite to make it auto fetch entities.
	 * <p>
	 * The typical implementation, if exists, is to override and call
	 * {@link #listRemoteIds(Map)} eg
	 *
	 * <pre>{@code
	 * protected Function<Map<String, String>, Requested<List<IdType>>> listFetcher() {
	 *   return this::listRemoteIds;
	 * }
	 * }</pre>
	 *
	 * If there is no listing method, then returning null (default implementation)
	 * implies no listing and the updater will skip this phase
	 * </p>
	 *
	 * @return
	 */
	protected Function<Map<String, String>, Requested<List<IdType>>> listFetcher() {
		return null;
	}

	/**
	 * create the entities with id specified in the configuration at startup.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void addListInit() {
		if (list.init != null && !list.init.isEmpty()) {
			log.trace("{} init={}", fetcherName(), list.init);
			insertIfAbsent(list.init);
		}
	}

	//
	// actual update
	//

	protected int lastBatchSize = 0;

	/**
	 * @return number of remaining entities that could be updated
	 */
	@Override
	public long nbToUpdate() {
		return repo().countByFetchActiveTrueAndExpiresBefore(Instant.now());
	}

	@Override
	protected boolean fetchUpdate() {
		long startTimeMs = System.currentTimeMillis();
		List<Entity> list = listToUpdate();
		int nbUpdates = list.size();
		if (nbUpdates > 0) {
			int nbSuccess = update(list);
			long nbRemain = nbToUpdate();
			long endTimeMs = System.currentTimeMillis();
			log.debug("{} updated {}/{} in {} ms, remain {}",
					fetcherName(),
					nbSuccess, nbUpdates,
					endTimeMs - startTimeMs, nbRemain);
		}
		return nbUpdates > 0;
	}

	/**
	 * set the expiry of the data that need fetching to 30 days before. This
	 * ensures they will be prioritized on the next update pulse. Note that this
	 * does not make their fetch active if they are not already ; on the contrary,
	 * those with fetch inactive won't be updated.
	 *
	 * @param datas
	 */
	@Transactional
	public void prioritize(Iterable<Entity> datas) {
		List<Entity> updated = StreamSupport.stream(datas.spliterator(), false)
				.filter(d -> d.isFetchActive() &&
						(!d.isFetched()
								|| d.getExpires() != null
								&& d.getExpires().isBefore(Instant.now())))
				.peek(d -> d.setExpires(d.getExpires().minus(30, ChronoUnit.DAYS)))
				.toList();
		saveAll(updated);
	}

	private Instant lastUpdateTime = null;

	protected int nextBatchSize() {
		// define qtty to get from the cycle and errors
		int maxFromCycle = getUpdate().getMax();
		int remainErrors = esiStatusService().availErrors();
		if (remainErrors <= getUpdate().getErrorsMin()) {
			log.trace("{} skip updates as only {} remaining errors", fetcherName(), remainErrors);
			return 0;
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
		log.debug(" {} max={} from rate={} cycle={}", fetcherName(), ret, maxFromRate, maxFromCycle);
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
		List<Entity> ret = lastBatchSize < 1
				? List.of()
						: repo().findByFetchActiveTrueAndExpiresBeforeOrderByExpiresAsc(Instant.now(), Limit.of(lastBatchSize));
		log.trace(" {} has {} entities to update with max batch size {}", fetcherName(), ret.size(), lastBatchSize);
		return ret;
	}

	/**
	 * update a list of entities
	 *
	 * @param data entities to update from the remote
	 * @return number of entities that were successfully updated. Those which were
	 *           not changed, or failed, are not counted.
	 */
	protected int update(List<Entity> data) {
		log.trace("{} updating {} entities", fetcherName(), data.size());
		Map<Entity, Fetched> successes = fetchData(data);
		int nbSuccess = successes.size();
		// remove those with null Fetched : they are 304
		successes.entrySet().removeIf(e -> e.getValue() == null);
		if (!successes.isEmpty()) {
			updateResponseOk(successes);
		}
		saveAll(data);
		log.trace(" {} updated {} entities with {} changes", fetcherName(), data.size(), successes.size());
		return nbSuccess;
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
