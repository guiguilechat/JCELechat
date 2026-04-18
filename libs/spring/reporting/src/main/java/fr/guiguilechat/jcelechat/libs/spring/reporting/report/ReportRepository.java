package fr.guiguilechat.jcelechat.libs.spring.reporting.report;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

	List<Report> findByStatusAndTryAfterLowerThanOrderByPriorityDesc(Report.Status status, Instant now, Limit limit);

}
