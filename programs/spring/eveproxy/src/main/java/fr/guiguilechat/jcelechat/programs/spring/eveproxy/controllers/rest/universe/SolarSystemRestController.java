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

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.DateAggregation;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemDateActivity;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.SystemStatisticsService;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.PeriodHeat;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.SystemJumpsService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/solarsystem/id")
@RequiredArgsConstructor
public class SolarSystemRestController {

	final private SolarSystemService solarSystemService;

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

		static SolarSystemDTO of(SolarSystem ss) {
			return new SolarSystemDTO(ss.getId(),
					ss.getName(),
					ss.getConstellation().getId(),
					ss.getConstellation().getRegion().getId(),
					ss.getConstellation().getRegion().getUniverse(),
					null);
		}
	}

	SolarSystemDTO toDTOWithAdjacents(SolarSystem ss) {
		return SolarSystemDTO.of(ss, solarSystemService.adjacent(ss));
	}

	@GetMapping("/{solarSystemId}")
	public ResponseEntity<SolarSystemDTO> byId(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		SolarSystem ss = solarSystemService.byId(solarSystemId);
		if (ss == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "system " + solarSystemId + " unknown");
		}
		return RestControllerHelper.makeResponse(SolarSystemDTO.of(ss), accept);
	}

	@GetMapping("/{solarSystemId}/adjacent")
	public ResponseEntity<List<SolarSystemDTO>> adjacent(
			@PathVariable int solarSystemId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		SolarSystem ss = solarSystemService.byId(solarSystemId);
		if (ss == null) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "system " + solarSystemId + " unknown");
		}
		List<SolarSystem> adjacents = solarSystemService.adjacent(ss);
		return RestControllerHelper.makeResponse(adjacents.stream().map(this::toDTOWithAdjacents).toList(), accept);
	}

	@Operation(summary = "system activity", description = "list aggregated activities for an activity type")
	@GetMapping("/{solarSystemId}/stats/{activity}/{aggreg}")
	public ResponseEntity<List<SystemDateActivity>> groupActivity(
			@PathVariable @Parameter(description = "solar system id. Jita=30000142 ") int solarSystemId,
			@PathVariable @Parameter(description = "selected activity") SystemActivity activity,
			@PathVariable @Parameter(description = "period to aggregte activity over") DateAggregation aggreg,
			@RequestParam Optional<ACCEPT_TEXT> accept,
			@RequestParam @Parameter(description = "days to limit the search, default 30") Optional<Integer> days) {
		return RestControllerHelper.makeResponse(
				systemStatisticsService.groupActivities(
						List.of(solarSystemId),
						activity,
						aggreg,
						RestControllerHelper.since(days, 30)),
				accept);
	}

	private static final int DEFAULT_WEEKS = 10;

	@GetMapping("/{solarSystemId}/jumps/weekmap")
	public ResponseEntity<List<PeriodHeat>> jumpsWeekMap(@PathVariable int solarSystemId,
			@RequestParam Optional<Integer> weeks,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				systemJumpsService.wActivity(List.of(solarSystemId),
						weeks == null ? DEFAULT_WEEKS : weeks.orElse(DEFAULT_WEEKS)),
				accept);
	}

}
