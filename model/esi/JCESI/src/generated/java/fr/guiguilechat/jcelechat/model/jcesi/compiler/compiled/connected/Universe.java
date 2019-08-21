package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.lelouet.collectionholders.impl.AObsObjHolder;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;

public class Universe {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, AObsObjHolder<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_holder = new HashMap<>();

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
    public ObsObjHolder<R_get_universe_structures_structure_id> structures(long structure_id) {
        AObsObjHolder<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_structures_structure_id_holder);
            try {
                synchronized (get_universe_structures_structure_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_structures_structure_id_holder);
                    {
                        ret = get_universe_structures_structure_id_holder.get(structure_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_structures_structure_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_structures_structure_id_holder.put(structure_id, ret);
                            (cache).addFetchCacheObject("get_universe_structures_structure_id", properties -> (cache.swagger).get_universe_structures(structure_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
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
