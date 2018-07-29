package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Loyalty {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObservableList<R_get_loyalty_stores_corporation_id_offers>> get_loyalty_stores_corporation_id_offers_holder = new HashMap<>();

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
    public ObservableList<R_get_loyalty_stores_corporation_id_offers> stores_offers(int corporation_id) {
        ObservableList<R_get_loyalty_stores_corporation_id_offers> ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_loyalty_stores_corporation_id_offers_holder)
            {
                ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_loyalty_stores_corporation_id_offers> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    ret.add(null);
                    get_loyalty_stores_corporation_id_offers_holder.put(corporation_id, ret);
                    (cache).addFetchCacheArray("get_loyalty_stores_corporation_id_offers", (page, headerHandler) -> (cache.swagger).get_loyalty_stores_offers(corporation_id, headerHandler), arr -> {
                        synchronized (finalret)
                        {
                            finalret.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
