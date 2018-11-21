package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_12_long_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dogma {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<Integer> get_dogma_attributes_holder;
    private final Map<Integer, ObsObjHolder<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_holder = new HashMap<>();
    private final Map<K_12_long_int, ObsObjHolder<R_get_dogma_dynamic_items_type_id_item_id>> get_dogma_dynamic_items_type_id_item_id_holder = new HashMap<>();
    private ObsListHolder<Integer> get_dogma_effects_holder;
    private final Map<Integer, ObsObjHolder<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_holder = new HashMap<>();

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
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_dogma_attributes_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_dogma_attributes_holder = (cache).toHolder(holder);
                    ObsListHolder<Integer> finalRet = get_dogma_attributes_holder;
                    (cache).addFetchCacheArray("get_dogma_attributes", (page, headerHandler) -> IntStream.of((cache.swagger).get_dogma_attributes(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.setAll(arr);
                            finalRet.dataReceived();
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(this);
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
        ObsObjHolder<R_get_dogma_attributes_attribute_id> ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_attributes_attribute_id_holder);
            synchronized (get_dogma_attributes_attribute_id_holder)
            {
                LockWatchDog.BARKER.hld(get_dogma_attributes_attribute_id_holder);
                ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_attributes_attribute_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_dogma_attributes_attribute_id_holder.put(attribute_id, ret);
                    (cache).addFetchCacheObject("get_dogma_attributes_attribute_id", headerHandler -> (cache.swagger).get_dogma_attributes(attribute_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_dogma_attributes_attribute_id_holder);
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
        K_12_long_int param = new K_12_long_int(item_id, type_id);
        ObsObjHolder<R_get_dogma_dynamic_items_type_id_item_id> ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_dynamic_items_type_id_item_id_holder);
            synchronized (get_dogma_dynamic_items_type_id_item_id_holder)
            {
                LockWatchDog.BARKER.hld(get_dogma_dynamic_items_type_id_item_id_holder);
                ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_dynamic_items_type_id_item_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_dogma_dynamic_items_type_id_item_id_holder.put(param, ret);
                    (cache).addFetchCacheObject("get_dogma_dynamic_items_type_id_item_id", headerHandler -> (cache.swagger).get_dogma_dynamic_items(item_id, type_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_dogma_dynamic_items_type_id_item_id_holder);
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
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_dogma_effects_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_dogma_effects_holder = (cache).toHolder(holder);
                    ObsListHolder<Integer> finalRet = get_dogma_effects_holder;
                    (cache).addFetchCacheArray("get_dogma_effects", (page, headerHandler) -> IntStream.of((cache.swagger).get_dogma_effects(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.setAll(arr);
                            finalRet.dataReceived();
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(this);
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
        ObsObjHolder<R_get_dogma_effects_effect_id> ret = get_dogma_effects_effect_id_holder.get(effect_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_dogma_effects_effect_id_holder);
            synchronized (get_dogma_effects_effect_id_holder)
            {
                LockWatchDog.BARKER.hld(get_dogma_effects_effect_id_holder);
                ret = get_dogma_effects_effect_id_holder.get(effect_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_effects_effect_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_dogma_effects_effect_id_holder.put(effect_id, ret);
                    (cache).addFetchCacheObject("get_dogma_effects_effect_id", headerHandler -> (cache.swagger).get_dogma_effects(effect_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_dogma_effects_effect_id_holder);
        }
        return ret;
    }
}
