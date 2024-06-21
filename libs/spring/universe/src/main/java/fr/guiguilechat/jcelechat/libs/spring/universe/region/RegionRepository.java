package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;

public interface RegionRepository extends IRemoteResourceRepository<Region, Integer> {

	List<Region> findByNameEqualsIgnoreCase(String name);

	List<Region> findByUniverse(String universe);

}
