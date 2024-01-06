package fr.guiguilechat.jcelechat.libs.spring.evehistory.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootContoller {


	@GetMapping("/index")public ResponseEntity<?> index() {
		return ResponseEntity.ok("working");
	}

}
