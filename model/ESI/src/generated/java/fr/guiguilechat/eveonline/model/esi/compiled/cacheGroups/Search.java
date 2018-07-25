package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_17_String_LString_Boolean;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_search;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Search {
    public final SwaggerCache<?> cache;
    private final Map<K_17_String_LString_Boolean, Property<R_get_search>> get_search_holder = new HashMap<>();

    public Search(SwaggerCache<?> parent) {
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
    public Property<R_get_search> get(String[] categories, String search, Boolean strict) {
        K_17_String_LString_Boolean param = new K_17_String_LString_Boolean(search, categories, strict);
        Property<R_get_search> ret = get_search_holder.get(param);
        if (ret == null) {
            synchronized (get_search_holder)
            {
                ret = get_search_holder.get(param);
                if (ret == null) {
                    SimpleObjectProperty<R_get_search> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_search_holder.put(param, ret);
                    (cache).addFetchCacheObject("get_search", headerHandler -> (cache.swagger).get(categories, search, strict, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
