package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.ConsumeProduct;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.PlanetaryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IntegrityResponseDroneFromP2 implements PlanetaryFactory {

	private static final int INPUT_SPACE = 30000;

	private static final int OUPUT_SPACE = 10000;

	private static final int NB_P4 = 3;

	private static final int P4_HOUR_VOLUME = 50;

	// each P4 feeds from 2 P3 for each of the 3 P3 required
	private static final int P3_PER_P4 = 6;

	// each cycle of a P3 consumes 10 of each of the 3 P2
	private static final int P3_CONSUME_P2 = 10 * 3;

	private static final double P2_VOLUME = 0.75;

	private static final List<Integer> P2_IDS = List.of(
			2329, // biocells
			3689, // mechanical parts
			9842, // miniature electronics
			2317, // oxydes
			3695, // polytextiles
			9838, // superconductors
			2312, // superstensile plastics
			9840, // transmitter
			3775// viral agent
	);

	private static final List<Integer> P4_IDS = List.of(
			2868// integrity response drones
	);

	private final TypeService typeService;

	@Override
	public ConsumeProduct production(int hours) {
		int cycles = Math.min(
				Math.min(hours * NB_P4, (int) Math.floor(INPUT_SPACE / P3_PER_P4 / P3_CONSUME_P2 / P2_VOLUME)),
				OUPUT_SPACE / P4_HOUR_VOLUME);
		Map<Type, Long> materials = typeService.byId(P2_IDS).stream()
				.collect(Collectors.toMap(t -> t, t -> 20l * cycles));
		Map<Type, Long> product = typeService.byId(P4_IDS).stream().collect(Collectors.toMap(t -> t, t -> 1l * cycles));
		return new ConsumeProduct(materials, product);
	}

	@Override
	public String name() {
		return "3×IntegrityResponseDrone, 18P3";
	}

}
