package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.kills;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemKillsRepository extends JpaRepository<SystemKills, Long> {

	@Query("""
select
	e.solarSystem.id,
	e.fetch.lastModified - 30 minute,
	e.npcKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
""")
	List<Object[]> npcKillsForSystemIds(Iterable<Integer> sysIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	e.fetch.lastModified - 30 minute,
	e.podKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
""")
	List<Object[]> podKillsForSystemIds(Iterable<Integer> sysIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	e.fetch.lastModified - 30 minute,
	e.shipKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
""")
	List<Object[]> shipKillsForSystemIds(Iterable<Integer> sysIds, Instant since);

}
