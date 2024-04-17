package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexHtmlController {

	@GetMapping("/")
	public String getRoot() {
		return "index";
	}

	@GetMapping("/index")
	public String getIndex() {
		return getRoot();
	}
}
