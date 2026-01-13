package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.inventory;

import java.math.BigDecimal;
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
import fr.guiguilechat.jcelechat.libs.spring.anon.industry.manufacturing.EivService;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.ContractMarketAggregator;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStatsService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillsAggregation;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.product.BlueprintProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.Space;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.loyalty.LoyaltyHTMLController;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.loyalty.LoyaltyHTMLController.LinkedLPOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.market.MarketHTMLController;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.MarketHistoryRestController;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.MerKillsRestController;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory/type")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class TypeHTMLController {

	private final BlueprintMaterialService blueprintMaterialService;

	private final BlueprintProductService blueprintProductService;

	@Lazy
	private final CategoryHTMLController categoryHTMLController;

	private final ContractMarketAggregator contractMarketAggregator;

	private final EivService eivService;

	@Lazy
	private final GroupHTMLController groupHTMLController;

	@Lazy
	private final MarketHistoryRestController historyRestController;

	private final KillStatsService killStatsService;

	private final LinkCorporationOfferService linkCorporationOfferService;

	@Lazy
	private final MarketHTMLController marketHtmlController;

	private final MarketLineService marketLineService;

	private final MerKillsRestController merKillsRestController;

	@Lazy
	private final LoyaltyHTMLController npcHtmlController;

	private final PriceService priceService;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeService typeService;

	@GetMapping("/{typeId}")
	public String getType(Model model,
			@PathVariable int typeId) {
		log.trace("fetching type");
		Type t = typeService.ofId(typeId);
		if (t == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " does not exist");
		}
		model.addAttribute("name", t.name());
		if (killStatsService.hasTypeStats(typeId)) {
			model.addAttribute("killsUrl", merKillsRestController.chartUri(
					typeId,
					KillsAggregation.MONTHLY,
					null).toString());
		}
		if (t.getMarketGroup() != null) {
			t.getMarketGroup().getName();
			model.addAttribute("marketGroup", t.getMarketGroup());
		}
		Group group = t.getGroup();
		if (group != null) {
			// can be null if type not fetched yet
			model.addAttribute("group", group);
			model.addAttribute("groupUrl", groupHTMLController.groupUrl(group));
			Category cat = group.getCategory();
			if (cat != null) {
				model.addAttribute("category", cat);
				model.addAttribute("catUrl", categoryHTMLController.categoryUrl(cat));
			}
		}

		Type prvType = typeService.prevType(t);
		if (prvType != null) {
			model.addAttribute("prvType", prvType);
		}
		Type nxtType = typeService.nextType(t);
		if (nxtType != null) {
			model.addAttribute("nxtType", nxtType);
		}

		log.trace("fetching manufacturingProd");
		List<LinkedProduct> manufProd = blueprintProductService.findProducts(t.getId(), ActivityType.manufacturing).stream()
				.map(this::linkedProduct)
				.sorted(Comparator.comparing(u -> u.type().name()))
				.toList();
		model.addAttribute("manufacturingProd", manufProd);

		log.trace("fetching manufacturingMats");
		List<LinkedMaterial> manufMats = blueprintMaterialService.forBPActivity(t.getId(), ActivityType.manufacturing)
				.stream()
				.map(this::linkedMaterial)
				.sorted(Comparator.comparing(u -> u.type().name()))
				.toList();
		model.addAttribute("manufacturingMats", manufMats);

		log.trace("fetching reactionProd");
		model.addAttribute("reactionProd",
				blueprintProductService.findProducts(t.getId(), ActivityType.reaction).stream()
						.map(this::linkedProduct)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching reactionMats");
		model.addAttribute("reactionMats",
				blueprintMaterialService.forBPActivity(t.getId(), ActivityType.reaction).stream()
						.map(this::linkedMaterial)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching seeded");
		List<LocatedBestOffer> seedOffers = marketLineService.seedLocations(t.getId());
		model.addAttribute("seeded", seeds(seedOffers));

		log.trace("fetching productOf");
		List<LinkedActivity> productOf = blueprintProductService
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
				blueprintMaterialService.findUsages(t.getId(), ActivityType.manufacturing).stream()
						.map(this::linkedUsage)
						.sorted(Comparator.comparing(u -> u.type().name()))
						.toList());

		log.trace("fetching reactionUses");
		model.addAttribute("reactionUses",
				blueprintMaterialService.findUsages(t.getId(), ActivityType.reaction).stream()
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

		if (t.isPublished() && t.getMarketGroup() != null) {
			model.addAttribute("marketUrl", marketHtmlController.uri(t).toString());
			model.addAttribute("historyUrl", historyRestController.uri(t).toString());

			log.trace("fetching jitabo");
			List<MarketLine> bos = marketLineService.forLocation(Station.JITA_HUB_ID, t.getId(), true);
			if (bos != null && !bos.isEmpty()) {
				model.addAttribute("jitabo", FormatTools.formatPrice(bos.get(0).getPrice()));
			}

			log.trace("fetching jitaso");
			List<MarketLine> jitaso = marketLineService.forLocation(Station.JITA_HUB_ID, t.getId(), false);
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
		}
		return "inventory/type";
	}

	public static record LinkedProduct(String url, Type type, int quantity, BigDecimal probability) {
	}

	public LinkedProduct linkedProduct(BlueprintProduct product) {
		return new LinkedProduct(
				typeUrl(product.getTypeId()),
				typeService.ofId(product.getTypeId()),
				product.getQuantity(),
				product.getProbability());
	}

	public static record LinkedMaterial(String url, Type type, long quantity) {
	}

	public LinkedMaterial linkedMaterial(BlueprintMaterial material) {
		return new LinkedMaterial(
				typeUrl(material.getTypeId()),
				typeService.ofId(material.getTypeId()),
				material.getQuantity());
	}

	public LinkedMaterial linkedMaterial(Type type, long quantity) {
		return new LinkedMaterial(
				typeUrl(type).toString(),
				type,
				quantity);
	}

	public LinkedMaterial linkedMaterial(int typeId, long quantity) {
		return linkedMaterial(typeService.ofId(typeId), quantity);
	}

	public static record LinkedActivity(String url, Type type, ActivityType activity, int quantity,
			BigDecimal probability, BlueprintProduct product) {
	}

	public LinkedActivity linkedActivity(BlueprintProduct product) {
		return new LinkedActivity(
				typeUrl(product.getActivity().getTypeId()),
				typeService.ofId(product.getActivity().getTypeId()),
				product.getActivity().getActivityType(),
				product.getQuantity(),
				product.getProbability(),
				product);
	}

	public static record LinkedUsage(String url, Type type, int quantity) {
	}

	public LinkedUsage linkedUsage(BlueprintMaterial material) {
		return new LinkedUsage(
				typeUrl(material.getActivity().getTypeId()),
				typeService.ofId(material.getActivity().getTypeId()),
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
		Map<Integer, Station> stationId2Station = stationService.ofId(seedStationIds).stream()
				.collect(Collectors.toMap((Function<? super Station, ? extends Integer>) Station::getId, s -> s));
		List<Integer> seedRegionIds = offers.stream().mapToInt(LocatedBestOffer::regionId).distinct().boxed().toList();
		Map<Integer, Region> regionId2Region = regionService.ofId(seedRegionIds).stream()
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

	@GetMapping("")
	public String getTypeQuery(
			Model model,
			int typeId) {
		return getType(model, typeId);
	}

	public String typeUrl(int typeId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getType", null, typeId).build()
				.toUri()
				.toString();
	}

	public String typeUrl(Type type) {
		return typeUrl(type.getId());
	}

}
