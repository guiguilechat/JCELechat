package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;

/** holds a response from a request */
public interface Requested<T> {

	int getResponseCode();

	T getOK();

	T getOKOr(T ifnotok);

	String getError();

	String getURL();

	default boolean isOk() {
		return getResponseCode() / 100 == 2;
	}

	default boolean isRedirect() {
		return getResponseCode() / 100 == 3;
	}

	default boolean isClientError() {
		return getResponseCode() / 100 == 4;
	}

	default boolean isServerError() {
		return getResponseCode() / 100 == 5;
	}

	/** return the headers that were returned by the server */
	Map<String, List<String>> getHeaders();

	String EXPIRES_PROP = "Expires";

	/**
	 * @return the expires header, if exists, else null.
	 */
	default String getExpires() {
		List<String> list = getHeaders().getOrDefault(EXPIRES_PROP, null);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**
	 * @return the value of expires, converted using
	 *           {@link Instant#getEpochSecond()}.
	 */
	default long getExpiresS() {
		return getExpiresInstant().getEpochSecond();
	}

	/**
	 * @return the value of expires, converted to instant;
	 */
	default Instant getExpiresInstant() {
		String expire = getExpires();
		if (expire == null) {
			return Instant.ofEpochSecond(0);
		}
		return ESIDateTools.headerInstant(expire);
	}

	default void setExpires(Instant expires) {
		if (expires == null) {
			getHeaders().remove(EXPIRES_PROP);
		} else {
			getHeaders().put(EXPIRES_PROP, List.of(ESIDateTools.offsetDateTimeHeader(expires.atOffset(ZoneOffset.UTC))));
		}
	}

	String DATE_PROP = "Date";

	/**
	 * @return the Date header, if exists, else null.
	 */
	default String getDate() {
		List<String> list = getHeaders().getOrDefault(DATE_PROP, null);
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	/**
	 * @return the Date header converted to Instant, if exists.
	 */
	default Instant getDateInstant() {
		String date = getDate();
		if (date == null) {
			return null;
		}
		return ESIDateTools.headerInstant(date);
	}

	/**
	 * @return The date header converted to instant, if exists, or now.
	 */
	default Instant getDateOrNow() {
		return Objects.requireNonNullElseGet(getDateInstant(), Instant::now);
	}

	/**
	 * extract the cache expire delay from the headers returned by a connection.
	 * If the headers are missing the data, return 0
	 *
	 * @return the long value in milliseconds after which the cache will expire,
	 *           or 0 if missing any header entries
	 */
	default long getCacheExpire() {
		String expire = getExpires();
		String date = getDate();
		if (expire == null || date == null || date.isEmpty()) {
			return 0;
		}
		return 1000 * ESIDateTools.headerEpochSeconds(expire)
				- 1000 * ESIDateTools.headerEpochSeconds(date);
	}

	/**
	 * * @return Last-Modified header, or null if absent
	 */
	default String getLastModified() {
		List<String> list = getHeaders().getOrDefault("Last-Modified", null);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	default Instant getLastModifiedInstant() {
		String lastmodified = getLastModified();
		if (lastmodified == null) {
			return Instant.ofEpochMilli(0);
		}
		return ESIDateTools.headerInstant(lastmodified);
	}

	/**
	 * get the number of errors remaining until prevented access. If this is 0 or
	 * lower we must wait {@link #getErrorsReset()} seconds
	 */
	default int getRemainingErrors() {
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
	default int getErrorsReset() {
		List<String> resetl = getHeaders().get("X-Esi-Error-Limit-Reset");
		if (resetl == null) {
			System.err.println("no errors limit reset headers, existing are : " + getHeaders().keySet());
			return 0;
		} else {
			return Integer.parseInt(resetl.get(0));
		}
	}

	/**
	 * @return the moment we can start fetching resource again.
	 */
	default Instant getErrorsResetInstant() {
		return getDateInstant().plusSeconds(getErrorsReset());
	}

	/**
	 * get the number of pages for a request.
	 *
	 * @return the number of pages specified by the header
	 */
	default int getNbPages() {
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

	String ETAG = "Etag";

	/** get the etag of the header, or null */
	default String getETag() {
		if (getHeaders().containsKey(ETAG)) {
			return getHeaders().get(ETAG).get(0);
		}
		return null;
	}

	/**
	 * creates a new requested that has same attributes except the body is
	 * transformed.
	 *
	 * @param <U>    the new type of the body
	 * @param mapper to transform the body
	 * @return a copy of this with the body replaced by the transformed one.
	 */
	<U> Requested<U> mapBody(Function<T, U> mapper);

	/**
	 * creates a new requested that has same attributes except the headers is
	 * transformed.
	 *
	 * @param mapper to transform the headers
	 * @return a copy of this with the headers replaced by the transformed one.
	 */
	Requested<T> mapHeaders(Function<Map<String, List<String>>, Map<String, List<String>>> mapper);

	/**
	 * creates a new requested that has same attributes except the expires header is
	 * transformed.
	 *
	 * @param mapper to transform the Instant expires
	 * @return a copy of this with the expires header replaced by the transformed
	 *           one.
	 */
	default Requested<T> mapExpires(Function<Instant, Instant> mapper) {
		return mapHeaders(headers -> {
			List<String> list = getHeaders().getOrDefault(EXPIRES_PROP, null);
			String expires = list == null || list.isEmpty() ? null : list.get(0);
			Instant expirei = expires == null ? null : ESIDateTools.headerInstant(expires);
			Instant newExpirei = mapper.apply(expirei);
			Map<String, List<String>> newHeaders = new HashMap<>(headers);
			if (newExpirei == null) {
				newHeaders.remove(EXPIRES_PROP);
			} else {
				newHeaders.put(EXPIRES_PROP, List.of(ESIDateTools.offsetDateTimeHeader(newExpirei.atOffset(ZoneOffset.UTC))));
			}
			return newHeaders;
		});
	}

}
