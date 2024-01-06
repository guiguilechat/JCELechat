package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Wars {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ListHolderImpl<Integer>> get_wars_holder = new HashMap<>();
    private final Map<Integer, ObjHolderSimple<R_get_wars_war_id>> get_wars_war_id_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<M_get_killmails_2>> get_wars_war_id_killmails_holder = new HashMap<>();

    public Wars(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return details about a war
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param war_id
     *     ID for a war
     */
    public ObjHolder<R_get_wars_war_id> get(int war_id) {
        ObjHolderSimple<R_get_wars_war_id> ret = get_wars_war_id_holder.get(war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_war_id_holder);
            try {
                synchronized (get_wars_war_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_wars_war_id_holder);
                    {
                        ret = get_wars_war_id_holder.get(war_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_wars_war_id>();
                            ObjHolderSimple<R_get_wars_war_id> finalRet = ret;
                            get_wars_war_id_holder.put(war_id, ret);
                            (cache).addFetchCacheObject("get_wars_war_id", properties -> (cache.swagger).get_wars(war_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_wars_war_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_wars_war_id_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of kills related to a war
     * 
     * cache over {@link Swagger#get_wars_killmails}<br />
     * 
     * @param war_id
     *     A valid war ID
     */
    public ListHolder<M_get_killmails_2> killmails(int war_id) {
        ListHolderImpl<M_get_killmails_2> ret = get_wars_war_id_killmails_holder.get(war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_war_id_killmails_holder);
            try {
                synchronized (get_wars_war_id_killmails_holder)
                {
                    LockWatchDog.BARKER.hld(get_wars_war_id_killmails_holder);
                    {
                        ret = get_wars_war_id_killmails_holder.get(war_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_killmails_2>();
                            get_wars_war_id_killmails_holder.put(war_id, ret);
                            ListHolderImpl<M_get_killmails_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_wars_war_id_killmails", (page, properties) -> (cache.swagger).get_wars_killmails(page, war_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_wars_war_id_killmails_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_wars_war_id_killmails_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of wars
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param max_war_id
     *     Only return wars with ID smaller than this
     */
    public ListHolder<Integer> wars(Integer max_war_id) {
        ListHolderImpl<Integer> ret = get_wars_holder.get(max_war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_holder);
            try {
                synchronized (get_wars_holder)
                {
                    LockWatchDog.BARKER.hld(get_wars_holder);
                    {
                        ret = get_wars_holder.get(max_war_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<Integer>();
                            get_wars_holder.put(max_war_id, ret);
                            ListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_wars", (page, properties) -> (cache.swagger).get_wars(max_war_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_wars_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_wars_holder);
            }
        }
        return ret;
    }
}
