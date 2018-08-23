package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Industry {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_industry_facilities> get_industry_facilities_holder;
    private ObsListHolder<R_get_industry_systems> get_industry_systems_holder;

    public Industry(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of industry facilities
     * 
     * cache over {@link Swagger#get_industry_facilities}<br />
     */
    public ObsListHolder<R_get_industry_facilities> facilities() {
        if (get_industry_facilities_holder == null) {
            synchronized (this)
            {
                if (get_industry_facilities_holder == null) {
                    ObservableList<R_get_industry_facilities> holder = FXCollections.observableArrayList();
                    get_industry_facilities_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_industry_facilities", (page, headerHandler) -> (cache.swagger).get_industry_facilities(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_industry_facilities_holder;
    }

    /**
     * Return cost indices for solar systems
     * 
     * cache over {@link Swagger#get_industry_systems}<br />
     */
    public ObsListHolder<R_get_industry_systems> systems() {
        if (get_industry_systems_holder == null) {
            synchronized (this)
            {
                if (get_industry_systems_holder == null) {
                    ObservableList<R_get_industry_systems> holder = FXCollections.observableArrayList();
                    get_industry_systems_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_industry_systems", (page, headerHandler) -> (cache.swagger).get_industry_systems(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_industry_systems_holder;
    }
}
