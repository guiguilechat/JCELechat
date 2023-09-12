package fr.guiguilechat.jcelechat.programs.checkeiv;

import java.util.Comparator;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ShowMaterialsErrors {

	@Data
	@RequiredArgsConstructor
	static class Evaluated {

		@Getter
		private final R_get_markets_prices prices;

		@Getter(lazy = true)
		private final EveType type = TypeIndex.getType(prices.type_id);

		@Getter(lazy = true)
		private final double jitaSO = ESIAccess.INSTANCE.markets.getLocalMarket(10000002).getSO(prices.type_id, 1).get();

		@Getter(lazy = true)
		private final double logDiffMarket = Math.log10(getJitaSO() / prices.adjusted_price);

		@Getter(lazy = true)
		private final double logDiffAverage = Math.log10(prices.average_price / prices.adjusted_price);
	}

	public static void main(String[] args) {
		List<R_get_markets_prices> prices = ESIAccess.INSTANCE.markets.marketPrices().get();
		System.out.println("type\tJita SO\tadjusted\taverage\tlog10 diff mkt\tlog10 diff avg");
		prices.stream().map(Evaluated::new)
		.filter(e -> e.getType() != null
		// only material category
		&& e.getType().getCategoryId() == 4
		//exclude Biochemical Material group
		&& e.getType().getGroupId() != 712
		// exclude Intermediate Materials group
		&& e.getType().getGroupId() != 428

		// && e.getLogDiffMarket() <= 1000
				)
		.sorted(Comparator.comparing(e -> -Math.abs(e.getLogDiffMarket())))
		.forEach(e -> {

					System.out.printf("%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",
					e.getType().name,
					e.getJitaSO(),
					e.getPrices().adjusted_price,
					e.getPrices().average_price,
					e.getLogDiffMarket(),
					e.getLogDiffAverage());
		});
	}

}
