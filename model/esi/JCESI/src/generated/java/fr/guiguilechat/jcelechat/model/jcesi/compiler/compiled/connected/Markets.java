package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id;
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
            LockWatchDog.BARKER.tak(get_markets_structures_structure_id_holder);
            synchronized (get_markets_structures_structure_id_holder)
            {
                LockWatchDog.BARKER.hld(get_markets_structures_structure_id_holder);
                ret = get_markets_structures_structure_id_holder.get(structure_id);
                if (ret == null) {
                    ObservableList<R_get_markets_structures_structure_id> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_structures_structure_id_holder.put(structure_id, ret);
                    ObsListHolder<R_get_markets_structures_structure_id> finalRet = ret;
                    (cache).addFetchCacheArray("get_markets_structures_structure_id", (page, properties) -> (cache.swagger).get_markets_structures(page, structure_id, properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.setAll(arr);
                            finalRet.dataReceived();
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_markets_structures_structure_id_holder);
        }
        return ret;
    }
}
