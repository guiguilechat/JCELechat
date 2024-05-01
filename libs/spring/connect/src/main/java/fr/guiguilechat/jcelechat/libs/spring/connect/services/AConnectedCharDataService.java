package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.ICharDataRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
public abstract class AConnectedCharDataService<Entity extends ACharData<Fetched>, Fetched, Repository extends ICharDataRepository<Entity>>
    extends ACharDataService<Entity, Fetched, Repository> {

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
			super.onNewEsiUser(user);
		}
	}

}
