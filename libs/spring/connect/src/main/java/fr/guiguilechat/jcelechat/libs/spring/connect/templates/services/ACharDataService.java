package fr.guiguilechat.jcelechat.libs.spring.connect.templates.services;

import fr.guiguilechat.jcelechat.libs.spring.connect.templates.model.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.repositories.ICharDataRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService.EsiUserListener;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public abstract class ACharDataService<Entity extends ACharData<Fetched>, Fetched, Repository extends ICharDataRepository<Entity>>
    extends ARemoteFetchedResourceService<Entity, Integer, Fetched, Repository>
    implements EsiUserListener {

	@Override
	public void onNewEsiUser(EsiUser user) {
		createIfMissing(user.getCharacterId());
	}

}
