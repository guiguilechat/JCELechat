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

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.LocatedBestOffer;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
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

	final private RegionLineService regionLineService;

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
			this(type.getTypeId(), type.getName(), type.getBasePrice(),
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
					List<LocatedBestOffer> seeds = type.getMarketGroupId() > 0 ? regionLineService.seedLocations(type.getTypeId())
							: Collections.emptyList();
					Map<Integer, Map<Long, Double>> seedMap = new HashMap<>();
					for (LocatedBestOffer s : seeds) {
						Map<Long, Double> rmap = seedMap.computeIfAbsent(s.regionId(), rid -> new HashMap<>());
						rmap.put(s.locationId(), s.bestPrice());
					}
					List<BlueprintActivity> mes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchMat);
					long meTime = mes.size() != 1 ? 0 : 256000l * mes.get(0).getTime() / 105;
					List<BlueprintActivity> tes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchTime);

					BPInfo bp = null;
					if (type.getGroup().getCategory().getCategoryId() == 9) {
						double eiv = eivService.eiv(type.getTypeId());
						long teTime = tes.size() != 1 ? 0 : 256000l * tes.get(0).getTime() / 105;
						long researchTime = teTime + meTime;

						LinkedHashMap<ACTIVITY_TYPE, List<Integer>> produces = new LinkedHashMap<>();
						for (ACTIVITY_TYPE act : ACTIVITY_TYPE.values()) {
							List<Product> prods = productService.findProducts(type.getTypeId(), act);
							if (!prods.isEmpty()) {
								produces.put(act, prods.stream().map(p -> p.getType().getTypeId()).toList());
							}
						}
						bp = new BPInfo(eiv, researchTime, produces);
					}

					LinkedHashMap<ACTIVITY_TYPE, List<Integer>> productOf = new LinkedHashMap<>();
					for (ACTIVITY_TYPE act : ACTIVITY_TYPE.values()) {
						List<Product> prods = productService.findProducers(type.getTypeId(), act);
						if (!prods.isEmpty()) {
							productOf.put(act, prods.stream().map(p -> p.getActivity().getType().getTypeId()).toList());
						}
					}

					return new IndustryInfo(type, bp, productOf, seedMap);
				}).toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

	public static record ConsumedMat(int typeId, String typeName, int quantity) {
		public ConsumedMat(Material mat) {
			this(mat.getType().getTypeId(), mat.getType().getName(), mat.getQuantity());
		}
	}

	public static record ProducedItem(int typeId, String typeName, int quantity, double probability) {
		public ProducedItem(Product prod) {
			this(prod.getType().getTypeId(), prod.getType().getName(), prod.getQuantity(), prod.getProbability());
		}
	}

	public static record ActivityInfo(int typeId, String name, ACTIVITY_TYPE activity,
			List<ConsumedMat> consumes, List<ProducedItem> produces) {

		public ActivityInfo(Type type, ACTIVITY_TYPE activity, List<Material> mats, List<Product> prods) {
			this(type.getTypeId(), type.getName(), activity,
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
						materialService.forBPActivity(t.getTypeId(), activity),
						productService.findProducts(t.getTypeId(), activity)))
				.toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

}
