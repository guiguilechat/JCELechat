package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.npc;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.NpcCharacterService;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.NpcCharacterService.AgentDetails;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/npc/agent")
@RequiredArgsConstructor
public class AgentRestController {

	private final NpcCharacterService npcCharacterService;

	@GetMapping("/list")
	public ResponseEntity<List<Integer>> list(@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				npcCharacterService.listIds(),
				accept);
	}

	@Transactional
	@GetMapping(value = "/csvdetails", produces = "text/csv")
	public String csvDetails(
			@RequestParam Optional<String> fs,
			@RequestParam Optional<String> ls) {
		String fieldSeparator = ",";
		if (fs != null && fs.isPresent()) {
			fieldSeparator = fs.get();
		}
		String lineSeparator = "\n";
		if (ls != null && ls.isPresent()) {
			lineSeparator = ls.get();
		}
		StringBuilder sb = new StringBuilder();
		for(AgentDetails ad : npcCharacterService.agentDetails()) {
			sb.append(ad.agent().getId());
			sb.append(fieldSeparator).append(ad.agent().getName());
			sb.append(fieldSeparator).append(ad.agent().getAgentType().getName());
			sb.append(fieldSeparator).append(ad.agent().getDivision().getName());
			sb.append(fieldSeparator).append(ad.agent().getLevel());
			sb.append(fieldSeparator).append(ad.agent().isLocator());
			sb.append(fieldSeparator).append(ad.corporation().getId());
			sb.append(fieldSeparator).append(ad.corporation().getName());
			sb.append(fieldSeparator).append(ad.station() != null ? ad.station().getId() : "");
			sb.append(fieldSeparator).append(ad.station() != null ? ad.station().name() : "");
			sb.append(fieldSeparator).append(ad.solarSystem().getId());
			sb.append(fieldSeparator).append(ad.solarSystem().getName());
			sb.append(fieldSeparator).append(ad.solarSystem().getSecurityStatus());
			sb.append(fieldSeparator).append(ad.region().getId());
			sb.append(fieldSeparator).append(ad.region().getName());

			sb.append(lineSeparator);
		}
		return sb.toString();
	}

}
