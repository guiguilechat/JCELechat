package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Loyalty {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObsListHolder<R_get_loyalty_stores_corporation_id_offers>> get_loyalty_stores_corporation_id_offers_holder = new HashMap<>();

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
    public ObsListHolder<R_get_loyalty_stores_corporation_id_offers> stores_offers(int corporation_id) {
        ObsListHolder<R_get_loyalty_stores_corporation_id_offers> ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_loyalty_stores_corporation_id_offers_holder);
            synchronized (get_loyalty_stores_corporation_id_offers_holder)
            {
                LockWatchDog.BARKER.hld(get_loyalty_stores_corporation_id_offers_holder);
                ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_loyalty_stores_corporation_id_offers> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_loyalty_stores_corporation_id_offers_holder.put(corporation_id, ret);
                    ObsListHolder<R_get_loyalty_stores_corporation_id_offers> finalRet = ret;
                    (cache).addFetchCacheArray("get_loyalty_stores_corporation_id_offers", (page, headerHandler) -> (cache.swagger).get_loyalty_stores_offers(corporation_id, headerHandler), arr -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.setAll(arr);
                            finalRet.dataReceived();
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_loyalty_stores_corporation_id_offers_holder);
        }
        return ret;
    }
}
