package fr.guiguilechat.jcelechat.libs.spring.mer.kill.stats;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

/** how to aggregate kills */
public enum KillsAggregation {
	DAYLY {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.append('-')
					.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
					.append('-')
					.append(offsetted.get(ChronoField.DAY_OF_MONTH))
					.toString();
		}
	},
	MONTHLY {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.append('-')
					.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
					.toString();
		}
	},
	WEEKLY {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.append('w')
					.append(offsetted.get(ChronoField.ALIGNED_WEEK_OF_YEAR))
					.toString();
		}
	},
	YEARLY {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.toString();
		}
	};

	public String legend() {
		return toString().toLowerCase();
	}

	public abstract String format(OffsetDateTime offsetted);

	public String format(Instant instant) {
		return format(instant.atOffset(ZoneOffset.UTC));
	}
}