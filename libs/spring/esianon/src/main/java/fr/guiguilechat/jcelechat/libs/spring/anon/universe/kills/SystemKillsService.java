package fr.guiguilechat.jcelechat.libs.spring.anon.universe.kills;

import java.time.Instant;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.DateActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.SystemDateActivity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SystemKillsService {

	private final SystemKillsRepository itemRepository;

	/**
	 * @return the stored time-npckills of given system
	 */
	public List<DateActivity> listNpcKills(
			int systemId,
			Instant since) {
		List<Object[]> rows = itemRepository.npcKillsForSystemId(systemId, since);
		return rows
				.stream()
				.map(DateActivity::ofRow)
				.toList();
	}

	public List<SystemDateActivity> aggregateNpcKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository.aggregateNpcKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository.aggregateNpcKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository.aggregateNpcKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository.aggregateNpcKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	/**
	 * @return the stored time-podkills of given system
	 */
	public List<DateActivity> listPodKills(
			int systemId,
			Instant since) {
		List<Object[]> rows = itemRepository.podKillsForSystemId(systemId, since);
		return rows
				.stream()
				.map(DateActivity::ofRow)
				.toList();
	}

	public List<SystemDateActivity> aggregatePodKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository.aggregatePodKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository.aggregatePodKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository.aggregatePodKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository.aggregatePodKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

	/**
	 * @return the stored time-shipkills of given system
	 */
	public List<DateActivity> listShipKills(
			int systemId,
			Instant since) {
		List<Object[]> rows = itemRepository.shipKillsForSystemId(systemId, since);
		return rows
				.stream()
				.map(DateActivity::ofRow)
				.toList();
	}

	public List<SystemDateActivity> aggregateShipKills(
			Iterable<Integer> systemIds,
			DateAggregation aggregation,
			Instant since) {
		List<Object[]> rows = switch (aggregation) {
		case DateAggregation.hourly -> itemRepository.aggregateShipKillsHourly(systemIds, since);
		case DateAggregation.daily -> itemRepository.aggregateShipKillsDaily(systemIds, since);
		case DateAggregation.weekly -> itemRepository.aggregateShipKillsWeekly(systemIds, since);
		case DateAggregation.monthly -> itemRepository.aggregateShipKillsMonthly(systemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + aggregation);
		};
		return rows
				.stream()
				.map(aggregation::ActivityOfRow)
				.toList();
	}

}
