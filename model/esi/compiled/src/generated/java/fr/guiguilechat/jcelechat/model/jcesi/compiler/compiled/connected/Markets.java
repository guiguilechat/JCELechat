package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Markets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ListHolderImpl<R_get_markets_structures_structure_id>> get_markets_structures_structure_id_holder = new HashMap<>();

    public Markets(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return all orders in a structure
     * 
     * cache over {@link Swagger#get_markets_structures}<br />
     * 
     * @param structure_id
     *     Return orders in this structure
     */
    public ListHolder<R_get_markets_structures_structure_id> structures(long structure_id) {
        ListHolderImpl<R_get_markets_structures_structure_id> ret = get_markets_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_structures_structure_id_holder);
            try {
                synchronized (get_markets_structures_structure_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_markets_structures_structure_id_holder);
                    {
                        ret = get_markets_structures_structure_id_holder.get(structure_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_markets_structures_structure_id>();
                            get_markets_structures_structure_id_holder.put(structure_id, ret);
                            ListHolderImpl<R_get_markets_structures_structure_id> finalRet = ret;
                            (cache).addFetchCacheArray("get_markets_structures_structure_id", (page, properties) -> (cache.swagger).get_markets_structures(page, structure_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_markets_structures_structure_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_markets_structures_structure_id_holder);
            }
        }
        return ret;
    }
}
