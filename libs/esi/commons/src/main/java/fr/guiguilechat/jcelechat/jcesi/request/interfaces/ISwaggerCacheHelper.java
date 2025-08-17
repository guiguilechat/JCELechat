package fr.guiguilechat.jcelechat.jcesi.request.interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.impl.collections.MapHolderImpl;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public interface ISwaggerCacheHelper {

	public <U> Pausable addFetchCacheArray(String name,
			BiFunction<Integer, Map<String, String>, Requested<U[]>> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles);

	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, String>, Requested<U>> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles);

	public <U, V> MapHolderImpl<U, V> toHolder(ObservableMap<U, V> map);

	public <U> ListHolderImpl<U> toHolder(ObservableList<U> list);

	public interface Pausable {

		public void pause();

		public void resume();
	}

}
