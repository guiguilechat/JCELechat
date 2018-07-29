package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_corporations_corporation_id_alliancehistory;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_corporations_corporation_id_icons;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Corporations {
    public final SwaggerDCCache<?> cache;
    private ObservableList<Integer> get_corporations_npccorps_holder;
    private final Map<Integer, Property<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_holder = new HashMap<>();
    private final Map<Integer, ObservableList<R_get_corporations_corporation_id_alliancehistory>> get_corporations_corporation_id_alliancehistory_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_corporations_corporation_id>> get_corporations_corporation_id_holder = new HashMap<>();

    public Corporations(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of npc corporations
     * 
     * cache over {@link Swagger#get_corporations_npccorps}<br />
     */
    public ObservableList<Integer> npccorps() {
        if (get_corporations_npccorps_holder == null) {
            synchronized (this)
            {
                if (get_corporations_npccorps_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_corporations_npccorps_holder = finalContainer;
                    get_corporations_npccorps_holder.add(null);
                    (cache).addFetchCacheArray("get_corporations_npccorps", (page, headerHandler) -> IntStream.of((cache.swagger).get_corporations_npccorps(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_corporations_npccorps_holder;
    }

    /**
     * Get the icon urls for a corporation
     * 
     * cache over {@link Swagger#get_corporations_icons}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public Property<R_get_corporations_corporation_id_icons> icons(int corporation_id) {
        Property<R_get_corporations_corporation_id_icons> ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_corporations_corporation_id_icons_holder)
            {
                ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_corporations_corporation_id_icons> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_corporations_corporation_id_icons_holder.put(corporation_id, ret);
                    (cache).addFetchCacheObject("get_corporations_corporation_id_icons", headerHandler -> (cache.swagger).get_corporations_icons(corporation_id, headerHandler), item -> {
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
     * Get a list of all the alliances a corporation has been a member of
     * 
     * cache over {@link Swagger#get_corporations_alliancehistory}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObservableList<R_get_corporations_corporation_id_alliancehistory> alliancehistory(int corporation_id) {
        ObservableList<R_get_corporations_corporation_id_alliancehistory> ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_corporations_corporation_id_alliancehistory_holder)
            {
                ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_corporations_corporation_id_alliancehistory> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_corporations_corporation_id_alliancehistory_holder.put(corporation_id, ret);
                    (cache).addFetchCacheArray("get_corporations_corporation_id_alliancehistory", (page, headerHandler) -> (cache.swagger).get_corporations_alliancehistory(corporation_id, headerHandler), arr -> {
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
     * Public information about a corporation
     * 
     * cache over {@link Swagger#get_corporations}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public Property<R_get_corporations_corporation_id> get(int corporation_id) {
        Property<R_get_corporations_corporation_id> ret = get_corporations_corporation_id_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_corporations_corporation_id_holder)
            {
                ret = get_corporations_corporation_id_holder.get(corporation_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_corporations_corporation_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_corporations_corporation_id_holder.put(corporation_id, ret);
                    (cache).addFetchCacheObject("get_corporations_corporation_id", headerHandler -> (cache.swagger).get_corporations(corporation_id, headerHandler), item -> {
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
