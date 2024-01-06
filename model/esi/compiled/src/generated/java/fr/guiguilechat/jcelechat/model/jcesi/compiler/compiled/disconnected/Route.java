package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_15_flag_int_int_Lint_LLint;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Route {
    public final SwaggerDCCache<?> cache;
    private final Map<K_15_flag_int_int_Lint_LLint, ListHolderImpl<Integer>> get_route_origin_destination_holder = new HashMap<>();

    public Route(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get the systems between origin and destination
     * 
     * cache over {@link Swagger#get_route}<br />
     * 
     * @param avoid
     *     avoid solar system ID(s)
     * @param connections
     *     connected solar system pairs
     * @param destination
     *     destination solar system ID
     * @param flag
     *     route security preference
     * @param origin
     *     origin solar system ID
     */
    public ListHolder<Integer> get(int[] avoid,
        int[][] connections,
        int destination,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag flag,
        int origin) {
        K_15_flag_int_int_Lint_LLint param = new K_15_flag_int_int_Lint_LLint(flag, origin, destination, avoid, connections);
        ListHolderImpl<Integer> ret = get_route_origin_destination_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_route_origin_destination_holder);
            try {
                synchronized (get_route_origin_destination_holder)
                {
                    LockWatchDog.BARKER.hld(get_route_origin_destination_holder);
                    {
                        ret = get_route_origin_destination_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<Integer>();
                            get_route_origin_destination_holder.put(param, ret);
                            ListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_route_origin_destination", (page, properties) -> (cache.swagger).get_route(avoid, connections, destination, flag, origin, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_route_origin_destination_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_route_origin_destination_holder);
            }
        }
        return ret;
    }
}
