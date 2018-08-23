package fr.guiguilechat.jcelechat.model.jcesi.interfaces;

import java.util.List;
import java.util.function.BiConsumer;

import javafx.beans.Observable;
import javafx.collections.ListChangeListener;

/**
 * holder on an observable list. All calls should be synchronized
 *
 */
public interface ObsListHolder<U> {

	/**
	 * wait for at least one element to be added, then return a copy of the
	 * underlying list.
	 *
	 * @return
	 */
	List<U> copy();

	/**
	 * wait for at least one element to be added, then apply the consumer to the
	 * (index,elements) couples.
	 *
	 * @param cons
	 */
	void apply(BiConsumer<Integer, U> cons);

	/**
	 * apply all existing values to the change listener, and register it as a
	 * listener of the underlying list.
	 *
	 * @param listener
	 */
	void follow(ListChangeListener<? super U> listener);

	void waitData();

	/** return an observable to be notified when values are changed */
	Observable asObservable();

}
