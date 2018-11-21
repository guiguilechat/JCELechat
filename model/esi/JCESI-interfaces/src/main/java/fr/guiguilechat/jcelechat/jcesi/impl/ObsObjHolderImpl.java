package fr.guiguilechat.jcelechat.jcesi.impl;

import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsObjHolderImpl<U> implements ObsObjHolder<U> {

	private ObservableValue<U> underlying;

	public ObsObjHolderImpl(ObservableValue<U> underlying) {
		this.underlying = underlying;
		underlying.addListener(this::objchangelisten);
	}

	CountDownLatch waitLatch = new CountDownLatch(1);

	@Override
	public void waitData() {
		try {
			waitLatch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected void objchangelisten(Object o, U old, U now) {
		waitLatch.countDown();
	}

	@Override
	public U get() {
		waitData();
		return underlying.getValue();
	}

	@Override
	public void follow(ChangeListener<U> change) {
		synchronized (underlying) {
			if (waitLatch.getCount() <= 0) {
				change.changed(underlying, null, underlying.getValue());
			}
			underlying.addListener(change);
		}
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

	@Override
	public void unfollow(ChangeListener<U> change) {
		synchronized (underlying) {
			underlying.removeListener(change);
		}
	}

}
