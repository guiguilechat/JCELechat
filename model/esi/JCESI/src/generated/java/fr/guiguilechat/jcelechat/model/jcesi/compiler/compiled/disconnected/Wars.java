package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Wars {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObsListHolder<Integer>> get_wars_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_wars_war_id>> get_wars_war_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<M_get_killmails_2>> get_wars_war_id_killmails_holder = new HashMap<>();

    public Wars(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of wars
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param max_war_id
     *     Only return wars with ID smaller than this
     */
    public ObsListHolder<Integer> wars(Integer max_war_id) {
        ObsListHolder<Integer> ret = get_wars_holder.get(max_war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_holder);
            synchronized (get_wars_holder)
            {
                LockWatchDog.BARKER.hld(get_wars_holder);
                ret = get_wars_holder.get(max_war_id);
                if (ret == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_wars_holder.put(max_war_id, ret);
                    ObsListHolder<Integer> finalRet = ret;
                    (cache).addFetchCacheArray("get_wars", (page, headerHandler) -> IntStream.of((cache.swagger).get_wars(max_war_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
            LockWatchDog.BARKER.rel(get_wars_holder);
        }
        return ret;
    }

    /**
     * Return details about a war
     * 
     * cache over {@link Swagger#get_wars}<br />
     * 
     * @param war_id
     *     ID for a war
     */
    public ObsObjHolder<R_get_wars_war_id> get(int war_id) {
        ObsObjHolder<R_get_wars_war_id> ret = get_wars_war_id_holder.get(war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_war_id_holder);
            synchronized (get_wars_war_id_holder)
            {
                LockWatchDog.BARKER.hld(get_wars_war_id_holder);
                ret = get_wars_war_id_holder.get(war_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_wars_war_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_wars_war_id_holder.put(war_id, ret);
                    (cache).addFetchCacheObject("get_wars_war_id", headerHandler -> (cache.swagger).get_wars(war_id, headerHandler), item -> {
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
            LockWatchDog.BARKER.rel(get_wars_war_id_holder);
        }
        return ret;
    }

    /**
     * Return a list of kills related to a war
     * 
     * cache over {@link Swagger#get_wars_killmails}<br />
     * 
     * @param war_id
     *     A valid war ID
     */
    public ObsListHolder<M_get_killmails_2> killmails(int war_id) {
        ObsListHolder<M_get_killmails_2> ret = get_wars_war_id_killmails_holder.get(war_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_wars_war_id_killmails_holder);
            synchronized (get_wars_war_id_killmails_holder)
            {
                LockWatchDog.BARKER.hld(get_wars_war_id_killmails_holder);
                ret = get_wars_war_id_killmails_holder.get(war_id);
                if (ret == null) {
                    ObservableList<M_get_killmails_2> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_wars_war_id_killmails_holder.put(war_id, ret);
                    ObsListHolder<M_get_killmails_2> finalRet = ret;
                    (cache).addFetchCacheArray("get_wars_war_id_killmails", (page, headerHandler) -> (cache.swagger).get_wars_killmails(page, war_id, headerHandler), arr -> {
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
            LockWatchDog.BARKER.rel(get_wars_war_id_killmails_holder);
        }
        return ret;
    }
}
