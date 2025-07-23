package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.ListenableHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * transform the data of a holder into another type
 */
@Slf4j
public class TransformHolder<T, U> extends ListenableHolder<T> implements BiConsumer<Holder<U>, Set<Runnable>> {

	@Getter(AccessLevel.PROTECTED)
	private final Holder<U> source;

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
		source.addAvaibilityListener(this);
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
				U u = source.get();
				if (!Objects.equals(u, lastReceived)) {
					T newValue = transformer.apply(u);
					value = newValue;
					lastReceived = u;
				}
				dirty = false;
			}

		}
	}

	@Override
	public void accept(Holder<U> t, Set<Runnable> u) {
		synchronized (this) {
			available = true;
			dirty = true;
		}
		cdl.countDown();
		transmitNotification(u);
	}

}
