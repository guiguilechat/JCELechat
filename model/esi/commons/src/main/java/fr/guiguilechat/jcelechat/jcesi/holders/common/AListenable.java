package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.StringHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.StringTransformHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * abstract base implementation of listeners
 *
 * @param <T>
 */
public abstract class AListenable<T> implements Holder<T> {

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final Set<WeakReference<Listener<T>>> listeners = new HashSet<>();

	@Override
	public void addListener(Listener<T> listener) {
		synchronized (listeners()) {
			listeners().add(new WeakReference<>(listener));
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
		synchronized (listeners()) {
			if (!listeners().isEmpty()) {
				boolean removedListener = false;
				for (WeakReference<Listener<T>> wr : listeners()) {
					Listener<T> l = wr.get();
					if (l != null) {
						l.accept(notif);
					} else {
						removedListener = true;
					}
				}
				if (removedListener) {
					listeners().removeIf(wr -> wr.get() == null);
				}
			}
		}
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final Set<Listener<?>> strongReferences = new HashSet<>();

	protected abstract Iterable<Holder<?>> parentHolders();

	@Override
	public void keepAlive(Listener<?> listener) {
		synchronized (listeners()) {
			boolean listens = listeners().stream()
					.filter(wr -> {
						var l = wr.get();
						return l != null && l.equals(listener);
					})
					.findAny().isPresent();
			if (listens) {
				strongReferences().add(listener);
			}
		}
		parentHolders().forEach(h -> h.keepAlive((Listener<?>) this));
	}

	@Override
	public void removeListener(Listener<T> listener) {
		synchronized (listeners()) {
			listeners().removeIf(wr -> wr.get() == null || listener.equals(wr.get()));
			strongReferences().remove(listener);
		}
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final StringHolder string = new StringTransformHolder<>(this, Object::toString);

}
