package fr.guiguilechat.jcelechat.libs.spring.sde.space.region;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface RegionRepository extends SdeEntityRepository<Region, Integer> {

	List<Region> findByNameEqualsIgnoreCase(String name);

	List<Region> findByUniverse(String universe);

	@Query("""
select
	distinct(region.universe)
from
	#{#entityName} region
""")
	List<String> listUniverses();

	@Query("""
select
	region.id
from
	#{#entityName} region
where
	region.universe=:universe
""")
	List<Integer> listIdsByUniverse(String universe);

	@Query("""
select
	region.id
from
	#{#entityName} region
""")
	List<Integer> listIds();

}
