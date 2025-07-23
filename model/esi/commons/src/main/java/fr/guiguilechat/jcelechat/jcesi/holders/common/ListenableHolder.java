package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

/**
 * abstract base implementation of listeners
 *
 * @param <T>
 */
public abstract class ListenableHolder<T> implements Holder<T> {

	protected final Set<WeakReference<BiConsumer<Holder<T>, Set<Runnable>>>> listeners = new HashSet<>();

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

	/**
	 * Notify the listeners that new data is available
	 */
	protected void transmitNotification(Set<Runnable> triggered) {
		synchronized (listeners) {
			if (!listeners.isEmpty()) {
				boolean removedListener = false;
				for (WeakReference<BiConsumer<Holder<T>, Set<Runnable>>> wr : listeners) {
					BiConsumer<Holder<T>, Set<Runnable>> l = wr.get();
					if (l != null) {
						l.accept(this, triggered);
					} else {
						removedListener = true;
					}
				}
				if (removedListener) {
					listeners.removeIf(wr -> wr.get() == null);
				}
			}
		}

	}

}
