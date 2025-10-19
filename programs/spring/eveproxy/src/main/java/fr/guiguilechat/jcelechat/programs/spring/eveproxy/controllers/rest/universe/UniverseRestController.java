package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe")
@RequiredArgsConstructor
public class UniverseRestController {

	final private ConstellationService constellationService;

	final private RegionService regionService;

	final private SolarSystemService solarSystemService;

	@GetMapping("/list")
	public ResponseEntity<List<String>> list(
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				regionService.listUniverses(),
				accept);
	}

	@GetMapping("/name/{universe}/regions")
	public ResponseEntity<List<Integer>> listRegions(
			@PathVariable String universe,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				regionService.listIdsByUniverse(universe),
				accept);
	}

	@GetMapping("/name/{universe}/constellations")
	public ResponseEntity<List<Integer>> listConstellations(
			@PathVariable String universe,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				constellationService.listIdsByUniverse(universe),
				accept);
	}

	@GetMapping("/name/{universe}/systems")
	public ResponseEntity<List<Integer>> listSystems(
			@PathVariable String universe,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				solarSystemService.listIdsByUniverse(universe),
				accept);
	}

}
