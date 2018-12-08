package fr.guiguilechat.jcelechat.jcesi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

import fr.guiguilechat.jcelechat.jcesi.impl.RequestedImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ISwaggerCacheHelper.Pausable;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ITransfer;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableSet;

public abstract class ConnectedImpl implements ITransfer {

	private static final Logger logger = LoggerFactory.getLogger(ConnectedImpl.class);

	public static final String IFNONEMATCH = "If-None-Match";
	public static final String ETAG = "Etag";

	protected void addConnection(Map<String, String> props) {
	}

	/**
	 * request an url
	 *
	 * @param url
	 *          the url to request
	 * @param method
	 *          the type of method
	 * @param properties
	 *          the properties to send in the header
	 * @param transmit
	 *          the data to send through the connection
	 * @param expectedClass
	 *          the class to convert the OK result to
	 * @return a new response holding the result of the request, or null if
	 *         connection issue
	 */
	public <T> Requested<T> request(String url, String method, Map<String, String> properties,
			Map<String, Object> transmit, Class<T> expectedClass) {
		try {
			URL target = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
			con.setRequestMethod(method);
			if (properties == null) {
				properties =new HashMap<>();
			}
			addConnection(properties);
			for (Entry<String, String> e : properties.entrySet()) {
				con.setRequestProperty(e.getKey(), e.getValue());
			}
			if (transmit != null && !transmit.isEmpty()) {
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(mapToJSON(transmit).getBytes(StandardCharsets.UTF_8));
				wr.flush();
				wr.close();
			}
			Map<String, List<String>> headers = con.getHeaderFields();
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
				String ret = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
				return new RequestedImpl<>(url, responseCode, null, convert(ret, expectedClass), headers);
				// 304 not modified
			case HttpsURLConnection.HTTP_NOT_MODIFIED:
				return new RequestedImpl<>(url, responseCode, null, null, headers);
				// 4xx client error
			case HttpsURLConnection.HTTP_BAD_REQUEST:
			case HttpsURLConnection.HTTP_UNAUTHORIZED:
			case HttpsURLConnection.HTTP_PAYMENT_REQUIRED:
			case HttpsURLConnection.HTTP_FORBIDDEN:
			case HttpsURLConnection.HTTP_NOT_FOUND:
			case HttpsURLConnection.HTTP_BAD_METHOD:
				// 5xx server error
			case HttpsURLConnection.HTTP_INTERNAL_ERROR:
			case HttpsURLConnection.HTTP_BAD_GATEWAY:
			case HttpsURLConnection.HTTP_UNAVAILABLE:
			case HttpsURLConnection.HTTP_GATEWAY_TIMEOUT:
				StringBuilder sb = new StringBuilder("[" + method + ":" + responseCode + "]" + url + " data=" + transmit + " ");
				if (con.getErrorStream() != null) {
					new BufferedReader(new InputStreamReader(con.getErrorStream())).lines().forEach(sb::append);
				}
				return new RequestedImpl<>(url, responseCode, sb.toString(), null, headers);
			default:
				return null;
			}
		} catch (Exception e) {
			logger.warn("while getting " + url, e);
			return null;
		}
	}

	@Override
	public Requested<Void> requestDel(String url, Map<String, String> properties) {
		return request(url, "DELETE", properties, null, null);
	}

	@Override
	public <T> Requested<T> requestGet(String url, Map<String, String> properties, Class<T> expectedClass) {
		return request(url, "GET", properties, null, expectedClass);
	}

	@Override
	public <T> Requested<T> requestPost(String url, Map<String, String> properties, Map<String, Object> transmit,
			Class<T> expectedClass) {
		return request(url, "POST", properties, transmit, expectedClass);
	}

	@Override
	public Requested<Void> requestPut(String url, Map<String, String> properties, Map<String, Object> transmit) {
		return request(url, "PUT", properties, transmit, null);
	}

	@Override
	public <T> Requested<List<T>> requestGetPages(
			BiFunction<Integer, Map<String, String>, Requested<T[]>> resourceAccess,
			Map<String, String> parameters) {
		RequestedImpl<List<T>> res = convertToList(resourceAccess.apply(1, parameters));
		if (res == null) {
			return null;
		}
		int nbPages = res.getNbPages();
		if (res.isOk() && nbPages > 1) {
			res.getOK().addAll(IntStream.rangeClosed(2, nbPages).parallel()
					.mapToObj(page -> resourceAccess.apply(page, parameters)).peek(req -> {
						if (!req.isOk()) {
							res.responseCode = req.getResponseCode();
							res.error = req.getError();
						}
					}).filter(Requested::isOk).map(req -> req.getOK()).flatMap(arr -> Stream.of(arr))
					.collect(Collectors.toList()));
		}
		return res;
	}

	protected <T> RequestedImpl<List<T>> convertToList(Requested<T[]> apply) {
		RequestedImpl<List<T>> ret = new RequestedImpl<>(apply.getURL(), apply.getResponseCode(), apply.getError(),
				new ArrayList<>(),
				apply.getHeaders());
		if (apply.isOk() && apply.getOK() != null) {
			ret.getOK().addAll(Arrays.asList(apply.getOK()));
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
		if (clazz == null) {
			return null;
		}
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

	////
	// scheduling
	////

	// TODO why set to 200 ? it seems lower value make deadlock
	// we set daemon otherwise the thread will prevent jvm from dying.
	public final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1000, r -> {
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
		}

		public void schedule(long delay_ms) {
			synchronized (exec) {
				if (!scheduled && !stop && !paused) {
					exec.schedule(this, delay_ms, TimeUnit.MILLISECONDS);
					scheduled = true;
					logState();
				}
			}
		}

		public String loggingName = "";

		public SelfExecutable withName(String name) {
			loggingName = name;
			return this;
		}

		protected void logState() {
			logger.debug("state of executable " + loggingName + " : " + (stop ? "stopped" : "started") + "|"
					+ (paused ? "paused" : "running") + "|" + (scheduled ? "scheduled" : "unscheduled")
					// , new Exception()
					);
		}

		private int count_shortdelay = 0;

		@Override
		public void run() {
			synchronized (exec) {
				scheduled = false;
			}
			if (stop) {
				return;
			}
			logState();
			long delay_ms = 1000;
			try {
				delay_ms = do_execute();
			} catch (Throwable e) {
				logger.warn("while fetching " + loggingName, e);
			} finally {
				if (delay_ms < 500) {
					count_shortdelay++;
					delay_ms = count_shortdelay * 1000;
					logger.trace(loggingName + " sleep for (corrected)"
							+ (delay_ms < 1000 ? delay_ms + "ms" : "" + delay_ms / 1000 + "s"));
				} else {
					logger.trace(loggingName + " sleep for " + (delay_ms < 1000 ? delay_ms + "ms" : "" + delay_ms / 1000 + "s"));
					count_shortdelay = 0;
				}
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

		private final BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher;

		private final Consumer<List<T>> cacheHandler;

		private String lastEtag = null;

		public ArrayCacheUpdaterTask(BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher,
				Consumer<List<T>> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected long do_execute() {
			HashMap<String, String> parameters = new HashMap<>();
			if (lastEtag != null) {
				parameters.put(IFNONEMATCH, lastEtag);
			}
			Requested<List<T>> res = requestGetPages(fetcher, parameters);
			if (res == null) {
				return 0;
			}
			String etag = res.getETag();
			if (etag != null) {
				if (!etag.equals(lastEtag)) {
					cacheHandler.accept(res.getOK());
				}
				lastEtag = etag;
			} else if (res.isOk()) {
				cacheHandler.accept(res.getOK());
			} else {
				logger.debug(res.getError());
			}
			return res.getCacheExpire();
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
	public <T> SelfExecutable addFetchCacheArray(String name,
			BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher,
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

		private final Function<Map<String, String>, Requested<T>> fetcher;

		private final Consumer<T> cacheHandler;

		private String lastEtag = null;

		public ObjectCacheUpdaterTask(Function<Map<String, String>, Requested<T>> fetcher, Consumer<T> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected long do_execute() throws Exception {
			Map<String, String> headerHandler = new HashMap<>();
			if (lastEtag != null) {
				headerHandler.put(IFNONEMATCH, lastEtag);
			}
			Requested<T> res = fetcher.apply(headerHandler);
			if (res == null) {
				return 0;
			} else if (res.isOk()) {
				lastEtag = res.getETag();
				cacheHandler.accept(res.getOK());
			} else if (res.isRedirect() && res.getResponseCode() == 304) {
				lastEtag = res.getETag();
			} else {
				logger.debug("" + res.getResponseCode() + " : " + res.getError());
			}
			return res.getCacheExpire();
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
	public <T> SelfExecutable addFetchCacheObject(String name, Function<Map<String, String>, Requested<T>> fetcher,
			Consumer<T> cacheHandler, String... requiredRoles) {
		SelfExecutable t = new ObjectCacheUpdaterTask<>(fetcher, cacheHandler).withName(name);
		if (requiredRoles != null && requiredRoles.length > 0) {
			t.bindToRoles(requiredRoles);
		}
		t.start();
		return t;
	}

}
