package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_icons;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Alliances {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<Integer> get_alliances_holder;
    private final Map<Integer, ObsListHolderImpl<Integer>> get_alliances_alliance_id_corporations_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_alliances_alliance_id>> get_alliances_alliance_id_holder = new HashMap<>();

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
                    {
                        if (get_alliances_holder == null) {
                            get_alliances_holder = new ObsListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_alliances", (page, properties) -> (cache.swagger).get_alliances(properties), arr -> get_alliances_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
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
        ObsListHolderImpl<Integer> ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_corporations_holder);
            try {
                synchronized (get_alliances_alliance_id_corporations_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_corporations_holder);
                    {
                        ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<Integer>();
                            get_alliances_alliance_id_corporations_holder.put(alliance_id, ret);
                            ObsListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_alliances_alliance_id_corporations", (page, properties) -> (cache.swagger).get_alliances_corporations(alliance_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_alliances_alliance_id_corporations_holder);
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
        ObsObjHolderSimple<R_get_alliances_alliance_id_icons> ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_icons_holder);
            try {
                synchronized (get_alliances_alliance_id_icons_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_icons_holder);
                    {
                        ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_alliances_alliance_id_icons>();
                            ObsObjHolderSimple<R_get_alliances_alliance_id_icons> finalRet = ret;
                            get_alliances_alliance_id_icons_holder.put(alliance_id, ret);
                            (cache).addFetchCacheObject("get_alliances_alliance_id_icons", properties -> (cache.swagger).get_alliances_icons(alliance_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_alliances_alliance_id_icons_holder);
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
        ObsObjHolderSimple<R_get_alliances_alliance_id> ret = get_alliances_alliance_id_holder.get(alliance_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_alliances_alliance_id_holder);
            try {
                synchronized (get_alliances_alliance_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_alliances_alliance_id_holder);
                    {
                        ret = get_alliances_alliance_id_holder.get(alliance_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_alliances_alliance_id>();
                            ObsObjHolderSimple<R_get_alliances_alliance_id> finalRet = ret;
                            get_alliances_alliance_id_holder.put(alliance_id, ret);
                            (cache).addFetchCacheObject("get_alliances_alliance_id", properties -> (cache.swagger).get_alliances(alliance_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_alliances_alliance_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_alliances_alliance_id_holder);
            }
        }
        return ret;
    }
}
