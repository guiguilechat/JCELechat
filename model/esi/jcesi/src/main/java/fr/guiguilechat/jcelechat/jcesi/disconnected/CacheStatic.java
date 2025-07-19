package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.impl.collections.MapHolderImpl;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * cache over an instance of {@link ESIRawPublic}. Typical use case is to call
 * {@link ESIRawPublic#INSTANCE}.cache
 */
public class CacheStatic extends SwaggerDCCache<ESIRawPublic> {

	public CacheStatic(ESIRawPublic swag) {
		super(swag);
	}

	@Override
	public <U> Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, String>, Requested<U[]>> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheArray("STATIC_" + name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, String>, Requested<U>> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheObject("STATIC_" + name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> ListHolderImpl<U> toHolder(ObservableList<U> list) {
		return new ListHolderImpl<>(list);
	}

	@Override
	public <U, V> MapHolderImpl<U, V> toHolder(ObservableMap<U, V> map) {
		return new MapHolderImpl<>(map);
	}

}
