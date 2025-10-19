package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.SchemMaterial;
import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.SchemProduct;
import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.Schematic;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
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

	private static final float TOTAL_PG = 19000;
	private static final int LP_PG = 700;

	private static final int LAUNCHPAD_STORAGE = 10000;

	public static int linkCpu(int layer) {
		return 17 + layer * 3;
	}

	public static int linkPg(int layer) {
		return 9 + layer * 4;
	}

	public static int maxSchematics(int layer) {
		return 6 * layer;
	}

	public static int nbSchematics(int nbLaunchpads, int schematicsCpu, int schematicsPg) {
		int cpuPerGroup = (int) Math.floor(TOTAL_CPU / nbLaunchpads);
		int pgPerGroup = (int) Math.floor(TOTAL_PG / nbLaunchpads);
		int remainingGroupCpu = cpuPerGroup - LP_CPU;
		int remainingGroupPg = pgPerGroup - LP_PG;
		int groupSchematics = 0;
		int linkedPipCpu = schematicsCpu;
		int linkedPipPg = schematicsPg;
		for (int layer = 1; layer <= 8 && linkedPipCpu <= remainingGroupCpu && linkedPipPg <= remainingGroupPg; layer++) {
			linkedPipCpu = schematicsCpu + linkCpu(layer);
			linkedPipPg = schematicsPg + linkPg(layer);
			int layerSchematics = Math.min(maxSchematics(layer), remainingGroupCpu / linkedPipCpu);
			layerSchematics = Math.min(layerSchematics, remainingGroupPg / linkedPipPg);
			remainingGroupCpu -= layerSchematics * linkedPipCpu;
			remainingGroupPg -= layerSchematics * linkedPipPg;
			groupSchematics += layerSchematics;
		}
		return groupSchematics * nbLaunchpads;
	}

	public NLaunchpadsWithSchematics(Schematic schematic, int nbLP) {
		this(schematic, nbSchematics(nbLP, schematic.getCpuLoad(), schematic.getPowerLoad()), nbLP);
	}

	@Override
	public ConsumeProduct production(int hours) {
		int maxCycleFromTime = nbSchematics * (hours * 3600 / schematic.getCycleTime());
		int maxCyclesFromMat = (int) Math.floor(nbLP * LAUNCHPAD_STORAGE
				/ schematic.getMaterials().stream()
						.mapToDouble(mat -> mat.getQuantity() * mat.getType().getVolume().doubleValue()).sum());
		int maxCyclesFromProd = (int) Math.floor(nbLP * LAUNCHPAD_STORAGE
				/ schematic.getProducts().stream()
						.mapToDouble(prod -> prod.getQuantity() * prod.getType().getVolume().doubleValue()).sum());
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

	@Override
	public Collection<Type> products() {
		return schematic.getProducts().stream().map(SchemProduct::getType).toList();
	}

	public static Stream<NLaunchpadsWithSchematics> stream(Schematic schematic) {
		return IntStream.rangeClosed(1, 6).mapToObj(nbLP -> new NLaunchpadsWithSchematics(schematic, nbLP))
				.filter(pf -> pf.getNbSchematics() > 0);
	}

}
