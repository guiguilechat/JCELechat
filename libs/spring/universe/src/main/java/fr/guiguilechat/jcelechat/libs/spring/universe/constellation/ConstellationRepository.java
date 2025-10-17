package fr.guiguilechat.jcelechat.libs.spring.universe.constellation;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface ConstellationRepository extends SdeEntityRepository<Constellation, Integer> {

	List<Constellation> findByNameEqualsIgnoreCase(String name);

	@Query("""
select
	constel.id
from
	#{#entityName} constel
where
	constel.region.universe=:universe
""")
	List<Integer> listIdsByUniverse(String universe);

	@Query("""
select
	constel.id
from
	#{#entityName} constel
""")
	List<Integer> listIds();

	@Query("""
select
	constel.id
from
	#{#entityName} constel
where
	constel.region.id=:regionId
""")
	List<Integer> listIdsByRegionId(int regionId);

}
