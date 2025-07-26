package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.common.AListenable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * transform the data of a holder into another type.
 * <p>
 * The transformer function should never return null
 * </p>
 */
@Slf4j
public class TransformHolder<T, U> extends AListenable<T> implements Listener<U> {

	@Getter(AccessLevel.PROTECTED)
	private final Holder<U> source;

	/**
	 * must not return null value. A null value means, that new value must be
	 * discarded.
	 */
	@Getter(AccessLevel.PROTECTED)
	private final Function<U, T> transformer;

	@Getter(AccessLevel.PROTECTED)
	private final CountDownLatch cdl = new CountDownLatch(1);

	/**
	 * true as soon as the source has transmitted a notification
	 */
	@Getter
	private boolean available = false;

	/**
	 * true when the source has sent a notification and the value has not been
	 * recomputed
	 */
	@Getter
	private boolean dirty = false;

	@Getter(AccessLevel.PROTECTED)
	private T value = null;

	@Getter(AccessLevel.PROTECTED)
	private U lastReceived = null;

	public TransformHolder(Holder<U> source, Function<U, T> transformer) {
		this.source = source;
		this.transformer = transformer;
		source.addListener(this);
	}

	@Override
	public T get() {
		if (!isAvailable()) {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				log.error("", e);
				throw new RuntimeException(e);
			}
		}
		if (dirty) {
			updateValue();
		}
		return value;
	}

	/**
	 * transform the source's value if different than previous one
	 */
	protected void updateValue() {
		synchronized (this) {
			if (dirty) {
				U u = Objects.requireNonNull(source.get());
				if (lastReceived == null || !Objects.equals(u, lastReceived)) {
					lastReceived = u;
					T newValue = transformer.apply(u);
					value = Objects.requireNonNull(newValue);
				}
				dirty = false;
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void accept(Notification<U> n) {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<U> da) {
			// only add a countdown when not already available.
			if (!available) {
				n.toExecute().add(()->cdl.countDown());
			}
			synchronized (this) {
				available = true;
				dirty = true;
			}
			transmit = da.ofParent(this);
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final Iterable<AListenable<?>> parentHolders = List.of((AListenable<?>) source);

}
