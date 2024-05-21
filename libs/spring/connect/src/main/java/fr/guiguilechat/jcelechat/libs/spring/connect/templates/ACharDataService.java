package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.IRemoteFetchedResourceRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public abstract class ACharDataService<
			Entity extends ARemoteFetchedResource<Integer, Fetched>,
			Fetched,
			Repository extends IRemoteFetchedResourceRepository<Entity, Integer>
		> extends ARemoteFetchedResourceService<Entity, Integer, Fetched, Repository>
    implements EsiUserListener {

	@Override
	public void onNewEsiUser(EsiUser user) {
		createIfAbsent(user.getCharacterId(), true);
	}

}
