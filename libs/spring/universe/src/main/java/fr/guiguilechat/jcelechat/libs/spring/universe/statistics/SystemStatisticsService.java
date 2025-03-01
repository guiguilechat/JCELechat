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

	public Map<Integer, List<SystemDateActivity>> activities(Iterable<Integer> solarSystemIds,
			SystemActivity activityType, Instant since) {
		return listActivities(solarSystemIds, activityType, since).stream()
				.collect(Collectors.groupingBy(SystemDateActivity::sysId));
	}

	protected List<SystemDateActivity> listActivities(Iterable<Integer> solarSystemIds, SystemActivity activityType,
			Instant since) {
		return switch (activityType) {
		case SystemActivity.jumps -> systemJumpsService.forSystemIds(solarSystemIds, since);
		case SystemActivity.podkills -> systemKillsService.podKillsForSystemIds(solarSystemIds, since);
		case SystemActivity.npckills -> systemKillsService.npcKillsForSystemIds(solarSystemIds, since);
		case SystemActivity.shipkills -> systemKillsService.shipKillsForSystemIds(solarSystemIds, since);
		default ->
			throw new IllegalArgumentException("Unexpected value: " + activityType);
		};

	}

}
