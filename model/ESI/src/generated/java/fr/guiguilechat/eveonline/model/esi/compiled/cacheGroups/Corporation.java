package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_7_int_long;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_extractions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Corporation {
    public final SwaggerCache<?> cache;
    private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_extractions>> get_corporation_corporation_id_mining_extractions_holder = new HashMap<>();
    private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_observers>> get_corporation_corporation_id_mining_observers_holder = new HashMap<>();
    private final Map<K_7_int_long, ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id>> get_corporation_corporation_id_mining_observers_observer_id_holder = new HashMap<>();

    public Corporation(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Extraction timers for all moon chunks being extracted by refineries belonging to a corporation.
     * 
     * cache over {@link Swagger#get_corporation_mining_extractions}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObservableList<R_get_corporation_corporation_id_mining_extractions> mining_extractions(int corporation_id) {
        ObservableList<R_get_corporation_corporation_id_mining_extractions> ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_corporation_corporation_id_mining_extractions_holder)
            {
                ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_corporation_corporation_id_mining_extractions> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    get_corporation_corporation_id_mining_extractions_holder.put(corporation_id, ret);
                    (cache).addFetchCacheArray("get_corporation_corporation_id_mining_extractions", (page, headerHandler) -> (cache.swagger).get_corporation_mining_extractions(corporation_id, page, headerHandler), arr -> {
                        synchronized (finalret)
                        {
                            finalret.setAll(arr);
                        }
                    }
                    , new String[] {"Structure_manager"});
                }
            }
        }
        return ret;
    }

    /**
     * Paginated list of all entities capable of observing and recording mining for a corporation
     * 
     * cache over {@link Swagger#get_corporation_mining_observers}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObservableList<R_get_corporation_corporation_id_mining_observers> mining_observers(int corporation_id) {
        ObservableList<R_get_corporation_corporation_id_mining_observers> ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
        if (ret == null) {
            synchronized (get_corporation_corporation_id_mining_observers_holder)
            {
                ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
                if (ret == null) {
                    ObservableList<R_get_corporation_corporation_id_mining_observers> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    get_corporation_corporation_id_mining_observers_holder.put(corporation_id, ret);
                    (cache).addFetchCacheArray("get_corporation_corporation_id_mining_observers", (page, headerHandler) -> (cache.swagger).get_corporation_mining_observers(corporation_id, page, headerHandler), arr -> {
                        synchronized (finalret)
                        {
                            finalret.setAll(arr);
                        }
                    }
                    , new String[] {"Accountant"});
                }
            }
        }
        return ret;
    }

    /**
     * Paginated record of all mining seen by an observer
     * 
     * cache over {@link Swagger#get_corporation_mining_observers}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param observer_id
     *     A mining observer id
     */
    public ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id> mining_observers(int corporation_id, long observer_id) {
        K_7_int_long param = new K_7_int_long(corporation_id, observer_id);
        ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id> ret = get_corporation_corporation_id_mining_observers_observer_id_holder.get(param);
        if (ret == null) {
            synchronized (get_corporation_corporation_id_mining_observers_observer_id_holder)
            {
                ret = get_corporation_corporation_id_mining_observers_observer_id_holder.get(param);
                if (ret == null) {
                    ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id> finalret = FXCollections.observableArrayList();
                    ret = finalret;
                    get_corporation_corporation_id_mining_observers_observer_id_holder.put(param, ret);
                    (cache).addFetchCacheArray("get_corporation_corporation_id_mining_observers_observer_id", (page, headerHandler) -> (cache.swagger).get_corporation_mining_observers(corporation_id, observer_id, page, headerHandler), arr -> {
                        synchronized (finalret)
                        {
                            finalret.setAll(arr);
                        }
                    }
                    , new String[] {"Accountant"});
                }
            }
        }
        return ret;
    }
}
