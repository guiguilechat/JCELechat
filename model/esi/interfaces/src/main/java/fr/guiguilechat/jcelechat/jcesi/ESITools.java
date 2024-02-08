package fr.guiguilechat.jcelechat.jcesi;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ESITools {

	//
	// convert from/to fields dates
	//

	/**
	 * @param date a date in format {@link DateTimeFormatter#ISO_DATE_TIME}
	 * @return that date at offset UTC.
	 */
	private static OffsetDateTime convertISODateTimeAsOffsetDateTime(String date) {
		return DateTimeFormatter.ISO_DATE_TIME.parse(date, OffsetDateTime::from);
	}

	public static OffsetDateTime fieldOffsetDateTime(String date) {
		return convertISODateTimeAsOffsetDateTime(date);
	}

	public static Instant fieldInstant(String date) {
		return convertISODateTimeAsOffsetDateTime(date).toInstant();
	}

	public static String offsetDateTimeField(OffsetDateTime date) {
		return DateTimeFormatter.ISO_DATE_TIME.format(date);
	}

	public static String instantField(Instant date) {
		return offsetDateTimeField(date.atOffset(ZoneOffset.UTC));
	}

	private static LocalDateTime convertISODateAsLocalDateTime(String date) {
		return convertISODateTimeAsOffsetDateTime(date).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * @param date a date in format {@link DateTimeFormatter#ISO_DATE_TIME}
	 * @return that date converted to a localdatetime within local offset
	 */
	public static LocalDateTime fieldLocalDateTime(String date) {
		return convertISODateAsLocalDateTime(date);
	}

	private static LocalDate convertISODateTimeAsLocalDate(String date) {
		return convertISODateTimeAsOffsetDateTime(date).atZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * @param date a date in format {@link DateTimeFormatter#ISO_DATE_TIME}
	 * @return that date converted to a localdate within local offset
	 */
	public static LocalDate fieldLocalDate(String date) {
		return convertISODateTimeAsLocalDate(date);
	}

	//
	// convert to/from header dates
	//

	private static OffsetDateTime convertHeaderOffsetDateTime(String date) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.parse(date, LocalDateTime::from).atOffset(ZoneOffset.UTC);
	}

	public static OffsetDateTime headerOffsetDateTime(String date) {
		return convertHeaderOffsetDateTime(date);
	}

	public static Instant headerInstant(String date) {
		return convertHeaderOffsetDateTime(date).toInstant();
	}

	public static long headerEpochSeconds(String date) {
		return convertHeaderOffsetDateTime(date).toEpochSecond();
	}

	private static String convertOffsetDateTimeAsRFC1123Date(OffsetDateTime date) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(date);
	}

	public static String offsetDateTimeHeader(OffsetDateTime date) {
		return convertOffsetDateTimeAsRFC1123Date(date);
	}
}
