package fr.guiguilechat.jcelechat.jcesi.connected;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.lelouet.collectionholders.impl.ObsObjHolderImpl;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

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
	public <U> Pausable addFetchCacheArray(String name,
			BiFunction<Integer, Map<String, String>, Requested<U[]>> fetcher,
			Consumer<List<U>> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheArray(name, fetcher, cacheHandler, requiredRoles);
	}

	@Override
	public <U> Pausable addFetchCacheObject(String name, Function<Map<String, String>, Requested<U>> fetcher,
			Consumer<U> cacheHandler, String... requiredRoles) {
		return swagger.addFetchCacheObject(swagger.verify().CharacterName + "." + name, fetcher, cacheHandler,
				requiredRoles);
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
