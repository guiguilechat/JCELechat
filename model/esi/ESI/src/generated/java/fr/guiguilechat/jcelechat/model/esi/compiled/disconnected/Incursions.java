package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_incursions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Incursions {
    public final SwaggerDCCache<?> cache;
    private ObservableList<R_get_incursions> get_incursions_holder;

    public Incursions(SwaggerDCCache<?> parent) {
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
                    get_incursions_holder.add(null);
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
