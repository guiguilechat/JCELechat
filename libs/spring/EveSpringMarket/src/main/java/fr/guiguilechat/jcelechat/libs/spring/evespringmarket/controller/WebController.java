package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "welcome to eve market";
	}

}
