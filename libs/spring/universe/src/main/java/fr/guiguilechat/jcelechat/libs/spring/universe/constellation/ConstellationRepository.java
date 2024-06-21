package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface ConstellationRepository extends IRemoteResourceRepository<Constellation, Integer> {

	List<Constellation> findByNameEqualsIgnoreCase(String name);

}
