package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_contacts;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alliances {
    public final SwaggerCOCache<?> cache;
    private final Map<Integer, ObsListHolderImpl<M_get_contacts_labels_2>> get_alliances_alliance_id_contacts_labels_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_alliances_alliance_id_contacts>> get_alliances_alliance_id_contacts_holder = new HashMap<>();

    public Alliances(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return custom labels for an alliance's contacts
     * 
     * cache over {@link Swagger#get_alliances_contacts_labels}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsListHolder<M_get_contacts_labels_2> contacts_labels(int alliance_id) {
        ObsListHolderImpl<M_get_contacts_labels_2> ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_contacts_labels_holder);
            try {
                synchronized (get_alliances_alliance_id_contacts_labels_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_contacts_labels_holder);
                    {
                        ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
                        if (ret == null) {
                            ObservableList<M_get_contacts_labels_2> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_alliances_alliance_id_contacts_labels_holder.put(alliance_id, ret);
                            ObsListHolderImpl<M_get_contacts_labels_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_alliances_alliance_id_contacts_labels", (page, properties) -> (cache.swagger).get_alliances_contacts_labels(alliance_id, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_alliances_alliance_id_contacts_labels_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_contacts_labels_holder);
            }
        }
        return ret;
    }

    /**
     * Return contacts of an alliance
     * 
     * cache over {@link Swagger#get_alliances_contacts}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsListHolder<R_get_alliances_alliance_id_contacts> contacts(int alliance_id) {
        ObsListHolderImpl<R_get_alliances_alliance_id_contacts> ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_contacts_holder);
            try {
                synchronized (get_alliances_alliance_id_contacts_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_contacts_holder);
                    {
                        ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
                        if (ret == null) {
                            ObservableList<R_get_alliances_alliance_id_contacts> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_alliances_alliance_id_contacts_holder.put(alliance_id, ret);
                            ObsListHolderImpl<R_get_alliances_alliance_id_contacts> finalRet = ret;
                            (cache).addFetchCacheArray("get_alliances_alliance_id_contacts", (page, properties) -> (cache.swagger).get_alliances_contacts(alliance_id, page, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_alliances_alliance_id_contacts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_contacts_holder);
            }
        }
        return ret;
    }
}
