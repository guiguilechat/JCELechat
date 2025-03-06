package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.kills;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemKillsRepository extends JpaRepository<SystemKills, Long> {

	//
	// npc kills
	//

	@Query("""
select
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.npcKills npcKills
from
	#{#entityName} e
where
	e.solarSystem.id = :systemId
	and e.fetch.lastModified - 30 minute>=:since
""")
	List<Object[]> npcKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, hour),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.solarSystem
""")
	List<Object[]> aggregateNpcKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, day),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, day),
	e.solarSystem
""")
	List<Object[]> aggregateNpcKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, week),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, week),
	e.solarSystem
""")
	List<Object[]> aggregateNpcKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, month),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, month),
	e.solarSystem
""")
	List<Object[]> aggregateNpcKillsMonthly(Iterable<Integer> systemsIds, Instant since);

	//
	// pod kills
	//

	@Query("""
select
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.podKills podKills
from
	#{#entityName} e
where
	e.solarSystem.id = :systemId
	and e.fetch.lastModified - 30 minute>=:since
""")
	List<Object[]> podKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, hour),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.solarSystem
""")
	List<Object[]> aggregatePodKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, day),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, day),
	e.solarSystem
""")
	List<Object[]> aggregatePodKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, week),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, week),
	e.solarSystem
""")
	List<Object[]> aggregatePodKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, month),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, month),
	e.solarSystem
""")
	List<Object[]> aggregatePodKillsMonthly(Iterable<Integer> systemsIds, Instant since);

	//
	// ship kills
	//

	@Query("""
select
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.shipKills shipKills
from
	#{#entityName} e
where
	e.solarSystem.id = :systemId
	and e.fetch.lastModified - 30 minute>=:since
""")
	List<Object[]> shipKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, hour),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.solarSystem
""")
	List<Object[]> aggregateShipKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, day),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, day),
	e.solarSystem
""")
	List<Object[]> aggregateShipKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, week),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, week),
	e.solarSystem
""")
	List<Object[]> aggregateShipKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, month),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute>=:since
group by
	truncate(e.fetch.lastModified - 30 minute, month),
	e.solarSystem
""")
	List<Object[]> aggregateShipKillsMonthly(Iterable<Integer> systemsIds, Instant since);

}
