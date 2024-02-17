package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.OfferLocation;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.BpService2;

@Controller
@RequestMapping("/html/dogma")
public class DogmaHtmlController {

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	private BpService2 bpService2;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private GroupService groupService;

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

	public static record LinkedUsage(String url, Type type, int quantity) {
	}

	LinkedUsage linkedUsage(Material material) {
		return new LinkedUsage(
				uri(material.getActivity().getType()).toString(),
				material.getActivity().getType(),
				material.getQuantity());
	}

	@GetMapping("/type/{typeFiltering}/{typeFilter}")
	public String getType(Model model, @PathVariable String typeFiltering,
			@PathVariable String typeFilter) {
		Type t = typeService.typeFilter(typeFiltering, typeFilter);
		model.addAttribute("name", t != null ? t.getName() : "unknown type " + typeFilter);
		if (t != null) {
			model.addAttribute("group", t.getGroup());
			model.addAttribute("groupUrl", uri(t.getGroup()).toString());
			model.addAttribute("category", t.getGroup().getCategory());
			model.addAttribute("catUrl", uri(t.getGroup().getCategory()).toString());

			Type prvType = typeService.prevType(t);
			if (prvType != null) {
				model.addAttribute("prvType", prvType);
				model.addAttribute("prvTypeUrl", uri(prvType));
			}
			Type nxtType = typeService.nextType(t);
			if (nxtType != null) {
				model.addAttribute("nxtType", nxtType);
				model.addAttribute("nxtTypeUrl", uri(nxtType));
			}

			model.addAttribute("manufacturingProd",
					productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream()
							.map(this::linkedProduct)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("manufacturingMats",
					materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream()
							.map(this::linkedMaterial)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("reactionProd",
					productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.reaction).stream()
							.map(this::linkedProduct)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("reactionMats",
					materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.reaction).stream()
							.map(this::linkedMaterial)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("seeded",
					regionLineService.seedLocations(t.getTypeId()).stream()
							.map(this::seed)
							.toList());
			model.addAttribute("productOf",
					productService.findProducers(List.of(t.getTypeId()), List.of(ACTIVITY_TYPE.values())).stream()
							.map(this::linkedActivity)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("manufacturingUses",
					materialService.findUsages(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream()
							.map(this::linkedUsage)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
			model.addAttribute("reactionUses",
					materialService.findUsages(t.getTypeId(), ACTIVITY_TYPE.reaction).stream()
							.map(this::linkedUsage)
							.sorted(Comparator.comparing(u -> u.type().getName()))
							.toList());
		}
		return "dogma/type";
	}

	URI uri(Group group) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getGroup", null, group.getGroupId()).build()
				.toUri();
	}

	static record LinkedType(String url, Type type) {
	}

	LinkedType linkedType(Type type) {
		return new LinkedType(uri(type).toString(), type);
	}

	@GetMapping("/group/{groupId}")
	public String getGroup(Model model, @PathVariable int groupId) {
		Optional<Group> og = groupService.byId(groupId);
		if (og.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "group " + groupId + " does not exist");
		}
		Group g = og.get();
		model.addAttribute("grp", g);
		model.addAttribute("category", g.getCategory());
		model.addAttribute("catUrl", uri(g.getCategory()).toString());

		Group prvGrp = groupService.prevGroup(g);
		if (prvGrp != null) {
			model.addAttribute("prvGrp", prvGrp);
			model.addAttribute("prvGrpUrl", uri(prvGrp));
		}
		Group nxtGrp = groupService.nextGroup(g);
		if (nxtGrp != null) {
			model.addAttribute("nxtGrp", nxtGrp);
			model.addAttribute("nxtGrpUrl", uri(nxtGrp));
		}

		model.addAttribute("types", typeService.byGroupId(groupId).stream()
				.sorted(Comparator.comparing(Type::getName))
				.map(this::linkedType)
				.toList());
		return "dogma/group";

	}

	URI uri(Category category) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCategory", null,
				category.getCategoryId()).build()
				.toUri();
	}

	@GetMapping("/category/{categoryId}")
	public String getCategory(Model model, @PathVariable int categoryId) {
		return "dogma/category";

	}

}
