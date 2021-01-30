package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Industry {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<R_get_industry_facilities> get_industry_facilities_holder;
    private ObsListHolderImpl<R_get_industry_systems> get_industry_systems_holder;

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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_industry_facilities_holder == null) {
                            get_industry_facilities_holder = new ObsListHolderImpl<R_get_industry_facilities>();
                            (cache).addFetchCacheArray("get_industry_facilities", (page, properties) -> (cache.swagger).get_industry_facilities(properties), arr -> get_industry_facilities_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_industry_systems_holder == null) {
                            get_industry_systems_holder = new ObsListHolderImpl<R_get_industry_systems>();
                            (cache).addFetchCacheArray("get_industry_systems", (page, properties) -> (cache.swagger).get_industry_systems(properties), arr -> get_industry_systems_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_industry_systems_holder;
    }
}
