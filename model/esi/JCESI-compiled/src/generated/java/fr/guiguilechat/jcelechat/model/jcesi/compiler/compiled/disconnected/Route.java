package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_16_flag_int_int_Lint_LLint;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Route {
    public final SwaggerDCCache<?> cache;
    private final Map<K_16_flag_int_int_Lint_LLint, ObsListHolderImpl<Integer>> get_route_origin_destination_holder = new HashMap<>();

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
    public ObsListHolder<Integer> get(int[] avoid,
        int[][] connections,
        int destination,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag flag,
        int origin) {
        K_16_flag_int_int_Lint_LLint param = new K_16_flag_int_int_Lint_LLint(flag, origin, destination, avoid, connections);
        ObsListHolderImpl<Integer> ret = get_route_origin_destination_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_route_origin_destination_holder);
            try {
                synchronized (get_route_origin_destination_holder)
                {
                    LockWatchDog.BARKER.hld(get_route_origin_destination_holder);
                    {
                        ret = get_route_origin_destination_holder.get(param);
                        if (ret == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_route_origin_destination_holder.put(param, ret);
                            ObsListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_route_origin_destination", (page, properties) -> (cache.swagger).get_route(avoid, connections, destination, flag, origin, properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
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
