package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YAMLCacheListener;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.Dijkstra;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps.Safer;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps.Shorter;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time.GatingSpeed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * routing strategies. The algorithms are based on the the parameters function
 * so they are datasource agnostic
 */
public class Route {

	@RequiredArgsConstructor
	public static class Cache<T extends Dijkstra<SolarSystem>> extends YAMLCacheListener {

		private final Function<SolarSystem, T> creator;

		protected final Map<SolarSystem, T> cachedFrom = new HashMap<>();

		public T from(SolarSystem from) {
			synchronized (cachedFrom) {
				return cachedFrom.computeIfAbsent(from, creator);
			}
		}

		public T from(String name, DataSource datasource) {
			return from(datasource.solarSystems().of(name));
		}

		/**
		 * resolves the solar system from the local cache.
		 *
		 * @param name
		 * @return
		 */
		public T from(String name) {
			return from(name, DataSourceLocalCache.INSTANCE);
		}

		public T from(int id, DataSource datasource) {
			return from(datasource.solarSystems().of(id));
		}

		/**
		 * resolves the solar system from the local cache.
		 *
		 * @param name
		 * @return
		 */
		public T from(int id) {
			return from(id, DataSourceLocalCache.INSTANCE);
		}

		@Override
		public void onSDECacheCleared() {
			synchronized (cachedFrom) {
				cachedFrom.clear();
			}
		}

		public int between(SolarSystem from, SolarSystem to) {
			return from(from).to(to).size();
		}

		public int between(int fromId, int toId) {
			SolarSystem from = SolarSystem.CACHE.of(fromId);
			SolarSystem to = SolarSystem.CACHE.of(toId);
			if (from == null) {
				throw new NullPointerException("can't resolve solar system from id " + fromId);
			}
			if (to == null) {
				throw new NullPointerException("can't resolve solar system from id "+toId);
			}
			return from(from).to(to).size();
		}
	}


	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Cache<Safer> safer = new Cache<Safer>(Safer::new);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final Cache<Shorter> shorter = new Cache<Shorter>(Shorter::new);

	// actually caracal
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final GatingSpeed cruiserSpeed = new GatingSpeed(4, 6, 288, 10);

	// actually providence
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final GatingSpeed freighterSpeed = new GatingSpeed(1.5, 40, 109.4, 10);

}
