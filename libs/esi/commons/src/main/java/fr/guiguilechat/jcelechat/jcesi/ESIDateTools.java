package fr.guiguilechat.jcelechat.jcesi;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * tools to manipulate header/field string as dates. Header and field have
 * different format, so as many functions as required.
 * <p>
 * The name of the functions refer to the format they convert from/into.<br />
 * eg a "instantHeader" converts an instant to a string in the header format
 * ;<br />
 * And a "fieldLocalDate" converts a string in the field format (eg from a
 * returned date
 * field) into a localdate
 * </p>
 * <p>
 * localDate and localDateTime are represented at local system zone, while
 * instant are (by definition) at UTC and offsetdatetimes are stored at UTC (as
 * they are received).
 * </p>
 */
public class ESIDateTools {

	//
	// convert between date class
	//

	private static OffsetDateTime offsetDateTime(Instant instant) {
		return instant.atOffset(ZoneOffset.UTC);
	}

	private static Instant instant(OffsetDateTime offsetDateTime) {
		return offsetDateTime.toInstant();
	}

	private static LocalDateTime localDateTime(OffsetDateTime offsetDateTime) {
		return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
	}

	private static LocalDate localDate(OffsetDateTime offsetDateTime) {
		return offsetDateTime.atZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
	}

	//
	// convert from field format to date
	//

	private static final DateTimeFormatter FIELD_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;


	public static OffsetDateTime fieldOffsetDateTime(String date) {
		return FIELD_FORMATTER.parse(date, OffsetDateTime::from);
	}

	/**
	 * converts a timestamp from a returned esi field into an instant
	 */
	public static Instant fieldInstant(String date) {
		return instant(fieldOffsetDateTime(date));
	}

	/**
	 * @param date a date in the field format
	 * @return that date converted to a localdatetime within local offset
	 */
	public static LocalDateTime fieldLocalDateTime(String date) {
		return localDateTime(fieldOffsetDateTime(date));
	}

	/**
	 * @param date a date in the field format
	 * @return that date converted to a localdate within local offset
	 */
	public static LocalDate fieldLocalDate(String date) {
		return localDate(fieldOffsetDateTime(date));
	}

	//
	// convert date to field format
	//

	public static String offsetDateTimeField(OffsetDateTime date) {
		return FIELD_FORMATTER.format(date);
	}

	public static String instantField(Instant date) {
		return offsetDateTimeField(date.atOffset(ZoneOffset.UTC));
	}

	//
	// convert from header format to date
	//

	private static final DateTimeFormatter HEADER_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

	public static OffsetDateTime headerOffsetDateTime(String date) {
		return HEADER_FORMATTER.parse(date, LocalDateTime::from).atOffset(ZoneOffset.UTC);
	}

	public static Instant headerInstant(String date) {
		return instant(headerOffsetDateTime(date));
	}

	public static long headerEpochSeconds(String date) {
		return headerOffsetDateTime(date).toEpochSecond();
	}

	public static LocalDateTime headerLocalDateTime(String date) {
		return localDateTime(headerOffsetDateTime(date));
	}

	public static LocalDate headerLocalDate(String date) {
		return localDate(headerOffsetDateTime(date));
	}

	//
	// convert date to header format
	//

	private static String convertOffsetDateTimeAsRFC1123Date(OffsetDateTime date) {
		return HEADER_FORMATTER.format(date);
	}

	public static String offsetDateTimeHeader(OffsetDateTime date) {
		return convertOffsetDateTimeAsRFC1123Date(date);
	}

	public static String instantHeader(Instant date) {
		return offsetDateTimeField(offsetDateTime(date));
	}

	// the compatibility header

	public static final String COMPATIBILITY_DATE_HEADER = "X-Compatibility-Date";

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final DateTimeFormatter compatibilityDateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

	public static String toCompatibilityHeader(Instant date) {
		if(date==null) {
			return null;
		}
		return date.atOffset(ZoneOffset.UTC).minus(11, ChronoUnit.HOURS).format(compatibilityDateFormatter());
	}

}
