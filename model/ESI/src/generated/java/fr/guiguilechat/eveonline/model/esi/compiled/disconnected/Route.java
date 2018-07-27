package fr.guiguilechat.eveonline.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.eveonline.model.esi.compiled.IConnected;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_16_flag_int_int_Lint_LLint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Route {
    public final SwaggerDCCache<?> cache;
    private final Map<K_16_flag_int_int_Lint_LLint, ObservableList<Integer>> get_route_origin_destination_holder = new HashMap<>();

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
    public ObservableList<Integer> get(int[] avoid, int[][] connections, int destination, IConnected.flag flag, int origin) {
        K_16_flag_int_int_Lint_LLint param = new K_16_flag_int_int_Lint_LLint(flag, origin, destination, avoid, connections);
        ObservableList<Integer> ret = get_route_origin_destination_holder.get(param);
        if (ret == null) {
            synchronized (get_route_origin_destination_holder)
            {
                ret = get_route_origin_destination_holder.get(param);
                if (ret == null) {
                    ObservableList<Integer> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_route_origin_destination_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_route_origin_destination", (page, headerHandler) -> IntStream.of((cache.swagger).get_route(avoid, connections, destination, flag, origin, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
