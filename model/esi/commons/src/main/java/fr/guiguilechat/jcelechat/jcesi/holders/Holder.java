package fr.guiguilechat.jcelechat.jcesi.holders;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformBoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformHolder;

/**
 * data holder. The data is first absent, then can be updateable.
 * <p>
 * When the data can be updated (or is actually updated for RW holder),
 * registered listeners will receive a notificatiion containing the holder and a
 * Set to add the elements that need to react to that change.
 * </p>
 *
 * @param <T>
 * @param <V>
 */
public interface Holder<T> {

	/**
	 * locks until the data is available, then return it
	 *
	 * @return stored data once available.
	 */
	T get();

	/**
	 * @return true if the data can be obtained from a {@link #get()} without
	 *         locking.
	 */
	boolean isAvailable();

	/**
	 * listens to the data availability change. The listener will be
	 * triggered whenever new data is potentially available, and at once if data is
	 * already present or available.
	 * <p>
	 * Listeners are stored as weak references,
	 * meaning they can be deleted if not reachable by an object strongly
	 * referenced.
	 * </p>
	 *
	 * @param listener
	 */
	void addAvaibilityListener(BiConsumer<Holder<T>, Set<Runnable>> listener);

	default <U> Holder<U> map(Function<T, U> transform) {
		return new TransformHolder<>(this, transform);
	}

	default BoolHolder test(Predicate<T> predicate) {
		return new TransformBoolHolder<>(this, predicate::test);
	}

}
