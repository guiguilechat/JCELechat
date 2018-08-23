package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.keys.K_16_flag_int_int_Lint_LLint;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Route {
    public final SwaggerDCCache<?> cache;
    private final Map<K_16_flag_int_int_Lint_LLint, ObsListHolder<Integer>> get_route_origin_destination_holder = new HashMap<>();

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
    public ObsListHolder<Integer> get(int[] avoid, int[][] connections, int destination, fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.flag flag, int origin) {
        K_16_flag_int_int_Lint_LLint param = new K_16_flag_int_int_Lint_LLint(flag, origin, destination, avoid, connections);
        ObsListHolder<Integer> ret = get_route_origin_destination_holder.get(param);
        if (ret == null) {
            synchronized (get_route_origin_destination_holder)
            {
                ret = get_route_origin_destination_holder.get(param);
                if (ret == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_route_origin_destination_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_route_origin_destination", (page, headerHandler) -> IntStream.of((cache.swagger).get_route(avoid, connections, destination, flag, origin, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
