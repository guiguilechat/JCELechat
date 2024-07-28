package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface RegionRepository extends IRemoteEntityRepository<Region, Integer> {

	List<Region> findByNameEqualsIgnoreCase(String name);

	List<Region> findByUniverse(String universe);

}
