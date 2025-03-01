package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer.kills;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;

import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;

/**
 * period to aggregate kills over
 */
public enum AggregPeriod {
	MONTH {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.append('-')
					.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
					.toString();
		}

		@Override
		public List<KillStats> stats(List<Integer> types, KillService killservice) {
			return killservice.monthlyStats(types);
		}

		@Override
		public RegularTimePeriod of(OffsetDateTime odt) {
			return new Month(odt.getMonthValue(), odt.getYear());
		}

		@Override
		public int periodDays(OffsetDateTime odt) {
			return (int) ChronoUnit.DAYS.between(odt, odt.plusMonths(1));
		}
	},
	WEEK {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.append('w')
					.append(offsetted.get(ChronoField.ALIGNED_WEEK_OF_YEAR))
					.toString();
		}

		@Override
		public List<KillStats> stats(List<Integer> types, KillService killservice) {
			return killservice.weeklyStats(types);
		}

		@Override
		public RegularTimePeriod of(OffsetDateTime odt) {
			return new Week(odt.get(ChronoField.ALIGNED_WEEK_OF_YEAR), odt.getYear());
		}

		@Override
		public int periodDays(OffsetDateTime odt) {
			return 7;
		}
	},
	YEAR {
		@Override
		public String format(OffsetDateTime offsetted) {
			return new StringBuilder()
					.append(offsetted.get(ChronoField.YEAR))
					.toString();
		}

		@Override
		public List<KillStats> stats(List<Integer> types, KillService killservice) {
			return killservice.yearlyStats(types);
		}

		@Override
		public RegularTimePeriod of(OffsetDateTime odt) {
			return new Year(odt.getYear());
		}

		@Override
		public int periodDays(OffsetDateTime odt) {
			return (int) ChronoUnit.DAYS.between(odt, odt.plusYears(1));
		}
	},
	DAY {
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

		@Override
		public List<KillStats> stats(List<Integer> types, KillService killservice) {
			return killservice.dailyStats(types);
		}

		@Override
		public RegularTimePeriod of(OffsetDateTime odt) {
			return new Day(odt.getDayOfMonth(), odt.getMonthValue(), odt.getYear());
		}

		@Override
		public int periodDays(OffsetDateTime odt) {
			return 1;
		}
	};

	public abstract String format(OffsetDateTime offsetted);

	public String format(Instant instant) {
		return format(instant.atOffset(ZoneOffset.UTC));
	}

	public abstract List<KillStats> stats(List<Integer> types, KillService killservice);

	public abstract RegularTimePeriod of(OffsetDateTime odt);

	public abstract int periodDays(OffsetDateTime odt);

	public static AggregPeriod by(Optional<String> by) {
		String bys = by.orElse("month");
		if (bys.equalsIgnoreCase("week") || bys.equalsIgnoreCase("weekly")) {
			return WEEK;
		}
		if (bys.equalsIgnoreCase("year") || bys.equalsIgnoreCase("yearly")) {
			return YEAR;
		}
		if (bys.equalsIgnoreCase("day") || bys.equalsIgnoreCase("daily")) {
			return DAY;
		}
		return MONTH;
	}
}