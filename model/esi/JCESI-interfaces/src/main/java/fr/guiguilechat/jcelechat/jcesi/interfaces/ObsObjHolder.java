package fr.guiguilechat.jcelechat.jcesi.interfaces;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;

/**
 * holder on a single object. call should be synchronized.
 *
 * @param <U>
 */
public interface ObsObjHolder<U> {

	public U get();

	public void follow(ChangeListener<U> cons);

	void unfollow(ChangeListener<U> change);

	void waitData();

	/** return an observable to be notified when values are changed */
	Observable asObservable();
}
