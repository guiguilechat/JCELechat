package fr.guiguilechat.jcelechat.model.esi.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsMapHolder;
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
		synchronized (underlying) {
			return new HashMap<>(underlying);
		}
	}

	@Override
	public void follow(MapChangeListener<? super K, ? super U> listener) {
		synchronized (underlying) {
			ObservableMap<K, U> othermap = FXCollections.observableHashMap();
			othermap.addListener(listener);
			othermap.putAll(underlying);
			underlying.addListener(listener);
		}
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

}
