package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsObjHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_icons;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alliances {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<Integer> get_alliances_holder;
    private final Map<Integer, ObsListHolder<Integer>> get_alliances_alliance_id_corporations_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_alliances_alliance_id>> get_alliances_alliance_id_holder = new HashMap<>();

    public Alliances(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * List all active player alliances
     * 
     * cache over {@link Swagger#get_alliances}<br />
     */
    public ObsListHolder<Integer> alliances() {
        if (get_alliances_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    if (get_alliances_holder == null) {
                        ObservableList<Integer> holder = FXCollections.observableArrayList();
                        get_alliances_holder = (cache).toHolder(holder);
                        ObsListHolder<Integer> finalRet = get_alliances_holder;
                        (cache).addFetchCacheArray("get_alliances", (page, properties) -> (cache.swagger).get_alliances(properties), arr -> {
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
        return get_alliances_holder;
    }

    /**
     * List all current member corporations of an alliance
     * 
     * cache over {@link Swagger#get_alliances_corporations}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsListHolder<Integer> corporations(int alliance_id) {
        ObsListHolder<Integer> ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_corporations_holder);
            try {
                synchronized (get_alliances_alliance_id_corporations_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_corporations_holder);
                    ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<Integer> holder = FXCollections.observableArrayList();
                        ret = (cache).toHolder(holder);
                        get_alliances_alliance_id_corporations_holder.put(alliance_id, ret);
                        ObsListHolder<Integer> finalRet = ret;
                        (cache).addFetchCacheArray("get_alliances_alliance_id_corporations", (page, properties) -> (cache.swagger).get_alliances_corporations(alliance_id, properties), arr -> {
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
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_corporations_holder);
            }
        }
        return ret;
    }

    /**
     * Get the icon urls for a alliance
     * 
     * cache over {@link Swagger#get_alliances_icons}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsObjHolder<R_get_alliances_alliance_id_icons> icons(int alliance_id) {
        ObsObjHolder<R_get_alliances_alliance_id_icons> ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_icons_holder);
            try {
                synchronized (get_alliances_alliance_id_icons_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_icons_holder);
                    ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id_icons> holder = new SimpleObjectProperty<>();
                        ret = (cache).toHolder(holder);
                        get_alliances_alliance_id_icons_holder.put(alliance_id, ret);
                        (cache).addFetchCacheObject("get_alliances_alliance_id_icons", properties -> (cache.swagger).get_alliances_icons(alliance_id, properties), item -> {
                            LockWatchDog.BARKER.tak(holder);
                            try {
                                synchronized (holder)
                                {
                                    LockWatchDog.BARKER.hld(holder);
                                    holder.set(item);
                                }
                            } finally {
                                LockWatchDog.BARKER.rel(holder);
                            }
                        }
                        );
                    }
                }
            } finally {
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_icons_holder);
            }
        }
        return ret;
    }

    /**
     * Public information about an alliance
     * 
     * cache over {@link Swagger#get_alliances}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsObjHolder<R_get_alliances_alliance_id> get(int alliance_id) {
        ObsObjHolder<R_get_alliances_alliance_id> ret = get_alliances_alliance_id_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_holder);
            try {
                synchronized (get_alliances_alliance_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_holder);
                    ret = get_alliances_alliance_id_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id> holder = new SimpleObjectProperty<>();
                        ret = (cache).toHolder(holder);
                        get_alliances_alliance_id_holder.put(alliance_id, ret);
                        (cache).addFetchCacheObject("get_alliances_alliance_id", properties -> (cache.swagger).get_alliances(alliance_id, properties), item -> {
                            LockWatchDog.BARKER.tak(holder);
                            try {
                                synchronized (holder)
                                {
                                    LockWatchDog.BARKER.hld(holder);
                                    holder.set(item);
                                }
                            } finally {
                                LockWatchDog.BARKER.rel(holder);
                            }
                        }
                        );
                    }
                }
            } finally {
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_holder);
            }
        }
        return ret;
    }
}
