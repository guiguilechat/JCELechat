package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;

public interface ConstellationRepository extends IRemoteFetchedResourceRepository<Constellation, Integer> {

	List<Constellation> findByNameEqualsIgnoreCase(String name);

}
