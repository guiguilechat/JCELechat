package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * service to manage Character data that needs to be connected. For example, its
 * wallet. Since it needs to be connected it also has optional scopes that the
 * {@link EsiUser} must have to be allowed to fetch it.
 * 
 * @param <Entity>     Char data representation
 * @param <Fetched>    Remote resource type
 * @param <Repository> jpa repository to store the char data.
 */
@Slf4j
@NoArgsConstructor
@Getter
public abstract class AConnectedCharDataService<
			Entity extends ARemoteFetchedResource<Integer, Fetched>,
			Fetched,
			Repository extends IRemoteFetchedResourceRepository<Entity, Integer>>
    extends ARemoteFetchedResourceService<Entity, Integer, Fetched, Repository>
    implements EsiUserListener {

	@Autowired
	@Accessors(fluent = true)
	@Lazy
	private EsiUserService userService;

	@Override
	protected Requested<Fetched> fetchData(Integer characterId, Map<String, String> properties) {
		ESIConnected con = userService().esiConnected(characterId, getRequiredScopes());
		if (con == null) {
			log.warn("no matching connexion for service {} , character id={} , scopes={}", getClass().getSimpleName(),
			    characterId, getRequiredScopes());
			return null;
		}
		return fetchCharacterData(con, characterId, properties);
	}

	protected abstract Requested<Fetched> fetchCharacterData(ESIConnected esiConnected, int characterId,
	    Map<String, String> properties);

	protected Set<String> getRequiredScopes() {
		return Set.of();
	}

	@Override
	public void onNewEsiUser(EsiUser user) {
		if (user.getScopes().containsAll(getRequiredScopes())) {
			createIfAbsent(user.getCharacterId());
		}
	}

}
