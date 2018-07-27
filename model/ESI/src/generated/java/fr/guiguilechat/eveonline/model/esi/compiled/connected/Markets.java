package fr.guiguilechat.eveonline.model.esi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCOCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_structures_structure_id;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Markets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObservableList<R_get_markets_structures_structure_id>> get_markets_structures_structure_id_holder = new HashMap<>();

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
    public ObservableList<R_get_markets_structures_structure_id> structures(long structure_id) {
        ObservableList<R_get_markets_structures_structure_id> ret = get_markets_structures_structure_id_holder.get(structure_id);
        if (ret == null) {
            synchronized (get_markets_structures_structure_id_holder)
            {
                ret = get_markets_structures_structure_id_holder.get(structure_id);
                if (ret == null) {
                    ObservableList<R_get_markets_structures_structure_id> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_markets_structures_structure_id_holder.put(structure_id, ret);
                    (cache).addFetchCacheArray("get_markets_structures_structure_id", (page, headerHandler) -> (cache.swagger).get_markets_structures(page, structure_id, headerHandler), arr -> {
                        synchronized (finalret)
                        {
                            finalret.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
