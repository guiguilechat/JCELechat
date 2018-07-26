package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Status {
    public final SwaggerCache<?> cache;
    private SimpleObjectProperty<R_get_status> get_status_holder;

    public Status(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * EVE Server status
     * 
     * cache over {@link Swagger#get_status}<br />
     */
    public Property<R_get_status> status() {
        if (get_status_holder == null) {
            synchronized (this)
            {
                if (get_status_holder == null) {
                    get_status_holder = new SimpleObjectProperty<>();
                    get_status_holder.setValue(null);
                    SimpleObjectProperty<R_get_status> finalContainer = get_status_holder;
                    (cache).addFetchCacheObject("get_status", headerHandler -> (cache.swagger).get_status(headerHandler), item -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.set(item);
                        }
                    }
                    );
                }
            }
        }
        return get_status_holder;
    }
}
