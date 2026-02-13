package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef.EveRefDayHistory;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef.EveRefDayHistory.AggregationStatus;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef.EveRefDayHistoryRepository;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "jcelechat.trade.history.aggreg")
@Getter
@Setter
@Service
public class AggregatedDailyHistoryUpdater implements EntityUpdater {

	private final AggregatedDailyHistoryRepository repo;

	private final EveRefDayHistoryRepository eveRefDayHistoryRepository;

	@Getter
	private final UpdateConfig update = new UpdateConfig();

	@Override
	@Transactional
	public boolean updatePulse() {
		List<EveRefDayHistory> toAggregate = eveRefDayHistoryRepository.findByAggregationStatusOrderByDateAsc(
				AggregationStatus.AVAIL,
				Limit.of(getUpdate().getMax()));
		if (toAggregate.isEmpty()) {
			log.debug("no avail everef yet ; retrying failed ones if any");
			toAggregate = eveRefDayHistoryRepository.findByAggregationStatusOrderByDateAsc(
					AggregationStatus.FAILED,
					Limit.of(getUpdate().getMax()));
		}
		if (toAggregate.isEmpty()) {
			return false;
		}
		List<LocalDate> dates = toAggregate.stream().map(EveRefDayHistory::getDate).toList();
		int deleted = repo.deleteForDates(dates);
		if (deleted > 0) {
			log.trace("deleted {} existing lines for {} dates", deleted, dates.size());
		}
		long start = System.currentTimeMillis();
		int inserted = repo.aggregateForDates(dates);
		long done = System.currentTimeMillis();
		log.debug(" aggregate orders from {} days into {} aggregates in {} ms ",
				dates.size(),
				inserted,
				done - start);
		toAggregate.forEach(dh -> {
			dh.setAggregationStatus(AggregationStatus.DONE);
		});
		return true;
	}

}
