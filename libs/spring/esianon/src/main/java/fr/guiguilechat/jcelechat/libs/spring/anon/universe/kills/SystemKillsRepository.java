package fr.guiguilechat.jcelechat.libs.spring.anon.universe.kills;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemPeriodEndKey;

public interface SystemKillsRepository extends JpaRepository<SystemKills, SystemPeriodEndKey> {

	//
	// npc kills
	//

	@Query("""
select
	truncate(e.date, hour),
	e.npcKills npcKills
from
	#{#entityName} e
where
	e.solarSystemId = :systemId
	and e.date>=:since
""")
	List<Object[]> npcKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, hour),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, hour),
	e.solarSystemId
""")
	List<Object[]> aggregateNpcKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, day),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, day),
	e.solarSystemId
""")
	List<Object[]> aggregateNpcKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, week),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, week),
	e.solarSystemId
""")
	List<Object[]> aggregateNpcKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, month),
	sum(e.npcKills) npcKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, month),
	e.solarSystemId
""")
	List<Object[]> aggregateNpcKillsMonthly(Iterable<Integer> systemsIds, Instant since);

	//
	// pod kills
	//

	@Query("""
select
	truncate(e.date, hour),
	e.podKills podKills
from
	#{#entityName} e
where
	e.solarSystemId = :systemId
	and e.date>=:since
""")
	List<Object[]> podKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, hour),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, hour),
	e.solarSystemId
""")
	List<Object[]> aggregatePodKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, day),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, day),
	e.solarSystemId
""")
	List<Object[]> aggregatePodKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, week),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, week),
	e.solarSystemId
""")
	List<Object[]> aggregatePodKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, month),
	sum(e.podKills) podKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, month),
	e.solarSystemId
""")
	List<Object[]> aggregatePodKillsMonthly(Iterable<Integer> systemsIds, Instant since);

	//
	// ship kills
	//

	@Query("""
select
	truncate(e.date, hour),
	e.shipKills shipKills
from
	#{#entityName} e
where
	e.solarSystemId = :systemId
	and e.date>=:since
""")
	List<Object[]> shipKillsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, hour),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, hour),
	e.solarSystemId
""")
	List<Object[]> aggregateShipKillsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, day),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, day),
	e.solarSystemId
""")
	List<Object[]> aggregateShipKillsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, week),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, week),
	e.solarSystemId
""")
	List<Object[]> aggregateShipKillsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, month),
	sum(e.shipKills) shipKills
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date>=:since
group by
	truncate(e.date, month),
	e.solarSystemId
""")
	List<Object[]> aggregateShipKillsMonthly(Iterable<Integer> systemsIds, Instant since);

	@Modifying
	@Query("""
delete from #{#entityName}
where periodEnd=:periodEnd
""")
	void deleteByPeriondEnd(Instant periodEnd);

	@Query("""
select
	max(periodEnd)
from
	#{#entityName}
""")
	Instant maxLastModified();

}
