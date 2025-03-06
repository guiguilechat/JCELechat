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

import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/region")
@RequiredArgsConstructor
public class RegionRestController {

	final private ConstellationService constellationService;

	final private RegionService regionService;

	final private SolarSystemService solarSystemService;

	@GetMapping("/list")
	public ResponseEntity<List<Integer>> list(
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				regionService.listIds(),
				accept);
	}

	@GetMapping("/id/{regionId}")
	public ResponseEntity<RegionDTO> byId(
			@PathVariable int regionId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		Region region = regionService.byId(regionId);
		if (region == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "region " + regionId + " unknown");
		}
		return RestControllerHelper.makeResponse(RegionDTO.of(region), accept);
	}

	@GetMapping("/id/{regionId}/constellations")
	public ResponseEntity<List<Integer>> listConstellations(
			@PathVariable int regionId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				constellationService.listIdsByRegionId(regionId),
				accept);
	}

	@GetMapping("/id/{regionId}/solarsystems")
	public ResponseEntity<List<Integer>> listSolarSystems(
			@PathVariable int regionId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				solarSystemService.listIdsByRegionId(regionId),
				accept);
	}

}
