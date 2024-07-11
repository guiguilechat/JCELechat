package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.BlueprintActivity.ACTIVITY_TYPE;
import fr.guiguilechat.jcelechat.libs.spring.industry.blueprint.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.trade2.prices.PriceService;
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
		Map<Type, Double> adj = priceService.adjusted();
		return Math.floor(materialService.forBPActivity(blueprintId, ACTIVITY_TYPE.manufacturing).stream()
		    .mapToDouble(m -> adj.getOrDefault(m.getType(), 0.0) * m.getQuantity())
				.sum());
	}

}
