package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Universe {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObjHolderSimple<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_holder = new HashMap<>();

    public Universe(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Returns information on requested structure if you are on the ACL. Otherwise, returns "Forbidden" for all inputs.
     * 
     * cache over {@link Swagger#get_universe_structures}<br />
     * 
     * @param structure_id
     *     An Eve structure ID
     */
    public ObjHolder<R_get_universe_structures_structure_id> structures(long structure_id) {
        ObjHolderSimple<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_structures_structure_id_holder);
            try {
                synchronized (get_universe_structures_structure_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_structures_structure_id_holder);
                    {
                        ret = get_universe_structures_structure_id_holder.get(structure_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_universe_structures_structure_id>();
                            ObjHolderSimple<R_get_universe_structures_structure_id> finalRet = ret;
                            get_universe_structures_structure_id_holder.put(structure_id, ret);
                            (cache).addFetchCacheObject("get_universe_structures_structure_id", properties -> (cache.swagger).get_universe_structures(structure_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_structures_structure_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_structures_structure_id_holder);
            }
        }
        return ret;
    }
}
