package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeAttributeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanetaryTaxService {

	final private TypeAttributeService typeAttributeService;

	private static final int EXPORTTAXMULTIPLIER_ID = 1641;

	public Map<Integer, Double> exportTaxById() {
		return typeAttributeService.byAttributeId(EXPORTTAXMULTIPLIER_ID).stream()
				.collect(Collectors.toMap(ta -> ta.getType().getTypeId(), ta -> ta.getAttValue().doubleValue()));
	}

	public Map<Type, Double> exportTaxByType() {
		return typeAttributeService.byAttributeId(EXPORTTAXMULTIPLIER_ID).stream()
				.collect(Collectors.toMap(TypeAttribute::getType, ta -> ta.getAttValue().doubleValue()));
	}

	private static final int IMPORTTAXMULTIPLIER_ID = 1640;

	public Map<Integer, Double> importTaxById() {
		return typeAttributeService.byAttributeId(IMPORTTAXMULTIPLIER_ID).stream()
				.collect(Collectors.toMap(ta -> ta.getType().getTypeId(), ta -> ta.getAttValue().doubleValue()));
	}

	public Map<Type, Double> importTaxByType() {
		return typeAttributeService.byAttributeId(IMPORTTAXMULTIPLIER_ID).stream()
				.collect(Collectors.toMap(TypeAttribute::getType, ta -> ta.getAttValue().doubleValue()));
	}

	public double concordTaxMult(boolean hs, int customCodeExpertise) {
		return hs ? 0.1 - 0.01 * customCodeExpertise : 0.0;
	}

}
