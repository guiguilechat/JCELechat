package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.BpService2;

@RestController
@RequestMapping("/api/blueprint")
public class BPRestController {

	@Autowired
	private BlueprintActivityService blueprintActivityService;

	@Autowired
	private BpService2 bpService2;

	@Autowired
	private TypeService typeService;

	@Autowired
	private RegionLineService regionLineService;

	public static record BpInfo(int typeId, String name, double eiv, long researchSeconds, String researchTime,
			Map<Integer, Map<Long, Double>> rid2lid2price) {

		BpInfo(int typeId, String name, double eiv, long researchTime, Map<Integer, Map<Long, Double>> rid2lid2price) {
			this(typeId, name, eiv, researchTime, secondsToDuration(researchTime), rid2lid2price);
		}

		static String secondsToDuration(long seconds) {
			StringBuilder sb = new StringBuilder();
			if (seconds > 3600 * 24) {
				int days = (int) (seconds / (3600 * 24));
				seconds = seconds % (3600 * 24);
				sb.append(days).append("d");
			}
			if (seconds > 3600) {
				int hours = (int) (seconds / 3600);
				seconds = seconds % 3600;
				sb.append(hours).append("h");
			}
			if (seconds > 60) {
				int minutes = (int) (seconds / 60);
				seconds = seconds % 60;
				sb.append(minutes).append("m");
			}
			sb.append(seconds).append("s");
			return sb.toString();
		}

	}

	@GetMapping("/{typeFiltering}/{typeFilter}")
	public ResponseEntity<?> showInformation(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@RequestParam Optional<String> accept) throws IOException {
		List<BpInfo> ret = RestControllerHelper.typesFilter(typeService, typeFiltering, typeFilter)
				.stream().map(type -> {
					double eiv = bpService2.eiv(type.getTypeId());
					List<OfferLocation> seeds = type.getMarketGroupID() > 0 ? regionLineService.seedLocations(type.getTypeId())
							: Collections.emptyList();
					Map<Integer, Map<Long, Double>> seedMap = new HashMap<>();
					for (OfferLocation s : seeds) {
						Map<Long, Double> rmap = seedMap.computeIfAbsent(s.regionId(), rid -> new HashMap<>());
						rmap.put(s.locationID(), s.bestPrice());
					}
					List<BlueprintActivity> mes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchMat);
					long meTime = mes.size() != 1 ? 0 : mes.get(0).getTime() * 256000;
					List<BlueprintActivity> tes = blueprintActivityService.forBPActivity(type.getTypeId(),
							ACTIVITY_TYPE.researchTime);
					long teTime = tes.size() != 1 ? 0 : tes.get(0).getTime() * 256000;
					long researchTime = teTime + meTime;

					return new BpInfo(type.getTypeId(), type.getName(), eiv, researchTime, seedMap);
				}).toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

}
