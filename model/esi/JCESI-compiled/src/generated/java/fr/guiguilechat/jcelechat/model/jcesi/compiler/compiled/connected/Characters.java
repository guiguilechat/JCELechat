package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_0_int_Integer;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_18_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_19_String_LString_int_Boolean;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_1_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_2_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_3_Boolean_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_4_Integer_int_Lint;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_5_int_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_6_Long_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_bids_4;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_agents_research;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_bookmarks_folders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar_event_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar_event_id_attendees;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_clones;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fatigue;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fittings;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fleet;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_labels;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_lists;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_mail_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_medals;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mining;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_opportunities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_search;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_ship;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skillqueue;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skills;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_titles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Characters {
    public final SwaggerCOCache<?> cache;
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_agents_research>> get_characters_character_id_agents_research_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_attributes>> get_characters_character_id_attributes_holder = new HashMap<>();
    private final Map<K_0_int_Integer, ObsListHolderImpl<R_get_characters_character_id_calendar>> get_characters_character_id_calendar_holder = new HashMap<>();
    private final Map<K_1_int_int, ObsListHolderImpl<R_get_characters_character_id_calendar_event_id_attendees>> get_characters_character_id_calendar_event_id_attendees_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_contacts_labels_2>> get_characters_character_id_contacts_labels_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_contracts_22>> get_characters_character_id_contracts_holder = new HashMap<>();
    private final Map<K_2_int_int, ObsListHolderImpl<M_get_contracts_contract_bids_4>> get_characters_character_id_contracts_contract_id_bids_holder = new HashMap<>();
    private final Map<K_2_int_int, ObsListHolderImpl<M_get_contracts_contract_items_6>> get_characters_character_id_contracts_contract_id_items_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_fatigue>> get_characters_character_id_fatigue_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_fleet>> get_characters_character_id_fleet_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_fw_stats>> get_characters_character_id_fw_stats_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<Integer>> get_characters_character_id_implants_holder = new HashMap<>();
    private final Map<K_3_Boolean_int, ObsListHolderImpl<R_get_characters_character_id_industry_jobs>> get_characters_character_id_industry_jobs_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_killmails_2>> get_characters_character_id_killmails_recent_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_location>> get_characters_character_id_location_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_loyalty_points>> get_characters_character_id_loyalty_points_holder = new HashMap<>();
    private final Map<K_4_Integer_int_Lint, ObsListHolderImpl<R_get_characters_character_id_mail>> get_characters_character_id_mail_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_mail_lists>> get_characters_character_id_mail_lists_holder = new HashMap<>();
    private final Map<K_5_int_int, ObsObjHolderSimple<R_get_characters_character_id_mail_mail_id>> get_characters_character_id_mail_mail_id_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_medals>> get_characters_character_id_medals_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_mining>> get_characters_character_id_mining_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_notifications_contacts>> get_characters_character_id_notifications_contacts_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_opportunities>> get_characters_character_id_opportunities_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_orders_history>> get_characters_character_id_orders_history_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_planets>> get_characters_character_id_planets_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_ship>> get_characters_character_id_ship_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_standings_3>> get_characters_character_id_standings_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_titles>> get_characters_character_id_titles_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<Double>> get_characters_character_id_wallet_holder = new HashMap<>();
    private final Map<K_6_Long_int, ObsListHolderImpl<R_get_characters_character_id_wallet_transactions>> get_characters_character_id_wallet_transactions_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_blueprints>> get_characters_character_id_blueprints_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_bookmarks_9>> get_characters_character_id_bookmarks_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_bookmarks_folders>> get_characters_character_id_bookmarks_folders_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_contacts>> get_characters_character_id_contacts_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_fittings>> get_characters_character_id_fittings_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_online>> get_characters_character_id_online_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_orders>> get_characters_character_id_orders_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_roles>> get_characters_character_id_roles_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_skillqueue>> get_characters_character_id_skillqueue_holder = new HashMap<>();
    private final Map<K_1_int_int, ObsObjHolderSimple<R_get_characters_character_id_calendar_event_id>> get_characters_character_id_calendar_event_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_clones>> get_characters_character_id_clones_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_mail_labels>> get_characters_character_id_mail_labels_holder = new HashMap<>();
    private final Map<K_18_int_int, ObsObjHolderSimple<R_get_characters_character_id_planets_planet_id>> get_characters_character_id_planets_planet_id_holder = new HashMap<>();
    private final Map<K_19_String_LString_int_Boolean, ObsObjHolderSimple<R_get_characters_character_id_search>> get_characters_character_id_search_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolderSimple<R_get_characters_character_id_skills>> get_characters_character_id_skills_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_assets>> get_characters_character_id_assets_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<R_get_characters_character_id_notifications>> get_characters_character_id_notifications_holder = new HashMap<>();
    private final Map<Integer, ObsListHolderImpl<M_get_journal_13>> get_characters_character_id_wallet_journal_holder = new HashMap<>();

    public Characters(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of agents research information for a character. The formula for finding the current research points with an agent is: currentPoints = remainderPoints + pointsPerDay * days(currentTime - researchStartDate)
     * 
     * cache over {@link Swagger#get_characters_agents_research}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_agents_research> agents_research(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_agents_research> ret = get_characters_character_id_agents_research_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_agents_research_holder);
            try {
                synchronized (get_characters_character_id_agents_research_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_agents_research_holder);
                    {
                        ret = get_characters_character_id_agents_research_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_agents_research>();
                            get_characters_character_id_agents_research_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_agents_research> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_agents_research", (page, properties) -> (cache.swagger).get_characters_agents_research(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_agents_research_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_agents_research_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of the characters assets
     * 
     * cache over {@link Swagger#get_characters_assets}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_assets> assets(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_assets> ret = get_characters_character_id_assets_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_assets_holder);
            try {
                synchronized (get_characters_character_id_assets_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_assets_holder);
                    {
                        ret = get_characters_character_id_assets_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_assets>();
                            get_characters_character_id_assets_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_assets> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_assets", (page, properties) -> (cache.swagger).get_characters_assets(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_assets_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_assets_holder);
            }
        }
        return ret;
    }

    /**
     * Return attributes of a character
     * 
     * cache over {@link Swagger#get_characters_attributes}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_attributes> attributes(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_attributes> ret = get_characters_character_id_attributes_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_attributes_holder);
            try {
                synchronized (get_characters_character_id_attributes_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_attributes_holder);
                    {
                        ret = get_characters_character_id_attributes_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_attributes>();
                            ObsObjHolderSimple<R_get_characters_character_id_attributes> finalRet = ret;
                            get_characters_character_id_attributes_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_attributes", properties -> (cache.swagger).get_characters_attributes(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_attributes_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_attributes_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of blueprints the character owns
     * 
     * cache over {@link Swagger#get_characters_blueprints}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_blueprints> blueprints(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_blueprints> ret = get_characters_character_id_blueprints_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_blueprints_holder);
            try {
                synchronized (get_characters_character_id_blueprints_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_blueprints_holder);
                    {
                        ret = get_characters_character_id_blueprints_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_blueprints>();
                            get_characters_character_id_blueprints_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_blueprints> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_blueprints", (page, properties) -> (cache.swagger).get_characters_blueprints(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_blueprints_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_blueprints_holder);
            }
        }
        return ret;
    }

    /**
     * A list of your character's personal bookmarks
     * 
     * cache over {@link Swagger#get_characters_bookmarks}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_bookmarks_9> bookmarks(int character_id) {
        ObsListHolderImpl<M_get_bookmarks_9> ret = get_characters_character_id_bookmarks_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_bookmarks_holder);
            try {
                synchronized (get_characters_character_id_bookmarks_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_bookmarks_holder);
                    {
                        ret = get_characters_character_id_bookmarks_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_bookmarks_9>();
                            get_characters_character_id_bookmarks_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_bookmarks_9> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_bookmarks", (page, properties) -> (cache.swagger).get_characters_bookmarks(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_bookmarks_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_bookmarks_holder);
            }
        }
        return ret;
    }

    /**
     * A list of your character's personal bookmark folders
     * 
     * cache over {@link Swagger#get_characters_bookmarks_folders}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_bookmarks_folders> bookmarks_folders(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_bookmarks_folders> ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_bookmarks_folders_holder);
            try {
                synchronized (get_characters_character_id_bookmarks_folders_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_bookmarks_folders_holder);
                    {
                        ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_bookmarks_folders>();
                            get_characters_character_id_bookmarks_folders_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_bookmarks_folders> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_bookmarks_folders", (page, properties) -> (cache.swagger).get_characters_bookmarks_folders(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_bookmarks_folders_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_bookmarks_folders_holder);
            }
        }
        return ret;
    }

    /**
     * Get 50 event summaries from the calendar. If no from_event ID is given, the resource will return the next 50 chronological event summaries from now. If a from_event ID is specified, it will return the next 50 chronological event summaries from after that event
     * 
     * cache over {@link Swagger#get_characters_calendar}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param from_event
     *     The event ID to retrieve events from
     */
    public ObsListHolder<R_get_characters_character_id_calendar> calendar(int character_id, Integer from_event) {
        K_0_int_Integer param = new K_0_int_Integer(character_id, from_event);
        ObsListHolderImpl<R_get_characters_character_id_calendar> ret = get_characters_character_id_calendar_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_calendar_holder);
            try {
                synchronized (get_characters_character_id_calendar_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_calendar_holder);
                    {
                        ret = get_characters_character_id_calendar_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_calendar>();
                            get_characters_character_id_calendar_holder.put(param, ret);
                            ObsListHolderImpl<R_get_characters_character_id_calendar> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_calendar", (page, properties) -> (cache.swagger).get_characters_calendar(character_id, from_event, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_calendar_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_calendar_holder);
            }
        }
        return ret;
    }

    /**
     * Get all the information for a specific event
     * 
     * cache over {@link Swagger#get_characters_calendar}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The id of the event requested
     */
    public ObsObjHolder<R_get_characters_character_id_calendar_event_id> calendar(int character_id, int event_id) {
        K_1_int_int param = new K_1_int_int(event_id, character_id);
        ObsObjHolderSimple<R_get_characters_character_id_calendar_event_id> ret = get_characters_character_id_calendar_event_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_calendar_event_id_holder);
            try {
                synchronized (get_characters_character_id_calendar_event_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_calendar_event_id_holder);
                    {
                        ret = get_characters_character_id_calendar_event_id_holder.get(param);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_calendar_event_id>();
                            ObsObjHolderSimple<R_get_characters_character_id_calendar_event_id> finalRet = ret;
                            get_characters_character_id_calendar_event_id_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_calendar_event_id", properties -> (cache.swagger).get_characters_calendar(character_id, event_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_calendar_event_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_calendar_event_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get all invited attendees for a given event
     * 
     * cache over {@link Swagger#get_characters_calendar_attendees}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The id of the event requested
     */
    public ObsListHolder<R_get_characters_character_id_calendar_event_id_attendees> calendar_attendees(int character_id, int event_id) {
        K_1_int_int param = new K_1_int_int(event_id, character_id);
        ObsListHolderImpl<R_get_characters_character_id_calendar_event_id_attendees> ret = get_characters_character_id_calendar_event_id_attendees_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_calendar_event_id_attendees_holder);
            try {
                synchronized (get_characters_character_id_calendar_event_id_attendees_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_calendar_event_id_attendees_holder);
                    {
                        ret = get_characters_character_id_calendar_event_id_attendees_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_calendar_event_id_attendees>();
                            get_characters_character_id_calendar_event_id_attendees_holder.put(param, ret);
                            ObsListHolderImpl<R_get_characters_character_id_calendar_event_id_attendees> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_calendar_event_id_attendees", (page, properties) -> (cache.swagger).get_characters_calendar_attendees(character_id, event_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_calendar_event_id_attendees_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_calendar_event_id_attendees_holder);
            }
        }
        return ret;
    }

    /**
     * A list of the character's clones
     * 
     * cache over {@link Swagger#get_characters_clones}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_clones> clones(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_clones> ret = get_characters_character_id_clones_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_clones_holder);
            try {
                synchronized (get_characters_character_id_clones_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_clones_holder);
                    {
                        ret = get_characters_character_id_clones_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_clones>();
                            ObsObjHolderSimple<R_get_characters_character_id_clones> finalRet = ret;
                            get_characters_character_id_clones_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_clones", properties -> (cache.swagger).get_characters_clones(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_clones_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_clones_holder);
            }
        }
        return ret;
    }

    /**
     * Return contacts of a character
     * 
     * cache over {@link Swagger#get_characters_contacts}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_contacts> contacts(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_contacts> ret = get_characters_character_id_contacts_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_contacts_holder);
            try {
                synchronized (get_characters_character_id_contacts_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_contacts_holder);
                    {
                        ret = get_characters_character_id_contacts_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_contacts>();
                            get_characters_character_id_contacts_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_contacts> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_contacts", (page, properties) -> (cache.swagger).get_characters_contacts(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_contacts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_contacts_holder);
            }
        }
        return ret;
    }

    /**
     * Return custom labels for a character's contacts
     * 
     * cache over {@link Swagger#get_characters_contacts_labels}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_contacts_labels_2> contacts_labels(int character_id) {
        ObsListHolderImpl<M_get_contacts_labels_2> ret = get_characters_character_id_contacts_labels_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_contacts_labels_holder);
            try {
                synchronized (get_characters_character_id_contacts_labels_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_contacts_labels_holder);
                    {
                        ret = get_characters_character_id_contacts_labels_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_contacts_labels_2>();
                            get_characters_character_id_contacts_labels_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_contacts_labels_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_contacts_labels", (page, properties) -> (cache.swagger).get_characters_contacts_labels(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_contacts_labels_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_contacts_labels_holder);
            }
        }
        return ret;
    }

    /**
     * Returns contracts available to a character, only if the character is issuer, acceptor or assignee. Only returns contracts no older than 30 days, or if the status is "in_progress".
     * 
     * cache over {@link Swagger#get_characters_contracts}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_contracts_22> contracts(int character_id) {
        ObsListHolderImpl<M_get_contracts_22> ret = get_characters_character_id_contracts_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_contracts_holder);
            try {
                synchronized (get_characters_character_id_contracts_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_contracts_holder);
                    {
                        ret = get_characters_character_id_contracts_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_contracts_22>();
                            get_characters_character_id_contracts_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_contracts_22> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_contracts", (page, properties) -> (cache.swagger).get_characters_contracts(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_contracts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_contracts_holder);
            }
        }
        return ret;
    }

    /**
     * Lists bids on a particular auction contract
     * 
     * cache over {@link Swagger#get_characters_contracts_bids}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param contract_id
     *     ID of a contract
     */
    public ObsListHolder<M_get_contracts_contract_bids_4> contracts_bids(int character_id, int contract_id) {
        K_2_int_int param = new K_2_int_int(contract_id, character_id);
        ObsListHolderImpl<M_get_contracts_contract_bids_4> ret = get_characters_character_id_contracts_contract_id_bids_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_contracts_contract_id_bids_holder);
            try {
                synchronized (get_characters_character_id_contracts_contract_id_bids_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_contracts_contract_id_bids_holder);
                    {
                        ret = get_characters_character_id_contracts_contract_id_bids_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_contracts_contract_bids_4>();
                            get_characters_character_id_contracts_contract_id_bids_holder.put(param, ret);
                            ObsListHolderImpl<M_get_contracts_contract_bids_4> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_contracts_contract_id_bids", (page, properties) -> (cache.swagger).get_characters_contracts_bids(character_id, contract_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_contracts_contract_id_bids_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_contracts_contract_id_bids_holder);
            }
        }
        return ret;
    }

    /**
     * Lists items of a particular contract
     * 
     * cache over {@link Swagger#get_characters_contracts_items}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param contract_id
     *     ID of a contract
     */
    public ObsListHolder<M_get_contracts_contract_items_6> contracts_items(int character_id, int contract_id) {
        K_2_int_int param = new K_2_int_int(contract_id, character_id);
        ObsListHolderImpl<M_get_contracts_contract_items_6> ret = get_characters_character_id_contracts_contract_id_items_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_contracts_contract_id_items_holder);
            try {
                synchronized (get_characters_character_id_contracts_contract_id_items_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_contracts_contract_id_items_holder);
                    {
                        ret = get_characters_character_id_contracts_contract_id_items_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_contracts_contract_items_6>();
                            get_characters_character_id_contracts_contract_id_items_holder.put(param, ret);
                            ObsListHolderImpl<M_get_contracts_contract_items_6> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_contracts_contract_id_items", (page, properties) -> (cache.swagger).get_characters_contracts_items(character_id, contract_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_contracts_contract_id_items_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_contracts_contract_id_items_holder);
            }
        }
        return ret;
    }

    /**
     * Return a character's jump activation and fatigue information
     * 
     * cache over {@link Swagger#get_characters_fatigue}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_fatigue> fatigue(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_fatigue> ret = get_characters_character_id_fatigue_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_fatigue_holder);
            try {
                synchronized (get_characters_character_id_fatigue_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_fatigue_holder);
                    {
                        ret = get_characters_character_id_fatigue_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_fatigue>();
                            ObsObjHolderSimple<R_get_characters_character_id_fatigue> finalRet = ret;
                            get_characters_character_id_fatigue_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_fatigue", properties -> (cache.swagger).get_characters_fatigue(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_fatigue_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_fatigue_holder);
            }
        }
        return ret;
    }

    /**
     * Return fittings of a character
     * 
     * cache over {@link Swagger#get_characters_fittings}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_fittings> fittings(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_fittings> ret = get_characters_character_id_fittings_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_fittings_holder);
            try {
                synchronized (get_characters_character_id_fittings_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_fittings_holder);
                    {
                        ret = get_characters_character_id_fittings_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_fittings>();
                            get_characters_character_id_fittings_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_fittings> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_fittings", (page, properties) -> (cache.swagger).get_characters_fittings(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_fittings_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_fittings_holder);
            }
        }
        return ret;
    }

    /**
     * Return the fleet ID the character is in, if any.
     * 
     * cache over {@link Swagger#get_characters_fleet}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_fleet> fleet(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_fleet> ret = get_characters_character_id_fleet_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_fleet_holder);
            try {
                synchronized (get_characters_character_id_fleet_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_fleet_holder);
                    {
                        ret = get_characters_character_id_fleet_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_fleet>();
                            ObsObjHolderSimple<R_get_characters_character_id_fleet> finalRet = ret;
                            get_characters_character_id_fleet_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_fleet", properties -> (cache.swagger).get_characters_fleet(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_fleet_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_fleet_holder);
            }
        }
        return ret;
    }

    /**
     * Statistical overview of a character involved in faction warfare
     * 
     * cache over {@link Swagger#get_characters_fw_stats}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_fw_stats> fw_stats(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_fw_stats> ret = get_characters_character_id_fw_stats_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_fw_stats_holder);
            try {
                synchronized (get_characters_character_id_fw_stats_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_fw_stats_holder);
                    {
                        ret = get_characters_character_id_fw_stats_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_fw_stats>();
                            ObsObjHolderSimple<R_get_characters_character_id_fw_stats> finalRet = ret;
                            get_characters_character_id_fw_stats_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_fw_stats", properties -> (cache.swagger).get_characters_fw_stats(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_fw_stats_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_fw_stats_holder);
            }
        }
        return ret;
    }

    /**
     * Search for entities that match a given sub-string.
     * 
     * cache over {@link Swagger#get_characters}<br />
     * 
     * @param categories
     *     Type of entities to search for
     * @param character_id
     *     An EVE character ID
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public ObsObjHolder<R_get_characters_character_id_search> get(String[] categories,
        int character_id,
        String search,
        Boolean strict) {
        K_19_String_LString_int_Boolean param = new K_19_String_LString_int_Boolean(search, categories, character_id, strict);
        ObsObjHolderSimple<R_get_characters_character_id_search> ret = get_characters_character_id_search_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_search_holder);
            try {
                synchronized (get_characters_character_id_search_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_search_holder);
                    {
                        ret = get_characters_character_id_search_holder.get(param);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_search>();
                            ObsObjHolderSimple<R_get_characters_character_id_search> finalRet = ret;
                            get_characters_character_id_search_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_search", properties -> (cache.swagger).get_characters(categories, character_id, search, strict, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_search_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_search_holder);
            }
        }
        return ret;
    }

    /**
     * Return implants on the active clone of a character
     * 
     * cache over {@link Swagger#get_characters_implants}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<Integer> implants(int character_id) {
        ObsListHolderImpl<Integer> ret = get_characters_character_id_implants_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_implants_holder);
            try {
                synchronized (get_characters_character_id_implants_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_implants_holder);
                    {
                        ret = get_characters_character_id_implants_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<Integer>();
                            get_characters_character_id_implants_holder.put(character_id, ret);
                            ObsListHolderImpl<Integer> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_implants", (page, properties) -> (cache.swagger).get_characters_implants(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_implants_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_implants_holder);
            }
        }
        return ret;
    }

    /**
     * List industry jobs placed by a character
     * 
     * cache over {@link Swagger#get_characters_industry_jobs}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param include_completed
     *     Whether to retrieve completed character industry jobs. Only includes jobs from the past 90 days
     */
    public ObsListHolder<R_get_characters_character_id_industry_jobs> industry_jobs(int character_id, Boolean include_completed) {
        K_3_Boolean_int param = new K_3_Boolean_int(include_completed, character_id);
        ObsListHolderImpl<R_get_characters_character_id_industry_jobs> ret = get_characters_character_id_industry_jobs_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_industry_jobs_holder);
            try {
                synchronized (get_characters_character_id_industry_jobs_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_industry_jobs_holder);
                    {
                        ret = get_characters_character_id_industry_jobs_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_industry_jobs>();
                            get_characters_character_id_industry_jobs_holder.put(param, ret);
                            ObsListHolderImpl<R_get_characters_character_id_industry_jobs> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_industry_jobs", (page, properties) -> (cache.swagger).get_characters_industry_jobs(character_id, include_completed, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_industry_jobs_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_industry_jobs_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of a character's kills and losses going back 90 days
     * 
     * cache over {@link Swagger#get_characters_killmails_recent}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_killmails_2> killmails_recent(int character_id) {
        ObsListHolderImpl<M_get_killmails_2> ret = get_characters_character_id_killmails_recent_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_killmails_recent_holder);
            try {
                synchronized (get_characters_character_id_killmails_recent_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_killmails_recent_holder);
                    {
                        ret = get_characters_character_id_killmails_recent_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_killmails_2>();
                            get_characters_character_id_killmails_recent_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_killmails_2> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_killmails_recent", (page, properties) -> (cache.swagger).get_characters_killmails_recent(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_killmails_recent_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_killmails_recent_holder);
            }
        }
        return ret;
    }

    /**
     * Information about the characters current location. Returns the current solar system id, and also the current station or structure ID if applicable
     * 
     * cache over {@link Swagger#get_characters_location}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_location> location(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_location> ret = get_characters_character_id_location_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_location_holder);
            try {
                synchronized (get_characters_character_id_location_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_location_holder);
                    {
                        ret = get_characters_character_id_location_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_location>();
                            ObsObjHolderSimple<R_get_characters_character_id_location> finalRet = ret;
                            get_characters_character_id_location_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_location", properties -> (cache.swagger).get_characters_location(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_location_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_location_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of loyalty points for all corporations the character has worked for
     * 
     * cache over {@link Swagger#get_characters_loyalty_points}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_loyalty_points> loyalty_points(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_loyalty_points> ret = get_characters_character_id_loyalty_points_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_loyalty_points_holder);
            try {
                synchronized (get_characters_character_id_loyalty_points_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_loyalty_points_holder);
                    {
                        ret = get_characters_character_id_loyalty_points_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_loyalty_points>();
                            get_characters_character_id_loyalty_points_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_loyalty_points> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_loyalty_points", (page, properties) -> (cache.swagger).get_characters_loyalty_points(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_loyalty_points_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_loyalty_points_holder);
            }
        }
        return ret;
    }

    /**
     * Return the 50 most recent mail headers belonging to the character that match the query criteria. Queries can be filtered by label, and last_mail_id can be used to paginate backwards
     * 
     * cache over {@link Swagger#get_characters_mail}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param labels
     *     Fetch only mails that match one or more of the given labels
     * @param last_mail_id
     *     List only mail with an ID lower than the given ID, if present
     */
    public ObsListHolder<R_get_characters_character_id_mail> mail(int character_id, int[] labels, Integer last_mail_id) {
        K_4_Integer_int_Lint param = new K_4_Integer_int_Lint(last_mail_id, character_id, labels);
        ObsListHolderImpl<R_get_characters_character_id_mail> ret = get_characters_character_id_mail_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_mail_holder);
            try {
                synchronized (get_characters_character_id_mail_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_mail_holder);
                    {
                        ret = get_characters_character_id_mail_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_mail>();
                            get_characters_character_id_mail_holder.put(param, ret);
                            ObsListHolderImpl<R_get_characters_character_id_mail> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_mail", (page, properties) -> (cache.swagger).get_characters_mail(character_id, labels, last_mail_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_mail_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_mail_holder);
            }
        }
        return ret;
    }

    /**
     * Return the contents of an EVE mail
     * 
     * cache over {@link Swagger#get_characters_mail}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param mail_id
     *     An EVE mail ID
     */
    public ObsObjHolder<R_get_characters_character_id_mail_mail_id> mail(int character_id, int mail_id) {
        K_5_int_int param = new K_5_int_int(mail_id, character_id);
        ObsObjHolderSimple<R_get_characters_character_id_mail_mail_id> ret = get_characters_character_id_mail_mail_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_mail_mail_id_holder);
            try {
                synchronized (get_characters_character_id_mail_mail_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_mail_mail_id_holder);
                    {
                        ret = get_characters_character_id_mail_mail_id_holder.get(param);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_mail_mail_id>();
                            ObsObjHolderSimple<R_get_characters_character_id_mail_mail_id> finalRet = ret;
                            get_characters_character_id_mail_mail_id_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_mail_mail_id", properties -> (cache.swagger).get_characters_mail(character_id, mail_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_mail_mail_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_mail_mail_id_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of the users mail labels, unread counts for each label and a total unread count.
     * 
     * cache over {@link Swagger#get_characters_mail_labels}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_mail_labels> mail_labels(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_mail_labels> ret = get_characters_character_id_mail_labels_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_mail_labels_holder);
            try {
                synchronized (get_characters_character_id_mail_labels_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_mail_labels_holder);
                    {
                        ret = get_characters_character_id_mail_labels_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_mail_labels>();
                            ObsObjHolderSimple<R_get_characters_character_id_mail_labels> finalRet = ret;
                            get_characters_character_id_mail_labels_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_mail_labels", properties -> (cache.swagger).get_characters_mail_labels(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_mail_labels_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_mail_labels_holder);
            }
        }
        return ret;
    }

    /**
     * Return all mailing lists that the character is subscribed to
     * 
     * cache over {@link Swagger#get_characters_mail_lists}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_mail_lists> mail_lists(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_mail_lists> ret = get_characters_character_id_mail_lists_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_mail_lists_holder);
            try {
                synchronized (get_characters_character_id_mail_lists_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_mail_lists_holder);
                    {
                        ret = get_characters_character_id_mail_lists_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_mail_lists>();
                            get_characters_character_id_mail_lists_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_mail_lists> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_mail_lists", (page, properties) -> (cache.swagger).get_characters_mail_lists(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_mail_lists_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_mail_lists_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of medals the character has
     * 
     * cache over {@link Swagger#get_characters_medals}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_medals> medals(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_medals> ret = get_characters_character_id_medals_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_medals_holder);
            try {
                synchronized (get_characters_character_id_medals_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_medals_holder);
                    {
                        ret = get_characters_character_id_medals_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_medals>();
                            get_characters_character_id_medals_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_medals> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_medals", (page, properties) -> (cache.swagger).get_characters_medals(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_medals_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_medals_holder);
            }
        }
        return ret;
    }

    /**
     * Paginated record of all mining done by a character for the past 30 days
     * 
     * cache over {@link Swagger#get_characters_mining}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_mining> mining(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_mining> ret = get_characters_character_id_mining_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_mining_holder);
            try {
                synchronized (get_characters_character_id_mining_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_mining_holder);
                    {
                        ret = get_characters_character_id_mining_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_mining>();
                            get_characters_character_id_mining_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_mining> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_mining", (page, properties) -> (cache.swagger).get_characters_mining(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_mining_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_mining_holder);
            }
        }
        return ret;
    }

    /**
     * Return character notifications
     * 
     * cache over {@link Swagger#get_characters_notifications}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_notifications> notifications(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_notifications> ret = get_characters_character_id_notifications_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_notifications_holder);
            try {
                synchronized (get_characters_character_id_notifications_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_notifications_holder);
                    {
                        ret = get_characters_character_id_notifications_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_notifications>();
                            get_characters_character_id_notifications_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_notifications> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_notifications", (page, properties) -> (cache.swagger).get_characters_notifications(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_notifications_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_notifications_holder);
            }
        }
        return ret;
    }

    /**
     * Return notifications about having been added to someone's contact list
     * 
     * cache over {@link Swagger#get_characters_notifications_contacts}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_notifications_contacts> notifications_contacts(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_notifications_contacts> ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_notifications_contacts_holder);
            try {
                synchronized (get_characters_character_id_notifications_contacts_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_notifications_contacts_holder);
                    {
                        ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_notifications_contacts>();
                            get_characters_character_id_notifications_contacts_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_notifications_contacts> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_notifications_contacts", (page, properties) -> (cache.swagger).get_characters_notifications_contacts(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_notifications_contacts_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_notifications_contacts_holder);
            }
        }
        return ret;
    }

    /**
     * Checks if the character is currently online
     * 
     * cache over {@link Swagger#get_characters_online}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_online> online(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_online> ret = get_characters_character_id_online_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_online_holder);
            try {
                synchronized (get_characters_character_id_online_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_online_holder);
                    {
                        ret = get_characters_character_id_online_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_online>();
                            ObsObjHolderSimple<R_get_characters_character_id_online> finalRet = ret;
                            get_characters_character_id_online_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_online", properties -> (cache.swagger).get_characters_online(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_online_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_online_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of tasks finished by a character
     * 
     * cache over {@link Swagger#get_characters_opportunities}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_opportunities> opportunities(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_opportunities> ret = get_characters_character_id_opportunities_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_opportunities_holder);
            try {
                synchronized (get_characters_character_id_opportunities_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_opportunities_holder);
                    {
                        ret = get_characters_character_id_opportunities_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_opportunities>();
                            get_characters_character_id_opportunities_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_opportunities> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_opportunities", (page, properties) -> (cache.swagger).get_characters_opportunities(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_opportunities_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_opportunities_holder);
            }
        }
        return ret;
    }

    /**
     * List open market orders placed by a character
     * 
     * cache over {@link Swagger#get_characters_orders}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_orders> orders(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_orders> ret = get_characters_character_id_orders_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_orders_holder);
            try {
                synchronized (get_characters_character_id_orders_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_orders_holder);
                    {
                        ret = get_characters_character_id_orders_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_orders>();
                            get_characters_character_id_orders_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_orders> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_orders", (page, properties) -> (cache.swagger).get_characters_orders(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_orders_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_orders_holder);
            }
        }
        return ret;
    }

    /**
     * List cancelled and expired market orders placed by a character up to 90 days in the past.
     * 
     * cache over {@link Swagger#get_characters_orders_history}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_orders_history> orders_history(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_orders_history> ret = get_characters_character_id_orders_history_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_orders_history_holder);
            try {
                synchronized (get_characters_character_id_orders_history_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_orders_history_holder);
                    {
                        ret = get_characters_character_id_orders_history_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_orders_history>();
                            get_characters_character_id_orders_history_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_orders_history> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_orders_history", (page, properties) -> (cache.swagger).get_characters_orders_history(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_orders_history_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_orders_history_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a list of all planetary colonies owned by a character.
     * 
     * cache over {@link Swagger#get_characters_planets}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_planets> planets(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_planets> ret = get_characters_character_id_planets_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_planets_holder);
            try {
                synchronized (get_characters_character_id_planets_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_planets_holder);
                    {
                        ret = get_characters_character_id_planets_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_planets>();
                            get_characters_character_id_planets_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_planets> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_planets", (page, properties) -> (cache.swagger).get_characters_planets(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_planets_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_planets_holder);
            }
        }
        return ret;
    }

    /**
     * Returns full details on the layout of a single planetary colony, including links, pins and routes. Note: Planetary information is only recalculated when the colony is viewed through the client. Information will not update until this criteria is met.
     * 
     * cache over {@link Swagger#get_characters_planets}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param planet_id
     *     Planet id of the target planet
     */
    public ObsObjHolder<R_get_characters_character_id_planets_planet_id> planets(int character_id, int planet_id) {
        K_18_int_int param = new K_18_int_int(planet_id, character_id);
        ObsObjHolderSimple<R_get_characters_character_id_planets_planet_id> ret = get_characters_character_id_planets_planet_id_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_planets_planet_id_holder);
            try {
                synchronized (get_characters_character_id_planets_planet_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_planets_planet_id_holder);
                    {
                        ret = get_characters_character_id_planets_planet_id_holder.get(param);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_planets_planet_id>();
                            ObsObjHolderSimple<R_get_characters_character_id_planets_planet_id> finalRet = ret;
                            get_characters_character_id_planets_planet_id_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_planets_planet_id", properties -> (cache.swagger).get_characters_planets(character_id, planet_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_planets_planet_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_planets_planet_id_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a character's corporation roles
     * 
     * cache over {@link Swagger#get_characters_roles}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_roles> roles(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_roles> ret = get_characters_character_id_roles_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_roles_holder);
            try {
                synchronized (get_characters_character_id_roles_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_roles_holder);
                    {
                        ret = get_characters_character_id_roles_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_roles>();
                            ObsObjHolderSimple<R_get_characters_character_id_roles> finalRet = ret;
                            get_characters_character_id_roles_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_roles", properties -> (cache.swagger).get_characters_roles(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_roles_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_roles_holder);
            }
        }
        return ret;
    }

    /**
     * Get the current ship type, name and id
     * 
     * cache over {@link Swagger#get_characters_ship}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_ship> ship(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_ship> ret = get_characters_character_id_ship_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_ship_holder);
            try {
                synchronized (get_characters_character_id_ship_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_ship_holder);
                    {
                        ret = get_characters_character_id_ship_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_ship>();
                            ObsObjHolderSimple<R_get_characters_character_id_ship> finalRet = ret;
                            get_characters_character_id_ship_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_ship", properties -> (cache.swagger).get_characters_ship(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_ship_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_ship_holder);
            }
        }
        return ret;
    }

    /**
     * List the configured skill queue for the given character
     * 
     * cache over {@link Swagger#get_characters_skillqueue}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_skillqueue> skillqueue(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_skillqueue> ret = get_characters_character_id_skillqueue_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_skillqueue_holder);
            try {
                synchronized (get_characters_character_id_skillqueue_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_skillqueue_holder);
                    {
                        ret = get_characters_character_id_skillqueue_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_skillqueue>();
                            get_characters_character_id_skillqueue_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_skillqueue> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_skillqueue", (page, properties) -> (cache.swagger).get_characters_skillqueue(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_skillqueue_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_skillqueue_holder);
            }
        }
        return ret;
    }

    /**
     * List all trained skills for the given character
     * 
     * cache over {@link Swagger#get_characters_skills}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<R_get_characters_character_id_skills> skills(int character_id) {
        ObsObjHolderSimple<R_get_characters_character_id_skills> ret = get_characters_character_id_skills_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_skills_holder);
            try {
                synchronized (get_characters_character_id_skills_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_skills_holder);
                    {
                        ret = get_characters_character_id_skills_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_characters_character_id_skills>();
                            ObsObjHolderSimple<R_get_characters_character_id_skills> finalRet = ret;
                            get_characters_character_id_skills_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_skills", properties -> (cache.swagger).get_characters_skills(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_skills_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_skills_holder);
            }
        }
        return ret;
    }

    /**
     * Return character standings from agents, NPC corporations, and factions
     * 
     * cache over {@link Swagger#get_characters_standings}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_standings_3> standings(int character_id) {
        ObsListHolderImpl<M_get_standings_3> ret = get_characters_character_id_standings_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_standings_holder);
            try {
                synchronized (get_characters_character_id_standings_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_standings_holder);
                    {
                        ret = get_characters_character_id_standings_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_standings_3>();
                            get_characters_character_id_standings_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_standings_3> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_standings", (page, properties) -> (cache.swagger).get_characters_standings(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_standings_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_standings_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a character's titles
     * 
     * cache over {@link Swagger#get_characters_titles}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<R_get_characters_character_id_titles> titles(int character_id) {
        ObsListHolderImpl<R_get_characters_character_id_titles> ret = get_characters_character_id_titles_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_titles_holder);
            try {
                synchronized (get_characters_character_id_titles_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_titles_holder);
                    {
                        ret = get_characters_character_id_titles_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_titles>();
                            get_characters_character_id_titles_holder.put(character_id, ret);
                            ObsListHolderImpl<R_get_characters_character_id_titles> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_titles", (page, properties) -> (cache.swagger).get_characters_titles(character_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_titles_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_titles_holder);
            }
        }
        return ret;
    }

    /**
     * Returns a character's wallet balance
     * 
     * cache over {@link Swagger#get_characters_wallet}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsObjHolder<Double> wallet(int character_id) {
        ObsObjHolderSimple<Double> ret = get_characters_character_id_wallet_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_wallet_holder);
            try {
                synchronized (get_characters_character_id_wallet_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_wallet_holder);
                    {
                        ret = get_characters_character_id_wallet_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<Double>();
                            ObsObjHolderSimple<Double> finalRet = ret;
                            get_characters_character_id_wallet_holder.put(character_id, ret);
                            (cache).addFetchCacheObject("get_characters_character_id_wallet", properties -> (cache.swagger).get_characters_wallet(character_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_wallet_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_wallet_holder);
            }
        }
        return ret;
    }

    /**
     * Retrieve the given character's wallet journal going 30 days back
     * 
     * cache over {@link Swagger#get_characters_wallet_journal}<br />
     * 
     * @param character_id
     *     An EVE character ID
     */
    public ObsListHolder<M_get_journal_13> wallet_journal(int character_id) {
        ObsListHolderImpl<M_get_journal_13> ret = get_characters_character_id_wallet_journal_holder.get(character_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_wallet_journal_holder);
            try {
                synchronized (get_characters_character_id_wallet_journal_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_wallet_journal_holder);
                    {
                        ret = get_characters_character_id_wallet_journal_holder.get(character_id);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<M_get_journal_13>();
                            get_characters_character_id_wallet_journal_holder.put(character_id, ret);
                            ObsListHolderImpl<M_get_journal_13> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_wallet_journal", (page, properties) -> (cache.swagger).get_characters_wallet_journal(character_id, page, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_wallet_journal_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_wallet_journal_holder);
            }
        }
        return ret;
    }

    /**
     * Get wallet transactions of a character
     * 
     * cache over {@link Swagger#get_characters_wallet_transactions}<br />
     * 
     * @param character_id
     *     An EVE character ID
     * @param from_id
     *     Only show transactions happened before the one referenced by this id
     */
    public ObsListHolder<R_get_characters_character_id_wallet_transactions> wallet_transactions(int character_id, Long from_id) {
        K_6_Long_int param = new K_6_Long_int(from_id, character_id);
        ObsListHolderImpl<R_get_characters_character_id_wallet_transactions> ret = get_characters_character_id_wallet_transactions_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_characters_character_id_wallet_transactions_holder);
            try {
                synchronized (get_characters_character_id_wallet_transactions_holder)
                {
                    LockWatchDog.BARKER.hld(get_characters_character_id_wallet_transactions_holder);
                    {
                        ret = get_characters_character_id_wallet_transactions_holder.get(param);
                        if (ret == null) {
                            ret = new ObsListHolderImpl<R_get_characters_character_id_wallet_transactions>();
                            get_characters_character_id_wallet_transactions_holder.put(param, ret);
                            ObsListHolderImpl<R_get_characters_character_id_wallet_transactions> finalRet = ret;
                            (cache).addFetchCacheArray("get_characters_character_id_wallet_transactions", (page, properties) -> (cache.swagger).get_characters_wallet_transactions(character_id, from_id, properties), arr -> finalRet.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_characters_character_id_wallet_transactions_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_characters_character_id_wallet_transactions_holder);
            }
        }
        return ret;
    }
}
