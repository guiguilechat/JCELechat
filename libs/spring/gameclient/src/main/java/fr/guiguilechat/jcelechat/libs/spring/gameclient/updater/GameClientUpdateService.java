package fr.guiguilechat.jcelechat.libs.spring.gameclient.updater;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdate.Status;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "gameclient.fetcher")
@Order(0) // does not depend on another entity updater
public class GameClientUpdateService implements EntityUpdater {

	final private CacheManager cacheManager;

	final private GameClientUpdateRepository repo;

	private final Optional<List<GameClientUpdateListener>> updateListeners;

	@Getter
	private UpdateConfig update = new UpdateConfig();

	/**
	 * when true, force next fetch
	 */
	@Getter
	@Setter
	private boolean force = false;

	private boolean sqlFailed = false;

	/** moment after which we actually try to fetch */
	private Instant nextFetch = null;

	/**
	 * request to force a fetch, disregarding previous build version, ASAP
	 */
	public void forceNext() {
		sqlFailed = false;
		force = true;
		nextFetch = null;
	}

	public GameClientUpdate findLastSuccess() {
		return repo.findTop1ByStatusInOrderByStartedDateDesc(List.of(Status.SUCCESS));
	}

	public GameClientUpdate save(GameClientUpdate entity) {
		if (entity.getCreatedDate() == null) {
			entity.setCreatedDate(Instant.now());
		}
		return repo.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public boolean fetch() {
		Instant startDate = Instant.now();
		if (nextFetch != null && nextFetch.isAfter(startDate)) {
			return false;
		}
		log.debug("updating Game client");
		GameClientUpdate ur = GameClientUpdate.builder()
				.startedDate(startDate).build();
		GameClientUpdate lastSuccess = findLastSuccess();
		ClientInfo ci = ClientInfo.fetch();
		ur.setBuildNumber(ci.getBuildNumber());
		if (!force && lastSuccess != null && ci.getBuildNumber().equals(lastSuccess.getBuildNumber())) {
			nextFetch = startDate.plusSeconds(getUpdate().getDelay());
			ur.setStatus(Status.CACHED);
			save(ur);
			return false;
		}
		log.info("importing game client build {}", ci.getBuildNumber());
		try {
			ClientCache cache = new ClientCache(ci);
			if (updateListeners.isPresent()) {
				List<GameClientUpdateListener> listeners = updateListeners.get();
				listeners.forEach(GameClientUpdateListener::beforeGameClientUpdate);
				listeners.forEach(l -> l.onGameClientUpdate(cache));
				listeners.stream().flatMap(l -> l.listGameClientCaches().stream())
						.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
				listeners.forEach(GameClientUpdateListener::afterGameClientUpdate);
			}
			ur.setStatus(Status.SUCCESS);
		} catch (Exception e) {
			if (e instanceof SQLException) {
				log.warn("failed with SQL error, won't retry unless requested to force");
				sqlFailed = true;
			}
			ur.setStatus(Status.FAIL);
			ur.setError(e.getMessage());
			log.error("while updating build " + ci.getBuildNumber(), e);
		}
		ur.setEndDate(Instant.now());
		save(ur);
		force = false;
		nextFetch = startDate.plusSeconds(getUpdate().getDelay());
		return !sqlFailed && ur.getStatus() != Status.SUCCESS;
	}

}
