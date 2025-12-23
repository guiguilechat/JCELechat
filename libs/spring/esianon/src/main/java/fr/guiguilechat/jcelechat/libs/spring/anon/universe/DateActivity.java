package fr.guiguilechat.jcelechat.libs.spring.anon.universe;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record DateActivity(OffsetDateTime date, double activity) {

	public static DateActivity ofRow(Object[] row) {
		return new DateActivity(
				((Instant) row[0]).atOffset(ZoneOffset.UTC),
				((Number) row[1]).doubleValue());
	}

}
