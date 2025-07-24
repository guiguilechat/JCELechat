package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.Listener;

/**
 * abstract base implementation of listeners
 *
 * @param <T>
 */
public abstract class ListenableHolder<T> implements Holder<T> {

	private final Set<WeakReference<Listener<T>>> listeners = new HashSet<>();

	@Override
	public void addListener(Listener<T> listener) {
		synchronized (listeners) {
			listeners.add(new WeakReference<>(listener));
			if (isAvailable()) {
				Set<Runnable> triggered = new HashSet<>();
				listener.accept(new DataAvailable<>(this, triggered, this));
				triggered.forEach(Runnable::run);
			}
		}
	}

	/**
	 * Notify the listeners that new data is available. If null, nothing happens
	 */
	protected void transmitNotification(Notification<T> notif) {
		if (notif == null) {
			return;
		}
		synchronized (listeners) {
			if (!listeners.isEmpty()) {
				boolean removedListener = false;
				for (WeakReference<Listener<T>> wr : listeners) {
					Listener<T> l = wr.get();
					if (l != null) {
						l.accept(notif);
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
