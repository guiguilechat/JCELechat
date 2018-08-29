package fr.guiguilechat.jcelechat.model.jcesi.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsMapHolder;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;

public class ObsMapHolderImpl<K, U> implements ObsMapHolder<K, U> {

	private ObservableMap<K, U> underlying;

	public ObsMapHolderImpl(ObservableMap<K, U> underlying) {
		this.underlying = underlying;
		underlying.addListener(this::mapchangelisten);
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

	protected void mapchangelisten(Change<? extends K, ? extends U> change) {
		waitLatch.countDown();
	}

	@Override
	public Map<K, U> copy() {
		waitData();
		Map<K, U> ret;
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			ret = new HashMap<>(underlying);
		}
		LockWatchDog.BARKER.rel(underlying);
		return ret;
	}

	@Override
	public void follow(MapChangeListener<? super K, ? super U> listener) {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			ObservableMap<K, U> othermap = FXCollections.observableHashMap();
			othermap.addListener(listener);
			othermap.putAll(underlying);
			underlying.addListener(listener);
		}
		LockWatchDog.BARKER.rel(underlying);
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

	@Override
	public void dataReceived() {
		waitLatch.countDown();
	}

	@Override
	public void unfollow(MapChangeListener<? super K, ? super U> change) {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			underlying.removeListener(change);
		}
		LockWatchDog.BARKER.rel(underlying);
	}

}
