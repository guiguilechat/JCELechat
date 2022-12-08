package fr.guiguilechat.jcelechat.jcesi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ESITools {

	/** parse an ESI date to UTC offset date. */
	public static OffsetDateTime convertDate(String date) {
		LocalDateTime ret = DateTimeFormatter.ISO_DATE_TIME.parse(date, LocalDateTime::from);
		return ret.atOffset(ZoneOffset.UTC);
	}

	/**
	 * parse an ESI date to a local date time (that is, converted from UTC offset
	 * to local offset)
	 */
	public static LocalDateTime convertLocalDateTime(String date) {
		return convertDate(date).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * parse an ESI date to a local date (that is, converted from UTC offset to
	 * local offset)
	 */
	public static LocalDate convertLocalDate(String date) {
		return convertDate(date).atZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
	}

	public static String formatDate(OffsetDateTime date) {
		return DateTimeFormatter.ISO_DATE_TIME.format(date);
	}

	public static OffsetDateTime convertHeaderDate(String date) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.parse(date, LocalDateTime::from).atOffset(ZoneOffset.UTC);
	}

	public static String formatHeaderDate(OffsetDateTime date) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(date);
	}
}
