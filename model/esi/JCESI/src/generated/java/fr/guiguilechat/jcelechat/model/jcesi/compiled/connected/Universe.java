package fr.guiguilechat.jcelechat.model.jcesi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;

public class Universe {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObsObjHolder<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_holder = new HashMap<>();

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
        ObsObjHolder<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            synchronized (get_universe_structures_structure_id_holder)
            {
                ret = get_universe_structures_structure_id_holder.get(structure_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_structures_structure_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_universe_structures_structure_id_holder.put(structure_id, ret);
                    (cache).addFetchCacheObject("get_universe_structures_structure_id", headerHandler -> (cache.swagger).get_universe_structures(structure_id, headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
