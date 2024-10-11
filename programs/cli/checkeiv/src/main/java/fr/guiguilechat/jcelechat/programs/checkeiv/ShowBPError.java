package fr.guiguilechat.jcelechat.programs.checkeiv;

import java.util.Comparator;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ShowBPError {

	@Data
	@RequiredArgsConstructor
	static class Evaluated {

		@Getter
		private final Blueprint bp;

		@Getter
		private final int type_id;

		@Getter(lazy = true)
		private final EveType type = TypeIndex.getType(type_id);

		@Getter(lazy = true)
		private final double eiv = bp.makeEIV(ESIAccess.INSTANCE.markets::getAdjusted);

		@Getter(lazy = true)
		private final double eivAvg = bp.makeEIV(ESIAccess.INSTANCE.markets::getAverage);

		@Getter(lazy = true)
		private final double logDiffAvg = Math.log10(getEivAvg() / getEiv());

		@Getter(lazy = true)
		private final double eivSO = bp
		    .makeEIV(type_id -> ESIAccess.INSTANCE.markets.getLocalMarket(10000002).getSOValue(type_id, 1).get());

		@Getter(lazy = true)
		private final double logDiffSO = Math.log10(getEivSO() / getEiv());
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("bp\tEIV\tEIV SO\tEIV avg\ttax effect %SO\ttax effect %avg\n");
		Blueprint.load().entrySet().stream()
		.map(e -> new Evaluated(e.getValue(), e.getKey()))
		.filter(e -> !e.getType().name.startsWith("Expired ")
				&& e.getEiv() != 0.0
				&& e.getEivSO() != Double.POSITIVE_INFINITY)
				.sorted(Comparator.comparing(e -> -Math.min(Math.abs(e.getLogDiffSO()), Math.abs(e.getLogDiffAvg()))))
		.forEach(e -> {
			sb.append(String.format("%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",
					e.getType().name,
					e.getEiv(),
					e.getEivSO(),
					e.getEivAvg(),
					100 * e.getEiv() / e.getEivSO(),
					100 * e.getEiv() / e.getEivAvg()));
		});
		System.out.println(sb);

	}

}
