package fr.guiguilechat.jcelechat.libs.spring.connect.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.DelegateOauth2User;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.C2CStandingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EsiConnectionInterceptor extends DefaultOAuth2UserService {

	private final CacheManager cacheManager;

	private final EsiUserService esiUserService;

	@Lazy
	private final EsiAppService esiAppService;

	@Lazy
	private final C2CStandingsService c2cStandingsService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		return processOAuth2User(oAuth2UserRequest, oAuth2User);
	}

	public static interface EsiUserListener {

		public default List<String> getEsiUserCaches() {
			return List.of();
		}

		@Async
		public default void onNewEsiUser(EsiUser user) {

		}
	}

	private final Optional<List<EsiUserListener>> updateListeners;

	public static final String LECHAT_AUTHORITIES = "LECHAT";

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		try {
			String appId = oAuth2UserRequest.getClientRegistration().getClientId();
			String appSecret = oAuth2UserRequest.getClientRegistration().getClientSecret();
			EsiApp app = esiAppService.findOrCreate(appId, appSecret);
			String characterName = oAuth2User.getName();
			int characterId = ((Number) oAuth2User.getAttributes().get("CharacterID")).intValue();
			Set<String> scopes = Set.of(((String) oAuth2User.getAttributes().get("Scopes")).split(" "));
			String refreshToken = (String) oAuth2UserRequest.getAdditionalParameters()
			    .get(OAuth2ParameterNames.REFRESH_TOKEN);

			EsiUser existingUserAccount = esiUserService.forAppCharacterId(app, characterId).stream()
			    .filter(user -> user.getScopes().containsAll(scopes)).findAny().orElse(null);
			if (existingUserAccount == null) {
				EsiUser newUserAccount = esiUserService.save(
				    EsiUser.builder()
				        .app(app)
				        .characterId(characterId)
				        .characterName(characterName)
				        .refreshToken(refreshToken)
				        .scopes(scopes)
				        .build());

				log.debug("saved new entry for user " + characterName);
				propagateEsiUser(newUserAccount);
			} else {
				log.debug("no need to save entry for user " + characterName);
			}
			List<String> addedRoles = new ArrayList<>();
			float effStanding = c2cStandingsService.effectiveStanding(95940101, characterId);
			if (effStanding >= 5) {
				addedRoles.add(LECHAT_AUTHORITIES);
			}

			Set<String> allScopes = esiUserService.forCharacterId(characterId).stream()
			    .flatMap(ei -> ei.getScopes().stream()).collect(Collectors.toSet());
			addedRoles.addAll(allScopes);

			DelegateOauth2User ret = new DelegateOauth2User(oAuth2User, addedRoles);
			return ret;
		} catch (Exception e) {
			log.error("while receiving new oauth2 user", e);
			return null;
		}
	}

	protected void propagateEsiUser(EsiUser newUserAccount) {
		if (updateListeners.isPresent()) {
			updateListeners.get().stream().flatMap(l -> l.getEsiUserCaches().stream())
			    .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			updateListeners.get().stream().forEach(l -> l.onNewEsiUser(newUserAccount));
		}

	}

	/**
	 * once the application is started, transmit the known esi user to all services.
	 * So that if a new service is added, it can start handling the data.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void postStartUp() {
		esiUserService.listAll().stream()
		    .filter(ei -> !ei.isCanceled())
		    .forEach(this::propagateEsiUser);

	}

}
