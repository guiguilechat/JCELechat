package fr.guiguilechat.jcelechat.libs.spring.update.manager.servicepulse;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpdaterPulseRepository extends JpaRepository<UpdaterPulse, Long> {

	public record TimeUsage(String serviceName, long ms) {
	}

	@Query("""
from
	#{#entityName} e
where
	e.start > :since
group by
	e.service
select
	e.service,
	sum(e.durationMs)
""")
	List<TimeUsage> activities(Instant since);

}
