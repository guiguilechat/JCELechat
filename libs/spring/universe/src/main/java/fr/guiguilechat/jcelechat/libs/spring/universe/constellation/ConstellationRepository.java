package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface ConstellationRepository extends IRemoteEntityRepository<Constellation, Integer> {

	List<Constellation> findByNameEqualsIgnoreCase(String name);

}
