package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.SolarSystemService;

@RestController
@RequestMapping("/api/solarsystem")
public class SolarSystemRestController {

	@Autowired
	private SolarSystemService ssService;

	record SolarSystemDTO(int solarSystemId, String name, int constellationId, int regionId, String universe) {
		static SolarSystemDTO of(SolarSystem ss) {
			return new SolarSystemDTO(ss.getSolarSystemId(),
					ss.getName(),
					ss.getConstellation().getConstellationId(),
					ss.getConstellation().getRegion().getRegionId(),
					ss.getConstellation().getRegion().getUniverse());
		}
	};

	@GetMapping("/byid/{solarSystemId}")
	public ResponseEntity<?> byId(@PathVariable int solarSystemId) {
		SolarSystem ss = ssService.findById(solarSystemId);
		if (ss == null) {
			return ResponseEntity.status(404).body("system " + solarSystemId + " unknown");
		}
		return ResponseEntity
				.ok(SolarSystemDTO.of(ss));
	}

}
