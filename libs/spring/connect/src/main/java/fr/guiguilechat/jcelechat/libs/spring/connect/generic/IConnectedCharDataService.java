package fr.guiguilechat.jcelechat.libs.spring.connect.generic;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiConnectionInterceptor.EsiUserListener;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntity;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityRepository;

public interface IConnectedCharDataService<
			Entity extends RemoteNumberEntity<Integer, Fetched>,
			Fetched,
			Repository extends RemoteNumberEntityRepository<Entity, Integer>>
    extends EsiUserListener {

}
