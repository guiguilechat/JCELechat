package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiApp;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.EsiUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EsiUserService extends DefaultOAuth2UserService {

	@Lazy
	private final EsiAppService esiAppService;

	private final EsiUserRepository repo;

	private final CacheManager cacheManager;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		return processOAuth2User(oAuth2UserRequest, oAuth2User);
	}

	public static interface EsiUserListener {

		public default List<String> listEsiUserCaches() {
			return List.of();
		}

		@Async
		public default void onNewEsiUser(EsiUser user) {

		}
	}

	private final Optional<List<EsiUserListener>> updateListeners;

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		String appId = oAuth2UserRequest.getClientRegistration().getClientId();
		String appSecret = oAuth2UserRequest.getClientRegistration().getClientSecret();
		EsiApp app = esiAppService.findOrCreate(appId, appSecret);
		String characterName = oAuth2User.getName();
		int characterId = ((Number) oAuth2User.getAttributes().get("CharacterID")).intValue();
		Set<String> scopes = Set.of(((String) oAuth2User.getAttributes().get("Scopes")).split(" "));
		String refreshTokenSpring = (String) oAuth2UserRequest.getAdditionalParameters()
				.get(OAuth2ParameterNames.REFRESH_TOKEN);

		EsiUser existingUserAccount = repo.findAllByAppAndCharacterIdAndCanceledFalse(app, characterId).stream()
				.filter(user -> user.getScopes().containsAll(scopes)).findAny().orElse(null);
		if (existingUserAccount == null) {
			EsiUser newUserAccount = save(
					EsiUser.builder()
							.app(app)
							.characterId(characterId)
							.characterName(characterName)
			        .refreshToken(refreshTokenSpring)
							.scopes(scopes)
							.build());

			log.debug("saved new entry for user " + characterName);
			if (updateListeners.isPresent()) {
				updateListeners.get().stream().flatMap(l -> l.listEsiUserCaches().stream())
					.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
				updateListeners.get().stream().forEach(l -> l.onNewEsiUser(newUserAccount));
			}
		} else {
			log.debug("no need to save entry for user " + characterName);
		}

// System.err.println("user attributes : " + oAuth2User.getAttributes());
		return oAuth2User;
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

	public static int getCharacterId(Authentication auth) {
		OAuth2User user = (OAuth2User) auth.getPrincipal();
		return ((Number) user.getAttributes().get("CharacterID")).intValue();
	}

	public static String getCharacterName(Authentication auth) {
		OAuth2User user = (OAuth2User) auth.getPrincipal();
		return (String) user.getAttributes().get("CharacterName");
	}

	public EsiUser esiUser(int characterId, Set<String> requiredScopes) {
		return forCharacterId(characterId).stream()
		    .filter(user -> user.getScopes().containsAll(requiredScopes)).findAny().orElse(null);
	}
}