package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.FilteredOut;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.ListHolder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * transforms a listholder of holders of the same type into another type.
 */
@Slf4j
public class TransformListHolder<T, SourceInternal, SourceHolder extends Holder<SourceInternal>>
		extends AListenable<T>
		implements Listener<List<SourceHolder>> {

	@Getter(AccessLevel.PROTECTED)
	private final ListHolder<SourceHolder> source;

	@Getter(AccessLevel.PROTECTED)
	private final Function<List<SourceInternal>, T> transform;

	public TransformListHolder(ListHolder<SourceHolder> source, Function<List<SourceInternal>, T> transform) {
		this.source = source;
		this.transform = transform;
		source.addListener(this);
	}

	@Getter(AccessLevel.PROTECTED)
	private final CountDownLatch cdl = new CountDownLatch(1);

	@Getter
	private boolean available = false;

	private final Map<SourceHolder, Object> addedListeners = new HashMap<>();

	private final Map<SourceHolder, SourceInternal> lastReceived = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public void accept(Notification<List<SourceHolder>> n) {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<List<SourceHolder>> da) {
			n.toExecute().add(this::onListUpdated);
			// don't ever transmit new value when receivng list update
			transmit = new FilteredOut<>(da.original(), da.toExecute(), this);
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	}

	protected void onListUpdated() {
		synchronized (this) {
			List<SourceHolder> newHolders = source.get();

			lastReceived.keySet().retainAll(newHolders);
			dirty = true;
		}

	}

	@Getter
	private boolean dirty = false;

	@Getter(AccessLevel.PROTECTED)
	private T value = null;

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

	protected void updateValue() {

	}

	@Override
	protected Iterable<Holder<?>> parentHolders() {
		List<Holder<?>> ret = new ArrayList<>();
		ret.add(source);
		if (source.isAvailable()) {
			ret.addAll(source.get());
		}
		return ret;
	}

}
