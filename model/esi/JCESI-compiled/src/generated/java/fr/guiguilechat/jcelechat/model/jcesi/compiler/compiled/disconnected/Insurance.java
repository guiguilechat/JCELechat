package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Insurance {
    public final SwaggerDCCache<?> cache;
    private ListHolderImpl<R_get_insurance_prices> get_insurance_prices_holder;

    public Insurance(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return available insurance levels for all ship types
     * 
     * cache over {@link Swagger#get_insurance_prices}<br />
     */
    public ListHolder<R_get_insurance_prices> prices() {
        if (get_insurance_prices_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_insurance_prices_holder == null) {
                            get_insurance_prices_holder = new ListHolderImpl<R_get_insurance_prices>();
                            (cache).addFetchCacheArray("get_insurance_prices", (page, properties) -> (cache.swagger).get_insurance_prices(properties), arr -> get_insurance_prices_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_insurance_prices_holder;
    }
}
