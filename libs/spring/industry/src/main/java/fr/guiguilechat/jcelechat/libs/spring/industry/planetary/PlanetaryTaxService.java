package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlanetaryTaxService {

	final private TypeAttributeService typeAttributeService;

	private static final int EXPORTTAXMULTIPLIER_ID = 1641;

	public Map<Integer, Double> exportTaxById() {
		return typeAttributeService.byAttributeId(EXPORTTAXMULTIPLIER_ID).stream()
		    .collect(Collectors.toMap(ta -> ta.getType().getId(), ta -> ta.getValue().doubleValue()));
	}

	public Map<Type, Double> exportTaxByType() {
		return typeAttributeService.byAttributeId(EXPORTTAXMULTIPLIER_ID).stream()
		    .collect(Collectors.toMap(TypeAttribute::getType, ta -> ta.getValue().doubleValue()));
	}

	private static final int IMPORTTAXMULTIPLIER_ID = 1640;

	public Map<Integer, Double> importTaxById() {
		return typeAttributeService.byAttributeId(IMPORTTAXMULTIPLIER_ID).stream()
		    .collect(Collectors.toMap(ta -> ta.getType().getId(), ta -> ta.getValue().doubleValue()));
	}

	public Map<Type, Double> importTaxByType() {
		return typeAttributeService.byAttributeId(IMPORTTAXMULTIPLIER_ID).stream()
		    .collect(Collectors.toMap(TypeAttribute::getType, ta -> ta.getValue().doubleValue()));
	}

	public double concordTaxMult(boolean hs, int customCodeExpertise) {
		return hs ? 0.1 - 0.01 * customCodeExpertise : 0.0;
	}

}
