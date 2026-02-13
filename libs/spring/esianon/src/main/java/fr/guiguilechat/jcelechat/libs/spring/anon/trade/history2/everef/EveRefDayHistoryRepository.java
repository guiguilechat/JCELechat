package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef.EveRefDayHistory.AggregationStatus;

public interface EveRefDayHistoryRepository extends JpaRepository<EveRefDayHistory, LocalDate> {

	@Query("""
from
	#{#entityName} performed
select
	max(date)
""")
	LocalDate maxDate();

	/**
	 * select for retry
	 *
	 * @param now Instant.now()
	 */
	List<EveRefDayHistory> findBySuccessFalseAndNextFetchBeforeOrderByNextFetchAsc(Instant now, Limit limit);

	/**
	 * select for aggregation
	 */
	List<EveRefDayHistory> findByAggregationStatusOrderByDateAsc(AggregationStatus agregationStatus, Limit limit);

}
