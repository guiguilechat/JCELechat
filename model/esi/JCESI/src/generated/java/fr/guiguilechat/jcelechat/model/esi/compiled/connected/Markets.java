package fr.guiguilechat.jcelechat.model.esi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_structures_structure_id;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Markets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObsListHolder<R_get_markets_structures_structure_id>> get_markets_structures_structure_id_holder = new HashMap<>();

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
    public ObsListHolder<R_get_markets_structures_structure_id> structures(long structure_id) {
        ObsListHolder<R_get_markets_structures_structure_id> ret = get_markets_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            synchronized (get_markets_structures_structure_id_holder)
            {
                ret = get_markets_structures_structure_id_holder.get(structure_id);
                if (ret == null) {
                    ObservableList<R_get_markets_structures_structure_id> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_structures_structure_id_holder.put(structure_id, ret);
                    (cache).addFetchCacheArray("get_markets_structures_structure_id", (page, headerHandler) -> (cache.swagger).get_markets_structures(page, structure_id, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
