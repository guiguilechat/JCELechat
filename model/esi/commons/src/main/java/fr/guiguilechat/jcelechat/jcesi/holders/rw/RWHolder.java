package fr.guiguilechat.jcelechat.jcesi.holders.rw;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.ListenableHolder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * A holder that allows to set the value. The listeners are only called when the
 * data is set(as well as when registered, if data is set)
 *
 * @param <T>
 */
@Slf4j
public class RWHolder<T> extends ListenableHolder<T> {

	@Getter
	private boolean available = false;

	private T value = null;

	private final CountDownLatch cdl = new CountDownLatch(1);

	public RWHolder() {
	}

	public RWHolder(T newVal) {
		this();
		set(newVal);
	}

	public void set(T newVal) {
		value = newVal;
		available = true;
		cdl.countDown();
		Set<Runnable> triggered = new HashSet<>();
		transmitNotification(triggered);
		triggered.forEach(Runnable::run);
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
		return value;
	}

	@Override
	public void addAvaibilityListener(BiConsumer<Holder<T>, Set<Runnable>> listener) {
		synchronized (listeners) {
			listeners.add(new WeakReference<>(listener));
			if (isAvailable()) {
				Set<Runnable> triggered = new HashSet<>();
				listener.accept(this, triggered);
				triggered.forEach(Runnable::run);
			}
		}
	}

}
