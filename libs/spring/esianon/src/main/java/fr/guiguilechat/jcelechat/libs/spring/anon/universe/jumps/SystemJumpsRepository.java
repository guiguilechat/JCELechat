package fr.guiguilechat.jcelechat.libs.spring.anon.universe.jumps;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemPeriodEndKey;

public interface SystemJumpsRepository extends JpaRepository<SystemJumps, SystemPeriodEndKey> {

	@Query("""
select
	e.date,
	e.shipJumps
from
	#{#entityName} e
where
	e.solarSystemId = :systemId
	and e.date >= :since
""")
	List<Object[]> jumpsForSystemId(int systemId, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, hour),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date >= :since
group by
	truncate(e.date, hour),
	e.solarSystemId
""")
	List<Object[]> aggregateJumpsHourly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, day),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date >= :since
group by
	truncate(e.date, day),
	e.solarSystemId
	""")
	List<Object[]> aggregateJumpsDaily(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, week),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date >= :since
group by
	truncate(e.date, week),
	e.solarSystemId
	""")
	List<Object[]> aggregateJumpsWeekly(Iterable<Integer> systemsIds, Instant since);

	@Query("""
select
	e.solarSystemId,
	truncate(e.date, month),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date >= :since
group by
	truncate(e.date, month),
	e.solarSystemId
	""")
	List<Object[]> aggregateJumpsMonthly(Iterable<Integer> systemsIds, Instant since);

	/**
	 * get the activity, grouped by day of week and hour, for a list of systems
	 */
	@Query("""
select
	e.solarSystemId,
	extract(day of week from e.date),
	extract(hour from e.date),
	sum(e.shipJumps)/:weeks daily_jumps
from
	#{#entityName} e
where
	e.solarSystemId in :systemsIds
	and e.date >= current_date() - :weeks * 7 * 1 day
group by
	extract(day of week from e.date),
	extract(hour from e.date),
	e.solarSystemId
""")
	List<Object[]> activity(Iterable<Integer> systemsIds, int weeks);

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
