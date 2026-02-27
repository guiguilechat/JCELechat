package fr.guiguilechat.jcelechat.libs.spring.anon.universe.jumps;

import java.time.Instant;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.DateActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.PeriodHeat;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.SystemDateActivity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SystemJumpsService {

	private final SystemJumpsRepository itemRepository;

	/**
	 * @return the stored time-jumps of given system
	 */
	public List<DateActivity> listJumps(
			int systemId,
			Instant since) {
		List<Object[]> rows = itemRepository.jumpsForSystemId(systemId, since);
		return rows
				.stream()
				.map(DateActivity::ofRow)
				.toList();
	}

	/**
	 * @return the aggregated jumps of several system ids
	 */
	public List<SystemDateActivity> aggregateJumps(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository.aggregateJumpsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository.aggregateJumpsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository.aggregateJumpsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository.aggregateJumpsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	/**
	 * converts a row from {@link SystemJumpsRepository#activity(Iterable, int)}
	 * into a
	 * {@link PeriodHeat}
	 *
	 * @param line
	 * @return
	 */
	static PeriodHeat wActivity(Object[] line) {
		return PeriodHeat.builder()
				.ssId(((Number) line[0]).intValue())
				.dow(((Number) line[1]).intValue())
				.h(((Number) line[2]).intValue())
				.dailyJumps(((Number) line[3]).doubleValue()).build();
	}

	public List<PeriodHeat> wActivity(Iterable<Integer> systemIds, int weeks) {
		return itemRepository.activity(systemIds, weeks).stream().map(SystemJumpsService::wActivity).toList();
	}

}
