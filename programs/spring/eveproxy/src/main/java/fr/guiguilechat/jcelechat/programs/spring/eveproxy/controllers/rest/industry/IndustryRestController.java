package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.industry;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Material;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Product;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.EivService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/industry")
@RequiredArgsConstructor
public class IndustryRestController {

	final private BlueprintActivityService blueprintActivityService;

	final private EivService eivService;

	private final GroupService groupService;

	final private MarketLineService marketLineService;

	final private MaterialService materialService;

	private final PriceService priceService;

	final private ProductService productService;

	final private TypeService typeService;

	public static record BPInfo(double eiv, double ptv1020, long researchSeconds, String researchHuman,
			Map<ActivityType, List<Integer>> produces) {

		public BPInfo(double eiv, long researchTime, Map<ActivityType, List<Integer>> produces) {
			this(eiv, eiv * 256000 / 105 / 50 * 2, researchTime, secondsToDuration(researchTime), produces);
		}

		static String secondsToDuration(long seconds) {
			StringBuilder sb = new StringBuilder();
			if (seconds > 3600 * 24) {
				int days = (int) (seconds / (3600 * 24));
				seconds = seconds % (3600 * 24);
				sb.append(days).append("d ");
			}
			if (seconds > 3600) {
				int hours = (int) (seconds / 3600);
				seconds = seconds % 3600;
				sb.append(hours).append("h ");
			}
			if (seconds > 60) {
				int minutes = (int) (seconds / 60);
				seconds = seconds % 60;
				sb.append(minutes).append("m ");
			}
			sb.append(seconds).append("s");
			return sb.toString();
		}

	}

	public static record IndustryInfo(int typeId, String name, float basePrice, BPInfo bp,
			Map<ActivityType, List<Integer>> productOf, Map<Integer, Map<Long, Double>> seedRegionLocPrice) {

		IndustryInfo(Type type, BPInfo bp,
				Map<ActivityType, List<Integer>> productOf,
				Map<Integer, Map<Long, Double>> rid2lid2price) {
			this(type.getId(), type.name(), type.getBasePrice().floatValue(),
					bp, productOf,
					rid2lid2price);
		}

	}

	@Transactional
	@GetMapping("/{typeFiltering}/{typeFilter}")
	public ResponseEntity<List<IndustryInfo>> showInformation(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@RequestParam Optional<ACCEPT_TEXT> accept) throws IOException {
		List<IndustryInfo> ret = typeService.typesFilter(typeFiltering, typeFilter)
				.stream().map(type -> {
			    List<LocatedBestOffer> seeds = type.getMarketGroup() != null ? marketLineService.seedLocations(type.getId())
							: Collections.emptyList();
					Map<Integer, Map<Long, Double>> seedMap = new HashMap<>();
			    for (LocatedBestOffer s : seeds) {
						Map<Long, Double> rmap = seedMap.computeIfAbsent(s.regionId(), _ -> new HashMap<>());
						rmap.put(s.locationId(), s.bestPrice());
					}
			    List<BlueprintActivity> mes = blueprintActivityService.forBPActivity(type.getId(),
							ActivityType.researchMat);
					long meTime = mes.size() != 1 ? 0 : 256000L * mes.get(0).getTime() / 105;
			    List<BlueprintActivity> tes = blueprintActivityService.forBPActivity(type.getId(),
							ActivityType.researchTime);

			    BPInfo bp = null;
			    if (type.getGroup().getCategory().getId() == 9) {
				    double eiv = eivService.eiv(type.getId());
						long teTime = tes.size() != 1 ? 0 : 256000L * tes.get(0).getTime() / 105;
						long researchTime = teTime + meTime;

						LinkedHashMap<ActivityType, List<Integer>> produces = new LinkedHashMap<>();
						for (ActivityType act : ActivityType.values()) {
					    List<Product> prods = productService.findProducts(type.getId(), act);
							if (!prods.isEmpty()) {
						    produces.put(act, prods.stream().map(p -> p.getType().getId()).toList());
					    }
						}
						bp = new BPInfo(eiv, researchTime, produces);
					}

					LinkedHashMap<ActivityType, List<Integer>> productOf = new LinkedHashMap<>();
					for (ActivityType act : ActivityType.values()) {
				    List<Product> prods = productService.findProducers(type.getId(), act);
						if (!prods.isEmpty()) {
					    productOf.put(act, prods.stream().map(p -> p.getActivity().getType().getId()).toList());
						}
					}

					return new IndustryInfo(type, bp, productOf, seedMap);
				}).toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

	public static record ConsumedMat(int typeId, String typeName, int quantity) {
		public ConsumedMat(Material mat) {
			this(mat.getType().getId(), mat.getType().name(), mat.getQuantity());
		}
	}

	public static record ProducedItem(int typeId, String typeName, int quantity, BigDecimal probability) {
		public ProducedItem(Product prod) {
			this(prod.getType().getId(), prod.getType().name(), prod.getQuantity(), prod.getProbability());
		}
	}

	public static record ActivityInfo(int typeId, String name, ActivityType activity,
			List<ConsumedMat> consumes, List<ProducedItem> produces) {

		public ActivityInfo(Type type, ActivityType activity, List<Material> mats, List<Product> prods) {
			this(type.getId(), type.name(), activity,
					mats.stream().map(ConsumedMat::new).toList(),
					prods.stream().map(ProducedItem::new).toList());
		}
	}

	@Transactional
	@GetMapping("/{typeFiltering}/{typeFilter}/{activity}")
	public ResponseEntity<List<ActivityInfo>> showActivity(
			@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@PathVariable ActivityType activity,
			Optional<ACCEPT_TEXT> accept) throws IOException {
		List<ActivityInfo> ret = typeService.typesFilter(typeFiltering, typeFilter).stream()
				.map(t -> new ActivityInfo(t, activity,
		        materialService.forBPActivity(t.getId(), activity),
		        productService.findProducts(t.getId(), activity)))
				.toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

	public static record BpValue(int productId, int blueprintId, long eiv, BigDecimal adjusted, BigDecimal average) {

		public static BpValue of(Type bpType, Type productType, Long eiv, Double adjusted, Double average) {
			return new BpValue(
					productType.getId(),
					bpType.getId(),
					eiv == null ? 0L : eiv,
					new BigDecimal(adjusted == null ? 0.0 : adjusted).setScale(5, RoundingMode.FLOOR)
							.stripTrailingZeros(),
					new BigDecimal(average == null ? 0.0 : average).setScale(5, RoundingMode.FLOOR)
							.stripTrailingZeros());
		}

	}

	public static record EivsResult(Map<String, String> query, List<BpValue> eivs) {

	}

	@Transactional
	@GetMapping("/eivs")
	public ResponseEntity<EivsResult> showEivs(
			@RequestParam @Parameter(description = "optional list of filters for product group name") Optional<List<String>> pgn,
			@RequestParam @Parameter(description = "optional list of filters for product group id") Optional<List<Integer>> pgi,
	    @RequestParam Optional<Boolean> published,
	    @RequestParam Optional<Boolean> marketable,
			Optional<ACCEPT_TEXT> accept) throws IOException {

		long start = System.currentTimeMillis();
		Map<String, String> query = new LinkedHashMap<>();
		query.put("receivedAt",
		    DateTimeFormatter.ISO_DATE_TIME.format(Instant.now().atZone(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS)));

		Boolean publishedValue = published == null ? null : published.orElse(null);
		query.put("published", String.valueOf(publishedValue));

		Boolean marketableValue = marketable == null ? null : marketable.orElse(null);
		query.put("marketable", String.valueOf(marketableValue));

		Set<Integer> gidFilters = null;
		if (pgn != null && pgn.isPresent()) {
			gidFilters = new HashSet<>(
					groupService.byNameInIgnoreCase(pgn.get()).stream().map(Group::getId).toList());
			query.put("pgn", pgn.get().toString());
		}
		if (pgi != null && pgi.isPresent()) {
			if (gidFilters == null) {
				gidFilters = new HashSet<>();
			}
			gidFilters.addAll(pgi.get());
			query.put("pgi", pgi.get().toString());
		}

		Set<Integer> tidFilters = null;
		if (gidFilters != null) {
			tidFilters = new HashSet<>(typeService.byGroupIdIn(gidFilters).stream().map(Type::getId).toList());
		}

		Map<Integer, Product> acceptedBpIdToProduct = (tidFilters == null
				? productService.findProducts(List.of(ActivityType.manufacturing))
				: productService.findProducers(tidFilters, List.of(ActivityType.manufacturing)))
		    .stream().collect(Collectors.toMap(p -> p.getActivity().getType().getId(), p -> p));

		if (publishedValue != null) {
			acceptedBpIdToProduct.entrySet().removeIf(e->e.getValue().getType().isPublished()!=publishedValue );
		}
		if (marketableValue != null) {
			acceptedBpIdToProduct.entrySet()
			    .removeIf(e -> e.getValue().getType().getMarketGroup() != null != marketableValue);
		}

		Map<Integer, Long> eivs = eivService.eivs(acceptedBpIdToProduct.keySet());
		Map<Integer, Double> adjusteds = priceService.adjusted();
		Map<Integer, Double> averages = priceService.average();

		List<BpValue> bpEivs = new ArrayList<>();
		for (Entry<Integer, Product> e : acceptedBpIdToProduct.entrySet()) {
			Product p = e.getValue();
			if (p == null) {
				System.err.println(" bp id=" + e.getKey() + " null product");
				continue;
			}
			Type productType = null;
			Type bpType = null;
			Long eiv = eivs.get(e.getKey());
			Double adjusted = null;
			Double average = null;
			if (p != null) {
				adjusted = adjusteds.get(p.getType().getId());
				average = averages.get(p.getType().getId());
				productType = p.getType();
				bpType = p.getActivity().getType();
			}
			if (productType != null && bpType != null) {
				bpEivs.add(BpValue.of(bpType, productType, eiv, adjusted, average));
			}
		}
		query.put("responseSize", String.valueOf(bpEivs.size()));
		bpEivs.sort(Comparator.comparing(BpValue::blueprintId));

		query.put("timeMs", String.valueOf(System.currentTimeMillis() - start));
		return RestControllerHelper.makeResponse(new EivsResult(query, bpEivs), accept);
	}

}
