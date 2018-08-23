package fr.guiguilechat.jcelechat.model.jcesi.interfaces;

import java.util.function.Consumer;

import javafx.beans.Observable;

/**
 * holder on a single object. call should be synchronized.
 *
 * @param <U>
 */
public interface ObsObjHolder<U> {

	public U get();

	public void follow(Consumer<U> cons);

	void waitData();

	/** return an observable to be notified when values are changed */
	Observable asObservable();
}
