package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.M_get_contracts_bids_contract_4;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsMapHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Contracts {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObsMapHolder<Integer, M_get_contracts_bids_contract_4>> get_contracts_public_bids_contract_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<R_get_contracts_public_items_contract_id>> get_contracts_public_items_contract_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<R_get_contracts_public_region_id>> get_contracts_public_region_id_holder = new HashMap<>();

    public Contracts(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Lists bids on a public auction contract
     * 
     * cache over {@link Swagger#get_contracts_public_bids}<br />
     * 
     * @param contract_id
     *     ID of a contract
     */
    public ObsMapHolder<Integer, M_get_contracts_bids_contract_4> public_bids(int contract_id) {
        ObsMapHolder<Integer, M_get_contracts_bids_contract_4> ret = get_contracts_public_bids_contract_id_holder.get(contract_id);
        if (ret == null) {
            synchronized (get_contracts_public_bids_contract_id_holder)
            {
                ret = get_contracts_public_bids_contract_id_holder.get(contract_id);
                if (ret == null) {
                    ObservableMap<Integer, M_get_contracts_bids_contract_4> holder = FXCollections.observableHashMap();
                    ret = (cache).toHolder(holder);
                    get_contracts_public_bids_contract_id_holder.put(contract_id, ret);
                    (cache).addFetchCacheArray("get_contracts_public_bids_contract_id", (page, headerHandler) -> (cache.swagger).get_contracts_public_bids(contract_id, page, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            LinkedHashMap<Integer, M_get_contracts_bids_contract_4> newmap = new LinkedHashMap<>();
                            for (M_get_contracts_bids_contract_4 val: arr) {
                                newmap.put((val.bid_id), (val));
                            }
                            holder.entrySet().retainAll(newmap.entrySet());
                            holder.putAll(newmap);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Lists items of a public contract
     * 
     * cache over {@link Swagger#get_contracts_public_items}<br />
     * 
     * @param contract_id
     *     ID of a contract
     */
    public ObsListHolder<R_get_contracts_public_items_contract_id> public_items(int contract_id) {
        ObsListHolder<R_get_contracts_public_items_contract_id> ret = get_contracts_public_items_contract_id_holder.get(contract_id);
        if (ret == null) {
            synchronized (get_contracts_public_items_contract_id_holder)
            {
                ret = get_contracts_public_items_contract_id_holder.get(contract_id);
                if (ret == null) {
                    ObservableList<R_get_contracts_public_items_contract_id> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_contracts_public_items_contract_id_holder.put(contract_id, ret);
                    (cache).addFetchCacheArray("get_contracts_public_items_contract_id", (page, headerHandler) -> (cache.swagger).get_contracts_public_items(contract_id, page, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Returns a paginated list of all public contracts in the given region
     * 
     * cache over {@link Swagger#get_contracts_public}<br />
     * 
     * @param region_id
     *     An EVE region id
     */
    public ObsListHolder<R_get_contracts_public_region_id> getpublic(int region_id) {
        ObsListHolder<R_get_contracts_public_region_id> ret = get_contracts_public_region_id_holder.get(region_id);
        if (ret == null) {
            synchronized (get_contracts_public_region_id_holder)
            {
                ret = get_contracts_public_region_id_holder.get(region_id);
                if (ret == null) {
                    ObservableList<R_get_contracts_public_region_id> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_contracts_public_region_id_holder.put(region_id, ret);
                    (cache).addFetchCacheArray("get_contracts_public_region_id", (page, headerHandler) -> (cache.swagger).get_contracts_public(page, region_id, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
