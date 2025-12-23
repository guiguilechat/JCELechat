package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStats;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillsAggregation;

public record PeriodKillStats(String periodStart, long numberKills, double totalMIskLost, double averageMIskLost) {

	public PeriodKillStats(KillStats source, KillsAggregation period) {
		this(period.format(source.periodStart()),
				source.nbKills().longValue(),
				source.totalIskLost().doubleValue() / 1000000,
				source.totalIskLost().doubleValue() / 1000000 / source.nbKills().longValue());
	}

}