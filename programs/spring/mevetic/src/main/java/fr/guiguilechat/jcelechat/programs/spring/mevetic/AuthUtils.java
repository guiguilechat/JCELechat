package fr.guiguilechat.jcelechat.programs.spring.mevetic;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;

public class AuthUtils {

	public static void addUser(Model model, Authentication auth) {
		model.addAttribute("charName", EsiUserService.getCharacterName(auth));
		model.addAttribute("charId", EsiUserService.getCharacterId(auth));
	}

}
