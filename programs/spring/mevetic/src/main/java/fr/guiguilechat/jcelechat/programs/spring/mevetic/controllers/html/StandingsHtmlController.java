package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/standings")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StandingsHtmlController {

	@GetMapping("/")
	public String root() {
		return "standings/root";
	}

}
