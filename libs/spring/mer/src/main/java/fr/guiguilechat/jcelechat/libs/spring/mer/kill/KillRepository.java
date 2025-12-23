package fr.guiguilechat.jcelechat.libs.spring.mer.kill;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStats;

public interface KillRepository extends JpaRepository<Kill, Long> {

	List<Kill> findByVictimTypeIdIn(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	trunc( min(kill.killDate) , year) period_start,
	DATEADD(year, 1, trunc( min(kill.killDate) , year)) period_end,
	count(*) nb_kills,
	sum(kill.iskLost) iskLost,
	min(kill.iskDestroyed)
from
	#{#entityName} kill
where
	kill.victimTypeId in :destroyedShipTypeId
group by
	trunc( kill.killDate , year)
order by
	trunc( min(kill.killDate) , year) desc
""")
	List<KillStats> yearlyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	trunc( min(kill.killDate) , month) period_start,
	DATEADD(month, 1, trunc( min(kill.killDate) , month)) period_end,
	count(*) nb_kills,
	sum(kill.iskLost) iskLost,
	min(kill.iskDestroyed)
from
	#{#entityName} kill
where
	kill.victimTypeId in :destroyedShipTypeId
group by
	trunc( kill.killDate , month)
order by
	trunc( min(kill.killDate) , month) desc
""")
	List<KillStats> monthlyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	trunc( min(kill.killDate) , week) period_start,
	DATEADD(week, 1, trunc( min(kill.killDate) , week)) period_end,
	count(*) nb_kills,
	sum(kill.iskLost) iskLost,
	min(kill.iskDestroyed)
from
	#{#entityName} kill
where
	kill.victimTypeId in :destroyedShipTypeId
group by
	trunc( kill.killDate , week)
order by
	trunc( min(kill.killDate) , week) desc
""")
	List<KillStats> weeklyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	trunc( min(kill.killDate) , day) period_start,
	DATEADD(day, 1, trunc( min(kill.killDate) , day)) period_end,
	count(*) nb_kills,
	sum(kill.iskLost) iskLost,
	min(kill.iskDestroyed)
from
	#{#entityName} kill
where
	kill.victimTypeId in :destroyedShipTypeId
group by
	trunc( kill.killDate , day)
order by
	trunc( min(kill.killDate) , day) desc
""")
	List<KillStats> dailyKills(@NonNull Collection<Integer> destroyedShipTypeId);

}
