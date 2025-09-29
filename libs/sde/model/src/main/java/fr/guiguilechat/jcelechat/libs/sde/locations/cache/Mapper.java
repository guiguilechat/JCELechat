package fr.guiguilechat.jcelechat.libs.sde.locations.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.StreamSupport;

import fr.guiguilechat.jcelechat.libs.sde.cache.tools.RWLockResource;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YAMLCacheListener;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * The various synchronized methods NEED to be (and so, are) synchronized on
 * write since the {@link ReentrantReadWriteLock} can't upgrade a read to a
 * write without allowing deadlocks. Therefore, if a method CAN create a write
 * (typically a call to the cache that can miss), the the full method must be
 * write locked
 * </p>
 */
@RequiredArgsConstructor
public class Mapper<T, U> extends YAMLCacheListener {

	private final JacksonYamlLHMLoader<T> loader;

	private final BiFunction<Integer, T, U> constructor;

	private final Map<Integer, U> cache = new HashMap<>();

	private final RWLockResource<ReentrantReadWriteLock> lck = new RWLockResource<>(
			new ReentrantReadWriteLock());

	@Override
	public void onSDECacheCleared() {
		try (var _ = lck.writeLock()) {
			cache.clear();
		}
	}

	protected U convertId(int id) {
		T sourced = loader.get(id);
		return sourced == null ? null : constructor.apply(id, sourced);
	}

	public U of(int id) {
		try (var _ = lck.writeLock()) {
			return cache.computeIfAbsent(id, this::convertId);
		}
	}

	public List<U> of(Iterable<Integer> ids) {
		if (ids == null) {
			return List.of();
		}
		try (var _ = lck.writeLock()) {
			return StreamSupport.stream(ids.spliterator(), false).map(this::of).toList();
		}
	}

	public Set<Integer> ids() {
		return loader.load().keySet();
	}

	public void forEachId(IntConsumer onId) {
		try (var _ = lck.readLock()) {
			StreamSupport.stream(ids().spliterator(), false).mapToInt(i -> i).forEach(onId);
		}
	}

	public List<U> all() {
		try (var _ = lck.writeLock()) {
			return StreamSupport.stream(ids().spliterator(), false).map(this::of).toList();
		}
	}

	public void forEachElement(Consumer<U> onElement) {
		try (var _ = lck.writeLock()) {
			StreamSupport.stream(ids().spliterator(), false).map(this::of).forEach(onElement);
		}
	}
}
