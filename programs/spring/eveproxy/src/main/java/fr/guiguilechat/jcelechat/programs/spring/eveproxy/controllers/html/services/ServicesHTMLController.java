package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.UpdateScheduler;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.UpdateScheduler.UpdatersList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/services")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ServicesHTMLController {

	@Lazy
	private final UpdateScheduler scheduler;

	@GetMapping("/")
	public String getRoot(Model model) {
		UpdatersList updaters = scheduler.updatersStatus();
		model.addAttribute("active", updaters.active());
		model.addAttribute("inactive", updaters.inactive());
		return "services/index";
	}

	public String rootUrl() {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getRoot", (Model) null)
				.build()
				.toUri()
				.toString();
	}

}
