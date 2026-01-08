package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.inventory;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.MarketHtmlController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory/group")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class GroupHTMLController {

	private final GroupService groupService;

	@Lazy
	private final MarketHtmlController marketHtmlController;

	@Transactional
	@GetMapping("/{groupId}")
	public String getGroup(Model model, @PathVariable int groupId) {
		Group g = groupService.ofId(groupId);
		if (g == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "group " + groupId + " does not exist");
		}
		model.addAttribute("grp", g);
		model.addAttribute("category", g.getCategory());

		Group prvGrp = groupService.prevGroup(g);
		if (prvGrp != null) {
			model.addAttribute("prvGrp", prvGrp);
		}
		Group nxtGrp = groupService.nextGroup(g);
		if (nxtGrp != null) {
			model.addAttribute("nxtGrp", nxtGrp);
		}

		model.addAttribute("jitaRanking", marketHtmlController.linkedRanking(g, List.of(Station.JITA_HUB_ID)));

		return "inventory/group";
	}

	/** with group id as query param */
	@Transactional
	@GetMapping("/gi")
	public String getGroupQuery(Model model, int groupId) {
		return getGroup(model, groupId);
	}

	public String groupUrl(Group group) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getGroup", null, group.getId()).build()
				.toUri()
				.toString();
	}

}
