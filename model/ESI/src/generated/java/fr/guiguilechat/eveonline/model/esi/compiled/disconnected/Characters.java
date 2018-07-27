package fr.guiguilechat.eveonline.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_corporationhistory;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_portrait;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Characters {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObservableList<R_get_characters_character_id_corporationhistory>> get_characters_character_id_corporationhistory_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_characters_character_id_portrait>> get_characters_character_id_portrait_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_characters_character_id>> get_characters_character_id_holder = new HashMap<>();

    public Characters(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of all the corporations a character has been a member of
     * 
     * cache over {@link Swagger#get_characters_corporationhistory}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObservableList<R_get_characters_character_id_corporationhistory> corporationhistory(int character_id) {
        ObservableList<R_get_characters_character_id_corporationhistory> ret = get_characters_character_id_corporationhistory_holder.get(character_id);
        if (ret == null) {
            synchronized (get_characters_character_id_corporationhistory_holder)
            {
                ret = get_characters_character_id_corporationhistory_holder.get(character_id);
                if (ret == null) {
                    ObservableList<R_get_characters_character_id_corporationhistory> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_characters_character_id_corporationhistory_holder.put(character_id, ret);
                    (cache).addFetchCacheArray("get_characters_character_id_corporationhistory", (page, headerHandler) -> (cache.swagger).get_characters_corporationhistory(character_id, headerHandler), arr -> {
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
     * Get portrait urls for a character
     * 
     * cache over {@link Swagger#get_characters_portrait}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public Property<R_get_characters_character_id_portrait> portrait(int character_id) {
        Property<R_get_characters_character_id_portrait> ret = get_characters_character_id_portrait_holder.get(character_id);
        if (ret == null) {
            synchronized (get_characters_character_id_portrait_holder)
            {
                ret = get_characters_character_id_portrait_holder.get(character_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_characters_character_id_portrait> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_characters_character_id_portrait_holder.put(character_id, ret);
                    (cache).addFetchCacheObject("get_characters_character_id_portrait", headerHandler -> (cache.swagger).get_characters_portrait(character_id, headerHandler), item -> {
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
     * Public information about a character
     * 
     * cache over {@link Swagger#get_characters}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public Property<R_get_characters_character_id> get(int character_id) {
        Property<R_get_characters_character_id> ret = get_characters_character_id_holder.get(character_id);
        if (ret == null) {
            synchronized (get_characters_character_id_holder)
            {
                ret = get_characters_character_id_holder.get(character_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_characters_character_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_characters_character_id_holder.put(character_id, ret);
                    (cache).addFetchCacheObject("get_characters_character_id", headerHandler -> (cache.swagger).get_characters(character_id, headerHandler), item -> {
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
