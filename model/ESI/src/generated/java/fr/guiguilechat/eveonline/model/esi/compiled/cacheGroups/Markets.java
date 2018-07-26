package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_14_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_15_Integer_int_order_type;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_structures_structure_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Markets {
    public final SwaggerCache<?> cache;
    private ObservableList<Integer> get_markets_groups_holder;
    private final Map<Integer, Property<R_get_markets_groups_market_group_id>> get_markets_groups_market_group_id_holder = new HashMap<>();
    private ObservableList<R_get_markets_prices> get_markets_prices_holder;
    private final Map<Long, ObservableList<R_get_markets_structures_structure_id>> get_markets_structures_structure_id_holder = new HashMap<>();
    private final Map<K_14_int_int, ObservableList<R_get_markets_region_id_history>> get_markets_region_id_history_holder = new HashMap<>();
    private final Map<K_15_Integer_int_order_type, ObservableList<R_get_markets_region_id_orders>> get_markets_region_id_orders_holder = new HashMap<>();
    private final Map<Integer, ObservableList<Integer>> get_markets_region_id_types_holder = new HashMap<>();

    public Markets(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of item groups
     * 
     * cache over {@link Swagger#get_markets_groups}<br />
     */
    public ObservableList<Integer> groups() {
        if (get_markets_groups_holder == null) {
            synchronized (this)
            {
                if (get_markets_groups_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_markets_groups_holder = finalContainer;
                    get_markets_groups_holder.add(null);
                    (cache).addFetchCacheArray("get_markets_groups", (page, headerHandler) -> IntStream.of((cache.swagger).get_markets_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
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
    public Property<R_get_markets_groups_market_group_id> groups(int market_group_id) {
        Property<R_get_markets_groups_market_group_id> ret = get_markets_groups_market_group_id_holder.get(market_group_id);
        if (ret == null) {
            synchronized (get_markets_groups_market_group_id_holder)
            {
                ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_markets_groups_market_group_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_markets_groups_market_group_id_holder.put(market_group_id, ret);
                    (cache).addFetchCacheObject("get_markets_groups_market_group_id", headerHandler -> (cache.swagger).get_markets_groups(market_group_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
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
    public ObservableList<R_get_markets_prices> prices() {
        if (get_markets_prices_holder == null) {
            synchronized (this)
            {
                if (get_markets_prices_holder == null) {
                    ObservableList<R_get_markets_prices> finalContainer = FXCollections.observableArrayList();
                    get_markets_prices_holder = finalContainer;
                    get_markets_prices_holder.add(null);
                    (cache).addFetchCacheArray("get_markets_prices", (page, headerHandler) -> (cache.swagger).get_markets_prices(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_markets_prices_holder;
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
    public ObservableList<R_get_markets_region_id_history> history(int region_id, int type_id) {
        K_14_int_int param = new K_14_int_int(type_id, region_id);
        ObservableList<R_get_markets_region_id_history> ret = get_markets_region_id_history_holder.get(param);
        if (ret == null) {
            synchronized (get_markets_region_id_history_holder)
            {
                ret = get_markets_region_id_history_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_history> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_markets_region_id_history_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_history", (page, headerHandler) -> (cache.swagger).get_markets_history(region_id, type_id, headerHandler), arr -> {
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
    public ObservableList<R_get_markets_region_id_orders> orders(Swagger.order_type order_type, int region_id, Integer type_id) {
        K_15_Integer_int_order_type param = new K_15_Integer_int_order_type(type_id, region_id, order_type);
        ObservableList<R_get_markets_region_id_orders> ret = get_markets_region_id_orders_holder.get(param);
        if (ret == null) {
            synchronized (get_markets_region_id_orders_holder)
            {
                ret = get_markets_region_id_orders_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_orders> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_markets_region_id_orders_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_orders", (page, headerHandler) -> (cache.swagger).get_markets_orders(order_type, page, region_id, type_id, headerHandler), arr -> {
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

    /**
     * Return a list of type IDs that have active orders in the region, for efficient market indexing.
     * 
     * cache over {@link Swagger#get_markets_types}<br />
     * 
     * @param region_id
     *     Return statistics in this region
     */
    public ObservableList<Integer> types(int region_id) {
        ObservableList<Integer> ret = get_markets_region_id_types_holder.get(region_id);
        if (ret == null) {
            synchronized (get_markets_region_id_types_holder)
            {
                ret = get_markets_region_id_types_holder.get(region_id);
                if (ret == null) {
                    ObservableList<Integer> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_markets_region_id_types_holder.put(region_id, ret);
                    (cache).addFetchCacheArray("get_markets_region_id_types", (page, headerHandler) -> IntStream.of((cache.swagger).get_markets_types(page, region_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
