package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.Material;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.trade.prices.PriceService;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class EivService {

	final private MaterialService materialService;

	final private PriceService priceService;

	protected long eiv(Map<Integer, Double> adj, List<Material> mats) {
		return (long) Math.floor(mats.stream()
		    .mapToDouble(m -> adj.getOrDefault(m.getType().getId(), 0.0) * m.getQuantity())
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
		Map<Integer, List<Material>> bpIdToMaterials = materialService
				.forBPActivity(bpIds, List.of(ActivityType.manufacturing)).stream()
		    .collect(Collectors.groupingBy(mat -> mat.getActivity().getType().getId()));
		return bpIdToMaterials.entrySet().stream()
		    .collect(Collectors.toMap(Entry::getKey, e -> eiv(adjusted, e.getValue())));
	}

}
