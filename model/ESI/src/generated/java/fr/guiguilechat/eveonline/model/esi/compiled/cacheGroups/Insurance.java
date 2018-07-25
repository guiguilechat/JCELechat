package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_insurance_prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Insurance {
    public final SwaggerCache<?> cache;
    private ObservableList<R_get_insurance_prices> get_insurance_prices_holder;

    public Insurance(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Return available insurance levels for all ship types
     * 
     * cache over {@link Swagger#get_insurance_prices}<br />
     */
    public ObservableList<R_get_insurance_prices> prices() {
        if (get_insurance_prices_holder == null) {
            synchronized (this)
            {
                if (get_insurance_prices_holder == null) {
                    ObservableList<R_get_insurance_prices> finalContainer = FXCollections.observableArrayList();
                    get_insurance_prices_holder = finalContainer;
                    (cache).addFetchCacheArray("get_insurance_prices", (page, headerHandler) -> (cache.swagger).get_insurance_prices(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_insurance_prices_holder;
    }
}