package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.impl.ObsListHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsMapHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsObjHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsMapHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class CacheStatic extends SwaggerDCCache<ESIStatic> {

	public CacheStatic(ESIStatic swag) {
		super(swag);
	}

	@Override
	public <U> Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>>, U[]> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheArray("STATIC_" + name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, List<String>>, U> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheObject("STATIC_" + name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> ObsListHolder<U> toHolder(ObservableList<U> list) {
		return new ObsListHolderImpl<>(list);
	}

	@Override
	public <U, V> ObsMapHolder<U, V> toHolder(ObservableMap<U, V> map) {
		return new ObsMapHolderImpl<>(map);
	}

	@Override
	public <U> ObsObjHolder<U> toHolder(ObservableValue<U> obj) {
		return new ObsObjHolderImpl<>(obj);
	}

}
