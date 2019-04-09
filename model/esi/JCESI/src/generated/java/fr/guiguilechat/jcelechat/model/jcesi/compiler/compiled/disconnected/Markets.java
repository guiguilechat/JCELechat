package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_14_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_15_Integer_int_order_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
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
            LockWatchDog.BARKER.tak(this);
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_markets_groups_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_markets_groups_holder = (cache).toHolder(holder);
                    ObsListHolder<Integer> finalRet = get_markets_groups_holder;
                    (cache).addFetchCacheArray("get_markets_groups", (page, properties) -> (cache.swagger).get_markets_groups(properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.clear();
                            if (arr!= null) {
                                holder.addAll(arr);
                            }
                        }
                        LockWatchDog.BARKER.rel(holder);
                        finalRet.dataReceived();
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(get_markets_groups_market_group_id_holder);
            synchronized (get_markets_groups_market_group_id_holder)
            {
                LockWatchDog.BARKER.hld(get_markets_groups_market_group_id_holder);
                ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_markets_groups_market_group_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_markets_groups_market_group_id_holder.put(market_group_id, ret);
                    (cache).addFetchCacheObject("get_markets_groups_market_group_id", properties -> (cache.swagger).get_markets_groups(market_group_id, properties), item -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.set(item);
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_markets_groups_market_group_id_holder);
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
            LockWatchDog.BARKER.tak(this);
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_markets_prices_holder == null) {
                    ObservableList<R_get_markets_prices> holder = FXCollections.observableArrayList();
                    get_markets_prices_holder = (cache).toHolder(holder);
                    ObsListHolder<R_get_markets_prices> finalRet = get_markets_prices_holder;
                    (cache).addFetchCacheArray("get_markets_prices", (page, properties) -> (cache.swagger).get_markets_prices(properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.clear();
                            if (arr!= null) {
                                holder.addAll(arr);
                            }
                        }
                        LockWatchDog.BARKER.rel(holder);
                        finalRet.dataReceived();
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(get_markets_region_id_history_holder);
            synchronized (get_markets_region_id_history_holder)
            {
                LockWatchDog.BARKER.hld(get_markets_region_id_history_holder);
                ret = get_markets_region_id_history_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_history> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_history_holder.put(param, ret);
                    ObsListHolder<R_get_markets_region_id_history> finalRet = ret;
                    (cache).addFetchCacheArray("get_markets_region_id_history", (page, properties) -> (cache.swagger).get_markets_history(region_id, type_id, properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.clear();
                            if (arr!= null) {
                                holder.addAll(arr);
                            }
                        }
                        LockWatchDog.BARKER.rel(holder);
                        finalRet.dataReceived();
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_markets_region_id_history_holder);
        }
        return ret;
    }

    /**
     * Return a list of orders in a region
     * 
     * cache over {@link Swagger#get_markets_orders}<br />
     * 
     * @param order_type
     *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders
     * @param region_id
     *     Return orders in this region
     * @param type_id
     *     Return orders only for this type
     */
    public ObsListHolder<R_get_markets_region_id_orders> orders(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type order_type, int region_id, Integer type_id) {
        K_15_Integer_int_order_type param = new K_15_Integer_int_order_type(type_id, region_id, order_type);
        ObsListHolder<R_get_markets_region_id_orders> ret = get_markets_region_id_orders_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_region_id_orders_holder);
            synchronized (get_markets_region_id_orders_holder)
            {
                LockWatchDog.BARKER.hld(get_markets_region_id_orders_holder);
                ret = get_markets_region_id_orders_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_markets_region_id_orders> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_orders_holder.put(param, ret);
                    ObsListHolder<R_get_markets_region_id_orders> finalRet = ret;
                    (cache).addFetchCacheArray("get_markets_region_id_orders", (page, properties) -> (cache.swagger).get_markets_orders(order_type, page, region_id, type_id, properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.clear();
                            if (arr!= null) {
                                holder.addAll(arr);
                            }
                        }
                        LockWatchDog.BARKER.rel(holder);
                        finalRet.dataReceived();
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_markets_region_id_orders_holder);
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
            LockWatchDog.BARKER.tak(get_markets_region_id_types_holder);
            synchronized (get_markets_region_id_types_holder)
            {
                LockWatchDog.BARKER.hld(get_markets_region_id_types_holder);
                ret = get_markets_region_id_types_holder.get(region_id);
                if (ret == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_markets_region_id_types_holder.put(region_id, ret);
                    ObsListHolder<Integer> finalRet = ret;
                    (cache).addFetchCacheArray("get_markets_region_id_types", (page, properties) -> (cache.swagger).get_markets_types(page, region_id, properties), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.clear();
                            if (arr!= null) {
                                holder.addAll(arr);
                            }
                        }
                        LockWatchDog.BARKER.rel(holder);
                        finalRet.dataReceived();
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_markets_region_id_types_holder);
        }
        return ret;
    }
}
