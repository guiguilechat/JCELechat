package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.connect.services.UserConnectionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/connected/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	private final OAuth2AuthorizedClientService authorizedClientService;

	private final UserConnectionService userConnectionService;

	@GetMapping("/")
	public String getUser() {
		System.err.println("" + userConnectionService.getClass());
// System.err.println("" + authorizedClientService.);
		return "user";
	}

}
