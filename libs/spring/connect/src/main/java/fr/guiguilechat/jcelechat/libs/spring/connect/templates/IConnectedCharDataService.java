package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.repositories.IRemoteFetchedResourceRepository;

public interface IConnectedCharDataService<
			Entity extends ARemoteFetchedResource<Integer, Fetched>,
			Fetched,
			Repository extends IRemoteFetchedResourceRepository<Entity, Integer>>
    extends EsiUserListener {

}
