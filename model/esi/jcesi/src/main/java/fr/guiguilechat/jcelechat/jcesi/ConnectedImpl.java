package fr.guiguilechat.jcelechat.jcesi;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.guiguilechat.jcelechat.jcesi.request.impl.RequestedImpl;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.ISwaggerCacheHelper.Pausable;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.ITransfer;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.ESIMeta;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import fr.lelouet.tools.holders.interfaces.numbers.BoolHolder;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableSet;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public abstract class ConnectedImpl implements ITransfer {

	public static final String IFNONEMATCH = "If-None-Match";
	public static final String ETAG = "Etag";

	private final static AppUserAgent SELF_USER_AGENT = new AppUserAgent("jcelechat", null, "lechatguigui@gmail.com",
			"https://github.com/guiguilechat/JCELechat/");

	private static final Logger csvLogger = LoggerFactory.getLogger(ConnectedImpl.class.getCanonicalName() + ".csv");

	/** to be called before sending a request */
	public static void logRequest(String method, String url, String transmit,
			Map<String, String> transmitHeaders) {
		transmit = toCSVField(transmit);
		String headersStr = transmitHeaders == null ? "" : toCSVField(transmitHeaders.toString());
		csvLogger.trace("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
				method,
				url,
				"",
				"",
				"",
				"",
				transmit == null ? "" : transmit,
				headersStr);
	}

	public static void logResponse(String method, String url, Integer responseCode, Number durationMs, String error,
			String warning,
			String transmit,
			Map<String, ?> receivedHeaders) {
		error = toCSVField(error);
		warning = toCSVField(warning);
		transmit = toCSVField(transmit);
		String headersStr = receivedHeaders == null ? "" : toCSVField(receivedHeaders.toString());
		if (error != null) {

			csvLogger.error("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
					method,
					url,
					responseCode == null ? "" : responseCode,
					durationMs == null ? "" : durationMs,
					error,
					warning == null ? "" : warning,
					transmit == null ? "" : transmit,
					headersStr);
		} else if (warning != null) {
			csvLogger.warn("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
					method,
					url,
					responseCode == null ? "" : responseCode,
					durationMs == null ? "" : durationMs,
					"",
					warning,
					transmit == null ? "" : transmit,
					headersStr);
		} else {
			csvLogger.debug("{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}",
					method,
					url,
					responseCode == null ? "" : responseCode,
					durationMs == null ? "" : durationMs,
					"",
					"",
					transmit == null ? "" : transmit,
					headersStr);
		}
	}

	/** strips the csv delimiters off */
	static String toCSVField(String source) {
		if (source == null) {
			return null;
		}
		return source
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\t", "\\t");
	}

	private final List<AppUserAgent> userAgents;

	@Getter(lazy = true)
	private final String userAgent = userAgents.stream()
			.collect(
					StringBuilder::new,
					(sb, ua) -> ua.toHeader(sb),
					(s1, s2) -> {
						s1.append(" ").append(s2);
					})
			.toString();

	public ConnectedImpl(List<AppUserAgent> userAgents) {
		this.userAgents = appendSelfUserAgent(userAgents);
	}

	public ConnectedImpl() {
		this(List.of());
	}

	public ConnectedImpl(AppUserAgent... appUserAgent) {
		this(appUserAgent == null ? List.of() : List.of(appUserAgent));
	}

	protected List<AppUserAgent> appendSelfUserAgent(List<AppUserAgent> userAgents) {
		List<AppUserAgent> ret = new ArrayList<>();
		if (userAgents != null) {
			ret.addAll(userAgents);
		}
		ret.add(SELF_USER_AGENT);
		return ret;
	}

	private final static long TIMEOUT_S = 12;

	@Getter(lazy = true)
	private final OkHttpClient client = new OkHttpClient.Builder()
			.addNetworkInterceptor(chain -> chain.proceed(
					chain.request()
							.newBuilder()
							.header("User-Agent", getUserAgent())
							.build()))
//			.addNetworkInterceptor(chain -> {
//				Request request = chain.request();
//				System.err.println("sent headers " + request.headers());
//				return chain.proceed(request);
//			})

			.callTimeout(TIMEOUT_S, TimeUnit.SECONDS)
			.connectTimeout(TIMEOUT_S, TimeUnit.SECONDS)
			.readTimeout(TIMEOUT_S, TimeUnit.SECONDS)
			.writeTimeout(TIMEOUT_S, TimeUnit.SECONDS)
			.build();

	/**
	 * request an url
	 *
	 * @param url            the url to request
	 * @param method         the type of method
	 * @param sendHeaders    the properties to send in the header
	 * @param transmitAsJson the data to send through the connection
	 * @param expectedClass  the class to convert the OK result to
	 * @param retries        optional number of retries on server error.
	 * @return a new response holding the result of the request, or null if
	 *         connection issue
	 */
	protected <T> Requested<T> request(String url, String method, Map<String, String> sendHeaders,
			Map<String, Object> transmitAsJson, Class<T> expectedClass, int... retries) {
		int remainingRetry = 2;
		if (retries != null && retries.length != 0) {
			remainingRetry = Math.max(0, retries[0]);
		}
		if (sendHeaders == null) {
			sendHeaders = new HashMap<>();
		}
		sendHeaders.put(ESIDateTools.COMPATIBILITY_DATE_HEADER, ESIMeta.COMPILED_DATE);
		addConnection(sendHeaders);

		Builder builder = new Request.Builder()
				.url(url);
		sendHeaders.entrySet().forEach(e -> builder.addHeader(e.getKey(), e.getValue()));
		String transmitStr = null;
		if (transmitAsJson != null && !transmitAsJson.isEmpty()) {
			transmitStr = mapToJSON(transmitAsJson);
			RequestBody rb = RequestBody.create(transmitStr, MediaType.parse("application/json; charset=utf-8"));
			builder.setBody$okhttp(rb);
		}
		builder.setMethod$okhttp(method);
		Request req = builder.build();
		Long requestSentTime = null;
		boolean needRetry = false;
		try {
			Response lastResponse = null;
			int lastResponseCode = 0;
			Exception lastException = null;

			// retry to send the data for as many retry, as long as the error is
			// server error type
			do {
				if (lastResponse != null) {
					lastResponse.close();
				}
				logRequest(method, url, transmitStr, sendHeaders);
				requestSentTime = System.currentTimeMillis();
				lastResponse = getClient().newCall(req).execute();
				lastResponseCode = lastResponse.code();
				needRetry = lastResponseCode / 100 == 5;
				if (needRetry) {
					remainingRetry--;
					if (remainingRetry > 0) {
						// the response will be discarded, so we need to log it now.
						String errorMessage = extractErrorMessage(lastResponse, method, lastResponseCode, url, transmitStr);
						long milliseconds = lastResponse.receivedResponseAtMillis() - lastResponse.sentRequestAtMillis();
						logResponse(method, url, lastResponseCode, milliseconds, errorMessage, null, transmitStr,
								lastResponse.headers().toMultimap());
					}
				} else {
					// try with resource to close the response.body at the end.
					try (var body = lastResponse.body()) {
						Map<String, List<String>> headers = lastResponse.headers().toMultimap();
						long milliseconds = lastResponse.receivedResponseAtMillis() - lastResponse.sentRequestAtMillis();
						switch (lastResponseCode) {
						// 2xx ok
						case HttpURLConnection.HTTP_OK:
						case HttpURLConnection.HTTP_CREATED:
						case HttpURLConnection.HTTP_ACCEPTED:
						case HttpURLConnection.HTTP_NOT_AUTHORITATIVE:
						case HttpURLConnection.HTTP_NO_CONTENT:
						case HttpURLConnection.HTTP_RESET:
						case HttpURLConnection.HTTP_PARTIAL:
							String ret = new String(body.bytes());
							logResponse(method, url, lastResponseCode, milliseconds, null, null, transmitStr,
									lastResponse.headers().toMultimap());
							return new RequestedImpl<>(url, lastResponseCode, null, convertJson(ret, expectedClass),
									headers);
						// 304 not modified
						case HttpURLConnection.HTTP_NOT_MODIFIED:
							String date = headers.getOrDefault("Date", List.of("")).get(0);
							String expires = headers.getOrDefault("Expires", List.of("")).get(0);
							if (date.equals(expires)) {
								// if expires=Date we add 20s to avoid CCP bug
								logResponse(method, url, lastResponseCode, milliseconds, null,
										"expires=" + expires + " same as date=" + date,
										transmitStr,
										lastResponse.headers().toMultimap());
								headers = new HashMap<>(headers);
								String newExpiry = ESIDateTools
										.offsetDateTimeHeader(ESIDateTools.headerOffsetDateTime(date).plusSeconds(20));
								headers.put("Expires", List.of(newExpiry));
							} else {
								logResponse(method, url, lastResponseCode, milliseconds, null, null, transmitStr,
										lastResponse.headers().toMultimap());
							}
							return new RequestedImpl<>(url, lastResponseCode, null, null, headers);
						// 4xx client error
						case HttpURLConnection.HTTP_BAD_REQUEST:
						case HttpURLConnection.HTTP_UNAUTHORIZED:
						case HttpURLConnection.HTTP_PAYMENT_REQUIRED:
						case HttpURLConnection.HTTP_FORBIDDEN:
						case HttpURLConnection.HTTP_NOT_FOUND:
						case HttpURLConnection.HTTP_BAD_METHOD:
							// 5xx server error
						case HttpURLConnection.HTTP_INTERNAL_ERROR:
						case HttpURLConnection.HTTP_BAD_GATEWAY:
						case HttpURLConnection.HTTP_UNAVAILABLE:
						case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
						default:
							String errorMessage = extractErrorMessage(lastResponse, method, lastResponseCode, url, transmitStr);
							logResponse(method, url, lastResponseCode, milliseconds, errorMessage, null, transmitStr,
									lastResponse.headers().toMultimap());
							return new RequestedImpl<>(url, lastResponseCode, errorMessage, null, headers);
						}
					} catch (Exception e) {
						lastException = e;
						needRetry = true;
						remainingRetry--;
						log.warn("caught while fetching " + url
								+ (remainingRetry >= 0 ? ", retry=" + remainingRetry : ", aborting"), e);
					}
				}
			} while (needRetry && remainingRetry >= 0);
			// could not receive reponse after retries : return error
			return new RequestedImpl<>(url, HttpURLConnection.HTTP_UNAVAILABLE, lastException.getMessage(), null, new HashMap<>());
		} catch (Exception e) {
			logResponse(method, url, null,
					requestSentTime == null ? null : System.currentTimeMillis() - requestSentTime,
					"[" + e.getClass().getSimpleName() + "]:" + e.getMessage(), null,
					transmitStr, null);
			log.error("while fetching " + url, e);
			return new RequestedImpl<>(url, HttpURLConnection.HTTP_UNAVAILABLE, e.getMessage(), null, new HashMap<>());
		}

	}

	protected String extractErrorMessage(Response response, String method, int responseCode, String url,
			String transmitStr) throws IOException {
		String errorMessage = null;
		if (response.body() != null) {
			errorMessage = response.body().string();
		} else {
			StringBuilder sb = new StringBuilder(
					"[" + method + ":" + responseCode + "]" + url + " data=" + transmitStr + " ");
			if (response.message() != null) {
				sb.append(response.message());
			}
			errorMessage = sb.toString();
		}
		return errorMessage;
	}

	@Override
	public Requested<Void> requestDel(String url, Map<String, String> properties) {
		return request(url, "DELETE", properties, null, null);
	}

	@Override
	public <T> Requested<T> requestGet(String url, Map<String, String> properties, Class<T> expectedClass) {
		Requested<T> ret = request(url, "GET", properties, null, expectedClass);
		if (ret.getResponseCode() / 100 != 2 && ret.getResponseCode() != 304) {
			log.debug("url=" + url + " resp=" + ret.getResponseCode() + " error=" + ret.getError());
		}
		return ret;
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

		Requested<T[]> applied = null;
		for (int retry = 3; retry > 0; retry--) {
			log.debug("calling pages, retry=" + retry + " params=" + parameters);
			applied = resourceAccess.apply(1, parameters);
			RequestedImpl<List<T>> page1 = convertToList(applied);
			if (page1 == null) {
				log.debug("received null for " + applied.getURL());
				return null;
			}
			int nbPages = page1.getNbPages();
			boolean[] mismatch = { false };
			if (page1.isOk() && nbPages > 1) {
				page1.getOK().addAll(fetchPagesFrom2(nbPages, resourceAccess, parameters, page1, mismatch));
			}
			if (!mismatch[0]) {
				if (page1.getResponseCode() != 200 && page1.getResponseCode() != 304) {
					log.debug(page1.getURL() + " request pages received responsecode=" + page1.getResponseCode()
							+ " error="
							+ page1.getError());
				}
				return page1;
			}
			// else we loop again
		}
		if (applied == null) {
			log.debug("returned null for first page");
		} else {
			log.debug("first page returned is " + applied.getURL() + " : " + applied.getResponseCode());
		}
		return null;
	}

	protected <T> List<T> fetchPagesFrom2(int nbPages,
			BiFunction<Integer, Map<String, String>, Requested<T[]>> resourceAccess, Map<String, String> parameters,
			RequestedImpl<List<T>> firstPage, boolean[] mismatch) {
		List<Requested<T[]>> pages = IntStream.rangeClosed(2, nbPages).parallel().mapToObj(page -> {
			Requested<T[]> ret = resourceAccess.apply(page, parameters);
			if (ret.isServerError()) {
				for (int pageretry = 0; ret.isServerError() && pageretry < 2; pageretry++) {
					log.debug(
							"fetching " + ret.getURL() + " again because error " + ret.getResponseCode() + " : "
									+ ret.getError());
					ret = resourceAccess.apply(page, parameters);
				}
			}
			return ret;
		}).toList();
		String firstLastModified = firstPage.getLastModified();
		List<Requested<T[]>> mismatcheds = pages.stream().filter(page -> {
			String pageLastModified = page.getLastModified();
			return firstLastModified != pageLastModified
					&& (firstLastModified == null || !firstLastModified.equals(pageLastModified));
		}).toList();
		if (!mismatcheds.isEmpty()) {
			String firstUrl = firstPage.getURL();
			String message = new StringBuilder("mismatching ")
					.append(mismatcheds.size())
					.append(" lastmodified , first page  is ")
					.append(firstUrl)
					.append(" (")
					.append(firstLastModified)
					.append("), different page is ")
					.append(mismatcheds.get(0).getURL())
					.append(" (")
					.append(mismatcheds.get(0).getLastModified())
					.append(")")

					.toString();
			logResponse("GET", firstUrl, firstPage.getResponseCode(), null, null, message, null,
					firstPage.getHeaders());
			mismatch[0] = true;
		}
		for (Requested<T[]> page : pages) {
			if (!page.isOk()) {
				firstPage.setResponseCode(page.getResponseCode());
				firstPage.setError(page.getError());
			}
		}

		List<T> listret = pages.stream()
				.filter(Requested::isOk)
				.map(Requested::getOK)
				.flatMap(Stream::of).toList();
		return listret;
	}

	protected <T> RequestedImpl<List<T>> convertToList(Requested<T[]> apply) {
		return new RequestedImpl<>(apply.getURL(), apply.getResponseCode(), apply.getError(),
				new ArrayList<>(apply.isOk() && apply.getOK() != null ? List.of(apply.getOK()) : List.of()),
				apply.getHeaders());
	}

	////
	//
	////

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final ObjectMapper mapper = makeMapper();

	protected ObjectMapper makeMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		return mapper;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ObjectWriter writer = new ObjectMapper().writer();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convertJson(String line, Class<? extends T> clazz) {
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
			return mapper().readerFor(clazz).readValue(line);
		} catch (Exception e) {
			log.error("while converting line " + line + "to class" + clazz.getName(), e);
			System.err.println(
					"exception caught while converting line " + line + "to class" + clazz.getName() + " : "
							+ e.getMessage());
			return null;
		}
	}

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
			synchronized (writer()) {
				if (transmit != null && transmit.size() == 1) {
					return writer().writeValueAsString(transmit.values().iterator().next());
				} else {
					return writer().writeValueAsString(transmit);
				}
			}
		} catch (JsonProcessingException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	////
	// scheduling
	////

	/**
	 * shared executor among all esi connections
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final static ScheduledThreadPoolExecutor executor = buildExec();

	/**
	 * build a scheduledexecutor
	 *
	 * @return
	 */
	private final static ScheduledThreadPoolExecutor buildExec() {
		// TODO why set to 200 ? it seems lower value make deadlock
		// we set daemon otherwise the thread will prevent jvm from dying.
		ScheduledThreadPoolExecutor ret = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(500, r -> {
			Thread t = Executors.defaultThreadFactory().newThread(r);
			t.setDaemon(true);
			return t;
		});
		ret.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		ret.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
		return ret;
	}

	/**
	 * fetcher that schedules itself on an executor after each fetch.
	 * <p>
	 * That class must be started. Once started, it will schedule itself after its
	 * execution
	 * </p>
	 * <p>
	 * It can also be paused. Pausing it prevents execution until resume is called.
	 * Paused and started are two different states,
	 * </p>
	 * <p>
	 * this abstract class role is to fetch data on a repeated pattern. The handler
	 * can receive null data in several occasions :
	 * <ul>
	 * <li>the data is not accessible (404)</li>
	 * <li>there are too many errors in the network(repeated 50x)</li>
	 * <li>the scheduler is bound to role it does not have
	 * <li>
	 * </ul>
	 * </p>
	 */
	public abstract class SelfExecutableFetcher<T> implements Runnable, Pausable, Consumer<Object> {

		LinkedList<Object> strongRefs = new LinkedList<>();

		@Override
		public void accept(Object t) {
			strongRefs.add(t);
		}

		protected final Consumer<T> cacheHandler;

		public SelfExecutableFetcher(Consumer<T> cacheHandler) {
			this.cacheHandler = cacheHandler;
		}

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

		public SelfExecutableFetcher<T> stop() {
			if (stop) {
				return this;
			}
			stop = true;
			logState();
			return this;
		}

		public SelfExecutableFetcher<T> start(long delay_ms) {
			if (!stop) {
				return this;
			}
			stop = false;
			if (!paused) {
				schedule(delay_ms);
			}
			logState();
			return this;
		}

		public SelfExecutableFetcher<T> start() {
			return start(0L);
		}

		@Override
		public void pause() {
			if (paused) {
				return;
			}
			paused = true;
			logState();
			cacheHandler.accept(null);
		}

		@Override
		public void resume() {
			if (!paused) {
				return;
			}
			paused = false;
			schedule(0);
		}

		public void schedule(long delay_ms) throws RejectedExecutionException {
			synchronized (executor()) {
				if (!scheduled && !stop && !paused) {
					try {
						executor().schedule(this, delay_ms, TimeUnit.MILLISECONDS);
					} catch (RejectedExecutionException e) {
						log.warn(loggingName + " can't schedule " + this, e);
					} catch (Exception e) {
						log.warn(loggingName + " can't schedule " + this, e);
						throw new UnsupportedOperationException(e);
					}
					scheduled = true;
					logState();
				}
			}
		}

		public String loggingName = "";

		public SelfExecutableFetcher<T> withName(String name) {
			loggingName = name;
			return this;
		}

		protected void logState() {
			log.trace("state of executable " + loggingName + " : " + (stop ? "stopped" : "started") + "|"
					+ (paused ? "paused" : "running") + "|" + (scheduled ? "scheduled" : "unscheduled"));
		}

		private int count_shortdelay = 0;
		private int count_error = 0;

		private String lastEtag = null;
		private long default_wait_ms = 500;

		@Override
		public void run() {
			synchronized (executor()) {
				scheduled = false;
			}
			if (stop) {
				return;
			}
			logState();
			long delay_ms = default_wait_ms;
			try {
				Map<String, String> headerHandler = new HashMap<>();
				if (lastEtag != null) {
					headerHandler.put(IFNONEMATCH, lastEtag);
				}
				Requested<T> res = fetch(headerHandler);
				if (res != null) {
					if (res.getResponseCode() == 420) {
						Integer remaining = res.getRemainingErrors();
						if (remaining != null && remaining < 40) {
							delay_ms = res.getErrorsReset() * default_wait_ms;
						}
					} else {
						// manage the etag
						String etag = res.getETag();
						if (etag != null) {
							if (!etag.equals(lastEtag)) {
								if (res.isOk()) {
									try {
										cacheHandler.accept(res.getOK());
									} catch (Exception e) {
										log.warn("for " + res.getURL() + " resCode=" + res.getResponseCode(), e);
									}
								} else if (res.isClientError() && res.getResponseCode() != 420) {
									log.debug(loggingName + " setting null in cache for request response type "
											+ res.getError());
									cacheHandler.accept(null);
								}
							}
							lastEtag = etag;
						} else if (res.isOk()) {
							lastEtag = res.getETag();
							try {
								cacheHandler.accept(res.getOK());
							} catch (Exception e) {
								log.warn("for " + res.getURL(), e);
							}
						} else if (res.isRedirect() && res.getResponseCode() == 304) {
							lastEtag = res.getETag();
						} else if (res.isClientError() || res.isRedirect()) {
							log.debug(loggingName + " " + res.getError() + " : setting data to null. expire in "
									+ res.getCacheExpire() + "ms");
							try {
								cacheHandler.accept(null);
							} catch (Exception e) {
								log.warn("for " + res.getURL(), e);
							}
						} else {
							log.debug(loggingName + res.getResponseCode() + " : " + res.getError());
						}
						// add a delay to avoid re fetching the data too fast
						delay_ms = res.getCacheExpire() + 500;
						if (res.isServerError() || res.isClientError()) {
							count_error++;
							delay_ms = (long) (5000 * Math.sqrt(count_error));
							log.debug(
									loggingName + " got +" + res.getError() + " error count=" + count_error
											+ " waiting=" + delay_ms);
						} else {
							count_error = 0;
						}
					}
				} else {
					log.debug("return is null for fetch pages " + loggingName);
				}
			} catch (Throwable e) {
				log.warn("while fetching " + loggingName, e);
			} finally {
				if (delay_ms < default_wait_ms) {
					count_shortdelay++;
					delay_ms = count_shortdelay * default_wait_ms;
					log.trace(loggingName + " sleep for (corrected)" + "" + delay_ms / 1000 + "s");
				} else {
					log.trace(loggingName + " sleep for " + "" + delay_ms / 1000 + "s");
					count_shortdelay = 0;
				}
				schedule(delay_ms);
			}
		}

		protected abstract Requested<T> fetch(Map<String, String> parameters);

		/**
		 * bind this retrieval state to the roles that are required by this character
		 *
		 * @param requiredRoles the roles, if not null or empty at least one of them
		 *                      must be acquired by the character to allow retrieval of
		 *                      data.
		 */
		protected void bindToRoles(String[] requiredRoles) {
			if (requiredRoles == null || requiredRoles.length == 0) {
				resume();
			} else {
				BoolHolder hasRoleVar = getRoles()
						.test(set -> Stream.of(requiredRoles).filter(set::contains).findAny().isPresent());
				hasRoleVar.follow(b -> {
					if (b) {
						resume();
					} else {
						pause();
					}
				}, this);
			}
		}
	}

	public abstract SetHolder<String> getRoles();

	@SuppressWarnings("unchecked")
	public static <T> ObservableBooleanValue bindContains(ObservableSet<T> set, T... values) {
		SimpleBooleanProperty ret = new SimpleBooleanProperty(true);
		set.addListener(
				(InvalidationListener) _ -> ret.set(Stream.of(values).filter(set::contains).findAny().isPresent()));
		ret.set(Stream.of(values).filter(set::contains).findAny().isPresent());
		return ret;
	}

	/**
	 * task to fetch an array.
	 *
	 * @param <T> the inner type of the array.
	 */
	public class ArrayCacheUpdaterTask<T> extends SelfExecutableFetcher<List<T>> {

		private final BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher;

		public ArrayCacheUpdaterTask(BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher,
				Consumer<List<T>> cacheHandler) {
			super(cacheHandler);
			this.fetcher = fetcher;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected Requested<List<T>> fetch(Map<String, String> parameters) {
			Requested<List<T>> ret = requestGetPages(fetcher, parameters);
			return ret;
		}
	}

	/**
	 * repeatedly fetch a cache and put the value in the handler. The cache expire
	 * is retrieved when fetching data, and used to schedule next retrieve.
	 *
	 * @param fetcher      the function that actually fetch a page, as an array of
	 *                     T. This function uses a handler as second parameter to
	 *                     store the headers of the resource.
	 * @param cacheHandler the data that consumes the new cache obtained from the
	 *                     fetcher. This should handle a null value in case the data
	 *                     can not be updated anymore (eg because the task is
	 *                     paused, the required roles are no more present, the
	 *                     server is down)
	 * @return a runnable stopper function. Once this function is called, the cache
	 *         will not be fetched anymore, unless of course it was already in the
	 *         fetch function.
	 * @param <T> the type of object the fetched array contains.
	 */
	public <T> SelfExecutableFetcher<List<T>> addFetchCacheArray(String name,
			BiFunction<Integer, Map<String, String>, Requested<T[]>> fetcher, Consumer<List<T>> cacheHandler,
			String... requiredRoles) {
		SelfExecutableFetcher<List<T>> t = new ArrayCacheUpdaterTask<>(fetcher, cacheHandler).withName(name);
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
	 * @param <T> the type of the object to fetch
	 */
	public class ObjectCacheUpdaterTask<T> extends SelfExecutableFetcher<T> {

		private final Function<Map<String, String>, Requested<T>> fetcher;

		public ObjectCacheUpdaterTask(Function<Map<String, String>, Requested<T>> fetcher, Consumer<T> cacheHandler) {
			super(cacheHandler);
			this.fetcher = fetcher;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		@Override
		protected Requested<T> fetch(Map<String, String> parameters) {
			return fetcher.apply(parameters);
		}
	}

	/**
	 * repeatedly fetch a cache and put the value in the handler. The cache expire
	 * is retrieved when fetching data, and used to schedule next retrieve.
	 *
	 * @param fetcher      the function that actually fetch the T. This function
	 *                     uses a handler to store the headers of the resource.
	 * @param cacheHandler the data that consumes the new cache obtained from the
	 *                     fetcher. This should handle a null value in case the data
	 *                     can not be updated anymore (eg because the task is
	 *                     paused, the required roles are no more present, the
	 *                     server is down)
	 * @return a runnable stopper function. Once this function is called, the cache
	 *         will not be fetched anymore, unless of course it was already in the
	 *         fetch function.
	 * @param <T> the type of object that represents the cache.
	 */
	public <T> SelfExecutableFetcher<T> addFetchCacheObject(String name,
			Function<Map<String, String>, Requested<T>> fetcher, Consumer<T> cacheHandler, String... requiredRoles) {
		SelfExecutableFetcher<T> t = new ObjectCacheUpdaterTask<>(fetcher, cacheHandler).withName(name);
		if (requiredRoles != null && requiredRoles.length > 0) {
			t.bindToRoles(requiredRoles);
		}
		t.start();
		return t;
	}

	/** add connection information for the server */
	protected void addConnection(Map<String, String> props) {
	}

}
