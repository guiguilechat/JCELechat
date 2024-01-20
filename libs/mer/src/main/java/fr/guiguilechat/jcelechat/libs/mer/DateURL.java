package fr.guiguilechat.jcelechat.libs.mer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * convert a localdate to corresponding url, from a set of known formatters per
 * date range
 */
public class DateURL {

	public static record DateRangeFormatter(LocalDate fromInclusive, LocalDate toExclusive, DateTimeFormatter format,
			long dayRange) {

		public static DateRangeFormatter ofRange(LocalDate fromInclusive, LocalDate toExclusive,
				String date2urlPattern) {
			if (fromInclusive == null || toExclusive == null) {
				return new DateRangeFormatter(fromInclusive, toExclusive, DateTimeFormatter.ofPattern(date2urlPattern),
						Long.MAX_VALUE);
			}
			if (!fromInclusive.isBefore(toExclusive)) {
				throw new RuntimeException("can't create with range :" + fromInclusive + " - " + toExclusive);
			}
			return new DateRangeFormatter(fromInclusive, toExclusive, DateTimeFormatter.ofPattern(date2urlPattern),
					ChronoUnit.DAYS.between(fromInclusive, toExclusive));
		}

		public static DateRangeFormatter ofMonth(int year, int month, String date2urlPattern) {
			LocalDate fromInclusive = LocalDate.of(year, month, 1);
			LocalDate toExclusiveDate = fromInclusive.plusMonths(1);
			return ofRange(fromInclusive, toExclusiveDate, date2urlPattern);
		}

		public static DateRangeFormatter ofMonthsRange(int year1, int month1, int year2, int month2, String date2urlPattern) {
			LocalDate fromInclusive = LocalDate.of(year1, month1, 1);
			LocalDate toExclusiveDate = LocalDate.of(year2, month2, 1);
			return ofRange(fromInclusive, toExclusiveDate, date2urlPattern);
		}

		public static DateRangeFormatter starting(int year, int month, String date2urlPattern) {
			LocalDate fromInclusive = LocalDate.of(year, month, 1);
			return ofRange(fromInclusive, null, date2urlPattern);
		}

		public static DateRangeFormatter ending(int year, int month, String date2urlPattern) {
			LocalDate toExclusiveDate = LocalDate.of(year, month, 1);
			return ofRange(null, toExclusiveDate, date2urlPattern);
		}

		public boolean accept(LocalDate target) {
			return (fromInclusive == null || !target.isBefore(fromInclusive()))
					&& (toExclusive() == null || target.isBefore(toExclusive()));
		}
	}

	List<DateRangeFormatter> formatters = new ArrayList<>();

	public DateURL with(DateRangeFormatter newFormat) {
		formatters.add(newFormat);
		Collections.sort(formatters, Comparator.comparing(drf -> -drf.dayRange()));
		return this;
	}

	public String format(LocalDate date) {
		return formatters.stream().filter(drf -> drf.accept(date))
				.map(drf -> drf == null ? null : drf.format().format(date)).findFirst().orElse(null);
	}

}