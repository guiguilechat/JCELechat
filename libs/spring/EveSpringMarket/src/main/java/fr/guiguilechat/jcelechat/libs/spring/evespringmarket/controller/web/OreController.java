package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api.OreAPI;
import fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api.OreAPI.Orderer;
import fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api.OreAPI.Security;

@Controller
@RequestMapping("/web/ore")
public class OreController {

	@Autowired
	OreAPI oreapi;

	@GetMapping("/volumic")
	public String volumic(Model model, Optional<Security> sec, Optional<Integer> regionid, Optional<Long> minvol,
			Optional<String> filter, Optional<Orderer> sort, Optional<Float> eff, Optional<String> allowNoOffer) {
		model.addAttribute("volumes", oreapi.volumic(sec, regionid, minvol, filter, sort, eff, allowNoOffer));
		return "oreVolumic";
	}

	@GetMapping("/**")
	@ResponseBody
	public String index() {
		return "welcome to eve ore";
	}

}
