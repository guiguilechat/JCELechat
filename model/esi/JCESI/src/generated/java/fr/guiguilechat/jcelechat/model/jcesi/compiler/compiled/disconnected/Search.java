package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_17_String_LString_Boolean;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_search;
import javafx.beans.property.SimpleObjectProperty;

public class Search {
    public final SwaggerDCCache<?> cache;
    private final Map<K_17_String_LString_Boolean, ObsObjHolder<R_get_search>> get_search_holder = new HashMap<>();

    public Search(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Search for entities that match a given sub-string.
     * 
     * cache over {@link Swagger#get}<br />
     * 
     * @param categories
     *     Type of entities to search for
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public ObsObjHolder<R_get_search> get(String[] categories, String search, Boolean strict) {
        K_17_String_LString_Boolean param = new K_17_String_LString_Boolean(search, categories, strict);
        ObsObjHolder<R_get_search> ret = get_search_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_search_holder);
            synchronized (get_search_holder)
            {
                LockWatchDog.BARKER.hld(get_search_holder);
                ret = get_search_holder.get(param);
                if (ret == null) {
                    SimpleObjectProperty<R_get_search> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_search_holder.put(param, ret);
                    (cache).addFetchCacheObject("get_search", headerHandler -> (cache.swagger).get(categories, search, strict, headerHandler), item -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.set(item);
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_search_holder);
        }
        return ret;
    }
}