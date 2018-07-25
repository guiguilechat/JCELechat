package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_incursions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Incursions {
    public final SwaggerCache<?> cache;
    private ObservableList<R_get_incursions> get_incursions_holder;

    public Incursions(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of current incursions
     * 
     * cache over {@link Swagger#get_incursions}<br />
     */
    public ObservableList<R_get_incursions> incursions() {
        if (get_incursions_holder == null) {
            synchronized (this)
            {
                if (get_incursions_holder == null) {
                    ObservableList<R_get_incursions> finalContainer = FXCollections.observableArrayList();
                    get_incursions_holder = finalContainer;
                    (cache).addFetchCacheArray("get_incursions", (page, headerHandler) -> (cache.swagger).get_incursions(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_incursions_holder;
    }
}
