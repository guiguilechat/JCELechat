package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.CharacterRoles;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.CharacterRolesService;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/connected/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	private final CharacterRolesService characterRolesService;

	protected void addUser(Model model, Authentication auth) {
		model.addAttribute("charName", EsiUserService.getCharacterName(auth));
		model.addAttribute("charId", EsiUserService.getCharacterId(auth));
	}

	@GetMapping("/")
	public String getUser(Model model, Authentication auth) {
		addUser(model, auth);
		return "user/index";
	}

	@GetMapping("/roles")
	public String getRoles(Model model, Authentication auth) {
		addUser(model, auth);
		Optional<CharacterRoles> userRoles = characterRolesService.fetchIfNeeded(EsiUserService.getCharacterId(auth));
		if (userRoles.isPresent()) {
			model.addAttribute("roles", userRoles.get().getRoles());
		}
		return "user/roles";
	}

}
