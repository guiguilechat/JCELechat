package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sde/solarsystem")
@RequiredArgsConstructor
public class SolarSystemRestController {

	final private SolarSystemService ssService;

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
			@RequestParam Optional<String> accept) {
		SolarSystem ss = ssService.byId(solarSystemId);
		if (ss == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "system " + solarSystemId + " unknown");
		}
		return RestControllerHelper.makeResponse(toDTO(ss), accept);
	}

	@GetMapping("/byid/{solarSystemId}/adjacent")
	public ResponseEntity<List<SolarSystemDTO>> adjacent(
			@PathVariable int solarSystemId,
			@RequestParam Optional<String> accept) {
		SolarSystem ss = ssService.byId(solarSystemId);
		if (ss == null) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "system " + solarSystemId + " unknown");
		}
		List<SolarSystem> adjacents = ssService.adjacent(ss);
		return RestControllerHelper.makeResponse(adjacents.stream().map(this::toDTO).toList(), accept);
	}

}
