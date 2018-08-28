package fr.guiguilechat.jcelechat.jcesi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
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

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ISwaggerCacheHelper.Pausable;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ITransfer;
import javafx.beans.InvalidationListener;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableSet;

public abstract class ConnectedImpl implements ITransfer {

	private static final Logger logger = LoggerFactory.getLogger(ConnectedImpl.class);

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
	 * connect to an url and retrieve the result.<br />
	 * This is a static method, all the code to handle specific options (eg
	 * connection, data type) must be handled before. That's why there is another
	 * connect method that has more code.
	 *
	 * @param url
	 *          the url to fetch
	 * @param method
	 *          the method to connect. must be POST or GET, DELETE or UPDATE
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
					// hack : set the cache data to expire tomorrow
					if (headerHandler != null) {
						synchronized (ESIAccount.formatter) {
							ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
							List<String> dateArr = headerHandler.get("Date");
							if (dateArr == null) {
								headerHandler.put("Date", Arrays.asList(ESIAccount.formatter.format(now)));
							}
							List<String> expArr = headerHandler.get("Expires");
							if (expArr == null) {
								headerHandler.put("Expires", Arrays.asList(ESIAccount.formatter.format(now.plus(Period.ofDays(1)))));
							} else {
								expArr.add(0, ESIAccount.formatter.format(now.plus(Period.ofDays(1))));
							}
							headerHandler.put("hackedHeader", Arrays.asList("true"));
						}
					}
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
				logger.warn("while getting " + url, e);
				return null;
			}
		}
		logger.warn("too many retries, returning null for " + url);
		return null;
	}

	private static void logConnectError(String method, String url, String transmit, int responseCode,
			InputStream errorStream) {
		StringBuilder sb = new StringBuilder("[" + method + ":" + responseCode + "]" + url + " data=" + transmit + " ");
		if (errorStream != null) {
			new BufferedReader(new InputStreamReader(errorStream)).lines().forEach(sb::append);
		}
		logger.warn(sb.toString());
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
	public static <T> List<T> loadPages(BiFunction<Integer, Map<String, List<String>>, T[]> resourceAccess,
			LongConsumer cacheExpireStore) {
		Map<String, List<String>> headerHandler = new HashMap<>();
		T[] res = resourceAccess.apply(1, headerHandler);
		if (cacheExpireStore != null) {
			long expire = ESIConnected.getCacheExpire(headerHandler);
			logger.debug("expiration ms is " + expire + "from " + headerHandler);
			cacheExpireStore.accept(System.currentTimeMillis() + expire);
		}
		if (res == null) {
			return null;
		}
		int nbpages = ESIConnected.getNbPages(headerHandler);
		List<T> ret = Stream.concat(Stream.of(res), IntStream.rangeClosed(2, nbpages).parallel()
				.mapToObj(page -> resourceAccess.apply(page, null)).flatMap(Stream::of)).collect(Collectors.toList());
		if (ret.contains(null)) {
			return null;
		}
		return ret;
	}

	////
	//
	////

	private final ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(String line, Class<? extends T> clazz) {
		try {
			if (line == null || line.length() == 0) {
				if (clazz.isArray()) {
					return (T) Array.newInstance(clazz.getComponentType(), 0);
				} else {
					return null;
				}
			}
			return mapper.readerFor(clazz).readValue(line);
		} catch (Exception e) {
			throw new UnsupportedOperationException("while converting line " + line + "to class" + clazz.getName(), e);
		}
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
	 * handle the connection and translation of data into the proper format
	 */
	public String connect(String url, String method, Map<String, Object> transmit,
			Map<String, List<String>> headerHandler) {
		HashMap<String, String> props = new HashMap<>();
		String datastr = null;
		addConnection(props);
		if (transmit != null) {
			props.put("Content-Type", "application/json");
			datastr = mapToJSON(transmit);
		}
		logger.debug("fetch " + method + " " + url);
		String ret = connect(url, method, props, datastr, headerHandler);
		logger.debug("answered " + method + " " + url);
		return ret;
	}

	protected void addConnection(HashMap<String, String> props) {
	}

	static final ObjectWriter ow = new ObjectMapper().writer();

	/**
	 * translate a map to json attributes.
	 * <p>
	 * There is a specific hack in case the map only contains one value : we then
	 * transmit directly this value.<br />
	 * eg if the data to transmit is {a:b} we only transmit {b}. if the data is
	 * {a:{k1:v1,k2:v2}} we transmit {k1:v1,k2:v2}
	 * </p>
	 *
	 * @param transmit
	 * @return
	 */
	protected static String mapToJSON(Map<String, Object> transmit) {
		try {
			// TODO check if Objectwriter is thread safe.
			// few tests did not show concurrency corruption, but just in case I sync
			// over it. If possible, use a thread-safe OW. If OW is already thread
			// safe, remove this comment.
			synchronized (ow) {
				if (transmit != null && transmit.size() == 1) {
					return ow.writeValueAsString(transmit.values().iterator().next());
				} else {
					return ow.writeValueAsString(transmit);
				}
			}
		} catch (JsonProcessingException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	@Override
	public String connectGet(String url, Map<String, List<String>> headerHandler) {
		return connect(url, "GET", null, headerHandler);
	}

	@Override
	public String connectDel(String url, Map<String, List<String>> headerHandler) {
		return connect(url, "DELETE", null, headerHandler);
	}

	@Override
	public String connectPost(String url, Map<String, Object> transmit,
			Map<String, List<String>> headerHandler) {
		return connect(url, "POST", transmit, headerHandler);
	}

	@Override
	public String connectPut(String url, Map<String, Object> transmit,
			Map<String, List<String>> headerHandler) {
		return connect(url, "PUT", transmit, headerHandler);
	}

	////
	// scheduling
	////

	// we set daemon otherwise the thread will prevent jvm from running.
	public final ScheduledExecutorService exec = Executors.newScheduledThreadPool(8, r -> {
		Thread t = Executors.defaultThreadFactory().newThread(r);
		t.setDaemon(true);
		return t;
	});

	/**
	 * class that uses the executor to schedule itself.
	 * <p>
	 * That class must be started. Once started, it will schedule itself after its
	 * execution
	 * </p>
	 * <p>
	 * It can also be paused. Pausing it prevents execution until resume is
	 * called. Paused and started are two different states,
	 * </p>
	 *
	 */
	public abstract class SelfExecutable implements Runnable, Pausable {

		/**
		 * is true when it is set to execute later on the executor
		 */
		boolean scheduled = false;

		/**
		 * is true when ordered to stop ASAP ; or not started yet
		 */
		boolean stop = true;

		/** set to true to temporary prevent scheduling of this */
		boolean paused = false;

		public void stop() {
			if (stop) {
				return;
			}
			stop = true;
			logState();
		}

		public void start() {
			if (!stop) {
				return;
			}
			stop = false;
			if (!paused) {
				schedule(0);
			}
			logState();
		}

		@Override
		public void pause() {
			if (paused) {
				return;
			}
			paused = true;
			logState();
		}

		@Override
		public void resume() {
			if (!paused) {
				return;
			}
			paused = false;
			schedule(0);
			logState();
		}

		public void schedule(long delay_ms) {
			synchronized (exec) {
				if (!scheduled && !stop && !paused) {
					exec.schedule(this, delay_ms, TimeUnit.MILLISECONDS);
					scheduled = true;
				}
			}
		}

		public String loggingName = "";

		public SelfExecutable withName(String name) {
			loggingName = name;
			return this;
		}

		protected void logState() {
			logger.info("state of executable " + loggingName + " : " + (stop ? "stopped" : "started") + "|"
					+ (paused ? "paused" : "running") + "|" + (scheduled ? "scheduled" : "unscheduled")
					// , new Exception()
					);
		}

		@Override
		public void run() {
			synchronized (exec) {
				scheduled = false;
			}
			if (stop) {
				return;
			}
			long delay_ms = 1000;
			try {
				delay_ms = do_execute();
			} catch (Throwable e) {
				logger.warn("while  fetching cache", e);
			} finally {
				schedule(delay_ms);
			}
		}

		/**
		 * execute the real method and return time before next execution, in ms
		 *
		 * @return
		 */
		protected abstract long do_execute() throws Exception;

		/**
		 * bind this retrieval state to the roles that are required by this
		 * character
		 *
		 * @param requiredRoles
		 *          the roles, if not null or empty at least one of them must be
		 *          acquired by the character to allow retrieval of data.
		 */
		protected void bindToRoles(String[] requiredRoles) {
			if (requiredRoles == null || requiredRoles.length == 0) {
				resume();
			} else {
				ObservableBooleanValue hasRolesVar = bindContains(getRoles(), requiredRoles);
				hasRolesVar.addListener((ob, old, now) -> {
					if (now) {
						resume();
					} else {
						pause();
					}
				});

				if (hasRolesVar.get()) {
					resume();
				} else {
					pause();
				}
			}
		}
	}

	public abstract ObservableSet<String> getRoles();

	@SuppressWarnings("unchecked")
	public static <T> ObservableBooleanValue bindContains(ObservableSet<T> set, T... values) {
		SimpleBooleanProperty ret = new SimpleBooleanProperty(true);
		set.addListener(
				(InvalidationListener) ob -> ret.set(Stream.of(values).filter(set::contains).findAny().isPresent()));
		ret.set(Stream.of(values).filter(set::contains).findAny().isPresent());
		return ret;
	}

	/**
	 * task to fetch an array.
	 *
	 * @param <T>
	 *          the inner type of the array.
	 */
	public class ArrayCacheUpdaterTask<T> extends SelfExecutable {

		private final BiFunction<Integer, Map<String, List<String>>, T[]> fetcher;

		private final Consumer<List<T>> cacheHandler;

		public ArrayCacheUpdaterTask(BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
				Consumer<List<T>> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected long do_execute() {
			LongProperty cachedExpire = new SimpleLongProperty();
			List<T> arr = ESIConnected.loadPages(fetcher, cachedExpire::set);
			long ret = 1000 + cachedExpire.get() - System.currentTimeMillis();
			if (arr != null) {
				cacheHandler.accept(arr);
			}
			return ret;
		}

		@Override
		public void pause() {
			super.pause();
			cacheHandler.accept(Collections.emptyList());
		}
	}

	/**
	 * repeatedly fetch a cache and put the value in the handler. The cache expire
	 * is retrieved when fetching data, and used to schedule next retrieve.
	 *
	 *
	 * @param fetcher
	 *          the function that actually fetch a page, as an array of T. This
	 *          function uses a handler as second parameter to store the headers
	 *          of the resource.
	 * @param cacheHandler
	 *          the data that consumes the new cache obtained from the fetcher.
	 *          This should handle a null value in case the data can not be
	 *          updated anymore (eg because the task is paused, the required roles
	 *          are no more present, the server is down)
	 * @return a runnable stopper function. Once this function is called, the
	 *         cache will not be fetched anymore, unless of course it was already
	 *         in the fetch function.
	 * @param <T>
	 *          the type of object the fetched array contains.
	 */
	public <T> SelfExecutable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
			Consumer<List<T>> cacheHandler, String... requiredRoles) {
		SelfExecutable t = new ArrayCacheUpdaterTask<>(fetcher, cacheHandler).withName(name);
		if (requiredRoles != null && requiredRoles.length > 0) {
			t.bindToRoles(requiredRoles);
		}
		t.start();
		return t;
	}

	public static final String[] NOROLE = {};

	/**
	 * class to fetch an object.
	 *
	 * @param <T>
	 *          the type of the object to fetch
	 */
	public class ObjectCacheUpdaterTask<T> extends SelfExecutable {

		private final Function<Map<String, List<String>>, T> fetcher;

		private final Consumer<T> cacheHandler;

		public ObjectCacheUpdaterTask(Function<Map<String, List<String>>, T> fetcher, Consumer<T> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected long do_execute() throws Exception {
			Map<String, List<String>> headerHandler = new HashMap<>();
			T res = fetcher.apply(headerHandler);
			long ret = 1000 + ESIConnected.getCacheExpire(headerHandler);
			if (res != null) {
				cacheHandler.accept(res);
			}
			return ret;
		}

		@Override
		public void pause() {
			super.pause();
			cacheHandler.accept(null);
		}
	}

	/**
	 * repeatedly fetch a cache and put the value in the handler. The cache expire
	 * is retrieved when fetching data, and used to schedule next retrieve.
	 *
	 *
	 * @param fetcher
	 *          the function that actually fetch the T. This function uses a
	 *          handler to store the headers of the resource.
	 * @param cacheHandler
	 *          the data that consumes the new cache obtained from the fetcher.
	 *          This should handle a null value in case the data can not be
	 *          updated anymore (eg because the task is paused, the required roles
	 *          are no more present, the server is down)
	 * @return a runnable stopper function. Once this function is called, the
	 *         cache will not be fetched anymore, unless of course it was already
	 *         in the fetch function.
	 * @param <T>
	 *          the type of object that represents the cache.
	 */
	public <T> SelfExecutable addFetchCacheObject(String name, Function<Map<String, List<String>>, T> fetcher,
			Consumer<T> cacheHandler, String... requiredRoles) {
		SelfExecutable t = new ObjectCacheUpdaterTask<>(fetcher, cacheHandler).withName(name);
		if (requiredRoles != null && requiredRoles.length > 0) {
			t.bindToRoles(requiredRoles);
		}
		t.start();
		return t;
	}

}
