package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/** holds a response from a request */
public interface Requested<T> {

	public int getResponseCode();

	public T getOK();

	public String getError();

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
	 * extract the cache expire delay from the headers returned by a connection.
	 * If the headers are missing the data, return 0
	 *
	 * @return the long value of milliseconds after which the cache will expire,
	 *         or System.currentTimeMillis if missing header entries
	 */
	public default long getCacheExpire() {
		List<String> expirel = getHeaders().get("Expires");
		if (expirel == null || expirel.isEmpty()) {
			return 0;
		}
		List<String> datel = getHeaders().get("Date");
		if (datel == null || datel.isEmpty()) {
			return 0;
		}
		synchronized (formatter) {
			return 1000 * ZonedDateTime.parse(expirel.get(0), formatter).toEpochSecond()
					- 1000 * ZonedDateTime.parse(datel.get(0), formatter).toEpochSecond();
		}
	}

	/**
	 * get the number of pages for a request.
	 *
	 * @return the number of pages specified by the header
	 */
	public default int getNbPages() {
		String pages = getHeaders().containsKey("x-pages") ? getHeaders().get("x-pages").get(0)
				: getHeaders().containsKey("X-Pages") ? getHeaders().get("X-Pages").get(0) : null;
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
