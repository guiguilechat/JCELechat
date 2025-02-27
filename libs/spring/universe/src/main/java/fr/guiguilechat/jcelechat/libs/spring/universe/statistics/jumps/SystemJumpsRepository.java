package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemJumpsRepository extends JpaRepository<SystemJumps, Long> {

	// hql interval should be constructor-agnostic regarding interval
	@Query("""
select
	e.solarSystem.id,
	date_trunc('day', e.fetch.lastModified - 30 minute),
	sum(e.shipJumps) jumps
from
	#{#entityName} e
where
	e.solarSystem.id in :systemsIds
group by
	date_trunc('day', e.fetch.lastModified - 30 minute),
	e.solarSystem
""")
	List<Object[]> dailyJumps(Iterable<Integer> systemsIds);

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
