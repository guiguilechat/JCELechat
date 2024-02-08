package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.WarpJumpDist;

@RestController
@RequestMapping("/api/sde/stargate")
public class StargateRestController {

	@Autowired
	private StargateService stargateService;

	@GetMapping("/g2g")
	public ResponseEntity<List<WarpJumpDist>> listG2G(
			@RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(stargateService.listG2G(), accept);
	}

}
