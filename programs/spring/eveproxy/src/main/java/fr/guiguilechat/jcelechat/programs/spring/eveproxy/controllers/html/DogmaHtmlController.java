package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

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

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Material;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Product;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Category;
import fr.guiguilechat.jcelechat.libs.spring.items.type.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Group;
import fr.guiguilechat.jcelechat.libs.spring.items.type.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.trade.orders.MarketOrderService;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.NpcHtmlController.LinkedLPOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.HistoryRestController;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.EivService;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/dogma")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DogmaHtmlController {

	private final CategoryService categoryService;

	private final LinkCorporationOfferService linkCorporationOfferService;

	private final EivService eivService;

	private final MaterialService materialService;

	private final GroupService groupService;

	@Lazy
	private final MarketHtmlController marketHtmlController;

	@Lazy
	private final HistoryRestController historyRestController;

	private final MarketOrderService marketOrderService;

	@Lazy
	private final NpcHtmlController npcHtmlController;

	private final PriceService priceService;

	private final ProductService productService;

	private final RegionService regionService;

	private final MarketLineService marketLineService;

	private final StationService stationService;

	private final TypeService typeService;

	public static record Seed(String space, String regionName, int regionId, String systemName, long locationId,
	    double price) {

		public String formatedPrice() {
			return FormatTools.formatPrice(price);
		}
	}

	Seed seed(LocatedBestOffer ol) {
		Optional<Region> or = regionService.findById(ol.regionId());
		String space = or.isPresent() ? or.get().getUniverse() : "ø";
		String regionName = or.isPresent() ? or.get().getName() : "unknown region " + ol.regionId();
		Station os = stationService.byId((int) ol.locationId());
		String systemName = os != null ? os.getSolarSystem().getName() : "unknown location " + ol.locationId();
		return new Seed(space, regionName, ol.regionId(), systemName, ol.locationId(), ol.bestPrice());
	}

	public URI uri(Type type) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getType", null, "ti", "" + type.getId()).build()
		    .toUri();
	}

	public static record LinkedProduct(String url, Type type, int quantity, double probability) {
	}

	public LinkedProduct linkedProduct(Product product) {
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
		return linkedMaterial(typeService.byId(typeId), quantity);
	}

	public static record LinkedActivity(String url, Type type, ACTIVITY_TYPE activity, int quantity, double probability,
	    Product product) {
	}

	public LinkedActivity linkedActivity(Product product) {
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

	public LinkedUsage linkedUsage(Material material) {
		return new LinkedUsage(
		    uri(material.getActivity().getType()).toString(),
		    material.getActivity().getType(),
		    material.getQuantity());
	}

	public static record RegionBestPrice(String regionName, double price) {

		public String getFormatedPrice() {
			return FormatTools.formatPrice(price);
		}

		public static RegionBestPrice of(int regionId, double value, Map<Integer, String> regionNamesById) {
			return new RegionBestPrice(regionNamesById.get(regionId), value);
		}

		public static RegionBestPrice of(Entry<Integer, Double> entry, Map<Integer, String> regionNamesById) {
			return of(entry.getKey(), entry.getValue(), regionNamesById);
		}
	}

	@Transactional
	@GetMapping("/type/{typeFiltering}/{typeFilter}")
	public String getType(Model model, @PathVariable String typeFiltering,
	    @PathVariable String typeFilter) {
		List<Type> types = typeService.typesFilter(typeFiltering, typeFilter);
		if (types.size() == 1) {
			Type t = types.get(0);
			model.addAttribute("name", t.name());
			model.addAttribute("marketUrl", marketHtmlController.uri(t).toString());
			model.addAttribute("historyUrl", historyRestController.uri(t).toString());
			if (t.getMarketGroup() != null) {
				model.addAttribute("marketGroup", marketHtmlController.linkedMarketGroup(t.getMarketGroup()));
			}
			Group group = t.getGroup();
			if (group != null) {
				// can be null if type not fetched yet
				model.addAttribute("group", group);
				model.addAttribute("groupUrl", uri(group).toString());
				Category cat = group.getCategory();
				if (cat != null) {
					model.addAttribute("category", cat);
					model.addAttribute("catUrl", uri(cat).toString());
				}
			}

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

			List<LinkedProduct> manufProd = productService.findProducts(t.getId(), ACTIVITY_TYPE.manufacturing).stream()
			    .map(this::linkedProduct)
			    .sorted(Comparator.comparing(u -> u.type().name()))
			    .toList();
			model.addAttribute("manufacturingProd", manufProd);

			List<LinkedMaterial> manufMats = materialService.forBPActivity(t.getId(), ACTIVITY_TYPE.manufacturing)
			    .stream()
			    .map(this::linkedMaterial)
			    .sorted(Comparator.comparing(u -> u.type().name()))
			    .toList();
			model.addAttribute("manufacturingMats", manufMats);

			model.addAttribute("reactionProd",
			    productService.findProducts(t.getId(), ACTIVITY_TYPE.reaction).stream()
			        .map(this::linkedProduct)
			        .sorted(Comparator.comparing(u -> u.type().name()))
			        .toList());

			model.addAttribute("reactionMats",
			    materialService.forBPActivity(t.getId(), ACTIVITY_TYPE.reaction).stream()
			        .map(this::linkedMaterial)
			        .sorted(Comparator.comparing(u -> u.type().name()))
			        .toList());

			model.addAttribute("seeded",
			    marketLineService.seedLocations(t.getId()).stream()
			        .map(this::seed)
			        .toList());

			List<LinkedActivity> productOf = productService
			    .findProducers(List.of(t.getId()), List.of(ACTIVITY_TYPE.values())).stream()
			    .map(this::linkedActivity)
			    .sorted(Comparator.comparing(u -> u.type().name()))
			    .toList();
			model.addAttribute("productOf", productOf);

			List<LinkedLPOffer> offers = linkCorporationOfferService.producing(t).stream()
			    .map(npcHtmlController::linkedLPOffer)
			    .sorted(Comparator.comparing(LinkedLPOffer::name))
			    .toList();
			model.addAttribute("offers", offers);

			model.addAttribute("manufacturingUses",
			    materialService.findUsages(t.getId(), ACTIVITY_TYPE.manufacturing).stream()
			        .map(this::linkedUsage)
			        .sorted(Comparator.comparing(u -> u.type().name()))
			        .toList());

			model.addAttribute("reactionUses",
			    materialService.findUsages(t.getId(), ACTIVITY_TYPE.reaction).stream()
			        .map(this::linkedUsage)
			        .sorted(Comparator.comparing(u -> u.type().name()))
			        .toList());

			model.addAttribute("adjusted",
			    FormatTools.formatPrice(priceService.adjusted().getOrDefault(t.getId(), 0.0).longValue()));
			model.addAttribute("average", FormatTools.formatPrice(priceService.average().getOrDefault(t.getId(), 0.0)));
			if (!manufProd.isEmpty()) {
				model.addAttribute("eiv", eivService.eiv(t.getId()));
			}
			if (productOf.size() == 1) {
				model.addAttribute("bpeiv",
				    eivService.eiv(productOf.get(0).product().getActivity().getType().getId()));
			}
			List<MarketLine> bos = marketLineService.forLocation(MarketLineService.JITAIV_ID, t.getId(), true);
			if (bos != null && !bos.isEmpty()) {
				model.addAttribute("jitabo", FormatTools.formatPrice(bos.get(0).getPrice()));
			}
			List<MarketLine> sos = marketLineService.forLocation(MarketLineService.JITAIV_ID, t.getId(), false);
			if (sos != null && !sos.isEmpty()) {
				model.addAttribute("jitaso", FormatTools.formatPrice(sos.get(0).getPrice()));
			}

			Map<Integer, String> regionNamesById = regionService.namesById();
			model.addAttribute("regionSell",
			    marketOrderService.lowestSellByRegion(t.getId())
			        .entrySet().stream()
			        .map(e -> RegionBestPrice.of(e, regionNamesById))
			        .sorted(Comparator.comparing(RegionBestPrice::price))
			        .toList());
			model.addAttribute("regionBuy",
			    marketOrderService.highestBuyByRegion(t.getId())
			        .entrySet().stream()
			        .map(e -> RegionBestPrice.of(e, regionNamesById))
			        .sorted(Comparator.comparing(rp -> -rp.price()))
			        .toList());
		} else {
				model.addAttribute("name", "unknown type " + typeFilter);
				model.addAttribute("types",
				    types.stream().sorted(Comparator.comparing(Type::name)).map(this::linkedType).toList());
		}
		return "dogma/type";
	}

	@Transactional
	@GetMapping("/type/{typeFiltering}")
	public String getTypeParam(Model model, @PathVariable String typeFiltering,
	    String filter) {
		return getType(model, typeFiltering, filter);
	}

	@Transactional
	@GetMapping("/types")
	public String getTypesIndex(Model model) {
		model.addAttribute("categories", categoryService.allById().values().stream()
		    .sorted(Comparator.comparing(Category::name))
				.map(this::linkedCategory)
				.toList());
		return "dogma/types";
	}

	public URI uri(Group group) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getGroup", null, group.getId()).build()
		    .toUri();
	}

	public static record LinkedType(String url, Type type, String name) {
	}

	public LinkedType linkedType(Type type) {
		return new LinkedType(uri(type).toString(), type, type.name());
	}

	@Transactional
	@GetMapping("/group/{groupId}")
	public String getGroup(Model model, @PathVariable int groupId) {
		Optional<Group> og = groupService.findById(groupId);
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
		    .sorted(Comparator.comparing(Type::name))
		    .map(this::linkedType)
		    .toList());

		return "dogma/group";
	}

	@Transactional
	@GetMapping("/group/gi")
	public String getGroupById(Model model, int groupId) {
		return getGroup(model, groupId);
	}

	static record LinkedGroup(String url, Group group) {
	}

	public LinkedGroup linkedGroup(Group group) {
		return new LinkedGroup(uri(group).toString(), group);
	}

	@Transactional
	@GetMapping("/category/{categoryId}")
	public String getCategory(Model model, @PathVariable int categoryId) {
		Optional<Category> oc = categoryService.findById(categoryId);
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

	public URI uri(Category category) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCategory", null,
		    category.getId()).build()
		    .toUri();
	}

	static record LinkedCategory(String url, Category category) {
	}

	public LinkedCategory linkedCategory(Category category) {
		return new LinkedCategory(uri(category).toString(), category);
	}

}
