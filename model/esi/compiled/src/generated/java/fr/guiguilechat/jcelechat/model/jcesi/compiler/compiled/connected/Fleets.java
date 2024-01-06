package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_members;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_wings;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Fleets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObjHolderSimple<R_get_fleets_fleet_id>> get_fleets_fleet_id_holder = new HashMap<>();
    private final Map<Long, ListHolderImpl<R_get_fleets_fleet_id_members>> get_fleets_fleet_id_members_holder = new HashMap<>();
    private final Map<Long, ListHolderImpl<R_get_fleets_fleet_id_wings>> get_fleets_fleet_id_wings_holder = new HashMap<>();

    public Fleets(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return details about a fleet
     * 
     * cache over {@link Swagger#get_fleets}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObjHolder<R_get_fleets_fleet_id> get(long fleet_id) {
        ObjHolderSimple<R_get_fleets_fleet_id> ret = get_fleets_fleet_id_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_holder);
            try {
                synchronized (get_fleets_fleet_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_holder);
                    {
                        ret = get_fleets_fleet_id_holder.get(fleet_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_fleets_fleet_id>();
                            ObjHolderSimple<R_get_fleets_fleet_id> finalRet = ret;
                            get_fleets_fleet_id_holder.put(fleet_id, ret);
                            (cache).addFetchCacheObject("get_fleets_fleet_id", properties -> (cache.swagger).get_fleets(fleet_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_holder);
            }
        }
        return ret;
    }

    /**
     * Return information about fleet members
     * 
     * cache over {@link Swagger#get_fleets_members}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ListHolder<R_get_fleets_fleet_id_members> members(long fleet_id) {
        ListHolderImpl<R_get_fleets_fleet_id_members> ret = get_fleets_fleet_id_members_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_members_holder);
            try {
                synchronized (get_fleets_fleet_id_members_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_members_holder);
                    {
                        ret = get_fleets_fleet_id_members_holder.get(fleet_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_fleets_fleet_id_members>();
                            get_fleets_fleet_id_members_holder.put(fleet_id, ret);
                            ListHolderImpl<R_get_fleets_fleet_id_members> finalRet = ret;
                            (cache).addFetchCacheArray("get_fleets_fleet_id_members", (page, properties) -> (cache.swagger).get_fleets_members(fleet_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_members_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_members_holder);
            }
        }
        return ret;
    }

    /**
     * Return information about wings in a fleet
     * 
     * cache over {@link Swagger#get_fleets_wings}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ListHolder<R_get_fleets_fleet_id_wings> wings(long fleet_id) {
        ListHolderImpl<R_get_fleets_fleet_id_wings> ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_wings_holder);
            try {
                synchronized (get_fleets_fleet_id_wings_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_wings_holder);
                    {
                        ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_fleets_fleet_id_wings>();
                            get_fleets_fleet_id_wings_holder.put(fleet_id, ret);
                            ListHolderImpl<R_get_fleets_fleet_id_wings> finalRet = ret;
                            (cache).addFetchCacheArray("get_fleets_fleet_id_wings", (page, properties) -> (cache.swagger).get_fleets_wings(fleet_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_wings_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_wings_holder);
            }
        }
        return ret;
    }
}
