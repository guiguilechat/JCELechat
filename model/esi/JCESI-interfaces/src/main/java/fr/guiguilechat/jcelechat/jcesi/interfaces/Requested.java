package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/** holds a response from a request */
public interface Requested<T> {

	public int getResponseCode();

	public T getOK();

	public T getOKOr(T ifnotok);

	public String getError();

	public String getURL();

	public default boolean isOk() {
		return getResponseCode() / 100 == 2;
	}

	public default boolean isRedirect() {
		return getResponseCode() / 100 == 3;
	}

	public default boolean isClientError() {
		return getResponseCode() / 100 == 4;
	}

	public default boolean isServerError() {
		return getResponseCode() / 100 == 5;
	}

	/** return the headers that were returned by the server */
	public Map<String, List<String>> getHeaders();

	/**
	 * formatter for data provided. all calls must be synchronized !
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	/**
	 *
	 * @return the expires header, if exists, else null.
	 */
	public default String getExpires() {
		List<String> list = getHeaders().getOrDefault("Expires", null);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	/**
	 *
	 * @return the Date header, if exists, else null.
	 */
	public default String getDate() {
		List<String> list = getHeaders().getOrDefault("Date", null);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	/**
	 * extract the cache expire delay from the headers returned by a connection.
	 * If the headers are missing the data, return 0
	 *
	 * @return the long value in milliseconds after which the cache will expire,
	 *         or 0 if missing any header entries
	 */
	public default long getCacheExpire() {
		String expire = getExpires();
		String date = getDate();
		if (expire == null || date.isEmpty()) {
			return 0;
		}
		return 1000 * ZonedDateTime.parse(expire, formatter).toEpochSecond()
				- 1000 * ZonedDateTime.parse(date, formatter).toEpochSecond();
	}

	/**
	 * get the number of errors remaining until prevented access. If this is 0 or
	 * lower we must wait {@link #getErrorsReset()} seconds
	 */
	public default int getRemainingErrors() {
		List<String> errorsl = getHeaders().get("X-ESI-Error-Limit-Remain");
		if (errorsl == null) {
			return 0;
		} else {
			return Integer.parseInt(errorsl.get(0));
		}
	}

	/**
	 * get the number of seconds until the error window resets.
	 */
	public default int getErrorsReset() {
		List<String> resetl = getHeaders().get("X-ESI-Error-Limit-Reset");
		if (resetl == null) {
			return 0;
		} else {
			return Integer.parseInt(resetl.get(0));
		}
	}

	/**
	 * get the number of pages for a request.
	 *
	 * @return the number of pages specified by the header
	 */
	public default int getNbPages() {
		List<String> list = getHeaders().get("x-pages");
		if (list == null) {
			list = getHeaders().get("X-Pages");
		}
		String pages = null;
		if (list != null && !list.isEmpty()) {
			pages = list.get(0);
		}
		return pages == null ? 1 : Integer.parseInt(pages);
	}

	public static final String ETAG = "Etag";

	/** get the etag of the header, or null */
	public default String getETag() {
		if (getHeaders().containsKey(ETAG)) {
			return getHeaders().get(ETAG).get(0);
		}
		return null;
	}

}
