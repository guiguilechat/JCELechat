package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

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

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.industry.manufacturing.EivService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.Space;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.trade.ContractMarketAggregator;
import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingService;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.NpcHtmlController.LinkedLPOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.MarketHistoryRestController;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class InventoryHtmlController {

	private final CategoryService categoryService;

	private final EivService eivService;

	private final GroupService groupService;

	@Lazy
	private final MarketHistoryRestController historyRestController;

	private final LinkCorporationOfferService linkCorporationOfferService;

	@Lazy
	private final MarketHtmlController marketHtmlController;

	private final MarketLineService marketLineService;

	private final MarketRankingService marketRankingService;

	private final ContractMarketAggregator contractMarketAggregator;

	private final BlueprintMaterialService materialService;

	@Lazy
	private final NpcHtmlController npcHtmlController;

	private final PriceService priceService;

	private final BlueprintProductService productService;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeAttributeService typeAttributeService;

	private final TypeService typeService;

	public static record Seed(String space, String regionName, int regionId, String systemName, int systemId,
			String locationName, long locationId,
			double price) {

		public String formatedPrice() {
			return FormatTools.formatPrice(price);
		}

		public String location() {
			return locationName + " (" + locationId + ")";
		}

		public String region() {
			return regionName + " (" + regionId + ")";
		}

		public String system() {
			return systemName + " (" + systemId + ")";
		}

		public static Seed of(Region region, int regionId, SolarSystem solSys, String locationName, long locationId,
				double price) {
			return new Seed(
					Space.of(solSys == null ? -1.0f : solSys.getSecurityStatus().floatValue(),
							region == null ? null : region.getUniverse())
							.getName(),
					region == null ? null : region.name(),
					regionId,
					solSys == null ? "unknown system" : solSys.getName(),
					solSys == null ? -1 : solSys.getId(),
					locationName,
					locationId,
					price);
		}
	}

	List<Seed> seeds(List<LocatedBestOffer> offers) {
		List<Integer> seedStationIds = offers.stream().filter(s -> s.locationId() < Integer.MAX_VALUE)
				.mapToInt(s -> (int) s.locationId()).distinct().boxed().toList();
		Map<Integer, Station> stationId2Station = stationService.byId(seedStationIds).stream()
				.collect(Collectors.toMap((Function<? super Station, ? extends Integer>) Station::getId, s -> s));
		List<Integer> seedRegionIds = offers.stream().mapToInt(LocatedBestOffer::regionId).distinct().boxed().toList();
		Map<Integer, Region> regionId2Region = regionService.byId(seedRegionIds).stream()
				.collect(Collectors.toMap((Function<? super Region, ? extends Integer>) Region::getId, r -> r));
		Map<Long, SolarSystem> stationId2SolSys = stationService.getSolarSystems(seedStationIds);
		return offers.stream()
				.map(off -> {
					Region r = regionId2Region.get(off.regionId());
					SolarSystem solSys = stationId2SolSys.get(off.locationId());
					Station sta = off.locationId() < Integer.MAX_VALUE ? stationId2Station.get((int) off.locationId())
							: null;
					return Seed.of(r, off.regionId(), solSys, sta == null ? "unresolved" : sta.name(),
							off.locationId(),
							off.bestPrice());
				})
				.sorted(Comparator.comparing((Function<? super Seed, ? extends String>) Seed::space))
				.sorted(Comparator.comparing((Function<? super Seed, ? extends Double>) Seed::price))
				.toList();
	}

	public URI typeUri(int typeId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getType", null, typeId).build()
				.toUri();
	}

	public URI uri(Type type) {
		return typeUri(type.getId());
	}

	public static record LinkedProduct(String url, Type type, int quantity, BigDecimal probability) {
	}

	public LinkedProduct linkedProduct(BlueprintProduct product) {
		return new LinkedProduct(
				typeUri(product.getTypeId()).toString(),
				typeService.byId(product.getTypeId()),
				product.getQuantity(),
				product.getProbability());
	}

	public static record LinkedMaterial(String url, Type type, long quantity) {
	}

	public LinkedMaterial linkedMaterial(BlueprintMaterial material) {
		return new LinkedMaterial(
				typeUri(material.getTypeId()).toString(),
				typeService.byId(material.getTypeId()),
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

	public static record LinkedActivity(String url, Type type, ActivityType activity, int quantity,
			BigDecimal probability, BlueprintProduct product) {
	}

	public LinkedActivity linkedActivity(BlueprintProduct product) {
		return new LinkedActivity(
				typeUri(product.getActivity().getTypeId()).toString(),
				typeService.byId(product.getActivity().getTypeId()),
				product.getActivity().getActivityType(),
				product.getQuantity(),
				product.getProbability(),
				product);
	}

	public static record LinkedUsage(String url, Type type, int quantity) {
	}

	public LinkedUsage linkedUsage(BlueprintMaterial material) {
		return new LinkedUsage(
				typeUri(material.getActivity().getTypeId()).toString(),
				typeService.byId(material.getActivity().getTypeId()),
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
	@GetMapping("/type/{typeId}")
	public String getType(Model model,
			@PathVariable int typeId) {
		log.trace("fetching type");
		Type t = typeService.byId(typeId);
		if (t == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " does not exist");
		}
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
		model.addAttribute("variations", variations(t.getId()));

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

		log.trace("fetching manufacturingProd");
		List<LinkedProduct> manufProd = productService.findProducts(t.getId(), ActivityType.manufacturing).stream()
				.map(this::linkedProduct)
				.sorted(Comparator.comparing(u -> u.type().name()))
				.toList();
		model.addAttribute("manufacturingProd", manufProd);

		log.trace("fetching manufacturingMats");
		List<LinkedMaterial> manufMats = materialService.forBPActivity(t.getId(), ActivityType.manufacturing)
				.stream()
				.map(this::linkedMaterial)
				.sorted(Comparator.comparing(u -> u.type().name()))
				.toList();
		model.addAttribute("manufacturingMats", manufMats);

		log.trace("fetching reactionProd");
		model.addAttribute("reactionProd",
				productService.findProducts(t.getId(), ActivityType.reaction).stream()
						.map(this::linkedProduct)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching reactionMats");
		model.addAttribute("reactionMats",
				materialService.forBPActivity(t.getId(), ActivityType.reaction).stream()
						.map(this::linkedMaterial)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching seeded");
		List<LocatedBestOffer> seedOffers = marketLineService.seedLocations(t.getId());
		model.addAttribute("seeded", seeds(seedOffers));

		log.trace("fetching productOf");
		List<LinkedActivity> productOf = productService
				.findProducers(List.of(t.getId()), List.of(ActivityType.values())).stream()
				.map(this::linkedActivity)
				.sorted(Comparator.comparing(u -> u.type().name()))
				.toList();
		model.addAttribute("productOf", productOf);

		log.trace("fetching offers");
		List<LinkedLPOffer> offers = linkCorporationOfferService.producing(t).stream()
				.map(npcHtmlController::linkedLPOffer)
				.sorted(Comparator.comparing(LinkedLPOffer::name))
				.toList();
		model.addAttribute("offers", offers);

		log.trace("fetching manufacturingUses");
		model.addAttribute("manufacturingUses",
				materialService.findUsages(t.getId(), ActivityType.manufacturing).stream()
						.map(this::linkedUsage)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching reactionUses");
		model.addAttribute("reactionUses",
				materialService.findUsages(t.getId(), ActivityType.reaction).stream()
						.map(this::linkedUsage)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching adjusted");
		model.addAttribute("adjusted",
				FormatTools.formatPrice((long) priceService.adjusted(t.getId())));
		log.trace("fetching average");
		model.addAttribute("average", FormatTools.formatPrice(priceService.average(t.getId())));
		if (!manufProd.isEmpty()) {
			log.trace("fetching eiv");
			model.addAttribute("eiv", eivService.eivs().getOrDefault(t.getId(), 0L));
		}
		if (productOf.size() == 1) {
			log.trace("fetching bpeiv");
			model.addAttribute("bpeiv",
					eivService.eivs().getOrDefault(productOf.get(0).product().getActivity().getTypeId(), 0L));
		}

		log.trace("fetching jitabo");
		List<MarketLine> bos = marketLineService.forLocation(MarketLineService.JITAIV_ID, t.getId(), true);
		if (bos != null && !bos.isEmpty()) {
			model.addAttribute("jitabo", FormatTools.formatPrice(bos.get(0).getPrice()));
		}

		log.trace("fetching jitaso");
		List<MarketLine> jitaso = marketLineService.forLocation(MarketLineService.JITAIV_ID, t.getId(), false);
		if (jitaso != null && !jitaso.isEmpty()) {
			model.addAttribute("jitaso", FormatTools.formatPrice(jitaso.get(0).getPrice()));
		}

		Map<Integer, String> regionNamesById = regionService.namesById();
		log.trace("fetching regionSell");
		model.addAttribute("regionSell",
				contractMarketAggregator.lowestSellByRegion(t.getId())
						.entrySet().stream()
						.map(e -> RegionBestPrice.of(e, regionNamesById))
						.sorted(Comparator.comparing(RegionBestPrice::price))
						.toList());
		log.trace("fetching regionBuy");
		model.addAttribute("regionBuy",
				contractMarketAggregator.highestBuyByRegion(t.getId())
						.entrySet().stream()
						.map(e -> RegionBestPrice.of(e, regionNamesById))
						.sorted(Comparator.comparing(rp -> -rp.price()))
						.toList());
		return "inventory/type";
	}

	@Transactional
	@GetMapping("/type")
	public String getTypeQuery(Model model,
			int typeId) {
		return getType(model, typeId);
	}

	@Transactional
	@GetMapping("/type/search/{typeFiltering}/{typeFilter}")
	public String searchType(Model model, @PathVariable String typeFiltering,
			@PathVariable String typeFilter) {
		List<Type> types = typeService.typesFilter(typeFiltering, typeFilter);
		if (types.size() == 1) {
			return "redirect:" + uri(types.get(0)).toString();
		}
		model.addAttribute("types",
				types.stream().sorted(Comparator.comparing(Type::name)).map(this::linkedType).toList());
		return "inventory/typesearch";
	}

	@Transactional
	@GetMapping("/type/search/{typeFiltering}")
	public String searchTypeQuery(Model model, @PathVariable String typeFiltering,
			String filter) {
		return searchType(model, typeFiltering, filter);
	}

	@Transactional
	@GetMapping("/type/search")
	public String getTypesIndex(Model model) {
		model.addAttribute("categories", categoryService.allById().values().stream()
				.sorted(Comparator.comparing(Category::name))
				.map(this::linkedCategory)
				.toList());
		return "inventory/typesearch";
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

	public List<LinkedType> variations(int typeId) {
		List<Integer> variationIds = typeService.listVariationIds(typeId);
		if (variationIds == null || variationIds.size() < 2) {
			return null;
		}
		Map<Integer, Number> typeId2MetaLevel = typeAttributeService.valuesForTypes(633, variationIds);
		return typeService.byId(variationIds).stream()
				.map(this::linkedType)
				.sorted(Comparator.comparing(LinkedType::name))
				.sorted(Comparator.comparing(lt -> typeId2MetaLevel.getOrDefault(lt.type().getId(), 0).intValue()))
				.toList();
	}

	@Transactional
	@GetMapping("/group/{groupId}")
	public String getGroup(Model model, @PathVariable int groupId) {
		Group g = groupService.byId(groupId);
		if (g == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "group " + groupId + " does not exist");
		}
		marketRankingService.rankBuyOffers(60003760, groupId);
		marketRankingService.rankSellOffers(60003760, groupId);
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

		return "inventory/group";
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
		Category c = categoryService.byId(categoryId);
		if (c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category " + categoryId + " does not exist");
		}
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

		return "inventory/category";
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
