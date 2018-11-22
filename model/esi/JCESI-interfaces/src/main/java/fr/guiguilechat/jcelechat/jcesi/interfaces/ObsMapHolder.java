package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.util.Map;

import javafx.beans.Observable;
import javafx.collections.MapChangeListener;

/**
 * Holder on an underlying observable map. All calls should be synchronized on
 * the underlying observable map.
 *
 * @param <U>
 * @param <V>
 */
public interface ObsMapHolder<U, V> {

	/**
	 * wait for at least one data to be received, then returns a copy of the
	 * underlying map
	 *
	 * @return
	 */
	Map<U, V> copy();

	/**
	 * synchronized call to the underlying map get, after the dxata is received.
	 * 
	 * @param key
	 * @return
	 */
	V get(U key);

	/**
	 * apply all existing values to the change listener, and register it as a
	 * listener of the underlying map.
	 *
	 * @param change
	 */
	void follow(MapChangeListener<? super U, ? super V> change);

	void unfollow(MapChangeListener<? super U, ? super V> change);

	void waitData();

	/**
	 * called by the data fetcher when data has been received. This has use only
	 * when the data received is empty, otherwise the put() methods should already
	 * call this method
	 */
	void dataReceived();

	/**
	 * register a runnable to be run once {@link #dataReceived()} is called. The
	 * call is made in a new thread.
	 *
	 * @param callback
	 *          the function to call once data is available. if data is already
	 *          available, this callback will be called at once.
	 */
	public default void onWaitEnd(Runnable callback) {
		new Thread(() -> {
			waitData();
			callback.run();
		}).start();
	}

	/** return an observable to be notified when values are changed */
	Observable asObservable();
}
