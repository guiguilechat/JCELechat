package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.merkills;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;

/**
 * requested kill details over a period to chart on
 */
public enum KillsDetail {
	AVG_LOST("average M isk lost") {
		@Override
		public double extract(KillStats stat, AggregPeriod by) {
			return stat.totalIskLost() / stat.nbKills() / 100000;
		}
	},
	KILLS_P_DAY("kills per day") {
		@Override
		public double extract(KillStats stat, AggregPeriod by) {
			OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
			int periodDays = by.periodDays(odt);
			return 1.0 * stat.nbKills() / periodDays;
		}
	},
	MEDIAN_LOST("median M isk lost") {
		@Override
		public double extract(KillStats stat, AggregPeriod by) {
			return stat.medianIskLost() / 1000000;
		}
	},
	MIN_LOST("min M isk lost") {
		@Override
		public double extract(KillStats stat, AggregPeriod by) {
			return stat.minIskLost() / 1000000;
		}
	},
	TOTAL_LOST_P_DAY("total M isk lost per day") {
		@Override
		public double extract(KillStats stat, AggregPeriod by) {
			OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
			int periodDays = by.periodDays(odt);
			return stat.totalIskLost() / periodDays / 1000000;

		}
	};

	public final String legend;

	private KillsDetail(String legend) {
		this.legend = legend;
	}

	public abstract double extract(KillStats stat, AggregPeriod by);

	public static KillsDetail of(String requested) {
		switch (Objects.requireNonNullElse(requested, "").toLowerCase()) {
			case "av":
			case "avg":
			case "average":
				return AVG_LOST;
			case "kill":
			case "kills":
			case "nb":
			case "qtty":
			case "quantity":
				return KILLS_P_DAY;
			case "md":
			case "med":
			case "median":
				return MEDIAN_LOST;
			case "low":
			case "min":
			case "minimum":
				return MIN_LOST;
			case "lost":
			case "tot":
			case "total":
			case "val":
			case "value":
				return TOTAL_LOST_P_DAY;
		}
		return KILLS_P_DAY;
	}
}