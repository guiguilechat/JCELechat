package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContactService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.roles.CharacterRoles;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.roles.CharacterRolesService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStandingService;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_standings_from_type;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/connected/user")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserStatusHtmlController {

	@Lazy
	private final CharacterAffiliationService characterAffiliationService;

	@Lazy
	private final CharacterContactService characterContactService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final CharacterRolesService characterRolesService;

	@Lazy
	private final CharacterStandingService characterStandingService;

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

	@GetMapping("/contacts")
	public String getContacts(Model model, Authentication auth) {
		addUser(model, auth);
		List<CharacterContact> userContacts = characterContactService
		    .list(EsiUserService.getCharacterId(auth));
		if (userContacts != null) {
			model.addAttribute("contacts", userContacts.stream().filter(cc -> !cc.isBlocked()).toList());
			model.addAttribute("blocked", userContacts.stream().filter(CharacterContact::isBlocked).toList());
		}
		return "user/contacts";
	}

	@GetMapping("/standings")
	public String getStandings(Model model, Authentication auth) {
		addUser(model, auth);
		List<CharacterStanding> userStandings = characterStandingService
		    .list(EsiUserService.getCharacterId(auth));
		if (userStandings != null) {
			model.addAttribute("agentStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.agent).toList());
			model.addAttribute("corporationStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.npc_corp).toList());
			model.addAttribute("factionStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.faction).toList());
		}
		return "user/standings";
	}

}
