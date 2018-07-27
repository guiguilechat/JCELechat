package fr.guiguilechat.eveonline.esi.disconnected;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerDCCache;

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

}
