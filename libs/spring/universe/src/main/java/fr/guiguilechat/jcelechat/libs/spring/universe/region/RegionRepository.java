package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepository extends JpaRepository<Region, Integer> {

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
