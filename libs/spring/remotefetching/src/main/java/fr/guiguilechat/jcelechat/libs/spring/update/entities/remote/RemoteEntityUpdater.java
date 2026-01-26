package fr.guiguilechat.jcelechat.libs.spring.update.entities.remote;

import java.time.Instant;
import java.util.ArrayList;
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
import java.util.stream.Collectors;

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
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.LocalEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.LocalEntityUpdater;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class RemoteEntityUpdater<
		Entity extends RemoteEntity<IdType, Fetched>,
		IdType extends Number,
		Fetched,
		Repository extends RemoteEntityRepository<Entity, IdType>,
		Service extends RemoteEntityService<Entity, IdType, Repository>
	>extends LocalEntityUpdater<Entity, IdType, Repository, Service> {

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
		 *         It may not be fetched if {@link #isActivateNewEntry()} is false
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
		 *         entities that are fetched for 204 (no body) or 304 (no change) are
		 *         mapped to null so that they are still considered as a success,
		 *         but are not processed
		 */
		protected Map<Entity, Fetched> fetchData(List<Entity> entities) {
			Map<Entity, Future<Requested<Fetched>>> dataToFuture = entities.stream()
					.collect(Collectors.toMap(d -> d,
							d -> executionService().submit(
									() -> fetchData(d.getId(), d.getLastEtag()))));
			Map<Entity, Requested<Fetched>> dataToRequested = new HashMap<>();
			List<Requested<?>> responses = new ArrayList<>();
			for (Entry<Entity, Future<Requested<Fetched>>> e : dataToFuture.entrySet()) {
				try {
					Requested<Fetched> response = e.getValue().get(5000, TimeUnit.SECONDS);
					dataToRequested.put(e.getKey(), response);
					responses.add(response);
				} catch (InterruptedException | ExecutionException | TimeoutException e1) {
					dataToRequested.put(e.getKey(), null);
				}
			}
			processEsiResponses(responses);
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
					case 4 -> updateRequestError(data, response);
					case 5 -> updateServerError(data, response);
					default -> {
						log.error("while updating {} id {}, received response code {} and error {}",
								data.getClass().getSimpleName(), data.getId(), responseCode, response.getError());
						throw new UnsupportedOperationException(
								"case " + responseCode + " not handled for url" + response.getURL());
					}
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
		 *         fetched if the {@link #isActivateNewEntry()} is false ; may be
		 *         empty if not created.
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
		 * Called when the request returned a 304 (no change). Defaults to
		 * {@link #updateMetaOk(RemoteEntity, Requested)}
		 */
		protected void updateNoChange(Entity data, Requested<Fetched> response) {
			updateMetaOk(data, response);
		}

		/**
		 * Called when the request returned a 4xx .
		 * delegates to some known code, can be overridden to handle more.
		 */
		protected void updateRequestError(Entity data, Requested<Fetched> response) {
			if (response.getResponseCode() == 420) {
				update420(data, response);
				return;
			}

			data.setFetchPriority(-1);
			updateExpiresError(data, response.getResponseCode());
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
			case 429:
				update429(data, response);
				return;
			default:
				log.warn("unhandled error code {} for service {}", response.getResponseCode(), serviceName());
			}
		}

		protected void updateExpiresError(Entity data, int respCode) {
			int nbErrors = data.increaseSuccessiveErrors();
			int delayBase = delayBase(respCode);
			int delayInc = delayInc(respCode);
			data.setExpiresIn(delayBase + delayInc * (nbErrors - 1));
		}

		/**
		 * banned. should not be called because the esi status should
		 * already have returned banned
		 */
		protected void update401(Entity data, Requested<Fetched> response) {
		}

		/** data is not authorized ? Can happen when status changed. */
		protected void update403(Entity data, Requested<Fetched> response) {
		}

		/** data is removed. After 5 errors we deactivate fetch. */
		protected void update404(Entity data, Requested<Fetched> response) {
			data.setRemoved(true);
			if (data.getSuccessiveErrors() > 4) {
				data.setFetchActive(false);
			}
		}

		/**
		 * path has specific rate limit not handled. Ignore generally, implement
		 * per-service.
		 */
		protected void update429(Entity data, Requested<Fetched> response) {
		}

		/** error limit exceeded */
		protected void update420(Entity data, Requested<Fetched> response) {
			data.setExpires(response.getErrorsResetInstant());
		}

		/**
		 * Called when the request returned a 5xx
		 */
		protected void updateServerError(Entity data, Requested<Fetched> response) {
			updateExpiresError(data, response.getResponseCode());
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
		 * be overriden by services which are likely to call it.
		 */
		protected void updateNullBody(Entity data, Requested<Fetched> response) {
			updateMetaOk(data, response);
			log.warn("{} received null body (204) for entity {}", serviceName(), data.getId());
		}

		@Override
		public String propertiesAsString() {
			try {
				ObjectMapper om = JsonMapper.builder().configure(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES, true)
						.build();
				return om.writeValueAsString(propertiesMap());
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * @return the properties set at startup, by field name, to be printed when
		 *         debug is activated.
		 */
		protected Map<String, Object> propertiesMap() {
			return Map.of("update", getUpdate());
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
				log.debug(" {} updated {}/{} in {} ms, remain {}",
						serviceName(),
						nbSuccess, nbUpdates,
						endTimeMs - startTimeMs, nbRemain);
			}
			return nbUpdates > 0;
		}

		private Instant lastUpdateTime = null;

		/**
		 * for those remote 1-1 entities, we also enforce the rate limit, in addition to
		 * {@link LocalEntityService#maxAllowedQueries}
		 */
		@Override
		protected int maxAllowedQueries() {
			int maxFromCycle = super.maxAllowedQueries();
			// define qtty to get from the rate
			int maxFromRate = getUpdate().getMax();
			if (lastUpdateTime != null) {
				long elapsedms = Instant.now().toEpochMilli() - lastUpdateTime.toEpochMilli();
				maxFromRate = (int) (getUpdate().getRate() * elapsedms / 1000 - lastBatchSize);
			}
			int ret = Math.min(maxFromCycle, maxFromRate);
			if (ret != maxFromCycle) {
				log.debug("  {} reduced max queries to {} from : rate={} cycle={}",
						serviceName(),
						ret,
						maxFromRate,
						maxFromCycle);
			}
			return ret;
		}

		/**
		 * List the next entities to update.
		 * <p>
		 * This limits the number of entities to update based on
		 * {@link #maxAllowedQueries()}
		 * </p>
		 *
		 * @return the entities that are to be updated in the next cycle
		 */
		protected List<Entity> listToUpdate() {
			lastBatchSize = maxAllowedQueries();
			List<Entity> ret = lastBatchSize < 1
					? List.of()
					: repo().findByFetchActiveTrueAndExpiresBeforeOrderByFetchPriorityDescExpiresAsc(Instant.now(),
							Limit.of(lastBatchSize));
			log.debug(" {} listed {} entities to update with max batch size {}",
					serviceName(),
					ret.size(),
					lastBatchSize);
			return ret;
		}

		/**
		 * update a list of entities
		 *
		 * @param data entities to update from the remote
		 * @return number of entities that were successfully updated. Those which were
		 *         not changed, or failed, are not counted.
		 */
		protected int update(List<Entity> data) {
			log.trace(" {} updating {} entities", serviceName(), data.size());
			Map<Entity, Fetched> successes = fetchData(data);
			int nbSuccess = successes.size();
			// remove those with null Fetched : they are 304
			successes.entrySet().removeIf(e -> e.getValue() == null);
			if (!successes.isEmpty()) {
				updateResponseOk(successes);
			}
			saveAll(data);
			log.trace(" {} updated {} entities with {} changes", serviceName(), data.size(), successes.size());
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
