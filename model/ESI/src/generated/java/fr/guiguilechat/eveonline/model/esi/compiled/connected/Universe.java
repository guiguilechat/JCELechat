package fr.guiguilechat.eveonline.model.esi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCOCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Universe {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, Property<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_holder = new HashMap<>();

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
    public Property<R_get_universe_structures_structure_id> structures(long structure_id) {
        Property<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            synchronized (get_universe_structures_structure_id_holder)
            {
                ret = get_universe_structures_structure_id_holder.get(structure_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_structures_structure_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_structures_structure_id_holder.put(structure_id, ret);
                    (cache).addFetchCacheObject("get_universe_structures_structure_id", headerHandler -> (cache.swagger).get_universe_structures(structure_id, headerHandler), item -> {
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
