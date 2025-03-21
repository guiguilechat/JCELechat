package fr.guiguilechat.jcelechat.programs.checkeiv;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ShowMaterialsErrorsFromBP {

	@Data
	@RequiredArgsConstructor
	static class Evaluated {

		@Getter
		private final R_get_markets_prices prices;

		@Getter(lazy = true)
		private final EveType type = TypeIndex.getType(prices.type_id);

		@Getter(lazy = true)
		private final double jitaSO = ESIAccess.INSTANCE.markets.getLocalMarket(10000002).getSOValue(prices.type_id, 1)
		    .get();

		@Getter(lazy = true)
		private final double logDiffMarket = Math.log10(getJitaSO() / prices.adjusted_price);

		@Getter(lazy = true)
		private final double logDiffAverage = Math.log10(prices.average_price / prices.adjusted_price);
	}

	public static void main(String[] args) {
		Set<Integer> usedMatsIds = Blueprint.storage().load().values().stream().filter(bp -> bp.manufacturing != null
				&& bp.manufacturing.products != null
				&& !bp.manufacturing.products.isEmpty())
				.flatMap(bp -> bp.manufacturing.materials.stream())
				.mapToInt(mr -> mr.id)
				.boxed().collect(Collectors.toSet())
				;

		List<R_get_markets_prices> prices = ESIAccess.INSTANCE.markets.marketPrices().get();
		System.out.println("type\tid\tadjusted\tJita SO\taverage\tEIV %SO\tEIV %avg");
		prices.stream().map(Evaluated::new)
		.filter(e -> e.getType() != null
						&& e.getJitaSO() != Double.POSITIVE_INFINITY
						&& usedMatsIds.contains(e.prices.type_id))
		.sorted(Comparator.comparing(e -> -Math.min(Math.abs(e.getLogDiffMarket()), Math.abs(e.getLogDiffAverage()))))
		.forEach(e -> {
			System.out.printf("%s\t%d\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",
					e.getType().name,
					e.getType().id,
					e.getPrices().adjusted_price,
					e.getJitaSO(),
					e.getPrices().average_price,
					100 * e.getPrices().adjusted_price / e.getJitaSO(),
					100 * e.getPrices().adjusted_price / e.getPrices().average_price);
		});
	}

}
