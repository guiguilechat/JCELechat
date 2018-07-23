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
         * @see get_alliances
         */
        public ObservableList<Integer> get_alliances() {
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
         * @see get_alliances_alliance_id_contacts_labels
         */
        public ObservableList<M_get_contacts_labels_2> get_alliances_alliance_id_contacts_labels(int alliance_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_contacts_labels_holder)
                {
                    ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_alliances_alliance_id_contacts_labels", (page, headerHandler) -> (swagger).get_alliances_alliance_id_contacts_labels(alliance_id, headerHandler), arr -> {
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
         * @see get_alliances_alliance_id_corporations
         */
        public ObservableList<Integer> get_alliances_alliance_id_corporations(int alliance_id) {
            ObservableList<Integer> ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_corporations_holder)
                {
                    ret = get_alliances_alliance_id_corporations_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_alliances_alliance_id_corporations", (page, headerHandler) -> IntStream.of((swagger).get_alliances_alliance_id_corporations(alliance_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
         * @see get_alliances_alliance_id_icons
         */
        public Property<R_get_alliances_alliance_id_icons> get_alliances_alliance_id_icons(int alliance_id) {
            Property<R_get_alliances_alliance_id_icons> ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_icons_holder)
                {
                    ret = get_alliances_alliance_id_icons_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id_icons> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_alliances_alliance_id_icons", (h->swagger.get_alliances_alliance_id_icons(alliance_id,h)), item -> {
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
         * @see get_alliances_alliance_id_contacts
         */
        public ObservableList<R_get_alliances_alliance_id_contacts> get_alliances_alliance_id_contacts(int alliance_id) {
            ObservableList<R_get_alliances_alliance_id_contacts> ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_contacts_holder)
                {
                    ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
                    if (ret == null) {
                        ObservableList<R_get_alliances_alliance_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_alliances_alliance_id_contacts", (page, headerHandler) -> (swagger).get_alliances_alliance_id_contacts(alliance_id, page, headerHandler), arr -> {
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
         * @see get_alliances_alliance_id
         */
        public Property<R_get_alliances_alliance_id> get_alliances_alliance_id(int alliance_id) {
            Property<R_get_alliances_alliance_id> ret = get_alliances_alliance_id_holder.get(alliance_id);
            if (ret == null) {
                synchronized (get_alliances_alliance_id_holder)
                {
                    ret = get_alliances_alliance_id_holder.get(alliance_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_alliances_alliance_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_alliances_alliance_id", (h->swagger.get_alliances_alliance_id(alliance_id,h)), item -> {
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
        private final Map<Integer, ObservableList<M_get_contacts_labels_2>> get_characters_character_id_contacts_labels_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contracts_22>> get_characters_character_id_contracts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_corporationhistory>> get_characters_character_id_corporationhistory_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fatigue>> get_characters_character_id_fatigue_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_fittings>> get_characters_character_id_fittings_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fleet>> get_characters_character_id_fleet_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_fw_stats>> get_characters_character_id_fw_stats_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_characters_character_id_implants_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_killmails_2>> get_characters_character_id_killmails_recent_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_location>> get_characters_character_id_location_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_loyalty_points>> get_characters_character_id_loyalty_points_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_mail_lists>> get_characters_character_id_mail_lists_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_medals>> get_characters_character_id_medals_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_mining>> get_characters_character_id_mining_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_notifications_contacts>> get_characters_character_id_notifications_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_opportunities>> get_characters_character_id_opportunities_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_planets>> get_characters_character_id_planets_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_ship>> get_characters_character_id_ship_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_standings_3>> get_characters_character_id_standings_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_titles>> get_characters_character_id_titles_holder = new HashMap<>();
        private final Map<Integer, Property<Double>> get_characters_character_id_wallet_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_bookmarks_9>> get_characters_character_id_bookmarks_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_bookmarks_folders>> get_characters_character_id_bookmarks_folders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_contacts>> get_characters_character_id_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_notifications>> get_characters_character_id_notifications_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_online>> get_characters_character_id_online_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_portrait>> get_characters_character_id_portrait_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_roles>> get_characters_character_id_roles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_skillqueue>> get_characters_character_id_skillqueue_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_characters_character_id_stats>> get_characters_character_id_stats_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_assets_8>> get_characters_character_id_assets_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_clones>> get_characters_character_id_clones_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_mail_labels>> get_characters_character_id_mail_labels_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id>> get_characters_character_id_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_characters_character_id_skills>> get_characters_character_id_skills_holder = new HashMap<>();

        /**
         * @see get_characters_character_id_agents_research
         */
        public ObservableList<R_get_characters_character_id_agents_research> get_characters_character_id_agents_research(int character_id) {
            ObservableList<R_get_characters_character_id_agents_research> ret = get_characters_character_id_agents_research_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_agents_research_holder)
                {
                    ret = get_characters_character_id_agents_research_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_agents_research> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_agents_research", (page, headerHandler) -> (swagger).get_characters_character_id_agents_research(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_attributes
         */
        public Property<R_get_characters_character_id_attributes> get_characters_character_id_attributes(int character_id) {
            Property<R_get_characters_character_id_attributes> ret = get_characters_character_id_attributes_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_attributes_holder)
                {
                    ret = get_characters_character_id_attributes_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_attributes> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_attributes", (h->swagger.get_characters_character_id_attributes(character_id,h)), item -> {
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
         * @see get_characters_character_id_calendar
         */
        public ObservableList<R_get_characters_character_id_calendar> get_characters_character_id_calendar(int character_id, Integer from_event) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_calendar_event_id_attendees
         */
        public ObservableList<R_get_characters_character_id_calendar_event_id_attendees> get_characters_character_id_calendar_event_id_attendees(int character_id, int event_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_contacts_labels
         */
        public ObservableList<M_get_contacts_labels_2> get_characters_character_id_contacts_labels(int character_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_characters_character_id_contacts_labels_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contacts_labels_holder)
                {
                    ret = get_characters_character_id_contacts_labels_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_contacts_labels", (page, headerHandler) -> (swagger).get_characters_character_id_contacts_labels(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_contracts
         */
        public ObservableList<M_get_contracts_22> get_characters_character_id_contracts(int character_id) {
            ObservableList<M_get_contracts_22> ret = get_characters_character_id_contracts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contracts_holder)
                {
                    ret = get_characters_character_id_contracts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_contracts_22> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_contracts", (page, headerHandler) -> (swagger).get_characters_character_id_contracts(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_contracts_contract_id_bids
         */
        public ObservableMap<Integer, M_get_contracts_contract_bids_4> get_characters_character_id_contracts_contract_id_bids(int character_id, int contract_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_contracts_contract_id_items
         */
        public ObservableMap<Long, M_get_contracts_contract_items_6> get_characters_character_id_contracts_contract_id_items(int character_id, int contract_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_corporationhistory
         */
        public ObservableList<R_get_characters_character_id_corporationhistory> get_characters_character_id_corporationhistory(int character_id) {
            ObservableList<R_get_characters_character_id_corporationhistory> ret = get_characters_character_id_corporationhistory_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_corporationhistory_holder)
                {
                    ret = get_characters_character_id_corporationhistory_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_corporationhistory> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_corporationhistory", (page, headerHandler) -> (swagger).get_characters_character_id_corporationhistory(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_fatigue
         */
        public Property<R_get_characters_character_id_fatigue> get_characters_character_id_fatigue(int character_id) {
            Property<R_get_characters_character_id_fatigue> ret = get_characters_character_id_fatigue_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fatigue_holder)
                {
                    ret = get_characters_character_id_fatigue_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fatigue> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_fatigue", (h->swagger.get_characters_character_id_fatigue(character_id,h)), item -> {
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
         * @see get_characters_character_id_fittings
         */
        public ObservableList<R_get_characters_character_id_fittings> get_characters_character_id_fittings(int character_id) {
            ObservableList<R_get_characters_character_id_fittings> ret = get_characters_character_id_fittings_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fittings_holder)
                {
                    ret = get_characters_character_id_fittings_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_fittings> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_fittings", (page, headerHandler) -> (swagger).get_characters_character_id_fittings(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_fleet
         */
        public Property<R_get_characters_character_id_fleet> get_characters_character_id_fleet(int character_id) {
            Property<R_get_characters_character_id_fleet> ret = get_characters_character_id_fleet_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fleet_holder)
                {
                    ret = get_characters_character_id_fleet_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fleet> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_fleet", (h->swagger.get_characters_character_id_fleet(character_id,h)), item -> {
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
         * @see get_characters_character_id_fw_stats
         */
        public Property<R_get_characters_character_id_fw_stats> get_characters_character_id_fw_stats(int character_id) {
            Property<R_get_characters_character_id_fw_stats> ret = get_characters_character_id_fw_stats_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_fw_stats_holder)
                {
                    ret = get_characters_character_id_fw_stats_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_fw_stats> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_fw_stats", (h->swagger.get_characters_character_id_fw_stats(character_id,h)), item -> {
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
         * @see get_characters_character_id_implants
         */
        public ObservableList<Integer> get_characters_character_id_implants(int character_id) {
            ObservableList<Integer> ret = get_characters_character_id_implants_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_implants_holder)
                {
                    ret = get_characters_character_id_implants_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_implants", (page, headerHandler) -> IntStream.of((swagger).get_characters_character_id_implants(character_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
         * @see get_characters_character_id_industry_jobs
         */
        public ObservableMap<Integer, R_get_characters_character_id_industry_jobs> get_characters_character_id_industry_jobs(int character_id, Boolean include_completed) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_killmails_recent
         */
        public ObservableList<M_get_killmails_2> get_characters_character_id_killmails_recent(int character_id) {
            ObservableList<M_get_killmails_2> ret = get_characters_character_id_killmails_recent_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_killmails_recent_holder)
                {
                    ret = get_characters_character_id_killmails_recent_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_killmails_recent", (page, headerHandler) -> (swagger).get_characters_character_id_killmails_recent(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_location
         */
        public Property<R_get_characters_character_id_location> get_characters_character_id_location(int character_id) {
            Property<R_get_characters_character_id_location> ret = get_characters_character_id_location_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_location_holder)
                {
                    ret = get_characters_character_id_location_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_location> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_location", (h->swagger.get_characters_character_id_location(character_id,h)), item -> {
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
         * @see get_characters_character_id_loyalty_points
         */
        public ObservableList<R_get_characters_character_id_loyalty_points> get_characters_character_id_loyalty_points(int character_id) {
            ObservableList<R_get_characters_character_id_loyalty_points> ret = get_characters_character_id_loyalty_points_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_loyalty_points_holder)
                {
                    ret = get_characters_character_id_loyalty_points_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_loyalty_points> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_loyalty_points", (page, headerHandler) -> (swagger).get_characters_character_id_loyalty_points(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_mail
         */
        public ObservableList<R_get_characters_character_id_mail> get_characters_character_id_mail(int character_id, int[] labels, Integer last_mail_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_mail_lists
         */
        public ObservableList<R_get_characters_character_id_mail_lists> get_characters_character_id_mail_lists(int character_id) {
            ObservableList<R_get_characters_character_id_mail_lists> ret = get_characters_character_id_mail_lists_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_lists_holder)
                {
                    ret = get_characters_character_id_mail_lists_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_mail_lists> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_mail_lists", (page, headerHandler) -> (swagger).get_characters_character_id_mail_lists(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_mail_mail_id
         */
        public Property<R_get_characters_character_id_mail_mail_id> get_characters_character_id_mail_mail_id(int character_id, int mail_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_medals
         */
        public ObservableList<R_get_characters_character_id_medals> get_characters_character_id_medals(int character_id) {
            ObservableList<R_get_characters_character_id_medals> ret = get_characters_character_id_medals_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_medals_holder)
                {
                    ret = get_characters_character_id_medals_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_medals> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_medals", (page, headerHandler) -> (swagger).get_characters_character_id_medals(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_mining
         */
        public ObservableList<R_get_characters_character_id_mining> get_characters_character_id_mining(int character_id) {
            ObservableList<R_get_characters_character_id_mining> ret = get_characters_character_id_mining_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mining_holder)
                {
                    ret = get_characters_character_id_mining_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_mining> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_mining", (page, headerHandler) -> (swagger).get_characters_character_id_mining(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_notifications_contacts
         */
        public ObservableList<R_get_characters_character_id_notifications_contacts> get_characters_character_id_notifications_contacts(int character_id) {
            ObservableList<R_get_characters_character_id_notifications_contacts> ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_notifications_contacts_holder)
                {
                    ret = get_characters_character_id_notifications_contacts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_notifications_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_notifications_contacts", (page, headerHandler) -> (swagger).get_characters_character_id_notifications_contacts(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_opportunities
         */
        public ObservableList<R_get_characters_character_id_opportunities> get_characters_character_id_opportunities(int character_id) {
            ObservableList<R_get_characters_character_id_opportunities> ret = get_characters_character_id_opportunities_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_opportunities_holder)
                {
                    ret = get_characters_character_id_opportunities_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_opportunities> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_opportunities", (page, headerHandler) -> (swagger).get_characters_character_id_opportunities(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_orders_history
         */
        public ObservableMap<Long, R_get_characters_character_id_orders_history> get_characters_character_id_orders_history(int character_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_planets
         */
        public ObservableList<R_get_characters_character_id_planets> get_characters_character_id_planets(int character_id) {
            ObservableList<R_get_characters_character_id_planets> ret = get_characters_character_id_planets_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_planets_holder)
                {
                    ret = get_characters_character_id_planets_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_planets> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_planets", (page, headerHandler) -> (swagger).get_characters_character_id_planets(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_ship
         */
        public Property<R_get_characters_character_id_ship> get_characters_character_id_ship(int character_id) {
            Property<R_get_characters_character_id_ship> ret = get_characters_character_id_ship_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_ship_holder)
                {
                    ret = get_characters_character_id_ship_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_ship> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_ship", (h->swagger.get_characters_character_id_ship(character_id,h)), item -> {
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
         * @see get_characters_character_id_standings
         */
        public ObservableList<M_get_standings_3> get_characters_character_id_standings(int character_id) {
            ObservableList<M_get_standings_3> ret = get_characters_character_id_standings_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_standings_holder)
                {
                    ret = get_characters_character_id_standings_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_standings_3> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_standings", (page, headerHandler) -> (swagger).get_characters_character_id_standings(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_titles
         */
        public ObservableList<R_get_characters_character_id_titles> get_characters_character_id_titles(int character_id) {
            ObservableList<R_get_characters_character_id_titles> ret = get_characters_character_id_titles_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_titles_holder)
                {
                    ret = get_characters_character_id_titles_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_titles", (page, headerHandler) -> (swagger).get_characters_character_id_titles(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_wallet
         */
        public Property<Double> get_characters_character_id_wallet(int character_id) {
            Property<Double> ret = get_characters_character_id_wallet_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_wallet_holder)
                {
                    ret = get_characters_character_id_wallet_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<Double> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_wallet", (h->swagger.get_characters_character_id_wallet(character_id,h)), item -> {
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
         * @see get_characters_character_id_wallet_transactions
         */
        public ObservableMap<Long, R_get_characters_character_id_wallet_transactions> get_characters_character_id_wallet_transactions(int character_id, Long from_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_blueprints
         */
        public ObservableMap<Long, M_get_blueprints_8> get_characters_character_id_blueprints(int character_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_bookmarks
         */
        public ObservableList<M_get_bookmarks_9> get_characters_character_id_bookmarks(int character_id) {
            ObservableList<M_get_bookmarks_9> ret = get_characters_character_id_bookmarks_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_bookmarks_holder)
                {
                    ret = get_characters_character_id_bookmarks_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_bookmarks_9> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_bookmarks", (page, headerHandler) -> (swagger).get_characters_character_id_bookmarks(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_bookmarks_folders
         */
        public ObservableList<R_get_characters_character_id_bookmarks_folders> get_characters_character_id_bookmarks_folders(int character_id) {
            ObservableList<R_get_characters_character_id_bookmarks_folders> ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_bookmarks_folders_holder)
                {
                    ret = get_characters_character_id_bookmarks_folders_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_bookmarks_folders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_bookmarks_folders", (page, headerHandler) -> (swagger).get_characters_character_id_bookmarks_folders(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_contacts
         */
        public ObservableList<R_get_characters_character_id_contacts> get_characters_character_id_contacts(int character_id) {
            ObservableList<R_get_characters_character_id_contacts> ret = get_characters_character_id_contacts_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_contacts_holder)
                {
                    ret = get_characters_character_id_contacts_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_contacts", (page, headerHandler) -> (swagger).get_characters_character_id_contacts(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_notifications
         */
        public ObservableList<R_get_characters_character_id_notifications> get_characters_character_id_notifications(int character_id) {
            ObservableList<R_get_characters_character_id_notifications> ret = get_characters_character_id_notifications_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_notifications_holder)
                {
                    ret = get_characters_character_id_notifications_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_notifications> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_notifications", (page, headerHandler) -> (swagger).get_characters_character_id_notifications(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_online
         */
        public Property<R_get_characters_character_id_online> get_characters_character_id_online(int character_id) {
            Property<R_get_characters_character_id_online> ret = get_characters_character_id_online_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_online_holder)
                {
                    ret = get_characters_character_id_online_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_online> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_online", (h->swagger.get_characters_character_id_online(character_id,h)), item -> {
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
         * @see get_characters_character_id_orders
         */
        public ObservableMap<Long, R_get_characters_character_id_orders> get_characters_character_id_orders(int character_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_portrait
         */
        public Property<R_get_characters_character_id_portrait> get_characters_character_id_portrait(int character_id) {
            Property<R_get_characters_character_id_portrait> ret = get_characters_character_id_portrait_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_portrait_holder)
                {
                    ret = get_characters_character_id_portrait_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_portrait> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_portrait", (h->swagger.get_characters_character_id_portrait(character_id,h)), item -> {
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
         * @see get_characters_character_id_roles
         */
        public Property<R_get_characters_character_id_roles> get_characters_character_id_roles(int character_id) {
            Property<R_get_characters_character_id_roles> ret = get_characters_character_id_roles_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_roles_holder)
                {
                    ret = get_characters_character_id_roles_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_roles> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_roles", (h->swagger.get_characters_character_id_roles(character_id,h)), item -> {
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
         * @see get_characters_character_id_skillqueue
         */
        public ObservableList<R_get_characters_character_id_skillqueue> get_characters_character_id_skillqueue(int character_id) {
            ObservableList<R_get_characters_character_id_skillqueue> ret = get_characters_character_id_skillqueue_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_skillqueue_holder)
                {
                    ret = get_characters_character_id_skillqueue_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_skillqueue> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_skillqueue", (page, headerHandler) -> (swagger).get_characters_character_id_skillqueue(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_stats
         */
        public ObservableList<R_get_characters_character_id_stats> get_characters_character_id_stats(int character_id) {
            ObservableList<R_get_characters_character_id_stats> ret = get_characters_character_id_stats_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_stats_holder)
                {
                    ret = get_characters_character_id_stats_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<R_get_characters_character_id_stats> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_stats", (page, headerHandler) -> (swagger).get_characters_character_id_stats(character_id, headerHandler), arr -> {
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
         * @see get_characters_character_id_assets
         */
        public ObservableList<M_get_assets_8> get_characters_character_id_assets(int character_id) {
            ObservableList<M_get_assets_8> ret = get_characters_character_id_assets_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_assets_holder)
                {
                    ret = get_characters_character_id_assets_holder.get(character_id);
                    if (ret == null) {
                        ObservableList<M_get_assets_8> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_characters_character_id_assets", (page, headerHandler) -> (swagger).get_characters_character_id_assets(character_id, page, headerHandler), arr -> {
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
         * @see get_characters_character_id_calendar_event_id
         */
        public Property<R_get_characters_character_id_calendar_event_id> get_characters_character_id_calendar_event_id(int character_id, int event_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_clones
         */
        public Property<R_get_characters_character_id_clones> get_characters_character_id_clones(int character_id) {
            Property<R_get_characters_character_id_clones> ret = get_characters_character_id_clones_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_clones_holder)
                {
                    ret = get_characters_character_id_clones_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_clones> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_clones", (h->swagger.get_characters_character_id_clones(character_id,h)), item -> {
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
         * @see get_characters_character_id_mail_labels
         */
        public Property<R_get_characters_character_id_mail_labels> get_characters_character_id_mail_labels(int character_id) {
            Property<R_get_characters_character_id_mail_labels> ret = get_characters_character_id_mail_labels_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_mail_labels_holder)
                {
                    ret = get_characters_character_id_mail_labels_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_mail_labels> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_mail_labels", (h->swagger.get_characters_character_id_mail_labels(character_id,h)), item -> {
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
         * @see get_characters_character_id_planets_planet_id
         */
        public Property<R_get_characters_character_id_planets_planet_id> get_characters_character_id_planets_planet_id(int character_id, int planet_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id_search
         */
        public Property<R_get_characters_character_id_search> get_characters_character_id_search(String[] categories, int character_id, String search, Boolean strict) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_characters_character_id
         */
        public Property<R_get_characters_character_id> get_characters_character_id(int character_id) {
            Property<R_get_characters_character_id> ret = get_characters_character_id_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_holder)
                {
                    ret = get_characters_character_id_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id", (h->swagger.get_characters_character_id(character_id,h)), item -> {
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
         * @see get_characters_character_id_skills
         */
        public Property<R_get_characters_character_id_skills> get_characters_character_id_skills(int character_id) {
            Property<R_get_characters_character_id_skills> ret = get_characters_character_id_skills_holder.get(character_id);
            if (ret == null) {
                synchronized (get_characters_character_id_skills_holder)
                {
                    ret = get_characters_character_id_skills_holder.get(character_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_characters_character_id_skills> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_characters_character_id_skills", (h->swagger.get_characters_character_id_skills(character_id,h)), item -> {
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
         * @see get_characters_character_id_wallet_journal
         */
        public ObservableMap<Long, M_get_journal_13> get_characters_character_id_wallet_journal(int character_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }
    }

    public class Corporation {
        private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_extractions>> get_corporation_corporation_id_mining_extractions_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporation_corporation_id_mining_observers>> get_corporation_corporation_id_mining_observers_holder = new HashMap<>();

        /**
         * @see get_corporation_corporation_id_mining_extractions
         */
        public ObservableList<R_get_corporation_corporation_id_mining_extractions> get_corporation_corporation_id_mining_extractions(int corporation_id) {
            ObservableList<R_get_corporation_corporation_id_mining_extractions> ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporation_corporation_id_mining_extractions_holder)
                {
                    ret = get_corporation_corporation_id_mining_extractions_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporation_corporation_id_mining_extractions> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporation_corporation_id_mining_extractions", (page, headerHandler) -> (swagger).get_corporation_corporation_id_mining_extractions(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporation_corporation_id_mining_observers
         */
        public ObservableList<R_get_corporation_corporation_id_mining_observers> get_corporation_corporation_id_mining_observers(int corporation_id) {
            ObservableList<R_get_corporation_corporation_id_mining_observers> ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporation_corporation_id_mining_observers_holder)
                {
                    ret = get_corporation_corporation_id_mining_observers_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporation_corporation_id_mining_observers> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporation_corporation_id_mining_observers", (page, headerHandler) -> (swagger).get_corporation_corporation_id_mining_observers(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporation_corporation_id_mining_observers_observer_id
         */
        public ObservableList<R_get_corporation_corporation_id_mining_observers_observer_id> get_corporation_corporation_id_mining_observers_observer_id(int corporation_id, long observer_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }
    }

    public class Corporations {
        private ObservableList<Integer> get_corporations_npccorps_holder;
        private final Map<Integer, ObservableList<M_get_bookmarks_9>> get_corporations_corporation_id_bookmarks_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_bookmarks_folders>> get_corporations_corporation_id_bookmarks_folders_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contacts_labels_2>> get_corporations_corporation_id_contacts_labels_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_contracts_22>> get_corporations_corporation_id_contracts_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_facilities>> get_corporations_corporation_id_facilities_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_holder = new HashMap<>();
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
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_titles>> get_corporations_corporation_id_titles_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_wallets>> get_corporations_corporation_id_wallets_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_alliancehistory>> get_corporations_corporation_id_alliancehistory_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_contacts>> get_corporations_corporation_id_contacts_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_containers_logs>> get_corporations_corporation_id_containers_logs_holder = new HashMap<>();
        private final Map<Integer, ObservableList<R_get_corporations_corporation_id_structures>> get_corporations_corporation_id_structures_holder = new HashMap<>();
        private final Map<Integer, ObservableList<M_get_assets_8>> get_corporations_corporation_id_assets_holder = new HashMap<>();
        private final Map<Integer, ObservableList<Integer>> get_corporations_corporation_id_members_holder = new HashMap<>();
        private final Map<Integer, Property<R_get_corporations_corporation_id>> get_corporations_corporation_id_holder = new HashMap<>();

        /**
         * @see get_corporations_npccorps
         */
        public ObservableList<Integer> get_corporations_npccorps() {
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
         * @see get_corporations_corporation_id_bookmarks
         */
        public ObservableList<M_get_bookmarks_9> get_corporations_corporation_id_bookmarks(int corporation_id) {
            ObservableList<M_get_bookmarks_9> ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_bookmarks_holder)
                {
                    ret = get_corporations_corporation_id_bookmarks_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_bookmarks_9> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_bookmarks", (page, headerHandler) -> (swagger).get_corporations_corporation_id_bookmarks(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_bookmarks_folders
         */
        public ObservableList<R_get_corporations_corporation_id_bookmarks_folders> get_corporations_corporation_id_bookmarks_folders(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_bookmarks_folders> ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_bookmarks_folders_holder)
                {
                    ret = get_corporations_corporation_id_bookmarks_folders_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_bookmarks_folders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_bookmarks_folders", (page, headerHandler) -> (swagger).get_corporations_corporation_id_bookmarks_folders(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_contacts_labels
         */
        public ObservableList<M_get_contacts_labels_2> get_corporations_corporation_id_contacts_labels(int corporation_id) {
            ObservableList<M_get_contacts_labels_2> ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contacts_labels_holder)
                {
                    ret = get_corporations_corporation_id_contacts_labels_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_contacts_labels_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_contacts_labels", (page, headerHandler) -> (swagger).get_corporations_corporation_id_contacts_labels(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_contracts
         */
        public ObservableList<M_get_contracts_22> get_corporations_corporation_id_contracts(int corporation_id) {
            ObservableList<M_get_contracts_22> ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contracts_holder)
                {
                    ret = get_corporations_corporation_id_contracts_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_contracts_22> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_contracts", (page, headerHandler) -> (swagger).get_corporations_corporation_id_contracts(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_contracts_contract_id_bids
         */
        public ObservableMap<Integer, M_get_contracts_contract_bids_4> get_corporations_corporation_id_contracts_contract_id_bids(int contract_id, int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_contracts_contract_id_items
         */
        public ObservableMap<Long, M_get_contracts_contract_items_6> get_corporations_corporation_id_contracts_contract_id_items(int contract_id, int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_customs_offices
         */
        public ObservableMap<Long, R_get_corporations_corporation_id_customs_offices> get_corporations_corporation_id_customs_offices(int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_divisions
         */
        public Property<R_get_corporations_corporation_id_divisions> get_corporations_corporation_id_divisions(int corporation_id) {
            Property<R_get_corporations_corporation_id_divisions> ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_divisions_holder)
                {
                    ret = get_corporations_corporation_id_divisions_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_divisions> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_corporations_corporation_id_divisions", (h->swagger.get_corporations_corporation_id_divisions(corporation_id,h)), item -> {
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
         * @see get_corporations_corporation_id_facilities
         */
        public ObservableList<R_get_corporations_corporation_id_facilities> get_corporations_corporation_id_facilities(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_facilities> ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_facilities_holder)
                {
                    ret = get_corporations_corporation_id_facilities_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_facilities> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_facilities", (page, headerHandler) -> (swagger).get_corporations_corporation_id_facilities(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_fw_stats
         */
        public Property<R_get_corporations_corporation_id_fw_stats> get_corporations_corporation_id_fw_stats(int corporation_id) {
            Property<R_get_corporations_corporation_id_fw_stats> ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_fw_stats_holder)
                {
                    ret = get_corporations_corporation_id_fw_stats_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_corporations_corporation_id_fw_stats", (h->swagger.get_corporations_corporation_id_fw_stats(corporation_id,h)), item -> {
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
         * @see get_corporations_corporation_id_icons
         */
        public Property<R_get_corporations_corporation_id_icons> get_corporations_corporation_id_icons(int corporation_id) {
            Property<R_get_corporations_corporation_id_icons> ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_icons_holder)
                {
                    ret = get_corporations_corporation_id_icons_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id_icons> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_corporations_corporation_id_icons", (h->swagger.get_corporations_corporation_id_icons(corporation_id,h)), item -> {
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
         * @see get_corporations_corporation_id_industry_jobs
         */
        public ObservableMap<Integer, R_get_corporations_corporation_id_industry_jobs> get_corporations_corporation_id_industry_jobs(int corporation_id, Boolean include_completed) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_killmails_recent
         */
        public ObservableList<M_get_killmails_2> get_corporations_corporation_id_killmails_recent(int corporation_id) {
            ObservableList<M_get_killmails_2> ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_killmails_recent_holder)
                {
                    ret = get_corporations_corporation_id_killmails_recent_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_killmails_recent", (page, headerHandler) -> (swagger).get_corporations_corporation_id_killmails_recent(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_medals
         */
        public ObservableList<R_get_corporations_corporation_id_medals> get_corporations_corporation_id_medals(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_medals> ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_medals_holder)
                {
                    ret = get_corporations_corporation_id_medals_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_medals> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_medals", (page, headerHandler) -> (swagger).get_corporations_corporation_id_medals(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_medals_issued
         */
        public ObservableList<R_get_corporations_corporation_id_medals_issued> get_corporations_corporation_id_medals_issued(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_medals_issued> ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_medals_issued_holder)
                {
                    ret = get_corporations_corporation_id_medals_issued_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_medals_issued> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_medals_issued", (page, headerHandler) -> (swagger).get_corporations_corporation_id_medals_issued(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_members_limit
         */
        public Property<Integer> get_corporations_corporation_id_members_limit(int corporation_id) {
            Property<Integer> ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_limit_holder)
                {
                    ret = get_corporations_corporation_id_members_limit_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<Integer> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_corporations_corporation_id_members_limit", (h->swagger.get_corporations_corporation_id_members_limit(corporation_id,h)), item -> {
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
         * @see get_corporations_corporation_id_members_titles
         */
        public ObservableList<R_get_corporations_corporation_id_members_titles> get_corporations_corporation_id_members_titles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_members_titles> ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_titles_holder)
                {
                    ret = get_corporations_corporation_id_members_titles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_members_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_members_titles", (page, headerHandler) -> (swagger).get_corporations_corporation_id_members_titles(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_membertracking
         */
        public ObservableList<R_get_corporations_corporation_id_membertracking> get_corporations_corporation_id_membertracking(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_membertracking> ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_membertracking_holder)
                {
                    ret = get_corporations_corporation_id_membertracking_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_membertracking> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_membertracking", (page, headerHandler) -> (swagger).get_corporations_corporation_id_membertracking(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_roles
         */
        public ObservableList<R_get_corporations_corporation_id_roles> get_corporations_corporation_id_roles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_roles> ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_roles_holder)
                {
                    ret = get_corporations_corporation_id_roles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_roles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_roles", (page, headerHandler) -> (swagger).get_corporations_corporation_id_roles(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_roles_history
         */
        public ObservableList<R_get_corporations_corporation_id_roles_history> get_corporations_corporation_id_roles_history(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_roles_history> ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_roles_history_holder)
                {
                    ret = get_corporations_corporation_id_roles_history_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_roles_history> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_roles_history", (page, headerHandler) -> (swagger).get_corporations_corporation_id_roles_history(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_shareholders
         */
        public ObservableList<R_get_corporations_corporation_id_shareholders> get_corporations_corporation_id_shareholders(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_shareholders> ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_shareholders_holder)
                {
                    ret = get_corporations_corporation_id_shareholders_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_shareholders> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_shareholders", (page, headerHandler) -> (swagger).get_corporations_corporation_id_shareholders(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_standings
         */
        public ObservableList<M_get_standings_3> get_corporations_corporation_id_standings(int corporation_id) {
            ObservableList<M_get_standings_3> ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_standings_holder)
                {
                    ret = get_corporations_corporation_id_standings_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_standings_3> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_standings", (page, headerHandler) -> (swagger).get_corporations_corporation_id_standings(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_starbases
         */
        public ObservableMap<Long, R_get_corporations_corporation_id_starbases> get_corporations_corporation_id_starbases(int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_starbases_starbase_id
         */
        public Property<R_get_corporations_corporation_id_starbases_starbase_id> get_corporations_corporation_id_starbases_starbase_id(int corporation_id, long starbase_id, int system_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_titles
         */
        public ObservableList<R_get_corporations_corporation_id_titles> get_corporations_corporation_id_titles(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_titles> ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_titles_holder)
                {
                    ret = get_corporations_corporation_id_titles_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_titles> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_titles", (page, headerHandler) -> (swagger).get_corporations_corporation_id_titles(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_wallets
         */
        public ObservableList<R_get_corporations_corporation_id_wallets> get_corporations_corporation_id_wallets(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_wallets> ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_wallets_holder)
                {
                    ret = get_corporations_corporation_id_wallets_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_wallets> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_wallets", (page, headerHandler) -> (swagger).get_corporations_corporation_id_wallets(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_wallets_division_transactions
         */
        public ObservableMap<Long, R_get_corporations_corporation_id_wallets_division_transactions> get_corporations_corporation_id_wallets_division_transactions(int corporation_id, int division, Long from_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_alliancehistory
         */
        public ObservableList<R_get_corporations_corporation_id_alliancehistory> get_corporations_corporation_id_alliancehistory(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_alliancehistory> ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_alliancehistory_holder)
                {
                    ret = get_corporations_corporation_id_alliancehistory_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_alliancehistory> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_alliancehistory", (page, headerHandler) -> (swagger).get_corporations_corporation_id_alliancehistory(corporation_id, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_blueprints
         */
        public ObservableMap<Long, M_get_blueprints_8> get_corporations_corporation_id_blueprints(int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_contacts
         */
        public ObservableList<R_get_corporations_corporation_id_contacts> get_corporations_corporation_id_contacts(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_contacts> ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_contacts_holder)
                {
                    ret = get_corporations_corporation_id_contacts_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_contacts> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_contacts", (page, headerHandler) -> (swagger).get_corporations_corporation_id_contacts(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_containers_logs
         */
        public ObservableList<R_get_corporations_corporation_id_containers_logs> get_corporations_corporation_id_containers_logs(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_containers_logs> ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_containers_logs_holder)
                {
                    ret = get_corporations_corporation_id_containers_logs_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_containers_logs> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_containers_logs", (page, headerHandler) -> (swagger).get_corporations_corporation_id_containers_logs(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_orders_history
         */
        public ObservableMap<Long, R_get_corporations_corporation_id_orders_history> get_corporations_corporation_id_orders_history(int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_structures
         */
        public ObservableList<R_get_corporations_corporation_id_structures> get_corporations_corporation_id_structures(int corporation_id) {
            ObservableList<R_get_corporations_corporation_id_structures> ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_structures_holder)
                {
                    ret = get_corporations_corporation_id_structures_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_corporations_corporation_id_structures> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_structures", (page, headerHandler) -> (swagger).get_corporations_corporation_id_structures(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_assets
         */
        public ObservableList<M_get_assets_8> get_corporations_corporation_id_assets(int corporation_id) {
            ObservableList<M_get_assets_8> ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_assets_holder)
                {
                    ret = get_corporations_corporation_id_assets_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<M_get_assets_8> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_assets", (page, headerHandler) -> (swagger).get_corporations_corporation_id_assets(corporation_id, page, headerHandler), arr -> {
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
         * @see get_corporations_corporation_id_members
         */
        public ObservableList<Integer> get_corporations_corporation_id_members(int corporation_id) {
            ObservableList<Integer> ret = get_corporations_corporation_id_members_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_members_holder)
                {
                    ret = get_corporations_corporation_id_members_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_corporations_corporation_id_members", (page, headerHandler) -> IntStream.of((swagger).get_corporations_corporation_id_members(corporation_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
         * @see get_corporations_corporation_id_orders
         */
        public ObservableMap<Long, R_get_corporations_corporation_id_orders> get_corporations_corporation_id_orders(int corporation_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id_wallets_division_journal
         */
        public ObservableMap<Long, M_get_journal_13> get_corporations_corporation_id_wallets_division_journal(int corporation_id, int division) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_corporations_corporation_id
         */
        public Property<R_get_corporations_corporation_id> get_corporations_corporation_id(int corporation_id) {
            Property<R_get_corporations_corporation_id> ret = get_corporations_corporation_id_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_corporations_corporation_id_holder)
                {
                    ret = get_corporations_corporation_id_holder.get(corporation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_corporations_corporation_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_corporations_corporation_id", (h->swagger.get_corporations_corporation_id(corporation_id,h)), item -> {
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
        private ObservableList<Integer> get_dogma_effects_holder;
        private final Map<Integer, Property<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_holder = new HashMap<>();

        /**
         * @see get_dogma_attributes
         */
        public ObservableList<Integer> get_dogma_attributes() {
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
         * @see get_dogma_attributes_attribute_id
         */
        public Property<R_get_dogma_attributes_attribute_id> get_dogma_attributes_attribute_id(int attribute_id) {
            Property<R_get_dogma_attributes_attribute_id> ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
            if (ret == null) {
                synchronized (get_dogma_attributes_attribute_id_holder)
                {
                    ret = get_dogma_attributes_attribute_id_holder.get(attribute_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_dogma_attributes_attribute_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_dogma_attributes_attribute_id", (h->swagger.get_dogma_attributes_attribute_id(attribute_id,h)), item -> {
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
         * @see get_dogma_dynamic_items_type_id_item_id
         */
        public Property<R_get_dogma_dynamic_items_type_id_item_id> get_dogma_dynamic_items_type_id_item_id(long item_id, int type_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_dogma_effects
         */
        public ObservableList<Integer> get_dogma_effects() {
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
         * @see get_dogma_effects_effect_id
         */
        public Property<R_get_dogma_effects_effect_id> get_dogma_effects_effect_id(int effect_id) {
            Property<R_get_dogma_effects_effect_id> ret = get_dogma_effects_effect_id_holder.get(effect_id);
            if (ret == null) {
                synchronized (get_dogma_effects_effect_id_holder)
                {
                    ret = get_dogma_effects_effect_id_holder.get(effect_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_dogma_effects_effect_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_dogma_effects_effect_id", (h->swagger.get_dogma_effects_effect_id(effect_id,h)), item -> {
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
         * @see get_fleets_fleet_id
         */
        public Property<R_get_fleets_fleet_id> get_fleets_fleet_id(long fleet_id) {
            Property<R_get_fleets_fleet_id> ret = get_fleets_fleet_id_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_holder)
                {
                    ret = get_fleets_fleet_id_holder.get(fleet_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_fleets_fleet_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_fleets_fleet_id", (h->swagger.get_fleets_fleet_id(fleet_id,h)), item -> {
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
         * @see get_fleets_fleet_id_members
         */
        public ObservableList<R_get_fleets_fleet_id_members> get_fleets_fleet_id_members(long fleet_id) {
            ObservableList<R_get_fleets_fleet_id_members> ret = get_fleets_fleet_id_members_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_members_holder)
                {
                    ret = get_fleets_fleet_id_members_holder.get(fleet_id);
                    if (ret == null) {
                        ObservableList<R_get_fleets_fleet_id_members> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_fleets_fleet_id_members", (page, headerHandler) -> (swagger).get_fleets_fleet_id_members(fleet_id, headerHandler), arr -> {
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
         * @see get_fleets_fleet_id_wings
         */
        public ObservableList<R_get_fleets_fleet_id_wings> get_fleets_fleet_id_wings(long fleet_id) {
            ObservableList<R_get_fleets_fleet_id_wings> ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
            if (ret == null) {
                synchronized (get_fleets_fleet_id_wings_holder)
                {
                    ret = get_fleets_fleet_id_wings_holder.get(fleet_id);
                    if (ret == null) {
                        ObservableList<R_get_fleets_fleet_id_wings> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_fleets_fleet_id_wings", (page, headerHandler) -> (swagger).get_fleets_fleet_id_wings(fleet_id, headerHandler), arr -> {
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
         * @see get_fw_leaderboards
         */
        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards() {
            if (get_fw_leaderboards_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_holder == null) {
                        get_fw_leaderboards_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_holder;
                        addFetchCacheObject("get_fw_leaderboards", (m->swagger.get_fw_leaderboards(m)), item -> {
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
         * @see get_fw_leaderboards_characters
         */
        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards_characters() {
            if (get_fw_leaderboards_characters_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_characters_holder == null) {
                        get_fw_leaderboards_characters_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_characters_holder;
                        addFetchCacheObject("get_fw_leaderboards_characters", (m->swagger.get_fw_leaderboards_characters(m)), item -> {
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
         * @see get_fw_leaderboards_corporations
         */
        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations() {
            if (get_fw_leaderboards_corporations_holder == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_corporations_holder == null) {
                        get_fw_leaderboards_corporations_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<M_get_fw_leaderboards_2> finalContainer = get_fw_leaderboards_corporations_holder;
                        addFetchCacheObject("get_fw_leaderboards_corporations", (m->swagger.get_fw_leaderboards_corporations(m)), item -> {
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
         * @see get_fw_stats
         */
        public ObservableList<R_get_fw_stats> get_fw_stats() {
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
         * @see get_fw_wars
         */
        public ObservableList<R_get_fw_wars> get_fw_wars() {
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
         * @see get_fw_systems
         */
        public ObservableList<R_get_fw_systems> get_fw_systems() {
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
         * @see get_incursions
         */
        public ObservableList<R_get_incursions> get_incursions() {
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
         * @see get_industry_facilities
         */
        public ObservableList<R_get_industry_facilities> get_industry_facilities() {
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
         * @see get_industry_systems
         */
        public ObservableList<R_get_industry_systems> get_industry_systems() {
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
         * @see get_insurance_prices
         */
        public ObservableList<R_get_insurance_prices> get_insurance_prices() {
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

        /**
         * @see get_killmails_killmail_id_killmail_hash
         */
        public Property<R_get_killmails_killmail_id_killmail_hash> get_killmails_killmail_id_killmail_hash(String killmail_hash, int killmail_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }
    }

    public class Loyalty {
        private final Map<Integer, ObservableList<R_get_loyalty_stores_corporation_id_offers>> get_loyalty_stores_corporation_id_offers_holder = new HashMap<>();

        /**
         * @see get_loyalty_stores_corporation_id_offers
         */
        public ObservableList<R_get_loyalty_stores_corporation_id_offers> get_loyalty_stores_corporation_id_offers(int corporation_id) {
            ObservableList<R_get_loyalty_stores_corporation_id_offers> ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
            if (ret == null) {
                synchronized (get_loyalty_stores_corporation_id_offers_holder)
                {
                    ret = get_loyalty_stores_corporation_id_offers_holder.get(corporation_id);
                    if (ret == null) {
                        ObservableList<R_get_loyalty_stores_corporation_id_offers> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_loyalty_stores_corporation_id_offers", (page, headerHandler) -> (swagger).get_loyalty_stores_corporation_id_offers(corporation_id, headerHandler), arr -> {
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
        private final Map<Integer, ObservableList<Integer>> get_markets_region_id_types_holder = new HashMap<>();

        /**
         * @see get_markets_groups
         */
        public ObservableList<Integer> get_markets_groups() {
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
         * @see get_markets_groups_market_group_id
         */
        public Property<R_get_markets_groups_market_group_id> get_markets_groups_market_group_id(int market_group_id) {
            Property<R_get_markets_groups_market_group_id> ret = get_markets_groups_market_group_id_holder.get(market_group_id);
            if (ret == null) {
                synchronized (get_markets_groups_market_group_id_holder)
                {
                    ret = get_markets_groups_market_group_id_holder.get(market_group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_markets_groups_market_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_markets_groups_market_group_id", (h->swagger.get_markets_groups_market_group_id(market_group_id,h)), item -> {
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
         * @see get_markets_prices
         */
        public ObservableList<R_get_markets_prices> get_markets_prices() {
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
         * @see get_markets_structures_structure_id
         */
        public ObservableList<R_get_markets_structures_structure_id> get_markets_structures_structure_id(long structure_id) {
            ObservableList<R_get_markets_structures_structure_id> ret = get_markets_structures_structure_id_holder.get(structure_id);
            if (ret == null) {
                synchronized (get_markets_structures_structure_id_holder)
                {
                    ret = get_markets_structures_structure_id_holder.get(structure_id);
                    if (ret == null) {
                        ObservableList<R_get_markets_structures_structure_id> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_markets_structures_structure_id", (page, headerHandler) -> (swagger).get_markets_structures_structure_id(page, structure_id, headerHandler), arr -> {
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
         * @see get_markets_region_id_history
         */
        public ObservableList<R_get_markets_region_id_history> get_markets_region_id_history(int region_id, int type_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_markets_region_id_orders
         */
        public ObservableList<R_get_markets_region_id_orders> get_markets_region_id_orders(Swagger.order_type order_type, int region_id, Integer type_id) {
            // TODO 
            throw new UnsupportedOperationException();
        }

        /**
         * @see get_markets_region_id_types
         */
        public ObservableList<Integer> get_markets_region_id_types(int region_id) {
            ObservableList<Integer> ret = get_markets_region_id_types_holder.get(region_id);
            if (ret == null) {
                synchronized (get_markets_region_id_types_holder)
                {
                    ret = get_markets_region_id_types_holder.get(region_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_markets_region_id_types", (page, headerHandler) -> IntStream.of((swagger).get_markets_region_id_types(page, region_id, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
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
         * @see get_opportunities_groups
         */
        public ObservableList<Integer> get_opportunities_groups() {
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
         * @see get_opportunities_groups_group_id
         */
        public Property<R_get_opportunities_groups_group_id> get_opportunities_groups_group_id(int group_id) {
            Property<R_get_opportunities_groups_group_id> ret = get_opportunities_groups_group_id_holder.get(group_id);
            if (ret == null) {
                synchronized (get_opportunities_groups_group_id_holder)
                {
                    ret = get_opportunities_groups_group_id_holder.get(group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_opportunities_groups_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_opportunities_groups_group_id", (h->swagger.get_opportunities_groups_group_id(group_id,h)), item -> {
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
         * @see get_opportunities_tasks
         */
        public ObservableList<Integer> get_opportunities_tasks() {
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
         * @see get_opportunities_tasks_task_id
         */
        public Property<R_get_opportunities_tasks_task_id> get_opportunities_tasks_task_id(int task_id) {
            Property<R_get_opportunities_tasks_task_id> ret = get_opportunities_tasks_task_id_holder.get(task_id);
            if (ret == null) {
                synchronized (get_opportunities_tasks_task_id_holder)
                {
                    ret = get_opportunities_tasks_task_id_holder.get(task_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_opportunities_tasks_task_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_opportunities_tasks_task_id", (h->swagger.get_opportunities_tasks_task_id(task_id,h)), item -> {
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

        /**
         * @see get_route_origin_destination
         */
        public ObservableList<Integer> get_route_origin_destination(int[] avoid, int[][] connections, int destination, Swagger.flag flag, int origin) {
            // TODO 
            throw new UnsupportedOperationException();
        }
    }

    public class Search {

        /**
         * @see get_search
         */
        public Property<R_get_search> get_search(String[] categories, String search, Boolean strict) {
            // TODO 
            throw new UnsupportedOperationException();
        }
    }

    public class Sovereignty {
        private ObservableMap<Integer, R_get_sovereignty_campaigns> get_sovereignty_campaigns_holder;
        private ObservableList<R_get_sovereignty_map> get_sovereignty_map_holder;
        private ObservableMap<Long, R_get_sovereignty_structures> get_sovereignty_structures_holder;

        /**
         * @see get_sovereignty_campaigns
         */
        public ObservableMap<Integer, R_get_sovereignty_campaigns> get_sovereignty_campaigns() {
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
         * @see get_sovereignty_map
         */
        public ObservableList<R_get_sovereignty_map> get_sovereignty_map() {
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
         * @see get_sovereignty_structures
         */
        public ObservableMap<Long, R_get_sovereignty_structures> get_sovereignty_structures() {
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
         * @see get_status
         */
        public Property<R_get_status> get_status() {
            if (get_status_holder == null) {
                synchronized (this)
                {
                    if (get_status_holder == null) {
                        get_status_holder = new SimpleObjectProperty<>();
                        SimpleObjectProperty<R_get_status> finalContainer = get_status_holder;
                        addFetchCacheObject("get_status", (m->swagger.get_status(m)), item -> {
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
         * @see get_universe_ancestries
         */
        public ObservableList<R_get_universe_ancestries> get_universe_ancestries() {
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
         * @see get_universe_asteroid_belts_asteroid_belt_id
         */
        public Property<R_get_universe_asteroid_belts_asteroid_belt_id> get_universe_asteroid_belts_asteroid_belt_id(int asteroid_belt_id) {
            Property<R_get_universe_asteroid_belts_asteroid_belt_id> ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
            if (ret == null) {
                synchronized (get_universe_asteroid_belts_asteroid_belt_id_holder)
                {
                    ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", (h->swagger.get_universe_asteroid_belts_asteroid_belt_id(asteroid_belt_id,h)), item -> {
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
         * @see get_universe_bloodlines
         */
        public ObservableList<R_get_universe_bloodlines> get_universe_bloodlines() {
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
         * @see get_universe_categories
         */
        public ObservableList<Integer> get_universe_categories() {
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
         * @see get_universe_categories_category_id
         */
        public Property<R_get_universe_categories_category_id> get_universe_categories_category_id(int category_id) {
            Property<R_get_universe_categories_category_id> ret = get_universe_categories_category_id_holder.get(category_id);
            if (ret == null) {
                synchronized (get_universe_categories_category_id_holder)
                {
                    ret = get_universe_categories_category_id_holder.get(category_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_categories_category_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_categories_category_id", (h->swagger.get_universe_categories_category_id(category_id,h)), item -> {
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
         * @see get_universe_constellations
         */
        public ObservableList<Integer> get_universe_constellations() {
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
         * @see get_universe_constellations_constellation_id
         */
        public Property<R_get_universe_constellations_constellation_id> get_universe_constellations_constellation_id(int constellation_id) {
            Property<R_get_universe_constellations_constellation_id> ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
            if (ret == null) {
                synchronized (get_universe_constellations_constellation_id_holder)
                {
                    ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_constellations_constellation_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_constellations_constellation_id", (h->swagger.get_universe_constellations_constellation_id(constellation_id,h)), item -> {
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
         * @see get_universe_graphics
         */
        public ObservableList<Integer> get_universe_graphics() {
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
         * @see get_universe_graphics_graphic_id
         */
        public Property<R_get_universe_graphics_graphic_id> get_universe_graphics_graphic_id(int graphic_id) {
            Property<R_get_universe_graphics_graphic_id> ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
            if (ret == null) {
                synchronized (get_universe_graphics_graphic_id_holder)
                {
                    ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_graphics_graphic_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_graphics_graphic_id", (h->swagger.get_universe_graphics_graphic_id(graphic_id,h)), item -> {
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
         * @see get_universe_groups
         */
        public ObservableList<Integer> get_universe_groups() {
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
         * @see get_universe_groups_group_id
         */
        public Property<R_get_universe_groups_group_id> get_universe_groups_group_id(int group_id) {
            Property<R_get_universe_groups_group_id> ret = get_universe_groups_group_id_holder.get(group_id);
            if (ret == null) {
                synchronized (get_universe_groups_group_id_holder)
                {
                    ret = get_universe_groups_group_id_holder.get(group_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_groups_group_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_groups_group_id", (h->swagger.get_universe_groups_group_id(group_id,h)), item -> {
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
         * @see get_universe_moons_moon_id
         */
        public Property<R_get_universe_moons_moon_id> get_universe_moons_moon_id(int moon_id) {
            Property<R_get_universe_moons_moon_id> ret = get_universe_moons_moon_id_holder.get(moon_id);
            if (ret == null) {
                synchronized (get_universe_moons_moon_id_holder)
                {
                    ret = get_universe_moons_moon_id_holder.get(moon_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_moons_moon_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_moons_moon_id", (h->swagger.get_universe_moons_moon_id(moon_id,h)), item -> {
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
         * @see get_universe_planets_planet_id
         */
        public Property<R_get_universe_planets_planet_id> get_universe_planets_planet_id(int planet_id) {
            Property<R_get_universe_planets_planet_id> ret = get_universe_planets_planet_id_holder.get(planet_id);
            if (ret == null) {
                synchronized (get_universe_planets_planet_id_holder)
                {
                    ret = get_universe_planets_planet_id_holder.get(planet_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_planets_planet_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_planets_planet_id", (h->swagger.get_universe_planets_planet_id(planet_id,h)), item -> {
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
         * @see get_universe_races
         */
        public ObservableList<R_get_universe_races> get_universe_races() {
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
         * @see get_universe_regions
         */
        public ObservableList<Integer> get_universe_regions() {
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
         * @see get_universe_regions_region_id
         */
        public Property<R_get_universe_regions_region_id> get_universe_regions_region_id(int region_id) {
            Property<R_get_universe_regions_region_id> ret = get_universe_regions_region_id_holder.get(region_id);
            if (ret == null) {
                synchronized (get_universe_regions_region_id_holder)
                {
                    ret = get_universe_regions_region_id_holder.get(region_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_regions_region_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_regions_region_id", (h->swagger.get_universe_regions_region_id(region_id,h)), item -> {
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
         * @see get_universe_schematics_schematic_id
         */
        public Property<R_get_universe_schematics_schematic_id> get_universe_schematics_schematic_id(int schematic_id) {
            Property<R_get_universe_schematics_schematic_id> ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
            if (ret == null) {
                synchronized (get_universe_schematics_schematic_id_holder)
                {
                    ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_schematics_schematic_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_schematics_schematic_id", (h->swagger.get_universe_schematics_schematic_id(schematic_id,h)), item -> {
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
         * @see get_universe_stargates_stargate_id
         */
        public Property<R_get_universe_stargates_stargate_id> get_universe_stargates_stargate_id(int stargate_id) {
            Property<R_get_universe_stargates_stargate_id> ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
            if (ret == null) {
                synchronized (get_universe_stargates_stargate_id_holder)
                {
                    ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stargates_stargate_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_stargates_stargate_id", (h->swagger.get_universe_stargates_stargate_id(stargate_id,h)), item -> {
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
         * @see get_universe_stars_star_id
         */
        public Property<R_get_universe_stars_star_id> get_universe_stars_star_id(int star_id) {
            Property<R_get_universe_stars_star_id> ret = get_universe_stars_star_id_holder.get(star_id);
            if (ret == null) {
                synchronized (get_universe_stars_star_id_holder)
                {
                    ret = get_universe_stars_star_id_holder.get(star_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stars_star_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_stars_star_id", (h->swagger.get_universe_stars_star_id(star_id,h)), item -> {
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
         * @see get_universe_structures
         */
        public ObservableList<Long> get_universe_structures() {
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
         * @see get_universe_system_jumps
         */
        public ObservableList<R_get_universe_system_jumps> get_universe_system_jumps() {
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
         * @see get_universe_systems
         */
        public ObservableList<Integer> get_universe_systems() {
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
         * @see get_universe_types
         */
        public ObservableList<Integer> get_universe_types() {
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
         * @see get_universe_factions
         */
        public ObservableList<R_get_universe_factions> get_universe_factions() {
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
         * @see get_universe_stations_station_id
         */
        public Property<R_get_universe_stations_station_id> get_universe_stations_station_id(int station_id) {
            Property<R_get_universe_stations_station_id> ret = get_universe_stations_station_id_holder.get(station_id);
            if (ret == null) {
                synchronized (get_universe_stations_station_id_holder)
                {
                    ret = get_universe_stations_station_id_holder.get(station_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_stations_station_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_stations_station_id", (h->swagger.get_universe_stations_station_id(station_id,h)), item -> {
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
         * @see get_universe_structures_structure_id
         */
        public Property<R_get_universe_structures_structure_id> get_universe_structures_structure_id(long structure_id) {
            Property<R_get_universe_structures_structure_id> ret = get_universe_structures_structure_id_holder.get(structure_id);
            if (ret == null) {
                synchronized (get_universe_structures_structure_id_holder)
                {
                    ret = get_universe_structures_structure_id_holder.get(structure_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_structures_structure_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_structures_structure_id", (h->swagger.get_universe_structures_structure_id(structure_id,h)), item -> {
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
         * @see get_universe_system_kills
         */
        public ObservableList<R_get_universe_system_kills> get_universe_system_kills() {
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
         * @see get_universe_types_type_id
         */
        public Property<R_get_universe_types_type_id> get_universe_types_type_id(int type_id) {
            Property<R_get_universe_types_type_id> ret = get_universe_types_type_id_holder.get(type_id);
            if (ret == null) {
                synchronized (get_universe_types_type_id_holder)
                {
                    ret = get_universe_types_type_id_holder.get(type_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_types_type_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_types_type_id", (h->swagger.get_universe_types_type_id(type_id,h)), item -> {
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
         * @see get_universe_systems_system_id
         */
        public Property<R_get_universe_systems_system_id> get_universe_systems_system_id(int system_id) {
            Property<R_get_universe_systems_system_id> ret = get_universe_systems_system_id_holder.get(system_id);
            if (ret == null) {
                synchronized (get_universe_systems_system_id_holder)
                {
                    ret = get_universe_systems_system_id_holder.get(system_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_universe_systems_system_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_universe_systems_system_id", (h->swagger.get_universe_systems_system_id(system_id,h)), item -> {
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
         * @see get_wars
         */
        public ObservableList<Integer> get_wars(Integer max_war_id) {
            ObservableList<Integer> ret = get_wars_holder.get(max_war_id);
            if (ret == null) {
                synchronized (get_wars_holder)
                {
                    ret = get_wars_holder.get(max_war_id);
                    if (ret == null) {
                        ObservableList<Integer> finalret = FXCollections.observableArrayList();
                        ret = finalret;
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
         * @see get_wars_war_id
         */
        public Property<R_get_wars_war_id> get_wars_war_id(int war_id) {
            Property<R_get_wars_war_id> ret = get_wars_war_id_holder.get(war_id);
            if (ret == null) {
                synchronized (get_wars_war_id_holder)
                {
                    ret = get_wars_war_id_holder.get(war_id);
                    if (ret == null) {
                        SimpleObjectProperty<R_get_wars_war_id> finalret = new SimpleObjectProperty<>();
                        ret = finalret;
                        addFetchCacheObject("get_wars_war_id", (h->swagger.get_wars_war_id(war_id,h)), item -> {
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
         * @see get_wars_war_id_killmails
         */
        public ObservableList<M_get_killmails_2> get_wars_war_id_killmails(int war_id) {
            ObservableList<M_get_killmails_2> ret = get_wars_war_id_killmails_holder.get(war_id);
            if (ret == null) {
                synchronized (get_wars_war_id_killmails_holder)
                {
                    ret = get_wars_war_id_killmails_holder.get(war_id);
                    if (ret == null) {
                        ObservableList<M_get_killmails_2> finalret = FXCollections.observableArrayList();
                        ret = finalret;
                        addFetchCacheArray("get_wars_war_id_killmails", (page, headerHandler) -> (swagger).get_wars_war_id_killmails(page, war_id, headerHandler), arr -> {
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
