package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.IRemoteFetchedResourceRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * service to manage Character data that needs to be connected. For example, its
 * wallet. Since it needs to be connected it also has optional scopes that the
 * {@link EsiUser} must have to be allowed to fetch it.
 * 
 * @param <Entity>     Char data representation
 * @param <Fetched>    Remote resource type
 * @param <Repository> jpa repository to store the char data.
 */
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

	protected ESIConnected esiConnected(int characterId) {
		EsiUser user = userService().esiUser(characterId, getRequiredScopes());
		return new ESIConnected(user.getRefreshToken(), user.getApp().getAppBase64());
	}

	@Override
	protected Requested<Fetched> fetchData(Integer characterId, Map<String, String> properties) {
		return fetchCharacterData(esiConnected(characterId), characterId, properties);
	}

	protected abstract Requested<Fetched> fetchCharacterData(ESIConnected esiConnected, int characterId,
	    Map<String, String> properties);

	protected Set<String> getRequiredScopes() {
		return Set.of();
	}

	@Override
	public void onNewEsiUser(EsiUser user) {
		if (user.getScopes().containsAll(getRequiredScopes())) {
			createIfAbsent(user.getCharacterId(), false);
		}
	}

}
