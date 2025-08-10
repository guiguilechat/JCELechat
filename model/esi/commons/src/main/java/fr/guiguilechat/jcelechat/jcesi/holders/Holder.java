package fr.guiguilechat.jcelechat.jcesi.holders;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.ListHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.ListTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.SetHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.SetTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.*;

/**
 * Main interface. A holder is basically a deferred cache with notification
 * methods. The data stored can never be null.
 * <p>
 * The main methods are : <br />
 * {@link #get()} locks the thread until the data is available, then recompute
 * the data if needed and return it; <br />
 * {@link #addListener(Listener)} that allows to received notifications,
 * including when potentially new data is available.
 * </p>
 * <p>
 * There are two main subclasses : {@link RWHolder}, which provide a setter,
 * and {@link TransformHolder}, which listen to other holders and transform
 * their data into another one.<br />
 * Data-specific sub implementations provide convenience holder methods, eg the
 * {@link StringHolder} provide a {@link StringHolder#length()} method that
 * returns a cached holder whose data is the length of the String one.<br />
 * Subclasses are named [containedType][operation][sourceType]Holder . For
 * example an implementation that translates a list into Number would be a
 * NumberTranslateListHolder
 * </p>
 * <p>
 * Listeners are not supposed to react directly to notifications. Instead, they
 * should add triggers, if needed, in the notification's
 * {@link Notification#toExecute()}
 * set of triggers to be called after all transitive listeners have been
 * notified, only sending appropriate notifications to their own listeners, if
 * any.<br />
 * For example, transform holder typically don't add such a trigger, and
 * only check if they have enough information to make their own data available.
 * Only user-specific listeners should add triggers, this is typically done
 * using {@link #listen(Consumer)}
 * </p>
 * <p>
 * Listeners are not stored using strong references, unless
 * {@link #keepAlive(Listener)} is called, meaning that if the listener is not
 * itself strongly referenced, it may be Garbage Collected (GC) and will not
 * receive any later notification. The method {@link #listen(Consumer)} does
 * strongly store a new listeners that retrieves the new data on update and call
 * the delegate consumer.
 * </p>
 * <p>
 * Since the data stored can't be null, data-specific implementations must
 * always have a default value in case the corresponding one is null.
 * </p>
 *
 * @param <T> Internal class this holder can contain.
 */
public interface Holder<T> {

	/**
	 * locks until the data is available, then return it
	 *
	 * @return stored data once available.
	 */
	T get();

	/**
	 * Mainly used for debugging.
	 *
	 * @return true if the data can be obtained without locking from a
	 *         {@link #get()}.
	 */
	boolean isAvailable();

	/**
	 * Add a weak listener, that is a listener that can be garbage collected once
	 * not strongly reachable by something else anymore.<br />
	 * This is the method that should be called by transformer holders, for
	 * user triggers use {@link #listen(Consumer)}
	 *
	 * @param listener will be triggered later whenever new data is potentially
	 *                 available,
	 *                 and at once if data is already present or available.
	 */
	void addListener(Listener<T> listener);

	/**
	 * keep a listener strongly referenced. This means the listener won't be
	 * eligible for GC unless this already is.<br />
	 * If not already listening to the listener, does nothing.
	 *
	 * @param listener
	 */
	void keepAlive(Listener<?> listener);

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
		keepAlive(listener);
	}

	//
	// convenience methods
	//

	default <U> Holder<U> map(Function<T, U> transform) {
		return new TransformHolder<>(this, transform);
	}

	default BoolHolder test(Predicate<T> predicate) {
		return new BoolTransformHolder<>(this, predicate::test);
	}

	default DoubleHolder mapDouble(ToDoubleFunction<T> transform) {
		return new DoubleTransformHolder<>(this, transform);
	}

	default FloatHolder mapFloat(Function<T, Float> transform) {
		return new FloatTransformHolder<>(this, transform);
	}

	default IntHolder mapInt(ToIntFunction<T> transform) {
		return new IntTransformHolder<>(this, transform::applyAsInt);
	}

	default <U> ListHolder<U> mapList(Function<T, List<U>> transform) {
		return new ListTransformHolder<>(this, transform);
	}

	default LongHolder mapLong(ToLongFunction<T> transform) {
		return new LongTransformHolder<>(this, transform::applyAsLong);
	}

	default <U> SetHolder<U> mapSet(Function<T, Set<U>> transform) {
		return new SetTransformHolder<>(this, transform);
	}

	default StringHolder mapString(Function<T, String> transform) {
		return new StringTransformHolder<>(this, transform);
	}

	default BoolHolder equals(Holder<?> other) {
		return new BoolTransformPairHolder<>(this, other, Objects::equals);
	}

	/**
	 * named string because Object::toString returns String, not stringHolder, so
	 * would be bad override
	 *
	 * @return a (cached) holder containing this as a string
	 */
	StringHolder string();

}
