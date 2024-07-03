package fr.guiguilechat.jcelechat.libs.spring.mer.kill;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface KillRepository extends JpaRepository<Kill, Long> {

	public List<Kill> findByDestroyedShipTypeIdIn(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	DATE_TRUNC( 'month', kill.kill_date at time zone 'UTC') date_month,
	count(*) nb_kills,
	sum(kill.isk_lost) iskLost,
	percentile_cont(0.5) WITHIN GROUP (ORDER BY kill.isk_lost),
	percentile_cont(0) WITHIN GROUP (ORDER BY kill.isk_lost)
from
	mer_kill kill
where
	kill.destroyed_ship_type_id in :destroyedShipTypeId
group by
	DATE_TRUNC( 'month', kill.kill_date at time zone 'UTC')
order by
	DATE_TRUNC( 'month', kill.kill_date at time zone 'UTC') desc
""", nativeQuery = true)
	public List<Object[]> monthlyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	DATE_TRUNC( 'week', kill.kill_date at time zone 'UTC') date_week,
	count(*) nb_kills,
	sum(kill.isk_lost) iskLost,
	percentile_cont(0.5) WITHIN GROUP (ORDER BY kill.isk_lost),
	percentile_cont(0) WITHIN GROUP (ORDER BY kill.isk_lost)
from
	mer_kill kill
where
	kill.destroyed_ship_type_id in :destroyedShipTypeId
group by
	DATE_TRUNC( 'week', kill.kill_date at time zone 'UTC')
order by
	DATE_TRUNC( 'week', kill.kill_date at time zone 'UTC') desc
""", nativeQuery = true)
	public List<Object[]> weeklyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	DATE_TRUNC( 'year', kill.kill_date at time zone 'UTC') date_week,
	count(*) nb_kills,
	sum(kill.isk_lost) iskLost,
	percentile_cont(0.5) WITHIN GROUP (ORDER BY kill.isk_lost),
	percentile_cont(0) WITHIN GROUP (ORDER BY kill.isk_lost)
from
	mer_kill kill
where
	kill.destroyed_ship_type_id in :destroyedShipTypeId
group by
	DATE_TRUNC( 'year', kill.kill_date at time zone 'UTC')
order by
	DATE_TRUNC( 'year', kill.kill_date at time zone 'UTC') desc
""", nativeQuery = true)
	public List<Object[]> yearlyKills(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	DATE_TRUNC( 'day', kill.kill_date at time zone 'UTC') date_week,
	count(*) nb_kills,
	sum(kill.isk_lost) iskLost,
	percentile_cont(0.5) WITHIN GROUP (ORDER BY kill.isk_lost),
	percentile_cont(0) WITHIN GROUP (ORDER BY kill.isk_lost)
from
	mer_kill kill
where
	kill.destroyed_ship_type_id in :destroyedShipTypeId
group by
	DATE_TRUNC( 'day', kill.kill_date at time zone 'UTC')
order by
	DATE_TRUNC( 'day', kill.kill_date at time zone 'UTC') desc
""", nativeQuery = true)
	public List<Object[]> dailyKills(@NonNull Collection<Integer> destroyedShipTypeId);

}
