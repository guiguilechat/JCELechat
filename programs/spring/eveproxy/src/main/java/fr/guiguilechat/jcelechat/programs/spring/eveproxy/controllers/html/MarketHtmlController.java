package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.market.model.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionContractService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/market")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketHtmlController {

	private final RegionContractService regionContractService;

	private final RegionLineService regionLineService;

	private final TypeService typeService;

	@GetMapping("/{typeId}")
	public String getTypeMarket(Model model, @PathVariable int typeId) {
		Optional<Type> oType = typeService.byId(typeId);
		model.addAttribute("name", oType.isPresent() ? oType.get().getName() : "unknown" + typeId);
		model.addAttribute("sos",
				Stream.concat(regionContractService.streamSOs(typeId), regionLineService.streamSOs(typeId))
						.sorted(Comparator.comparing(MarketOrder::getPrice)).toList());
		model.addAttribute("bos",
				Stream.concat(regionContractService.streamBOs(typeId), regionLineService.streamBOs(typeId))
						.sorted(Comparator.comparing(mo -> -mo.getPrice())).toList());
		return "market/type";
	}

}
