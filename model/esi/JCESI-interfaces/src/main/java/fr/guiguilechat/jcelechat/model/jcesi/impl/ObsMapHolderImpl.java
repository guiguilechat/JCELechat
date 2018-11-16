package fr.guiguilechat.jcelechat.model.jcesi.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
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

	/**
	 * cleanest way to add listener with correct type. Otherwise just adding
	 * c->waitLatch.countDown() is ambiguous
	 */
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

	/**
	 * create a new observableMap that map each entry in the source to an entry in
	 * the ret. creation and deletion of key are mappecd accordingly.
	 *
	 * @param source
	 * @param mapping
	 * @return
	 */
	public static <K, S, T> ObsMapHolderImpl<K, T> map(ObsMapHolder<K, S> source, Function<S, T> mapping) {
		ObservableMap<K, T> containedTarget = FXCollections.observableHashMap();
		ObsMapHolderImpl<K, T> ret = new ObsMapHolderImpl<>(containedTarget);
		source.follow(c -> {
			if (c.wasRemoved() && !c.wasAdded()) {
				synchronized (containedTarget) {
					containedTarget.remove(c.getKey());
				}
			} else {
				synchronized (containedTarget) {
					containedTarget.put(c.getKey(), mapping.apply(c.getValueAdded()));
				}
			}
		});
		source.onWaitEnd(() -> ret.dataReceived());
		return ret;
	}

	public static <K, V> ObsMapHolderImpl<K, V> toMap(ObsListHolder<V> list, Function<V, K> keyExtractor) {
		ObservableMap<K, V> internal = FXCollections.observableHashMap();
		ObsMapHolderImpl<K, V> ret = new ObsMapHolderImpl<>(internal);
		list.follow(c -> {
			while (c.next()) {
				synchronized (internal) {
					if (c.wasRemoved()) {
						for (V removed : c.getRemoved()) {
							internal.remove(keyExtractor.apply(removed));
						}
					}
					if (c.wasAdded()) {
						for (V added : c.getAddedSubList()) {
							internal.put(keyExtractor.apply(added), added);
						}
					}
				}
			}
		});
		return ret;
	}

}
