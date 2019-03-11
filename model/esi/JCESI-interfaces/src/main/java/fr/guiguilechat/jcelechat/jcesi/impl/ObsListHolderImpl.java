package fr.guiguilechat.jcelechat.jcesi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

public class ObsListHolderImpl<U> implements ObsListHolder<U> {

	private ObservableList<U> underlying;

	public ObsListHolderImpl(ObservableList<U> underlying) {
		this.underlying = underlying;
		underlying.addListener(this::listchangelisten);
	}

	CountDownLatch waitLatch = new CountDownLatch(1);

	private ArrayList<Consumer<List<U>>> receiveListeners;

	@Override
	public void waitData() {
		try {
			waitLatch.await();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	protected void listchangelisten(Change<? extends U> change) {
		waitLatch.countDown();
	}

	@Override
	public List<U> copy() {
		waitData();
		LockWatchDog.BARKER.tak(underlying);
		List<U> ret;
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			ret = new ArrayList<>(underlying);
		}
		LockWatchDog.BARKER.rel(underlying);
		return ret;
	}

	@Override
	public void apply(BiConsumer<Integer, U> cons) {
		waitData();
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			for (int i = 0; i < underlying.size(); i++) {
				cons.accept(i, underlying.get(i));
			}
		}
		LockWatchDog.BARKER.rel(underlying);
	}

	@Override
	public void follow(ListChangeListener<? super U> listener) {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			ObservableList<U> otherlist = FXCollections.observableArrayList();
			otherlist.addListener(listener);
			otherlist.addAll(underlying);
			underlying.addListener(listener);
		}
		LockWatchDog.BARKER.rel(underlying);
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

	@Override
	public void addReceivedListener(Consumer<List<U>> callback) {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			if (receiveListeners == null) {
				receiveListeners = new ArrayList<>();
			}
			receiveListeners.add(callback);
			if (waitLatch.getCount() == 0) {
				callback.accept(underlying);
			}
		}
		LockWatchDog.BARKER.rel(underlying);
	}

	@Override
	public boolean remReceivedListener(Consumer<List<U>> callback) {
		synchronized (underlying) {
			return receiveListeners.remove(callback);
		}
	}

	@Override
	public void dataReceived() {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			waitLatch.countDown();
			if (receiveListeners != null) {
				List<U> consumed = underlying;
				for (Consumer<List<U>> r : receiveListeners) {
					r.accept(consumed);
				}
			}
		}
		LockWatchDog.BARKER.rel(underlying);
	}

	@Override
	public void unfollow(ListChangeListener<? super U> change) {
		LockWatchDog.BARKER.tak(underlying);
		synchronized (underlying) {
			LockWatchDog.BARKER.hld(underlying);
			underlying.removeListener(change);
		}
		LockWatchDog.BARKER.rel(underlying);
	}
}