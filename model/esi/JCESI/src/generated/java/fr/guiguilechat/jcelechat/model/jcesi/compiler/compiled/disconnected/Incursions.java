package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_incursions;
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
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    if (get_incursions_holder == null) {
                        ObservableList<R_get_incursions> holder = FXCollections.observableArrayList();
                        get_incursions_holder = (cache).toHolder(holder);
                        ObsListHolder<R_get_incursions> finalRet = get_incursions_holder;
                        (cache).addFetchCacheArray("get_incursions", (page, properties) -> (cache.swagger).get_incursions(properties), arr -> {
                            LockWatchDog.BARKER.tak(holder);
                            try {
                                synchronized (holder)
                                {
                                    LockWatchDog.BARKER.hld(holder);
                                    holder.clear();
                                    if (arr!= null) {
                                        holder.addAll(arr);
                                    }
                                }
                            } finally {
                                LockWatchDog.BARKER.rel(holder);
                            }
                            finalRet.dataReceived();
                        }
                        );
                    }
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_incursions_holder;
    }
}
