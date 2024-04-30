package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.ACharDataRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService.EsiUserListener;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class ACharDataService<Entity extends ACharData<Fetched>, Fetched, Repository extends ACharDataRepository<Entity>>
		implements EsiUserListener {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	private Repository repo;

	@Autowired
	@Accessors(fluent = true)
	@Lazy
	private EsiUserService userService;

	public Entity save(Entity data) {
		if (data.getCreated() == null) {
			data.setCreated(Instant.now());
		}
		data.setLastUpdate(Instant.now());
		return repo().save(data);
	}

	@Transactional
	@Async
	CompletableFuture<Void> update(Entity data, EsiUser user) {
		// System.err.println("updating data class " + data.getClass().getSimpleName() +
		// " for character "
		// + data.getCharacterId() + " with refresh token " + user.getRefreshToken());
		String lastEtag = data.getLastEtag();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		try {
		Requested<Fetched> response = fetchCharacterData(
				new ESIConnected(user.getRefreshToken(), user.getApp().getAppBase64()), user.getCharacterId(), properties);
		int responseCode = response.getResponseCode();
		switch (responseCode) {
			case 200:
				data.update(response.getExpiresInstant(), response.getETag());
				data.update(response.getOK());
				data = save(data);
			break;
			case 304:
				data.setExpires(response.getExpiresInstant());
				data = save(data);
			break;
			default:
				log.error("while updating character {} info class {}, received response code {} and error {}",
				    user.getCharacterId(), data.getClass().getSimpleName(), responseCode, response.getError());
		}
	} catch (Exception e) {
		log.error("while updating " + data.getClass().getSimpleName() + " for char " + user.getCharacterId(), e);
	}
		return CompletableFuture.completedFuture(null);
	}

	protected abstract Requested<Fetched> fetchCharacterData(ESIConnected esiConnected, int characterId,
			Map<String, String> properties);

	protected Set<String> getRequiredScopes() {
		return Set.of();
	}

	protected boolean defaultActivation() {
		return true;
	}

	@Override
	public void onNewEsiUser(EsiUser user) {
// System.err.println("received new esi user");
if (user.getScopes().containsAll(getRequiredScopes())) {
			Optional<Entity> op = repo.findById(user.getCharacterId());
			if (op.isEmpty()) {
				Entity e = create(user.getCharacterId());
				e.setActive(defaultActivation());
				repo.save(e);
				log.error("created new entry of class {} for character {}", e.getClass().getSimpleName(),
						user.getCharacterName());
			} else {
				log.error("no need to create new entry for character {}", user.getCharacterName());
			}
		} else {
			log.error("new user entry for characdter {} missing scopes", user.getCharacterName());
		}
	}

	protected abstract Entity create(int characterId);

	protected Optional<Entity> forCharacterId(int characterId) {
		return repo.findById(characterId);
	}

	public Optional<Entity> fetchIfNeeded(int characterId) {
		Optional<Entity> ret = forCharacterId(characterId);
		if (ret.isEmpty() || ret.get().isFetched()) {
			return ret;
		}
		try {
			update(ret.get(), esiUser(characterId)).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("while fetching " + ret.get().getClass() + " for character id" + characterId, e);
		}
		return ret;
	}

	protected EsiUser esiUser(int characterId) {
		return userService().forCharacterId(characterId).stream()
		    .filter(user -> user.getScopes().containsAll(getRequiredScopes())).findAny().orElse(null);
	}


}
