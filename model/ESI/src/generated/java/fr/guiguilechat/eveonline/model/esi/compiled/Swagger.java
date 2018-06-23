package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_assets_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_blueprints_8;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_characters_character_mail_recipients_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_bids_4;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_journal_13;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_standings_3;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_locations_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_names_2;
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
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fittings_items;
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
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_outposts_outpost_id;
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
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_characters_affiliation;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_characters_character_id_fittings_created;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_fleets_fleet_id_wings_created;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_fleets_fleet_id_wings_wing_id_squads_created;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_ids;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_names;

public interface Swagger {
    public static final String[] SCOPES = new String[] {"esi-characters.write_contacts.v1", "esi-skills.read_skills.v1", "esi-characters.read_fatigue.v1", "esi-corporations.read_divisions.v1", "esi-corporations.read_corporation_membership.v1", "esi-bookmarks.read_character_bookmarks.v1", "esi-assets.read_corporation_assets.v1", "esi-fittings.read_fittings.v1", "esi-contracts.read_corporation_contracts.v1", "esi-fleets.write_fleet.v1", "esi-ui.write_waypoint.v1", "esi-industry.read_character_jobs.v1", "esi-bookmarks.read_corporation_bookmarks.v1", "esi-industry.read_character_mining.v1", "esi-clones.read_clones.v1", "esi-characters.read_agents_research.v1", "esi-calendar.respond_calendar_events.v1", "esi-location.read_online.v1", "esi-mail.read_mail.v1", "esi-characterstats.read.v1", "esi-search.search_structures.v1", "esi-corporations.read_contacts.v1", "esi-corporations.read_container_logs.v1", "esi-characters.read_contacts.v1", "esi-fittings.write_fittings.v1", "esi-markets.structure_markets.v1", "esi-wallet.read_corporation_wallets.v1", "esi-characters.read_corporation_roles.v1", "esi-wallet.read_character_wallet.v1", "esi-assets.read_assets.v1", "esi-killmails.read_killmails.v1", "esi-characters.read_medals.v1", "esi-location.read_ship_type.v1", "esi-skills.read_skillqueue.v1", "esi-contracts.read_character_contracts.v1", "esi-mail.send_mail.v1", "esi-alliances.read_contacts.v1", "esi-location.read_location.v1", "esi-ui.open_window.v1", "esi-fleets.read_fleet.v1", "esi-industry.read_corporation_mining.v1", "esi-corporations.read_blueprints.v1", "esi-calendar.read_calendar_events.v1", "esi-markets.read_character_orders.v1", "esi-markets.read_corporation_orders.v1", "esi-characters.read_notifications.v1", "esi-characters.read_standings.v1", "esi-characters.read_opportunities.v1", "esi-corporations.read_standings.v1", "esi-industry.read_corporation_jobs.v1", "esi-characters.read_fw_stats.v1", "esi-corporations.read_titles.v1", "esi-universe.read_structures.v1", "esi-corporations.track_members.v1", "esi-corporations.read_fw_stats.v1", "esi-characters.read_loyalty.v1", "esi-mail.organize_mail.v1", "esi-corporations.read_structures.v1", "esi-corporations.read_outposts.v1", "esi-corporations.read_starbases.v1", "esi-clones.read_implants.v1", "esi-killmails.read_corporation_killmails.v1", "esi-corporations.read_medals.v1", "esi-planets.manage_planets.v1", "esi-characters.read_titles.v1", "esi-corporations.read_facilities.v1", "esi-planets.read_customs_offices.v1", "esi-characters.read_blueprints.v1"};
    /**
     * the roles required for {@link #get_corporation_corporation_id_mining_extractions this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_EXTRACTIONS_ROLES = new String[] {"Structure_manager"};
    /**
     * the roles required for {@link #get_corporation_corporation_id_mining_observers this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_ROLES = new String[] {"Accountant"};
    /**
     * the roles required for {@link #get_corporation_corporation_id_mining_observers_observer_id this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_OBSERVER_ID_ROLES = new String[] {"Accountant"};
    /**
     * the roles required for {@link #post_corporations_corporation_id_assets_names this method}
     */
    public static final String[] POST_CORPORATIONS_CORPORATION_ID_ASSETS_NAMES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_customs_offices this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_CUSTOMS_OFFICES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_divisions this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_DIVISIONS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_facilities this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_FACILITIES_ROLES = new String[] {"Factory_Manager"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_industry_jobs this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES = new String[] {"FactoryManager"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_killmails_recent this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_KILLMAILS_RECENT_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_medals_issued this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEDALS_ISSUED_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_members_limit this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERS_LIMIT_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_members_titles this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERS_TITLES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_membertracking this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERTRACKING_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_orders_history this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ORDERS_HISTORY_ROLES = new String[] {"Accountant", "Trader"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_outposts this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_OUTPOSTS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_outposts_outpost_id this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_OUTPOSTS_OUTPOST_ID_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_roles_history this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ROLES_HISTORY_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_shareholders this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_SHAREHOLDERS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_starbases this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STARBASES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_starbases_starbase_id this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STARBASES_STARBASE_ID_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_titles this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_TITLES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_wallets this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_ROLES = new String[] {"Accountant", "Junior_Accountant"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_wallets_division_transactions this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_TRANSACTIONS_ROLES = new String[] {"Accountant", "Junior_Accountant"};
    /**
     * the roles required for {@link #post_corporations_corporation_id_assets_locations this method}
     */
    public static final String[] POST_CORPORATIONS_CORPORATION_ID_ASSETS_LOCATIONS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_blueprints this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_BLUEPRINTS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_containers_logs this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_CONTAINERS_LOGS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_orders this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ORDERS_ROLES = new String[] {"Accountant", "Trader"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_structures this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STRUCTURES_ROLES = new String[] {"StationManager"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_assets this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ASSETS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_corporation_id_wallets_division_journal this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_JOURNAL_ROLES = new String[] {"Accountant", "Junior_Accountant"};

    public String flatten(Object o);

    public String connectGet(String url, boolean connected, Map<String, List<String>> headerHandler);

    public String connectDel(String url, boolean connected, Map<String, List<String>> headerHandler);

    public String connectPost(String url, Map<String, Object> content, boolean connected, Map<String, List<String>> headerHandler);

    public String connectPut(String url, Map<String, Object> content, boolean connected, Map<String, List<String>> headerHandler);

    public<T> T convert(String line, Class<? extends T> clazz);

    /**
     * List all alliances
     * <p>
     * List all active player alliances<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default int[] get_alliances(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/alliances/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get alliance contact labels
     * <p>
     * Return custom labels for an alliance's contacts<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public default M_get_contacts_labels_2 [] get_alliances_alliance_id_contacts_labels(int alliance_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/contacts/labels/".replace("{alliance_id}", ""+alliance_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contacts_labels_2[].class));
    }

    /**
     * List alliance's corporations
     * <p>
     * List all current member corporations of an alliance<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public default int[] get_alliances_alliance_id_corporations(int alliance_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/corporations/".replace("{alliance_id}", ""+alliance_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get alliance icon
     * <p>
     * Get the icon urls for a alliance<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public default R_get_alliances_alliance_id_icons get_alliances_alliance_id_icons(int alliance_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/icons/".replace("{alliance_id}", ""+alliance_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id_icons.class));
    }

    /**
     * Character affiliation
     * <p>
     * Bulk lookup of character IDs to corporation, alliance and faction<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param characters
     *     The character IDs to fetch affiliations for. All characters must exist, or none will be returned.
     */
    public default R_post_characters_affiliation[] post_characters_affiliation(int[] characters, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/affiliation/");
        Map<String, Object> content = new HashMap<>();
        content.put("characters", characters);
        String fetched = connectPost(url, content, false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_characters_affiliation[].class));
    }

    /**
     * Get agents research
     * <p>
     * Return a list of agents research information for a character. The formula for finding the current research points with an agent is: currentPoints = remainderPoints + pointsPerDay * days(currentTime - researchStartDate)<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_agents_research[] get_characters_character_id_agents_research(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/agents_research/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_agents_research[].class));
    }

    /**
     * Get character asset names
     * <p>
     * Return names for a set of item ids, which you can get from character assets endpoint. Typically used for items that can customize names, like containers or ships.
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param item_ids
     *     A list of item ids
     */
    public default M_post_assets_names_2 [] post_characters_character_id_assets_names(int character_id, long[] item_ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/assets/names/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_names_2[].class));
    }

    /**
     * Get character attributes
     * <p>
     * Return attributes of a character<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_attributes get_characters_character_id_attributes(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/attributes/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_attributes.class));
    }

    /**
     * List calendar event summaries
     * <p>
     * Get 50 event summaries from the calendar. If no from_event ID is given, the resource will return the next 50 chronological event summaries from now. If a from_event ID is specified, it will return the next 50 chronological event summaries from after that event.<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param from_event
     *     The event ID to retrieve events from
     */
    public default R_get_characters_character_id_calendar[] get_characters_character_id_calendar(int character_id, Integer from_event, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/calendar/".replace("{character_id}", ""+character_id)+"?"+(from_event==null?"":"&from_event="+flatten(from_event)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar[].class));
    }

    /**
     * Get attendees
     * <p>
     * Get all invited attendees for a given event<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The id of the event requested
     */
    public default R_get_characters_character_id_calendar_event_id_attendees[] get_characters_character_id_calendar_event_id_attendees(int character_id, int event_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/calendar/{event_id}/attendees/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar_event_id_attendees[].class));
    }

    /**
     * Get contact labels
     * <p>
     * Return custom labels for a character's contacts<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default M_get_contacts_labels_2 [] get_characters_character_id_contacts_labels(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contacts/labels/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contacts_labels_2[].class));
    }

    /**
     * Get contracts
     * <p>
     * Returns contracts available to a character, only if the character is issuer, acceptor or assignee. Only returns contracts no older than 30 days, or if the status is "in_progress".<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_contracts_22 [] get_characters_character_id_contracts(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_22[].class));
    }

    /**
     * Get contract bids
     * <p>
     * Lists bids on a particular auction contract<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param contract_id
     *     ID of a contract
     */
    public default M_get_contracts_contract_bids_4 [] get_characters_character_id_contracts_contract_id_bids(int character_id, int contract_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/bids/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_bids_4[].class));
    }

    /**
     * Get contract items
     * <p>
     * Lists items of a particular contract<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param contract_id
     *     ID of a contract
     */
    public default M_get_contracts_contract_items_6 [] get_characters_character_id_contracts_contract_id_items(int character_id, int contract_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/items/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_items_6[].class));
    }

    /**
     * Get corporation history
     * <p>
     * Get a list of all the corporations a character has been a member of<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_corporationhistory[] get_characters_character_id_corporationhistory(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/corporationhistory/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_corporationhistory[].class));
    }

    /**
     * Get jump fatigue
     * <p>
     * Return a character's jump activation and fatigue information<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_fatigue get_characters_character_id_fatigue(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fatigue/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fatigue.class));
    }

    /**
     * Get fittings
     * <p>
     * Return fittings of a character<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_fittings[] get_characters_character_id_fittings(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fittings[].class));
    }

    /**
     * Create fitting
     * <p>
     * Save a new fitting for a character
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param description
     *     description string
     * @param items
     *     items array
     * @param name
     *     name string
     * @param ship_type_id
     *     ship_type_id integer
     */
    public default R_post_characters_character_id_fittings_created post_characters_character_id_fittings(int character_id, String description, R_get_characters_character_id_fittings_items[] items, String name, int ship_type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("description", description);
        content.put("items", items);
        content.put("name", name);
        content.put("ship_type_id", ship_type_id);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_characters_character_id_fittings_created.class));
    }

    /**
     * Delete fitting
     * <p>
     * Delete a fitting from a character
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param fitting_id
     *     ID for a fitting of this character
     */
    public default void delete_characters_character_id_fittings_fitting_id(int character_id, int fitting_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fittings/{fitting_id}/".replace("{character_id}", ""+character_id).replace("{fitting_id}", ""+fitting_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Get character fleet info
     * <p>
     * Return the fleet ID the character is in, if any.<br />
     * This route is cached for up to 60 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_fleet get_characters_character_id_fleet(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fleet/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fleet.class));
    }

    /**
     * Overview of a character involved in faction warfare
     * <p>
     * Statistical overview of a character involved in faction warfare<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_fw_stats get_characters_character_id_fw_stats(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fw/stats/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fw_stats.class));
    }

    /**
     * Get active implants
     * <p>
     * Return implants on the active clone of a character<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default int[] get_characters_character_id_implants(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/implants/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * List character industry jobs
     * <p>
     * List industry jobs placed by a character<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param include_completed
     *     Whether retrieve completed character industry jobs as well
     */
    public default R_get_characters_character_id_industry_jobs[] get_characters_character_id_industry_jobs(int character_id, Boolean include_completed, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/industry/jobs/".replace("{character_id}", ""+character_id)+"?"+(include_completed==null?"":"&include_completed="+flatten(include_completed)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_industry_jobs[].class));
    }

    /**
     * Get a character's recent kills and losses
     * <p>
     * Return a list of a character's kills and losses going back 90 days<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_killmails_2 [] get_characters_character_id_killmails_recent(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/killmails/recent/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_killmails_2[].class));
    }

    /**
     * Get character location
     * <p>
     * Information about the characters current location. Returns the current solar system id, and also the current station or structure ID if applicable.<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_location get_characters_character_id_location(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/location/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_location.class));
    }

    /**
     * Get loyalty points
     * <p>
     * Return a list of loyalty points for all corporations the character has worked for<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_loyalty_points[] get_characters_character_id_loyalty_points(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/loyalty/points/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_loyalty_points[].class));
    }

    /**
     * Return mail headers
     * <p>
     * Return the 50 most recent mail headers belonging to the character that match the query criteria. Queries can be filtered by label, and last_mail_id can be used to paginate backwards.<br />
     * This route is cached for up to 30 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param labels
     *     Fetch only mails that match one or more of the given labels
     * @param last_mail_id
     *     List only mail with an ID lower than the given ID, if present
     */
    public default R_get_characters_character_id_mail[] get_characters_character_id_mail(int character_id, int[] labels, Integer last_mail_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/".replace("{character_id}", ""+character_id)+"?"+(labels==null?"":"&labels="+flatten(labels))+(last_mail_id==null?"":"&last_mail_id="+flatten(last_mail_id)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail[].class));
    }

    /**
     * Send a new mail
     * <p>
     * Create and send a new mail
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param approved_cost
     *     approved_cost integer
     * @param body
     *     body string
     * @param recipients
     *     recipients array
     * @param subject
     *     subject string
     */
    public default int post_characters_character_id_mail(int character_id, long approved_cost, String body, M_get_characters_character_mail_recipients_2 [] recipients, String subject, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("approved_cost", approved_cost);
        content.put("body", body);
        content.put("recipients", recipients);
        content.put("subject", subject);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (int.class));
    }

    /**
     * Delete a mail label
     * <p>
     * Delete a mail label
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param label_id
     *     An EVE label id
     */
    public default void delete_characters_character_id_mail_labels_label_id(int character_id, int label_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/labels/{label_id}/".replace("{character_id}", ""+character_id).replace("{label_id}", ""+label_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Return mailing list subscriptions
     * <p>
     * Return all mailing lists that the character is subscribed to<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_mail_lists[] get_characters_character_id_mail_lists(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/lists/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_lists[].class));
    }

    /**
     * Return a mail
     * <p>
     * Return the contents of an EVE mail<br />
     * This route is cached for up to 30 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param mail_id
     *     An EVE mail ID
     */
    public default R_get_characters_character_id_mail_mail_id get_characters_character_id_mail_mail_id(int character_id, int mail_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_mail_id.class));
    }

    /**
     * Delete a mail
     * <p>
     * Delete a mail
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param mail_id
     *     An EVE mail ID
     */
    public default void delete_characters_character_id_mail_mail_id(int character_id, int mail_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Update metadata about a mail
     * <p>
     * Update metadata about a mail
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param labels
     *     Labels to assign to the mail. Pre-existing labels are unassigned.
     * @param read
     *     Whether the mail is flagged as read
     * @param mail_id
     *     An EVE mail ID
     */
    public default void put_characters_character_id_mail_mail_id(int character_id, int[] labels, boolean read, int mail_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        Map<String, Object> content = new HashMap<>();
        content.put("labels", labels);
        content.put("read", read);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Get medals
     * <p>
     * Return a list of medals the character has<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_medals[] get_characters_character_id_medals(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/medals/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_medals[].class));
    }

    /**
     * Character mining ledger
     * <p>
     * Paginated record of all mining done by a character for the past 30 days<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_characters_character_id_mining[] get_characters_character_id_mining(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mining/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mining[].class));
    }

    /**
     * Get new contact notifications
     * <p>
     * Return notifications about having been added to someone's contact list<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_notifications_contacts[] get_characters_character_id_notifications_contacts(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/notifications/contacts/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_notifications_contacts[].class));
    }

    /**
     * Get a character's completed tasks
     * <p>
     * Return a list of tasks finished by a character<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_opportunities[] get_characters_character_id_opportunities(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/opportunities/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_opportunities[].class));
    }

    /**
     * List historical orders by a character
     * <p>
     * List cancelled and expired market orders placed by a character up to 90 days in the past.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_characters_character_id_orders_history[] get_characters_character_id_orders_history(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/orders/history/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_orders_history[].class));
    }

    /**
     * Get colonies
     * <p>
     * Returns a list of all planetary colonies owned by a character.<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_planets[] get_characters_character_id_planets(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/planets/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets[].class));
    }

    /**
     * Get current ship
     * <p>
     * Get the current ship type, name and id<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_ship get_characters_character_id_ship(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/ship/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_ship.class));
    }

    /**
     * Get standings
     * <p>
     * Return character standings from agents, NPC corporations, and factions<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default M_get_standings_3 [] get_characters_character_id_standings(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/standings/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_standings_3[].class));
    }

    /**
     * Get character corporation titles
     * <p>
     * Returns a character's titles<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_titles[] get_characters_character_id_titles(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/titles/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_titles[].class));
    }

    /**
     * Get a character's wallet balance
     * <p>
     * Returns a character's wallet balance<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default double get_characters_character_id_wallet(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/wallet/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (double.class));
    }

    /**
     * Get wallet transactions
     * <p>
     * Get wallet transactions of a character<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param from_id
     *     Only show transactions happened before the one referenced by this id
     */
    public default R_get_characters_character_id_wallet_transactions[] get_characters_character_id_wallet_transactions(int character_id, Long from_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/wallet/transactions/".replace("{character_id}", ""+character_id)+"?"+(from_id==null?"":"&from_id="+flatten(from_id)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_wallet_transactions[].class));
    }

    /**
     * Moon extraction timers
     * <p>
     * Extraction timers for all moon chunks being extracted by refineries belonging to a corporation.<br />
     * This route is cached for up to 1800 seconds<br />
     * Requires one of the following EVE corporation role(s): Structure_manager
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATION_CORPORATION_ID_MINING_EXTRACTIONS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporation_corporation_id_mining_extractions[] get_corporation_corporation_id_mining_extractions(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/extractions/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_extractions[].class));
    }

    /**
     * Corporation mining observers
     * <p>
     * Paginated list of all entities capable of observing and recording mining for a corporation<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporation_corporation_id_mining_observers[] get_corporation_corporation_id_mining_observers(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/observers/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers[].class));
    }

    /**
     * Observed corporation mining
     * <p>
     * Paginated record of all mining seen by an observer<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_OBSERVER_ID_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param observer_id
     *     A mining observer id
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporation_corporation_id_mining_observers_observer_id[] get_corporation_corporation_id_mining_observers_observer_id(int corporation_id, long observer_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/observers/{observer_id}/".replace("{corporation_id}", ""+corporation_id).replace("{observer_id}", ""+observer_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id[].class));
    }

    /**
     * Get npc corporations
     * <p>
     * Get a list of npc corporations<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_corporations_npccorps(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/npccorps/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get coporation asset names
     * <p>
     * Return names for a set of item ids, which you can get from corporation assets endpoint. Only valid for items that can customize names, like containers or ships.<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #POST_CORPORATIONS_CORPORATION_ID_ASSETS_NAMES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param item_ids
     *     A list of item ids
     */
    public default M_post_assets_names_2 [] post_corporations_corporation_id_assets_names(int corporation_id, long[] item_ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/assets/names/".replace("{corporation_id}", ""+corporation_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_names_2[].class));
    }

    /**
     * List corporation bookmarks
     * <p>
     * A list of your corporation's bookmarks<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_bookmarks_9 [] get_corporations_corporation_id_bookmarks(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/bookmarks/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_bookmarks_9[].class));
    }

    /**
     * List corporation bookmark folders
     * <p>
     * A list of your corporation's bookmark folders<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_bookmarks_folders[] get_corporations_corporation_id_bookmarks_folders(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/bookmarks/folders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders[].class));
    }

    /**
     * Get corporation contact labels
     * <p>
     * Return custom labels for a corporation's contacts<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default M_get_contacts_labels_2 [] get_corporations_corporation_id_contacts_labels(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contacts/labels/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contacts_labels_2[].class));
    }

    /**
     * Get corporation contracts
     * <p>
     * Returns contracts available to a corporation, only if the corporation is issuer, acceptor or assignee. Only returns contracts no older than 30 days, or if the status is "in_progress".<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_contracts_22 [] get_corporations_corporation_id_contracts(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_22[].class));
    }

    /**
     * Get corporation contract bids
     * <p>
     * Lists bids on a particular auction contract<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param contract_id
     *     ID of a contract
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_contracts_contract_bids_4 [] get_corporations_corporation_id_contracts_contract_id_bids(int contract_id, int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/bids/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_bids_4[].class));
    }

    /**
     * Get corporation contract items
     * <p>
     * Lists items of a particular contract<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param contract_id
     *     ID of a contract
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default M_get_contracts_contract_items_6 [] get_corporations_corporation_id_contracts_contract_id_items(int contract_id, int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/items/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_contracts_contract_items_6[].class));
    }

    /**
     * List corporation customs offices
     * <p>
     * List customs offices owned by a corporation<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_CUSTOMS_OFFICES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_customs_offices[] get_corporations_corporation_id_customs_offices(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/customs_offices/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_customs_offices[].class));
    }

    /**
     * Get corporation divisions
     * <p>
     * Return corporation hangar and wallet division names, only show if a division is not using the default name<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_DIVISIONS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_divisions get_corporations_corporation_id_divisions(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/divisions/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_divisions.class));
    }

    /**
     * Get corporation facilities
     * <p>
     * Return a corporation's facilities<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Factory_Manager
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_FACILITIES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_facilities[] get_corporations_corporation_id_facilities(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/facilities/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_facilities[].class));
    }

    /**
     * Overview of a corporation involved in faction warfare
     * <p>
     * Statistics about a corporation involved in faction warfare<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_fw_stats get_corporations_corporation_id_fw_stats(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/fw/stats/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_fw_stats.class));
    }

    /**
     * Get corporation icon
     * <p>
     * Get the icon urls for a corporation<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_icons get_corporations_corporation_id_icons(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/icons/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_icons.class));
    }

    /**
     * List corporation industry jobs
     * <p>
     * List industry jobs run by a corporation<br />
     * This route is cached for up to 300 seconds<br />
     * Requires one of the following EVE corporation role(s): FactoryManager
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param include_completed
     *     Whether retrieve completed industry jobs as well
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_industry_jobs[] get_corporations_corporation_id_industry_jobs(int corporation_id, Boolean include_completed, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/industry/jobs/".replace("{corporation_id}", ""+corporation_id)+"?"+(include_completed==null?"":"&include_completed="+flatten(include_completed))+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_industry_jobs[].class));
    }

    /**
     * Get a corporation's recent kills and losses
     * <p>
     * Get a list of a corporation's kills and losses going back 90 days<br />
     * This route is cached for up to 300 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_KILLMAILS_RECENT_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_killmails_2 [] get_corporations_corporation_id_killmails_recent(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/killmails/recent/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_killmails_2[].class));
    }

    /**
     * Get corporation medals
     * <p>
     * Returns a corporation's medals<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_medals[] get_corporations_corporation_id_medals(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/medals/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_medals[].class));
    }

    /**
     * Get corporation issued medals
     * <p>
     * Returns medals issued by a corporation<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_MEDALS_ISSUED_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_medals_issued[] get_corporations_corporation_id_medals_issued(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/medals/issued/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_medals_issued[].class));
    }

    /**
     * Get corporation member limit
     * <p>
     * Return a corporation's member limit, not including CEO himself<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_MEMBERS_LIMIT_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default int get_corporations_corporation_id_members_limit(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/members/limit/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (int.class));
    }

    /**
     * Get corporation's members' titles
     * <p>
     * Returns a corporation's members' titles<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_MEMBERS_TITLES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_members_titles[] get_corporations_corporation_id_members_titles(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/members/titles/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_members_titles[].class));
    }

    /**
     * Track corporation members
     * <p>
     * Returns additional information about a corporation's members which helps tracking their activities<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_MEMBERTRACKING_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_membertracking[] get_corporations_corporation_id_membertracking(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/membertracking/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_membertracking[].class));
    }

    /**
     * List historical orders from a corporation
     * <p>
     * List cancelled and expired market orders placed on behalf of a corporation up to 90 days in the past.<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Trader<br />
     * Warning: This route has an upgrade available.<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/corporations/{corporation_id}/orders/history/)
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_ORDERS_HISTORY_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_orders_history[] get_corporations_corporation_id_orders_history(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/orders/history/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_orders_history[].class));
    }

    /**
     * Get corporation outposts
     * <p>
     * Get a list of corporation outpost IDs Note: This endpoint will be removed once outposts are migrated to Citadels as talked about in this blog: https://community.eveonline.com/news/dev-blogs/the-next-steps-in-structure-transition/<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director<br />
     * Warning: Outposts have been removed, this route will be deleted on 2018-07-08
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_OUTPOSTS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default int[] get_corporations_corporation_id_outposts(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/outposts/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get corporation outpost details
     * <p>
     * Get details about a given outpost. Note: This endpoint will be removed once outposts are migrated to Citadels as talked about in this blog: https://community.eveonline.com/news/dev-blogs/the-next-steps-in-structure-transition/<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director<br />
     * Warning: Outposts have been removed, this route will be deleted on 2018-07-08
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_OUTPOSTS_OUTPOST_ID_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param outpost_id
     *     A station (outpost) ID
     */
    public default R_get_corporations_corporation_id_outposts_outpost_id get_corporations_corporation_id_outposts_outpost_id(int corporation_id, int outpost_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/outposts/{outpost_id}/".replace("{corporation_id}", ""+corporation_id).replace("{outpost_id}", ""+outpost_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_outposts_outpost_id.class));
    }

    /**
     * Get corporation member roles
     * <p>
     * Return the roles of all members if the character has the personnel manager role or any grantable role.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_roles[] get_corporations_corporation_id_roles(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/roles/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_roles[].class));
    }

    /**
     * Get corporation member roles history
     * <p>
     * Return how roles have changed for a coporation's members, up to a month<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_ROLES_HISTORY_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_roles_history[] get_corporations_corporation_id_roles_history(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/roles/history/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_roles_history[].class));
    }

    /**
     * Get corporation shareholders
     * <p>
     * Return the current shareholders of a corporation.<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_SHAREHOLDERS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_shareholders[] get_corporations_corporation_id_shareholders(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/shareholders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_shareholders[].class));
    }

    /**
     * Get corporation standings
     * <p>
     * Return corporation standings from agents, NPC corporations, and factions<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_standings_3 [] get_corporations_corporation_id_standings(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/standings/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_standings_3[].class));
    }

    /**
     * Get corporation starbases (POSes)
     * <p>
     * Returns list of corporation starbases (POSes)<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_STARBASES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_starbases[] get_corporations_corporation_id_starbases(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/starbases/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_starbases[].class));
    }

    /**
     * Get starbase (POS) detail
     * <p>
     * Returns various settings and fuels of a starbase (POS)<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_STARBASES_STARBASE_ID_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param starbase_id
     *     An EVE starbase (POS) ID
     * @param system_id
     *     The solar system this starbase (POS) is located in,
     */
    public default R_get_corporations_corporation_id_starbases_starbase_id get_corporations_corporation_id_starbases_starbase_id(int corporation_id, long starbase_id, int system_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/starbases/{starbase_id}/".replace("{corporation_id}", ""+corporation_id).replace("{starbase_id}", ""+starbase_id)+"?"+"&system_id="+flatten(system_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_starbases_starbase_id.class));
    }

    /**
     * Get corporation titles
     * <p>
     * Returns a corporation's titles<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_TITLES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_titles[] get_corporations_corporation_id_titles(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/titles/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_titles[].class));
    }

    /**
     * Returns a corporation's wallet balance
     * <p>
     * Get a corporation's wallets<br />
     * This route is cached for up to 300 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Junior_Accountant
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_WALLETS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_wallets[] get_corporations_corporation_id_wallets(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/wallets/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_wallets[].class));
    }

    /**
     * Get corporation wallet transactions
     * <p>
     * Get wallet transactions of a corporation<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Junior_Accountant
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_TRANSACTIONS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param division
     *     Wallet key of the division to fetch journals from
     * @param from_id
     *     Only show journal entries happened before the transaction referenced by this id
     */
    public default R_get_corporations_corporation_id_wallets_division_transactions[] get_corporations_corporation_id_wallets_division_transactions(int corporation_id, int division, Long from_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/wallets/{division}/transactions/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division)+"?"+(from_id==null?"":"&from_id="+flatten(from_id)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions[].class));
    }

    /**
     * Get attributes
     * <p>
     * Get a list of dogma attribute ids<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_dogma_attributes(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/dogma/attributes/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get attribute information
     * <p>
     * Get information on a dogma attribute<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param attribute_id
     *     A dogma attribute ID
     */
    public default R_get_dogma_attributes_attribute_id get_dogma_attributes_attribute_id(int attribute_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/dogma/attributes/{attribute_id}/".replace("{attribute_id}", ""+attribute_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_attributes_attribute_id.class));
    }

    /**
     * Get dynamic item information
     * <p>
     * Returns info about a dynamic item resulting from mutation with a mutaplasmid.<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param item_id
     *     item_id integer
     * @param type_id
     *     type_id integer
     */
    public default R_get_dogma_dynamic_items_type_id_item_id get_dogma_dynamic_items_type_id_item_id(long item_id, int type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/dogma/dynamic/items/{type_id}/{item_id}/".replace("{item_id}", ""+item_id).replace("{type_id}", ""+type_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id.class));
    }

    /**
     * Get effects
     * <p>
     * Get a list of dogma effect ids<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_dogma_effects(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/dogma/effects/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get fleet information
     * <p>
     * Return details about a fleet<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public default R_get_fleets_fleet_id get_fleets_fleet_id(long fleet_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id.class));
    }

    /**
     * Update fleet
     * <p>
     * Update settings about a fleet
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param is_free_move
     *     Should free-move be enabled in the fleet
     * @param motd
     *     New fleet MOTD in CCP flavoured HTML
     */
    public default void put_fleets_fleet_id(long fleet_id, boolean is_free_move, String motd, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id));
        Map<String, Object> content = new HashMap<>();
        content.put("is_free_move", is_free_move);
        content.put("motd", motd);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Get fleet members
     * <p>
     * Return information about fleet members<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_fleets_fleet_id_members[] get_fleets_fleet_id_members(long fleet_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id_members[].class));
    }

    /**
     * Create fleet invitation
     * <p>
     * Invite a character into the fleet. If a character has a CSPA charge set it is not possible to invite them to the fleet using ESI
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param character_id
     *     The character you want to invite
     * @param role
     *     If a character is invited with the `fleet_commander` role, neither `wing_id` or `squad_id` should be specified. If a character is invited with the `wing_commander` role, only `wing_id` should be specified. If a character is invited with the `squad_commander` role, both `wing_id` and `squad_id` should be specified. If a character is invited with the `squad_member` role, `wing_id` and `squad_id` should either both be specified or not specified at all. If they aren’t specified, the invited character will join any squad with available positions.
     * @param squad_id
     *     squad_id integer
     * @param wing_id
     *     wing_id integer
     */
    public default void post_fleets_fleet_id_members(long fleet_id, int character_id, String role, long squad_id, long wing_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id));
        Map<String, Object> content = new HashMap<>();
        content.put("character_id", character_id);
        content.put("role", role);
        content.put("squad_id", squad_id);
        content.put("wing_id", wing_id);
        connectPost(url, content, true, headerHandler);
    }

    /**
     * Kick fleet member
     * <p>
     * Kick a fleet member
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param member_id
     *     The character ID of a member in this fleet
     */
    public default void delete_fleets_fleet_id_members_member_id(long fleet_id, int member_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/{member_id}/".replace("{fleet_id}", ""+fleet_id).replace("{member_id}", ""+member_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Move fleet member
     * <p>
     * Move a fleet member around
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param member_id
     *     The character ID of a member in this fleet
     * @param role
     *     If a character is moved to the `fleet_commander` role, neither `wing_id` or `squad_id` should be specified. If a character is moved to the `wing_commander` role, only `wing_id` should be specified. If a character is moved to the `squad_commander` role, both `wing_id` and `squad_id` should be specified. If a character is moved to the `squad_member` role, both `wing_id` and `squad_id` should be specified.
     * @param squad_id
     *     squad_id integer
     * @param wing_id
     *     wing_id integer
     */
    public default void put_fleets_fleet_id_members_member_id(long fleet_id, int member_id, String role, long squad_id, long wing_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/{member_id}/".replace("{fleet_id}", ""+fleet_id).replace("{member_id}", ""+member_id));
        Map<String, Object> content = new HashMap<>();
        content.put("role", role);
        content.put("squad_id", squad_id);
        content.put("wing_id", wing_id);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Delete fleet squad
     * <p>
     * Delete a fleet squad, only empty squads can be deleted
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param squad_id
     *     The squad to delete
     */
    public default void delete_fleets_fleet_id_squads_squad_id(long fleet_id, long squad_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/squads/{squad_id}/".replace("{fleet_id}", ""+fleet_id).replace("{squad_id}", ""+squad_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Rename fleet squad
     * <p>
     * Rename a fleet squad
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param name
     *     name string
     * @param squad_id
     *     The squad to rename
     */
    public default void put_fleets_fleet_id_squads_squad_id(long fleet_id, String name, long squad_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/squads/{squad_id}/".replace("{fleet_id}", ""+fleet_id).replace("{squad_id}", ""+squad_id));
        Map<String, Object> content = new HashMap<>();
        content.put("name", name);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Get fleet wings
     * <p>
     * Return information about wings in a fleet<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_fleets_fleet_id_wings[] get_fleets_fleet_id_wings(long fleet_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id_wings[].class));
    }

    /**
     * Create fleet wing
     * <p>
     * Create a new wing in a fleet
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     */
    public default R_post_fleets_fleet_id_wings_created post_fleets_fleet_id_wings(long fleet_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id));
        String fetched = connectPost(url, Collections.emptyMap(), true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_fleets_fleet_id_wings_created.class));
    }

    /**
     * Delete fleet wing
     * <p>
     * Delete a fleet wing, only empty wings can be deleted. The wing may contain squads, but the squads must be empty
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param wing_id
     *     The wing to delete
     */
    public default void delete_fleets_fleet_id_wings_wing_id(long fleet_id, long wing_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        connectDel(url,true, headerHandler);
    }

    /**
     * Rename fleet wing
     * <p>
     * Rename a fleet wing
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param name
     *     name string
     * @param wing_id
     *     The wing to rename
     */
    public default void put_fleets_fleet_id_wings_wing_id(long fleet_id, String name, long wing_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        Map<String, Object> content = new HashMap<>();
        content.put("name", name);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Create fleet squad
     * <p>
     * Create a new squad in a fleet
     * </p>
     * 
     * @param fleet_id
     *     ID for a fleet
     * @param wing_id
     *     The wing_id to create squad in
     */
    public default R_post_fleets_fleet_id_wings_wing_id_squads_created post_fleets_fleet_id_wings_wing_id_squads(long fleet_id, long wing_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/squads/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        String fetched = connectPost(url, Collections.emptyMap(), true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_fleets_fleet_id_wings_wing_id_squads_created.class));
    }

    /**
     * List of the top factions in faction warfare
     * <p>
     * Top 4 leaderboard of factions for kills and victory points separated by total, last week and yesterday.<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default M_get_fw_leaderboards_2 get_fw_leaderboards(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * List of the top pilots in faction warfare
     * <p>
     * Top 100 leaderboard of pilots for kills and victory points separated by total, last week and yesterday.<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default M_get_fw_leaderboards_2 get_fw_leaderboards_characters(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/characters/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * List of the top corporations in faction warfare
     * <p>
     * Top 10 leaderboard of corporations for kills and victory points separated by total, last week and yesterday.<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default M_get_fw_leaderboards_2 get_fw_leaderboards_corporations(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/corporations/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * An overview of statistics about factions involved in faction warfare
     * <p>
     * Statistical overviews of factions involved in faction warfare<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default R_get_fw_stats[] get_fw_stats(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/stats/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_stats[].class));
    }

    /**
     * Ownership of faction warfare systems
     * <p>
     * An overview of the current ownership of faction warfare solar systems<br />
     * This route is cached for up to 1800 seconds<br />
     * Warning: This route has an upgrade available.<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/fw/systems/)
     * </p>
     */
    public default R_get_fw_systems[] get_fw_systems(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/systems/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_systems[].class));
    }

    /**
     * Data about which NPC factions are at war
     * <p>
     * Data about which NPC factions are at war<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default R_get_fw_wars[] get_fw_wars(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/fw/wars/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_wars[].class));
    }

    /**
     * List incursions
     * <p>
     * Return a list of current incursions<br />
     * This route is cached for up to 300 seconds
     * </p>
     */
    public default R_get_incursions[] get_incursions(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/incursions/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_incursions[].class));
    }

    /**
     * List industry facilities
     * <p>
     * Return a list of industry facilities<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_industry_facilities[] get_industry_facilities(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/industry/facilities/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_industry_facilities[].class));
    }

    /**
     * List solar system cost indices
     * <p>
     * Return cost indices for solar systems<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_industry_systems[] get_industry_systems(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/industry/systems/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_industry_systems[].class));
    }

    /**
     * List insurance levels
     * <p>
     * Return available insurance levels for all ship types<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_insurance_prices[] get_insurance_prices(Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/insurance/prices/"+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_insurance_prices[].class));
    }

    /**
     * Get a single killmail
     * <p>
     * Return a single killmail from its ID and hash<br />
     * This route is cached for up to 1209600 seconds
     * </p>
     * 
     * @param killmail_hash
     *     The killmail hash for verification
     * @param killmail_id
     *     The killmail ID to be queried
     */
    public default R_get_killmails_killmail_id_killmail_hash get_killmails_killmail_id_killmail_hash(String killmail_hash, int killmail_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/killmails/{killmail_id}/{killmail_hash}/".replace("{killmail_hash}", ""+killmail_hash).replace("{killmail_id}", ""+killmail_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_killmails_killmail_id_killmail_hash.class));
    }

    /**
     * List loyalty store offers
     * <p>
     * Return a list of offers from a specific corporation's loyalty store<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_loyalty_stores_corporation_id_offers[] get_loyalty_stores_corporation_id_offers(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/loyalty/stores/{corporation_id}/offers/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_loyalty_stores_corporation_id_offers[].class));
    }

    /**
     * Get item groups
     * <p>
     * Get a list of item groups<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_markets_groups(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/groups/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get item group information
     * <p>
     * Get information on an item group<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param market_group_id
     *     An Eve item group ID
     */
    public default R_get_markets_groups_market_group_id get_markets_groups_market_group_id(Swagger.language language, int market_group_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/groups/{market_group_id}/".replace("{market_group_id}", ""+market_group_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_groups_market_group_id.class));
    }

    /**
     * List market prices
     * <p>
     * Return a list of prices<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_markets_prices[] get_markets_prices(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/prices/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_prices[].class));
    }

    /**
     * List orders in a structure
     * <p>
     * Return all orders in a structure<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param page
     *     Which page of results to return
     * @param structure_id
     *     Return orders in this structure
     */
    public default R_get_markets_structures_structure_id[] get_markets_structures_structure_id(Integer page, long structure_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/structures/{structure_id}/".replace("{structure_id}", ""+structure_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_structures_structure_id[].class));
    }

    /**
     * List historical market statistics in a region
     * <p>
     * Return a list of historical market statistics for the specified type in a region<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param region_id
     *     Return statistics in this region
     * @param type_id
     *     Return statistics for this type
     */
    public default R_get_markets_region_id_history[] get_markets_region_id_history(int region_id, int type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/history/".replace("{region_id}", ""+region_id)+"?"+"&type_id="+flatten(type_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_history[].class));
    }

    /**
     * List orders in a region
     * <p>
     * Return a list of orders in a region<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param order_type
     *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders.
     * @param page
     *     Which page of results to return
     * @param region_id
     *     Return orders in this region
     * @param type_id
     *     Return orders only for this type
     */
    public default R_get_markets_region_id_orders[] get_markets_region_id_orders(Swagger.order_type order_type, Integer page, int region_id, Integer type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/orders/".replace("{region_id}", ""+region_id)+"?"+(order_type==null?"":"&order_type="+flatten(order_type))+(page==null?"":"&page="+flatten(page))+(type_id==null?"":"&type_id="+flatten(type_id)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_region_id_orders[].class));
    }

    /**
     * List type IDs relevant to a market
     * <p>
     * Return a list of type IDs that have active orders in the region, for efficient market indexing.<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param page
     *     Which page of results to return
     * @param region_id
     *     Return statistics in this region
     */
    public default int[] get_markets_region_id_types(Integer page, int region_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/types/".replace("{region_id}", ""+region_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get opportunities groups
     * <p>
     * Return a list of opportunities groups<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_opportunities_groups(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/opportunities/groups/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get opportunities group
     * <p>
     * Return information of an opportunities group<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param group_id
     *     ID of an opportunities group
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_opportunities_groups_group_id get_opportunities_groups_group_id(int group_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/opportunities/groups/{group_id}/".replace("{group_id}", ""+group_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_groups_group_id.class));
    }

    /**
     * Get opportunities tasks
     * <p>
     * Return a list of opportunities tasks<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_opportunities_tasks(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/opportunities/tasks/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get opportunities task
     * <p>
     * Return information of an opportunities task<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param task_id
     *     ID of an opportunities task
     */
    public default R_get_opportunities_tasks_task_id get_opportunities_tasks_task_id(int task_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/opportunities/tasks/{task_id}/".replace("{task_id}", ""+task_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_tasks_task_id.class));
    }

    /**
     * Get route
     * <p>
     * Get the systems between origin and destination<br />
     * This route is cached for up to 86400 seconds
     * </p>
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
    public default int[] get_route_origin_destination(int[] avoid, int[][] connections, int destination, Swagger.flag flag, int origin, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/route/{origin}/{destination}/".replace("{destination}", ""+destination).replace("{origin}", ""+origin)+"?"+(avoid==null?"":"&avoid="+flatten(avoid))+(connections==null?"":"&connections="+flatten(connections))+(flag==null?"":"&flag="+flatten(flag)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * List sovereignty campaigns
     * <p>
     * Shows sovereignty data for campaigns.<br />
     * This route is cached for up to 5 seconds
     * </p>
     */
    public default R_get_sovereignty_campaigns[] get_sovereignty_campaigns(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/sovereignty/campaigns/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_campaigns[].class));
    }

    /**
     * List sovereignty of systems
     * <p>
     * Shows sovereignty information for solar systems<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_sovereignty_map[] get_sovereignty_map(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/sovereignty/map/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_map[].class));
    }

    /**
     * List sovereignty structures
     * <p>
     * Shows sovereignty data for structures.<br />
     * This route is cached for up to 120 seconds
     * </p>
     */
    public default R_get_sovereignty_structures[] get_sovereignty_structures(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/sovereignty/structures/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_sovereignty_structures[].class));
    }

    /**
     * Retrieve the uptime and player counts
     * <p>
     * EVE Server status<br />
     * This route is cached for up to 30 seconds
     * </p>
     */
    public default R_get_status get_status(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/status/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status.class));
    }

    /**
     * Open Contract Window
     * <p>
     * Open the contract window inside the client
     * </p>
     * 
     * @param contract_id
     *     The contract to open
     */
    public default void post_ui_openwindow_contract(int contract_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/contract/"+"?"+"&contract_id="+flatten(contract_id));
        connectPost(url, Collections.emptyMap(), true, headerHandler);
    }

    /**
     * Open Information Window
     * <p>
     * Open the information window for a character, corporation or alliance inside the client
     * </p>
     * 
     * @param target_id
     *     The target to open
     */
    public default void post_ui_openwindow_information(int target_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/information/"+"?"+"&target_id="+flatten(target_id));
        connectPost(url, Collections.emptyMap(), true, headerHandler);
    }

    /**
     * Open Market Details
     * <p>
     * Open the market details window for a specific typeID inside the client
     * </p>
     * 
     * @param type_id
     *     The item type to open in market window
     */
    public default void post_ui_openwindow_marketdetails(int type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/marketdetails/"+"?"+"&type_id="+flatten(type_id));
        connectPost(url, Collections.emptyMap(), true, headerHandler);
    }

    /**
     * Open New Mail Window
     * <p>
     * Open the New Mail window, according to settings from the request if applicable
     * </p>
     * 
     * @param body
     *     body string
     * @param recipients
     *     recipients array
     * @param subject
     *     subject string
     * @param to_corp_or_alliance_id
     *     to_corp_or_alliance_id integer
     * @param to_mailing_list_id
     *     Corporations, alliances and mailing lists are all types of mailing groups. You may only send to one mailing group, at a time, so you may fill out either this field or the to_corp_or_alliance_ids field
     */
    public default void post_ui_openwindow_newmail(String body, int[] recipients, String subject, int to_corp_or_alliance_id, int to_mailing_list_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/newmail/");
        Map<String, Object> content = new HashMap<>();
        content.put("body", body);
        content.put("recipients", recipients);
        content.put("subject", subject);
        content.put("to_corp_or_alliance_id", to_corp_or_alliance_id);
        content.put("to_mailing_list_id", to_mailing_list_id);
        connectPost(url, content, true, headerHandler);
    }

    /**
     * Get ancestries
     * <p>
     * Get all character ancestries<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_ancestries[] get_universe_ancestries(Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/ancestries/"+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_ancestries[].class));
    }

    /**
     * Get asteroid belt information
     * <p>
     * Get information on an asteroid belt<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param asteroid_belt_id
     *     asteroid_belt_id integer
     */
    public default R_get_universe_asteroid_belts_asteroid_belt_id get_universe_asteroid_belts_asteroid_belt_id(int asteroid_belt_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/asteroid_belts/{asteroid_belt_id}/".replace("{asteroid_belt_id}", ""+asteroid_belt_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id.class));
    }

    /**
     * Get bloodlines
     * <p>
     * Get a list of bloodlines<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_bloodlines[] get_universe_bloodlines(Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/bloodlines/"+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_bloodlines[].class));
    }

    /**
     * Get item categories
     * <p>
     * Get a list of item categories<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_universe_categories(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/categories/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get item category information
     * <p>
     * Get information of an item category<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param category_id
     *     An Eve item category ID
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_categories_category_id get_universe_categories_category_id(int category_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/categories/{category_id}/".replace("{category_id}", ""+category_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_categories_category_id.class));
    }

    /**
     * Get constellations
     * <p>
     * Get a list of constellations<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_universe_constellations(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/constellations/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get constellation information
     * <p>
     * Get information on a constellation<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param constellation_id
     *     constellation_id integer
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_constellations_constellation_id get_universe_constellations_constellation_id(int constellation_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/constellations/{constellation_id}/".replace("{constellation_id}", ""+constellation_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_constellations_constellation_id.class));
    }

    /**
     * Get graphics
     * <p>
     * Get a list of graphics<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_universe_graphics(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/graphics/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get graphic information
     * <p>
     * Get information on a graphic<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param graphic_id
     *     graphic_id integer
     */
    public default R_get_universe_graphics_graphic_id get_universe_graphics_graphic_id(int graphic_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/graphics/{graphic_id}/".replace("{graphic_id}", ""+graphic_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_graphics_graphic_id.class));
    }

    /**
     * Get item groups
     * <p>
     * Get a list of item groups<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param page
     *     Which page of results to return
     */
    public default int[] get_universe_groups(Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/groups/"+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get item group information
     * <p>
     * Get information on an item group<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param group_id
     *     An Eve item group ID
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_groups_group_id get_universe_groups_group_id(int group_id, Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/groups/{group_id}/".replace("{group_id}", ""+group_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_groups_group_id.class));
    }

    /**
     * Bulk names to IDs
     * <p>
     * Resolve a set of names to IDs in the following categories: agents, alliances, characters, constellations, corporations factions, inventory_types, regions, stations, and systems. Only exact matches will be returned. All names searched for are cached for 12 hours.
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param names
     *     The names to resolve
     */
    public default R_post_universe_ids post_universe_ids(Swagger.language language, String[] names, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/ids/"+"?"+(language==null?"":"&language="+flatten(language)));
        Map<String, Object> content = new HashMap<>();
        content.put("names", names);
        String fetched = connectPost(url, content, false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_ids.class));
    }

    /**
     * Get moon information
     * <p>
     * Get information on a moon<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param moon_id
     *     moon_id integer
     */
    public default R_get_universe_moons_moon_id get_universe_moons_moon_id(int moon_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/moons/{moon_id}/".replace("{moon_id}", ""+moon_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_moons_moon_id.class));
    }

    /**
     * Get planet information
     * <p>
     * Get information on a planet<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param planet_id
     *     planet_id integer
     */
    public default R_get_universe_planets_planet_id get_universe_planets_planet_id(int planet_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/planets/{planet_id}/".replace("{planet_id}", ""+planet_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_planets_planet_id.class));
    }

    /**
     * Get character races
     * <p>
     * Get a list of character races<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_races[] get_universe_races(Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/races/"+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_races[].class));
    }

    /**
     * Get regions
     * <p>
     * Get a list of regions<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_universe_regions(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/regions/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get region information
     * <p>
     * Get information on a region<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param region_id
     *     region_id integer
     */
    public default R_get_universe_regions_region_id get_universe_regions_region_id(Swagger.language language, int region_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/regions/{region_id}/".replace("{region_id}", ""+region_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_regions_region_id.class));
    }

    /**
     * Get schematic information
     * <p>
     * Get information on a planetary factory schematic<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param schematic_id
     *     A PI schematic ID
     */
    public default R_get_universe_schematics_schematic_id get_universe_schematics_schematic_id(int schematic_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/schematics/{schematic_id}/".replace("{schematic_id}", ""+schematic_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_schematics_schematic_id.class));
    }

    /**
     * Get stargate information
     * <p>
     * Get information on a stargate<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param stargate_id
     *     stargate_id integer
     */
    public default R_get_universe_stargates_stargate_id get_universe_stargates_stargate_id(int stargate_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/stargates/{stargate_id}/".replace("{stargate_id}", ""+stargate_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stargates_stargate_id.class));
    }

    /**
     * Get star information
     * <p>
     * Get information on a star<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param star_id
     *     star_id integer
     */
    public default R_get_universe_stars_star_id get_universe_stars_star_id(int star_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/stars/{star_id}/".replace("{star_id}", ""+star_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stars_star_id.class));
    }

    /**
     * List all public structures
     * <p>
     * List all public structures<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default long[] get_universe_structures(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/structures/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (long[].class));
    }

    /**
     * Get structure information
     * <p>
     * Returns information on requested structure, if you are on the ACL. Otherwise, returns "Forbidden" for all inputs.<br />
     * This route is cached for up to 3600 seconds<br />
     * Warning: This route has an upgrade available.<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/universe/structures/{structure_id}/)
     * </p>
     * 
     * @param structure_id
     *     An Eve structure ID
     */
    public default R_get_universe_structures_structure_id get_universe_structures_structure_id(long structure_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/structures/{structure_id}/".replace("{structure_id}", ""+structure_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id.class));
    }

    /**
     * Get system jumps
     * <p>
     * Get the number of jumps in solar systems within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with jumps will be listed<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_universe_system_jumps[] get_universe_system_jumps(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/system_jumps/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_system_jumps[].class));
    }

    /**
     * Get solar systems
     * <p>
     * Get a list of solar systems<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default int[] get_universe_systems(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/systems/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get types
     * <p>
     * Get a list of type ids<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param page
     *     Which page of results to return
     */
    public default int[] get_universe_types(Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/universe/types/"+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * List wars
     * <p>
     * Return a list of wars<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param max_war_id
     *     Only return wars with ID smaller than this.
     */
    public default int[] get_wars(Integer max_war_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/wars/"+"?"+(max_war_id==null?"":"&max_war_id="+flatten(max_war_id)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get war information
     * <p>
     * Return details about a war<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param war_id
     *     ID for a war
     */
    public default R_get_wars_war_id get_wars_war_id(int war_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/wars/{war_id}/".replace("{war_id}", ""+war_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_wars_war_id.class));
    }

    /**
     * List kills for a war
     * <p>
     * Return a list of kills related to a war<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param page
     *     Which page of results to return
     * @param war_id
     *     A valid war ID
     */
    public default M_get_killmails_2 [] get_wars_war_id_killmails(Integer page, int war_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v1/wars/{war_id}/killmails/".replace("{war_id}", ""+war_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_killmails_2[].class));
    }

    /**
     * Get alliance contacts
     * <p>
     * Return contacts of an alliance<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_alliances_alliance_id_contacts[] get_alliances_alliance_id_contacts(int alliance_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/alliances/{alliance_id}/contacts/".replace("{alliance_id}", ""+alliance_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id_contacts[].class));
    }

    /**
     * Get character asset locations
     * <p>
     * Return locations for a set of item ids, which you can get from character assets endpoint. Coordinates for items in hangars or stations are set to (0,0,0)
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param item_ids
     *     A list of item ids
     */
    public default M_post_assets_locations_2 [] post_characters_character_id_assets_locations(int character_id, long[] item_ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/assets/locations/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_locations_2[].class));
    }

    /**
     * Get blueprints
     * <p>
     * Return a list of blueprints the character owns<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_blueprints_8 [] get_characters_character_id_blueprints(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/blueprints/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_blueprints_8[].class));
    }

    /**
     * List bookmarks
     * <p>
     * A list of your character's personal bookmarks<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_bookmarks_9 [] get_characters_character_id_bookmarks(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/bookmarks/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_bookmarks_9[].class));
    }

    /**
     * List bookmark folders
     * <p>
     * A list of your character's personal bookmark folders<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_characters_character_id_bookmarks_folders[] get_characters_character_id_bookmarks_folders(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/bookmarks/folders/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_bookmarks_folders[].class));
    }

    /**
     * Get contacts
     * <p>
     * Return contacts of a character<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_characters_character_id_contacts[] get_characters_character_id_contacts(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_contacts[].class));
    }

    /**
     * Add contacts
     * <p>
     * Bulk add contacts with same settings
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param contact_ids
     *     A list of contacts
     * @param label_ids
     *     Add custom labels to the new contact
     * @param standing
     *     Standing for the contact
     * @param watched
     *     Whether the contact should be watched, note this is only effective on characters
     */
    public default int[] post_characters_character_id_contacts(int character_id, int[] contact_ids, long[] label_ids, float standing, Boolean watched, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(label_ids==null?"":"&label_ids="+flatten(label_ids))+"&standing="+flatten(standing)+(watched==null?"":"&watched="+flatten(watched)));
        Map<String, Object> content = new HashMap<>();
        content.put("contact_ids", contact_ids);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Delete contacts
     * <p>
     * Bulk delete contacts
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param contact_ids
     *     A list of contacts to delete
     */
    public default void delete_characters_character_id_contacts(int character_id, int[] contact_ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(contact_ids==null?"":"&contact_ids="+flatten(contact_ids)));
        connectDel(url,true, headerHandler);
    }

    /**
     * Edit contacts
     * <p>
     * Bulk edit contacts with same settings
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param contact_ids
     *     A list of contacts
     * @param label_ids
     *     Add custom labels to the contact
     * @param standing
     *     Standing for the contact
     * @param watched
     *     Whether the contact should be watched, note this is only effective on characters
     */
    public default void put_characters_character_id_contacts(int character_id, int[] contact_ids, long[] label_ids, float standing, Boolean watched, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(label_ids==null?"":"&label_ids="+flatten(label_ids))+"&standing="+flatten(standing)+(watched==null?"":"&watched="+flatten(watched)));
        Map<String, Object> content = new HashMap<>();
        content.put("contact_ids", contact_ids);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Create a mail label
     * <p>
     * Create a mail label
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param color
     *     Hexadecimal string representing label color, in RGB format
     * @param name
     *     name string
     */
    public default int post_characters_character_id_mail_labels(int character_id, String color, String name, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("color", color);
        content.put("name", name);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (int.class));
    }

    /**
     * Get character notifications
     * <p>
     * Return character notifications<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_notifications[] get_characters_character_id_notifications(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/notifications/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_notifications[].class));
    }

    /**
     * Get character online
     * <p>
     * Checks if the character is currently online<br />
     * This route is cached for up to 60 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_online get_characters_character_id_online(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/online/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_online.class));
    }

    /**
     * List open orders from a character
     * <p>
     * List open market orders placed by a character<br />
     * This route is cached for up to 1200 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_orders[] get_characters_character_id_orders(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/orders/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_orders[].class));
    }

    /**
     * Get character portraits
     * <p>
     * Get portrait urls for a character<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_portrait get_characters_character_id_portrait(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/portrait/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_portrait.class));
    }

    /**
     * Get character corporation roles
     * <p>
     * Returns a character's corporation roles<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_roles get_characters_character_id_roles(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/roles/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_roles.class));
    }

    /**
     * Get character's skill queue
     * <p>
     * List the configured skill queue for the given character<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_skillqueue[] get_characters_character_id_skillqueue(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/skillqueue/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_skillqueue[].class));
    }

    /**
     * Yearly aggregate stats
     * <p>
     * Returns aggregate yearly stats for a character<br />
     * This route is cached for up to 86400 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_stats[] get_characters_character_id_stats(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/stats/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_stats[].class));
    }

    /**
     * Get alliance history
     * <p>
     * Get a list of all the alliances a corporation has been a member of<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id_alliancehistory[] get_corporations_corporation_id_alliancehistory(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/alliancehistory/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_alliancehistory[].class));
    }

    /**
     * Get corporation asset locations
     * <p>
     * Return locations for a set of item ids, which you can get from corporation assets endpoint. Coordinates for items in hangars or stations are set to (0,0,0)<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #POST_CORPORATIONS_CORPORATION_ID_ASSETS_LOCATIONS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param item_ids
     *     A list of item ids
     */
    public default M_post_assets_locations_2 [] post_corporations_corporation_id_assets_locations(int corporation_id, long[] item_ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/assets/locations/".replace("{corporation_id}", ""+corporation_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_post_assets_locations_2[].class));
    }

    /**
     * Get corporation blueprints
     * <p>
     * Returns a list of blueprints the corporation owns<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_BLUEPRINTS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_blueprints_8 [] get_corporations_corporation_id_blueprints(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/blueprints/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_blueprints_8[].class));
    }

    /**
     * Get corporation contacts
     * <p>
     * Return contacts of a corporation<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_contacts[] get_corporations_corporation_id_contacts(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/contacts/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_contacts[].class));
    }

    /**
     * Get all corporation ALSC logs
     * <p>
     * Returns logs recorded in the past seven days from all audit log secure containers (ALSC) owned by a given corporation<br />
     * This route is cached for up to 600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_CONTAINERS_LOGS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_containers_logs[] get_corporations_corporation_id_containers_logs(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/containers/logs/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_containers_logs[].class));
    }

    /**
     * List open orders from a corporation
     * <p>
     * List open market orders placed on behalf of a corporation<br />
     * This route is cached for up to 1200 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Trader<br />
     * Warning: This route has an upgrade available.<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/corporations/{corporation_id}/orders/)
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_ORDERS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_orders[] get_corporations_corporation_id_orders(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/orders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_orders[].class));
    }

    /**
     * Get corporation structures
     * <p>
     * Get a list of corporation structures. This route's version includes the changes to structures detailed in this blog: https://www.eveonline.com/article/upwell-2.0-structures-changes-coming-on-february-13th<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): StationManager
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_STRUCTURES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param page
     *     Which page of results to return
     */
    public default R_get_corporations_corporation_id_structures[] get_corporations_corporation_id_structures(int corporation_id, Swagger.language language, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/structures/".replace("{corporation_id}", ""+corporation_id)+"?"+(language==null?"":"&language="+flatten(language))+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_structures[].class));
    }

    /**
     * Get effect information
     * <p>
     * Get information on a dogma effect<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param effect_id
     *     A dogma effect ID
     */
    public default R_get_dogma_effects_effect_id get_dogma_effects_effect_id(int effect_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/dogma/effects/{effect_id}/".replace("{effect_id}", ""+effect_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_effects_effect_id.class));
    }

    /**
     * Search on a string
     * <p>
     * Search for entities that match a given sub-string.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param categories
     *     Type of entities to search for
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public default R_get_search get_search(String[] categories, Swagger.language language, String search, Boolean strict, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/search/"+"?"+(categories==null?"":"&categories="+flatten(categories))+(language==null?"":"&language="+flatten(language))+(search==null?"":"&search="+flatten(search))+(strict==null?"":"&strict="+flatten(strict)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_search.class));
    }

    /**
     * Set Autopilot Waypoint
     * <p>
     * Set a solar system as autopilot waypoint
     * </p>
     * 
     * @param add_to_beginning
     *     Whether this solar system should be added to the beginning of all waypoints
     * @param clear_other_waypoints
     *     Whether clean other waypoints beforing adding this one
     * @param destination_id
     *     The destination to travel to, can be solar system, station or structure's id
     */
    public default void post_ui_autopilot_waypoint(boolean add_to_beginning, boolean clear_other_waypoints, long destination_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/ui/autopilot/waypoint/"+"?"+"&add_to_beginning="+flatten(add_to_beginning)+"&clear_other_waypoints="+flatten(clear_other_waypoints)+"&destination_id="+flatten(destination_id));
        connectPost(url, Collections.emptyMap(), true, headerHandler);
    }

    /**
     * Get factions
     * <p>
     * Get a list of factions<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     */
    public default R_get_universe_factions[] get_universe_factions(Swagger.language language, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/universe/factions/"+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_factions[].class));
    }

    /**
     * Get names and categories for a set of ID's
     * <p>
     * Resolve a set of IDs to names and categories. Supported ID's for resolving are: Characters, Corporations, Alliances, Stations, Solar Systems, Constellations, Regions, Types.
     * </p>
     * 
     * @param ids
     *     The ids to resolve
     */
    public default R_post_universe_names[] post_universe_names(int[] ids, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/universe/names/");
        Map<String, Object> content = new HashMap<>();
        content.put("ids", ids);
        String fetched = connectPost(url, content, false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_post_universe_names[].class));
    }

    /**
     * Get station information
     * <p>
     * Get information on a station<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param station_id
     *     station_id integer
     */
    public default R_get_universe_stations_station_id get_universe_stations_station_id(int station_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/universe/stations/{station_id}/".replace("{station_id}", ""+station_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stations_station_id.class));
    }

    /**
     * Get system kills
     * <p>
     * Get the number of ship, pod and NPC kills per solar system within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with kills will be listed<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default R_get_universe_system_kills[] get_universe_system_kills(Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v2/universe/system_kills/");
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_system_kills[].class));
    }

    /**
     * Get alliance information
     * <p>
     * Public information about an alliance<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public default R_get_alliances_alliance_id get_alliances_alliance_id(int alliance_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/alliances/{alliance_id}/".replace("{alliance_id}", ""+alliance_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id.class));
    }

    /**
     * Get character assets
     * <p>
     * Return a list of the characters assets<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_assets_8 [] get_characters_character_id_assets(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/assets/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_assets_8[].class));
    }

    /**
     * Get an event
     * <p>
     * Get all the information for a specific event<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The id of the event requested
     */
    public default R_get_characters_character_id_calendar_event_id get_characters_character_id_calendar_event_id(int character_id, int event_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_calendar_event_id.class));
    }

    /**
     * Respond to an event
     * <p>
     * Set your response status to an event
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The ID of the event requested
     * @param response
     *     response string
     */
    public default void put_characters_character_id_calendar_event_id(int character_id, int event_id, String response, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        Map<String, Object> content = new HashMap<>();
        content.put("response", response);
        connectPut(url, content, true, headerHandler);
    }

    /**
     * Get clones
     * <p>
     * A list of the character's clones<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_clones get_characters_character_id_clones(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/clones/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_clones.class));
    }

    /**
     * Get mail labels and unread counts
     * <p>
     * Return a list of the users mail labels, unread counts for each label and a total unread count.<br />
     * This route is cached for up to 30 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_mail_labels get_characters_character_id_mail_labels(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_labels.class));
    }

    /**
     * Get colony layout
     * <p>
     * Returns full details on the layout of a single planetary colony, including links, pins and routes. Note: Planetary information is only recalculated when the colony is viewed through the client. Information will not update until this criteria is met.<br />
     * This route is cached for up to 600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param planet_id
     *     Planet id of the target planet
     */
    public default R_get_characters_character_id_planets_planet_id get_characters_character_id_planets_planet_id(int character_id, int planet_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/planets/{planet_id}/".replace("{character_id}", ""+character_id).replace("{planet_id}", ""+planet_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_planets_planet_id.class));
    }

    /**
     * Search on a string
     * <p>
     * Search for entities that match a given sub-string.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param categories
     *     Type of entities to search for
     * @param character_id
     *     An EVE character ID
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public default R_get_characters_character_id_search get_characters_character_id_search(String[] categories, int character_id, Swagger.language language, String search, Boolean strict, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/search/".replace("{character_id}", ""+character_id)+"?"+(categories==null?"":"&categories="+flatten(categories))+(language==null?"":"&language="+flatten(language))+(search==null?"":"&search="+flatten(search))+(strict==null?"":"&strict="+flatten(strict)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_search.class));
    }

    /**
     * Get corporation assets
     * <p>
     * Return a list of the corporation assets<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_ASSETS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_assets_8 [] get_corporations_corporation_id_assets(int corporation_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/assets/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_assets_8[].class));
    }

    /**
     * Get corporation members
     * <p>
     * Return the current member list of a corporation, the token's character need to be a member of the corporation.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default int[] get_corporations_corporation_id_members(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/members/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (int[].class));
    }

    /**
     * Get corporation wallet journal
     * <p>
     * Retrieve the given corporation's wallet journal for the given division going 30 days back<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Junior_Accountant
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_JOURNAL_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param division
     *     Wallet key of the division to fetch journals from
     * @param page
     *     Which page of results to return
     */
    public default M_get_journal_13 [] get_corporations_corporation_id_wallets_division_journal(int corporation_id, int division, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/wallets/{division}/journal/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_journal_13[].class));
    }

    /**
     * Get solar system information
     * <p>
     * Get information on a solar system. NOTE: This route does not work with abyssal systems.<br />
     * This route expires daily at 11:05<br />
     * Warning: This route has an upgrade available.<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/universe/systems/{system_id}/)
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param system_id
     *     system_id integer
     */
    public default R_get_universe_systems_system_id get_universe_systems_system_id(Swagger.language language, int system_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/universe/systems/{system_id}/".replace("{system_id}", ""+system_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_systems_system_id.class));
    }

    /**
     * Get type information
     * <p>
     * Get information on a type<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param language
     *     Language to use in the response, takes precedence over Accept-Language
     * @param type_id
     *     An Eve item type ID
     */
    public default R_get_universe_types_type_id get_universe_types_type_id(Swagger.language language, int type_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v3/universe/types/{type_id}/".replace("{type_id}", ""+type_id)+"?"+(language==null?"":"&language="+flatten(language)));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_types_type_id.class));
    }

    /**
     * Get character's public information
     * <p>
     * Public information about a character<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id get_characters_character_id(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id.class));
    }

    /**
     * Calculate a CSPA charge cost
     * <p>
     * Takes a source character ID in the url and a set of target character ID's in the body, returns a CSPA charge cost
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param characters
     *     The target characters to calculate the charge for
     */
    public default float post_characters_character_id_cspa(int character_id, int[] characters, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/cspa/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("characters", characters);
        String fetched = connectPost(url, content, true, headerHandler);
        return convert((fetched), (float.class));
    }

    /**
     * Get character skills
     * <p>
     * List all trained skills for the given character<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default R_get_characters_character_id_skills get_characters_character_id_skills(int character_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/skills/".replace("{character_id}", ""+character_id));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_skills.class));
    }

    /**
     * Get character wallet journal
     * <p>
     * Retrieve the given character's wallet journal going 30 days back<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param page
     *     Which page of results to return
     */
    public default M_get_journal_13 [] get_characters_character_id_wallet_journal(int character_id, Integer page, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/wallet/journal/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        String fetched=connectGet(url,true, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_journal_13[].class));
    }

    /**
     * Get corporation information
     * <p>
     * Public information about a corporation<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     */
    public default R_get_corporations_corporation_id get_corporations_corporation_id(int corporation_id, Map<String, List<String>> headerHandler) {
        String url = ("https://esi.evetech.net/v4/corporations/{corporation_id}/".replace("{corporation_id}", ""+corporation_id));
        String fetched=connectGet(url,false, headerHandler);
        return convert((fetched), (fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id.class));
    }

    public static enum flag {
        shortest("shortest"),
        secure("secure"),
        insecure("insecure");
        public final String toString;

        flag(String toString) {
            this.toString = toString;
        }

        @Override
        public String toString() {
            return toString;
        }
    }

    public static enum language {
        de("de"),
        en_us("en-us"),
        fr("fr"),
        ja("ja"),
        ru("ru"),
        zh("zh");
        public final String toString;

        language(String toString) {
            this.toString = toString;
        }

        @Override
        public String toString() {
            return toString;
        }
    }

    public static enum order_type {
        buy("buy"),
        sell("sell"),
        all("all");
        public final String toString;

        order_type(String toString) {
            this.toString = toString;
        }

        @Override
        public String toString() {
            return toString;
        }
    }
}
