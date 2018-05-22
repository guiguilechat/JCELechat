package fr.guiguilechat.eveonline.model.esi.direct;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.LongConsumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.ESITools;
import fr.guiguilechat.eveonline.model.esi.ESITools.AccessToken;
import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;

/**
 * raw access to the esi services using a connection.
 *
 */
public class ESIConnection implements Swagger {

	private static final Logger logger = LoggerFactory.getLogger(ESIConnection.class);

	private final String basicAuth, refreshToken;

	protected AccessToken accessToken = null;

	public ESIConnection(String refreshToken, String basicAuth) {
		this.basicAuth = basicAuth;
		this.refreshToken = refreshToken;
	}

	public boolean isNull() {
		return basicAuth == null || refreshToken == null;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			logger.trace("fetching access token");
			accessToken = ESITools.getAccessToken(basicAuth, refreshToken);
		}
		return accessToken == null ? null : accessToken.token;
	}

	public String getAuthorization() {
		return "Bearer " + getAccessToken();
	}

	private static HashMap<String, Integer> requestedURLs = new HashMap<>();

	public static Map<String, Integer> getRequestedURls() {
		return Collections.unmodifiableMap(requestedURLs);
	}

	/**
	 * get the ascending order of urls requested; that means url most required is
	 * at the end.
	 */
	public static List<Entry<String, Integer>> sortedUrls() {
		ArrayList<Entry<String, Integer>> list = new ArrayList<>(requestedURLs.entrySet());
		Collections.sort(list, (e1, e2) -> e1.getValue() - e2.getValue());
		return list;
	}

	public static void clearRequestedURls() {
		requestedURLs.clear();
	}

	/**
	 * connect to an url and retrieve the result.
	 *
	 * @param url
	 *          the url to fetch
	 * @param method
	 *          the method to connect. must be POST or GET
	 * @param properties
	 *          the properties to transmit in the header
	 * @param transmit
	 *          the data to transmit during the query
	 * @param headerHandler
	 *          the map to store the header received from the server.
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	public static String connect(String url, String method, Map<String, String> properties, String transmit,
			Map<String, List<String>> headerHandler) {
		synchronized (requestedURLs) {
			requestedURLs.put(url, 1 + requestedURLs.getOrDefault(url, 0));
		}
		for (int retry = 10; retry > 0; retry--) {
			try {
				URL target = new URL(url);
				HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
				con.setRequestMethod(method);
				if (properties != null) {
					for (Entry<String, String> e : properties.entrySet()) {
						con.setRequestProperty(e.getKey(), e.getValue());
					}
				}
				if (transmit != null) {
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.write(transmit.getBytes(StandardCharsets.UTF_8));
					wr.flush();
					wr.close();
				}
				if (headerHandler != null) {
					headerHandler.clear();
					headerHandler.putAll(con.getHeaderFields());
				}
				int responseCode = con.getResponseCode();
				switch (responseCode) {
				// 2xx ok
				case HttpsURLConnection.HTTP_OK:
				case HttpsURLConnection.HTTP_CREATED:
				case HttpsURLConnection.HTTP_ACCEPTED:
				case HttpsURLConnection.HTTP_NOT_AUTHORITATIVE:
				case HttpsURLConnection.HTTP_NO_CONTENT:
				case HttpsURLConnection.HTTP_RESET:
				case HttpsURLConnection.HTTP_PARTIAL:
					return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
					// 4xx client error
				case HttpsURLConnection.HTTP_BAD_REQUEST:
				case HttpsURLConnection.HTTP_UNAUTHORIZED:
				case HttpsURLConnection.HTTP_PAYMENT_REQUIRED:
				case HttpsURLConnection.HTTP_FORBIDDEN:
				case HttpsURLConnection.HTTP_NOT_FOUND:
				case HttpsURLConnection.HTTP_BAD_METHOD:
					logConnectError(method, url, transmit, responseCode, con.getErrorStream());
					return null;
					// 5xx server error
				case HttpsURLConnection.HTTP_INTERNAL_ERROR:
				case HttpsURLConnection.HTTP_BAD_GATEWAY:
				case HttpsURLConnection.HTTP_UNAVAILABLE:
				case HttpsURLConnection.HTTP_GATEWAY_TIMEOUT:
					logConnectError(method, url, transmit, responseCode, con.getErrorStream());
					break;
				default:
					logConnectError(method, url, transmit, responseCode, con.getErrorStream());
				}
				int remaining = con.getHeaderFieldInt("X-ESI-Error-Limit-Remain", 100);
				// if we are 10 errors from the error rate, we wait until next error
				// window
				if (remaining <= 10) {
					int waitS = con.getHeaderFieldInt("X-ESI-Error-Limit-Reset", 60);
					logger.warn(" " + remaining + " errors remaining, waiting for " + waitS + " s");
					Thread.sleep(1000 * waitS);
				}
			} catch (Exception e) {
				logger.debug("while geting " + url, e);
				return null;
			}
		}
		return null;
	}

	private static void logConnectError(String method, String url, String transmit, int responseCode,
			InputStream errorStream) {
		StringBuilder sb = new StringBuilder("[" + method + ":" + responseCode + "]" + url + " data=" + transmit + " ");
		new BufferedReader(new InputStreamReader(errorStream)).lines().forEach(sb::append);
		logger.warn(sb.toString());

	}

	/**
	 * get an url, using your authorization
	 *
	 * @param url
	 *          the url to fetch
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	@Override
	public String connectGet(String url, boolean connected, Map<String, List<String>> headerHandler) {
		Map<String, String> props;
		if (connected) {
			props = new HashMap<>();
			props.put("Authorization", getAuthorization());
		} else {
			props = Collections.emptyMap();
		}
		return connect(url, "GET", props, null, headerHandler);
	}

	/**
	 * post an url, using your authorization
	 *
	 * @param url
	 *          the url to fetch
	 * @param contentType
	 *          the type of the data transmitted
	 * @param transmit
	 *          the data to transmit during the query
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	public String connectPost(String url, String contentType, String transmit, boolean connected,
			Map<String, List<String>> headerHandler) {
		HashMap<String, String> props = new HashMap<>();
		if (connected) {
			props.put("Authorization", getAuthorization());
		}
		props.put("Content-Type", contentType);
		return connect(url, "POST", props, transmit, headerHandler);
	}

	ObjectWriter ow = new ObjectMapper().writer();

	@Override
	public String connectPost(String url, Map<String, Object> transmit, boolean connected,
			Map<String, List<String>> headerHandler) {
		try {
			// specific hack : if only one thing to transmit, eg ids:[1,2,3], you have
			// to transmit the ids directly.
			// that means "id:1" will be actually transmitted as "1",
			// while "id:1,name:\"lol\"" will be transmited as is
			if (transmit != null && transmit.size() == 1) {
				return connectPost(url, "application/json", ow.writeValueAsString(transmit.values().iterator().next()),
						connected, headerHandler);
			} else {
				return connectPost(url, "application/json", ow.writeValueAsString(transmit), connected, headerHandler);
			}
		} catch (JsonProcessingException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public static class R_Verify {
		public int CharacterID;
		public String CharacterName;
		public String ExpiresOn;
		public String Scopes;
		public String TokenType;
		public String CharacterOwnerHash;
		public String IntellectualProperty;
	}

	public R_Verify verify() {
		return convert(connectGet("https://login.eveonline.com/oauth/verify", true, null), R_Verify.class);
	}

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T convert(String line, Class<? extends T> clazz) {
		if (line == null || line.length() == 0) {
			return null;
		}
		try {
			return mapper.readerFor(clazz).readValue(line);
		} catch (Exception e) {
			throw new UnsupportedOperationException("while converting line " + line + "to class" + clazz.getName(), e);
		}
	}

	@Override
	public int hashCode() {
		return (basicAuth != null ? basicAuth.hashCode() : 0) + (refreshToken != null ? refreshToken.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIConnection.class) {
			return false;
		}
		ESIConnection o = (ESIConnection) obj;
		return (refreshToken == null && o.refreshToken == null
				|| refreshToken != null && refreshToken.equals(o.refreshToken))
				&& (basicAuth == null && o.basicAuth == null || basicAuth != null && basicAuth.equals(o.basicAuth));
	}

	/**
	 * flatten all
	 *
	 * @param o
	 * @return
	 */
	@Override
	public String flatten(Object o) {
		if (o == null) {
			return null;
		}
		if (o.getClass().isArray()) {
			Class<?> ct = o.getClass().getComponentType();
			if (ct.isPrimitive()) {
				if (ct == boolean.class) {
					boolean[] b = (boolean[]) o;
					return IntStream.range(0, b.length - 1).mapToObj(i -> Boolean.toString(b[i]))
							.collect(Collectors.joining(","));
				} else if (ct == char.class) {
					char[] c = (char[]) o;
					return IntStream.range(0, c.length - 1).mapToObj(i -> Character.toString(c[i]))
							.collect(Collectors.joining(","));
				} else if (ct == int.class || ct == short.class || ct == byte.class) {
					return IntStream.of((int[]) o).mapToObj(Integer::toString).collect(Collectors.joining(","));
				} else if (ct == long.class) {
					return LongStream.of((long[]) o).mapToObj(Long::toString).collect(Collectors.joining(","));
				} else if (ct == double.class || ct == float.class) {
					return DoubleStream.of((double[]) o).mapToObj(Double::toString).collect(Collectors.joining(","));
				}
			}
			return Stream.of((Object[]) o).map(Object::toString).collect(Collectors.joining(","));
		} else {
			return o.toString();
		}
	}

	/**
	 * extract the cache expire from the headers returned by a connection. If the
	 * headers are missing the data, return 0
	 *
	 * @param headers
	 * @return the long value of milliseconds at which the cache will expire, or
	 *         System.currentTimeMillis if missing header entries
	 */
	public static long getCacheExpire(Map<String, List<String>> headers) {
		List<String> expirel = headers.get("Expires");
		if (expirel == null || expirel.isEmpty()) {
			return 0;
		}
		List<String> datel = headers.get("Date");
		if (datel == null || datel.isEmpty()) {
			return 0;
		}
		synchronized (ESIAccount.formatter) {
			return 1000 * ZonedDateTime.parse(expirel.get(0), ESIAccount.formatter).toEpochSecond()
					- 1000 * ZonedDateTime.parse(datel.get(0), ESIAccount.formatter).toEpochSecond();
		}
	}

	/**
	 * get the number of pages for a request.
	 *
	 * @param headers
	 * @return
	 */
	public static int getNbPages(Map<String, List<String>> headers) {
		String pages = headers.containsKey("x-pages") ? headers.get("x-pages").get(0)
				: headers.containsKey("X-Pages") ? headers.get("X-Pages").get(0) : null;
				return pages == null ? 1 : Integer.parseInt(pages);
	}

	/**
	 * load the pages for a given resource access.
	 *
	 * @param resourceAccess
	 *          loads given page , and store header values. (page, store->result).
	 *          Must be able to handle null store.
	 * @param cacheExpireStore
	 *          store the cache expiration value, retrieved from the headers of
	 *          the first page. can be null.
	 * @return the stream of values retrieved from the first page and following
	 *         pages. Those values are only fetched on demand, store them using
	 *         collect to avoid delay on iteration.
	 */
	public static <T> Stream<T> loadPages(BiFunction<Integer, Map<String, List<String>>, T[]> resourceAccess,
			LongConsumer cacheExpireStore) {
		Map<String, List<String>> headerHandler = new HashMap<>();
		T[] res = resourceAccess.apply(1, headerHandler);
		if (res == null) {
			return null;
		}
		int nbpages = ESIConnection.getNbPages(headerHandler);
		if (cacheExpireStore != null) {
			long expire = ESIConnection.getCacheExpire(headerHandler);
			cacheExpireStore.accept(System.currentTimeMillis() + expire);
		}
		return Stream.concat(Stream.of(res), IntStream.rangeClosed(2, nbpages).parallel()
				.mapToObj(page -> resourceAccess.apply(page, null)).flatMap(Stream::of));
	}
}
