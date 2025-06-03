package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_14_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_15_Integer_int_order_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Markets {
    public final SwaggerDCCache<?> cache;
    private ListHolderImpl<Integer> get_markets_groups_holder;
    private final Map<Integer, ObjHolderSimple<R_get_markets_groups_market_group_id>> get_markets_groups_market_group_id_holder = new HashMap<>();
    private ListHolderImpl<R_get_markets_prices> get_markets_prices_holder;
    private final Map<K_14_int_int, ListHolderImpl<R_get_markets_region_id_history>> get_markets_region_id_history_holder = new HashMap<>();
    private final Map<K_15_Integer_int_order_type, ListHolderImpl<R_get_markets_region_id_orders>> get_markets_region_id_orders_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<Integer>> get_markets_region_id_types_holder = new HashMap<>();

    public Markets(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of item groups
     * 
     * cache over {@link Swagger#get_markets_groups}<br />
     */
    public ListHolder<Integer> groups() {
        if (get_markets_groups_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_markets_groups_holder == null) {
                            get_markets_groups_holder = new ListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_markets_groups", (page, properties) -> (cache.swagger).get_markets_groups(properties), arr -> get_markets_groups_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObjHolder<R_get_markets_groups_market_group_id> groups(int market_group_id) {
        ObjHolderSimple<R_get_markets_groups_market_group_id> ret = get_markets_groups_market_group_id_holder.get(market_group_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_groups_market_group_id_holder);
            try {
                synchronized (get_markets_groups_market_group_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_markets_groups_market_group_id_holder);
                    {
                        ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_markets_groups_market_group_id>();
                            ObjHolderSimple<R_get_markets_groups_market_group_id> finalRet = ret;
                            get_markets_groups_market_group_id_holder.put(market_group_id, ret);
                            (cache).addFetchCacheObject("get_markets_groups_market_group_id", properties -> (cache.swagger).get_markets_groups(market_group_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_markets_groups_market_group_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_markets_groups_market_group_id_holder);
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
    public ListHolder<R_get_markets_region_id_history> history(int region_id, int type_id) {
        K_14_int_int param = new K_14_int_int(type_id, region_id);
        ListHolderImpl<R_get_markets_region_id_history> ret = get_markets_region_id_history_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_region_id_history_holder);
            try {
                synchronized (get_markets_region_id_history_holder)
                {
                    LockWatchDog.BARKER.hld(get_markets_region_id_history_holder);
                    {
                        ret = get_markets_region_id_history_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_markets_region_id_history>();
                            get_markets_region_id_history_holder.put(param, ret);
                            ListHolderImpl<R_get_markets_region_id_history> finalRet = ret;
                            (cache).addFetchCacheArray("get_markets_region_id_history", (page, properties) -> (cache.swagger).get_markets_history(region_id, type_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_markets_region_id_history_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_markets_region_id_history_holder);
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
     *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders
     * @param region_id
     *     Return orders in this region
     * @param type_id
     *     Return orders only for this type
     */
    public ListHolder<R_get_markets_region_id_orders> orders(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type order_type, int region_id, Integer type_id) {
        K_15_Integer_int_order_type param = new K_15_Integer_int_order_type(type_id, region_id, order_type);
        ListHolderImpl<R_get_markets_region_id_orders> ret = get_markets_region_id_orders_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_region_id_orders_holder);
            try {
                synchronized (get_markets_region_id_orders_holder)
                {
                    LockWatchDog.BARKER.hld(get_markets_region_id_orders_holder);
                    {
                        ret = get_markets_region_id_orders_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_markets_region_id_orders>();
                            get_markets_region_id_orders_holder.put(param, ret);
                            ListHolderImpl<R_get_markets_region_id_orders> finalRet = ret;
                            (cache).addFetchCacheArray("get_markets_region_id_orders", (page, properties) -> (cache.swagger).get_markets_orders(order_type, page, region_id, type_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_markets_region_id_orders_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_markets_region_id_orders_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of prices
     * 
     * cache over {@link Swagger#get_markets_prices}<br />
     */
    public ListHolder<R_get_markets_prices> prices() {
        if (get_markets_prices_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_markets_prices_holder == null) {
                            get_markets_prices_holder = new ListHolderImpl<R_get_markets_prices>();
                            (cache).addFetchCacheArray("get_markets_prices", (page, properties) -> (cache.swagger).get_markets_prices(properties), arr -> get_markets_prices_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_markets_prices_holder;
    }

    /**
     * Return a list of type IDs that have active orders in the region, for efficient market indexing.
     * 
     * cache over {@link Swagger#get_markets_types}<br />
     * 
     * @param region_id
     *     Return statistics in this region
     */
    public ListHolder<Integer> types(int region_id) {
        ListHolderImpl<Integer> ret = get_markets_region_id_types_holder.get(region_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_markets_region_id_types_holder);
            try {
                synchronized (get_markets_region_id_types_holder)
                {
                    LockWatchDog.BARKER.hld(get_markets_region_id_types_holder);
                    {
                        ret = get_markets_region_id_types_holder.get(region_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<Integer>();
                            get_markets_region_id_types_holder.put(region_id, ret);
                            ListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_markets_region_id_types", (page, properties) -> (cache.swagger).get_markets_types(page, region_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_markets_region_id_types_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_markets_region_id_types_holder);
            }
        }
        return ret;
    }
}
