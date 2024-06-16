package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;

public interface RegionRepository extends IRemoteFetchedResourceRepository<Region, Integer> {

	List<Region> findByNameEqualsIgnoreCase(String name);

	List<Region> findByUniverse(String universe);

}
