package fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record KillStats(
		Instant periodStart, Instant periodEnd,
		Number nbKills,
		Number totalIskLost, Number minIskDestroyed)
		implements Serializable {

	public long periodDays() {
		return ChronoUnit.DAYS.between(periodStart, periodEnd);
	}

	public double killPerDay() {
		return nbKills().doubleValue() / periodDays();
	}

	public double iksLostPerDay() {
		return totalIskLost().doubleValue() / periodDays();
	}

	public double avgFitPrice() {
		return totalIskLost().doubleValue() / nbKills().longValue();
	}


}