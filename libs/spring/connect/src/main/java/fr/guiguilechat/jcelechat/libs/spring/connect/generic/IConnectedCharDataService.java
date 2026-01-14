package fr.guiguilechat.jcelechat.libs.spring.connect.generic;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityRepository;

public interface IConnectedCharDataService<
			Entity extends RemoteEntity<Integer, Fetched>,
			Fetched,
			Repository extends RemoteEntityRepository<Entity, Integer>>
    extends EsiUserListener {

}
