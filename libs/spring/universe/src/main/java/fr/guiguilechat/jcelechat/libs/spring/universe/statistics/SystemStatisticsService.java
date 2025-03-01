package fr.guiguilechat.jcelechat.libs.spring.universe.statistics;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.SystemJumpsService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.kills.SystemKillsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SystemStatisticsService {

	@Lazy
	private final SystemJumpsService systemJumpsService;

	@Lazy
	private final SystemKillsService systemKillsService;

	public List<SystemDateActivity> groupActivities(
			Iterable<Integer> solarSystemIds,
			SystemActivity activityType,
			DateAggregation aggregation,
			Instant since) {
		return switch (activityType) {
		case SystemActivity.jumps -> systemJumpsService.aggregateJumps(solarSystemIds, aggregation, since);
		case SystemActivity.podkills -> systemKillsService.aggregatePodKills(solarSystemIds, aggregation, since);
		case SystemActivity.npckills -> systemKillsService.aggregateNpcKills(solarSystemIds, aggregation, since);
		case SystemActivity.shipkills -> systemKillsService.aggregateShipKills(solarSystemIds, aggregation, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + activityType);
		};

	}

	public Map<Integer, List<SystemDateActivity>> activities(Iterable<Integer> solarSystemIds,
			SystemActivity activityType,
			DateAggregation aggregation,
			Instant since) {
		return groupActivities(solarSystemIds, activityType, aggregation, since).stream()
				.collect(Collectors.groupingBy(SystemDateActivity::sysId));
	}

}
