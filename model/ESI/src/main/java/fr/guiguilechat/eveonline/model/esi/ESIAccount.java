package fr.guiguilechat.eveonline.model.esi;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.Corporation;
import fr.guiguilechat.eveonline.model.esi.modeled.EveCharacter;
import fr.guiguilechat.eveonline.model.esi.modeled.Industry;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;
import fr.guiguilechat.eveonline.model.esi.modeled.Names;
import fr.guiguilechat.eveonline.model.esi.modeled.PI;
import fr.guiguilechat.eveonline.model.esi.modeled.Route;
import fr.guiguilechat.eveonline.model.esi.modeled.Universe;
import fr.guiguilechat.eveonline.model.esi.modeled.Verify;
import javafx.beans.InvalidationListener;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableSet;

/**
 * encapsulation of a raw connection to have better modeling
 *
 */
public class ESIAccount {

	private static final Logger logger = LoggerFactory.getLogger(ESIAccount.class);

	/**
	 * formatter for data provided. all calls must be synchronized !
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	public final ESIConnection raw;

	public ESIAccount(ESIConnection raw) {
		this.raw = raw;
		verify = new Verify(raw);
		names = new Names(raw);
	}

	public ESIAccount(String refresh, String base) {
		this(new ESIConnection(refresh, base));
	}

	public static final ESIAccount DISCONNECTED = new ESIAccount(null, null);

	public ESIConnection getConnection() {
		return raw;
	}

	public final EveCharacter character = new EveCharacter(this);

	public final Verify verify;

	public final Names names;

	public final Markets markets = new Markets(this);

	public final Industry industry = new Industry(this);

	public final PI pi = new PI(this);

	public final Route route = new Route(this);

	public final Corporation corporation = new Corporation(this);

	public final Universe universe = new Universe(this);

	public int characterId() {
		return verify.characterID();
	}

	public String characterName() {
		return verify.characterName();
	}

	@Override
	public int hashCode() {
		return raw.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIAccount.class) {
			return false;
		}
		ESIConnection otherraw = ((ESIAccount) obj).raw;
		return raw == null || otherraw == null ? raw == otherraw : raw.equals(otherraw);
	}

	// execution of data retrieval

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
	public abstract class SelfExecutable implements Runnable {

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

		public void pause() {
			if (paused) {
				return;
			}
			paused = true;
			logState();
		}

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
				ObservableBooleanValue hasRolesVar = bindContains(character.getRoles(), requiredRoles);
				hasRolesVar.addListener((ob, old, now) -> {
					if (now) {
						resume();
					} else {
						pause();
					}
				});

				logger.info("roles required " + Arrays.asList(requiredRoles) + "are present on " + characterName() + " ? "
						+ hasRolesVar.get() + " : roles are " + character.getRoles());
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
			long ret = 1000;
			if (arr != null) {
				ret += cachedExpire.get() - System.currentTimeMillis();
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
			long ret = 1000;
			if (res != null) {
				ret += ESIConnection.getCacheExpire(headerHandler);
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
