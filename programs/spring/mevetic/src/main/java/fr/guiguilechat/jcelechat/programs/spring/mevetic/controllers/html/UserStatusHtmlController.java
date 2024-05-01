package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.CharacterRoles;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.CharacterRolesService;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/connected/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	@Lazy
	private final CharacterRolesService characterRolesService;

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	protected void addUser(Model model, Authentication auth) {
		model.addAttribute("charName", EsiUserService.getCharacterName(auth));
		model.addAttribute("charId", EsiUserService.getCharacterId(auth));
	}

	@GetMapping("/")
	public String getUser(Model model, Authentication auth) {
		addUser(model, auth);
		int charId = EsiUserService.getCharacterId(auth);
		Optional<CharacterAffiliation> oca = characterAffiliationService.getFetched(charId);
		if (oca.isPresent()) {
			model.addAttribute("affiliation", oca.get());
		}
		return "user/index";
	}

	@GetMapping("/roles")
	public String getRoles(Model model, Authentication auth) {
		addUser(model, auth);
		Optional<CharacterRoles> userRoles = characterRolesService.getFetched(EsiUserService.getCharacterId(auth));
		if (userRoles.isPresent()) {
			model.addAttribute("roles", userRoles.get());
		}
		return "user/roles";
	}

}
