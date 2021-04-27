package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_11_long_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Dogma {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<Integer> get_dogma_attributes_holder;
    private final Map<Integer, ObsObjHolderSimple<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_holder = new HashMap<>();
    private final Map<K_11_long_int, ObsObjHolderSimple<R_get_dogma_dynamic_items_type_id_item_id>> get_dogma_dynamic_items_type_id_item_id_holder = new HashMap<>();
    private ObsListHolderImpl<Integer> get_dogma_effects_holder;
    private final Map<Integer, ObsObjHolderSimple<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_holder = new HashMap<>();

    public Dogma(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of dogma attribute ids
     * 
     * cache over {@link Swagger#get_dogma_attributes}<br />
     */
    public ObsListHolder<Integer> attributes() {
        if (get_dogma_attributes_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_dogma_attributes_holder == null) {
                            get_dogma_attributes_holder = new ObsListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_dogma_attributes", (page, properties) -> (cache.swagger).get_dogma_attributes(properties), arr -> get_dogma_attributes_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_dogma_attributes_holder;
    }

    /**
     * Get information on a dogma attribute
     * 
     * cache over {@link Swagger#get_dogma_attributes}<br />
     * 
     * @param attribute_id
     *     A dogma attribute ID
     */
    public ObsObjHolder<R_get_dogma_attributes_attribute_id> attributes(int attribute_id) {
        ObsObjHolderSimple<R_get_dogma_attributes_attribute_id> ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_attributes_attribute_id_holder);
            try {
                synchronized (get_dogma_attributes_attribute_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_dogma_attributes_attribute_id_holder);
                    {
                        ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_dogma_attributes_attribute_id>();
                            ObsObjHolderSimple<R_get_dogma_attributes_attribute_id> finalRet = ret;
                            get_dogma_attributes_attribute_id_holder.put(attribute_id, ret);
                            (cache).addFetchCacheObject("get_dogma_attributes_attribute_id", properties -> (cache.swagger).get_dogma_attributes(attribute_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_dogma_attributes_attribute_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_dogma_attributes_attribute_id_holder);
            }
        }
        return ret;
    }

    /**
     * Returns info about a dynamic item resulting from mutation with a mutaplasmid.
     * 
     * cache over {@link Swagger#get_dogma_dynamic_items}<br />
     * 
     * @param item_id
     *     item_id integer
     * @param type_id
     *     type_id integer
     */
    public ObsObjHolder<R_get_dogma_dynamic_items_type_id_item_id> dynamic_items(long item_id, int type_id) {
        K_11_long_int param = new K_11_long_int(item_id, type_id);
        ObsObjHolderSimple<R_get_dogma_dynamic_items_type_id_item_id> ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_dynamic_items_type_id_item_id_holder);
            try {
                synchronized (get_dogma_dynamic_items_type_id_item_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_dogma_dynamic_items_type_id_item_id_holder);
                    {
                        ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_dogma_dynamic_items_type_id_item_id>();
                            ObsObjHolderSimple<R_get_dogma_dynamic_items_type_id_item_id> finalRet = ret;
                            get_dogma_dynamic_items_type_id_item_id_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_dogma_dynamic_items_type_id_item_id", properties -> (cache.swagger).get_dogma_dynamic_items(item_id, type_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_dogma_dynamic_items_type_id_item_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_dogma_dynamic_items_type_id_item_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of dogma effect ids
     * 
     * cache over {@link Swagger#get_dogma_effects}<br />
     */
    public ObsListHolder<Integer> effects() {
        if (get_dogma_effects_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_dogma_effects_holder == null) {
                            get_dogma_effects_holder = new ObsListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_dogma_effects", (page, properties) -> (cache.swagger).get_dogma_effects(properties), arr -> get_dogma_effects_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_dogma_effects_holder;
    }

    /**
     * Get information on a dogma effect
     * 
     * cache over {@link Swagger#get_dogma_effects}<br />
     * 
     * @param effect_id
     *     A dogma effect ID
     */
    public ObsObjHolder<R_get_dogma_effects_effect_id> effects(int effect_id) {
        ObsObjHolderSimple<R_get_dogma_effects_effect_id> ret = get_dogma_effects_effect_id_holder.get(effect_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_effects_effect_id_holder);
            try {
                synchronized (get_dogma_effects_effect_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_dogma_effects_effect_id_holder);
                    {
                        ret = get_dogma_effects_effect_id_holder.get(effect_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_dogma_effects_effect_id>();
                            ObsObjHolderSimple<R_get_dogma_effects_effect_id> finalRet = ret;
                            get_dogma_effects_effect_id_holder.put(effect_id, ret);
                            (cache).addFetchCacheObject("get_dogma_effects_effect_id", properties -> (cache.swagger).get_dogma_effects(effect_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_dogma_effects_effect_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_dogma_effects_effect_id_holder);
            }
        }
        return ret;
    }
}
