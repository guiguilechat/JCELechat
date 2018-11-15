package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_corporationhistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_portrait;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Characters {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObsListHolder<R_get_characters_character_id_corporationhistory>> get_characters_character_id_corporationhistory_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_characters_character_id_portrait>> get_characters_character_id_portrait_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_characters_character_id>> get_characters_character_id_holder = new HashMap<>();

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
    public ObsListHolder<R_get_characters_character_id_corporationhistory> corporationhistory(int character_id) {
        ObsListHolder<R_get_characters_character_id_corporationhistory> ret = get_characters_character_id_corporationhistory_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_corporationhistory_holder);
            synchronized (get_characters_character_id_corporationhistory_holder)
            {
                LockWatchDog.BARKER.hld(get_characters_character_id_corporationhistory_holder);
                ret = get_characters_character_id_corporationhistory_holder.get(character_id);
                if (ret == null) {
                    ObservableList<R_get_characters_character_id_corporationhistory> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_characters_character_id_corporationhistory_holder.put(character_id, ret);
                    ObsListHolder<R_get_characters_character_id_corporationhistory> finalRet = ret;
                    (cache).addFetchCacheArray("get_characters_character_id_corporationhistory", (page, headerHandler) -> (cache.swagger).get_characters_corporationhistory(character_id, headerHandler), arr -> {
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
            LockWatchDog.BARKER.rel(get_characters_character_id_corporationhistory_holder);
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
    public ObsObjHolder<R_get_characters_character_id_portrait> portrait(int character_id) {
        ObsObjHolder<R_get_characters_character_id_portrait> ret = get_characters_character_id_portrait_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_portrait_holder);
            synchronized (get_characters_character_id_portrait_holder)
            {
                LockWatchDog.BARKER.hld(get_characters_character_id_portrait_holder);
                ret = get_characters_character_id_portrait_holder.get(character_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_characters_character_id_portrait> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_characters_character_id_portrait_holder.put(character_id, ret);
                    (cache).addFetchCacheObject("get_characters_character_id_portrait", headerHandler -> (cache.swagger).get_characters_portrait(character_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_characters_character_id_portrait_holder);
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
    public ObsObjHolder<R_get_characters_character_id> get(int character_id) {
        ObsObjHolder<R_get_characters_character_id> ret = get_characters_character_id_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_holder);
            synchronized (get_characters_character_id_holder)
            {
                LockWatchDog.BARKER.hld(get_characters_character_id_holder);
                ret = get_characters_character_id_holder.get(character_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_characters_character_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_characters_character_id_holder.put(character_id, ret);
                    (cache).addFetchCacheObject("get_characters_character_id", headerHandler -> (cache.swagger).get_characters(character_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_characters_character_id_holder);
        }
        return ret;
    }
}
