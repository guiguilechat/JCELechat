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
import lombok.RequiredArgsConstructor;
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

	// events handling.
	// all events are translated to the same update method at the end.

	@SuppressWarnings("unchecked")
	@Override
	public void accept(Notification<List<SourceHolder>> n) {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<List<SourceHolder>> da) {
			n.toExecute().add(this::onAnyUpdate);
			// don't ever transmit new value when receiving list update
			transmit = new FilteredOut<>(da.original(), da.toExecute(), this);
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	}

	@SuppressWarnings("unchecked")
	protected void acceptItemNotification(Notification<SourceInternal> n) {
		Notification<T> transmit = null;
		if (n instanceof DataAvailable<SourceInternal> da) {
			n.toExecute().add(this::onAnyUpdate);
			transmit = new FilteredOut<>(da.original(), da.toExecute(), this);
		} else {
			transmit = (Notification<T>) n;
		}
		transmitNotification(transmit);
	}

	protected void onAnyUpdate() {

	}

	@Getter(AccessLevel.PROTECTED)
	private final CountDownLatch cdl = new CountDownLatch(1);

	// set to true only when the list is fetched AND list's items are also fetched.
	@Getter
	private boolean available = false;

	@RequiredArgsConstructor
	private class ListItemListener implements Listener<SourceInternal> {

		public final SourceHolder holder;

		public boolean avail = false;

		public SourceInternal lastReceived = null;

		public void addListener() {
			holder.addListener(this);
		}

		public void removeListener() {
			holder.removeListener(this);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void accept(Notification<SourceInternal> n) {
			Notification<T> transmit = null;
			if (n instanceof DataAvailable<SourceInternal> da) {
				n.toExecute().add(TransformListHolder.this::updateFromPossibleValues);
				// we received a new value for this item, but we don't know if other items also
				// did
				avail = true;
			} else {
				transmit = (Notification<T>) n;
			}
			// TODO Auto-generated method stub

		}
	}

	/**
	 * called when
	 */
	protected void updateFromPossibleValues() {

	}

	/**
	 * for each element of the list, the last received data
	 */
	private final Map<SourceHolder, ListItemListener> itemsReceivers = new HashMap<>();


	protected void onListUpdated() {
		synchronized (itemsReceivers) {
			List<SourceHolder> newHolders = source.get();
			List<SourceHolder> toRemove = itemsReceivers.keySet().stream().filter(sh -> !newHolders.contains(sh))
					.toList();
			toRemove.forEach(sh -> {
				itemsReceivers.get(sh).removeListener();
				itemsReceivers.remove(sh);
			});

			dirty = true;
		}
	}

	@Getter
	private boolean dirty = false;

	/**
	 * last value computed
	 */
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
		synchronized (itemsReceivers) {
			computeValueIfNeededAndPossible();
		}
		return value;
	}

	/**
	 * compute the new value if each listeners are available and is dirty.
	 * Should be called inside a sync over {@link #itemsReceivers}
	 */
	private boolean computeValueIfNeededAndPossible() {
		if (!dirty) {
			return false;
		}
		List<SourceInternal> items = new ArrayList<>();
		boolean missingItem = false;
		boolean changed = false;
		for (ListItemListener lil : itemsReceivers.values()) {
			if (!lil.avail) {
				missingItem = true;
				break;
			} else {
				SourceInternal item = lil.holder.get();
				if (!item.equals(lil.lastReceived)) {
					changed = true;
					lil.lastReceived = item;
				}
				items.add(item);
			}

		}
		if (missingItem || !changed) {
			return false;
		}

		return true;
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
