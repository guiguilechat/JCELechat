package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.util.List;

/**
 * reacts to updates, typically to invalidate cache.
 * <p>
 * to use it, define your own sub interface, and create a field
 * with getter to have {@link #getListeners}, eg :
 *
 * <pre>{@code
 * public static interface XListener extends EntityUpdateListener {
 * }
 *
 * @Getter
 * private final Optional&lt;List&lt;XListener>> listeners;
 * }</pre>
 * </p>
 * <p>
 * Then the service that implement your XListener can provide their own cache
 * List to invalidate on update with {@link #getCacheList()}
 * </p>
 */
public interface CacheInvalidator {

	/** triggered when at least an item is updated */
	default void onUpdate() {
	}

	/**
	 * list the caches that should be invalidated on entity update.
	 * <p>
	 * can be implemented with eg
	 *
	 * <pre>{@code
	 * @Getter(lazy = true)
	 * private final List<String> cacheList = List.of(
	 * 		"cache1",
	 * 		"cache2");
	 * }</pre>
	 * </p>
	 */
	default List<String> getCacheList() {
		return List.of();
	}
}