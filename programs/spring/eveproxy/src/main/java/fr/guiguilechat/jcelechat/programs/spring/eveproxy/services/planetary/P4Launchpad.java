package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private final int nbP4;
	private final int nbLP;

	private static final int LAUNCHPAD_STORAGE = 10000;

	@Override
	public ConsumeProduct production(int hours) {
		int maxCycleFromTime = nbP4 * (hours * 3600 / schematic.getCycleTime());
		int maxCyclesFromMat = (int) Math.floor(nbLP * LAUNCHPAD_STORAGE
				/ schematic.getMaterials().stream().mapToDouble(mat -> mat.getQuantity() * mat.getType().getVolume()).sum());
		int maxCyclesFromProd = (int) Math.floor(nbLP * LAUNCHPAD_STORAGE
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
		return schematic.getName() + "×" + nbP4 + "+Launchpad×" + nbLP;
	}

	// known possibilities of P4(high-tech production plant) and launchpad on a
	// planet.
	private static final int[][] P4_LP = {
			{ 18, 1 },
			{ 16, 2 },
			{ 12, 3 }
	};

	public static Stream<P4Launchpad> stream(Schematic schematic) {
		return Stream.of(P4_LP).map(arr -> new P4Launchpad(schematic, arr[0], arr[1]));
	}

}
