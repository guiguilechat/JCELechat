package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.OfferLocation;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.BpService2;

@RestController
@RequestMapping("/api/industry")
public class IndustryRestController {

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	private BpService2 bpService2;

	@Autowired
	private ProductService productService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private RegionLineService regionLineService;

	public static record BPInfo(double eiv, long researchSeconds, String researchHuman,
			Map<ACTIVITY_TYPE, List<Integer>> produces) {

		public BPInfo(double eiv, long researchTime, Map<ACTIVITY_TYPE, List<Integer>> produces) {
			this(eiv, researchTime, secondsToDuration(researchTime), produces);
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
					List<OfferLocation> seeds = type.getMarketGroupID() > 0 ? regionLineService.seedLocations(type.getTypeId())
							: Collections.emptyList();
					Map<Integer, Map<Long, Double>> seedMap = new HashMap<>();
					for (OfferLocation s : seeds) {
						Map<Long, Double> rmap = seedMap.computeIfAbsent(s.regionId(), rid -> new HashMap<>());
						rmap.put(s.locationID(), s.bestPrice());
					}
					List<BlueprintActivity> mes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchMat);
					long meTime = mes.size() != 1 ? 0 : 256000l * mes.get(0).getTime() / 105;
					List<BlueprintActivity> tes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchTime);

					BPInfo bp = null;
					if (type.getGroup().getCategory().getCategoryId() == 9) {
						double eiv = bpService2.eiv(type.getTypeId());
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

}
