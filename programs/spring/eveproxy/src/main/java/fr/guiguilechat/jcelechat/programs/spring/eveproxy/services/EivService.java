package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.prices.services.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class EivService {

	final private MaterialService materialService;

	final private PriceService priceService;

	public double eiv(int blueprintId) {
		Map<Integer, Double> adj = priceService.adjusted();
		return Math.floor(materialService.forBPActivity(blueprintId, ACTIVITY_TYPE.manufacturing).stream()
				.mapToDouble(m -> adj.getOrDefault(m.getType().getTypeId(), 0.0) * m.getQuantity())
				.sum());
	}

}
