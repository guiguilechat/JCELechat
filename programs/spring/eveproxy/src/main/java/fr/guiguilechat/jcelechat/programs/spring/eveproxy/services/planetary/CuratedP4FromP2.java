package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.ConsumeProduct;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.PlanetEvalService.PlanetaryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CuratedP4FromP2 implements PlanetaryFactory {

	private static final int INPUT_SPACE = 30000;

	private static final int OUPUT_SPACE = 10000;

	private static final int NB_P4 = 3;

	private static final int P4_HOUR_VOLUME = 50;

	// each P4 feeds from 2 P3 for each of the 3 P3 required
	private static final int P3_PER_P4 = 6;

	// each cycle of a P3 consumes 10 of each of the 3 P2
	private static final int P3_CONSUME_P2 = 10 * 3;

	private static final double P2_VOLUME = 0.75;

	private final Type product;

	private final List<Type> materials;

	public CuratedP4FromP2(int[] productsIds, int[] materialsIds, Map<Integer, Type> typesbyId) {
		this(typesbyId.get(productsIds[0]),
				IntStream.of(materialsIds).mapToObj(typesbyId::get).toList());
	}

	@Override
	public ConsumeProduct production(int hours) {
		int cycles = Math.min(
				Math.min(hours * NB_P4, (int) Math.floor(INPUT_SPACE / P3_PER_P4 / P3_CONSUME_P2 / P2_VOLUME)),
				OUPUT_SPACE / P4_HOUR_VOLUME);
		Map<Type, Long> materialsQuantity = materials.stream()
				.collect(Collectors.toMap(t -> t, t -> 20l * cycles));
		Map<Type, Long> productsQuantity = Stream.of(product).collect(Collectors.toMap(t -> t, t -> 1l * cycles));
		return new ConsumeProduct(materialsQuantity, productsQuantity);
	}

	@Override
	public String name() {
		return "3×" + product.getName() + " from P2";
	}

	// list of [productarray,materialsarray]
	private static final int[][][] curatedProductsMaterial = {
			{
					{ 2867 }, // broadcast nodes
					{
							2329, // biocells
							2327, // microfiber shielding
							2321, // polyaramids
							3697, // silicate glass
							2312, // superstensile plastics
							9840, // transmitter
					}
			},
			{
					{ 2868 }, // integrity response drones
					{
							2329, // biocells
							3689, // mechanical parts
							9842, // miniature electronics
							2317, // oxydes
							3695, // polytextiles
							9838, // superconductors
							2312, // superstensile plastics
							9840, // transmitter
							3775, // viral agent
					}
			},
			{
					{ 2871 }, // Recursive Computing Module
					{
							2329, // biocells
							2463, // nanites
							2312, // superstensile plastics
							2319, // test cultures
							9840, // transmitter
							2328, // water-cooled cpu
					}
			},
			{
					{ 2872 }, // self-harmonizing power core
					{
							44, // enriched uranium
							15317, // genetic enhanced livestocks
							2327, // microfiber shielding
							2321, // polyaramids
							9830, // rocket fuel
							3697, // silicate glass
					}
			},
			{
					{ 2876 }, // Wetware Mainframe
					{
							3828, // construction blocks
							9836, // consumer electtronics
							9832, // coolant
							3693, // fertilizer
							3725, // livestock
							2463, // nanites
							2319, // test cultures
							3691, // synthetic oil
							2328, // water-cooled cpu
					}
			},
	};

	public static Stream<CuratedP4FromP2> stream(TypeService typeService) {
		Map<Integer, Type> typesbyId = typeService.byId(
				Stream.of(curatedProductsMaterial)
						.flatMapToInt(arr -> Stream.of(arr).flatMapToInt(IntStream::of))
						.boxed().toList())
				.stream()
				.collect(Collectors.toMap(Type::getTypeId, t -> t));
		return Stream.of(curatedProductsMaterial).map(curated -> new CuratedP4FromP2(curated[0], curated[1], typesbyId));
	}

}
