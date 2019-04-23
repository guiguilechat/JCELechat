package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.lelouet.collectionholders.interfaces.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Industry {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_industry_facilities> get_industry_facilities_holder;
    private ObsListHolder<R_get_industry_systems> get_industry_systems_holder;

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
                    if (get_industry_facilities_holder == null) {
                        ObservableList<R_get_industry_facilities> holder = FXCollections.observableArrayList();
                        get_industry_facilities_holder = (cache).toHolder(holder);
                        ObsListHolder<R_get_industry_facilities> finalRet = get_industry_facilities_holder;
                        (cache).addFetchCacheArray("get_industry_facilities", (page, properties) -> (cache.swagger).get_industry_facilities(properties), arr -> {
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
                    if (get_industry_systems_holder == null) {
                        ObservableList<R_get_industry_systems> holder = FXCollections.observableArrayList();
                        get_industry_systems_holder = (cache).toHolder(holder);
                        ObsListHolder<R_get_industry_systems> finalRet = get_industry_systems_holder;
                        (cache).addFetchCacheArray("get_industry_systems", (page, properties) -> (cache.swagger).get_industry_systems(properties), arr -> {
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
        return get_industry_systems_holder;
    }
}
