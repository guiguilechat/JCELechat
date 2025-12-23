package fr.guiguilechat.jcelechat.libs.spring.anon.universe.jumps;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemJumpsRepository extends JpaRepository<SystemJumps, Long> {

	@Query("""
select
	e.fetch.lastModified - 30 minute,
	e.shipJumps
from
	#{#entityName} e
where
	e.solarSystem.id = :systemId
	and e.fetch.lastModified - 30 minute >= :since
""")
	List<Object[]> jumpsForSystemId(int systemId, Instant since);

	// hql interval should be constructor-agnostic regarding interval

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, hour),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
group by
	truncate(e.fetch.lastModified - 30 minute, hour),
	e.solarSystem
""")
	List<Object[]> aggregateJumpsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, day),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
group by
	truncate(e.fetch.lastModified - 30 minute, day),
	e.solarSystem
	""")
	List<Object[]> aggregateJumpsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, week),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
group by
	truncate(e.fetch.lastModified - 30 minute, week),
	e.solarSystem
	""")
	List<Object[]> aggregateJumpsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystem.id,
	truncate(e.fetch.lastModified - 30 minute, month),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= :since
group by
	truncate(e.fetch.lastModified - 30 minute, month),
	e.solarSystem
	""")
	List<Object[]> aggregateJumpsMonthly(Iterable<Integer> systemsIds, Instant since);

	/**
	 * get the activity, grouped by day of week and hour, for a list of systems
	 */
	@Query("""
select
	e.solarSystem.id,
	extract(day of week from e.fetch.lastModified - 30 minute),
	extract(hour from e.fetch.lastModified - 30 minute),
	sum(e.shipJumps)/:weeks daily_jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
	and e.fetch.lastModified - 30 minute >= current_date() - :weeks * 7 * 1 day
group by
	extract(day of week from e.fetch.lastModified - 30 minute),
	extract(hour from e.fetch.lastModified - 30 minute),
	e.solarSystem
""")
	List<Object[]> activity(Iterable<Integer> systemsIds, int weeks);

}
