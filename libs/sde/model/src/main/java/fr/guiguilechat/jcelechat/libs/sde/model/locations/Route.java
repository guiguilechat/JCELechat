package fr.guiguilechat.jcelechat.libs.sde.model.locations;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YAMLCacheListener;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.Dijkstra;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps.Safer;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.jumps.Shorter;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time.GatingSpeed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

public class Route {

	@RequiredArgsConstructor
	public static class Cache<T extends Dijkstra<SolarSystem>> extends YAMLCacheListener {

		private final Function<SolarSystem, T> creator;

		protected final Map<SolarSystem, T> cache = new HashMap<>();

		public T from(SolarSystem from) {
			synchronized (cache) {
				return cache.computeIfAbsent(from, creator);
			}
		}

		public T from(String name) {
			return from(SolarSystem.CACHE.of(name));
		}

		public T from(int id) {
			return from(SolarSystem.CACHE.of(id));
		}

		@Override
		public void onSDECacheCleared() {
			synchronized (cache) {
				cache.clear();
			}
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
	private static final GatingSpeed cruiserSpeed = new GatingSpeed(4, 8, 240, 10);

}
