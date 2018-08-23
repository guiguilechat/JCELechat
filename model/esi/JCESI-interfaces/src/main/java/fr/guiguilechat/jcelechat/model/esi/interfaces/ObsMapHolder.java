package fr.guiguilechat.jcelechat.model.esi.interfaces;

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
	 * apply all existing values to the change listener, and register it as a
	 * listener of the underlying map.
	 *
	 * @param change
	 */
	void follow(MapChangeListener<? super U, ? super V> change);

	void waitData();

	/** return an observable to be notified when values are changed */
	Observable asObservable();
}
