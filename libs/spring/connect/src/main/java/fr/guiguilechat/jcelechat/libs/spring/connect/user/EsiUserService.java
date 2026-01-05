package fr.guiguilechat.jcelechat.libs.spring.connect.user;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.C2CStandingsService;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EsiUserService implements EsiUserListener {

	@Lazy
	private final EsiAppService esiAppService;

	@Lazy
	private final C2CStandingsService c2cStandingsService;

	@Lazy
	private final EsiUserRepository repo;

	public static int getCharacterId(Authentication auth) {
		OAuth2User user = (OAuth2User) auth.getPrincipal();
		return ((Number) user.getAttributes().get("CharacterID")).intValue();
	}

	public static String getCharacterName(Authentication auth) {
		OAuth2User user = (OAuth2User) auth.getPrincipal();
		return (String) user.getAttributes().get("CharacterName");
	}

	public EsiUser save(EsiUser data) {
		if (data.getCreated() == null) {
			data.setCreated(Instant.now());
		}
		data.setLastUpdate(Instant.now());
		return repo.save(data);
	}

	public List<EsiUser> forCharacterId(int characterId) {
		return repo.findAllByCharacterIdAndCanceledFalse(characterId);
	}

	public List<EsiUser> forAppCharacterId(EsiApp app, int characterId) {
		return repo.findAllByAppAndCharacterIdAndCanceledFalse(app, characterId);
	}

	//
	// caching of the user+ scopes to an esiconnected. This should avoid having to
	// create multiple connection, therefore refreshtoken+verify calls.
	//

	protected EsiUser searchUserScopes(int characterId, Set<String> requiredScopes) {
		return forCharacterId(characterId).stream()
		    .sorted(Comparator.comparing(eu -> -eu.getScopes().size()))
		    .filter(u -> u.getScopes().containsAll(requiredScopes)).findAny().orElse(null);
	}

	private record ScopedEsiKey(int characterId, Set<String> requiredScopes) {
	}

	/**
	 * first cache matches the required char Id and scopes to the known refresh
	 * token
	 */
	private Map<ScopedEsiKey, EsiUser> scopedEsiUserCache = Collections.synchronizedMap(new HashMap<>());

	/** second cache matches the refresh token to actual connexion */
	private Map<Long, ESIConnected> esiUserIdConnectedCache = Collections.synchronizedMap(new HashMap<>());

	@Override
	public void onNewEsiUser(EsiUser user) {
		esiUserIdConnectedCache.clear();
		scopedEsiUserCache.clear();
	}

	public ESIConnected esiConnected(int characterId, Set<String> requiredScopes) {
		ScopedEsiKey key = new ScopedEsiKey(characterId, requiredScopes);
		EsiUser user = scopedEsiUserCache.computeIfAbsent(key, _ -> searchUserScopes(characterId, requiredScopes));
		return user == null ? null
		    : esiUserIdConnectedCache.computeIfAbsent(user.getId(),
						_ -> new ESIConnected(user.getRefreshToken(), user.getApp().getAppBase64()));
	}


	public List<EsiUser> listAll() {
		return repo.findAll();
	}
}
