package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * A holder that allows to set the value. The listeners are only called when the
 * data is set (as well as when registered, if data is already set)
 *
 * @param <T>
 */
@Slf4j
public class RWHolder<T> extends AListenable<T> {

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
		value = Objects.requireNonNull(newVal);
		available = true;
		cdl.countDown();
		Set<Runnable> triggered = new HashSet<>();
		transmitNotification(new DataAvailable<>(this, triggered, this));
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

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final Iterable<Holder<?>> parentHolders = List.of();

}
