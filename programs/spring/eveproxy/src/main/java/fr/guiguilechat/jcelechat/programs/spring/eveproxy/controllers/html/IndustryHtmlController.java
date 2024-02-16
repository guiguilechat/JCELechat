package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.OfferLocation;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.BpService2;

@Controller
@RequestMapping("/html/industry")
public class IndustryHtmlController {


	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	private BpService2 bpService2;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private ProductService productService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private RegionLineService regionLineService;

	@Autowired
	private StationService stationService;

	@Autowired
	private TypeService typeService;

	public static record Seed(String space, String regionName, int regionId, String systemName, long locationId,
			double price) {

	}

	Seed of(OfferLocation ol) {
		Optional<Region> or = regionService.byId(ol.regionId());
		String space = or.isPresent() ? or.get().getUniverse() : "ø";
		String regionName = or.isPresent() ? or.get().getName() : "unknown region " + ol.regionId();
		Station os = stationService.findById((int) ol.locationId());
		String systemName = os != null ? os.getSolarSystem().getName() : "unknown locaiton " + ol.locationId();
		return new Seed(space, regionName, ol.regionId(), systemName, ol.locationId(), ol.bestPrice());
	}

	@GetMapping("/blueprint/{typeFiltering}/{typeFilter}")
	public String getBlueprint(Model model, @PathVariable String typeFiltering,
			@PathVariable String typeFilter) {
		Type t = typeService.typeFilter(typeFiltering, typeFilter);
		model.addAttribute("name", t != null ? t.getName() : "unknown bp " + typeFilter);
		if (t != null) {
			model.addAttribute("manufacturingProd", productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.manufacturing));
			model.addAttribute("manufacturingMats",
					materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.manufacturing));
			model.addAttribute("seeded", regionLineService.seedLocations(t.getTypeId()).stream().map(this::of).toList());
		}
		return "blueprint";
	}

}
