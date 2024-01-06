package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Loyalty {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ListHolderImpl<R_get_loyalty_stores_corporation_id_offers>> get_loyalty_stores_corporation_id_offers_holder = new HashMap<>();

    public Loyalty(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of offers from a specific corporation's loyalty store
     * 
     * cache over {@link Swagger#get_loyalty_stores_offers}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_loyalty_stores_corporation_id_offers> stores_offers(int corporation_id) {
        ListHolderImpl<R_get_loyalty_stores_corporation_id_offers> ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_loyalty_stores_corporation_id_offers_holder);
            try {
                synchronized (get_loyalty_stores_corporation_id_offers_holder)
                {
                    LockWatchDog.BARKER.hld(get_loyalty_stores_corporation_id_offers_holder);
                    {
                        ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_loyalty_stores_corporation_id_offers>();
                            get_loyalty_stores_corporation_id_offers_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_loyalty_stores_corporation_id_offers> finalRet = ret;
                            (cache).addFetchCacheArray("get_loyalty_stores_corporation_id_offers", (page, properties) -> (cache.swagger).get_loyalty_stores_offers(corporation_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_loyalty_stores_corporation_id_offers_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_loyalty_stores_corporation_id_offers_holder);
            }
        }
        return ret;
    }
}
