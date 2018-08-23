package fr.guiguilechat.jcelechat.model.jcesi.interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public interface ISwaggerCacheHelper {

	public <U> Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>>, U[]> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles);

	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, List<String>>, U> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles);

	public <U, V> ObsMapHolder<U, V> toHolder(ObservableMap<U, V> map);

	public <U> ObsListHolder<U> toHolder(ObservableList<U> list);

	public <U> ObsObjHolder<U> toHolder(ObservableValue<U> obj);

	public interface Pausable {

		public void pause();

		public void resume();
	}

}
