package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_structures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Sovereignty {
    public final SwaggerCache<?> cache;
    private ObservableMap<Integer, R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
    private ObservableList<R_get_sovereignty_map> get_sovereignty_map_holder;
    private ObservableMap<Long, R_get_sovereignty_structures> get_sovereignty_structures_holder;

    public Sovereignty(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Shows sovereignty data for campaigns.
     * 
     * cache over {@link Swagger#get_sovereignty_campaigns}<br />
     */
    public ObservableMap<Integer, R_get_sovereignty_campaigns> campaigns() {
        if (get_sovereignty_campaigns_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_campaigns_holder == null) {
                    get_sovereignty_campaigns_holder = FXCollections.observableHashMap();
                    ObservableMap<Integer, R_get_sovereignty_campaigns> finalContainer = get_sovereignty_campaigns_holder;
                    (cache).addFetchCacheArray("get_sovereignty_campaigns", (page, headerHandler) -> (cache.swagger).get_sovereignty_campaigns(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            LinkedHashMap<Integer, R_get_sovereignty_campaigns> newmap = new LinkedHashMap<>();
                            for (R_get_sovereignty_campaigns val: arr) {
                                newmap.put((val.campaign_id), (val));
                            }
                            finalContainer.entrySet();
                            finalContainer.putAll(newmap);
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
    public ObservableList<R_get_sovereignty_map> map() {
        if (get_sovereignty_map_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_map_holder == null) {
                    ObservableList<R_get_sovereignty_map> finalContainer = FXCollections.observableArrayList();
                    get_sovereignty_map_holder = finalContainer;
                    (cache).addFetchCacheArray("get_sovereignty_map", (page, headerHandler) -> (cache.swagger).get_sovereignty_map(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
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
    public ObservableMap<Long, R_get_sovereignty_structures> structures() {
        if (get_sovereignty_structures_holder == null) {
            synchronized (this)
            {
                if (get_sovereignty_structures_holder == null) {
                    get_sovereignty_structures_holder = FXCollections.observableHashMap();
                    ObservableMap<Long, R_get_sovereignty_structures> finalContainer = get_sovereignty_structures_holder;
                    (cache).addFetchCacheArray("get_sovereignty_structures", (page, headerHandler) -> (cache.swagger).get_sovereignty_structures(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            LinkedHashMap<Long, R_get_sovereignty_structures> newmap = new LinkedHashMap<>();
                            for (R_get_sovereignty_structures val: arr) {
                                newmap.put((val.structure_id), (val));
                            }
                            finalContainer.entrySet();
                            finalContainer.putAll(newmap);
                        }
                    }
                    );
                }
            }
        }
        return get_sovereignty_structures_holder;
    }
}
