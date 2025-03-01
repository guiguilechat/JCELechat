package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;

public record PeriodKillStats(String periodStart, long numberKills, double totalMIskLost, double averageMIskLost,
		double medianMIskLost) {

	public PeriodKillStats(KillStats source, AggregPeriod period) {
		this(period.format(source.periodStart()),
				source.nbKills(),
				source.totalIskLost() / 1000000,
				source.totalIskLost() / 1000000 / source.nbKills(),
				source.medianIskLost() / 1000000);
	}

}