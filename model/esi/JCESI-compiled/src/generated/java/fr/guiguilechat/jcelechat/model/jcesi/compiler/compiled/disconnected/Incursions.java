package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_incursions;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Incursions {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<R_get_incursions> get_incursions_holder;

    public Incursions(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of current incursions
     * 
     * cache over {@link Swagger#get_incursions}<br />
     */
    public ObsListHolder<R_get_incursions> incursions() {
        if (get_incursions_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_incursions_holder == null) {
                            get_incursions_holder = new ObsListHolderImpl<R_get_incursions>();
                            (cache).addFetchCacheArray("get_incursions", (page, properties) -> (cache.swagger).get_incursions(properties), arr -> get_incursions_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_incursions_holder;
    }
}
