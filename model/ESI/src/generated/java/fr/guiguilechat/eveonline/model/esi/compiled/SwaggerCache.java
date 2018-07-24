package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_0_int_Integer;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_10_int_long_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_11_int_int_Long;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_12_long_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_13_String_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_14_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_15_Integer_int_order_type;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_16_flag_int_int_Lint_LLint;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_17_String_LString_Boolean;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_18_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_19_String_LString_int_Boolean;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_1_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_20_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_2_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_3_Boolean_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_4_Integer_int_Lint;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_5_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_6_Long_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_7_int_long;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_8_int_int;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_9_int_Boolean;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_assets_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_blueprints_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_bids_4;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_journal_13;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_standings_3;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id_contacts;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id_icons;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_agents_research;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_attributes;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_bookmarks_folders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar_event_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar_event_id_attendees;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_clones;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_contacts;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_corporationhistory;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fatigue;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fittings;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fleet;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fw_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_location;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_labels;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_lists;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_mail_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_medals;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mining;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_notifications;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_notifications_contacts;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_opportunities;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_orders_history;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_portrait;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_search;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_ship;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_skillqueue;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_skills;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_titles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_extractions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_alliancehistory;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_contacts;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_containers_logs;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_customs_offices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_facilities;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_fw_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_icons;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_industry_jobs;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_medals;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_medals_issued;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_members_titles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_membertracking;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_orders_history;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_roles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_roles_history;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_shareholders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_starbases;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_starbases_starbase_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_structures;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_titles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_wallets;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id_members;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id_wings;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_systems;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_wars;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_incursions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_insurance_prices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_structures_structure_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_search;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_structures;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_ancestries;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_bloodlines;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_races;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_system_jumps;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_system_kills;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_wars_war_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


/**
 * @param T
 *     the type of Swagger this refers to. this parameter allows to work on specific implementation of Swagger, thus call its methdos instead of having to cast.
 */
public abstract class SwaggerCache<T extends Swagger> {
    public final T swagger;
    public final Alliances alliances = new Alliances();
    public final Characters characters = new Characters();
    public final Corporation corporation = new Corporation();
    public final Corporations corporations = new Corporations();
    public final Dogma dogma = new Dogma();
    public final Fleets fleets = new Fleets();
    public final Fw fw = new Fw();
    public final Incursions incursions = new Incursions();
    public final Industry industry = new Industry();
    public final Insurance insurance = new Insurance();
    public final Killmails killmails = new Killmails();
    public final Loyalty loyalty = new Loyalty();
    public final Markets markets = new Markets();
    public final Opportunities opportunities = new Opportunities();
    public final Route route = new Route();
    public final Sovereignty sovereignty = new Sovereignty();
    public final Status status = new Status();
    public final Universe universe = new Universe();
    public final Wars wars = new Wars();
    public final Search search = new Search();

    public SwaggerCache(T swag) {
        swagger = swag;
    }

    public abstract<U> SwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public abstract<U> SwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public class Alliances {
        private ObservableList<Integer> get_alliances_holder;
        private final Map<Integer, ObservableList<M_get_contacts_labels_2>> get_alliances_alliance_id_contacts_labels_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_alliances_alliance_id_corporations_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_alliances_alliance_id_contacts>> get_alliances_alliance_id_contacts_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_alliances_alliance_id>> get_alliances_alliance_id_holder = new HashMap<>();

        /**
         * List all active player alliances
         * 
         * cache over {@link Swagger#get_alliances}<br />
         */
        public ObservableList<Integer> alliances() {
            if (get_alliances_holder == null) {
                synchronized (this)
                {
                    if (get_alliances_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_alliances_holder = finalContainer;
                        addFetchCacheArray("get_alliances", (page, headerHandler) -> IntStream.of((swagger).get_alliances(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_alliances_holder;
        }

        /**
         * Return custom labels for an alliance's contacts
         * 
         * cache over {@link Swagger#get_alliances_contacts_labels}<br />
         * 
         * @param alliance_id
         *     An EVE alliance ID
         */
        public ObservableList<M_get_contacts_labels_2> contacts_labels(int alliance_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_contacts_labels_holder)
                {
                    ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_alliances_alliance_id_contacts_labels_holder.put(alliance_id, ret);
                        addFetchCacheArray("get_alliances_alliance_id_contacts_labels", (page, headerHandler) -> (swagger).get_alliances_contacts_labels(alliance_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * List all current member corporations of an alliance
         * 
         * cache over {@link Swagger#get_alliances_corporations}<br />
         * 
         * @param alliance_id
         *     An EVE alliance ID
         */
        public ObservableList<Integer> corporations(int alliance_id) {
            ObservableList<Integer> ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_corporations_holder)
                {
                    ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_alliances_alliance_id_corporations_holder.put(alliance_id, ret);
                        addFetchCacheArray("get_alliances_alliance_id_corporations", (page, headerHandler) -> IntStream.of((swagger).get_alliances_corporations(alliance_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_alliances_alliance_id_icons> icons(int alliance_id) {
            Property<R_get_alliances_alliance_id_icons> ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_icons_holder)
                {
                    ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id_icons> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_alliances_alliance_id_icons_holder.put(alliance_id, ret);
                        addFetchCacheObject("get_alliances_alliance_id_icons", headerHandler -> (swagger).get_alliances_icons(alliance_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_alliances_alliance_id_contacts> contacts(int alliance_id) {
            ObservableList<R_get_alliances_alliance_id_contacts> ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_contacts_holder)
                {
                    ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<R_get_alliances_alliance_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_alliances_alliance_id_contacts_holder.put(alliance_id, ret);
                        addFetchCacheArray("get_alliances_alliance_id_contacts", (page, headerHandler) -> (swagger).get_alliances_contacts(alliance_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_alliances_alliance_id> get(int alliance_id) {
            Property<R_get_alliances_alliance_id> ret = get_alliances_alliance_id_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_holder)
                {
                    ret = get_alliances_alliance_id_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_alliances_alliance_id_holder.put(alliance_id, ret);
                        addFetchCacheObject("get_alliances_alliance_id", headerHandler -> (swagger).get_alliances(alliance_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Characters {
        private final Map<Integer, ObservableList<R_get_characters_character_id_agents_research>> get_characters_character_id_agents_research_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_attributes>> get_characters_character_id_attributes_holder = new HashMap<>();
        private final Map<K_0_int_Integer, ObservableList<R_get_characters_character_id_calendar>> get_characters_character_id_calendar_holder = new HashMap<>();
        private final Map<K_1_int_int, ObservableList<R_get_characters_character_id_calendar_event_id_attendees>> get_characters_character_id_calendar_event_id_attendees_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contacts_labels_2>> get_characters_character_id_contacts_labels_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contracts_22>> get_characters_character_id_contracts_holder = new HashMap<>();
        private final Map<K_2_int_int, ObservableMap<Integer, M_get_contracts_contract_bids_4>> get_characters_character_id_contracts_contract_id_bids_holder = new HashMap<>();
        private final Map<K_2_int_int, ObservableMap<Long, M_get_contracts_contract_items_6>> get_characters_character_id_contracts_contract_id_items_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_corporationhistory>> get_characters_character_id_corporationhistory_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fatigue>> get_characters_character_id_fatigue_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_fittings>> get_characters_character_id_fittings_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fleet>> get_characters_character_id_fleet_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fw_stats>> get_characters_character_id_fw_stats_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_characters_character_id_implants_holder = new HashMap<>();
        private final Map<K_3_Boolean_int, ObservableMap<Integer, R_get_characters_character_id_industry_jobs>> get_characters_character_id_industry_jobs_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_killmails_2>> get_characters_character_id_killmails_recent_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_location>> get_characters_character_id_location_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_loyalty_points>> get_characters_character_id_loyalty_points_holder = new HashMap<>();
        private final Map<K_4_Integer_int_Lint, ObservableList<R_get_characters_character_id_mail>> get_characters_character_id_mail_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_mail_lists>> get_characters_character_id_mail_lists_holder = new HashMap<>();
        private final Map<K_5_int_int, Property<R_get_characters_character_id_mail_mail_id>> get_characters_character_id_mail_mail_id_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_medals>> get_characters_character_id_medals_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_mining>> get_characters_character_id_mining_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_notifications_contacts>> get_characters_character_id_notifications_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_opportunities>> get_characters_character_id_opportunities_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_characters_character_id_orders_history>> get_characters_character_id_orders_history_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_planets>> get_characters_character_id_planets_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_ship>> get_characters_character_id_ship_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_standings_3>> get_characters_character_id_standings_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_titles>> get_characters_character_id_titles_holder = new HashMap<>();
        private final Map<Integer, Property<Double>> get_characters_character_id_wallet_holder = new HashMap<>();
        private final Map<K_6_Long_int, ObservableMap<Long, R_get_characters_character_id_wallet_transactions>> get_characters_character_id_wallet_transactions_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, M_get_blueprints_8>> get_characters_character_id_blueprints_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_bookmarks_9>> get_characters_character_id_bookmarks_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_bookmarks_folders>> get_characters_character_id_bookmarks_folders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_contacts>> get_characters_character_id_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_notifications>> get_characters_character_id_notifications_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_online>> get_characters_character_id_online_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_characters_character_id_orders>> get_characters_character_id_orders_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_portrait>> get_characters_character_id_portrait_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_roles>> get_characters_character_id_roles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_skillqueue>> get_characters_character_id_skillqueue_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_stats>> get_characters_character_id_stats_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_assets_8>> get_characters_character_id_assets_holder = new HashMap<>();
        private final Map<K_1_int_int, Property<R_get_characters_character_id_calendar_event_id>> get_characters_character_id_calendar_event_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_clones>> get_characters_character_id_clones_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_mail_labels>> get_characters_character_id_mail_labels_holder = new HashMap<>();
        private final Map<K_18_int_int, Property<R_get_characters_character_id_planets_planet_id>> get_characters_character_id_planets_planet_id_holder = new HashMap<>();
        private final Map<K_19_String_LString_int_Boolean, Property<R_get_characters_character_id_search>> get_characters_character_id_search_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id>> get_characters_character_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_skills>> get_characters_character_id_skills_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, M_get_journal_13>> get_characters_character_id_wallet_journal_holder = new HashMap<>();

        /**
         * Return a list of agents research information for a character. The formula for finding the current research points with an agent is: currentPoints = remainderPoints + pointsPerDay * days(currentTime - researchStartDate)
         * 
         * cache over {@link Swagger#get_characters_agents_research}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public ObservableList<R_get_characters_character_id_agents_research> agents_research(int character_id) {
            ObservableList<R_get_characters_character_id_agents_research> ret = get_characters_character_id_agents_research_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_agents_research_holder)
                {
                    ret = get_characters_character_id_agents_research_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_agents_research> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_agents_research_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_agents_research", (page, headerHandler) -> (swagger).get_characters_agents_research(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_attributes> attributes(int character_id) {
            Property<R_get_characters_character_id_attributes> ret = get_characters_character_id_attributes_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_attributes_holder)
                {
                    ret = get_characters_character_id_attributes_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_attributes> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_attributes_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_attributes", headerHandler -> (swagger).get_characters_attributes(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get 50 event summaries from the calendar. If no from_event ID is given, the resource will return the next 50 chronological event summaries from now. If a from_event ID is specified, it will return the next 50 chronological event summaries from after that event.
         * 
         * cache over {@link Swagger#get_characters_calendar}<br />
         * 
         * @param character_id
         *     An EVE character ID
         * @param from_event
         *     The event ID to retrieve events from
         */
        public ObservableList<R_get_characters_character_id_calendar> calendar(int character_id, Integer from_event) {
            K_0_int_Integer param = new K_0_int_Integer(character_id, from_event);
            ObservableList<R_get_characters_character_id_calendar> ret = get_characters_character_id_calendar_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_calendar_holder)
                {
                    ret = get_characters_character_id_calendar_holder.get(param);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_calendar> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_calendar_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_calendar", (page, headerHandler) -> (swagger).get_characters_calendar(character_id, from_event, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_calendar_event_id_attendees> calendar_attendees(int character_id, int event_id) {
            K_1_int_int param = new K_1_int_int(event_id, character_id);
            ObservableList<R_get_characters_character_id_calendar_event_id_attendees> ret = get_characters_character_id_calendar_event_id_attendees_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_calendar_event_id_attendees_holder)
                {
                    ret = get_characters_character_id_calendar_event_id_attendees_holder.get(param);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_calendar_event_id_attendees> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_calendar_event_id_attendees_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_calendar_event_id_attendees", (page, headerHandler) -> (swagger).get_characters_calendar_attendees(character_id, event_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_contacts_labels_2> contacts_labels(int character_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_characters_character_id_contacts_labels_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contacts_labels_holder)
                {
                    ret = get_characters_character_id_contacts_labels_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_contacts_labels_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_contacts_labels", (page, headerHandler) -> (swagger).get_characters_contacts_labels(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_contracts_22> contracts(int character_id) {
            ObservableList<M_get_contracts_22> ret = get_characters_character_id_contracts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contracts_holder)
                {
                    ret = get_characters_character_id_contracts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_contracts_22> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_contracts_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_contracts", (page, headerHandler) -> (swagger).get_characters_contracts(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Integer, M_get_contracts_contract_bids_4> contracts_bids(int character_id, int contract_id) {
            K_2_int_int param = new K_2_int_int(contract_id, character_id);
            ObservableMap<Integer, M_get_contracts_contract_bids_4> ret = get_characters_character_id_contracts_contract_id_bids_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_contracts_contract_id_bids_holder)
                {
                    ret = get_characters_character_id_contracts_contract_id_bids_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Integer, M_get_contracts_contract_bids_4> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_contracts_contract_id_bids_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_contracts_contract_id_bids", (page, headerHandler) -> (swagger).get_characters_contracts_bids(character_id, contract_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Integer, M_get_contracts_contract_bids_4> newmap = new LinkedHashMap<>();
                                for (M_get_contracts_contract_bids_4 val: arr) {
                                    newmap.put((val.bid_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, M_get_contracts_contract_items_6> contracts_items(int character_id, int contract_id) {
            K_2_int_int param = new K_2_int_int(contract_id, character_id);
            ObservableMap<Long, M_get_contracts_contract_items_6> ret = get_characters_character_id_contracts_contract_id_items_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_contracts_contract_id_items_holder)
                {
                    ret = get_characters_character_id_contracts_contract_id_items_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Long, M_get_contracts_contract_items_6> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_contracts_contract_id_items_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_contracts_contract_id_items", (page, headerHandler) -> (swagger).get_characters_contracts_items(character_id, contract_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_contracts_contract_items_6> newmap = new LinkedHashMap<>();
                                for (M_get_contracts_contract_items_6 val: arr) {
                                    newmap.put((val.record_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of all the corporations a character has been a member of
         * 
         * cache over {@link Swagger#get_characters_corporationhistory}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public ObservableList<R_get_characters_character_id_corporationhistory> corporationhistory(int character_id) {
            ObservableList<R_get_characters_character_id_corporationhistory> ret = get_characters_character_id_corporationhistory_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_corporationhistory_holder)
                {
                    ret = get_characters_character_id_corporationhistory_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_corporationhistory> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_corporationhistory_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_corporationhistory", (page, headerHandler) -> (swagger).get_characters_corporationhistory(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_fatigue> fatigue(int character_id) {
            Property<R_get_characters_character_id_fatigue> ret = get_characters_character_id_fatigue_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fatigue_holder)
                {
                    ret = get_characters_character_id_fatigue_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fatigue> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_fatigue_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_fatigue", headerHandler -> (swagger).get_characters_fatigue(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_fittings> fittings(int character_id) {
            ObservableList<R_get_characters_character_id_fittings> ret = get_characters_character_id_fittings_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fittings_holder)
                {
                    ret = get_characters_character_id_fittings_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_fittings> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_fittings_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_fittings", (page, headerHandler) -> (swagger).get_characters_fittings(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_fleet> fleet(int character_id) {
            Property<R_get_characters_character_id_fleet> ret = get_characters_character_id_fleet_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fleet_holder)
                {
                    ret = get_characters_character_id_fleet_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fleet> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_fleet_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_fleet", headerHandler -> (swagger).get_characters_fleet(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_fw_stats> fw_stats(int character_id) {
            Property<R_get_characters_character_id_fw_stats> ret = get_characters_character_id_fw_stats_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fw_stats_holder)
                {
                    ret = get_characters_character_id_fw_stats_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fw_stats> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_fw_stats_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_fw_stats", headerHandler -> (swagger).get_characters_fw_stats(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<Integer> implants(int character_id) {
            ObservableList<Integer> ret = get_characters_character_id_implants_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_implants_holder)
                {
                    ret = get_characters_character_id_implants_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_implants_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_implants", (page, headerHandler) -> IntStream.of((swagger).get_characters_implants(character_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
         *     Whether retrieve completed character industry jobs as well
         */
        public ObservableMap<Integer, R_get_characters_character_id_industry_jobs> industry_jobs(int character_id, Boolean include_completed) {
            K_3_Boolean_int param = new K_3_Boolean_int(include_completed, character_id);
            ObservableMap<Integer, R_get_characters_character_id_industry_jobs> ret = get_characters_character_id_industry_jobs_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_industry_jobs_holder)
                {
                    ret = get_characters_character_id_industry_jobs_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Integer, R_get_characters_character_id_industry_jobs> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_industry_jobs_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_industry_jobs", (page, headerHandler) -> (swagger).get_characters_industry_jobs(character_id, include_completed, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Integer, R_get_characters_character_id_industry_jobs> newmap = new LinkedHashMap<>();
                                for (R_get_characters_character_id_industry_jobs val: arr) {
                                    newmap.put((val.job_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_killmails_2> killmails_recent(int character_id) {
            ObservableList<M_get_killmails_2> ret = get_characters_character_id_killmails_recent_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_killmails_recent_holder)
                {
                    ret = get_characters_character_id_killmails_recent_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_killmails_recent_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_killmails_recent", (page, headerHandler) -> (swagger).get_characters_killmails_recent(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Information about the characters current location. Returns the current solar system id, and also the current station or structure ID if applicable.
         * 
         * cache over {@link Swagger#get_characters_location}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public Property<R_get_characters_character_id_location> location(int character_id) {
            Property<R_get_characters_character_id_location> ret = get_characters_character_id_location_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_location_holder)
                {
                    ret = get_characters_character_id_location_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_location> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_location_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_location", headerHandler -> (swagger).get_characters_location(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_loyalty_points> loyalty_points(int character_id) {
            ObservableList<R_get_characters_character_id_loyalty_points> ret = get_characters_character_id_loyalty_points_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_loyalty_points_holder)
                {
                    ret = get_characters_character_id_loyalty_points_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_loyalty_points> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_loyalty_points_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_loyalty_points", (page, headerHandler) -> (swagger).get_characters_loyalty_points(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return the 50 most recent mail headers belonging to the character that match the query criteria. Queries can be filtered by label, and last_mail_id can be used to paginate backwards.
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
        public ObservableList<R_get_characters_character_id_mail> mail(int character_id, int[] labels, Integer last_mail_id) {
            K_4_Integer_int_Lint param = new K_4_Integer_int_Lint(last_mail_id, character_id, labels);
            ObservableList<R_get_characters_character_id_mail> ret = get_characters_character_id_mail_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_holder)
                {
                    ret = get_characters_character_id_mail_holder.get(param);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_mail> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_mail_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_mail", (page, headerHandler) -> (swagger).get_characters_mail(character_id, labels, last_mail_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_mail_lists> mail_lists(int character_id) {
            ObservableList<R_get_characters_character_id_mail_lists> ret = get_characters_character_id_mail_lists_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_lists_holder)
                {
                    ret = get_characters_character_id_mail_lists_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_mail_lists> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_mail_lists_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_mail_lists", (page, headerHandler) -> (swagger).get_characters_mail_lists(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_mail_mail_id> mail(int character_id, int mail_id) {
            K_5_int_int param = new K_5_int_int(mail_id, character_id);
            Property<R_get_characters_character_id_mail_mail_id> ret = get_characters_character_id_mail_mail_id_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_mail_id_holder)
                {
                    ret = get_characters_character_id_mail_mail_id_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_mail_mail_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_mail_mail_id_holder.put(param, ret);
                        addFetchCacheObject("get_characters_character_id_mail_mail_id", headerHandler -> (swagger).get_characters_mail(character_id, mail_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_medals> medals(int character_id) {
            ObservableList<R_get_characters_character_id_medals> ret = get_characters_character_id_medals_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_medals_holder)
                {
                    ret = get_characters_character_id_medals_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_medals> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_medals_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_medals", (page, headerHandler) -> (swagger).get_characters_medals(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_mining> mining(int character_id) {
            ObservableList<R_get_characters_character_id_mining> ret = get_characters_character_id_mining_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mining_holder)
                {
                    ret = get_characters_character_id_mining_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_mining> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_mining_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_mining", (page, headerHandler) -> (swagger).get_characters_mining(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_notifications_contacts> notifications_contacts(int character_id) {
            ObservableList<R_get_characters_character_id_notifications_contacts> ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_notifications_contacts_holder)
                {
                    ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_notifications_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_notifications_contacts_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_notifications_contacts", (page, headerHandler) -> (swagger).get_characters_notifications_contacts(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_opportunities> opportunities(int character_id) {
            ObservableList<R_get_characters_character_id_opportunities> ret = get_characters_character_id_opportunities_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_opportunities_holder)
                {
                    ret = get_characters_character_id_opportunities_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_opportunities> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_opportunities_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_opportunities", (page, headerHandler) -> (swagger).get_characters_opportunities(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_characters_character_id_orders_history> orders_history(int character_id) {
            ObservableMap<Long, R_get_characters_character_id_orders_history> ret = get_characters_character_id_orders_history_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_orders_history_holder)
                {
                    ret = get_characters_character_id_orders_history_holder.get(character_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_characters_character_id_orders_history> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_orders_history_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_orders_history", (page, headerHandler) -> (swagger).get_characters_orders_history(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_characters_character_id_orders_history> newmap = new LinkedHashMap<>();
                                for (R_get_characters_character_id_orders_history val: arr) {
                                    newmap.put((val.order_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_planets> planets(int character_id) {
            ObservableList<R_get_characters_character_id_planets> ret = get_characters_character_id_planets_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_planets_holder)
                {
                    ret = get_characters_character_id_planets_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_planets> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_planets_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_planets", (page, headerHandler) -> (swagger).get_characters_planets(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_ship> ship(int character_id) {
            Property<R_get_characters_character_id_ship> ret = get_characters_character_id_ship_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_ship_holder)
                {
                    ret = get_characters_character_id_ship_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_ship> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_ship_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_ship", headerHandler -> (swagger).get_characters_ship(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_standings_3> standings(int character_id) {
            ObservableList<M_get_standings_3> ret = get_characters_character_id_standings_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_standings_holder)
                {
                    ret = get_characters_character_id_standings_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_standings_3> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_standings_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_standings", (page, headerHandler) -> (swagger).get_characters_standings(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_titles> titles(int character_id) {
            ObservableList<R_get_characters_character_id_titles> ret = get_characters_character_id_titles_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_titles_holder)
                {
                    ret = get_characters_character_id_titles_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_titles_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_titles", (page, headerHandler) -> (swagger).get_characters_titles(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<Double> wallet(int character_id) {
            Property<Double> ret = get_characters_character_id_wallet_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_wallet_holder)
                {
                    ret = get_characters_character_id_wallet_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<Double> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_wallet_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_wallet", headerHandler -> (swagger).get_characters_wallet(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_characters_character_id_wallet_transactions> wallet_transactions(int character_id, Long from_id) {
            K_6_Long_int param = new K_6_Long_int(from_id, character_id);
            ObservableMap<Long, R_get_characters_character_id_wallet_transactions> ret = get_characters_character_id_wallet_transactions_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_wallet_transactions_holder)
                {
                    ret = get_characters_character_id_wallet_transactions_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Long, R_get_characters_character_id_wallet_transactions> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_wallet_transactions_holder.put(param, ret);
                        addFetchCacheArray("get_characters_character_id_wallet_transactions", (page, headerHandler) -> (swagger).get_characters_wallet_transactions(character_id, from_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_characters_character_id_wallet_transactions> newmap = new LinkedHashMap<>();
                                for (R_get_characters_character_id_wallet_transactions val: arr) {
                                    newmap.put((val.transaction_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, M_get_blueprints_8> blueprints(int character_id) {
            ObservableMap<Long, M_get_blueprints_8> ret = get_characters_character_id_blueprints_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_blueprints_holder)
                {
                    ret = get_characters_character_id_blueprints_holder.get(character_id);
                    if (ret == null) {
                        ObservableMap<Long, M_get_blueprints_8> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_blueprints_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_blueprints", (page, headerHandler) -> (swagger).get_characters_blueprints(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_blueprints_8> newmap = new LinkedHashMap<>();
                                for (M_get_blueprints_8 val: arr) {
                                    newmap.put((val.item_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_bookmarks_9> bookmarks(int character_id) {
            ObservableList<M_get_bookmarks_9> ret = get_characters_character_id_bookmarks_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_bookmarks_holder)
                {
                    ret = get_characters_character_id_bookmarks_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_bookmarks_9> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_bookmarks_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_bookmarks", (page, headerHandler) -> (swagger).get_characters_bookmarks(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_bookmarks_folders> bookmarks_folders(int character_id) {
            ObservableList<R_get_characters_character_id_bookmarks_folders> ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_bookmarks_folders_holder)
                {
                    ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_bookmarks_folders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_bookmarks_folders_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_bookmarks_folders", (page, headerHandler) -> (swagger).get_characters_bookmarks_folders(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_contacts> contacts(int character_id) {
            ObservableList<R_get_characters_character_id_contacts> ret = get_characters_character_id_contacts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contacts_holder)
                {
                    ret = get_characters_character_id_contacts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_contacts_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_contacts", (page, headerHandler) -> (swagger).get_characters_contacts(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_notifications> notifications(int character_id) {
            ObservableList<R_get_characters_character_id_notifications> ret = get_characters_character_id_notifications_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_notifications_holder)
                {
                    ret = get_characters_character_id_notifications_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_notifications> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_notifications_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_notifications", (page, headerHandler) -> (swagger).get_characters_notifications(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_online> online(int character_id) {
            Property<R_get_characters_character_id_online> ret = get_characters_character_id_online_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_online_holder)
                {
                    ret = get_characters_character_id_online_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_online> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_online_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_online", headerHandler -> (swagger).get_characters_online(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_characters_character_id_orders> orders(int character_id) {
            ObservableMap<Long, R_get_characters_character_id_orders> ret = get_characters_character_id_orders_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_orders_holder)
                {
                    ret = get_characters_character_id_orders_holder.get(character_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_characters_character_id_orders> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_orders_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_orders", (page, headerHandler) -> (swagger).get_characters_orders(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_characters_character_id_orders> newmap = new LinkedHashMap<>();
                                for (R_get_characters_character_id_orders val: arr) {
                                    newmap.put((val.order_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get portrait urls for a character
         * 
         * cache over {@link Swagger#get_characters_portrait}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public Property<R_get_characters_character_id_portrait> portrait(int character_id) {
            Property<R_get_characters_character_id_portrait> ret = get_characters_character_id_portrait_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_portrait_holder)
                {
                    ret = get_characters_character_id_portrait_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_portrait> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_portrait_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_portrait", headerHandler -> (swagger).get_characters_portrait(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_roles> roles(int character_id) {
            Property<R_get_characters_character_id_roles> ret = get_characters_character_id_roles_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_roles_holder)
                {
                    ret = get_characters_character_id_roles_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_roles> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_roles_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_roles", headerHandler -> (swagger).get_characters_roles(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_characters_character_id_skillqueue> skillqueue(int character_id) {
            ObservableList<R_get_characters_character_id_skillqueue> ret = get_characters_character_id_skillqueue_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_skillqueue_holder)
                {
                    ret = get_characters_character_id_skillqueue_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_skillqueue> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_skillqueue_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_skillqueue", (page, headerHandler) -> (swagger).get_characters_skillqueue(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Returns aggregate yearly stats for a character
         * 
         * cache over {@link Swagger#get_characters_stats}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public ObservableList<R_get_characters_character_id_stats> stats(int character_id) {
            ObservableList<R_get_characters_character_id_stats> ret = get_characters_character_id_stats_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_stats_holder)
                {
                    ret = get_characters_character_id_stats_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_stats> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_stats_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_stats", (page, headerHandler) -> (swagger).get_characters_stats(character_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_assets_8> assets(int character_id) {
            ObservableList<M_get_assets_8> ret = get_characters_character_id_assets_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_assets_holder)
                {
                    ret = get_characters_character_id_assets_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_assets_8> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_characters_character_id_assets_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_assets", (page, headerHandler) -> (swagger).get_characters_assets(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_calendar_event_id> calendar(int character_id, int event_id) {
            K_1_int_int param = new K_1_int_int(event_id, character_id);
            Property<R_get_characters_character_id_calendar_event_id> ret = get_characters_character_id_calendar_event_id_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_calendar_event_id_holder)
                {
                    ret = get_characters_character_id_calendar_event_id_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_calendar_event_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_calendar_event_id_holder.put(param, ret);
                        addFetchCacheObject("get_characters_character_id_calendar_event_id", headerHandler -> (swagger).get_characters_calendar(character_id, event_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_clones> clones(int character_id) {
            Property<R_get_characters_character_id_clones> ret = get_characters_character_id_clones_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_clones_holder)
                {
                    ret = get_characters_character_id_clones_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_clones> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_clones_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_clones", headerHandler -> (swagger).get_characters_clones(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_mail_labels> mail_labels(int character_id) {
            Property<R_get_characters_character_id_mail_labels> ret = get_characters_character_id_mail_labels_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_labels_holder)
                {
                    ret = get_characters_character_id_mail_labels_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_mail_labels> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_mail_labels_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_mail_labels", headerHandler -> (swagger).get_characters_mail_labels(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_planets_planet_id> planets(int character_id, int planet_id) {
            K_18_int_int param = new K_18_int_int(planet_id, character_id);
            Property<R_get_characters_character_id_planets_planet_id> ret = get_characters_character_id_planets_planet_id_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_planets_planet_id_holder)
                {
                    ret = get_characters_character_id_planets_planet_id_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_planets_planet_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_planets_planet_id_holder.put(param, ret);
                        addFetchCacheObject("get_characters_character_id_planets_planet_id", headerHandler -> (swagger).get_characters_planets(character_id, planet_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_search> get(String[] categories, int character_id, String search, Boolean strict) {
            K_19_String_LString_int_Boolean param = new K_19_String_LString_int_Boolean(search, categories, character_id, strict);
            Property<R_get_characters_character_id_search> ret = get_characters_character_id_search_holder.get(param);
            if (ret == null) {
                synchronized (get_characters_character_id_search_holder)
                {
                    ret = get_characters_character_id_search_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_search> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_search_holder.put(param, ret);
                        addFetchCacheObject("get_characters_character_id_search", headerHandler -> (swagger).get_characters(categories, character_id, search, strict, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Public information about a character
         * 
         * cache over {@link Swagger#get_characters}<br />
         * 
         * @param character_id
         *     An EVE character ID
         */
        public Property<R_get_characters_character_id> get(int character_id) {
            Property<R_get_characters_character_id> ret = get_characters_character_id_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_holder)
                {
                    ret = get_characters_character_id_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id", headerHandler -> (swagger).get_characters(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public Property<R_get_characters_character_id_skills> skills(int character_id) {
            Property<R_get_characters_character_id_skills> ret = get_characters_character_id_skills_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_skills_holder)
                {
                    ret = get_characters_character_id_skills_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_skills> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_characters_character_id_skills_holder.put(character_id, ret);
                        addFetchCacheObject("get_characters_character_id_skills", headerHandler -> (swagger).get_characters_skills(character_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, M_get_journal_13> wallet_journal(int character_id) {
            ObservableMap<Long, M_get_journal_13> ret = get_characters_character_id_wallet_journal_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_wallet_journal_holder)
                {
                    ret = get_characters_character_id_wallet_journal_holder.get(character_id);
                    if (ret == null) {
                        ObservableMap<Long, M_get_journal_13> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_characters_character_id_wallet_journal_holder.put(character_id, ret);
                        addFetchCacheArray("get_characters_character_id_wallet_journal", (page, headerHandler) -> (swagger).get_characters_wallet_journal(character_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_journal_13> newmap = new LinkedHashMap<>();
                                for (M_get_journal_13 val: arr) {
                                    newmap.put((val.id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Corporation {
        private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_extractions>> get_corporation_corporation_id_mining_extractions_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_observers>> get_corporation_corporation_id_mining_observers_holder = new HashMap<>();
        private final Map<K_7_int_long, ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id>> get_corporation_corporation_id_mining_observers_observer_id_holder = new HashMap<>();

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
                        addFetchCacheArray("get_corporation_corporation_id_mining_extractions", (page, headerHandler) -> (swagger).get_corporation_mining_extractions(corporation_id, page, headerHandler), arr -> {
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
                        addFetchCacheArray("get_corporation_corporation_id_mining_observers", (page, headerHandler) -> (swagger).get_corporation_mining_observers(corporation_id, page, headerHandler), arr -> {
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
                        addFetchCacheArray("get_corporation_corporation_id_mining_observers_observer_id", (page, headerHandler) -> (swagger).get_corporation_mining_observers(corporation_id, observer_id, page, headerHandler), arr -> {
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

    public class Corporations {
        private ObservableList<Integer> get_corporations_npccorps_holder;
        private final Map<Integer, ObservableList<M_get_bookmarks_9>> get_corporations_corporation_id_bookmarks_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_bookmarks_folders>> get_corporations_corporation_id_bookmarks_folders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contacts_labels_2>> get_corporations_corporation_id_contacts_labels_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contracts_22>> get_corporations_corporation_id_contracts_holder = new HashMap<>();
        private final Map<K_8_int_int, ObservableMap<Integer, M_get_contracts_contract_bids_4>> get_corporations_corporation_id_contracts_contract_id_bids_holder = new HashMap<>();
        private final Map<K_8_int_int, ObservableMap<Long, M_get_contracts_contract_items_6>> get_corporations_corporation_id_contracts_contract_id_items_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_corporations_corporation_id_customs_offices>> get_corporations_corporation_id_customs_offices_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_facilities>> get_corporations_corporation_id_facilities_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_holder = new HashMap<>();
        private final Map<K_9_int_Boolean, ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs>> get_corporations_corporation_id_industry_jobs_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_killmails_2>> get_corporations_corporation_id_killmails_recent_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_medals>> get_corporations_corporation_id_medals_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_medals_issued>> get_corporations_corporation_id_medals_issued_holder = new HashMap<>();
        private final Map<Integer, Property<Integer>> get_corporations_corporation_id_members_limit_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_members_titles>> get_corporations_corporation_id_members_titles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_membertracking>> get_corporations_corporation_id_membertracking_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_roles>> get_corporations_corporation_id_roles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_roles_history>> get_corporations_corporation_id_roles_history_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_shareholders>> get_corporations_corporation_id_shareholders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_standings_3>> get_corporations_corporation_id_standings_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_corporations_corporation_id_starbases>> get_corporations_corporation_id_starbases_holder = new HashMap<>();
        private final Map<K_10_int_long_int, Property<R_get_corporations_corporation_id_starbases_starbase_id>> get_corporations_corporation_id_starbases_starbase_id_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_titles>> get_corporations_corporation_id_titles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_wallets>> get_corporations_corporation_id_wallets_holder = new HashMap<>();
        private final Map<K_11_int_int_Long, ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions>> get_corporations_corporation_id_wallets_division_transactions_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_alliancehistory>> get_corporations_corporation_id_alliancehistory_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, M_get_blueprints_8>> get_corporations_corporation_id_blueprints_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_contacts>> get_corporations_corporation_id_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_containers_logs>> get_corporations_corporation_id_containers_logs_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_corporations_corporation_id_orders_history>> get_corporations_corporation_id_orders_history_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_structures>> get_corporations_corporation_id_structures_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_assets_8>> get_corporations_corporation_id_assets_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_corporations_corporation_id_members_holder = new HashMap<>();
        private final Map<Integer, ObservableMap<Long, R_get_corporations_corporation_id_orders>> get_corporations_corporation_id_orders_holder = new HashMap<>();
        private final Map<K_20_int_int, ObservableMap<Long, M_get_journal_13>> get_corporations_corporation_id_wallets_division_journal_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id>> get_corporations_corporation_id_holder = new HashMap<>();

        /**
         * Get a list of npc corporations
         * 
         * cache over {@link Swagger#get_corporations_npccorps}<br />
         */
        public ObservableList<Integer> npccorps() {
            if (get_corporations_npccorps_holder == null) {
                synchronized (this)
                {
                    if (get_corporations_npccorps_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_corporations_npccorps_holder = finalContainer;
                        addFetchCacheArray("get_corporations_npccorps", (page, headerHandler) -> IntStream.of((swagger).get_corporations_npccorps(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_corporations_npccorps_holder;
        }

        /**
         * A list of your corporation's bookmarks
         * 
         * cache over {@link Swagger#get_corporations_bookmarks}<br />
         * 
         * @param corporation_id
         *     An EVE corporation ID
         */
        public ObservableList<M_get_bookmarks_9> bookmarks(int corporation_id) {
            ObservableList<M_get_bookmarks_9> ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_bookmarks_holder)
                {
                    ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_bookmarks_9> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_bookmarks_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_bookmarks", (page, headerHandler) -> (swagger).get_corporations_bookmarks(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * A list of your corporation's bookmark folders
         * 
         * cache over {@link Swagger#get_corporations_bookmarks_folders}<br />
         * 
         * @param corporation_id
         *     An EVE corporation ID
         */
        public ObservableList<R_get_corporations_corporation_id_bookmarks_folders> bookmarks_folders(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_bookmarks_folders> ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_bookmarks_folders_holder)
                {
                    ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_bookmarks_folders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_bookmarks_folders_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_bookmarks_folders", (page, headerHandler) -> (swagger).get_corporations_bookmarks_folders(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_contacts_labels_2> contacts_labels(int corporation_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contacts_labels_holder)
                {
                    ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_contacts_labels_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_contacts_labels", (page, headerHandler) -> (swagger).get_corporations_contacts_labels(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<M_get_contracts_22> contracts(int corporation_id) {
            ObservableList<M_get_contracts_22> ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contracts_holder)
                {
                    ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_contracts_22> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_contracts_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_contracts", (page, headerHandler) -> (swagger).get_corporations_contracts(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Integer, M_get_contracts_contract_bids_4> contracts_bids(int contract_id, int corporation_id) {
            K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
            ObservableMap<Integer, M_get_contracts_contract_bids_4> ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contracts_contract_id_bids_holder)
                {
                    ret = get_corporations_corporation_id_contracts_contract_id_bids_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Integer, M_get_contracts_contract_bids_4> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_contracts_contract_id_bids_holder.put(param, ret);
                        addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_bids", (page, headerHandler) -> (swagger).get_corporations_contracts_bids(contract_id, corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Integer, M_get_contracts_contract_bids_4> newmap = new LinkedHashMap<>();
                                for (M_get_contracts_contract_bids_4 val: arr) {
                                    newmap.put((val.bid_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, M_get_contracts_contract_items_6> contracts_items(int contract_id, int corporation_id) {
            K_8_int_int param = new K_8_int_int(corporation_id, contract_id);
            ObservableMap<Long, M_get_contracts_contract_items_6> ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contracts_contract_id_items_holder)
                {
                    ret = get_corporations_corporation_id_contracts_contract_id_items_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Long, M_get_contracts_contract_items_6> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_contracts_contract_id_items_holder.put(param, ret);
                        addFetchCacheArray("get_corporations_corporation_id_contracts_contract_id_items", (page, headerHandler) -> (swagger).get_corporations_contracts_items(contract_id, corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_contracts_contract_items_6> newmap = new LinkedHashMap<>();
                                for (M_get_contracts_contract_items_6 val: arr) {
                                    newmap.put((val.record_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_corporations_corporation_id_customs_offices> customs_offices(int corporation_id) {
            ObservableMap<Long, R_get_corporations_corporation_id_customs_offices> ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_customs_offices_holder)
                {
                    ret = get_corporations_corporation_id_customs_offices_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_corporations_corporation_id_customs_offices> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_customs_offices_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_customs_offices", (page, headerHandler) -> (swagger).get_corporations_customs_offices(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_corporations_corporation_id_customs_offices> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_customs_offices val: arr) {
                                    newmap.put((val.office_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public Property<R_get_corporations_corporation_id_divisions> divisions(int corporation_id) {
            Property<R_get_corporations_corporation_id_divisions> ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_divisions_holder)
                {
                    ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_divisions> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_divisions_holder.put(corporation_id, ret);
                        addFetchCacheObject("get_corporations_corporation_id_divisions", headerHandler -> (swagger).get_corporations_divisions(corporation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_facilities> facilities(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_facilities> ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_facilities_holder)
                {
                    ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_facilities> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_facilities_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_facilities", (page, headerHandler) -> (swagger).get_corporations_facilities(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Factory_Manager"});
                    }
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
        public Property<R_get_corporations_corporation_id_fw_stats> fw_stats(int corporation_id) {
            Property<R_get_corporations_corporation_id_fw_stats> ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_fw_stats_holder)
                {
                    ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_fw_stats_holder.put(corporation_id, ret);
                        addFetchCacheObject("get_corporations_corporation_id_fw_stats", headerHandler -> (swagger).get_corporations_fw_stats(corporation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get the icon urls for a corporation
         * 
         * cache over {@link Swagger#get_corporations_icons}<br />
         * 
         * @param corporation_id
         *     An EVE corporation ID
         */
        public Property<R_get_corporations_corporation_id_icons> icons(int corporation_id) {
            Property<R_get_corporations_corporation_id_icons> ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_icons_holder)
                {
                    ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_icons> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_icons_holder.put(corporation_id, ret);
                        addFetchCacheObject("get_corporations_corporation_id_icons", headerHandler -> (swagger).get_corporations_icons(corporation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
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
         *     Whether retrieve completed industry jobs as well
         */
        public ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> industry_jobs(int corporation_id, Boolean include_completed) {
            K_9_int_Boolean param = new K_9_int_Boolean(corporation_id, include_completed);
            ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_industry_jobs_holder)
                {
                    ret = get_corporations_corporation_id_industry_jobs_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_industry_jobs_holder.put(param, ret);
                        addFetchCacheArray("get_corporations_corporation_id_industry_jobs", (page, headerHandler) -> (swagger).get_corporations_industry_jobs(corporation_id, include_completed, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Integer, R_get_corporations_corporation_id_industry_jobs> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_industry_jobs val: arr) {
                                    newmap.put((val.job_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"FactoryManager"});
                    }
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
        public ObservableList<M_get_killmails_2> killmails_recent(int corporation_id) {
            ObservableList<M_get_killmails_2> ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_killmails_recent_holder)
                {
                    ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_killmails_recent_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_killmails_recent", (page, headerHandler) -> (swagger).get_corporations_killmails_recent(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_medals> medals(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_medals> ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_medals_holder)
                {
                    ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_medals> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_medals_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_medals", (page, headerHandler) -> (swagger).get_corporations_medals(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_corporations_corporation_id_medals_issued> medals_issued(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_medals_issued> ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_medals_issued_holder)
                {
                    ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_medals_issued> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_medals_issued_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_medals_issued", (page, headerHandler) -> (swagger).get_corporations_medals_issued(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public Property<Integer> members_limit(int corporation_id) {
            Property<Integer> ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_limit_holder)
                {
                    ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<Integer> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_members_limit_holder.put(corporation_id, ret);
                        addFetchCacheObject("get_corporations_corporation_id_members_limit", headerHandler -> (swagger).get_corporations_members_limit(corporation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_members_titles> members_titles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_members_titles> ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_titles_holder)
                {
                    ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_members_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_members_titles_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_members_titles", (page, headerHandler) -> (swagger).get_corporations_members_titles(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_membertracking> membertracking(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_membertracking> ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_membertracking_holder)
                {
                    ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_membertracking> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_membertracking_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_membertracking", (page, headerHandler) -> (swagger).get_corporations_membertracking(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_roles> roles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_roles> ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_roles_holder)
                {
                    ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_roles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_roles_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_roles", (page, headerHandler) -> (swagger).get_corporations_roles(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_corporations_corporation_id_roles_history> roles_history(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_roles_history> ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_roles_history_holder)
                {
                    ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_roles_history> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_roles_history_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_roles_history", (page, headerHandler) -> (swagger).get_corporations_roles_history(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_shareholders> shareholders(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_shareholders> ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_shareholders_holder)
                {
                    ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_shareholders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_shareholders_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_shareholders", (page, headerHandler) -> (swagger).get_corporations_shareholders(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<M_get_standings_3> standings(int corporation_id) {
            ObservableList<M_get_standings_3> ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_standings_holder)
                {
                    ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_standings_3> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_standings_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_standings", (page, headerHandler) -> (swagger).get_corporations_standings(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_corporations_corporation_id_starbases> starbases(int corporation_id) {
            ObservableMap<Long, R_get_corporations_corporation_id_starbases> ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_starbases_holder)
                {
                    ret = get_corporations_corporation_id_starbases_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_corporations_corporation_id_starbases> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_starbases_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_starbases", (page, headerHandler) -> (swagger).get_corporations_starbases(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_corporations_corporation_id_starbases> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_starbases val: arr) {
                                    newmap.put((val.starbase_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public Property<R_get_corporations_corporation_id_starbases_starbase_id> starbases(int corporation_id, long starbase_id, int system_id) {
            K_10_int_long_int param = new K_10_int_long_int(corporation_id, starbase_id, system_id);
            Property<R_get_corporations_corporation_id_starbases_starbase_id> ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_starbases_starbase_id_holder)
                {
                    ret = get_corporations_corporation_id_starbases_starbase_id_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_starbases_starbase_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_starbases_starbase_id_holder.put(param, ret);
                        addFetchCacheObject("get_corporations_corporation_id_starbases_starbase_id", headerHandler -> (swagger).get_corporations_starbases(corporation_id, starbase_id, system_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_titles> titles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_titles> ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_titles_holder)
                {
                    ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_titles_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_titles", (page, headerHandler) -> (swagger).get_corporations_titles(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_wallets> wallets(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_wallets> ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_wallets_holder)
                {
                    ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_wallets> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_wallets_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_wallets", (page, headerHandler) -> (swagger).get_corporations_wallets(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Accountant", "Junior_Accountant"});
                    }
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
        public ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> wallets_transactions(int corporation_id, int division, Long from_id) {
            K_11_int_int_Long param = new K_11_int_int_Long(division, corporation_id, from_id);
            ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_wallets_division_transactions_holder)
                {
                    ret = get_corporations_corporation_id_wallets_division_transactions_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_wallets_division_transactions_holder.put(param, ret);
                        addFetchCacheArray("get_corporations_corporation_id_wallets_division_transactions", (page, headerHandler) -> (swagger).get_corporations_wallets_transactions(corporation_id, division, from_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_wallets_division_transactions val: arr) {
                                    newmap.put((val.transaction_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Accountant", "Junior_Accountant"});
                    }
                }
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
        public ObservableList<R_get_corporations_corporation_id_alliancehistory> alliancehistory(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_alliancehistory> ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_alliancehistory_holder)
                {
                    ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_alliancehistory> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_alliancehistory_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_alliancehistory", (page, headerHandler) -> (swagger).get_corporations_alliancehistory(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, M_get_blueprints_8> blueprints(int corporation_id) {
            ObservableMap<Long, M_get_blueprints_8> ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_blueprints_holder)
                {
                    ret = get_corporations_corporation_id_blueprints_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableMap<Long, M_get_blueprints_8> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_blueprints_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_blueprints", (page, headerHandler) -> (swagger).get_corporations_blueprints(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_blueprints_8> newmap = new LinkedHashMap<>();
                                for (M_get_blueprints_8 val: arr) {
                                    newmap.put((val.item_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_contacts> contacts(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_contacts> ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contacts_holder)
                {
                    ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_contacts_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_contacts", (page, headerHandler) -> (swagger).get_corporations_contacts(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableList<R_get_corporations_corporation_id_containers_logs> containers_logs(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_containers_logs> ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_containers_logs_holder)
                {
                    ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_containers_logs> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_containers_logs_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_containers_logs", (page, headerHandler) -> (swagger).get_corporations_containers_logs(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableMap<Long, R_get_corporations_corporation_id_orders_history> orders_history(int corporation_id) {
            ObservableMap<Long, R_get_corporations_corporation_id_orders_history> ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_orders_history_holder)
                {
                    ret = get_corporations_corporation_id_orders_history_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_corporations_corporation_id_orders_history> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_orders_history_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_orders_history", (page, headerHandler) -> (swagger).get_corporations_orders_history(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_corporations_corporation_id_orders_history> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_orders_history val: arr) {
                                    newmap.put((val.order_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Accountant", "Trader"});
                    }
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
        public ObservableList<R_get_corporations_corporation_id_structures> structures(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_structures> ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_structures_holder)
                {
                    ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_structures> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_structures_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_structures", (page, headerHandler) -> (swagger).get_corporations_structures(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"StationManager"});
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of the corporation assets
         * 
         * cache over {@link Swagger#get_corporations_assets}<br />
         * 
         * @param corporation_id
         *     An EVE corporation ID
         */
        public ObservableList<M_get_assets_8> assets(int corporation_id) {
            ObservableList<M_get_assets_8> ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_assets_holder)
                {
                    ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_assets_8> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_assets_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_assets", (page, headerHandler) -> (swagger).get_corporations_assets(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        , new String[] {"Director"});
                    }
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
        public ObservableList<Integer> members(int corporation_id) {
            ObservableList<Integer> ret = get_corporations_corporation_id_members_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_holder)
                {
                    ret = get_corporations_corporation_id_members_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_corporations_corporation_id_members_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_members", (page, headerHandler) -> IntStream.of((swagger).get_corporations_members(corporation_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
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
        public ObservableMap<Long, R_get_corporations_corporation_id_orders> orders(int corporation_id) {
            ObservableMap<Long, R_get_corporations_corporation_id_orders> ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_orders_holder)
                {
                    ret = get_corporations_corporation_id_orders_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableMap<Long, R_get_corporations_corporation_id_orders> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_orders_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_corporations_corporation_id_orders", (page, headerHandler) -> (swagger).get_corporations_orders(corporation_id, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, R_get_corporations_corporation_id_orders> newmap = new LinkedHashMap<>();
                                for (R_get_corporations_corporation_id_orders val: arr) {
                                    newmap.put((val.order_id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Accountant", "Trader"});
                    }
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
        public ObservableMap<Long, M_get_journal_13> wallets_journal(int corporation_id, int division) {
            K_20_int_int param = new K_20_int_int(division, corporation_id);
            ObservableMap<Long, M_get_journal_13> ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_wallets_division_journal_holder)
                {
                    ret = get_corporations_corporation_id_wallets_division_journal_holder.get(param);
                    if (ret == null) {
                        ObservableMap<Long, M_get_journal_13> finalret = FXCollections.observableHashMap();
                        ret = finalret;
                        get_corporations_corporation_id_wallets_division_journal_holder.put(param, ret);
                        addFetchCacheArray("get_corporations_corporation_id_wallets_division_journal", (page, headerHandler) -> (swagger).get_corporations_wallets_journal(corporation_id, division, page, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                LinkedHashMap<Long, M_get_journal_13> newmap = new LinkedHashMap<>();
                                for (M_get_journal_13 val: arr) {
                                    newmap.put((val.id), (val));
                                }
                                finalret.entrySet();
                                finalret.putAll(newmap);
                            }
                        }
                        , new String[] {"Accountant", "Junior_Accountant"});
                    }
                }
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
        public Property<R_get_corporations_corporation_id> get(int corporation_id) {
            Property<R_get_corporations_corporation_id> ret = get_corporations_corporation_id_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_holder)
                {
                    ret = get_corporations_corporation_id_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_corporations_corporation_id_holder.put(corporation_id, ret);
                        addFetchCacheObject("get_corporations_corporation_id", headerHandler -> (swagger).get_corporations(corporation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Dogma {
        private ObservableList<Integer> get_dogma_attributes_holder;
        private final Map<Integer, Property<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_holder = new HashMap<>();
        private final Map<K_12_long_int, Property<R_get_dogma_dynamic_items_type_id_item_id>> get_dogma_dynamic_items_type_id_item_id_holder = new HashMap<>();
        private ObservableList<Integer> get_dogma_effects_holder;
        private final Map<Integer, Property<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_holder = new HashMap<>();

        /**
         * Get a list of dogma attribute ids
         * 
         * cache over {@link Swagger#get_dogma_attributes}<br />
         */
        public ObservableList<Integer> attributes() {
            if (get_dogma_attributes_holder == null) {
                synchronized (this)
                {
                    if (get_dogma_attributes_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_dogma_attributes_holder = finalContainer;
                        addFetchCacheArray("get_dogma_attributes", (page, headerHandler) -> IntStream.of((swagger).get_dogma_attributes(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_dogma_attributes_holder;
        }

        /**
         * Get information on a dogma attribute
         * 
         * cache over {@link Swagger#get_dogma_attributes}<br />
         * 
         * @param attribute_id
         *     A dogma attribute ID
         */
        public Property<R_get_dogma_attributes_attribute_id> attributes(int attribute_id) {
            Property<R_get_dogma_attributes_attribute_id> ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
            if (ret == null) {
                synchronized (get_dogma_attributes_attribute_id_holder)
                {
                    ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_dogma_attributes_attribute_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_dogma_attributes_attribute_id_holder.put(attribute_id, ret);
                        addFetchCacheObject("get_dogma_attributes_attribute_id", headerHandler -> (swagger).get_dogma_attributes(attribute_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Returns info about a dynamic item resulting from mutation with a mutaplasmid.
         * 
         * cache over {@link Swagger#get_dogma_dynamic_items}<br />
         * 
         * @param item_id
         *     item_id integer
         * @param type_id
         *     type_id integer
         */
        public Property<R_get_dogma_dynamic_items_type_id_item_id> dynamic_items(long item_id, int type_id) {
            K_12_long_int param = new K_12_long_int(item_id, type_id);
            Property<R_get_dogma_dynamic_items_type_id_item_id> ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
            if (ret == null) {
                synchronized (get_dogma_dynamic_items_type_id_item_id_holder)
                {
                    ret = get_dogma_dynamic_items_type_id_item_id_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_dogma_dynamic_items_type_id_item_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_dogma_dynamic_items_type_id_item_id_holder.put(param, ret);
                        addFetchCacheObject("get_dogma_dynamic_items_type_id_item_id", headerHandler -> (swagger).get_dogma_dynamic_items(item_id, type_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of dogma effect ids
         * 
         * cache over {@link Swagger#get_dogma_effects}<br />
         */
        public ObservableList<Integer> effects() {
            if (get_dogma_effects_holder == null) {
                synchronized (this)
                {
                    if (get_dogma_effects_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_dogma_effects_holder = finalContainer;
                        addFetchCacheArray("get_dogma_effects", (page, headerHandler) -> IntStream.of((swagger).get_dogma_effects(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_dogma_effects_holder;
        }

        /**
         * Get information on a dogma effect
         * 
         * cache over {@link Swagger#get_dogma_effects}<br />
         * 
         * @param effect_id
         *     A dogma effect ID
         */
        public Property<R_get_dogma_effects_effect_id> effects(int effect_id) {
            Property<R_get_dogma_effects_effect_id> ret = get_dogma_effects_effect_id_holder.get(effect_id);
            if (ret == null) {
                synchronized (get_dogma_effects_effect_id_holder)
                {
                    ret = get_dogma_effects_effect_id_holder.get(effect_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_dogma_effects_effect_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_dogma_effects_effect_id_holder.put(effect_id, ret);
                        addFetchCacheObject("get_dogma_effects_effect_id", headerHandler -> (swagger).get_dogma_effects(effect_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Fleets {
        private final Map<Long, Property<R_get_fleets_fleet_id>> get_fleets_fleet_id_holder = new HashMap<>();
        private final Map<Long, ObservableList<R_get_fleets_fleet_id_members>> get_fleets_fleet_id_members_holder = new HashMap<>();
        private final Map<Long, ObservableList<R_get_fleets_fleet_id_wings>> get_fleets_fleet_id_wings_holder = new HashMap<>();

        /**
         * Return details about a fleet
         * 
         * cache over {@link Swagger#get_fleets}<br />
         * 
         * @param fleet_id
         *     ID for a fleet
         */
        public Property<R_get_fleets_fleet_id> get(long fleet_id) {
            Property<R_get_fleets_fleet_id> ret = get_fleets_fleet_id_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_holder)
                {
                    ret = get_fleets_fleet_id_holder.get(fleet_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_fleets_fleet_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_fleets_fleet_id_holder.put(fleet_id, ret);
                        addFetchCacheObject("get_fleets_fleet_id", headerHandler -> (swagger).get_fleets(fleet_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return information about fleet members
         * 
         * cache over {@link Swagger#get_fleets_members}<br />
         * 
         * @param fleet_id
         *     ID for a fleet
         */
        public ObservableList<R_get_fleets_fleet_id_members> members(long fleet_id) {
            ObservableList<R_get_fleets_fleet_id_members> ret = get_fleets_fleet_id_members_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_members_holder)
                {
                    ret = get_fleets_fleet_id_members_holder.get(fleet_id);
                    if (ret == null) {
                        ObservableList<R_get_fleets_fleet_id_members> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_fleets_fleet_id_members_holder.put(fleet_id, ret);
                        addFetchCacheArray("get_fleets_fleet_id_members", (page, headerHandler) -> (swagger).get_fleets_members(fleet_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return information about wings in a fleet
         * 
         * cache over {@link Swagger#get_fleets_wings}<br />
         * 
         * @param fleet_id
         *     ID for a fleet
         */
        public ObservableList<R_get_fleets_fleet_id_wings> wings(long fleet_id) {
            ObservableList<R_get_fleets_fleet_id_wings> ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_wings_holder)
                {
                    ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
                    if (ret == null) {
                        ObservableList<R_get_fleets_fleet_id_wings> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_fleets_fleet_id_wings_holder.put(fleet_id, ret);
                        addFetchCacheArray("get_fleets_fleet_id_wings", (page, headerHandler) -> (swagger).get_fleets_wings(fleet_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Fw {
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_holder;
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_characters_holder;
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations_holder;
        private ObservableList<R_get_fw_stats> get_fw_stats_holder;
        private ObservableList<R_get_fw_wars> get_fw_wars_holder;
        private ObservableList<R_get_fw_systems> get_fw_systems_holder;

        /**
         * Top 4 leaderboard of factions for kills and victory points separated by total, last week and yesterday.
         * 
         * cache over {@link Swagger#get_fw_leaderboards}<br />
         */
        public Property<M_get_fw_leaderboards_2> leaderboards() {
            if (get_fw_leaderboards_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_holder == null) {
                        get_fw_leaderboards_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_holder;
                        addFetchCacheObject("get_fw_leaderboards", headerHandler -> (swagger).get_fw_leaderboards(headerHandler), item -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_leaderboards_holder;
        }

        /**
         * Top 100 leaderboard of pilots for kills and victory points separated by total, last week and yesterday.
         * 
         * cache over {@link Swagger#get_fw_leaderboards_characters}<br />
         */
        public Property<M_get_fw_leaderboards_2> leaderboards_characters() {
            if (get_fw_leaderboards_characters_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_characters_holder == null) {
                        get_fw_leaderboards_characters_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_characters_holder;
                        addFetchCacheObject("get_fw_leaderboards_characters", headerHandler -> (swagger).get_fw_leaderboards_characters(headerHandler), item -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_leaderboards_characters_holder;
        }

        /**
         * Top 10 leaderboard of corporations for kills and victory points separated by total, last week and yesterday.
         * 
         * cache over {@link Swagger#get_fw_leaderboards_corporations}<br />
         */
        public Property<M_get_fw_leaderboards_2> leaderboards_corporations() {
            if (get_fw_leaderboards_corporations_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_corporations_holder == null) {
                        get_fw_leaderboards_corporations_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_corporations_holder;
                        addFetchCacheObject("get_fw_leaderboards_corporations", headerHandler -> (swagger).get_fw_leaderboards_corporations(headerHandler), item -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_leaderboards_corporations_holder;
        }

        /**
         * Statistical overviews of factions involved in faction warfare
         * 
         * cache over {@link Swagger#get_fw_stats}<br />
         */
        public ObservableList<R_get_fw_stats> stats() {
            if (get_fw_stats_holder == null) {
                synchronized (this)
                {
                    if (get_fw_stats_holder == null) {
                        ObservableList<R_get_fw_stats> finalContainer = FXCollections.observableArrayList();
                        get_fw_stats_holder = finalContainer;
                        addFetchCacheArray("get_fw_stats", (page, headerHandler) -> (swagger).get_fw_stats(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_stats_holder;
        }

        /**
         * Data about which NPC factions are at war
         * 
         * cache over {@link Swagger#get_fw_wars}<br />
         */
        public ObservableList<R_get_fw_wars> wars() {
            if (get_fw_wars_holder == null) {
                synchronized (this)
                {
                    if (get_fw_wars_holder == null) {
                        ObservableList<R_get_fw_wars> finalContainer = FXCollections.observableArrayList();
                        get_fw_wars_holder = finalContainer;
                        addFetchCacheArray("get_fw_wars", (page, headerHandler) -> (swagger).get_fw_wars(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_wars_holder;
        }

        /**
         * An overview of the current ownership of faction warfare solar systems
         * 
         * cache over {@link Swagger#get_fw_systems}<br />
         */
        public ObservableList<R_get_fw_systems> systems() {
            if (get_fw_systems_holder == null) {
                synchronized (this)
                {
                    if (get_fw_systems_holder == null) {
                        ObservableList<R_get_fw_systems> finalContainer = FXCollections.observableArrayList();
                        get_fw_systems_holder = finalContainer;
                        addFetchCacheArray("get_fw_systems", (page, headerHandler) -> (swagger).get_fw_systems(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_fw_systems_holder;
        }
    }

    public class Incursions {
        private ObservableList<R_get_incursions> get_incursions_holder;

        /**
         * Return a list of current incursions
         * 
         * cache over {@link Swagger#get_incursions}<br />
         */
        public ObservableList<R_get_incursions> incursions() {
            if (get_incursions_holder == null) {
                synchronized (this)
                {
                    if (get_incursions_holder == null) {
                        ObservableList<R_get_incursions> finalContainer = FXCollections.observableArrayList();
                        get_incursions_holder = finalContainer;
                        addFetchCacheArray("get_incursions", (page, headerHandler) -> (swagger).get_incursions(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_incursions_holder;
        }
    }

    public class Industry {
        private ObservableList<R_get_industry_facilities> get_industry_facilities_holder;
        private ObservableList<R_get_industry_systems> get_industry_systems_holder;

        /**
         * Return a list of industry facilities
         * 
         * cache over {@link Swagger#get_industry_facilities}<br />
         */
        public ObservableList<R_get_industry_facilities> facilities() {
            if (get_industry_facilities_holder == null) {
                synchronized (this)
                {
                    if (get_industry_facilities_holder == null) {
                        ObservableList<R_get_industry_facilities> finalContainer = FXCollections.observableArrayList();
                        get_industry_facilities_holder = finalContainer;
                        addFetchCacheArray("get_industry_facilities", (page, headerHandler) -> (swagger).get_industry_facilities(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_industry_facilities_holder;
        }

        /**
         * Return cost indices for solar systems
         * 
         * cache over {@link Swagger#get_industry_systems}<br />
         */
        public ObservableList<R_get_industry_systems> systems() {
            if (get_industry_systems_holder == null) {
                synchronized (this)
                {
                    if (get_industry_systems_holder == null) {
                        ObservableList<R_get_industry_systems> finalContainer = FXCollections.observableArrayList();
                        get_industry_systems_holder = finalContainer;
                        addFetchCacheArray("get_industry_systems", (page, headerHandler) -> (swagger).get_industry_systems(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_industry_systems_holder;
        }
    }

    public class Insurance {
        private ObservableList<R_get_insurance_prices> get_insurance_prices_holder;

        /**
         * Return available insurance levels for all ship types
         * 
         * cache over {@link Swagger#get_insurance_prices}<br />
         */
        public ObservableList<R_get_insurance_prices> prices() {
            if (get_insurance_prices_holder == null) {
                synchronized (this)
                {
                    if (get_insurance_prices_holder == null) {
                        ObservableList<R_get_insurance_prices> finalContainer = FXCollections.observableArrayList();
                        get_insurance_prices_holder = finalContainer;
                        addFetchCacheArray("get_insurance_prices", (page, headerHandler) -> (swagger).get_insurance_prices(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_insurance_prices_holder;
        }
    }

    public class Killmails {
        private final Map<K_13_String_int, Property<R_get_killmails_killmail_id_killmail_hash>> get_killmails_killmail_id_killmail_hash_holder = new HashMap<>();

        /**
         * Return a single killmail from its ID and hash
         * 
         * cache over {@link Swagger#get_killmails}<br />
         * 
         * @param killmail_hash
         *     The killmail hash for verification
         * @param killmail_id
         *     The killmail ID to be queried
         */
        public Property<R_get_killmails_killmail_id_killmail_hash> get(String killmail_hash, int killmail_id) {
            K_13_String_int param = new K_13_String_int(killmail_hash, killmail_id);
            Property<R_get_killmails_killmail_id_killmail_hash> ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
            if (ret == null) {
                synchronized (get_killmails_killmail_id_killmail_hash_holder)
                {
                    ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_killmails_killmail_id_killmail_hash> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_killmails_killmail_id_killmail_hash_holder.put(param, ret);
                        addFetchCacheObject("get_killmails_killmail_id_killmail_hash", headerHandler -> (swagger).get_killmails(killmail_hash, killmail_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Loyalty {
        private final Map<Integer, ObservableList<R_get_loyalty_stores_corporation_id_offers>> get_loyalty_stores_corporation_id_offers_holder = new HashMap<>();

        /**
         * Return a list of offers from a specific corporation's loyalty store
         * 
         * cache over {@link Swagger#get_loyalty_stores_offers}<br />
         * 
         * @param corporation_id
         *     An EVE corporation ID
         */
        public ObservableList<R_get_loyalty_stores_corporation_id_offers> stores_offers(int corporation_id) {
            ObservableList<R_get_loyalty_stores_corporation_id_offers> ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_loyalty_stores_corporation_id_offers_holder)
                {
                    ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_loyalty_stores_corporation_id_offers> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_loyalty_stores_corporation_id_offers_holder.put(corporation_id, ret);
                        addFetchCacheArray("get_loyalty_stores_corporation_id_offers", (page, headerHandler) -> (swagger).get_loyalty_stores_offers(corporation_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Markets {
        private ObservableList<Integer> get_markets_groups_holder;
        private final Map<Integer, Property<R_get_markets_groups_market_group_id>> get_markets_groups_market_group_id_holder = new HashMap<>();
        private ObservableList<R_get_markets_prices> get_markets_prices_holder;
        private final Map<Long, ObservableList<R_get_markets_structures_structure_id>> get_markets_structures_structure_id_holder = new HashMap<>();
        private final Map<K_14_int_int, ObservableList<R_get_markets_region_id_history>> get_markets_region_id_history_holder = new HashMap<>();
        private final Map<K_15_Integer_int_order_type, ObservableList<R_get_markets_region_id_orders>> get_markets_region_id_orders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_markets_region_id_types_holder = new HashMap<>();

        /**
         * Get a list of item groups
         * 
         * cache over {@link Swagger#get_markets_groups}<br />
         */
        public ObservableList<Integer> groups() {
            if (get_markets_groups_holder == null) {
                synchronized (this)
                {
                    if (get_markets_groups_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_markets_groups_holder = finalContainer;
                        addFetchCacheArray("get_markets_groups", (page, headerHandler) -> IntStream.of((swagger).get_markets_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_markets_groups_holder;
        }

        /**
         * Get information on an item group
         * 
         * cache over {@link Swagger#get_markets_groups}<br />
         * 
         * @param market_group_id
         *     An Eve item group ID
         */
        public Property<R_get_markets_groups_market_group_id> groups(int market_group_id) {
            Property<R_get_markets_groups_market_group_id> ret = get_markets_groups_market_group_id_holder.get(market_group_id);
            if (ret == null) {
                synchronized (get_markets_groups_market_group_id_holder)
                {
                    ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_markets_groups_market_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_markets_groups_market_group_id_holder.put(market_group_id, ret);
                        addFetchCacheObject("get_markets_groups_market_group_id", headerHandler -> (swagger).get_markets_groups(market_group_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of prices
         * 
         * cache over {@link Swagger#get_markets_prices}<br />
         */
        public ObservableList<R_get_markets_prices> prices() {
            if (get_markets_prices_holder == null) {
                synchronized (this)
                {
                    if (get_markets_prices_holder == null) {
                        ObservableList<R_get_markets_prices> finalContainer = FXCollections.observableArrayList();
                        get_markets_prices_holder = finalContainer;
                        addFetchCacheArray("get_markets_prices", (page, headerHandler) -> (swagger).get_markets_prices(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_markets_prices_holder;
        }

        /**
         * Return all orders in a structure
         * 
         * cache over {@link Swagger#get_markets_structures}<br />
         * 
         * @param structure_id
         *     Return orders in this structure
         */
        public ObservableList<R_get_markets_structures_structure_id> structures(long structure_id) {
            ObservableList<R_get_markets_structures_structure_id> ret = get_markets_structures_structure_id_holder.get(structure_id);
            if (ret == null) {
                synchronized (get_markets_structures_structure_id_holder)
                {
                    ret = get_markets_structures_structure_id_holder.get(structure_id);
                    if (ret == null) {
                        ObservableList<R_get_markets_structures_structure_id> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_markets_structures_structure_id_holder.put(structure_id, ret);
                        addFetchCacheArray("get_markets_structures_structure_id", (page, headerHandler) -> (swagger).get_markets_structures(page, structure_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of historical market statistics for the specified type in a region
         * 
         * cache over {@link Swagger#get_markets_history}<br />
         * 
         * @param region_id
         *     Return statistics in this region
         * @param type_id
         *     Return statistics for this type
         */
        public ObservableList<R_get_markets_region_id_history> history(int region_id, int type_id) {
            K_14_int_int param = new K_14_int_int(type_id, region_id);
            ObservableList<R_get_markets_region_id_history> ret = get_markets_region_id_history_holder.get(param);
            if (ret == null) {
                synchronized (get_markets_region_id_history_holder)
                {
                    ret = get_markets_region_id_history_holder.get(param);
                    if (ret == null) {
                        ObservableList<R_get_markets_region_id_history> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_markets_region_id_history_holder.put(param, ret);
                        addFetchCacheArray("get_markets_region_id_history", (page, headerHandler) -> (swagger).get_markets_history(region_id, type_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of orders in a region
         * 
         * cache over {@link Swagger#get_markets_orders}<br />
         * 
         * @param order_type
         *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders.
         * @param region_id
         *     Return orders in this region
         * @param type_id
         *     Return orders only for this type
         */
        public ObservableList<R_get_markets_region_id_orders> orders(Swagger.order_type order_type, int region_id, Integer type_id) {
            K_15_Integer_int_order_type param = new K_15_Integer_int_order_type(type_id, region_id, order_type);
            ObservableList<R_get_markets_region_id_orders> ret = get_markets_region_id_orders_holder.get(param);
            if (ret == null) {
                synchronized (get_markets_region_id_orders_holder)
                {
                    ret = get_markets_region_id_orders_holder.get(param);
                    if (ret == null) {
                        ObservableList<R_get_markets_region_id_orders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_markets_region_id_orders_holder.put(param, ret);
                        addFetchCacheArray("get_markets_region_id_orders", (page, headerHandler) -> (swagger).get_markets_orders(order_type, page, region_id, type_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of type IDs that have active orders in the region, for efficient market indexing.
         * 
         * cache over {@link Swagger#get_markets_types}<br />
         * 
         * @param region_id
         *     Return statistics in this region
         */
        public ObservableList<Integer> types(int region_id) {
            ObservableList<Integer> ret = get_markets_region_id_types_holder.get(region_id);
            if (ret == null) {
                synchronized (get_markets_region_id_types_holder)
                {
                    ret = get_markets_region_id_types_holder.get(region_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_markets_region_id_types_holder.put(region_id, ret);
                        addFetchCacheArray("get_markets_region_id_types", (page, headerHandler) -> IntStream.of((swagger).get_markets_types(page, region_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Opportunities {
        private ObservableList<Integer> get_opportunities_groups_holder;
        private final Map<Integer, Property<R_get_opportunities_groups_group_id>> get_opportunities_groups_group_id_holder = new HashMap<>();
        private ObservableList<Integer> get_opportunities_tasks_holder;
        private final Map<Integer, Property<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_holder = new HashMap<>();

        /**
         * Return a list of opportunities groups
         * 
         * cache over {@link Swagger#get_opportunities_groups}<br />
         */
        public ObservableList<Integer> groups() {
            if (get_opportunities_groups_holder == null) {
                synchronized (this)
                {
                    if (get_opportunities_groups_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_opportunities_groups_holder = finalContainer;
                        addFetchCacheArray("get_opportunities_groups", (page, headerHandler) -> IntStream.of((swagger).get_opportunities_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_opportunities_groups_holder;
        }

        /**
         * Return information of an opportunities group
         * 
         * cache over {@link Swagger#get_opportunities_groups}<br />
         * 
         * @param group_id
         *     ID of an opportunities group
         */
        public Property<R_get_opportunities_groups_group_id> groups(int group_id) {
            Property<R_get_opportunities_groups_group_id> ret = get_opportunities_groups_group_id_holder.get(group_id);
            if (ret == null) {
                synchronized (get_opportunities_groups_group_id_holder)
                {
                    ret = get_opportunities_groups_group_id_holder.get(group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_opportunities_groups_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_opportunities_groups_group_id_holder.put(group_id, ret);
                        addFetchCacheObject("get_opportunities_groups_group_id", headerHandler -> (swagger).get_opportunities_groups(group_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Return a list of opportunities tasks
         * 
         * cache over {@link Swagger#get_opportunities_tasks}<br />
         */
        public ObservableList<Integer> tasks() {
            if (get_opportunities_tasks_holder == null) {
                synchronized (this)
                {
                    if (get_opportunities_tasks_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_opportunities_tasks_holder = finalContainer;
                        addFetchCacheArray("get_opportunities_tasks", (page, headerHandler) -> IntStream.of((swagger).get_opportunities_tasks(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_opportunities_tasks_holder;
        }

        /**
         * Return information of an opportunities task
         * 
         * cache over {@link Swagger#get_opportunities_tasks}<br />
         * 
         * @param task_id
         *     ID of an opportunities task
         */
        public Property<R_get_opportunities_tasks_task_id> tasks(int task_id) {
            Property<R_get_opportunities_tasks_task_id> ret = get_opportunities_tasks_task_id_holder.get(task_id);
            if (ret == null) {
                synchronized (get_opportunities_tasks_task_id_holder)
                {
                    ret = get_opportunities_tasks_task_id_holder.get(task_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_opportunities_tasks_task_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_opportunities_tasks_task_id_holder.put(task_id, ret);
                        addFetchCacheObject("get_opportunities_tasks_task_id", headerHandler -> (swagger).get_opportunities_tasks(task_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public interface Pausable {

        public void pause();

        public void resume();
    }

    public class Route {
        private final Map<K_16_flag_int_int_Lint_LLint, ObservableList<Integer>> get_route_origin_destination_holder = new HashMap<>();

        /**
         * Get the systems between origin and destination
         * 
         * cache over {@link Swagger#get_route}<br />
         * 
         * @param avoid
         *     avoid solar system ID(s)
         * @param connections
         *     connected solar system pairs
         * @param destination
         *     destination solar system ID
         * @param flag
         *     route security preference
         * @param origin
         *     origin solar system ID
         */
        public ObservableList<Integer> get(int[] avoid, int[][] connections, int destination, Swagger.flag flag, int origin) {
            K_16_flag_int_int_Lint_LLint param = new K_16_flag_int_int_Lint_LLint(flag, origin, destination, avoid, connections);
            ObservableList<Integer> ret = get_route_origin_destination_holder.get(param);
            if (ret == null) {
                synchronized (get_route_origin_destination_holder)
                {
                    ret = get_route_origin_destination_holder.get(param);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_route_origin_destination_holder.put(param, ret);
                        addFetchCacheArray("get_route_origin_destination", (page, headerHandler) -> IntStream.of((swagger).get_route(avoid, connections, destination, flag, origin, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Search {
        private final Map<K_17_String_LString_Boolean, Property<R_get_search>> get_search_holder = new HashMap<>();

        /**
         * Search for entities that match a given sub-string.
         * 
         * cache over {@link Swagger#get}<br />
         * 
         * @param categories
         *     Type of entities to search for
         * @param search
         *     The string to search on
         * @param strict
         *     Whether the search should be a strict match
         */
        public Property<R_get_search> get(String[] categories, String search, Boolean strict) {
            K_17_String_LString_Boolean param = new K_17_String_LString_Boolean(search, categories, strict);
            Property<R_get_search> ret = get_search_holder.get(param);
            if (ret == null) {
                synchronized (get_search_holder)
                {
                    ret = get_search_holder.get(param);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_search> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_search_holder.put(param, ret);
                        addFetchCacheObject("get_search", headerHandler -> (swagger).get(categories, search, strict, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Sovereignty {
        private ObservableMap<Integer, R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
        private ObservableList<R_get_sovereignty_map> get_sovereignty_map_holder;
        private ObservableMap<Long, R_get_sovereignty_structures> get_sovereignty_structures_holder;

        /**
         * Shows sovereignty data for campaigns.
         * 
         * cache over {@link Swagger#get_sovereignty_campaigns}<br />
         */
        public ObservableMap<Integer, R_get_sovereignty_campaigns> campaigns() {
            if (get_sovereignty_campaigns_holder == null) {
                synchronized (this)
                {
                    if (get_sovereignty_campaigns_holder == null) {
                        get_sovereignty_campaigns_holder = FXCollections.observableHashMap();
                        ObservableMap<Integer, R_get_sovereignty_campaigns> finalContainer = get_sovereignty_campaigns_holder;
                        addFetchCacheArray("get_sovereignty_campaigns", (page, headerHandler) -> (swagger).get_sovereignty_campaigns(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                LinkedHashMap<Integer, R_get_sovereignty_campaigns> newmap = new LinkedHashMap<>();
                                for (R_get_sovereignty_campaigns val: arr) {
                                    newmap.put((val.campaign_id), (val));
                                }
                                finalContainer.entrySet();
                                finalContainer.putAll(newmap);
                            }
                        }
                        );
                    }
                }
            }
            return get_sovereignty_campaigns_holder;
        }

        /**
         * Shows sovereignty information for solar systems
         * 
         * cache over {@link Swagger#get_sovereignty_map}<br />
         */
        public ObservableList<R_get_sovereignty_map> map() {
            if (get_sovereignty_map_holder == null) {
                synchronized (this)
                {
                    if (get_sovereignty_map_holder == null) {
                        ObservableList<R_get_sovereignty_map> finalContainer = FXCollections.observableArrayList();
                        get_sovereignty_map_holder = finalContainer;
                        addFetchCacheArray("get_sovereignty_map", (page, headerHandler) -> (swagger).get_sovereignty_map(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_sovereignty_map_holder;
        }

        /**
         * Shows sovereignty data for structures.
         * 
         * cache over {@link Swagger#get_sovereignty_structures}<br />
         */
        public ObservableMap<Long, R_get_sovereignty_structures> structures() {
            if (get_sovereignty_structures_holder == null) {
                synchronized (this)
                {
                    if (get_sovereignty_structures_holder == null) {
                        get_sovereignty_structures_holder = FXCollections.observableHashMap();
                        ObservableMap<Long, R_get_sovereignty_structures> finalContainer = get_sovereignty_structures_holder;
                        addFetchCacheArray("get_sovereignty_structures", (page, headerHandler) -> (swagger).get_sovereignty_structures(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                LinkedHashMap<Long, R_get_sovereignty_structures> newmap = new LinkedHashMap<>();
                                for (R_get_sovereignty_structures val: arr) {
                                    newmap.put((val.structure_id), (val));
                                }
                                finalContainer.entrySet();
                                finalContainer.putAll(newmap);
                            }
                        }
                        );
                    }
                }
            }
            return get_sovereignty_structures_holder;
        }
    }

    public class Status {
        private SimpleObjectProperty<R_get_status> get_status_holder;

        /**
         * EVE Server status
         * 
         * cache over {@link Swagger#get_status}<br />
         */
        public Property<R_get_status> status() {
            if (get_status_holder == null) {
                synchronized (this)
                {
                    if (get_status_holder == null) {
                        get_status_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<R_get_status> finalContainer = get_status_holder;
                        addFetchCacheObject("get_status", headerHandler -> (swagger).get_status(headerHandler), item -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return get_status_holder;
        }
    }

    public class Universe {
        private ObservableList<R_get_universe_ancestries> get_universe_ancestries_holder;
        private final Map<Integer, Property<R_get_universe_asteroid_belts_asteroid_belt_id>> get_universe_asteroid_belts_asteroid_belt_id_holder = new HashMap<>();
        private ObservableList<R_get_universe_bloodlines> get_universe_bloodlines_holder;
        private ObservableList<Integer> get_universe_categories_holder;
        private final Map<Integer, Property<R_get_universe_categories_category_id>> get_universe_categories_category_id_holder = new HashMap<>();
        private ObservableList<Integer> get_universe_constellations_holder;
        private final Map<Integer, Property<R_get_universe_constellations_constellation_id>> get_universe_constellations_constellation_id_holder = new HashMap<>();
        private ObservableList<Integer> get_universe_graphics_holder;
        private final Map<Integer, Property<R_get_universe_graphics_graphic_id>> get_universe_graphics_graphic_id_holder = new HashMap<>();
        private ObservableList<Integer> get_universe_groups_holder;
        private final Map<Integer, Property<R_get_universe_groups_group_id>> get_universe_groups_group_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_moons_moon_id>> get_universe_moons_moon_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_planets_planet_id>> get_universe_planets_planet_id_holder = new HashMap<>();
        private ObservableList<R_get_universe_races> get_universe_races_holder;
        private ObservableList<Integer> get_universe_regions_holder;
        private final Map<Integer, Property<R_get_universe_regions_region_id>> get_universe_regions_region_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_schematics_schematic_id>> get_universe_schematics_schematic_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_stargates_stargate_id>> get_universe_stargates_stargate_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_stars_star_id>> get_universe_stars_star_id_holder = new HashMap<>();
        private ObservableList<Long> get_universe_structures_holder;
        private ObservableList<R_get_universe_system_jumps> get_universe_system_jumps_holder;
        private ObservableList<Integer> get_universe_systems_holder;
        private ObservableList<Integer> get_universe_types_holder;
        private ObservableList<R_get_universe_factions> get_universe_factions_holder;
        private final Map<Integer, Property<R_get_universe_stations_station_id>> get_universe_stations_station_id_holder = new HashMap<>();
        private final Map<Long, Property<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_holder = new HashMap<>();
        private ObservableList<R_get_universe_system_kills> get_universe_system_kills_holder;
        private final Map<Integer, Property<R_get_universe_types_type_id>> get_universe_types_type_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_universe_systems_system_id>> get_universe_systems_system_id_holder = new HashMap<>();

        /**
         * Get all character ancestries
         * 
         * cache over {@link Swagger#get_universe_ancestries}<br />
         */
        public ObservableList<R_get_universe_ancestries> ancestries() {
            if (get_universe_ancestries_holder == null) {
                synchronized (this)
                {
                    if (get_universe_ancestries_holder == null) {
                        ObservableList<R_get_universe_ancestries> finalContainer = FXCollections.observableArrayList();
                        get_universe_ancestries_holder = finalContainer;
                        addFetchCacheArray("get_universe_ancestries", (page, headerHandler) -> (swagger).get_universe_ancestries(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_ancestries_holder;
        }

        /**
         * Get information on an asteroid belt
         * 
         * cache over {@link Swagger#get_universe_asteroid_belts}<br />
         * 
         * @param asteroid_belt_id
         *     asteroid_belt_id integer
         */
        public Property<R_get_universe_asteroid_belts_asteroid_belt_id> asteroid_belts(int asteroid_belt_id) {
            Property<R_get_universe_asteroid_belts_asteroid_belt_id> ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
            if (ret == null) {
                synchronized (get_universe_asteroid_belts_asteroid_belt_id_holder)
                {
                    ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_asteroid_belts_asteroid_belt_id_holder.put(asteroid_belt_id, ret);
                        addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", headerHandler -> (swagger).get_universe_asteroid_belts(asteroid_belt_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of bloodlines
         * 
         * cache over {@link Swagger#get_universe_bloodlines}<br />
         */
        public ObservableList<R_get_universe_bloodlines> bloodlines() {
            if (get_universe_bloodlines_holder == null) {
                synchronized (this)
                {
                    if (get_universe_bloodlines_holder == null) {
                        ObservableList<R_get_universe_bloodlines> finalContainer = FXCollections.observableArrayList();
                        get_universe_bloodlines_holder = finalContainer;
                        addFetchCacheArray("get_universe_bloodlines", (page, headerHandler) -> (swagger).get_universe_bloodlines(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_bloodlines_holder;
        }

        /**
         * Get a list of item categories
         * 
         * cache over {@link Swagger#get_universe_categories}<br />
         */
        public ObservableList<Integer> categories() {
            if (get_universe_categories_holder == null) {
                synchronized (this)
                {
                    if (get_universe_categories_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_categories_holder = finalContainer;
                        addFetchCacheArray("get_universe_categories", (page, headerHandler) -> IntStream.of((swagger).get_universe_categories(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_categories_holder;
        }

        /**
         * Get information of an item category
         * 
         * cache over {@link Swagger#get_universe_categories}<br />
         * 
         * @param category_id
         *     An Eve item category ID
         */
        public Property<R_get_universe_categories_category_id> categories(int category_id) {
            Property<R_get_universe_categories_category_id> ret = get_universe_categories_category_id_holder.get(category_id);
            if (ret == null) {
                synchronized (get_universe_categories_category_id_holder)
                {
                    ret = get_universe_categories_category_id_holder.get(category_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_categories_category_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_categories_category_id_holder.put(category_id, ret);
                        addFetchCacheObject("get_universe_categories_category_id", headerHandler -> (swagger).get_universe_categories(category_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of constellations
         * 
         * cache over {@link Swagger#get_universe_constellations}<br />
         */
        public ObservableList<Integer> constellations() {
            if (get_universe_constellations_holder == null) {
                synchronized (this)
                {
                    if (get_universe_constellations_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_constellations_holder = finalContainer;
                        addFetchCacheArray("get_universe_constellations", (page, headerHandler) -> IntStream.of((swagger).get_universe_constellations(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_constellations_holder;
        }

        /**
         * Get information on a constellation
         * 
         * cache over {@link Swagger#get_universe_constellations}<br />
         * 
         * @param constellation_id
         *     constellation_id integer
         */
        public Property<R_get_universe_constellations_constellation_id> constellations(int constellation_id) {
            Property<R_get_universe_constellations_constellation_id> ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
            if (ret == null) {
                synchronized (get_universe_constellations_constellation_id_holder)
                {
                    ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_constellations_constellation_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_constellations_constellation_id_holder.put(constellation_id, ret);
                        addFetchCacheObject("get_universe_constellations_constellation_id", headerHandler -> (swagger).get_universe_constellations(constellation_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of graphics
         * 
         * cache over {@link Swagger#get_universe_graphics}<br />
         */
        public ObservableList<Integer> graphics() {
            if (get_universe_graphics_holder == null) {
                synchronized (this)
                {
                    if (get_universe_graphics_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_graphics_holder = finalContainer;
                        addFetchCacheArray("get_universe_graphics", (page, headerHandler) -> IntStream.of((swagger).get_universe_graphics(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_graphics_holder;
        }

        /**
         * Get information on a graphic
         * 
         * cache over {@link Swagger#get_universe_graphics}<br />
         * 
         * @param graphic_id
         *     graphic_id integer
         */
        public Property<R_get_universe_graphics_graphic_id> graphics(int graphic_id) {
            Property<R_get_universe_graphics_graphic_id> ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
            if (ret == null) {
                synchronized (get_universe_graphics_graphic_id_holder)
                {
                    ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_graphics_graphic_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_graphics_graphic_id_holder.put(graphic_id, ret);
                        addFetchCacheObject("get_universe_graphics_graphic_id", headerHandler -> (swagger).get_universe_graphics(graphic_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of item groups
         * 
         * cache over {@link Swagger#get_universe_groups}<br />
         */
        public ObservableList<Integer> groups() {
            if (get_universe_groups_holder == null) {
                synchronized (this)
                {
                    if (get_universe_groups_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_groups_holder = finalContainer;
                        addFetchCacheArray("get_universe_groups", (page, headerHandler) -> IntStream.of((swagger).get_universe_groups(page, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_groups_holder;
        }

        /**
         * Get information on an item group
         * 
         * cache over {@link Swagger#get_universe_groups}<br />
         * 
         * @param group_id
         *     An Eve item group ID
         */
        public Property<R_get_universe_groups_group_id> groups(int group_id) {
            Property<R_get_universe_groups_group_id> ret = get_universe_groups_group_id_holder.get(group_id);
            if (ret == null) {
                synchronized (get_universe_groups_group_id_holder)
                {
                    ret = get_universe_groups_group_id_holder.get(group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_groups_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_groups_group_id_holder.put(group_id, ret);
                        addFetchCacheObject("get_universe_groups_group_id", headerHandler -> (swagger).get_universe_groups(group_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a moon
         * 
         * cache over {@link Swagger#get_universe_moons}<br />
         * 
         * @param moon_id
         *     moon_id integer
         */
        public Property<R_get_universe_moons_moon_id> moons(int moon_id) {
            Property<R_get_universe_moons_moon_id> ret = get_universe_moons_moon_id_holder.get(moon_id);
            if (ret == null) {
                synchronized (get_universe_moons_moon_id_holder)
                {
                    ret = get_universe_moons_moon_id_holder.get(moon_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_moons_moon_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_moons_moon_id_holder.put(moon_id, ret);
                        addFetchCacheObject("get_universe_moons_moon_id", headerHandler -> (swagger).get_universe_moons(moon_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a planet
         * 
         * cache over {@link Swagger#get_universe_planets}<br />
         * 
         * @param planet_id
         *     planet_id integer
         */
        public Property<R_get_universe_planets_planet_id> planets(int planet_id) {
            Property<R_get_universe_planets_planet_id> ret = get_universe_planets_planet_id_holder.get(planet_id);
            if (ret == null) {
                synchronized (get_universe_planets_planet_id_holder)
                {
                    ret = get_universe_planets_planet_id_holder.get(planet_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_planets_planet_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_planets_planet_id_holder.put(planet_id, ret);
                        addFetchCacheObject("get_universe_planets_planet_id", headerHandler -> (swagger).get_universe_planets(planet_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get a list of character races
         * 
         * cache over {@link Swagger#get_universe_races}<br />
         */
        public ObservableList<R_get_universe_races> races() {
            if (get_universe_races_holder == null) {
                synchronized (this)
                {
                    if (get_universe_races_holder == null) {
                        ObservableList<R_get_universe_races> finalContainer = FXCollections.observableArrayList();
                        get_universe_races_holder = finalContainer;
                        addFetchCacheArray("get_universe_races", (page, headerHandler) -> (swagger).get_universe_races(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_races_holder;
        }

        /**
         * Get a list of regions
         * 
         * cache over {@link Swagger#get_universe_regions}<br />
         */
        public ObservableList<Integer> regions() {
            if (get_universe_regions_holder == null) {
                synchronized (this)
                {
                    if (get_universe_regions_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_regions_holder = finalContainer;
                        addFetchCacheArray("get_universe_regions", (page, headerHandler) -> IntStream.of((swagger).get_universe_regions(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_regions_holder;
        }

        /**
         * Get information on a region
         * 
         * cache over {@link Swagger#get_universe_regions}<br />
         * 
         * @param region_id
         *     region_id integer
         */
        public Property<R_get_universe_regions_region_id> regions(int region_id) {
            Property<R_get_universe_regions_region_id> ret = get_universe_regions_region_id_holder.get(region_id);
            if (ret == null) {
                synchronized (get_universe_regions_region_id_holder)
                {
                    ret = get_universe_regions_region_id_holder.get(region_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_regions_region_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_regions_region_id_holder.put(region_id, ret);
                        addFetchCacheObject("get_universe_regions_region_id", headerHandler -> (swagger).get_universe_regions(region_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a planetary factory schematic
         * 
         * cache over {@link Swagger#get_universe_schematics}<br />
         * 
         * @param schematic_id
         *     A PI schematic ID
         */
        public Property<R_get_universe_schematics_schematic_id> schematics(int schematic_id) {
            Property<R_get_universe_schematics_schematic_id> ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
            if (ret == null) {
                synchronized (get_universe_schematics_schematic_id_holder)
                {
                    ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_schematics_schematic_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_schematics_schematic_id_holder.put(schematic_id, ret);
                        addFetchCacheObject("get_universe_schematics_schematic_id", headerHandler -> (swagger).get_universe_schematics(schematic_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a stargate
         * 
         * cache over {@link Swagger#get_universe_stargates}<br />
         * 
         * @param stargate_id
         *     stargate_id integer
         */
        public Property<R_get_universe_stargates_stargate_id> stargates(int stargate_id) {
            Property<R_get_universe_stargates_stargate_id> ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
            if (ret == null) {
                synchronized (get_universe_stargates_stargate_id_holder)
                {
                    ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stargates_stargate_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_stargates_stargate_id_holder.put(stargate_id, ret);
                        addFetchCacheObject("get_universe_stargates_stargate_id", headerHandler -> (swagger).get_universe_stargates(stargate_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a star
         * 
         * cache over {@link Swagger#get_universe_stars}<br />
         * 
         * @param star_id
         *     star_id integer
         */
        public Property<R_get_universe_stars_star_id> stars(int star_id) {
            Property<R_get_universe_stars_star_id> ret = get_universe_stars_star_id_holder.get(star_id);
            if (ret == null) {
                synchronized (get_universe_stars_star_id_holder)
                {
                    ret = get_universe_stars_star_id_holder.get(star_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stars_star_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_stars_star_id_holder.put(star_id, ret);
                        addFetchCacheObject("get_universe_stars_star_id", headerHandler -> (swagger).get_universe_stars(star_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * List all public structures
         * 
         * cache over {@link Swagger#get_universe_structures}<br />
         */
        public ObservableList<Long> structures() {
            if (get_universe_structures_holder == null) {
                synchronized (this)
                {
                    if (get_universe_structures_holder == null) {
                        ObservableList<Long> finalContainer = FXCollections.observableArrayList();
                        get_universe_structures_holder = finalContainer;
                        addFetchCacheArray("get_universe_structures", (page, headerHandler) -> LongStream.of((swagger).get_universe_structures(headerHandler)).mapToObj((Long::valueOf)).toArray((Long[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_structures_holder;
        }

        /**
         * Get the number of jumps in solar systems within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with jumps will be listed
         * 
         * cache over {@link Swagger#get_universe_system_jumps}<br />
         */
        public ObservableList<R_get_universe_system_jumps> system_jumps() {
            if (get_universe_system_jumps_holder == null) {
                synchronized (this)
                {
                    if (get_universe_system_jumps_holder == null) {
                        ObservableList<R_get_universe_system_jumps> finalContainer = FXCollections.observableArrayList();
                        get_universe_system_jumps_holder = finalContainer;
                        addFetchCacheArray("get_universe_system_jumps", (page, headerHandler) -> (swagger).get_universe_system_jumps(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_system_jumps_holder;
        }

        /**
         * Get a list of solar systems
         * 
         * cache over {@link Swagger#get_universe_systems}<br />
         */
        public ObservableList<Integer> systems() {
            if (get_universe_systems_holder == null) {
                synchronized (this)
                {
                    if (get_universe_systems_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_systems_holder = finalContainer;
                        addFetchCacheArray("get_universe_systems", (page, headerHandler) -> IntStream.of((swagger).get_universe_systems(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_systems_holder;
        }

        /**
         * Get a list of type ids
         * 
         * cache over {@link Swagger#get_universe_types}<br />
         */
        public ObservableList<Integer> types() {
            if (get_universe_types_holder == null) {
                synchronized (this)
                {
                    if (get_universe_types_holder == null) {
                        ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                        get_universe_types_holder = finalContainer;
                        addFetchCacheArray("get_universe_types", (page, headerHandler) -> IntStream.of((swagger).get_universe_types(page, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_types_holder;
        }

        /**
         * Get a list of factions
         * 
         * cache over {@link Swagger#get_universe_factions}<br />
         */
        public ObservableList<R_get_universe_factions> factions() {
            if (get_universe_factions_holder == null) {
                synchronized (this)
                {
                    if (get_universe_factions_holder == null) {
                        ObservableList<R_get_universe_factions> finalContainer = FXCollections.observableArrayList();
                        get_universe_factions_holder = finalContainer;
                        addFetchCacheArray("get_universe_factions", (page, headerHandler) -> (swagger).get_universe_factions(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_factions_holder;
        }

        /**
         * Get information on a station
         * 
         * cache over {@link Swagger#get_universe_stations}<br />
         * 
         * @param station_id
         *     station_id integer
         */
        public Property<R_get_universe_stations_station_id> stations(int station_id) {
            Property<R_get_universe_stations_station_id> ret = get_universe_stations_station_id_holder.get(station_id);
            if (ret == null) {
                synchronized (get_universe_stations_station_id_holder)
                {
                    ret = get_universe_stations_station_id_holder.get(station_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stations_station_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_stations_station_id_holder.put(station_id, ret);
                        addFetchCacheObject("get_universe_stations_station_id", headerHandler -> (swagger).get_universe_stations(station_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Returns information on requested structure if you are on the ACL. Otherwise, returns "Forbidden" for all inputs.
         * 
         * cache over {@link Swagger#get_universe_structures}<br />
         * 
         * @param structure_id
         *     An Eve structure ID
         */
        public Property<R_get_universe_structures_structure_id> structures(long structure_id) {
            Property<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
            if (ret == null) {
                synchronized (get_universe_structures_structure_id_holder)
                {
                    ret = get_universe_structures_structure_id_holder.get(structure_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_structures_structure_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_structures_structure_id_holder.put(structure_id, ret);
                        addFetchCacheObject("get_universe_structures_structure_id", headerHandler -> (swagger).get_universe_structures(structure_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get the number of ship, pod and NPC kills per solar system within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with kills will be listed
         * 
         * cache over {@link Swagger#get_universe_system_kills}<br />
         */
        public ObservableList<R_get_universe_system_kills> system_kills() {
            if (get_universe_system_kills_holder == null) {
                synchronized (this)
                {
                    if (get_universe_system_kills_holder == null) {
                        ObservableList<R_get_universe_system_kills> finalContainer = FXCollections.observableArrayList();
                        get_universe_system_kills_holder = finalContainer;
                        addFetchCacheArray("get_universe_system_kills", (page, headerHandler) -> (swagger).get_universe_system_kills(headerHandler), arr -> {
                            synchronized (finalContainer)
                            {
                                finalContainer.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return get_universe_system_kills_holder;
        }

        /**
         * Get information on a type
         * 
         * cache over {@link Swagger#get_universe_types}<br />
         * 
         * @param type_id
         *     An Eve item type ID
         */
        public Property<R_get_universe_types_type_id> types(int type_id) {
            Property<R_get_universe_types_type_id> ret = get_universe_types_type_id_holder.get(type_id);
            if (ret == null) {
                synchronized (get_universe_types_type_id_holder)
                {
                    ret = get_universe_types_type_id_holder.get(type_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_types_type_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_types_type_id_holder.put(type_id, ret);
                        addFetchCacheObject("get_universe_types_type_id", headerHandler -> (swagger).get_universe_types(type_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }

        /**
         * Get information on a solar system.
         * 
         * cache over {@link Swagger#get_universe_systems}<br />
         * 
         * @param system_id
         *     system_id integer
         */
        public Property<R_get_universe_systems_system_id> systems(int system_id) {
            Property<R_get_universe_systems_system_id> ret = get_universe_systems_system_id_holder.get(system_id);
            if (ret == null) {
                synchronized (get_universe_systems_system_id_holder)
                {
                    ret = get_universe_systems_system_id_holder.get(system_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_systems_system_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_universe_systems_system_id_holder.put(system_id, ret);
                        addFetchCacheObject("get_universe_systems_system_id", headerHandler -> (swagger).get_universe_systems(system_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }

    public class Wars {
        private final Map<Integer, ObservableList<Integer>> get_wars_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_wars_war_id>> get_wars_war_id_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_killmails_2>> get_wars_war_id_killmails_holder = new HashMap<>();

        /**
         * Return a list of wars
         * 
         * cache over {@link Swagger#get_wars}<br />
         * 
         * @param max_war_id
         *     Only return wars with ID smaller than this.
         */
        public ObservableList<Integer> wars(Integer max_war_id) {
            ObservableList<Integer> ret = get_wars_holder.get(max_war_id);
            if (ret == null) {
                synchronized (get_wars_holder)
                {
                    ret = get_wars_holder.get(max_war_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_wars_holder.put(max_war_id, ret);
                        addFetchCacheArray("get_wars", (page, headerHandler) -> IntStream.of((swagger).get_wars(max_war_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
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
        public Property<R_get_wars_war_id> get(int war_id) {
            Property<R_get_wars_war_id> ret = get_wars_war_id_holder.get(war_id);
            if (ret == null) {
                synchronized (get_wars_war_id_holder)
                {
                    ret = get_wars_war_id_holder.get(war_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_wars_war_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        get_wars_war_id_holder.put(war_id, ret);
                        addFetchCacheObject("get_wars_war_id", headerHandler -> (swagger).get_wars(war_id, headerHandler), item -> {
                            synchronized (finalret)
                            {
                                finalret.set(item);
                            }
                        }
                        );
                    }
                }
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
        public ObservableList<M_get_killmails_2> killmails(int war_id) {
            ObservableList<M_get_killmails_2> ret = get_wars_war_id_killmails_holder.get(war_id);
            if (ret == null) {
                synchronized (get_wars_war_id_killmails_holder)
                {
                    ret = get_wars_war_id_killmails_holder.get(war_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        get_wars_war_id_killmails_holder.put(war_id, ret);
                        addFetchCacheArray("get_wars_war_id_killmails", (page, headerHandler) -> (swagger).get_wars_killmails(page, war_id, headerHandler), arr -> {
                            synchronized (finalret)
                            {
                                finalret.setAll(arr);
                            }
                        }
                        );
                    }
                }
            }
            return ret;
        }
    }
}
