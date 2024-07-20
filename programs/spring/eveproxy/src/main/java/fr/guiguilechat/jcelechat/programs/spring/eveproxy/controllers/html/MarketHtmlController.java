package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.items.type.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.items.type.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrderService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.DogmaHtmlController.LinkedType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/market")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketHtmlController {

	@Lazy
	private final DogmaHtmlController dogmaHtmlController;

	private final MarketGroupService marketGroupService;

	private final MarketOrderService marketOrderService;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeService typeService;

	@Transactional
	@GetMapping("/{typeId}")
	public String getTypeMarket(Model model, @PathVariable int typeId) {
		Optional<Type> oType = typeService.findById(typeId);
		Map<Integer, String> regionNamesById = regionService.namesById();
		Map<Integer, String> stationNamesById = stationService.namesById();
		Map<Long, String> structuresNamesById = Map.of();
		if (oType.isPresent()) {
			Type type = oType.get();
			model.addAttribute("typeUrl", dogmaHtmlController.uri(type).toString());
			model.addAttribute("name", type.name());
		} else {
			model.addAttribute("name",  "unknown" + typeId);
		}
		model.addAttribute("sos",
						marketOrderService.sellOrders(typeId).stream()
						.peek(
								mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById, structuresNamesById))
						.toList());
		model.addAttribute("bos",
				marketOrderService.buyOrders(typeId).stream()
						.peek(
								mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById, structuresNamesById))
						.toList());
		return "market/type";
	}

	public URI uri(Type type) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getTypeMarket", null, "" + type.getId()).build()
				.toUri();
	}

	public static record LinkedMarketType(String name, String url) {
	}

	public LinkedMarketType linkedMarketType(Type type) {
		return new LinkedMarketType(type.name(), uri(type).toString());
	}


	@GetMapping("")
	public String getRoot() {
		return "redirect:market/search";
	}

	@GetMapping("/search")
	public String getSearch(Model model, Optional<String> typeName) {
		if (typeName.isPresent() && !typeName.get().isBlank()) {
			List<Type> types = typeService.search(typeName.get());
			if (types.size() == 1) {
				return "redirect:" + types.get(0).getId();
			} else {
				model.addAttribute("types",
						types.stream().map(this::linkedMarketType).sorted(Comparator.comparing(lmt -> lmt.name)).toList());
			}
		}
		return "market/index";
	}

	@GetMapping("/search/")
	public String getSearchSlash(Model model, Optional<String> typeName) {
		return getSearch(model, typeName);
	}

	// market groups

	@Transactional
	@GetMapping("/group/{marketGroupId}")
	public String getMarketGroup(Model model, @PathVariable int marketGroupId) {
		Optional<MarketGroup> oMarketGroup = marketGroupService.findById(marketGroupId);
		if (oMarketGroup.isPresent()) {
			MarketGroup marketGroup = oMarketGroup.get();
			model.addAttribute("name", marketGroup.name());
			if (marketGroup.getParent() != null) {
				model.addAttribute("parent", linkedMarketGroup(marketGroup.getParent()));
			}
			if (marketGroup.getSubGroups() != null && !marketGroup.getSubGroups().isEmpty()) {
				model.addAttribute("children", marketGroup.getSubGroups().stream()
				    .map(this::linkedMarketGroup)
				    .sorted(Comparator.comparing(l -> l.name))
				    .toList());
			}
			if (marketGroup.getTypes() != null && !marketGroup.getTypes().isEmpty()) {
				model.addAttribute("types", marketGroup.getTypes().stream()
				    .map(dogmaHtmlController::linkedType)
				    .sorted(Comparator.comparing(LinkedType::name))
				    .toList());
			}
		} else {
			model.addAttribute("name", "unknown" + marketGroupId);
		}
		return "market/group";
	}

	@Transactional
	@GetMapping("/groups")
	public String getMarketGroups(Model model) {
		model.addAttribute("roots",
		    marketGroupService.roots().stream()
		    .sorted(Comparator.comparing(MarketGroup::name))
		    .map(this::linkedMarketGroup)
		        .toList());
		return "market/groups";
	}

	public URI uri(MarketGroup marketGroup) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getMarketGroup", null, "" + marketGroup.getId()).build()
		    .toUri();
	}

	public static record LinkedMarketGroup(String name, String url) {
	}

	public LinkedMarketGroup linkedMarketGroup(MarketGroup marketGroup) {
		return new LinkedMarketGroup(marketGroup.name(), uri(marketGroup).toString());
	}

}
