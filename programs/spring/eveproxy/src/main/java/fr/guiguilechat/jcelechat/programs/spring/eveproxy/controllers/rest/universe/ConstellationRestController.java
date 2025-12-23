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

import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystemService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe.dto.ConstellationDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/constellation")
@RequiredArgsConstructor
public class ConstellationRestController {

	final private ConstellationService constellationService;

	final private SolarSystemService solarSystemService;

	@GetMapping("/list")
	public ResponseEntity<List<Integer>> list(
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				constellationService.listIds(),
				accept);
	}

	@GetMapping("/id/{constellationId}")
	public ResponseEntity<ConstellationDTO> byId(
			@PathVariable int constellationId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		Constellation c = constellationService.byId(constellationId);
		if (c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "constellation " + constellationId + " unknown");
		}
		return RestControllerHelper.makeResponse(ConstellationDTO.of(c), accept);
	}

	@GetMapping("/id/{constellationId}/solarsystems")
	public ResponseEntity<List<Integer>> listSolarSystems(
			@PathVariable int constellationId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				solarSystemService.listIdsByConstellationId(constellationId),
				accept);
	}

}
