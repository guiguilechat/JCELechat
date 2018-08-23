package fr.guiguilechat.jcelechat.model.esi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_fleets_fleet_id_members;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_fleets_fleet_id_wings;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fleets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, ObsObjHolder<R_get_fleets_fleet_id>> get_fleets_fleet_id_holder = new HashMap<>();
    private final Map<Long, ObsListHolder<R_get_fleets_fleet_id_members>> get_fleets_fleet_id_members_holder = new HashMap<>();
    private final Map<Long, ObsListHolder<R_get_fleets_fleet_id_wings>> get_fleets_fleet_id_wings_holder = new HashMap<>();

    public Fleets(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return details about a fleet
     * 
     * cache over {@link Swagger#get_fleets}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsObjHolder<R_get_fleets_fleet_id> get(long fleet_id) {
        ObsObjHolder<R_get_fleets_fleet_id> ret = get_fleets_fleet_id_holder.get(fleet_id);
        if (ret == null) {
            synchronized (get_fleets_fleet_id_holder)
            {
                ret = get_fleets_fleet_id_holder.get(fleet_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_fleets_fleet_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_fleets_fleet_id_holder.put(fleet_id, ret);
                    (cache).addFetchCacheObject("get_fleets_fleet_id", headerHandler -> (cache.swagger).get_fleets(fleet_id, headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Return information about fleet members
     * 
     * cache over {@link Swagger#get_fleets_members}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsListHolder<R_get_fleets_fleet_id_members> members(long fleet_id) {
        ObsListHolder<R_get_fleets_fleet_id_members> ret = get_fleets_fleet_id_members_holder.get(fleet_id);
        if (ret == null) {
            synchronized (get_fleets_fleet_id_members_holder)
            {
                ret = get_fleets_fleet_id_members_holder.get(fleet_id);
                if (ret == null) {
                    ObservableList<R_get_fleets_fleet_id_members> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_fleets_fleet_id_members_holder.put(fleet_id, ret);
                    (cache).addFetchCacheArray("get_fleets_fleet_id_members", (page, headerHandler) -> (cache.swagger).get_fleets_members(fleet_id, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Return information about wings in a fleet
     * 
     * cache over {@link Swagger#get_fleets_wings}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsListHolder<R_get_fleets_fleet_id_wings> wings(long fleet_id) {
        ObsListHolder<R_get_fleets_fleet_id_wings> ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
        if (ret == null) {
            synchronized (get_fleets_fleet_id_wings_holder)
            {
                ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
                if (ret == null) {
                    ObservableList<R_get_fleets_fleet_id_wings> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_fleets_fleet_id_wings_holder.put(fleet_id, ret);
                    (cache).addFetchCacheArray("get_fleets_fleet_id_wings", (page, headerHandler) -> (cache.swagger).get_fleets_wings(fleet_id, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
