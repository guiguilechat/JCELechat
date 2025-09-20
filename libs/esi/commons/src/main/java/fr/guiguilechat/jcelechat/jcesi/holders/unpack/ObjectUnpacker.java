package fr.guiguilechat.jcelechat.jcesi.holders.unpack;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.Listener;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification;
import fr.guiguilechat.jcelechat.jcesi.holders.Notification.DataAvailable;
import fr.guiguilechat.jcelechat.jcesi.holders.common.AListenable;
import lombok.AccessLevel;
import lombok.Getter;

public class ObjectUnpacker<T, H extends Holder<T>> extends AListenable<T> implements Listener<H> {

	private final Holder<H> source;

	private H lastSource = null;

	public ObjectUnpacker(Holder<H> source) {
		this.source = source;
		source.addListener(this);
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Iterable<Holder<?>> parentHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void accept(Notification<H> n) {
		Notification<T> transmit = switch (n) {
		case DataAvailable<H> da -> {
			n.toExecute().add(this::updateContainer);
			yield da.ofParent(this);
		}
		default -> (Notification<T>) n;
		};
		transmitNotification(transmit);
	}

	protected void updateContainer() {
		H newSource = source.get();
		if (lastSource == null || !lastSource.equals(newSource)) {
			if (lastSource != null) {
				lastSource.removeListener(getSourceListener());
			}
			lastSource = newSource;
			newSource.addListener(getSourceListener());
		}
	}

	protected void onNewElement(Notification<T> n) {
		Notification<T> transmit = switch (n) {
		case DataAvailable<T> da -> {
			yield da.ofParent(this);
		}
		default -> n;
		};
		transmitNotification(transmit);
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final Listener<T> sourceListener = this::onNewElement;

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}
