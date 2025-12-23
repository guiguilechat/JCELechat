package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.DateActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemActivityType;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemStatisticsService;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.PeriodHeat;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.universe.jumps.SystemJumpsService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SecFilter;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe.dto.SolarSystemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/solarsystem")
@RequiredArgsConstructor
public class SolarsystemRestController {

	final private SolarSystemService solarSystemService;

	final private SystemStatisticsService systemStatisticsService;

	final private SystemJumpsService systemJumpsService;


	@GetMapping("/list")
	public ResponseEntity<List<Integer>> list(
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				solarSystemService.listIds(),
				accept);
	}

	@GetMapping("/list/{security}")
	public ResponseEntity<List<Integer>> listBySecurity(
			@PathVariable SecFilter security,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				solarSystemService.listIdsBySecurityBetween(security.lowerSS, security.higherSS),
				accept);
	}

	@GetMapping("/id/{solarSystemId}")
	public ResponseEntity<SolarSystemDTO> byId(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		SolarSystem ss = solarSystemService.byId(solarSystemId);
		if (ss == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "system " + solarSystemId + " unknown");
		}
		return RestControllerHelper.makeResponse(SolarSystemDTO.of(ss), accept);
	}

	@GetMapping("/id/{solarSystemId}/adjacent")
	public ResponseEntity<List<Integer>> adjacent(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(solarSystemService.adjacentIds(solarSystemId), accept);
	}

	@Operation(summary = "system activity", description = "list stored activities for an activity type. The date is 30minute before the returned last-modified header of the server, at UTC.")
	@GetMapping("/id/{solarSystemId}/{activity}")
	public ResponseEntity<List<DateActivity>> activity(
			@PathVariable @Parameter(description = "solar system id. Jita=30000142 ") int solarSystemId,
			@PathVariable @Parameter(description = "selected activity") SystemActivityType activity,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days prior to today to get the activities. Default 1") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.activities(
						solarSystemId,
						activity,
						RestControllerHelper.sinceDefault(days, 1)),
				accept);
	}

	@Operation(summary = "system activity", description = "list aggregated activities for an activity type")
	@GetMapping("/id/{solarSystemId}/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivity(
			@PathVariable @Parameter(description = "solar system id. Jita=30000142 ") int solarSystemId,
			@PathVariable @Parameter(description = "selected activity") SystemActivityType activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						List.of(solarSystemId),
						activity,
						aggreg,
						RestControllerHelper.sinceDefault(days, 30)),
				accept);
	}

	// TODO rework that
	private static final int DEFAULT_WEEKS = 10;

	@GetMapping("/id/{solarSystemId}/jumps/weekmap")
	public ResponseEntity<List<PeriodHeat>> jumpsWeekMap(@PathVariable int solarSystemId,
			@RequestParam Optional<Integer> weeks,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				systemJumpsService.wActivity(List.of(solarSystemId),
						weeks == null ? DEFAULT_WEEKS : weeks.orElse(DEFAULT_WEEKS)),
				accept);
	}

}
