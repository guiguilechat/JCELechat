package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.ESITools;

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

	public static String EXPIRES_PROP = "Expires";

	/**
	 *
	 * @return the expires header, if exists, else null.
	 */
	public default String getExpires() {
		List<String> list = getHeaders().getOrDefault(EXPIRES_PROP, null);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	/**
	 * @return the value of expires, converted using
	 *           {@link Instant#getEpochSecond()}.
	 */
	public default long getExpiresS() {
		return getExpiresInstant().getEpochSecond();
	}

	/**
	 * @return the value of expires, converted to instant;
	 */
	public default Instant getExpiresInstant() {
		String expire = getExpires();
		if (expire == null) {
			return Instant.ofEpochSecond(0);
		}
		return ESITools.headerInstant(expire);
	}

	public static String DATE_PROP = "Date";

	/**
	 * @return the Date header, if exists, else null.
	 */
	public default String getDate() {
		List<String> list = getHeaders().getOrDefault(DATE_PROP, null);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	/**
	 * @return the Date header converted to Instant, if exists.
	 */
	public default Instant getDateInstant() {
		String date = getDate();
		if (date == null) {
			return null;
		}
		return ESITools.headerInstant(date);
	}

	/**
	 * @return The date header converted to instant, if exists, or now.
	 */
	public default Instant getDateOrNow() {
		return Objects.requireNonNullElseGet(getDateInstant(), Instant::now);
	}

	/**
	 * extract the cache expire delay from the headers returned by a connection.
	 * If the headers are missing the data, return 0
	 *
	 * @return the long value in milliseconds after which the cache will expire,
	 *           or 0 if missing any header entries
	 */
	public default long getCacheExpire() {
		String expire = getExpires();
		String date = getDate();
		if (expire == null || date.isEmpty()) {
			return 0;
		}
		return 1000 * ESITools.headerEpochSeconds(expire)
				- 1000 * ESITools.headerEpochSeconds(date);
	}

	/**
	 * * @return Last-Modified header, or null if absent
	 */
	public default String getLastModified() {
		List<String> list = getHeaders().getOrDefault("Last-Modified", null);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public default Instant getLastModifiedInstant() {
		String lastmodified = getLastModified();
		if (lastmodified == null) {
			return Instant.ofEpochMilli(0);
		}
		return ESITools.headerInstant(lastmodified);
	}

	/**
	 * get the number of errors remaining until prevented access. If this is 0 or
	 * lower we must wait {@link #getErrorsReset()} seconds
	 */
	public default int getRemainingErrors() {
		List<String> errorsl = getHeaders().get("X-Esi-Error-Limit-Remain");
		if (errorsl == null) {
			System.err.println("no error limit remain headers, existing are : " + getHeaders().keySet());
			return 0;
		} else {
			return Integer.parseInt(errorsl.get(0));
		}
	}

	/**
	 * get the number of seconds until the error window resets.
	 */
	public default int getErrorsReset() {
		List<String> resetl = getHeaders().get("X-Esi-Error-Limit-Reset");
		if (resetl == null) {
			System.err.println("no errors limit reset headers, existing are : " + getHeaders().keySet());
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

	public <U> Requested<U> mapBody(Function<T, U> mapper);

	public Requested<T> mapHeaders(Function<Map<String, List<String>>, Map<String, List<String>>> mapper);

}
