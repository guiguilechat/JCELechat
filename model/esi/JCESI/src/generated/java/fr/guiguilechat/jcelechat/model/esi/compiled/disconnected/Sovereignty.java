package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_sovereignty_structures;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsMapHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Sovereignty {
    public final SwaggerDCCache<?> cache;
    private ObsMapHolder<Integer, R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
    private ObsListHolder<R_get_sovereignty_map> get_sovereignty_map_holder;
    private ObsMapHolder<Long, R_get_sovereignty_structures> get_sovereignty_structures_holder;

    public Sovereignty(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Shows sovereignty data for campaigns.
     * 
     * cache over {@link Swagger#get_sovereignty_campaigns}<br />
     */
    public ObsMapHolder<Integer, R_get_sovereignty_campaigns> campaigns() {
        if (get_sovereignty_campaigns_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_campaigns_holder == null) {
                    ObservableMap<Integer, R_get_sovereignty_campaigns> holder = FXCollections.observableHashMap();
                    get_sovereignty_campaigns_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_sovereignty_campaigns", (page, headerHandler) -> (cache.swagger).get_sovereignty_campaigns(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            LinkedHashMap<Integer, R_get_sovereignty_campaigns> newmap = new LinkedHashMap<>();
                            for (R_get_sovereignty_campaigns val: arr) {
                                newmap.put((val.campaign_id), (val));
                            }
                            holder.entrySet().retainAll(newmap.entrySet());
                            holder.putAll(newmap);
                        }
                    }
                    );
                }
            }
        }
        return get_sovereignty_campaigns_holder;
    }

    /**
     * Shows sovereignty information for solar systems
     * 
     * cache over {@link Swagger#get_sovereignty_map}<br />
     */
    public ObsListHolder<R_get_sovereignty_map> map() {
        if (get_sovereignty_map_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_map_holder == null) {
                    ObservableList<R_get_sovereignty_map> holder = FXCollections.observableArrayList();
                    get_sovereignty_map_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_sovereignty_map", (page, headerHandler) -> (cache.swagger).get_sovereignty_map(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_sovereignty_map_holder;
    }

    /**
     * Shows sovereignty data for structures.
     * 
     * cache over {@link Swagger#get_sovereignty_structures}<br />
     */
    public ObsMapHolder<Long, R_get_sovereignty_structures> structures() {
        if (get_sovereignty_structures_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_structures_holder == null) {
                    ObservableMap<Long, R_get_sovereignty_structures> holder = FXCollections.observableHashMap();
                    get_sovereignty_structures_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_sovereignty_structures", (page, headerHandler) -> (cache.swagger).get_sovereignty_structures(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            LinkedHashMap<Long, R_get_sovereignty_structures> newmap = new LinkedHashMap<>();
                            for (R_get_sovereignty_structures val: arr) {
                                newmap.put((val.structure_id), (val));
                            }
                            holder.entrySet().retainAll(newmap.entrySet());
                            holder.putAll(newmap);
                        }
                    }
                    );
                }
            }
        }
        return get_sovereignty_structures_holder;
    }
}
