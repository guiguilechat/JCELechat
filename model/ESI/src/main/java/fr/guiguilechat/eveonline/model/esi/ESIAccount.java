package fr.guiguilechat.eveonline.model.esi;

import java.time.format.DateTimeFormatter;
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
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

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
	 * task to fetch an array.
	 *
	 * @param <T>
	 *          the inner type of the array.
	 */
	public class ArrayCacheUpdaterTask<T> implements Runnable {

		private final BiFunction<Integer, Map<String, List<String>>, T[]> fetcher;

		private final Consumer<Stream<T>> cacheHandler;

		public ArrayCacheUpdaterTask(BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
				Consumer<Stream<T>> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		boolean stop = false;

		@Override
		public void run() {
			if (stop) {
				return;
			}
			long delay_ms = 1000;
			try {
				LongProperty cachedExpire = new SimpleLongProperty();
				Stream<T> arr = ESIConnection.loadPages(
						fetcher,
						cachedExpire::set);
				if (arr != null) {
					cacheHandler.accept(arr);
					delay_ms += cachedExpire.get() - System.currentTimeMillis();
				}
			} catch (Throwable e) {
				logger.warn("while  fetching cache", e);
			} finally {
				exec.schedule(this, Math.max(delay_ms, 0), TimeUnit.MILLISECONDS);
			}
		}

		public void stop() {
			stop = true;
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
	 * @return a runnable stopper function. Once this function is called, the
	 *         cache will not be fetched anymore, unless of course it was already
	 *         in the fetch function.
	 * @param <T>
	 *          the type of object the fetched array contains.
	 */
	public <T> Runnable addFetchCacheArray(BiFunction<Integer, Map<String, List<String>>, T[]> fetcher,
			Consumer<Stream<T>> cacheHandler) {
		ArrayCacheUpdaterTask<T> t = new ArrayCacheUpdaterTask<>(fetcher, cacheHandler);
		exec.schedule(t, 0, TimeUnit.SECONDS);
		return t::stop;
	}

	/**
	 * class to fetch an object.
	 *
	 * @param <T>
	 *          the type fo the object to fetch
	 */
	public class ObjectCacheUpdaterTask<T> implements Runnable {

		private final Function<Map<String, List<String>>, T> fetcher;

		private final Consumer<T> cacheHandler;

		public ObjectCacheUpdaterTask(Function<Map<String, List<String>>, T> fetcher, Consumer<T> cacheHandler) {
			this.fetcher = fetcher;
			this.cacheHandler = cacheHandler;
			if (cacheHandler == null || fetcher == null) {
				throw new NullPointerException();
			}
		}

		boolean stop = false;

		@Override
		public void run() {
			if (stop) {
				return;
			}
			long delay_ms = 1000;
			try {
				Map<String, List<String>> headerHandler = new HashMap<>();
				T res = fetcher.apply(headerHandler);
				if (res != null) {
					delay_ms += ESIConnection.getCacheExpire(headerHandler);
					cacheHandler.accept(res);
				}
			} catch (Throwable e) {
				logger.warn("while  fetching cache", e);
			} finally {
				exec.schedule(this, Math.max(delay_ms, 0), TimeUnit.MILLISECONDS);
			}
		}

		public void stop() {
			stop = true;
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
	 * @return a runnable stopper function. Once this function is called, the
	 *         cache will not be fetched anymore, unless of course it was already
	 *         in the fetch function.
	 * @param <T>
	 *          the type of object that represents the cache.
	 */
	public <T> Runnable addFetchCacheObject(Function<Map<String, List<String>>, T> fetcher, Consumer<T> cacheHandler) {
		ObjectCacheUpdaterTask<T> t = new ObjectCacheUpdaterTask<>(fetcher, cacheHandler);
		exec.schedule(t, 0, TimeUnit.SECONDS);
		return t::stop;
	}

}
