package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

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
                    {
                        if (get_status_holder == null) {
                            ObsObjHolderSimple<R_get_status> holder = new ObsObjHolderSimple<>();
                            get_status_holder = holder;
                            (cache).addFetchCacheObject("get_status", properties -> (cache.swagger).get_status(properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_status_holder;
    }
}
