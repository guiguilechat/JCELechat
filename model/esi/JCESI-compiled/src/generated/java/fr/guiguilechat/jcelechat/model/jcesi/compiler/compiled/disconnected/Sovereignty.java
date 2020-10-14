package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_structures;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sovereignty {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
    private ObsListHolderImpl<R_get_sovereignty_map> get_sovereignty_map_holder;
    private ObsListHolderImpl<R_get_sovereignty_structures> get_sovereignty_structures_holder;

    public Sovereignty(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Shows sovereignty data for campaigns.
     * 
     * cache over {@link Swagger#get_sovereignty_campaigns}<br />
     */
    public ObsListHolder<R_get_sovereignty_campaigns> campaigns() {
        if (get_sovereignty_campaigns_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_campaigns_holder == null) {
                            ObservableList<R_get_sovereignty_campaigns> holder = FXCollections.observableArrayList();
                            get_sovereignty_campaigns_holder = (cache).toHolder(holder);
                            ObsListHolderImpl<R_get_sovereignty_campaigns> finalRet = get_sovereignty_campaigns_holder;
                            (cache).addFetchCacheArray("get_sovereignty_campaigns", (page, properties) -> (cache.swagger).get_sovereignty_campaigns(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
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
    public ObsListHolder<R_get_sovereignty_map> map() {
        if (get_sovereignty_map_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_map_holder == null) {
                            ObservableList<R_get_sovereignty_map> holder = FXCollections.observableArrayList();
                            get_sovereignty_map_holder = (cache).toHolder(holder);
                            ObsListHolderImpl<R_get_sovereignty_map> finalRet = get_sovereignty_map_holder;
                            (cache).addFetchCacheArray("get_sovereignty_map", (page, properties) -> (cache.swagger).get_sovereignty_map(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
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
    public ObsListHolder<R_get_sovereignty_structures> structures() {
        if (get_sovereignty_structures_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_sovereignty_structures_holder == null) {
                            ObservableList<R_get_sovereignty_structures> holder = FXCollections.observableArrayList();
                            get_sovereignty_structures_holder = (cache).toHolder(holder);
                            ObsListHolderImpl<R_get_sovereignty_structures> finalRet = get_sovereignty_structures_holder;
                            (cache).addFetchCacheArray("get_sovereignty_structures", (page, properties) -> (cache.swagger).get_sovereignty_structures(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
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
