package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_status;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;

public class Status {
    public final SwaggerDCCache<?> cache;
    private ObsObjHolder<R_get_status> get_status_holder;

    public Status(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * EVE Server status
     * 
     * cache over {@link Swagger#get_status}<br />
     */
    public ObsObjHolder<R_get_status> status() {
        if (get_status_holder == null) {
            synchronized (this)
            {
                if (get_status_holder == null) {
                    SimpleObjectProperty<R_get_status> holder = new SimpleObjectProperty<>();
                    get_status_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheObject("get_status", headerHandler -> (cache.swagger).get_status(headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return get_status_holder;
    }
}
