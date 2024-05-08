package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexHtmlController {

	@GetMapping("/")
	public String getRoot(Model model) {
		return "index";
	}

	@GetMapping("/index")
	public String getIndex(Model model) {
		System.err.println("getIndex");
		return getRoot(model);
	}
}
