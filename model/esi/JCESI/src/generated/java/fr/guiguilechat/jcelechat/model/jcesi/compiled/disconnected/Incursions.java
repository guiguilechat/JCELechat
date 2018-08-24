package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_incursions;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Incursions {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_incursions> get_incursions_holder;

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
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_incursions_holder == null) {
                    ObservableList<R_get_incursions> holder = FXCollections.observableArrayList();
                    get_incursions_holder = (cache).toHolder(holder);
                    ObsListHolder<R_get_incursions> finalRet = get_incursions_holder;
                    (cache).addFetchCacheArray("get_incursions", (page, headerHandler) -> (cache.swagger).get_incursions(headerHandler), arr -> {
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
        return get_incursions_holder;
    }
}
