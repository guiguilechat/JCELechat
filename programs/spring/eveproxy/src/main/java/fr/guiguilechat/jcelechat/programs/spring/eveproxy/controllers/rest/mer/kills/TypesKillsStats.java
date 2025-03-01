package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;

public record TypesKillsStats(KillsFilters filters, List<PeriodKillStats> stats) {

	public TypesKillsStats(List<Integer> types, String timeSort, List<KillStats> periodStats, AggregPeriod period) {
		this(new KillsFilters(types, timeSort, period),
				periodStats.stream().map(s -> new PeriodKillStats(s, period)).toList());
	}
}