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

import fr.guiguilechat.jcelechat.libs.spring.market.services.MarketOrderService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/market")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketHtmlController {

	@Lazy
	private final DogmaHtmlController dogmaHtmlController;

	private final MarketOrderService marketOrderService;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeService typeService;

	@Transactional
	@GetMapping("/{typeId}")
	public String getTypeMarket(Model model, @PathVariable int typeId) {
		Optional<Type> oType = typeService.byId(typeId);
		Map<Integer, String> regionNamesById = regionService.namesById();
		Map<Integer, String> stationNamesById = stationService.namesById();
		Map<Long, String> structuresNamesById = Map.of();
		if (oType.isPresent()) {
			Type type = oType.get();
			model.addAttribute("typeUrl", dogmaHtmlController.uri(type).toString());
			model.addAttribute("name", type.getName());
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
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getTypeMarket", null, "" + type.getTypeId()).build()
				.toUri();
	}

	public static record LinkedMarketType(String name, String url) {
	}

	public LinkedMarketType linkedMarketType(Type type) {
		return new LinkedMarketType(type.getName(), uri(type).toString());
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
				return "redirect:" + types.get(0).getTypeId();
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

}
