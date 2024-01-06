package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_7_int_long;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_extractions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Corporation {
    public final SwaggerCOCache<?> cache;
    private final Map<Integer, ListHolderImpl<R_get_corporation_corporation_id_mining_extractions>> get_corporation_corporation_id_mining_extractions_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporation_corporation_id_mining_observers>> get_corporation_corporation_id_mining_observers_holder = new HashMap<>();
    private final Map<K_7_int_long, ListHolderImpl<R_get_corporation_corporation_id_mining_observers_observer_id>> get_corporation_corporation_id_mining_observers_observer_id_holder = new HashMap<>();

    public Corporation(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Extraction timers for all moon chunks being extracted by refineries belonging to a corporation.
     * 
     * cache over {@link Swagger#get_corporation_mining_extractions}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporation_corporation_id_mining_extractions> mining_extractions(int corporation_id) {
        ListHolderImpl<R_get_corporation_corporation_id_mining_extractions> ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporation_corporation_id_mining_extractions_holder);
            try {
                synchronized (get_corporation_corporation_id_mining_extractions_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporation_corporation_id_mining_extractions_holder);
                    {
                        ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporation_corporation_id_mining_extractions>();
                            get_corporation_corporation_id_mining_extractions_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporation_corporation_id_mining_extractions> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporation_corporation_id_mining_extractions", (page, properties) -> (cache.swagger).get_corporation_mining_extractions(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Station_Manager"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_extractions_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_extractions_holder);
            }
        }
        return ret;
    }

    /**
     * Paginated list of all entities capable of observing and recording mining for a corporation
     * 
     * cache over {@link Swagger#get_corporation_mining_observers}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporation_corporation_id_mining_observers> mining_observers(int corporation_id) {
        ListHolderImpl<R_get_corporation_corporation_id_mining_observers> ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporation_corporation_id_mining_observers_holder);
            try {
                synchronized (get_corporation_corporation_id_mining_observers_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporation_corporation_id_mining_observers_holder);
                    {
                        ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporation_corporation_id_mining_observers>();
                            get_corporation_corporation_id_mining_observers_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporation_corporation_id_mining_observers> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporation_corporation_id_mining_observers", (page, properties) -> (cache.swagger).get_corporation_mining_observers(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Accountant"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_observers_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_observers_holder);
            }
        }
        return ret;
    }

    /**
     * Paginated record of all mining seen by an observer
     * 
     * cache over {@link Swagger#get_corporation_mining_observers}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param observer_id
     *     A mining observer id
     */
    public ListHolder<R_get_corporation_corporation_id_mining_observers_observer_id> mining_observers(int corporation_id, long observer_id) {
        K_7_int_long param = new K_7_int_long(corporation_id, observer_id);
        ListHolderImpl<R_get_corporation_corporation_id_mining_observers_observer_id> ret = get_corporation_corporation_id_mining_observers_observer_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporation_corporation_id_mining_observers_observer_id_holder);
            try {
                synchronized (get_corporation_corporation_id_mining_observers_observer_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporation_corporation_id_mining_observers_observer_id_holder);
                    {
                        ret = get_corporation_corporation_id_mining_observers_observer_id_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporation_corporation_id_mining_observers_observer_id>();
                            get_corporation_corporation_id_mining_observers_observer_id_holder.put(param, ret);
                            ListHolderImpl<R_get_corporation_corporation_id_mining_observers_observer_id> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporation_corporation_id_mining_observers_observer_id", (page, properties) -> (cache.swagger).get_corporation_mining_observers(corporation_id, observer_id, page, properties), arr -> finalRet.set(arr), new String[] {"Accountant"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_observers_observer_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporation_corporation_id_mining_observers_observer_id_holder);
            }
        }
        return ret;
    }
}
