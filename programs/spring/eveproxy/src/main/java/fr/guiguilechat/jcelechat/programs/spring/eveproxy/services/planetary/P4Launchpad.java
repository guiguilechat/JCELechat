package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.Schematic;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.ConsumeProduct;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.PlanetaryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * an installation made of a single launchpad and 18 P4 factories
 */
@Getter
@Setter
@RequiredArgsConstructor
public class P4Launchpad implements PlanetaryFactory {

	private final Schematic schematic;

	private static final int NB_P4 = 18;
	private static final int LAUNCHPAD_STORAGE = 10000;

	@Override
	public ConsumeProduct production(int hours) {
		int maxCycleFromTime = NB_P4 * (hours * 3600 / schematic.getCycleTime());
		int maxCyclesFromMat = (int) Math.floor(LAUNCHPAD_STORAGE
				/ schematic.getMaterials().stream().mapToDouble(mat -> mat.getQuantity() * mat.getType().getVolume()).sum());
		int maxCyclesFromProd = (int) Math.floor(LAUNCHPAD_STORAGE
				/ schematic.getProducts().stream().mapToDouble(prod -> prod.getQuantity() * prod.getType().getVolume()).sum());
		int cycles = Math.min(maxCycleFromTime, Math.min(maxCyclesFromMat, maxCyclesFromProd));
		Map<Type, Long> mats = schematic.getMaterials().stream()
				.collect(Collectors.toMap(SchemMaterial::getType, mat -> (long) (cycles * mat.getQuantity())));
		Map<Type, Long> prods = schematic.getProducts().stream()
				.collect(Collectors.toMap(SchemProduct::getType, mat -> (long) (cycles * mat.getQuantity())));
		return new ConsumeProduct(mats, prods);
	}

	@Override
	public String name() {
		return schematic.getName() + "×" + NB_P4 + "+Launchpad×1";
	}

}
