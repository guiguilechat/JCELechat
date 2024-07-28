package fr.guiguilechat.jcelechat.libs.spring.connect.templates;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface IConnectedCharDataService<
			Entity extends ARemoteEntity<Integer, Fetched>,
			Fetched,
			Repository extends IRemoteEntityRepository<Entity, Integer>>
    extends EsiUserListener {

}
