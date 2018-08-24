package fr.guiguilechat.jcelechat.model.jcesi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
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
		synchronized (underlying) {
			return new ArrayList<>(underlying);
		}
	}

	@Override
	public void apply(BiConsumer<Integer, U> cons) {
		waitData();
		synchronized (underlying) {
			for (int i = 0; i < underlying.size(); i++) {
				cons.accept(i, underlying.get(i));
			}
		}

	}

	@Override
	public void follow(ListChangeListener<? super U> listener) {
		synchronized (underlying) {
			ObservableList<U> otherlist = FXCollections.observableArrayList();
			otherlist.addListener(listener);
			otherlist.addAll(underlying);
			underlying.addListener(listener);
		}
	}

	@Override
	public Observable asObservable() {
		return underlying;
	}

	@Override
	public void dataReceived() {
		waitLatch.countDown();
	}
}