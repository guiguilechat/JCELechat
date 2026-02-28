package fr.guiguilechat.jcelechat.libs.spring.anon.industry.indices;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemPeriodEndKey;

public interface SystemCostIndicesRepository extends JpaRepository<SystemCostIndices, SystemPeriodEndKey> {

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

	@Query("""
from
	#{#entityName} e
where
	periodEnd=(select max(periodEnd) from #{#entityName})
select
	e
""")
	List<SystemCostIndices> lastIndexes();

}
