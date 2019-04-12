package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    if (get_status_holder == null) {
                        SimpleObjectProperty<R_get_status> holder = new SimpleObjectProperty<>();
                        get_status_holder = (cache).toHolder(holder);
                        (cache).addFetchCacheObject("get_status", properties -> (cache.swagger).get_status(properties), item -> {
                            LockWatchDog.BARKER.tak(holder);
                            try {
                                synchronized (holder)
                                {
                                    LockWatchDog.BARKER.hld(holder);
                                    holder.set(item);
                                }
                            } finally {
                                LockWatchDog.BARKER.rel(holder);
                            }
                        }
                        );
                    }
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_status_holder;
    }
}
