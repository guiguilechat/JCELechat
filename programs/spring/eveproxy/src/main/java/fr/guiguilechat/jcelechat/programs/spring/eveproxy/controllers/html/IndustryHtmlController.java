package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.OfferLocation;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
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

	Seed seed(OfferLocation ol) {
		Optional<Region> or = regionService.byId(ol.regionId());
		String space = or.isPresent() ? or.get().getUniverse() : "ø";
		String regionName = or.isPresent() ? or.get().getName() : "unknown region " + ol.regionId();
		Station os = stationService.findById((int) ol.locationId());
		String systemName = os != null ? os.getSolarSystem().getName() : "unknown locaiton " + ol.locationId();
		return new Seed(space, regionName, ol.regionId(), systemName, ol.locationId(), ol.bestPrice());
	}

	URI uri(Type type) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getType", null, "ti", "" + type.getTypeId()).build()
				.toUri();
	}

	public static record LinkedProduct(String url, Type type, int quantity, double probability) {
	}

	LinkedProduct linkedProduct(Product product) {
		return new LinkedProduct(
				uri(product.getType()).toString(),
				product.getType(),
				product.getQuantity(),
				product.getProbability());
	}

	public static record LinkedMaterial(String url, Type type, int quantity) {
	}

	LinkedMaterial linkedMaterial(Material material) {
		return new LinkedMaterial(
				uri(material.getType()).toString(),
				material.getType(),
				material.getQuantity());
	}

	public static record LinkedActivity(String url, Type type, ACTIVITY_TYPE activity, int quantity, double probability) {
	}

	LinkedActivity linkedActivity(Product product) {
		return new LinkedActivity(
				uri(product.getActivity().getType()).toString(),
				product.getActivity().getType(),
				product.getActivity().getActivity(),
				product.getQuantity(),
				product.getProbability());
	}

	@GetMapping("/type/{typeFiltering}/{typeFilter}")
	public String getType(Model model, @PathVariable String typeFiltering,
			@PathVariable String typeFilter) {
		Type t = typeService.typeFilter(typeFiltering, typeFilter);
		model.addAttribute("name", t != null ? t.getName() : "unknown bp " + typeFilter);
		if (t != null) {
			model.addAttribute("manufacturingProd",
					productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream().map(this::linkedProduct)
							.toList());
			model.addAttribute("manufacturingMats",
					materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream().map(this::linkedMaterial)
							.toList());
			model.addAttribute("reactionProd",
					productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.reaction).stream().map(this::linkedProduct)
							.toList());
			model.addAttribute("reactionMats",
					materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.reaction).stream().map(this::linkedMaterial)
							.toList());
			model.addAttribute("seeded", regionLineService.seedLocations(t.getTypeId()).stream().map(this::seed).toList());
			model.addAttribute("productOf",
					productService.findProducers(List.of(t.getTypeId()), List.of(ACTIVITY_TYPE.values())).stream()
							.map(this::linkedActivity)
							.toList());
		}
		return "blueprint";
	}

}
