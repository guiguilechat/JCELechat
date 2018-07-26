package fr.guiguilechat.eveonline.model.esi.direct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.tools.JavaFxTools;
import javafx.beans.InvalidationListener;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

/**
 * The cache is dedicated to holding the data from a swagger.
 * <p>
 * The expiration date of the data is retrieved from the web service's returned
 * headers, at which time the cache handles the fetch of new data and stores the
 * new data.
 * </p>
 */
public class Cache extends SwaggerCache<ESIConnection> {

	private static final Logger logger = LoggerFactory.getLogger(Cache.class);

	public Cache(ESIConnection swag) {
		super(swag);
	}

	// execution of data retrieval

	private ObservableSet<String> roles;

	public ObservableSet<String> getRoles() {
		if (roles == null) {
			synchronized (swagger) {
				if (roles == null) {
					roles = JavaFxTools.makeSet(characters.roles(swagger.verify().CharacterID),
							r -> Arrays.asList(r.roles));
				}
			}
		}
		return roles;
	}

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

				logger.info("roles required " + Arrays.asList(requiredRoles) + "are present on "
						+ swagger.verify().CharacterName + " ? "
						+ hasRolesVar.get() + " : roles are " + getRoles());
				if (hasRolesVar.get()) {
					resume();
				} else {
					pause();
				}
			}
		}
	}

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
			List<T> arr = ESIConnection.loadPages(fetcher, cachedExpire::set);
			long ret = 1000 + cachedExpire.get() - System.currentTimeMillis();
			if (arr != null) {
				cacheHandler.accept(arr);
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
	@Override
	public <T> SelfExecutable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
			Consumer<List<T>> cacheHandler, String... requiredRoles) {
		SelfExecutable t = new ArrayCacheUpdaterTask<>(fetcher, cacheHandler)
				.withName(swagger.verify().CharacterName + "." + name);
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
			long ret = 1000 + ESIConnection.getCacheExpire(headerHandler);
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
	@Override
	public <T> SelfExecutable addFetchCacheObject(String name, Function<Map<String, List<String>>, T> fetcher,
			Consumer<T> cacheHandler, String... requiredRoles) {
		SelfExecutable t = new ObjectCacheUpdaterTask<>(fetcher, cacheHandler)
				.withName(swagger.verify().CharacterName + "." + name);
		if (requiredRoles != null && requiredRoles.length > 0) {
			t.bindToRoles(requiredRoles);
		}
		t.start();
		return t;
	}

	/**
	 * apply the existing map to the listener, then register the listener.
	 *
	 * @param map
	 * @param listener
	 */
	public static <U, V> void listen(ObservableMap<U, V> map, MapChangeListener<U, V> listener) {
		synchronized (map) {
			ObservableMap<U, V> other = FXCollections.observableHashMap();
			other.addListener(listener);
			other.putAll(map.entrySet().stream().filter(e -> e.getKey() != null)
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
			map.addListener(listener);
		}
	}

	/**
	 * wait for the map to acquire data
	 *
	 * @param map
	 */
	public static <U, V> void wait(ObservableMap<U, V> map) {
		CountDownLatch latch;
		synchronized (map) {
			if (map.size() != 1 || !map.containsKey(null)) {
				return;
			}
			latch = new CountDownLatch(1);
			map.addListener(new MapChangeListener<U, V>() {

				@Override
				public void onChanged(Change<? extends U, ? extends V> change) {
					map.removeListener(this);
					latch.countDown();
				}

			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	/**
	 * apply the existing list to the listener, then register the listener.
	 *
	 * @param list
	 * @param listener
	 */
	public static <U> void listen(ObservableList<U> list, ListChangeListener<U> listener) {
		synchronized (list) {
			ObservableList<U> other = FXCollections.observableArrayList();
			other.addListener(listener);
			other.addAll(list.stream().filter(item -> item != null).collect(Collectors.toList()));
			list.addListener(listener);
		}
	}

	/**
	 * wait for the list to acquire data
	 *
	 * @param map
	 */
	public static <U> void wait(ObservableList<U> list) {
		CountDownLatch latch;
		synchronized (list) {
			if (list.size() != 1 || list.get(0) != null) {
				return;
			}
			latch = new CountDownLatch(1);
			list.addListener(new ListChangeListener<U>() {

				@Override
				public void onChanged(Change<? extends U> change) {
					list.removeListener(this);
					latch.countDown();
				}

			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	/**
	 * apply the existing item to the listener, then register the listener.
	 *
	 * @param obs
	 * @param listener
	 */
	public static <U> void listen(ObservableValue<U> obs, ChangeListener<U> listener) {
		synchronized (obs) {
			if (obs.getValue() != null) {
				listener.changed(obs, null, obs.getValue());
			}
			obs.addListener(listener);
		}
	}

	/**
	 * wait for the observablevalue to acquire data
	 *
	 * @param map
	 */
	public static <U> void wait(ObservableValue<U> obs) {
		CountDownLatch latch;
		synchronized (obs) {
			if (obs.getValue() != null) {
				return;
			}
			latch = new CountDownLatch(1);
			obs.addListener(new ChangeListener<U>() {

				@Override
				public void changed(ObservableValue<? extends U> observable, U oldValue, U newValue) {
					obs.removeListener(this);
					latch.countDown();
				}

			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}
