package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Material;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Product;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.EivService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/industry")
@RequiredArgsConstructor
public class IndustryRestController {

	final private BlueprintActivityService blueprintActivityService;

	final private EivService eivService;

	final private MaterialService materialService;

	final private ProductService productService;

	final private TypeService typeService;

	final private MarketLineService marketLineService;

	public static record BPInfo(double eiv, double ptv1020, long researchSeconds, String researchHuman,
			Map<ACTIVITY_TYPE, List<Integer>> produces) {

		public BPInfo(double eiv, long researchTime, Map<ACTIVITY_TYPE, List<Integer>> produces) {
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
			Map<ACTIVITY_TYPE, List<Integer>> productOf, Map<Integer, Map<Long, Double>> seedRegionLocPrice) {

		IndustryInfo(Type type, BPInfo bp,
				Map<ACTIVITY_TYPE, List<Integer>> productOf,
				Map<Integer, Map<Long, Double>> rid2lid2price) {
			this(type.getId(), type.getName(), type.getBasePrice(),
					bp, productOf,
					rid2lid2price);
		}

	}

	@GetMapping("/{typeFiltering}/{typeFilter}")
	public ResponseEntity<List<IndustryInfo>> showInformation(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@RequestParam Optional<String> accept) throws IOException {
		List<IndustryInfo> ret = typeService.typesFilter(typeFiltering, typeFilter)
				.stream().map(type -> {
			    List<LocatedBestOffer> seeds = type.getMarketGroupId() > 0 ? marketLineService.seedLocations(type.getId())
							: Collections.emptyList();
					Map<Integer, Map<Long, Double>> seedMap = new HashMap<>();
			    for (LocatedBestOffer s : seeds) {
						Map<Long, Double> rmap = seedMap.computeIfAbsent(s.regionId(), rid -> new HashMap<>());
						rmap.put(s.locationId(), s.bestPrice());
					}
			    List<BlueprintActivity> mes = blueprintActivityService.forBPActivity(type.getId(),
							ACTIVITY_TYPE.researchMat);
					long meTime = mes.size() != 1 ? 0 : 256000l * mes.get(0).getTime() / 105;
			    List<BlueprintActivity> tes = blueprintActivityService.forBPActivity(type.getId(),
							ACTIVITY_TYPE.researchTime);

			    BPInfo bp = null;
			    if (type.getGroup().getCategory().getId() == 9) {
				    double eiv = eivService.eiv(type.getId());
						long teTime = tes.size() != 1 ? 0 : 256000l * tes.get(0).getTime() / 105;
						long researchTime = teTime + meTime;

						LinkedHashMap<ACTIVITY_TYPE, List<Integer>> produces = new LinkedHashMap<>();
						for (ACTIVITY_TYPE act : ACTIVITY_TYPE.values()) {
					    List<Product> prods = productService.findProducts(type.getId(), act);
							if (!prods.isEmpty()) {
						    produces.put(act, prods.stream().map(p -> p.getType().getId()).toList());
					    }
						}
						bp = new BPInfo(eiv, researchTime, produces);
					}

					LinkedHashMap<ACTIVITY_TYPE, List<Integer>> productOf = new LinkedHashMap<>();
					for (ACTIVITY_TYPE act : ACTIVITY_TYPE.values()) {
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
			this(mat.getType().getId(), mat.getType().getName(), mat.getQuantity());
		}
	}

	public static record ProducedItem(int typeId, String typeName, int quantity, double probability) {
		public ProducedItem(Product prod) {
			this(prod.getType().getId(), prod.getType().getName(), prod.getQuantity(), prod.getProbability());
		}
	}

	public static record ActivityInfo(int typeId, String name, ACTIVITY_TYPE activity,
			List<ConsumedMat> consumes, List<ProducedItem> produces) {

		public ActivityInfo(Type type, ACTIVITY_TYPE activity, List<Material> mats, List<Product> prods) {
			this(type.getId(), type.getName(), activity,
					mats.stream().map(ConsumedMat::new).toList(),
					prods.stream().map(ProducedItem::new).toList());
		}
	}

	@GetMapping("/{typeFiltering}/{typeFilter}/{activity}")
	public ResponseEntity<List<ActivityInfo>> showActivity(
			@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@PathVariable ACTIVITY_TYPE activity,
			@RequestParam Optional<String> accept) throws IOException {
		List<ActivityInfo> ret = typeService.typesFilter(typeFiltering, typeFilter).stream()
				.map(t -> new ActivityInfo(t, activity,
		        materialService.forBPActivity(t.getId(), activity),
		        productService.findProducts(t.getId(), activity)))
				.toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

}
