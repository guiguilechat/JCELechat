package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResource;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface IConnectedCharDataService<
			Entity extends ARemoteResource<Integer, Fetched>,
			Fetched,
			Repository extends IRemoteResourceRepository<Entity, Integer>>
    extends EsiUserListener {

}
