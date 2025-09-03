package fr.guiguilechat.jcelechat.jcesi.transmit;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;

/**
 * result of a request we sent to a server. At least knows of the url and the
 * headers we sent
 */
sealed interface RequestResult<ResponseType> {
	String url();

	Map<String, Object> sentHeaders();
}

/** sending was prevented (not even tried) */
sealed interface RequestPrevented<ResponseType> extends RequestResult<ResponseType> {

}

/**
 * did not send the request as the authorization does not meet the required
 * scopes
 */
record MissingScopes<ResponseType>(String url, Map<String, Object> sentHeaders, Set<Object> requiredScopes)
		implements RequestPrevented<ResponseType> {
}

/**
 * did not send the request as the connection does not meet the required
 * (level) rights, eg corporation roles
 */
record MissingRights<ResponseType>(String url, Map<String, Object> sentHeaders, Object level,
		Set<Object> requiredRights) implements RequestPrevented<ResponseType> {
}

/**
 * there was an exception during transmission. Typically timeout or url invalid
 */
record TransmissionException<ResponseType>(String url, Map<String, Object> sentHeaders, Throwable exception)
		implements RequestResult<ResponseType> {
}

/**
 * we received a response from the server. So a response code, the delay between
 * sending request and receiving full body, and the headers transmitted by the
 * server.
 */
sealed interface RequestResponse<ResponseType> extends RequestResult<ResponseType> {
	int responseCode();

	long delayMs();

	// received headers management

	Map<String, List<String>> receivedHeaders();

	/**
	 * @return first received header matching given name
	 */
	default String firstHeader(String header) {
		List<String> list = receivedHeaders().getOrDefault(header, null);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**
	 * @return converted first received header matching given name, or create one
	 *         from supplier
	 */
	default <H> H firstHeader(String header, Function<String, H> convert, Supplier<H> onMissing) {
		String value = firstHeader(header);
		return value == null ? onMissing.get() : convert.apply(value);
	}

	/**
	 * @return converted first received header matching given name, or default one
	 */
	default <H> H firstHeader(String header, H onMissing, Function<String, H> convert) {
		String value = firstHeader(header);
		return value == null ? onMissing : convert.apply(value);
	}

	/**
	 * @return converted first received header matching given names, or default one
	 */
	default <H> H firstHeader(String[] headers, H onMissing, Function<String, H> convert) {
		for (String header : headers) {
			String value = firstHeader(header);
			if (value != null) {
				return convert.apply(value);
			}
		}
		return onMissing;
	}

	// Expires received header

	String HEADER_EXPIRES = "Expires";

	Instant DEFAULT_EXPIRES = Instant.ofEpochSecond(0);

	/**
	 * @return {@link #HEADER_EXPIRES}, converted to instant
	 */
	default Instant getExpires() {
		return firstHeader(HEADER_EXPIRES, DEFAULT_EXPIRES, ESIDateTools::headerInstant);
	}

	/**
	 * @return {@link #HEADER_EXPIRES}, converted using
	 *         {@link Instant#getEpochSecond()}.
	 */
	default long getExpiresAsEpochSeconds() {
		return getExpires().getEpochSecond();
	}

	// Date received header

	String HEADER_DATE = "Date";

	/**
	 * @return the Date header converted to Instant, if exists.
	 */
	default Instant getDate() {
		return firstHeader(HEADER_DATE, ESIDateTools::headerInstant, () -> null);
	}

	/**
	 * @return The date header converted to instant, if exists, or now.
	 */
	default Instant getDateOrNow() {
		return firstHeader(HEADER_DATE, ESIDateTools::headerInstant, (Supplier<Instant>) Instant::now);
	}

	/**
	 * extract the cache expire delay, in ms, from the headers returned by a
	 * connection.
	 * If the date header is missing, uses now.
	 *
	 * @return value in milliseconds after which the cache will expire, with minimum
	 *         0 value
	 */
	default long cacheExpireMS() {
		return Math.max(getExpires().toEpochMilli() - getDateOrNow().toEpochMilli(), 0L);
	}

	// Last-Modified received header

	String HEADER_LASTMODIFIED = "Last-Modified";

	Instant DEFAULT_LASTMODIFIED = Instant.ofEpochSecond(0);

	/**
	 * * @return Last-Modified header, or epoch(0) if absent
	 */
	default Instant getLastModified() {
		return firstHeader(HEADER_LASTMODIFIED, DEFAULT_LASTMODIFIED, ESIDateTools::headerInstant);
	}

	// Error-limit-remain received header

	String HEADER_ERRORREMAIN = "X-Esi-Error-Limit-Remain";

	/**
	 * get the number of errors remaining until prevented access, defaults 0. If
	 * this is 0 or
	 * lower we must wait {@link #getErrorsReset()} seconds
	 */
	default int getRemainingErrors() {
		return firstHeader(HEADER_ERRORREMAIN, 0, Integer::parseInt);
	}

	// Error-limit-reset received header

	String HEADER_ERRORRESET = "X-Esi-Error-Limit-Reset";

	/**
	 * get the number of seconds until the error window resets. Defaults to 60
	 */
	default int getErrorsReset() {
		return firstHeader(HEADER_ERRORRESET, 60, Integer::parseInt);
	}

	/**
	 * @return the moment we can start fetching resource again.
	 */
	default Instant getErrorsResetInstant() {
		return getDateOrNow().plusSeconds(getErrorsReset());
	}

	// pages headers

	String[] HEADERS_PAGES = { "x-pages", "X-Pages" };

	/**
	 * get the number of pages for a request.
	 *
	 * @return the number of pages specified by the header
	 */
	default int getNbPages() {
		return firstHeader(HEADERS_PAGES, 1, Integer::parseInt);
	}

	// etag header

	String HEADER_ETAG = "Etag";

	/** get the etag of the header, or null */
	default String getETag() {
		return firstHeader(HEADER_ETAG);
	}
}

/**
 * the server responded a 200-like and we decoded the body if non-null.
 *
 * @param <T> body type (use Void for no body)
 */
record Ok<ResponseType>(String url, Map<String, Object> sentHeaders, int responseCode, long delayMs,
		ResponseType response,
		Map<String, List<String>> receivedHeaders) implements RequestResponse<ResponseType> {
}

/**
 * the server responded a 200 but we failed to decode the body
 */
record DecodingError<ResponseType>(String url, Map<String, Object> sentHeaders, int responseCode, long delayMs,
		Class<ResponseType> expectedClass,
		String body, Throwable decodingError,
		Map<String, List<String>> receivedHeaders) implements RequestResponse<ResponseType> {
}

/**
 * 304
 */
record NotModified<ResponseType>(String url, Map<String, Object> sentHeaders, long delayMs,
		Map<String, List<String>> receivedHeaders) implements RequestResponse<ResponseType> {
	@Override
	public int responseCode() {
		return 304;
	}
}

/**
 * 4xx
 */
record RequestError<ResponseType>(String url, Map<String, Object> sentHeaders, int responseCode, long delayMs,
		Map<String, List<String>> receivedHeaders, String message) implements RequestResponse<ResponseType> {

}

/**
 * 5xx
 */
record ServerError<ResponseType>(String url, Map<String, Object> sentHeaders, int responseCode, long delayMs,
		Map<String, List<String>> receivedHeaders, String message) implements RequestResponse<ResponseType> {

}

/**
 * Several requests were performed, and general result in a success.<br />
 * In that case, the url should be one called to identify the success :
 * <ul>
 * <li>
 * For paginated resources, this means the first call add a page information
 * that allowed to fetch the next pages. The URL should be that first one.</li>
 * <li>
 * For multi-part, this means a main element was aggregated from several sub
 * resources. The url should be the one that allowed to identify those parts, or
 * to identify that main element.</li>
 * <li>
 * For retry-on-error, the last successful url should be called, typically if a
 * redirect is received, or alternative url are used.</li>
 * </ul>
 */
record AggregateSuccess<ResponseType>(String url, Map<String, Object> sentHeaders,
		List<RequestResult<ResponseType>> aggregate, ResponseType result)
		implements RequestResult<ResponseType> {

}

/**
 * several requests were performed, but the whole could not result in a success.
 * The errorType can be Void if none shall be supplied.
 */
record AggregateFailed<ResponseType, ErrorType>(String url, Map<String, Object> sentHeaders,
		List<RequestResult<ResponseType>> aggregate, ErrorType error)
		implements RequestResult<ResponseType> {

}
