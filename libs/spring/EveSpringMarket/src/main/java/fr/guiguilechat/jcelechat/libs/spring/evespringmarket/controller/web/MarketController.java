package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/market")
public class MarketController {

	@GetMapping("/**")
	@ResponseBody
	public String index() {
		return "welcome to eve market";
	}

}
