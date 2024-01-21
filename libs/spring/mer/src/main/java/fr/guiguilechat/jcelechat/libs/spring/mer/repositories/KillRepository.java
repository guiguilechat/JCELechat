package fr.guiguilechat.jcelechat.libs.spring.mer.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;

public interface KillRepository extends JpaRepository<Kill, Long> {

	public List<Kill> findByDestroyedShipTypeIdIn(Collection<Integer> destroyedShipTypeId);

	@Query(value = """
select
	DATE_TRUNC( 'month', kill.killDate) date_month,
	count(*) nb_kills,
	sum(kill.iskLost) iskLost,
	percentile_cont(0.5) WITHIN GROUP (ORDER BY kill.iskLost)
from
	MerKill kill
where
	kill.destroyedShipTypeId in :destroyedShipTypeId
group by
	DATE_TRUNC( 'month', kill.killDate)
order by DATE_TRUNC( 'month', kill.killDate) desc
			""")
	public List<Object[]> monthlyKills(Collection<Integer> destroyedShipTypeId);

}
