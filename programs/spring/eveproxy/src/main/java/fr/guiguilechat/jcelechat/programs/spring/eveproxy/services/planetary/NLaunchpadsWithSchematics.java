package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class NLaunchpadsWithSchematics implements PlanetaryFactory {

	private final Schematic schematic;
	private final int nbSchematics;
	private final int nbLP;

	private static final float TOTAL_CPU = 25415;
	private static final int LP_CPU = 3600;

	private static final int LAUNCHPAD_STORAGE = 10000;

	public static int linkCpu(int layer) {
		return 17 + layer * 3;
	}

	public static int maxSchematics(int layer) {
		return 6 * layer;
	}

	public static int nbSchematics(int nbLaunchpads, int schematicsCpu) {
		int cpuPerGroup = (int) Math.floor(TOTAL_CPU / nbLaunchpads);
		int remainingGroupCpu = cpuPerGroup - LP_CPU;
		int groupSchematics = 0;
		int linkedPipCpu = schematicsCpu;
		for (int layer = 1; layer <= 8 && linkedPipCpu <= remainingGroupCpu; layer++) {
			linkedPipCpu = schematicsCpu + linkCpu(layer);
			int layerSchematics = Math.min(maxSchematics(layer), remainingGroupCpu / linkedPipCpu);
			remainingGroupCpu -= layerSchematics * linkedPipCpu;
			groupSchematics += layerSchematics;
		}
		return groupSchematics * nbLaunchpads;
	}

	public NLaunchpadsWithSchematics(Schematic schematic, int nbLP) {
		this(schematic, nbSchematics(nbLP, schematic.getCpuLoad()), nbLP);
	}

	@Override
	public ConsumeProduct production(int hours) {
		int maxCycleFromTime = nbSchematics * (hours * 3600 / schematic.getCycleTime());
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
		return schematic.getName() + "×" + nbSchematics + "+Launchpad×" + nbLP;
	}

	public static Stream<NLaunchpadsWithSchematics> stream(Schematic schematic) {
		return IntStream.rangeClosed(1, 6).mapToObj(nbLP -> new NLaunchpadsWithSchematics(schematic, nbLP))
				.filter(pf -> pf.getNbSchematics() > 0);
	}

}
