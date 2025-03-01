package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.jfree.chart.JFreeChart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorId;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorName;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemStatisticsService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/solarsystem/search")
@RequiredArgsConstructor
public class SolarSystemSearchRestController {

	final private SolarSystemService solarSystemService;

	final private SystemStatisticsService systemStatisticsService;

	private static final int DEFAULT_DAYS = 30;

	@GetMapping("/stats/name/{selector}/chart")
	public void chartActivityName(
			@PathVariable SystemSelectorName selector,
			HttpServletResponse response,
			@RequestParam Optional<SystemActivity> left,
			@RequestParam Optional<SystemActivity> right,
			@RequestParam List<String> names,
			@RequestParam Optional<Integer> days,
			@RequestParam Optional<String> accept)
			throws IOException {

		if ((left == null || left.isEmpty()) && (right == null || right.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "need at least left or right");
		}
		List<Integer> sids = solarSystemService.selectNames(selector, names);
		Map<Integer, String> sids2Names = solarSystemService.namesForIds(sids);
		Instant since = RestControllerHelper.since(days, DEFAULT_DAYS);
		Map<Integer, List<SystemDateActivity>> leftValues = null;
		if (left.isPresent()) {
			leftValues = systemStatisticsService.activities(sids, left.get(), DateAggregation.hourly, since);
		}
		Map<Integer, List<SystemDateActivity>> rightValues = null;
		if (right.isPresent()) {
			rightValues = systemStatisticsService.activities(sids, right.get(), DateAggregation.hourly, since);
		}
		JFreeChart chart = drawActivityChart(sids2Names, left, leftValues, right, rightValues);
		RestControllerHelper.addResponseJFreeChart(response, chart, accept);
	}

	private JFreeChart drawActivityChart(Map<Integer, String> sids2Names,
			Optional<SystemActivity> left,
			Map<Integer, List<SystemDateActivity>> leftValues,
			Optional<SystemActivity> right,
			Map<Integer, List<SystemDateActivity>> rightValues) {
		LinkedHashMap<Integer, String> sortedSId2Name = new LinkedHashMap<>();
		sids2Names.entrySet().stream().sorted(Comparator.comparing(Entry::getValue))
				.forEach(e -> sortedSId2Name.put(e.getKey(), e.getValue()));

		return null;
	}

	@Operation(summary = "search activity by names", description = "list aggregated activities for an activity type and searched systems")
	@GetMapping("/name/{selector}/stats/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivityNames(
			@PathVariable @Parameter(description = "name to ids function") SystemSelectorName selector,
			@PathVariable @Parameter(description = "selected activity") SystemActivity activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam @Parameter(description = "(comma separated) names to transform into system ids. Example : jita,Amarr,doDixiE") List<String> names,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						solarSystemService.selectNames(selector, names),
						activity,
						aggreg,
						RestControllerHelper.since(days, 30)),
				accept);
	}

	@Operation(summary = "search activity by ids", description = "list aggregated activities for an activity type and searched systems")
	@GetMapping("/id/{selector}/stats/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivityIds(
			@PathVariable @Parameter(description = "id to ids function") SystemSelectorId selector,
			@PathVariable @Parameter(description = "selected activity") SystemActivity activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam @Parameter(description = "(comma separated) ids to transform into system ids") List<Integer> ids,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						solarSystemService.selectIds(selector, ids),
						activity,
						aggreg,
						RestControllerHelper.since(days, 30)),
				accept);
	}

}
