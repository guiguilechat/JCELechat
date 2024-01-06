package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_structures;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Sovereignty {
    public final SwaggerDCCache<?> cache;
    private ListHolderImpl<R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
    private ListHolderImpl<R_get_sovereignty_map> get_sovereignty_map_holder;
    private ListHolderImpl<R_get_sovereignty_structures> get_sovereignty_structures_holder;

    public Sovereignty(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Shows sovereignty data for campaigns.
     * 
     * cache over {@link Swagger#get_sovereignty_campaigns}<br />
     */
    public ListHolder<R_get_sovereignty_campaigns> campaigns() {
        if (get_sovereignty_campaigns_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_campaigns_holder == null) {
                            get_sovereignty_campaigns_holder = new ListHolderImpl<R_get_sovereignty_campaigns>();
                            (cache).addFetchCacheArray("get_sovereignty_campaigns", (page, properties) -> (cache.swagger).get_sovereignty_campaigns(properties), arr -> get_sovereignty_campaigns_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_sovereignty_campaigns_holder;
    }

    /**
     * Shows sovereignty information for solar systems
     * 
     * cache over {@link Swagger#get_sovereignty_map}<br />
     */
    public ListHolder<R_get_sovereignty_map> map() {
        if (get_sovereignty_map_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_map_holder == null) {
                            get_sovereignty_map_holder = new ListHolderImpl<R_get_sovereignty_map>();
                            (cache).addFetchCacheArray("get_sovereignty_map", (page, properties) -> (cache.swagger).get_sovereignty_map(properties), arr -> get_sovereignty_map_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_sovereignty_map_holder;
    }

    /**
     * Shows sovereignty data for structures.
     * 
     * cache over {@link Swagger#get_sovereignty_structures}<br />
     */
    public ListHolder<R_get_sovereignty_structures> structures() {
        if (get_sovereignty_structures_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_structures_holder == null) {
                            get_sovereignty_structures_holder = new ListHolderImpl<R_get_sovereignty_structures>();
                            (cache).addFetchCacheArray("get_sovereignty_structures", (page, properties) -> (cache.swagger).get_sovereignty_structures(properties), arr -> get_sovereignty_structures_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_sovereignty_structures_holder;
    }
}
