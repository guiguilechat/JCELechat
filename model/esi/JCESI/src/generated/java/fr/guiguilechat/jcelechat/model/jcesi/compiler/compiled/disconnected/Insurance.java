package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Insurance {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_insurance_prices> get_insurance_prices_holder;

    public Insurance(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return available insurance levels for all ship types
     * 
     * cache over {@link Swagger#get_insurance_prices}<br />
     */
    public ObsListHolder<R_get_insurance_prices> prices() {
        if (get_insurance_prices_holder == null) {
            LockWatchDog.BARKER.tak(this);
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_insurance_prices_holder == null) {
                    ObservableList<R_get_insurance_prices> holder = FXCollections.observableArrayList();
                    get_insurance_prices_holder = (cache).toHolder(holder);
                    ObsListHolder<R_get_insurance_prices> finalRet = get_insurance_prices_holder;
                    (cache).addFetchCacheArray("get_insurance_prices", (page, headerHandler) -> (cache.swagger).get_insurance_prices(headerHandler), arr -> {
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
            LockWatchDog.BARKER.rel(this);
        }
        return get_insurance_prices_holder;
    }
}