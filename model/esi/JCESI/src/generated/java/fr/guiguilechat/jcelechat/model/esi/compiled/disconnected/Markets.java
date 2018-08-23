package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.keys.K_14_int_int;
import fr.guiguilechat.jcelechat.model.esi.compiled.keys.K_15_Integer_int_order_type;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Markets {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<Integer> get_markets_groups_holder;
    private final Map<Integer, ObsObjHolder<R_get_markets_groups_market_group_id>> get_markets_groups_market_group_id_holder = new HashMap<>();
    private ObsListHolder<R_get_markets_prices> get_markets_prices_holder;
    private final Map<K_14_int_int, ObsListHolder<R_get_markets_region_id_history>> get_markets_region_id_history_holder = new HashMap<>();
    private final Map<K_15_Integer_int_order_type, ObsListHolder<R_get_markets_region_id_orders>> get_markets_region_id_orders_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<Integer>> get_markets_region_id_types_holder = new HashMap<>();

    public Markets(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of item groups
     * 
     * cache over {@link Swagger#get_markets_groups}<br />
     */
    public ObsListHolder<Integer> groups() {
        if (get_markets_groups_holder == null) {
            synchronized (this)
            {
                if (get_markets_groups_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_markets_groups_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_markets_groups", (page, headerHandler) -> IntStream.of((cache.swagger).get_markets_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_markets_groups_holder;
    }

    /**
     * Get information on an item group
     * 
     * cache over {@link Swagger#get_markets_groups}<br />
     * 
     * @param market_group_id
     *     An Eve item group ID
     */
    public ObsObjHolder<R_get_markets_groups_market_group_id> groups(int market_group_id) {
        ObsObjHolder<R_get_markets_groups_market_group_id> ret = get_markets_groups_market_group_id_holder.get(market_group_id);
        if (ret == null) {
            synchronized (get_markets_groups_market_group_id_holder)
            {
                ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_markets_groups_market_group_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_markets_groups_market_group_id_holder.put(market_group_id, ret);
                    (cache).addFetchCacheObject("get_markets_groups_market_group_id", headerHandler -> (cache.swagger).get_markets_groups(market_group_id, headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Return a list of prices
     * 
     * cache over {@link Swagger#get_markets_prices}<br />
     */
    public ObsListHolder<R_get_markets_prices> prices() {
        if (get_markets_prices_holder == null) {
            synchronized (this)
            {
                if (get_markets_prices_holder == null) {
                    ObservableList<R_get_markets_prices> holder = FXCollections.observableArrayList();
                    get_markets_prices_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_markets_prices", (page, headerHandler) -> (cache.swagger).get_markets_prices(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_markets_prices_holder;
    }

    /**
     * Return a list of historical market statistics for the specified type in a region
     * 
     * cache over {@link Swagger#get_markets_history}<br />
     * 
     * @param region_id
     *     Return statistics in this region
     * @param type_id
     *     Return statistics for this type
     */
    public ObsListHolder<R_get_markets_region_id_history> history(int region_id, int type_id) {
        K_14_int_int param = new K_14_int_int(type_id, region_id);
        ObsListHolder<R_get_markets_region_id_history> ret = get_markets_region_id_history_holder.get(param);
        if (ret == null) {
            synchronized (get_markets_region_id_history_holder)
            {
                ret = get_markets_region_id_history_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_history> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_history_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_history", (page, headerHandler) -> (cache.swagger).get_markets_history(region_id, type_id, headerHandler), arr -> {
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

    /**
     * Return a list of orders in a region
     * 
     * cache over {@link Swagger#get_markets_orders}<br />
     * 
     * @param order_type
     *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders.
     * @param region_id
     *     Return orders in this region
     * @param type_id
     *     Return orders only for this type
     */
    public ObsListHolder<R_get_markets_region_id_orders> orders(fr.guiguilechat.jcelechat.model.esi.compiled.structures.order_type order_type, int region_id, Integer type_id) {
        K_15_Integer_int_order_type param = new K_15_Integer_int_order_type(type_id, region_id, order_type);
        ObsListHolder<R_get_markets_region_id_orders> ret = get_markets_region_id_orders_holder.get(param);
        if (ret == null) {
            synchronized (get_markets_region_id_orders_holder)
            {
                ret = get_markets_region_id_orders_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_orders> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_orders_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_orders", (page, headerHandler) -> (cache.swagger).get_markets_orders(order_type, page, region_id, type_id, headerHandler), arr -> {
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

    /**
     * Return a list of type IDs that have active orders in the region, for efficient market indexing.
     * 
     * cache over {@link Swagger#get_markets_types}<br />
     * 
     * @param region_id
     *     Return statistics in this region
     */
    public ObsListHolder<Integer> types(int region_id) {
        ObsListHolder<Integer> ret = get_markets_region_id_types_holder.get(region_id);
        if (ret == null) {
            synchronized (get_markets_region_id_types_holder)
            {
                ret = get_markets_region_id_types_holder.get(region_id);
                if (ret == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_types_holder.put(region_id, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_types", (page, headerHandler) -> IntStream.of((cache.swagger).get_markets_types(page, region_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
