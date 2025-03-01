package fr.guiguilechat.jcelechat.libs.spring.universe.statistics;

import java.time.Instant;
import java.time.ZoneOffset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateAggregation {
	hourly("hour", 1),
	dayly("day", 24),
	weekly("week", 24 * 7),
	monthly("month", 365 / 12);

	/**
	 * hql's truncate() "field" parameter. Not used at the moment.
	 */
	private final String hqlTruncateField;
	private final int nbHours;

	public SystemDateActivity ActivityOfRow(Object[] row) {
		return new SystemDateActivity(
				((Number) row[0]).intValue(),
				((Instant) row[1]).atOffset(ZoneOffset.UTC),
				((Number) row[2]).doubleValue(),
				((Number) row[2]).doubleValue() / getNbHours());
	}
}
