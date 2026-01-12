package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndexHtmlController {

	@GetMapping("/")
	public String getIndex() {
		return "redirect:html";
	}

	@GetMapping("/html")
	public String getIndexHtml() {
		return "index";
	}

}
