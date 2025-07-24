package fr.guiguilechat.jcelechat.jcesi.holders;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.common.StrongRefStorage;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.BoolTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformHolder;

/**
 * data holder. The data is first absent, then can be updateable.
 * <p>
 * When the data can be updated (or is actually updated for RW holder),
 * registered listeners will receive a notification containing the holder and a
 * Set to add the elements that need to react to that change.
 * </p>
 * <p>
 * Subclasses are named [containedType][operation][sourceType]Holder . For
 * example an implementation that translates a list into Number would be a
 * NumberTranslateListHolder
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
	 * Add a weak listener, that is a listener that can be garbage collected once
	 * not strongly reachable by something else anymore.<br />
	 * This is the method that should be called to transform holders, for
	 * daemon-like trigger use {@link #addListener(BiConsumer)}
	 *
	 * @param listener will be triggered whenever new data is potentially available,
	 *                 and at once if data is already present or available.
	 */
	void addListener(Listener<T> listener);

	default <U> Holder<U> map(Function<T, U> transform) {
		return new TransformHolder<>(this, transform);
	}

	default BoolHolder test(Predicate<T> predicate) {
		return new BoolTransformHolder<>(this, predicate::test);
	}

	/**
	 * add a strongly referenced listener on the new values. Whenever the holder has
	 * available data, it will request to call the consumer for the new value upon
	 * notification.<br />
	 * <p>
	 * This method actually creates a new listener, which is then strongly
	 * referenced by
	 * {@link StrongRefStorage}. If storing it as a Strong reference listener,
	 * instead of a weak one, that would still not prevent it from being elected for
	 * GC if the source this depends on is not also strongly reachable. <br />
	 * For example, if creating a RWHolder[List[T]] and getting its size as a
	 * Holder[Integer], then using a max(size, 5) Holder[Integer], listening to that
	 * max holder would not make the size holder strongly reachable.
	 * </p>
	 *
	 * @param onNewValue called when new value is retrieved
	 */
	default void listen(Consumer<T> onNewValue) {
		Listener<T> listener = n -> {
			if (n instanceof DataAvailable<T> da) {
				da.toExecute().add(
						() -> onNewValue.accept(get()));
			}
		};
		addListener(listener);
		StrongRefStorage.maintain(listener);
	}

}
