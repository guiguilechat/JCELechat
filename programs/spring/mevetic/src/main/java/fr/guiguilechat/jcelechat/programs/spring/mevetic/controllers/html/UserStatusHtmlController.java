package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import fr.guiguilechat.jcelechat.libs.spring.npc.model.Corporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.Faction;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationService;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.FactionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_standings_from_type;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
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

	@Lazy
	private final CorporationService corporationService;

	@Lazy
	private final FactionService factionService;

	@GetMapping("/")
	public String getUser(Model model, Authentication auth) {
		int charId = EsiUserService.getCharacterId(auth);
		Optional<CharacterAffiliation> oca = characterAffiliationService.getFetched(charId);
		if (oca.isPresent()) {
			model.addAttribute("affiliation", oca.get());
		}
		return "user/settings";
	}

	@GetMapping("/roles")
	public String getRoles(Model model, Authentication auth) {
		Optional<CharacterRoles> userRoles = characterRolesService.getFetched(EsiUserService.getCharacterId(auth));
		if (userRoles.isPresent()) {
			model.addAttribute("roles", userRoles.get());
		}
		return "user/roles";
	}

	@GetMapping("/contacts")
	public String getContacts(Model model, Authentication auth) {
		List<CharacterContact> userContacts = characterContactService
		    .list(EsiUserService.getCharacterId(auth));
		if (userContacts != null) {
			model.addAttribute("contacts", userContacts.stream().filter(cc -> !cc.isBlocked()).toList());
			model.addAttribute("blocked", userContacts.stream().filter(CharacterContact::isBlocked).toList());
		}
		return "user/contacts";
	}

	public static record NamedStanding(String fromName, int fromId, float standing) {

		public NamedStanding(CharacterStanding standing, String fromName) {
			this(fromName, standing.getFromId(), standing.getStanding());
		}

	}

	public NamedStanding factionStanding(CharacterStanding standing, Map<Integer, Faction> factions) {
		Faction f = factions.get(standing.getFromId());
		String name = f == null ? "faction" + standing.getFromId() : f.getName();
		return new NamedStanding(standing, name);
	}

	public NamedStanding corporationStanding(CharacterStanding standing, Map<Integer, Corporation> corporations) {
		Corporation c = corporations.get(standing.getFromId());
		String name = c == null ? "faction" + standing.getFromId() : c.getName();
		return new NamedStanding(standing, name);
	}

	public NamedStanding agentStanding(CharacterStanding standing) {
		return new NamedStanding(standing, "");
	}

	@GetMapping("/standings")
	public String getStandings(Model model, Authentication auth) {
		List<CharacterStanding> userStandings = characterStandingService
		    .list(EsiUserService.getCharacterId(auth));
		if (userStandings != null) {
			Map<Integer, Faction> factions = factionService.allById();
			Map<Integer, Corporation> corporations = corporationService.allById();
			model.addAttribute("agentStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.agent)
			    .sorted(Comparator.comparing(s -> -s.getStanding()))
			    .map(this::agentStanding)
			    .toList());
			model.addAttribute("corporationStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.npc_corp)
			    .sorted(Comparator.comparing(s -> -s.getStanding()))
			    .map(c -> corporationStanding(c, corporations))
			    .toList());
			model.addAttribute("factionStandings", userStandings.stream()
			    .filter(cs -> cs.getFromType() == get_characters_character_id_standings_from_type.faction)
			    .sorted(Comparator.comparing(s -> -s.getStanding()))
			    .map(f -> factionStanding(f, factions))
			    .toList());
		}
		return "user/standings";
	}

}
