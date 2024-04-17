package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/connected/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	private final OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping("/")
	public String getUser() {
		return "user";
	}

}
