package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.market;

import java.util.Comparator;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/market/group")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketGroupHTMLController {

	private final MarketGroupService marketGroupService;

	@GetMapping("/{marketGroupId}")
	public String getMarketGroup(Model model, @PathVariable int marketGroupId) {
		MarketGroup marketGroup = marketGroupService.ofId(marketGroupId);
		if (marketGroup != null) {
			model.addAttribute("name", marketGroup.name());
			if (marketGroup.getParent() != null) {
				model.addAttribute("parent", marketGroup.getParent());
			}
			if (marketGroup.getSubGroups() != null && !marketGroup.getSubGroups().isEmpty()) {
				model.addAttribute("children", marketGroup.getSubGroups().stream()
						.sorted(Comparator.comparing(MarketGroup::getName))
						.toList());
			}
			if (marketGroup.getTypes() != null && !marketGroup.getTypes().isEmpty()) {
				model.addAttribute("types",
						marketGroup.getTypes().stream()
								.sorted(Comparator.comparing(Type::name))
								.toList());
			}
		} else {
			model.addAttribute("name", "unknown" + marketGroupId);
		}
		return "market/group";
	}

	public String marketGroupUrl(MarketGroup marketGroup) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getMarketGroup", null, "" + marketGroup.getId())
				.build()
				.toUri()
				.toString();
	}

	//

	@GetMapping("/list")
	public String getMarketGroups(Model model) {
		model.addAttribute("roots",
				marketGroupService.roots().stream()
						.sorted(Comparator.comparing(MarketGroup::name))
						.toList());
		return "market/groups";
	}

	public String rootUrl() {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getMarketGroups", (Model) null)
				.build()
				.toUri()
				.toString();

	}

}
