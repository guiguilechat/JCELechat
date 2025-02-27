package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface SolarSystemRepository extends IRemoteEntityRepository<SolarSystem, Integer> {

	@Query("""
select
	sg.destination.solarSystem
from
	EsiUniverseStargate sg
where
	sg.solarSystem=:source
""") List<SolarSystem> adjacent(SolarSystem source);

	List<SolarSystem> findByNameEqualsIgnoreCase(String name);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	lower(ss.constellation.region.name)=lower(:regionName)
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByRegionNameIgnoreCase(String regionName, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	ss.constellation.region.id=:regionId
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByRegionId(int regionId, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	lower(ss.constellation.name)=lower(:constellationName)
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByConstellationNameIgnoreCase(String constellationName, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	ss.constellation.id=:constellationId
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByConstellationId(int constellationId, float minSS, float maxSS);

}
