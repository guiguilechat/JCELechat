package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceService;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class EivService {

	final private BlueprintMaterialService materialService;

	final private PriceService priceService;

	protected long eiv(Map<Integer, Double> adj, List<BlueprintMaterial> mats) {
		return (long) Math.floor(mats.stream()
				.mapToDouble(m -> adj.getOrDefault(m.getTypeId(), 0.0) * m.getQuantity())
		    .sum());
	}

	public long eiv(int blueprintId) {
		return eiv(priceService.adjusted(), materialService.forBPActivity(blueprintId, ActivityType.manufacturing));
	}

	/**
	 * @param bpIds blueprint ids
	 * @return for each blueprint id provided, the eiv of that blueprint.
	 */
	public Map<Integer, Long> eivs(Iterable<Integer> bpIds) {
		if (bpIds == null || !bpIds.iterator().hasNext()) {
			return Map.of();
		}
		Map<Integer, Double> adjusted = priceService.adjusted();
		Map<Integer, List<BlueprintMaterial>> bpIdToMaterials = materialService
				.forBPActivity(bpIds, List.of(ActivityType.manufacturing)).stream()
				.collect(Collectors.groupingBy(mat -> mat.getActivity().getTypeId()));
		return bpIdToMaterials.entrySet().stream()
		    .collect(Collectors.toMap(Entry::getKey, e -> eiv(adjusted, e.getValue())));
	}

}
