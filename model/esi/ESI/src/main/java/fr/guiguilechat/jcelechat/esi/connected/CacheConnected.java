package fr.guiguilechat.jcelechat.esi.connected;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerCOCache;

/**
 * The cache is dedicated to holding the data from a swagger.
 * <p>
 * The expiration date of the data is retrieved from the web service's returned
 * headers, at which time the cache handles the fetch of new data and stores the
 * new data.
 * </p>
 */
public class CacheConnected extends SwaggerCOCache<ESIConnected> {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CacheConnected.class);

	public CacheConnected(ESIConnected swag) {
		super(swag);
	}

	// execution of data retrieval

	@Override
	public <U> Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>>, U[]> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheArray(name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, List<String>>, U> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheObject(swagger.verify().CharacterName + "." + name, fetcher, cacheHandler,
				requiredRoles);
	}

}
