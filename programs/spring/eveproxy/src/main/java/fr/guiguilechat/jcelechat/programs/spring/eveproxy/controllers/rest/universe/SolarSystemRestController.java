package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorName;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemStatisticsService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.DailyJumps;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.PeriodHeat;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.SystemJumpsService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sde/solarsystem")
@RequiredArgsConstructor
public class SolarSystemRestController {


	final private SolarSystemService ssService;

	final private SystemStatisticsService systemStatisticsService;

	final private SystemJumpsService systemJumpsService;

	record SolarSystemDTO(int solarSystemId, String name, int constellationId, int regionId, String universe,
			List<Integer> adajcent) {
		static SolarSystemDTO of(SolarSystem ss, List<SolarSystem> adajcent) {
			return new SolarSystemDTO(ss.getId(),
					ss.getName(),
					ss.getConstellation().getId(),
					ss.getConstellation().getRegion().getId(),
					ss.getConstellation().getRegion().getUniverse(),
					adajcent.stream().map(SolarSystem::getId).toList());
		}
	}

	SolarSystemDTO toDTO(SolarSystem ss) {
		return SolarSystemDTO.of(ss, ssService.adjacent(ss));
	}

	@GetMapping("/byid/{solarSystemId}")
	public ResponseEntity<SolarSystemDTO> byId(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		SolarSystem ss = ssService.byId(solarSystemId);
		if (ss == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "system " + solarSystemId + " unknown");
		}
		return RestControllerHelper.makeResponse(toDTO(ss), accept);
	}

	@GetMapping("/byid/{solarSystemId}/adjacent")
	public ResponseEntity<List<SolarSystemDTO>> adjacent(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		SolarSystem ss = ssService.byId(solarSystemId);
		if (ss == null) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "system " + solarSystemId + " unknown");
		}
		List<SolarSystem> adjacents = ssService.adjacent(ss);
		return RestControllerHelper.makeResponse(adjacents.stream().map(this::toDTO).toList(), accept);
	}

	@GetMapping("/byid/{solarSystemId}/jumps/daily")
	public ResponseEntity<List<DailyJumps>> jumpsDaily(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(systemJumpsService.dailyJumps(List.of(solarSystemId)), accept);
	}

	private static final int DEFAULT_WEEKS = 10;

	@GetMapping("/byid/{solarSystemId}/jumps/weekmap")
	public ResponseEntity<List<PeriodHeat>> jumpsWeekMap(@PathVariable int solarSystemId,
			@RequestParam Optional<Integer> weeks,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				systemJumpsService.wActivity(List.of(solarSystemId),
						weeks == null ? DEFAULT_WEEKS : weeks.orElse(DEFAULT_WEEKS)),
				accept);
	}

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
		List<Integer> sids = ssService.selectNames(selector, names);
		Map<Integer, String> sids2Names = ssService.namesForIds(sids);
		int daysValue = days == null ? DEFAULT_DAYS : days.orElse(DEFAULT_DAYS);
		if (daysValue < 0) {
			daysValue=0;
		}
		Instant since = Instant.now().truncatedTo(ChronoUnit.DAYS).minus(daysValue, ChronoUnit.DAYS);
		Map<Integer, List<SystemDateActivity>> leftValues = null;
		if (left.isPresent()) {
			leftValues = systemStatisticsService.activities(sids, left.get(), since);
		}
		Map<Integer, List<SystemDateActivity>> rightValues = null;
		if (right.isPresent()) {
			rightValues = systemStatisticsService.activities(sids, right.get(), since);
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

}
