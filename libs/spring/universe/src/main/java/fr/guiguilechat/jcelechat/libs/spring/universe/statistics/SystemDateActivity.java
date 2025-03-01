package fr.guiguilechat.jcelechat.libs.spring.universe.statistics;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record SystemDateActivity(int sysId, OffsetDateTime date, double activity) {

	/**
	 * converts a db row into an activity
	 *
	 * @param row {system id, instant, value}
	 */
	public static SystemDateActivity ofRow(Object[] row) {
		return new SystemDateActivity(
				((Number) row[0]).intValue(),
				((Instant) row[1]).atOffset(ZoneOffset.UTC),
				((Number) row[0]).doubleValue());
	}

}
