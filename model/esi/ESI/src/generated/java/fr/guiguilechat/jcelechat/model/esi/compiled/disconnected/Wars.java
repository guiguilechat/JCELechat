package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_wars_war_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Wars {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObservableList<Integer>> get_wars_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_wars_war_id>> get_wars_war_id_holder = new HashMap<>();
    private final Map<Integer, ObservableList<M_get_killmails_2>> get_wars_war_id_killmails_holder = new HashMap<>();

    public Wars(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of wars
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param max_war_id
     *     Only return wars with ID smaller than this.
     */
    public ObservableList<Integer> wars(Integer max_war_id) {
        ObservableList<Integer> ret = get_wars_holder.get(max_war_id);
        if (ret == null) {
            synchronized (get_wars_holder)
            {
                ret = get_wars_holder.get(max_war_id);
                if (ret == null) {
                    ObservableList<Integer> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_wars_holder.put(max_war_id, ret);
                    (cache).addFetchCacheArray("get_wars", (page, headerHandler) -> IntStream.of((cache.swagger).get_wars(max_war_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
     * Return details about a war
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param war_id
     *     ID for a war
     */
    public Property<R_get_wars_war_id> get(int war_id) {
        Property<R_get_wars_war_id> ret = get_wars_war_id_holder.get(war_id);
        if (ret == null) {
            synchronized (get_wars_war_id_holder)
            {
                ret = get_wars_war_id_holder.get(war_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_wars_war_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_wars_war_id_holder.put(war_id, ret);
                    (cache).addFetchCacheObject("get_wars_war_id", headerHandler -> (cache.swagger).get_wars(war_id, headerHandler), item -> {
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
     * Return a list of kills related to a war
     * 
     * cache over {@link Swagger#get_wars_killmails}<br />
     * 
     * @param war_id
     *     A valid war ID
     */
    public ObservableList<M_get_killmails_2> killmails(int war_id) {
        ObservableList<M_get_killmails_2> ret = get_wars_war_id_killmails_holder.get(war_id);
        if (ret == null) {
            synchronized (get_wars_war_id_killmails_holder)
            {
                ret = get_wars_war_id_killmails_holder.get(war_id);
                if (ret == null) {
                    ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_wars_war_id_killmails_holder.put(war_id, ret);
                    (cache).addFetchCacheArray("get_wars_war_id_killmails", (page, headerHandler) -> (cache.swagger).get_wars_killmails(page, war_id, headerHandler), arr -> {
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
