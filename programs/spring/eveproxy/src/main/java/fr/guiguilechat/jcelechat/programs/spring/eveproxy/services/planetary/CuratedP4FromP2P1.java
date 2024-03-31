package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class CuratedP4FromP2P1 implements PlanetaryFactory {

	private static final int INPUT_SPACE = 30000;

	private static final int OUPUT_SPACE = 10000;

	private static final int NB_P4F = 4;

	private static final int P4F_HOUR_VOLUME = 50;

	// each P4F feeds from 2 P3F for each of P3 required
	private static final int EACH_P3F_PER_P4F = 2;

	// each cycle of a P3F consumes 10 of each of the 2 P2
	private static final int P3F_CONSUME_EACHP2 = 10;

	private static final double P2_VOLUME = 0.75;

	private static final int P4F_CONSUME_P1 = 40;

	private static final double P1_VOLUME = 0.19;

	private final Type product;

	private final List<Type> p2s;

	/** total volume of the P2 consumed per P4 cycle */
	@Getter(lazy = true)
	private final double volAllP2PerP4Cycle = EACH_P3F_PER_P4F * p2s.size() * P3F_CONSUME_EACHP2 * P2_VOLUME;

	private final List<Type> p1s;

	/** total volume of the P2 consumed per P4 cycle */
	@Getter(lazy = true)
	private final double volAllP1PerP4Cycle = P4F_CONSUME_P1 * p1s.size() * P1_VOLUME;

	public CuratedP4FromP2P1(int[] productsIds, int[] p2sIds, int[] p1sIds, Map<Integer, Type> typesbyId) {
		this(typesbyId.get(productsIds[0]),
				IntStream.of(p2sIds).mapToObj(typesbyId::get).toList(),
				IntStream.of(p1sIds).mapToObj(typesbyId::get).toList()
				);
	}

	@Override
	public ConsumeProduct production(int hours) {
		int cycles = Math.min(
				Math.min(hours * NB_P4F,
						(int) Math
								.floor(INPUT_SPACE / (getVolAllP2PerP4Cycle() + getVolAllP1PerP4Cycle()))),
				OUPUT_SPACE / P4F_HOUR_VOLUME);
		Map<Type, Long> materialsQuantity = new HashMap<>();
		materialsQuantity.putAll(
				p2s.stream()
						.collect(Collectors.toMap(t -> t, t -> 1l * EACH_P3F_PER_P4F * P3F_CONSUME_EACHP2 * cycles)));
		materialsQuantity.putAll(
				p1s.stream()
						.collect(Collectors.toMap(t -> t, t -> 1l * P4F_CONSUME_P1 * cycles)));
		Map<Type, Long> productsQuantity = Stream.of(product).collect(Collectors.toMap(t -> t, t -> 1l * cycles));
		return new ConsumeProduct(materialsQuantity, productsQuantity);
	}

	@Override
	public String name() {
		return "" + NB_P4F + "×" + product.getName() + " from P2P1";
	}

	@Override
	public Collection<Type> products() {
		return Set.of(product);
	}

	// list of [productarray,materialsarray]
	private static final int[][][] curatedProductsMaterial = {
			{
					{ 2869 }, // Nano-Factory
					{
							3693, // fertilizer
							3695, // polytextiles
							9838, // superconductors
							3691, // synthetic oil
					},
					{
							2398 // reactive metals
					}
			},
			{
					{ 2870 }, // Organic Mortar Applicators
					{
							9836, // consumer electronics
							9832, // coolant
							3689, // mechanical parts
							2317, // oxydes
					},
					{
							2393 // bacteria
					}
			},
			{
					{ 2875 }, // Sterile Conduits
					{
							3828, // construction blocks
							3725, // livestock
							9842, // miniature electronics
							3775, // viral agent
					},
					{
							3645 // water
					}
			}
	};

	public static Stream<CuratedP4FromP2P1> stream(TypeService typeService) {
		Map<Integer, Type> typesbyId = typeService.byIdIn(
				Stream.of(curatedProductsMaterial)
						.flatMapToInt(arr -> Stream.of(arr).flatMapToInt(IntStream::of))
						.boxed().toList())
				.stream()
				.collect(Collectors.toMap(Type::getTypeId, t -> t));
		return Stream.of(curatedProductsMaterial)
				.map(curated -> new CuratedP4FromP2P1(curated[0], curated[1], curated[2], typesbyId))
				.filter(cpfp -> cpfp.getProduct() != null);
	}

}
