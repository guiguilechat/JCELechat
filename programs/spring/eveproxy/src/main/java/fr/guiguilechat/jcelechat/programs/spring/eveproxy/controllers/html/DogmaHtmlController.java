package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionLine;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.prices.services.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.NpcHtmlController.LinkedLPOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.EivService;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/dogma")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DogmaHtmlController {

	private final CategoryService categoryService;

	private final CorporationOfferService corporationOfferService;

	private final EivService eivService;

	private final MaterialService materialService;

	private final GroupService groupService;

	@Lazy
	private final MarketHtmlController marketHtmlController;

	@Lazy
	private final NpcHtmlController npcHtmlController;

	private final PriceService priceService;

	private final ProductService productService;

	private final RegionService regionService;

	private final RegionLineService regionLineService;

	private final StationService stationService;

	private final TypeService typeService;

	public static record Seed(String space, String regionName, int regionId, String systemName, long locationId,
			double price) {

		public String formatedPrice() {
			return FormatTools.formatPrice(price);
		}
	}

	Seed seed(LocatedBestOffer ol) {
		Optional<Region> or = regionService.byId(ol.regionId());
		String space = or.isPresent() ? or.get().getUniverse() : "ø";
		String regionName = or.isPresent() ? or.get().getName() : "unknown region " + ol.regionId();
		Station os = stationService.findById((int) ol.locationId());
		String systemName = os != null ? os.getSolarSystem().getName() : "unknown location " + ol.locationId();
		return new Seed(space, regionName, ol.regionId(), systemName, ol.locationId(), ol.bestPrice());
	}

	public URI uri(Type type) {
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

	public static record LinkedMaterial(String url, Type type, long quantity) {
	}

	public LinkedMaterial linkedMaterial(Material material) {
		return new LinkedMaterial(
				uri(material.getType()).toString(),
				material.getType(),
				material.getQuantity());
	}

	public LinkedMaterial linkedMaterial(Type type, long quantity) {
		return new LinkedMaterial(
				uri(type).toString(),
				type,
				quantity);
	}

	public LinkedMaterial linkedMaterial(int typeId, long quantity) {
		return linkedMaterial(typeService.byId(typeId).orElse(null), quantity);
	}

	public static record LinkedActivity(String url, Type type, ACTIVITY_TYPE activity, int quantity, double probability,
			Product product) {
	}

	LinkedActivity linkedActivity(Product product) {
		return new LinkedActivity(
				uri(product.getActivity().getType()).toString(),
				product.getActivity().getType(),
				product.getActivity().getActivity(),
				product.getQuantity(),
				product.getProbability(),
				product);
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
		List<Type> types = typeService.typesFilter(typeFiltering, typeFilter);
		if (types.size() == 1) {
			Type t = types.get(0);
			model.addAttribute("name", t.getName());
			model.addAttribute("group", t.getGroup());
			model.addAttribute("groupUrl", uri(t.getGroup()).toString());
			model.addAttribute("category", t.getGroup().getCategory());
			model.addAttribute("catUrl", uri(t.getGroup().getCategory()).toString());
			model.addAttribute("marketUrl", marketHtmlController.uri(t).toString());

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

			List<LinkedProduct> manufProd = productService.findProducts(t.getTypeId(), ACTIVITY_TYPE.manufacturing).stream()
					.map(this::linkedProduct)
					.sorted(Comparator.comparing(u -> u.type().getName()))
					.toList();
			model.addAttribute("manufacturingProd", manufProd);
			List<LinkedMaterial> manufMats = materialService.forBPActivity(t.getTypeId(), ACTIVITY_TYPE.manufacturing)
					.stream()
					.map(this::linkedMaterial)
					.sorted(Comparator.comparing(u -> u.type().getName()))
					.toList();
			model.addAttribute("manufacturingMats", manufMats);
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
			List<LinkedActivity> productOf = productService
					.findProducers(List.of(t.getTypeId()), List.of(ACTIVITY_TYPE.values())).stream()
					.map(this::linkedActivity)
					.sorted(Comparator.comparing(u -> u.type().getName()))
					.toList();
			model.addAttribute("productOf", productOf);
			List<LinkedLPOffer> offers =
					corporationOfferService.producing(t).stream()
							.map(npcHtmlController::linkedLPOffer)
							.sorted(Comparator.comparing(lo -> lo.offer().name()))
							.toList();
			model.addAttribute("offers", offers);
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

			model.addAttribute("adjusted", priceService.adjusted().getOrDefault(t.getTypeId(), 0.0).longValue());
			model.addAttribute("average", FormatTools.formatPrice(priceService.average().getOrDefault(t.getTypeId(), 0.0)));
			if (!manufProd.isEmpty()) {
				model.addAttribute("eiv", (long) eivService.eiv(t.getTypeId()));
			}
			if (productOf.size() == 1) {
				model.addAttribute("bpeiv",
						(long) eivService.eiv(productOf.get(0).product().getActivity().getType().getTypeId()));
			}
			List<RegionLine> bos = regionLineService.forLocation(RegionLineService.JITAIV_ID, t.getTypeId(), true);
			if (bos != null && !bos.isEmpty()) {
				model.addAttribute("jitabo", FormatTools.formatPrice(bos.get(0).getPrice()));
			}
			List<RegionLine> sos = regionLineService.forLocation(RegionLineService.JITAIV_ID, t.getTypeId(), false);
			if (sos != null && !sos.isEmpty()) {
				model.addAttribute("jitaso", FormatTools.formatPrice(sos.get(0).getPrice()));
			}
		} else {
			model.addAttribute("name", "unknown type " + typeFilter);
			model.addAttribute("types", types.stream().map(this::linkedType).toList());
		}
		return "dogma/type";
	}

	@GetMapping("/type/{typeFiltering}")
	public String getTypeParam(Model model, @PathVariable String typeFiltering,
			String filter) {
		return getType(model, typeFiltering, filter);
	}

	@GetMapping("/types")
	public String getTypesIndex() {
		return "dogma/types";
	}

	public URI uri(Group group) {
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

	@GetMapping("/group/gi")
	public String getGroupById(Model model, int groupId) {
		return getGroup(model, groupId);
	}

	public URI uri(Category category) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCategory", null,
				category.getCategoryId()).build()
				.toUri();
	}

	static record LinkedGroup(String url, Group group) {
	}

	LinkedGroup linkedGroup(Group group) {
		return new LinkedGroup(uri(group).toString(), group);
	}

	@GetMapping("/category/{categoryId}")
	public String getCategory(Model model, @PathVariable int categoryId) {
		Optional<Category> oc = categoryService.byId(categoryId);
		if (oc.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category " + categoryId + " does not exist");
		}

		Category c = oc.get();
		model.addAttribute("cat", c);

		Category prvCat = categoryService.prevGroup(c);
		if (prvCat != null) {
			model.addAttribute("prvCat", prvCat);
			model.addAttribute("prvCatUrl", uri(prvCat));
		}
		Category nxtCat = categoryService.nextGroup(c);
		if (nxtCat != null) {
			model.addAttribute("nxtCat", nxtCat);
			model.addAttribute("nxtCatUrl", uri(nxtCat));
		}

		model.addAttribute("groups", groupService.byCatId(categoryId).stream()
				.sorted(Comparator.comparing(Group::getName))
				.map(this::linkedGroup)
				.toList());

		return "dogma/category";
	}

}
