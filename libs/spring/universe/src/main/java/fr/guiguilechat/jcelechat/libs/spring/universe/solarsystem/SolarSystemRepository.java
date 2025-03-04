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
	lower(ss.name) in :sysNames
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByLowerNameIn(Iterable<String> sysNames, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	lower(ss.constellation.region.name) in :regionNames
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByLowerRegionNameIn(Iterable<String> regionNames, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	ss.constellation.region.id in :regionIds
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByRegionIdIn(Iterable<Integer> regionIds, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	lower(ss.constellation.name) in :constellationNames
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByLowerConstellationNameIn(Iterable<String> constellationNames, float minSS, float maxSS);

	@Query("""
select
	ss.id
from
	#{#entityName} ss
where
	ss.constellation.id in :constellationIds
	and ss.securityStatus>=:minSS
	and ss.securityStatus<=:maxSS
""")
	List<Integer> idByConstellationIdIn(Iterable<Integer> constellationIds, float minSS, float maxSS);

}
