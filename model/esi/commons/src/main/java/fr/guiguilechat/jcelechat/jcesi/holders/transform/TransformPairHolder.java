package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.FilteredOut;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.common.ListenableHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * transforms two holders (left and right) into a result.
 *
 * @param <T> resulting type
 * @param <L> observed Left holder type
 * @param <R> observed Right holder type
 */
@Slf4j
public class TransformPairHolder<T, L, R> extends ListenableHolder<T> {

	// left representation

	@Getter(AccessLevel.PROTECTED)
	private final Holder<L> leftSource;

	@Getter(AccessLevel.PROTECTED)
	private boolean leftDirty = false;

	@Getter(AccessLevel.PROTECTED)
	private boolean leftAvail = false;

	@Getter(AccessLevel.PROTECTED)
	private L lastLeft = null;

	// right representation

	@Getter(AccessLevel.PROTECTED)
	private final Holder<R> rightSource;

	@Getter(AccessLevel.PROTECTED)
	private boolean rightDirty = false;

	@Getter(AccessLevel.PROTECTED)
	private boolean rightAvail = false;

	@Getter(AccessLevel.PROTECTED)
	private R lastRight = null;

	//

	@Getter(AccessLevel.PROTECTED)
	private final BiFunction<L, R, T> transformer;

	public TransformPairHolder(Holder<L> leftSource, Holder<R> rightSource, BiFunction<L, R, T> transformer) {
		this.leftSource = leftSource;
		this.rightSource = rightSource;
		this.transformer = transformer;
		leftSource.addListener(getLeftListener());
		rightSource.addListener(getRightListener());
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@SuppressWarnings("unchecked")
	private final Listener<L> leftListener = n -> {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<L> da) {
			leftAvail = true;
			leftDirty = true;
			if (rightAvail) {
				transmit = da.ofParent(this);
			} else {
				transmit = new FilteredOut<>(da.original(), da.toExecute(), this);
			}
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	};

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@SuppressWarnings("unchecked")
	private final Listener<R> rightListener = n -> {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<R> da) {
			rightAvail = true;
			rightDirty = true;
			if (leftAvail) {
				transmit = da.ofParent(this);
			} else {
				transmit = new FilteredOut<>(da.original(), da.toExecute(), this);
			}
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	};

	@Getter(AccessLevel.PROTECTED)
	private T lastValue = null;

	@Getter(AccessLevel.PROTECTED)
	private final CountDownLatch cdl = new CountDownLatch(1);

	@Override
	public boolean isAvailable() {
		return leftAvail && rightAvail;
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
		if (leftDirty || rightDirty) {
			updateValue();
		}
		return lastValue;
	}

	/**
	 * transform the left and right values if at least one is different from
	 * previous ones
	 */
	protected void updateValue() {
		synchronized (this) {
			boolean changed = false;
			if (leftDirty) {
				L l = leftSource.get();
				if (!Objects.equals(l, lastLeft)) {
					changed = true;
					leftDirty = false;
					lastLeft = l;
				}
			}
			if (rightDirty) {
				R r = rightSource.get();
				if (!Objects.equals(r, lastRight)) {
					changed = true;
					rightDirty = false;
					lastRight = r;
				}
			}
			if (changed) {
				lastValue = transformer.apply(lastLeft, lastRight);
			}
		}
	}

}
