package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_members;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_wings;
import fr.lelouet.collectionholders.impl.AObsObjHolder;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fleets {
    public final SwaggerCOCache<?> cache;
    private final Map<Long, AObsObjHolder<R_get_fleets_fleet_id>> get_fleets_fleet_id_holder = new HashMap<>();
    private final Map<Long, ObsListHolderImpl<R_get_fleets_fleet_id_members>> get_fleets_fleet_id_members_holder = new HashMap<>();
    private final Map<Long, ObsListHolderImpl<R_get_fleets_fleet_id_wings>> get_fleets_fleet_id_wings_holder = new HashMap<>();

    public Fleets(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return details about a fleet
     * 
     * cache over {@link Swagger#get_fleets}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsObjHolder<R_get_fleets_fleet_id> get(long fleet_id) {
        AObsObjHolder<R_get_fleets_fleet_id> ret = get_fleets_fleet_id_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_holder);
            try {
                synchronized (get_fleets_fleet_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_holder);
                    {
                        ret = get_fleets_fleet_id_holder.get(fleet_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_fleets_fleet_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_fleets_fleet_id_holder.put(fleet_id, ret);
                            (cache).addFetchCacheObject("get_fleets_fleet_id", properties -> (cache.swagger).get_fleets(fleet_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_holder);
            }
        }
        return ret;
    }

    /**
     * Return information about fleet members
     * 
     * cache over {@link Swagger#get_fleets_members}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsListHolder<R_get_fleets_fleet_id_members> members(long fleet_id) {
        ObsListHolderImpl<R_get_fleets_fleet_id_members> ret = get_fleets_fleet_id_members_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_members_holder);
            try {
                synchronized (get_fleets_fleet_id_members_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_members_holder);
                    {
                        ret = get_fleets_fleet_id_members_holder.get(fleet_id);
                        if (ret == null) {
                            ObservableList<R_get_fleets_fleet_id_members> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_fleets_fleet_id_members_holder.put(fleet_id, ret);
                            ObsListHolderImpl<R_get_fleets_fleet_id_members> finalRet = ret;
                            (cache).addFetchCacheArray("get_fleets_fleet_id_members", (page, properties) -> (cache.swagger).get_fleets_members(fleet_id, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_members_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_members_holder);
            }
        }
        return ret;
    }

    /**
     * Return information about wings in a fleet
     * 
     * cache over {@link Swagger#get_fleets_wings}<br />
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public ObsListHolder<R_get_fleets_fleet_id_wings> wings(long fleet_id) {
        ObsListHolderImpl<R_get_fleets_fleet_id_wings> ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_fleets_fleet_id_wings_holder);
            try {
                synchronized (get_fleets_fleet_id_wings_holder)
                {
                    LockWatchDog.BARKER.hld(get_fleets_fleet_id_wings_holder);
                    {
                        ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
                        if (ret == null) {
                            ObservableList<R_get_fleets_fleet_id_wings> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_fleets_fleet_id_wings_holder.put(fleet_id, ret);
                            ObsListHolderImpl<R_get_fleets_fleet_id_wings> finalRet = ret;
                            (cache).addFetchCacheArray("get_fleets_fleet_id_wings", (page, properties) -> (cache.swagger).get_fleets_wings(fleet_id, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_fleets_fleet_id_wings_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_fleets_fleet_id_wings_holder);
            }
        }
        return ret;
    }
}
