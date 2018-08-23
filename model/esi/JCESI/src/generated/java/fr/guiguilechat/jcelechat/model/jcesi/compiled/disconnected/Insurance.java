package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_insurance_prices;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Insurance {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_insurance_prices> get_insurance_prices_holder;

    public Insurance(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return available insurance levels for all ship types
     * 
     * cache over {@link Swagger#get_insurance_prices}<br />
     */
    public ObsListHolder<R_get_insurance_prices> prices() {
        if (get_insurance_prices_holder == null) {
            synchronized (this)
            {
                if (get_insurance_prices_holder == null) {
                    ObservableList<R_get_insurance_prices> holder = FXCollections.observableArrayList();
                    get_insurance_prices_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_insurance_prices", (page, headerHandler) -> (cache.swagger).get_insurance_prices(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_insurance_prices_holder;
    }
}
