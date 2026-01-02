package fr.guiguilechat.jcelechat.libs.sde.model.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.guiguilechat.jcelechat.libs.sde.cache.tools.RWLockResource;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YAMLCacheListener;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlMapIntLoader;
import lombok.RequiredArgsConstructor;

/**
 * map existing sde data into model instances. The internal caches are
 * invalidated on sde update
 * <p>
 * The various synchronized methods NEED to be (and so, are) synchronized on
 * write since the {@link ReentrantReadWriteLock} can't upgrade a read to a
 * write without allowing deadlocks. Therefore, if a method CAN create a write
 * (typically a call to the cache that can miss), the the full method must be
 * write locked
 * </p>
 */
@RequiredArgsConstructor
public class Mapper<T, U> extends YAMLCacheListener implements EntityMap<U> {

	protected final YamlMapIntLoader<T> loader;

	private final BiFunction<Integer, T, U> constructor;

	private final Map<Integer, U> cache = new HashMap<>();

	protected final RWLockResource<ReentrantReadWriteLock> lck = new RWLockResource<>(
			new ReentrantReadWriteLock());

	protected Stream<Runnable> cacheClearers() {
		return Stream.of(() -> {
			synchronized (cache) {
				cache.clear();
			}
		});
	}

	@Override
	public void onSDECacheCleared() {
		try (var _ = lck.writeLock()) {
			cacheClearers().forEach(Runnable::run);
		}
	}

	protected U convertId(int id) {
		T sourced = loader.get(id);
		return sourced == null ? null : constructor.apply(id, sourced);
	}

	@Override
	public U of(int id) {
		var ret = cache.get(id);
		if (ret != null) {
			return ret;
		}
		try (var _ = lck.writeLock()) {
			synchronized (cache) {
				return cache.computeIfAbsent(id, this::convertId);
			}
		}
	}

	@Override
	public List<U> of(Iterable<Integer> ids) {
		if (ids == null) {
			return List.of();
		}
		try (var _ = lck.writeLock()) {
			return StreamSupport.stream(ids.spliterator(), false)
					.map(this::of).toList();
		}
	}

	@Override
	public Set<Integer> ids() {
		return loader.load().keySet();
	}

	public Map<Integer, T> byIds() {
		return loader.load();
	}

	@Override
	public void forEachId(IntConsumer onId) {
		try (var _ = lck.readLock()) {
			StreamSupport.stream(ids().spliterator(), false).mapToInt(i -> i).forEach(onId);
		}
	}

	@Override
	public List<U> all() {
		try (var _ = lck.writeLock()) {
			return StreamSupport.stream(ids().spliterator(), false).map(this::of).toList();
		}
	}

	@Override
	public void forEachElement(Consumer<U> onElement) {
		try (var _ = lck.writeLock()) {
			StreamSupport.stream(ids().spliterator(), false).map(this::of).forEach(onElement);
		}
	}
}
