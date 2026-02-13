package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate.AggregatedTypeDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
@Setter
@Service
public class AggregatedDailyHistoryService {

	private final AggregatedDailyHistoryRepository repo;

	// query for one type

	protected List<AggregatedDailyHistory> itemHistory(int typeId, int me, int te, boolean copy, LocalDate start,
			LocalDate end) {
		if (start != null && end != null) {
			return repo.findAllByTypeIdAndMeAndTeAndCopyAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(typeId, me,
					te, copy, start, end);
		}
		if (start != null) {
			return repo.findAllByTypeIdAndMeAndTeAndCopyAndDateGreaterThanEqualOrderByDate(typeId, me, te, copy, start);
		}
		if (end != null) {
			return repo.findAllByTypeIdAndMeAndTeAndCopyAndDateLessThanEqualOrderByDate(typeId, me, te, copy, end);
		}
		return repo.findAllByTypeIdAndMeAndTeAndCopyOrderByDate(typeId, me, te, copy);
	}

	public List<AggregatedDailyHistory> typeHistory(int typeId, LocalDate start, LocalDate end) {
		return itemHistory(typeId, 0, 0, false, start, end);
	}

	public List<AggregatedDailyHistory> bpoHistory(int typeId, int me, int te, LocalDate start, LocalDate end) {
		return itemHistory(typeId, me, te, false, start, end);
	}

	public List<AggregatedDailyHistory> bpcHistory(int typeId, int me, int te, LocalDate start, LocalDate end) {
		return itemHistory(typeId, me, te, true, start, end);
	}

	// query for several types

	protected List<AggregatedDailyHistory> itemsHistory(Iterable<Integer> typeIds, int me, int te, boolean copy,
			LocalDate start,
			LocalDate end) {
		if (start != null && end != null) {
			return repo.findAllByTypeIdInAndMeAndTeAndCopyAndDateGreaterThanEqualAndDateLessThanEqualOrderByDate(typeIds, me,
					te, copy, start, end);
		}
		if (start != null) {
			return repo.findAllByTypeIdInAndMeAndTeAndCopyAndDateGreaterThanEqualOrderByDate(typeIds, me, te, copy, start);
		}
		if (end != null) {
			return repo.findAllByTypeIdInAndMeAndTeAndCopyAndDateLessThanEqualOrderByDate(typeIds, me, te, copy, end);
		}
		return repo.findAllByTypeIdInAndMeAndTeAndCopyOrderByDate(typeIds, me, te, copy);
	}

	public List<AggregatedDailyHistory> typesHistory(Iterable<Integer> typeIds, LocalDate start, LocalDate end) {
		return itemsHistory(typeIds, 0, 0, false, start, end);
	}

	// find most sold

	public List<AggregatedTypeDetails> aggregateHighestIskVolume(int days, int limit) {
		var now = LocalDate.now();
		var minDay = now.minusDays(days);
		long start = System.currentTimeMillis();
		List<AggregatedTypeDetails> ret = repo.sortSalesByTotalValue(minDay, now, limit);
		long stop = System.currentTimeMillis();
		log.trace("fetched most sold over {} days in {} ms, returning {} records", days, stop - start, ret.size());
		return ret;
	}

	public boolean hasEntry(int typeId, int me, int te, boolean copy) {
		return repo.existsByTypeIdAndMeAndTeAndCopy(typeId, me, te, copy);
	}

}
