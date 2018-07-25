package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_12_long_int;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_effects_effect_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dogma {
    public final SwaggerCache<?> cache;
    private ObservableList<Integer> get_dogma_attributes_holder;
    private final Map<Integer, Property<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_holder = new HashMap<>();
    private final Map<K_12_long_int, Property<R_get_dogma_dynamic_items_type_id_item_id>> get_dogma_dynamic_items_type_id_item_id_holder = new HashMap<>();
    private ObservableList<Integer> get_dogma_effects_holder;
    private final Map<Integer, Property<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_holder = new HashMap<>();

    public Dogma(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of dogma attribute ids
     * 
     * cache over {@link Swagger#get_dogma_attributes}<br />
     */
    public ObservableList<Integer> attributes() {
        if (get_dogma_attributes_holder == null) {
            synchronized (this)
            {
                if (get_dogma_attributes_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_dogma_attributes_holder = finalContainer;
                    (cache).addFetchCacheArray("get_dogma_attributes", (page, headerHandler) -> IntStream.of((cache.swagger).get_dogma_attributes(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
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
    public Property<R_get_dogma_attributes_attribute_id> attributes(int attribute_id) {
        Property<R_get_dogma_attributes_attribute_id> ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
        if (ret == null) {
            synchronized (get_dogma_attributes_attribute_id_holder)
            {
                ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_attributes_attribute_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_dogma_attributes_attribute_id_holder.put(attribute_id, ret);
                    (cache).addFetchCacheObject("get_dogma_attributes_attribute_id", headerHandler -> (cache.swagger).get_dogma_attributes(attribute_id, headerHandler), item -> {
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
     * Returns info about a dynamic item resulting from mutation with a mutaplasmid.
     * 
     * cache over {@link Swagger#get_dogma_dynamic_items}<br />
     * 
     * @param item_id
     *     item_id integer
     * @param type_id
     *     type_id integer
     */
    public Property<R_get_dogma_dynamic_items_type_id_item_id> dynamic_items(long item_id, int type_id) {
        K_12_long_int param = new K_12_long_int(item_id, type_id);
        Property<R_get_dogma_dynamic_items_type_id_item_id> ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
        if (ret == null) {
            synchronized (get_dogma_dynamic_items_type_id_item_id_holder)
            {
                ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_dynamic_items_type_id_item_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_dogma_dynamic_items_type_id_item_id_holder.put(param, ret);
                    (cache).addFetchCacheObject("get_dogma_dynamic_items_type_id_item_id", headerHandler -> (cache.swagger).get_dogma_dynamic_items(item_id, type_id, headerHandler), item -> {
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
     * Get a list of dogma effect ids
     * 
     * cache over {@link Swagger#get_dogma_effects}<br />
     */
    public ObservableList<Integer> effects() {
        if (get_dogma_effects_holder == null) {
            synchronized (this)
            {
                if (get_dogma_effects_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_dogma_effects_holder = finalContainer;
                    (cache).addFetchCacheArray("get_dogma_effects", (page, headerHandler) -> IntStream.of((cache.swagger).get_dogma_effects(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
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
    public Property<R_get_dogma_effects_effect_id> effects(int effect_id) {
        Property<R_get_dogma_effects_effect_id> ret = get_dogma_effects_effect_id_holder.get(effect_id);
        if (ret == null) {
            synchronized (get_dogma_effects_effect_id_holder)
            {
                ret = get_dogma_effects_effect_id_holder.get(effect_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_dogma_effects_effect_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_dogma_effects_effect_id_holder.put(effect_id, ret);
                    (cache).addFetchCacheObject("get_dogma_effects_effect_id", headerHandler -> (cache.swagger).get_dogma_effects(effect_id, headerHandler), item -> {
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
}
