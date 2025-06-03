package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_10_int_long_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_11_int_int_Long;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_19_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_8_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_9_int_Boolean;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_bids_4;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_containers_logs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_customs_offices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_medals;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_medals_issued;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_members_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_membertracking;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_shareholders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_starbases;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_starbases_starbase_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Corporations {
    public final SwaggerCOCache<?> cache;
    private final Map<Integer, ListHolderImpl<M_get_contacts_labels_2>> get_corporations_corporation_id_contacts_labels_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<M_get_contracts_22>> get_corporations_corporation_id_contracts_holder = new HashMap<>();
    private final Map<K_8_int_int, ListHolderImpl<M_get_contracts_contract_bids_4>> get_corporations_corporation_id_contracts_contract_id_bids_holder = new HashMap<>();
    private final Map<K_8_int_int, ListHolderImpl<M_get_contracts_contract_items_6>> get_corporations_corporation_id_contracts_contract_id_items_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_customs_offices>> get_corporations_corporation_id_customs_offices_holder = new HashMap<>();
    private final Map<Integer, ObjHolderSimple<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_facilities>> get_corporations_corporation_id_facilities_holder = new HashMap<>();
    private final Map<Integer, ObjHolderSimple<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_holder = new HashMap<>();
    private final Map<K_9_int_Boolean, ListHolderImpl<R_get_corporations_corporation_id_industry_jobs>> get_corporations_corporation_id_industry_jobs_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<M_get_killmails_2>> get_corporations_corporation_id_killmails_recent_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_medals>> get_corporations_corporation_id_medals_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_medals_issued>> get_corporations_corporation_id_medals_issued_holder = new HashMap<>();
    private final Map<Integer, ObjHolderSimple<Integer>> get_corporations_corporation_id_members_limit_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_members_titles>> get_corporations_corporation_id_members_titles_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_membertracking>> get_corporations_corporation_id_membertracking_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_orders_history>> get_corporations_corporation_id_orders_history_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_roles>> get_corporations_corporation_id_roles_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_roles_history>> get_corporations_corporation_id_roles_history_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_shareholders>> get_corporations_corporation_id_shareholders_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<M_get_standings_3>> get_corporations_corporation_id_standings_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_starbases>> get_corporations_corporation_id_starbases_holder = new HashMap<>();
    private final Map<K_10_int_long_int, ObjHolderSimple<R_get_corporations_corporation_id_starbases_starbase_id>> get_corporations_corporation_id_starbases_starbase_id_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_titles>> get_corporations_corporation_id_titles_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_wallets>> get_corporations_corporation_id_wallets_holder = new HashMap<>();
    private final Map<K_11_int_int_Long, ListHolderImpl<R_get_corporations_corporation_id_wallets_division_transactions>> get_corporations_corporation_id_wallets_division_transactions_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_blueprints>> get_corporations_corporation_id_blueprints_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_contacts>> get_corporations_corporation_id_contacts_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_containers_logs>> get_corporations_corporation_id_containers_logs_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_orders>> get_corporations_corporation_id_orders_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<Integer>> get_corporations_corporation_id_members_holder = new HashMap<>();
    private final Map<K_19_int_int, ListHolderImpl<M_get_journal_13>> get_corporations_corporation_id_wallets_division_journal_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_assets>> get_corporations_corporation_id_assets_holder = new HashMap<>();
    private final Map<Integer, ListHolderImpl<R_get_corporations_corporation_id_structures>> get_corporations_corporation_id_structures_holder = new HashMap<>();

    public Corporations(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of the corporation assets
     * 
     * cache over {@link Swagger#get_corporations_assets}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_assets> assets(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_assets> ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_assets_holder);
            try {
                synchronized (get_corporations_corporation_id_assets_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_assets_holder);
                    {
                        ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_assets>();
                            get_corporations_corporation_id_assets_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_assets> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_assets", (page, properties) -> (cache.swagger).get_corporations_assets(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_assets_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_assets_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a list of blueprints the corporation owns
     * 
     * cache over {@link Swagger#get_corporations_blueprints}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_blueprints> blueprints(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_blueprints> ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_blueprints_holder);
            try {
                synchronized (get_corporations_corporation_id_blueprints_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_blueprints_holder);
                    {
                        ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_blueprints>();
                            get_corporations_corporation_id_blueprints_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_blueprints> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_blueprints", (page, properties) -> (cache.swagger).get_corporations_blueprints(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_blueprints_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_blueprints_holder);
            }
        }
        return ret;
    }

    /**
     * Return contacts of a corporation
     * 
     * cache over {@link Swagger#get_corporations_contacts}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_contacts> contacts(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_contacts> ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_contacts_holder);
            try {
                synchronized (get_corporations_corporation_id_contacts_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_contacts_holder);
                    {
                        ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_contacts>();
                            get_corporations_corporation_id_contacts_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_contacts> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_contacts", (page, properties) -> (cache.swagger).get_corporations_contacts(corporation_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_holder);
            }
        }
        return ret;
    }

    /**
     * Return custom labels for a corporation's contacts
     * 
     * cache over {@link Swagger#get_corporations_contacts_labels}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_contacts_labels_2> contacts_labels(int corporation_id) {
        ListHolderImpl<M_get_contacts_labels_2> ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_contacts_labels_holder);
            try {
                synchronized (get_corporations_corporation_id_contacts_labels_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_contacts_labels_holder);
                    {
                        ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_contacts_labels_2>();
                            get_corporations_corporation_id_contacts_labels_holder.put(corporation_id, ret);
                            ListHolderImpl<M_get_contacts_labels_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_contacts_labels", (page, properties) -> (cache.swagger).get_corporations_contacts_labels(corporation_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_labels_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_contacts_labels_holder);
            }
        }
        return ret;
    }

    /**
     * Returns logs recorded in the past seven days from all audit log secure containers (ALSC) owned by a given corporation
     * 
     * cache over {@link Swagger#get_corporations_containers_logs}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_containers_logs> containers_logs(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_containers_logs> ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_containers_logs_holder);
            try {
                synchronized (get_corporations_corporation_id_containers_logs_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_containers_logs_holder);
                    {
                        ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_containers_logs>();
                            get_corporations_corporation_id_containers_logs_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_containers_logs> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_containers_logs", (page, properties) -> (cache.swagger).get_corporations_containers_logs(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_containers_logs_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_containers_logs_holder);
            }
        }
        return ret;
    }

    /**
     * Returns contracts available to a corporation, only if the corporation is issuer, acceptor or assignee. Only returns contracts no older than 30 days, or if the status is "in_progress".
     * 
     * cache over {@link Swagger#get_corporations_contracts}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_contracts_22> contracts(int corporation_id) {
        ListHolderImpl<M_get_contracts_22> ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_holder);
            try {
                synchronized (get_corporations_corporation_id_contracts_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_holder);
                    {
                        ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_contracts_22>();
                            get_corporations_corporation_id_contracts_holder.put(corporation_id, ret);
                            ListHolderImpl<M_get_contracts_22> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_contracts", (page, properties) -> (cache.swagger).get_corporations_contracts(corporation_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_holder);
            }
        }
        return ret;
    }

    /**
     * Lists bids on a particular auction contract
     * 
     * cache over {@link Swagger#get_corporations_contracts_bids}<br />
     * 
     * @param contract_id
     *     ID of a contract
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_contracts_contract_bids_4> contracts_bids(int contract_id, int corporation_id) {
        K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
        ListHolderImpl<M_get_contracts_contract_bids_4> ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_contract_id_bids_holder);
            try {
                synchronized (get_corporations_corporation_id_contracts_contract_id_bids_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_contract_id_bids_holder);
                    {
                        ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_contracts_contract_bids_4>();
                            get_corporations_corporation_id_contracts_contract_id_bids_holder.put(param, ret);
                            ListHolderImpl<M_get_contracts_contract_bids_4> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_bids", (page, properties) -> (cache.swagger).get_corporations_contracts_bids(contract_id, corporation_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_bids_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_bids_holder);
            }
        }
        return ret;
    }

    /**
     * Lists items of a particular contract
     * 
     * cache over {@link Swagger#get_corporations_contracts_items}<br />
     * 
     * @param contract_id
     *     ID of a contract
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_contracts_contract_items_6> contracts_items(int contract_id, int corporation_id) {
        K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
        ListHolderImpl<M_get_contracts_contract_items_6> ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_contracts_contract_id_items_holder);
            try {
                synchronized (get_corporations_corporation_id_contracts_contract_id_items_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_contracts_contract_id_items_holder);
                    {
                        ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_contracts_contract_items_6>();
                            get_corporations_corporation_id_contracts_contract_id_items_holder.put(param, ret);
                            ListHolderImpl<M_get_contracts_contract_items_6> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_items", (page, properties) -> (cache.swagger).get_corporations_contracts_items(contract_id, corporation_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_items_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_contracts_contract_id_items_holder);
            }
        }
        return ret;
    }

    /**
     * List customs offices owned by a corporation
     * 
     * cache over {@link Swagger#get_corporations_customs_offices}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_customs_offices> customs_offices(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_customs_offices> ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_customs_offices_holder);
            try {
                synchronized (get_corporations_corporation_id_customs_offices_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_customs_offices_holder);
                    {
                        ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_customs_offices>();
                            get_corporations_corporation_id_customs_offices_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_customs_offices> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_customs_offices", (page, properties) -> (cache.swagger).get_corporations_customs_offices(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_customs_offices_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_customs_offices_holder);
            }
        }
        return ret;
    }

    /**
     * Return corporation hangar and wallet division names, only show if a division is not using the default name
     * 
     * cache over {@link Swagger#get_corporations_divisions}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObjHolder<R_get_corporations_corporation_id_divisions> divisions(int corporation_id) {
        ObjHolderSimple<R_get_corporations_corporation_id_divisions> ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_divisions_holder);
            try {
                synchronized (get_corporations_corporation_id_divisions_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_divisions_holder);
                    {
                        ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_corporations_corporation_id_divisions>();
                            ObjHolderSimple<R_get_corporations_corporation_id_divisions> finalRet = ret;
                            get_corporations_corporation_id_divisions_holder.put(corporation_id, ret);
                            (cache).addFetchCacheObject("get_corporations_corporation_id_divisions", properties -> (cache.swagger).get_corporations_divisions(corporation_id, properties), item -> finalRet.set(item), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_divisions_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_divisions_holder);
            }
        }
        return ret;
    }

    /**
     * Return a corporation's facilities
     * 
     * cache over {@link Swagger#get_corporations_facilities}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_facilities> facilities(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_facilities> ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_facilities_holder);
            try {
                synchronized (get_corporations_corporation_id_facilities_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_facilities_holder);
                    {
                        ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_facilities>();
                            get_corporations_corporation_id_facilities_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_facilities> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_facilities", (page, properties) -> (cache.swagger).get_corporations_facilities(corporation_id, properties), arr -> finalRet.set(arr), new String[] {"Factory_Manager"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_facilities_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_facilities_holder);
            }
        }
        return ret;
    }

    /**
     * Statistics about a corporation involved in faction warfare
     * 
     * cache over {@link Swagger#get_corporations_fw_stats}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObjHolder<R_get_corporations_corporation_id_fw_stats> fw_stats(int corporation_id) {
        ObjHolderSimple<R_get_corporations_corporation_id_fw_stats> ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_fw_stats_holder);
            try {
                synchronized (get_corporations_corporation_id_fw_stats_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_fw_stats_holder);
                    {
                        ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_corporations_corporation_id_fw_stats>();
                            ObjHolderSimple<R_get_corporations_corporation_id_fw_stats> finalRet = ret;
                            get_corporations_corporation_id_fw_stats_holder.put(corporation_id, ret);
                            (cache).addFetchCacheObject("get_corporations_corporation_id_fw_stats", properties -> (cache.swagger).get_corporations_fw_stats(corporation_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_fw_stats_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_fw_stats_holder);
            }
        }
        return ret;
    }

    /**
     * List industry jobs run by a corporation
     * 
     * cache over {@link Swagger#get_corporations_industry_jobs}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param include_completed
     *     Whether to retrieve completed corporation industry jobs. Only includes jobs from the past 90 days
     */
    public ListHolder<R_get_corporations_corporation_id_industry_jobs> industry_jobs(int corporation_id, Boolean include_completed) {
        K_9_int_Boolean param = new K_9_int_Boolean(corporation_id, include_completed);
        ListHolderImpl<R_get_corporations_corporation_id_industry_jobs> ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_industry_jobs_holder);
            try {
                synchronized (get_corporations_corporation_id_industry_jobs_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_industry_jobs_holder);
                    {
                        ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_industry_jobs>();
                            get_corporations_corporation_id_industry_jobs_holder.put(param, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_industry_jobs> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_industry_jobs", (page, properties) -> (cache.swagger).get_corporations_industry_jobs(corporation_id, include_completed, page, properties), arr -> finalRet.set(arr), new String[] {"Factory_Manager"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_industry_jobs_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_industry_jobs_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of a corporation's kills and losses going back 90 days
     * 
     * cache over {@link Swagger#get_corporations_killmails_recent}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_killmails_2> killmails_recent(int corporation_id) {
        ListHolderImpl<M_get_killmails_2> ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_killmails_recent_holder);
            try {
                synchronized (get_corporations_corporation_id_killmails_recent_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_killmails_recent_holder);
                    {
                        ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_killmails_2>();
                            get_corporations_corporation_id_killmails_recent_holder.put(corporation_id, ret);
                            ListHolderImpl<M_get_killmails_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_killmails_recent", (page, properties) -> (cache.swagger).get_corporations_killmails_recent(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_killmails_recent_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_killmails_recent_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a corporation's medals
     * 
     * cache over {@link Swagger#get_corporations_medals}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_medals> medals(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_medals> ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_medals_holder);
            try {
                synchronized (get_corporations_corporation_id_medals_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_medals_holder);
                    {
                        ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_medals>();
                            get_corporations_corporation_id_medals_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_medals> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_medals", (page, properties) -> (cache.swagger).get_corporations_medals(corporation_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_holder);
            }
        }
        return ret;
    }

    /**
     * Returns medals issued by a corporation
     * 
     * cache over {@link Swagger#get_corporations_medals_issued}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_medals_issued> medals_issued(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_medals_issued> ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_medals_issued_holder);
            try {
                synchronized (get_corporations_corporation_id_medals_issued_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_medals_issued_holder);
                    {
                        ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_medals_issued>();
                            get_corporations_corporation_id_medals_issued_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_medals_issued> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_medals_issued", (page, properties) -> (cache.swagger).get_corporations_medals_issued(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_issued_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_medals_issued_holder);
            }
        }
        return ret;
    }

    /**
     * Return the current member list of a corporation, the token's character need to be a member of the corporation.
     * 
     * cache over {@link Swagger#get_corporations_members}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<Integer> members(int corporation_id) {
        ListHolderImpl<Integer> ret = get_corporations_corporation_id_members_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_holder);
            try {
                synchronized (get_corporations_corporation_id_members_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_holder);
                    {
                        ret = get_corporations_corporation_id_members_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<Integer>();
                            get_corporations_corporation_id_members_holder.put(corporation_id, ret);
                            ListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_members", (page, properties) -> (cache.swagger).get_corporations_members(corporation_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_holder);
            }
        }
        return ret;
    }

    /**
     * Return a corporation's member limit, not including CEO himself
     * 
     * cache over {@link Swagger#get_corporations_members_limit}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ObjHolder<Integer> members_limit(int corporation_id) {
        ObjHolderSimple<Integer> ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_limit_holder);
            try {
                synchronized (get_corporations_corporation_id_members_limit_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_limit_holder);
                    {
                        ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ObjHolderSimple<Integer>();
                            ObjHolderSimple<Integer> finalRet = ret;
                            get_corporations_corporation_id_members_limit_holder.put(corporation_id, ret);
                            (cache).addFetchCacheObject("get_corporations_corporation_id_members_limit", properties -> (cache.swagger).get_corporations_members_limit(corporation_id, properties), item -> finalRet.set(item), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_limit_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_limit_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a corporation's members' titles
     * 
     * cache over {@link Swagger#get_corporations_members_titles}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_members_titles> members_titles(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_members_titles> ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_members_titles_holder);
            try {
                synchronized (get_corporations_corporation_id_members_titles_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_members_titles_holder);
                    {
                        ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_members_titles>();
                            get_corporations_corporation_id_members_titles_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_members_titles> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_members_titles", (page, properties) -> (cache.swagger).get_corporations_members_titles(corporation_id, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_titles_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_members_titles_holder);
            }
        }
        return ret;
    }

    /**
     * Returns additional information about a corporation's members which helps tracking their activities
     * 
     * cache over {@link Swagger#get_corporations_membertracking}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_membertracking> membertracking(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_membertracking> ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_membertracking_holder);
            try {
                synchronized (get_corporations_corporation_id_membertracking_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_membertracking_holder);
                    {
                        ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_membertracking>();
                            get_corporations_corporation_id_membertracking_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_membertracking> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_membertracking", (page, properties) -> (cache.swagger).get_corporations_membertracking(corporation_id, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_membertracking_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_membertracking_holder);
            }
        }
        return ret;
    }

    /**
     * List open market orders placed on behalf of a corporation
     * 
     * cache over {@link Swagger#get_corporations_orders}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_orders> orders(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_orders> ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_orders_holder);
            try {
                synchronized (get_corporations_corporation_id_orders_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_orders_holder);
                    {
                        ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_orders>();
                            get_corporations_corporation_id_orders_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_orders> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_orders", (page, properties) -> (cache.swagger).get_corporations_orders(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Accountant", "Trader"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_holder);
            }
        }
        return ret;
    }

    /**
     * List cancelled and expired market orders placed on behalf of a corporation up to 90 days in the past.
     * 
     * cache over {@link Swagger#get_corporations_orders_history}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_orders_history> orders_history(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_orders_history> ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_orders_history_holder);
            try {
                synchronized (get_corporations_corporation_id_orders_history_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_orders_history_holder);
                    {
                        ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_orders_history>();
                            get_corporations_corporation_id_orders_history_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_orders_history> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_orders_history", (page, properties) -> (cache.swagger).get_corporations_orders_history(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Accountant", "Trader"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_history_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_orders_history_holder);
            }
        }
        return ret;
    }

    /**
     * Return the roles of all members if the character has the personnel manager role or any grantable role.
     * 
     * cache over {@link Swagger#get_corporations_roles}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_roles> roles(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_roles> ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_roles_holder);
            try {
                synchronized (get_corporations_corporation_id_roles_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_roles_holder);
                    {
                        ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_roles>();
                            get_corporations_corporation_id_roles_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_roles> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_roles", (page, properties) -> (cache.swagger).get_corporations_roles(corporation_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_holder);
            }
        }
        return ret;
    }

    /**
     * Return how roles have changed for a coporation's members, up to a month
     * 
     * cache over {@link Swagger#get_corporations_roles_history}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_roles_history> roles_history(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_roles_history> ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_roles_history_holder);
            try {
                synchronized (get_corporations_corporation_id_roles_history_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_roles_history_holder);
                    {
                        ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_roles_history>();
                            get_corporations_corporation_id_roles_history_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_roles_history> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_roles_history", (page, properties) -> (cache.swagger).get_corporations_roles_history(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_history_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_roles_history_holder);
            }
        }
        return ret;
    }

    /**
     * Return the current shareholders of a corporation.
     * 
     * cache over {@link Swagger#get_corporations_shareholders}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_shareholders> shareholders(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_shareholders> ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_shareholders_holder);
            try {
                synchronized (get_corporations_corporation_id_shareholders_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_shareholders_holder);
                    {
                        ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_shareholders>();
                            get_corporations_corporation_id_shareholders_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_shareholders> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_shareholders", (page, properties) -> (cache.swagger).get_corporations_shareholders(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_shareholders_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_shareholders_holder);
            }
        }
        return ret;
    }

    /**
     * Return corporation standings from agents, NPC corporations, and factions
     * 
     * cache over {@link Swagger#get_corporations_standings}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<M_get_standings_3> standings(int corporation_id) {
        ListHolderImpl<M_get_standings_3> ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_standings_holder);
            try {
                synchronized (get_corporations_corporation_id_standings_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_standings_holder);
                    {
                        ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_standings_3>();
                            get_corporations_corporation_id_standings_holder.put(corporation_id, ret);
                            ListHolderImpl<M_get_standings_3> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_standings", (page, properties) -> (cache.swagger).get_corporations_standings(corporation_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_standings_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_standings_holder);
            }
        }
        return ret;
    }

    /**
     * Returns list of corporation starbases (POSes)
     * 
     * cache over {@link Swagger#get_corporations_starbases}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_starbases> starbases(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_starbases> ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_starbases_holder);
            try {
                synchronized (get_corporations_corporation_id_starbases_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_starbases_holder);
                    {
                        ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_starbases>();
                            get_corporations_corporation_id_starbases_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_starbases> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_starbases", (page, properties) -> (cache.swagger).get_corporations_starbases(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_holder);
            }
        }
        return ret;
    }

    /**
     * Returns various settings and fuels of a starbase (POS)
     * 
     * cache over {@link Swagger#get_corporations_starbases}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param starbase_id
     *     An EVE starbase (POS) ID
     * @param system_id
     *     The solar system this starbase (POS) is located in,
     */
    public ObjHolder<R_get_corporations_corporation_id_starbases_starbase_id> starbases(int corporation_id, long starbase_id, int system_id) {
        K_10_int_long_int param = new K_10_int_long_int(corporation_id, starbase_id, system_id);
        ObjHolderSimple<R_get_corporations_corporation_id_starbases_starbase_id> ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_starbases_starbase_id_holder);
            try {
                synchronized (get_corporations_corporation_id_starbases_starbase_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_starbases_starbase_id_holder);
                    {
                        ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_corporations_corporation_id_starbases_starbase_id>();
                            ObjHolderSimple<R_get_corporations_corporation_id_starbases_starbase_id> finalRet = ret;
                            get_corporations_corporation_id_starbases_starbase_id_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_corporations_corporation_id_starbases_starbase_id", properties -> (cache.swagger).get_corporations_starbases(corporation_id, starbase_id, system_id, properties), item -> finalRet.set(item), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_starbase_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_starbases_starbase_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of corporation structures. This route's version includes the changes to structures detailed in this blog: https://www.eveonline.com/article/upwell-2.0-structures-changes-coming-on-february-13th
     * 
     * cache over {@link Swagger#get_corporations_structures}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_structures> structures(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_structures> ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_structures_holder);
            try {
                synchronized (get_corporations_corporation_id_structures_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_structures_holder);
                    {
                        ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_structures>();
                            get_corporations_corporation_id_structures_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_structures> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_structures", (page, properties) -> (cache.swagger).get_corporations_structures(corporation_id, page, properties), arr -> finalRet.set(arr), new String[] {"Station_Manager"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_structures_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_structures_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a corporation's titles
     * 
     * cache over {@link Swagger#get_corporations_titles}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_titles> titles(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_titles> ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_titles_holder);
            try {
                synchronized (get_corporations_corporation_id_titles_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_titles_holder);
                    {
                        ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_titles>();
                            get_corporations_corporation_id_titles_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_titles> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_titles", (page, properties) -> (cache.swagger).get_corporations_titles(corporation_id, properties), arr -> finalRet.set(arr), new String[] {"Director"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_titles_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_titles_holder);
            }
        }
        return ret;
    }

    /**
     * Get a corporation's wallets
     * 
     * cache over {@link Swagger#get_corporations_wallets}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public ListHolder<R_get_corporations_corporation_id_wallets> wallets(int corporation_id) {
        ListHolderImpl<R_get_corporations_corporation_id_wallets> ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_holder);
            try {
                synchronized (get_corporations_corporation_id_wallets_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_holder);
                    {
                        ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_wallets>();
                            get_corporations_corporation_id_wallets_holder.put(corporation_id, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_wallets> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_wallets", (page, properties) -> (cache.swagger).get_corporations_wallets(corporation_id, properties), arr -> finalRet.set(arr), new String[] {"Accountant", "Junior_Accountant"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_holder);
            }
        }
        return ret;
    }

    /**
     * Retrieve the given corporation's wallet journal for the given division going 30 days back
     * 
     * cache over {@link Swagger#get_corporations_wallets_journal}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param division
     *     Wallet key of the division to fetch journals from
     */
    public ListHolder<M_get_journal_13> wallets_journal(int corporation_id, int division) {
        K_19_int_int param = new K_19_int_int(division, corporation_id);
        ListHolderImpl<M_get_journal_13> ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_division_journal_holder);
            try {
                synchronized (get_corporations_corporation_id_wallets_division_journal_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_division_journal_holder);
                    {
                        ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<M_get_journal_13>();
                            get_corporations_corporation_id_wallets_division_journal_holder.put(param, ret);
                            ListHolderImpl<M_get_journal_13> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_wallets_division_journal", (page, properties) -> (cache.swagger).get_corporations_wallets_journal(corporation_id, division, page, properties), arr -> finalRet.set(arr), new String[] {"Accountant", "Junior_Accountant"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_journal_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_journal_holder);
            }
        }
        return ret;
    }

    /**
     * Get wallet transactions of a corporation
     * 
     * cache over {@link Swagger#get_corporations_wallets_transactions}<br />
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param division
     *     Wallet key of the division to fetch journals from
     * @param from_id
     *     Only show journal entries happened before the transaction referenced by this id
     */
    public ListHolder<R_get_corporations_corporation_id_wallets_division_transactions> wallets_transactions(int corporation_id, int division, Long from_id) {
        K_11_int_int_Long param = new K_11_int_int_Long(division, corporation_id, from_id);
        ListHolderImpl<R_get_corporations_corporation_id_wallets_division_transactions> ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_corporations_corporation_id_wallets_division_transactions_holder);
            try {
                synchronized (get_corporations_corporation_id_wallets_division_transactions_holder)
                {
                    LockWatchDog.BARKER.hld(get_corporations_corporation_id_wallets_division_transactions_holder);
                    {
                        ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
                        if (ret == null) {
                            ret = new ListHolderImpl<R_get_corporations_corporation_id_wallets_division_transactions>();
                            get_corporations_corporation_id_wallets_division_transactions_holder.put(param, ret);
                            ListHolderImpl<R_get_corporations_corporation_id_wallets_division_transactions> finalRet = ret;
                            (cache).addFetchCacheArray("get_corporations_corporation_id_wallets_division_transactions", (page, properties) -> (cache.swagger).get_corporations_wallets_transactions(corporation_id, division, from_id, properties), arr -> finalRet.set(arr), new String[] {"Accountant", "Junior_Accountant"});
                        }
                    }
                    LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_transactions_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_corporations_corporation_id_wallets_division_transactions_holder);
            }
        }
        return ret;
    }
}
