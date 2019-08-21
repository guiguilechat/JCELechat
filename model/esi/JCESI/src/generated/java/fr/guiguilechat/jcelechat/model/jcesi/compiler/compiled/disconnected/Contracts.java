package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contracts {
    public final SwaggerDCCache<?> cache;
    private final Map<Integer, ObsListHolderImpl<R_get_contracts_public_bids_contract_id>> get_contracts_public_bids_contract_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_contracts_public_items_contract_id>> get_contracts_public_items_contract_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_contracts_public_region_id>> get_contracts_public_region_id_holder = new HashMap<>();

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
    public ObsListHolder<R_get_contracts_public_bids_contract_id> public_bids(int contract_id) {
        ObsListHolderImpl<R_get_contracts_public_bids_contract_id> ret = get_contracts_public_bids_contract_id_holder.get(contract_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_contracts_public_bids_contract_id_holder);
            try {
                synchronized (get_contracts_public_bids_contract_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_contracts_public_bids_contract_id_holder);
                    {
                        ret = get_contracts_public_bids_contract_id_holder.get(contract_id);
                        if (ret == null) {
                            ObservableList<R_get_contracts_public_bids_contract_id> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_contracts_public_bids_contract_id_holder.put(contract_id, ret);
                            ObsListHolderImpl<R_get_contracts_public_bids_contract_id> finalRet = ret;
                            (cache).addFetchCacheArray("get_contracts_public_bids_contract_id", (page, properties) -> (cache.swagger).get_contracts_public_bids(contract_id, page, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_contracts_public_bids_contract_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_contracts_public_bids_contract_id_holder);
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
        ObsListHolderImpl<R_get_contracts_public_items_contract_id> ret = get_contracts_public_items_contract_id_holder.get(contract_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_contracts_public_items_contract_id_holder);
            try {
                synchronized (get_contracts_public_items_contract_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_contracts_public_items_contract_id_holder);
                    {
                        ret = get_contracts_public_items_contract_id_holder.get(contract_id);
                        if (ret == null) {
                            ObservableList<R_get_contracts_public_items_contract_id> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_contracts_public_items_contract_id_holder.put(contract_id, ret);
                            ObsListHolderImpl<R_get_contracts_public_items_contract_id> finalRet = ret;
                            (cache).addFetchCacheArray("get_contracts_public_items_contract_id", (page, properties) -> (cache.swagger).get_contracts_public_items(contract_id, page, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_contracts_public_items_contract_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_contracts_public_items_contract_id_holder);
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
        ObsListHolderImpl<R_get_contracts_public_region_id> ret = get_contracts_public_region_id_holder.get(region_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_contracts_public_region_id_holder);
            try {
                synchronized (get_contracts_public_region_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_contracts_public_region_id_holder);
                    {
                        ret = get_contracts_public_region_id_holder.get(region_id);
                        if (ret == null) {
                            ObservableList<R_get_contracts_public_region_id> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_contracts_public_region_id_holder.put(region_id, ret);
                            ObsListHolderImpl<R_get_contracts_public_region_id> finalRet = ret;
                            (cache).addFetchCacheArray("get_contracts_public_region_id", (page, properties) -> (cache.swagger).get_contracts_public(page, region_id, properties), arr -> {
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
                    LockWatchDog.BARKER.rel(get_contracts_public_region_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_contracts_public_region_id_holder);
            }
        }
        return ret;
    }
}
