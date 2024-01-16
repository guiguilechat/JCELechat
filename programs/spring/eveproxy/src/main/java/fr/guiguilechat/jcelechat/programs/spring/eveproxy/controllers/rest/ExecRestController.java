package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.UpdateResultService;

@RestController
@RequestMapping("/api/exec")
public class ExecRestController {

	@Autowired
	private UpdateResultService urService;

	@GetMapping("/sde/forcefetch")
	public ResponseEntity<?> jitaByType() {
		urService.requireFetch();
		return ResponseEntity.ok("");
	}

}
