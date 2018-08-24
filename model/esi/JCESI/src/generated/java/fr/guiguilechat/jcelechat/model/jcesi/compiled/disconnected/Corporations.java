package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_alliancehistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_corporations_corporation_id_icons;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Corporations {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<Integer> get_corporations_npccorps_holder;
    private final Map<Integer, ObsObjHolder<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<R_get_corporations_corporation_id_alliancehistory>> get_corporations_corporation_id_alliancehistory_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_corporations_corporation_id>> get_corporations_corporation_id_holder = new HashMap<>();

    public Corporations(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get a list of npc corporations
     * 
     * cache over {@link Swagger#get_corporations_npccorps}<br />
     */
    public ObsListHolder<Integer> npccorps() {
        if (get_corporations_npccorps_holder == null) {
            LockWatchDog.BARKER.tak(this);
            synchronized (this)
            {
                LockWatchDog.BARKER.hld(this);
                if (get_corporations_npccorps_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_corporations_npccorps_holder = (cache).toHolder(holder);
                    ObsListHolder<Integer> finalRet = get_corporations_npccorps_holder;
                    (cache).addFetchCacheArray("get_corporations_npccorps", (page, headerHandler) -> IntStream.of((cache.swagger).get_corporations_npccorps(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
        return get_corporations_npccorps_holder;
    }

    /**
     * Get the icon urls for a corporation
     * 
     * cache over {@link Swagger#get_corporations_icons}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObsObjHolder<R_get_corporations_corporation_id_icons> icons(int corporation_id) {
        ObsObjHolder<R_get_corporations_corporation_id_icons> ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_icons_holder);
            synchronized (get_corporations_corporation_id_icons_holder)
            {
                LockWatchDog.BARKER.hld(get_corporations_corporation_id_icons_holder);
                ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_corporations_corporation_id_icons> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_corporations_corporation_id_icons_holder.put(corporation_id, ret);
                    (cache).addFetchCacheObject("get_corporations_corporation_id_icons", headerHandler -> (cache.swagger).get_corporations_icons(corporation_id, headerHandler), item -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.set(item);
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_corporations_corporation_id_icons_holder);
        }
        return ret;
    }

    /**
     * Get a list of all the alliances a corporation has been a member of
     * 
     * cache over {@link Swagger#get_corporations_alliancehistory}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObsListHolder<R_get_corporations_corporation_id_alliancehistory> alliancehistory(int corporation_id) {
        ObsListHolder<R_get_corporations_corporation_id_alliancehistory> ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_alliancehistory_holder);
            synchronized (get_corporations_corporation_id_alliancehistory_holder)
            {
                LockWatchDog.BARKER.hld(get_corporations_corporation_id_alliancehistory_holder);
                ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_corporations_corporation_id_alliancehistory> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_corporations_corporation_id_alliancehistory_holder.put(corporation_id, ret);
                    ObsListHolder<R_get_corporations_corporation_id_alliancehistory> finalRet = ret;
                    (cache).addFetchCacheArray("get_corporations_corporation_id_alliancehistory", (page, headerHandler) -> (cache.swagger).get_corporations_alliancehistory(corporation_id, headerHandler), arr -> {
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
            LockWatchDog.BARKER.rel(get_corporations_corporation_id_alliancehistory_holder);
        }
        return ret;
    }

    /**
     * Public information about a corporation
     * 
     * cache over {@link Swagger#get_corporations}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObsObjHolder<R_get_corporations_corporation_id> get(int corporation_id) {
        ObsObjHolder<R_get_corporations_corporation_id> ret = get_corporations_corporation_id_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_holder);
            synchronized (get_corporations_corporation_id_holder)
            {
                LockWatchDog.BARKER.hld(get_corporations_corporation_id_holder);
                ret = get_corporations_corporation_id_holder.get(corporation_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_corporations_corporation_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_corporations_corporation_id_holder.put(corporation_id, ret);
                    (cache).addFetchCacheObject("get_corporations_corporation_id", headerHandler -> (cache.swagger).get_corporations(corporation_id, headerHandler), item -> {
                        LockWatchDog.BARKER.tak(holder);
                        synchronized (holder)
                        {
                            LockWatchDog.BARKER.hld(holder);
                            holder.set(item);
                        }
                        LockWatchDog.BARKER.rel(holder);
                    }
                    );
                }
            }
            LockWatchDog.BARKER.rel(get_corporations_corporation_id_holder);
        }
        return ret;
    }
}
