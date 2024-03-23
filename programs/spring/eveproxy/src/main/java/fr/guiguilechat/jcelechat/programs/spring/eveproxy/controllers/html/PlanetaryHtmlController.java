package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.PlanetEvalParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/planet")
@RequiredArgsConstructor
public class PlanetaryHtmlController {

	private final PlanetEvalService planetEvalService;

	@Transactional
	@GetMapping("/factories")
	public String getFactories(Model model, PlanetEvalParams params) {

		model.addAttribute("params", params);
		model.addAttribute("evals", planetEvalService.evaluate(params));
		return "planetary/factories";
	}
}
