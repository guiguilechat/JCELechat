package fr.guiguilechat.jcelechat.programs.spring.mevetic.controllers.html;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.character.CharacterInformation;
import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.character.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.faction.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.affiliations.faction.FactionInfoService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.C2CStandingsService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding.CharacterStandingList;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStandingService;
import fr.guiguilechat.jcelechat.libs.spring.connect.user.EsiUserService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_standings_from_type;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/standings")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StandingsHtmlController {

	@Lazy
	private final C2CStandingsService c2cStandingsService;

	@Lazy
	private final CharacterInformationService characterInformationService;

	@Lazy
	private final CharacterStandingService characterStandingService;

	@Lazy
	private final CorporationInfoService corporationService;

	@Lazy
	private final FactionInfoService factionService;

	@GetMapping("/")
	public String avail(Model model, Authentication auth) {
		int charId = EsiUserService.getCharacterId(auth);
		List<LinkedcharacterStanding> charStandings = Stream
		    .concat(Stream.of(linkedcharacterStanding(charId)), c2cStandingsService.effectiveStandings(charId)
		    .entrySet().stream()
		    .filter(e -> e.getValue() >= 5.0)
		        .map(e -> linkedcharacterStanding(e.getKey())))
		    .distinct()
		    .sorted(Comparator.comparing(LinkedcharacterStanding::charName))
		    .toList();
		model.addAttribute("available", charStandings);
		return "standings/avail";
	}

	@GetMapping("/{targetCharId}")
	public String standings(Model model, Authentication auth, @PathVariable int targetCharId) {
		int userCharId = EsiUserService.getCharacterId(auth);
		if (c2cStandingsService.effectiveStanding(targetCharId, userCharId) < 5.0) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
			    "you are not allowed");
		}
		addNPCStandings(model, targetCharId);
		return "standings/user";
	}

	public URI uri(int charId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "standings", null, null, charId).build()
		    .toUri();
	}

	public static record LinkedcharacterStanding(String charName, String url) {

	}

	public LinkedcharacterStanding linkedcharacterStanding(int charId) {
		return new LinkedcharacterStanding(characterInformationService.createFetch(charId).getName(), uri(charId).toString());
	}

	protected void addNPCStandings(Model model, int charId) {
		CharacterInformation CharInfo = characterInformationService.createFetch(charId);
		model.addAttribute("charName", CharInfo.getName());
		CharacterStandingList charFetch = characterStandingService.createFetch(charId);
		List<CharacterStanding> userStandings = characterStandingService
		    .list(charId);
		if (userStandings != null) {
			Map<Integer, FactionInfo> factions = factionService.allById();
			Map<Integer, CorporationInfo> corporations = corporationService.allById();
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
		if (charFetch != null && charFetch.isFetched()) {
			if (charFetch.getExpires() != null) {
				Duration remain = Duration.between(Instant.now(), charFetch.getExpires());
				model.addAttribute("expires",
				    remain.toString().substring(2).replaceAll("\\.[0-9]*", ""));
			}
			if (charFetch.getLastUpdate() != null) {
				model.addAttribute("lastupdate", charFetch.getLastUpdate());
			}
		}
	}

	public static record NamedStanding(String fromName, int fromId, float standing) {

		public NamedStanding(CharacterStanding standing, String fromName) {
			this(fromName, standing.getFromId(), standing.getStanding());
		}

	}

	public NamedStanding factionStanding(CharacterStanding standing, Map<Integer, FactionInfo> factions) {
		FactionInfo f = factions.get(standing.getFromId());
		String name = f == null ? "faction" + standing.getFromId() : f.getName();
		return new NamedStanding(standing, name);
	}

	public NamedStanding corporationStanding(CharacterStanding standing, Map<Integer, CorporationInfo> corporations) {
		CorporationInfo c = corporations.get(standing.getFromId());
		String name = c == null ? "corporation" + standing.getFromId() : c.getName();
		return new NamedStanding(standing, name);
	}

	public NamedStanding agentStanding(CharacterStanding standing) {
		return new NamedStanding(standing, "");
	}

}
