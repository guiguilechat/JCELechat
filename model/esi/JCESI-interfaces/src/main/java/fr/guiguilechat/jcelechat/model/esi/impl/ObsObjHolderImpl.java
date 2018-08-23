package fr.guiguilechat.jcelechat.model.esi.impl;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsObjHolder;
import javafx.beans.Observable;
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
	public void follow(Consumer<U> cons) {
		synchronized (underlying) {
			if (waitLatch.getCount() <= 0) {
				cons.accept(get());
			}
			underlying.addListener((o, old, now) -> cons.accept(now));
		}
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

}
