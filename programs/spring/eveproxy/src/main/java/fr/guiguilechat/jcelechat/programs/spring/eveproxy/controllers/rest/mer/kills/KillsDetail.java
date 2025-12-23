package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats.KillStats;

/**
 * requested kill details over a period to chart on
 */
public enum KillsDetail {
	AVG_LOST("average M isk lost") {
		@Override
		public double extract(KillStats stat) {
			return stat.totalIskLost().doubleValue() / stat.nbKills().intValue() / 100000;
		}
	},
	KILLS_P_DAY("kills per day") {
		@Override
		public double extract(KillStats stat) {
			return stat.killPerDay();
		}
	},
	MIN_LOST("min M isk destroyed") {
		@Override
		public double extract(KillStats stat) {
			return stat.minIskDestroyed().doubleValue() / 1000000;
		}
	},
	TOTAL_LOST_P_DAY("total M isk lost per day") {
		@Override
		public double extract(KillStats stat) {
			return stat.avgFitPrice();

		}
	};

	public final String legend;

	KillsDetail(String legend) {
		this.legend = legend;
	}

	public abstract double extract(KillStats stat);

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