package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_alliances_alliance_id_icons;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alliances {
    public final SwaggerDCCache<?> cache;
    private ObservableList<Integer> get_alliances_holder;
    private final Map<Integer, ObservableList<Integer>> get_alliances_alliance_id_corporations_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_alliances_alliance_id>> get_alliances_alliance_id_holder = new HashMap<>();

    public Alliances(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * List all active player alliances
     * 
     * cache over {@link Swagger#get_alliances}<br />
     */
    public ObservableList<Integer> alliances() {
        if (get_alliances_holder == null) {
            synchronized (this)
            {
                if (get_alliances_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_alliances_holder = finalContainer;
                    get_alliances_holder.add(null);
                    (cache).addFetchCacheArray("get_alliances", (page, headerHandler) -> IntStream.of((cache.swagger).get_alliances(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_alliances_holder;
    }

    /**
     * List all current member corporations of an alliance
     * 
     * cache over {@link Swagger#get_alliances_corporations}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObservableList<Integer> corporations(int alliance_id) {
        ObservableList<Integer> ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
        if (ret == null) {
            synchronized (get_alliances_alliance_id_corporations_holder)
            {
                ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
                if (ret == null) {
                    ObservableList<Integer> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_alliances_alliance_id_corporations_holder.put(alliance_id, ret);
                    (cache).addFetchCacheArray("get_alliances_alliance_id_corporations", (page, headerHandler) -> IntStream.of((cache.swagger).get_alliances_corporations(alliance_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
     * Get the icon urls for a alliance
     * 
     * cache over {@link Swagger#get_alliances_icons}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public Property<R_get_alliances_alliance_id_icons> icons(int alliance_id) {
        Property<R_get_alliances_alliance_id_icons> ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
        if (ret == null) {
            synchronized (get_alliances_alliance_id_icons_holder)
            {
                ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_alliances_alliance_id_icons> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_alliances_alliance_id_icons_holder.put(alliance_id, ret);
                    (cache).addFetchCacheObject("get_alliances_alliance_id_icons", headerHandler -> (cache.swagger).get_alliances_icons(alliance_id, headerHandler), item -> {
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
     * Public information about an alliance
     * 
     * cache over {@link Swagger#get_alliances}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public Property<R_get_alliances_alliance_id> get(int alliance_id) {
        Property<R_get_alliances_alliance_id> ret = get_alliances_alliance_id_holder.get(alliance_id);
        if (ret == null) {
            synchronized (get_alliances_alliance_id_holder)
            {
                ret = get_alliances_alliance_id_holder.get(alliance_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_alliances_alliance_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_alliances_alliance_id_holder.put(alliance_id, ret);
                    (cache).addFetchCacheObject("get_alliances_alliance_id", headerHandler -> (cache.swagger).get_alliances(alliance_id, headerHandler), item -> {
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
