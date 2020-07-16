package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ITransfer;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_22;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_bids_4;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_items_6;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_post_assets_locations_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_post_assets_names_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_contacts;
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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fittings_items;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fleet;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_location;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_labels;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_lists;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_mail_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_recipients;
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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_journal;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_extractions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_journal;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_members;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_wings;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_character_id_fittings_created;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_fleets_fleet_id_wings_created;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_fleets_fleet_id_wings_wing_id_squads_created;


/**
 * interface to access the ESI with a connected account.<br />This typically gives access to the character information, corporation, etc.
 */
public interface G_ICOAccess
    extends ITransfer
{
    public static final String[] SCOPES = new String[] {"esi-characters.write_contacts.v1", "esi-skills.read_skills.v1", "esi-characters.read_fatigue.v1", "esi-corporations.read_divisions.v1", "esi-corporations.read_corporation_membership.v1", "esi-bookmarks.read_character_bookmarks.v1", "esi-assets.read_corporation_assets.v1", "esi-fittings.read_fittings.v1", "esi-contracts.read_corporation_contracts.v1", "esi-fleets.write_fleet.v1", "esi-ui.write_waypoint.v1", "esi-industry.read_character_jobs.v1", "esi-bookmarks.read_corporation_bookmarks.v1", "esi-industry.read_character_mining.v1", "esi-clones.read_clones.v1", "esi-characters.read_agents_research.v1", "esi-calendar.respond_calendar_events.v1", "esi-location.read_online.v1", "esi-mail.read_mail.v1", "esi-search.search_structures.v1", "esi-corporations.read_contacts.v1", "esi-corporations.read_container_logs.v1", "esi-characters.read_contacts.v1", "esi-fittings.write_fittings.v1", "esi-markets.structure_markets.v1", "esi-wallet.read_corporation_wallets.v1", "esi-characters.read_corporation_roles.v1", "esi-wallet.read_character_wallet.v1", "esi-assets.read_assets.v1", "esi-killmails.read_killmails.v1", "esi-characters.read_medals.v1", "esi-location.read_ship_type.v1", "esi-skills.read_skillqueue.v1", "esi-contracts.read_character_contracts.v1", "esi-mail.send_mail.v1", "esi-alliances.read_contacts.v1", "esi-location.read_location.v1", "esi-ui.open_window.v1", "esi-fleets.read_fleet.v1", "esi-industry.read_corporation_mining.v1", "esi-corporations.read_blueprints.v1", "esi-calendar.read_calendar_events.v1", "esi-markets.read_character_orders.v1", "esi-markets.read_corporation_orders.v1", "esi-characters.read_notifications.v1", "esi-characters.read_standings.v1", "esi-characters.read_opportunities.v1", "esi-corporations.read_standings.v1", "esi-industry.read_corporation_jobs.v1", "esi-characters.read_fw_stats.v1", "esi-corporations.read_titles.v1", "esi-universe.read_structures.v1", "esi-corporations.track_members.v1", "esi-corporations.read_fw_stats.v1", "esi-characters.read_loyalty.v1", "esi-mail.organize_mail.v1", "esi-corporations.read_structures.v1", "esi-corporations.read_starbases.v1", "esi-clones.read_implants.v1", "esi-killmails.read_corporation_killmails.v1", "esi-corporations.read_medals.v1", "esi-planets.manage_planets.v1", "esi-characters.read_titles.v1", "esi-corporations.read_facilities.v1", "esi-planets.read_customs_offices.v1", "esi-characters.read_blueprints.v1"};
    /**
     * the roles required for {@link #get_corporation_mining_extractions this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_EXTRACTIONS_ROLES = new String[] {"Station_Manager"};
    /**
     * the roles required for {@link #get_corporation_mining_observers this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_ROLES = new String[] {"Accountant"};
    /**
     * the roles required for {@link #get_corporation_mining_observers this method}
     */
    public static final String[] GET_CORPORATION_CORPORATION_ID_MINING_OBSERVERS_OBSERVER_ID_ROLES = new String[] {"Accountant"};
    /**
     * the roles required for {@link #post_corporations_assets_names this method}
     */
    public static final String[] POST_CORPORATIONS_CORPORATION_ID_ASSETS_NAMES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_customs_offices this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_CUSTOMS_OFFICES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_divisions this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_DIVISIONS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_facilities this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_FACILITIES_ROLES = new String[] {"Factory_Manager"};
    /**
     * the roles required for {@link #get_corporations_industry_jobs this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES = new String[] {"Factory_Manager"};
    /**
     * the roles required for {@link #get_corporations_killmails_recent this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_KILLMAILS_RECENT_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_medals_issued this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEDALS_ISSUED_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_members_limit this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERS_LIMIT_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_members_titles this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERS_TITLES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_membertracking this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_MEMBERTRACKING_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_roles_history this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ROLES_HISTORY_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_shareholders this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_SHAREHOLDERS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_starbases this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STARBASES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_starbases this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STARBASES_STARBASE_ID_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_titles this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_TITLES_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_wallets this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_ROLES = new String[] {"Accountant", "Junior_Accountant"};
    /**
     * the roles required for {@link #get_corporations_wallets_transactions this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_TRANSACTIONS_ROLES = new String[] {"Accountant", "Junior_Accountant"};
    /**
     * the roles required for {@link #post_corporations_assets_locations this method}
     */
    public static final String[] POST_CORPORATIONS_CORPORATION_ID_ASSETS_LOCATIONS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_blueprints this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_BLUEPRINTS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_containers_logs this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_CONTAINERS_LOGS_ROLES = new String[] {"Director"};
    /**
     * the roles required for {@link #get_corporations_orders_history this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ORDERS_HISTORY_ROLES = new String[] {"Accountant", "Trader"};
    /**
     * the roles required for {@link #get_corporations_orders this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ORDERS_ROLES = new String[] {"Accountant", "Trader"};
    /**
     * the roles required for {@link #get_corporations_structures this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_STRUCTURES_ROLES = new String[] {"Station_Manager"};
    /**
     * the roles required for {@link #get_corporations_wallets_journal this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_WALLETS_DIVISION_JOURNAL_ROLES = new String[] {"Accountant", "Junior_Accountant"};
    /**
     * the roles required for {@link #get_corporations_assets this method}
     */
    public static final String[] GET_CORPORATIONS_CORPORATION_ID_ASSETS_ROLES = new String[] {"Director"};

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
    public default Requested<M_get_contacts_labels_2 []> get_alliances_contacts_labels(int alliance_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/contacts/labels/".replace("{alliance_id}", ""+alliance_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2[].class));
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
    public default Requested<R_get_characters_character_id_agents_research[]> get_characters_agents_research(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/agents_research/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_agents_research[].class));
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
    public default Requested<M_post_assets_names_2 []> post_characters_assets_names(int character_id, long[] item_ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/assets/names/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        return requestPost(url, properties, content, M_post_assets_names_2 [].class);
    }

    /**
     * Get character attributes
     * <p>
     * Return attributes of a character<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<R_get_characters_character_id_attributes> get_characters_attributes(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/attributes/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes.class));
    }

    /**
     * List calendar event summaries
     * <p>
     * Get 50 event summaries from the calendar. If no from_event ID is given, the resource will return the next 50 chronological event summaries from now. If a from_event ID is specified, it will return the next 50 chronological event summaries from after that event<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param from_event
     *     The event ID to retrieve events from
     */
    public default Requested<R_get_characters_character_id_calendar[]> get_characters_calendar(int character_id, Integer from_event, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/calendar/".replace("{character_id}", ""+character_id)+"?"+(from_event==null?"":"&from_event="+flatten(from_event)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar[].class));
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
    public default Requested<R_get_characters_character_id_calendar_event_id_attendees[]> get_characters_calendar_attendees(int character_id, int event_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/calendar/{event_id}/attendees/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar_event_id_attendees[].class));
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
    public default Requested<M_get_contacts_labels_2 []> get_characters_contacts_labels(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contacts/labels/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2[].class));
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
    public default Requested<M_get_contracts_22 []> get_characters_contracts(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_22[].class));
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
    public default Requested<M_get_contracts_contract_bids_4 []> get_characters_contracts_bids(int character_id, int contract_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/bids/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_bids_4[].class));
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
    public default Requested<M_get_contracts_contract_items_6 []> get_characters_contracts_items(int character_id, int contract_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/items/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_items_6[].class));
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
    public default Requested<R_get_characters_character_id_fatigue> get_characters_fatigue(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fatigue/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fatigue.class));
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
    public default Requested<Void> delete_characters_fittings(int character_id, int fitting_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fittings/{fitting_id}/".replace("{character_id}", ""+character_id).replace("{fitting_id}", ""+fitting_id));
        return (requestDel(url, properties));
    }

    /**
     * Get character fleet info
     * <p>
     * Return the fleet ID the character is in, if any.<br />
     * This route is cached for up to 60 seconds<br />
     * Warning: This route has an upgrade available<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/characters/{character_id}/fleet/)
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<R_get_characters_character_id_fleet> get_characters_fleet(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fleet/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fleet.class));
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
    public default Requested<R_get_characters_character_id_fw_stats> get_characters_fw_stats(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/fw/stats/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fw_stats.class));
    }

    /**
     * Get active implants
     * <p>
     * Return implants on the active clone of a character<br />
     * This route is cached for up to 120 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<Integer[]> get_characters_implants(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/implants/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,java.lang.Integer[].class));
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
     *     Whether to retrieve completed character industry jobs. Only includes jobs from the past 90 days
     */
    public default Requested<R_get_characters_character_id_industry_jobs[]> get_characters_industry_jobs(int character_id, Boolean include_completed, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/industry/jobs/".replace("{character_id}", ""+character_id)+"?"+(include_completed==null?"":"&include_completed="+flatten(include_completed)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_industry_jobs[].class));
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
    public default Requested<M_get_killmails_2 []> get_characters_killmails_recent(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/killmails/recent/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2[].class));
    }

    /**
     * Get character location
     * <p>
     * Information about the characters current location. Returns the current solar system id, and also the current station or structure ID if applicable<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<R_get_characters_character_id_location> get_characters_location(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/location/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_location.class));
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
    public default Requested<R_get_characters_character_id_loyalty_points[]> get_characters_loyalty_points(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/loyalty/points/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_loyalty_points[].class));
    }

    /**
     * Return mail headers
     * <p>
     * Return the 50 most recent mail headers belonging to the character that match the query criteria. Queries can be filtered by label, and last_mail_id can be used to paginate backwards<br />
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
    public default Requested<R_get_characters_character_id_mail[]> get_characters_mail(int character_id,
        int[] labels,
        Integer last_mail_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/".replace("{character_id}", ""+character_id)+"?"+(labels==null?"":"&labels="+flatten(labels))+(last_mail_id==null?"":"&last_mail_id="+flatten(last_mail_id)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail[].class));
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
    public default Requested<Integer> post_characters(int character_id,
        long approved_cost,
        String body,
        R_get_characters_character_id_mail_recipients[] recipients,
        String subject,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("approved_cost", approved_cost);
        content.put("body", body);
        content.put("recipients", recipients);
        content.put("subject", subject);
        return requestPost(url, properties, content, Integer.class);
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
    public default Requested<Void> delete_characters_mail_labels(int character_id, int label_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/labels/{label_id}/".replace("{character_id}", ""+character_id).replace("{label_id}", ""+label_id));
        return (requestDel(url, properties));
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
    public default Requested<R_get_characters_character_id_mail_lists[]> get_characters_mail_lists(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/lists/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_lists[].class));
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
    public default Requested<R_get_characters_character_id_mail_mail_id> get_characters_mail(int character_id, int mail_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_mail_id.class));
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
    public default Requested<Void> put_characters_mail(int character_id,
        int[] labels,
        boolean read,
        int mail_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        Map<String, Object> content = new HashMap<>();
        content.put("labels", labels);
        content.put("read", read);
        return requestPut(url, properties, content);
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
    public default Requested<Void> delete_characters_mail(int character_id, int mail_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id));
        return (requestDel(url, properties));
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
    public default Requested<R_get_characters_character_id_medals[]> get_characters_medals(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/medals/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_medals[].class));
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
    public default Requested<R_get_characters_character_id_mining[]> get_characters_mining(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/mining/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mining[].class));
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
    public default Requested<R_get_characters_character_id_notifications_contacts[]> get_characters_notifications_contacts(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/notifications/contacts/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications_contacts[].class));
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
    public default Requested<R_get_characters_character_id_opportunities[]> get_characters_opportunities(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/opportunities/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_opportunities[].class));
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
    public default Requested<R_get_characters_character_id_orders_history[]> get_characters_orders_history(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/orders/history/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders_history[].class));
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
    public default Requested<R_get_characters_character_id_planets[]> get_characters_planets(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/planets/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets[].class));
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
    public default Requested<R_get_characters_character_id_ship> get_characters_ship(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/ship/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_ship.class));
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
    public default Requested<M_get_standings_3 []> get_characters_standings(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/standings/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3[].class));
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
    public default Requested<R_get_characters_character_id_titles[]> get_characters_titles(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/titles/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_titles[].class));
    }

    /**
     * Get a character's wallet balance
     * <p>
     * Returns a character's wallet balance<br />
     * This route is cached for up to 120 seconds<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/characters/{character_id}/wallet/)
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<Double> get_characters_wallet(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/wallet/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,double.class));
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
    public default Requested<R_get_characters_character_id_wallet_transactions[]> get_characters_wallet_transactions(int character_id, Long from_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/wallet/transactions/".replace("{character_id}", ""+character_id)+"?"+(from_id==null?"":"&from_id="+flatten(from_id)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions[].class));
    }

    /**
     * Moon extraction timers
     * <p>
     * Extraction timers for all moon chunks being extracted by refineries belonging to a corporation.<br />
     * This route is cached for up to 1800 seconds<br />
     * Requires one of the following EVE corporation role(s): Station_Manager
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
    public default Requested<R_get_corporation_corporation_id_mining_extractions[]> get_corporation_mining_extractions(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/extractions/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_extractions[].class));
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
    public default Requested<R_get_corporation_corporation_id_mining_observers[]> get_corporation_mining_observers(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/observers/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers[].class));
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
    public default Requested<R_get_corporation_corporation_id_mining_observers_observer_id[]> get_corporation_mining_observers(int corporation_id,
        long observer_id,
        Integer page,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporation/{corporation_id}/mining/observers/{observer_id}/".replace("{corporation_id}", ""+corporation_id).replace("{observer_id}", ""+observer_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporation_corporation_id_mining_observers_observer_id[].class));
    }

    /**
     * Get corporation asset names
     * <p>
     * Return names for a set of item ids, which you can get from corporation assets endpoint. Only valid for items that can customize names, like containers or ships<br />
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
    public default Requested<M_post_assets_names_2 []> post_corporations_assets_names(int corporation_id, long[] item_ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/assets/names/".replace("{corporation_id}", ""+corporation_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        return requestPost(url, properties, content, M_post_assets_names_2 [].class);
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
    public default Requested<M_get_bookmarks_9 []> get_corporations_bookmarks(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/bookmarks/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9[].class));
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
    public default Requested<R_get_corporations_corporation_id_bookmarks_folders[]> get_corporations_bookmarks_folders(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/bookmarks/folders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders[].class));
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
    public default Requested<M_get_contacts_labels_2 []> get_corporations_contacts_labels(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contacts/labels/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contacts_labels_2[].class));
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
    public default Requested<M_get_contracts_22 []> get_corporations_contracts(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_22[].class));
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
    public default Requested<M_get_contracts_contract_bids_4 []> get_corporations_contracts_bids(int contract_id,
        int corporation_id,
        Integer page,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/bids/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_bids_4[].class));
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
    public default Requested<M_get_contracts_contract_items_6 []> get_corporations_contracts_items(int contract_id, int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/items/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_contracts_contract_items_6[].class));
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
    public default Requested<R_get_corporations_corporation_id_customs_offices[]> get_corporations_customs_offices(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/customs_offices/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_customs_offices[].class));
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
    public default Requested<R_get_corporations_corporation_id_divisions> get_corporations_divisions(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/divisions/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_divisions.class));
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
    public default Requested<R_get_corporations_corporation_id_facilities[]> get_corporations_facilities(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/facilities/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_facilities[].class));
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
    public default Requested<R_get_corporations_corporation_id_fw_stats> get_corporations_fw_stats(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/fw/stats/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_fw_stats.class));
    }

    /**
     * List corporation industry jobs
     * <p>
     * List industry jobs run by a corporation<br />
     * This route is cached for up to 300 seconds<br />
     * Requires one of the following EVE corporation role(s): Factory_Manager
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_INDUSTRY_JOBS_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param include_completed
     *     Whether to retrieve completed corporation industry jobs. Only includes jobs from the past 90 days
     * @param page
     *     Which page of results to return
     */
    public default Requested<R_get_corporations_corporation_id_industry_jobs[]> get_corporations_industry_jobs(int corporation_id,
        Boolean include_completed,
        Integer page,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/industry/jobs/".replace("{corporation_id}", ""+corporation_id)+"?"+(include_completed==null?"":"&include_completed="+flatten(include_completed))+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_industry_jobs[].class));
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
    public default Requested<M_get_killmails_2 []> get_corporations_killmails_recent(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/killmails/recent/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2[].class));
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
    public default Requested<R_get_corporations_corporation_id_medals[]> get_corporations_medals(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/medals/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_medals[].class));
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
    public default Requested<R_get_corporations_corporation_id_medals_issued[]> get_corporations_medals_issued(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/medals/issued/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_medals_issued[].class));
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
    public default Requested<Integer> get_corporations_members_limit(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/members/limit/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,int.class));
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
    public default Requested<R_get_corporations_corporation_id_members_titles[]> get_corporations_members_titles(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/members/titles/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_members_titles[].class));
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
    public default Requested<R_get_corporations_corporation_id_membertracking[]> get_corporations_membertracking(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/membertracking/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_membertracking[].class));
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
    public default Requested<R_get_corporations_corporation_id_roles[]> get_corporations_roles(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/roles/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles[].class));
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
    public default Requested<R_get_corporations_corporation_id_roles_history[]> get_corporations_roles_history(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/roles/history/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_roles_history[].class));
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
    public default Requested<R_get_corporations_corporation_id_shareholders[]> get_corporations_shareholders(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/shareholders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_shareholders[].class));
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
    public default Requested<M_get_standings_3 []> get_corporations_standings(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/standings/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3[].class));
    }

    /**
     * Get corporation starbases (POSes)
     * <p>
     * Returns list of corporation starbases (POSes)<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Director<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/corporations/{corporation_id}/starbases/)
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
    public default Requested<R_get_corporations_corporation_id_starbases[]> get_corporations_starbases(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/starbases/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_starbases[].class));
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
    public default Requested<R_get_corporations_corporation_id_starbases_starbase_id> get_corporations_starbases(int corporation_id,
        long starbase_id,
        int system_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/starbases/{starbase_id}/".replace("{corporation_id}", ""+corporation_id).replace("{starbase_id}", ""+starbase_id)+"?"+"&system_id="+flatten(system_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_starbases_starbase_id.class));
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
    public default Requested<R_get_corporations_corporation_id_titles[]> get_corporations_titles(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/titles/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_titles[].class));
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
    public default Requested<R_get_corporations_corporation_id_wallets[]> get_corporations_wallets(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/wallets/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets[].class));
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
    public default Requested<R_get_corporations_corporation_id_wallets_division_transactions[]> get_corporations_wallets_transactions(int corporation_id,
        int division,
        Long from_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/wallets/{division}/transactions/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division)+"?"+(from_id==null?"":"&from_id="+flatten(from_id)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions[].class));
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
    public default Requested<R_get_fleets_fleet_id> get_fleets(long fleet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id.class));
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
    public default Requested<Void> put_fleets(long fleet_id,
        boolean is_free_move,
        String motd,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id));
        Map<String, Object> content = new HashMap<>();
        content.put("is_free_move", is_free_move);
        content.put("motd", motd);
        return requestPut(url, properties, content);
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
     */
    public default Requested<R_get_fleets_fleet_id_members[]> get_fleets_members(long fleet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_members[].class));
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
    public default Requested<Void> post_fleets_members(long fleet_id,
        int character_id,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.role role,
        long squad_id,
        long wing_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id));
        Map<String, Object> content = new HashMap<>();
        content.put("character_id", character_id);
        content.put("role", role);
        content.put("squad_id", squad_id);
        content.put("wing_id", wing_id);
        return requestPost(url, properties, content, Void.class);
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
    public default Requested<Void> put_fleets_members(long fleet_id,
        int member_id,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.role role,
        long squad_id,
        long wing_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/{member_id}/".replace("{fleet_id}", ""+fleet_id).replace("{member_id}", ""+member_id));
        Map<String, Object> content = new HashMap<>();
        content.put("role", role);
        content.put("squad_id", squad_id);
        content.put("wing_id", wing_id);
        return requestPut(url, properties, content);
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
    public default Requested<Void> delete_fleets_members(long fleet_id, int member_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/members/{member_id}/".replace("{fleet_id}", ""+fleet_id).replace("{member_id}", ""+member_id));
        return (requestDel(url, properties));
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
    public default Requested<Void> put_fleets_squads(long fleet_id,
        String name,
        long squad_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/squads/{squad_id}/".replace("{fleet_id}", ""+fleet_id).replace("{squad_id}", ""+squad_id));
        Map<String, Object> content = new HashMap<>();
        content.put("name", name);
        return requestPut(url, properties, content);
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
    public default Requested<Void> delete_fleets_squads(long fleet_id, long squad_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/squads/{squad_id}/".replace("{fleet_id}", ""+fleet_id).replace("{squad_id}", ""+squad_id));
        return (requestDel(url, properties));
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
     */
    public default Requested<R_get_fleets_fleet_id_wings[]> get_fleets_wings(long fleet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fleets_fleet_id_wings[].class));
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
    public default Requested<R_post_fleets_fleet_id_wings_created> post_fleets_wings(long fleet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id));
        return requestPost(url, properties, null, R_post_fleets_fleet_id_wings_created.class);
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
    public default Requested<Void> put_fleets_wings(long fleet_id,
        String name,
        long wing_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        Map<String, Object> content = new HashMap<>();
        content.put("name", name);
        return requestPut(url, properties, content);
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
    public default Requested<Void> delete_fleets_wings(long fleet_id, long wing_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        return (requestDel(url, properties));
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
    public default Requested<R_post_fleets_fleet_id_wings_wing_id_squads_created> post_fleets_wings_squads(long fleet_id, long wing_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fleets/{fleet_id}/wings/{wing_id}/squads/".replace("{fleet_id}", ""+fleet_id).replace("{wing_id}", ""+wing_id));
        return requestPost(url, properties, null, R_post_fleets_fleet_id_wings_wing_id_squads_created.class);
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
    public default Requested<R_get_markets_structures_structure_id[]> get_markets_structures(Integer page, long structure_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/structures/{structure_id}/".replace("{structure_id}", ""+structure_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_structures_structure_id[].class));
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
    public default Requested<Void> post_ui_openwindow_contract(int contract_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/contract/"+"?"+"&contract_id="+flatten(contract_id));
        return requestPost(url, properties, null, Void.class);
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
    public default Requested<Void> post_ui_openwindow_information(int target_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/information/"+"?"+"&target_id="+flatten(target_id));
        return requestPost(url, properties, null, Void.class);
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
    public default Requested<Void> post_ui_openwindow_marketdetails(int type_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/marketdetails/"+"?"+"&type_id="+flatten(type_id));
        return requestPost(url, properties, null, Void.class);
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
    public default Requested<Void> post_ui_openwindow_newmail(String body,
        int[] recipients,
        String subject,
        int to_corp_or_alliance_id,
        int to_mailing_list_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/ui/openwindow/newmail/");
        Map<String, Object> content = new HashMap<>();
        content.put("body", body);
        content.put("recipients", recipients);
        content.put("subject", subject);
        content.put("to_corp_or_alliance_id", to_corp_or_alliance_id);
        content.put("to_mailing_list_id", to_mailing_list_id);
        return requestPost(url, properties, content, Void.class);
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
    public default Requested<R_get_alliances_alliance_id_contacts[]> get_alliances_contacts(int alliance_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/alliances/{alliance_id}/contacts/".replace("{alliance_id}", ""+alliance_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_contacts[].class));
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
    public default Requested<M_post_assets_locations_2 []> post_characters_assets_locations(int character_id, long[] item_ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/assets/locations/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        return requestPost(url, properties, content, M_post_assets_locations_2 [].class);
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
    public default Requested<R_get_characters_character_id_blueprints[]> get_characters_blueprints(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/blueprints/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_blueprints[].class));
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
    public default Requested<M_get_bookmarks_9 []> get_characters_bookmarks(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/bookmarks/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9[].class));
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
    public default Requested<R_get_characters_character_id_bookmarks_folders[]> get_characters_bookmarks_folders(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/bookmarks/folders/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_bookmarks_folders[].class));
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
    public default Requested<R_get_characters_character_id_contacts[]> get_characters_contacts(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts[].class));
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
    public default Requested<Void> put_characters_contacts(int character_id,
        int[] contact_ids,
        long[] label_ids,
        float standing,
        Boolean watched,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(label_ids==null?"":"&label_ids="+flatten(label_ids))+"&standing="+flatten(standing)+(watched==null?"":"&watched="+flatten(watched)));
        Map<String, Object> content = new HashMap<>();
        content.put("contact_ids", contact_ids);
        return requestPut(url, properties, content);
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
    public default Requested<Void> delete_characters_contacts(int character_id, int[] contact_ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(contact_ids==null?"":"&contact_ids="+flatten(contact_ids)));
        return (requestDel(url, properties));
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
    public default Requested<Integer[]> post_characters_contacts(int character_id,
        int[] contact_ids,
        long[] label_ids,
        float standing,
        Boolean watched,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id)+"?"+(label_ids==null?"":"&label_ids="+flatten(label_ids))+"&standing="+flatten(standing)+(watched==null?"":"&watched="+flatten(watched)));
        Map<String, Object> content = new HashMap<>();
        content.put("contact_ids", contact_ids);
        return requestPost(url, properties, content, Integer[].class);
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
    public default Requested<R_get_characters_character_id_fittings[]> get_characters_fittings(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_fittings[].class));
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
    public default Requested<R_post_characters_character_id_fittings_created> post_characters_s(int character_id,
        String description,
        R_get_characters_character_id_fittings_items[] items,
        String name,
        int ship_type_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("description", description);
        content.put("items", items);
        content.put("name", name);
        content.put("ship_type_id", ship_type_id);
        return requestPost(url, properties, content, R_post_characters_character_id_fittings_created.class);
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
    public default Requested<Integer> post_characters_mail_s(int character_id,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.color color,
        String name,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("color", color);
        content.put("name", name);
        return requestPost(url, properties, content, Integer.class);
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
    public default Requested<R_get_characters_character_id_online> get_characters_online(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/online/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_online.class));
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
    public default Requested<R_get_characters_character_id_orders[]> get_characters_orders(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/orders/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders[].class));
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
    public default Requested<R_get_characters_character_id_roles> get_characters_roles(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/roles/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles.class));
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
    public default Requested<R_get_characters_character_id_skillqueue[]> get_characters_skillqueue(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/skillqueue/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skillqueue[].class));
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
    public default Requested<M_post_assets_locations_2 []> post_corporations_assets_locations(int corporation_id, long[] item_ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/assets/locations/".replace("{corporation_id}", ""+corporation_id));
        Map<String, Object> content = new HashMap<>();
        content.put("item_ids", item_ids);
        return requestPost(url, properties, content, M_post_assets_locations_2 [].class);
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
    public default Requested<R_get_corporations_corporation_id_blueprints[]> get_corporations_blueprints(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/blueprints/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_blueprints[].class));
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
    public default Requested<R_get_corporations_corporation_id_contacts[]> get_corporations_contacts(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/contacts/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_contacts[].class));
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
    public default Requested<R_get_corporations_corporation_id_containers_logs[]> get_corporations_containers_logs(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/containers/logs/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_containers_logs[].class));
    }

    /**
     * List historical orders from a corporation
     * <p>
     * List cancelled and expired market orders placed on behalf of a corporation up to 90 days in the past.<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Trader
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
    public default Requested<R_get_corporations_corporation_id_orders_history[]> get_corporations_orders_history(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/orders/history/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders_history[].class));
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
    public default Requested<Void> post_ui_autopilot_waypoint(boolean add_to_beginning,
        boolean clear_other_waypoints,
        long destination_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/ui/autopilot/waypoint/"+"?"+"&add_to_beginning="+flatten(add_to_beginning)+"&clear_other_waypoints="+flatten(clear_other_waypoints)+"&destination_id="+flatten(destination_id));
        return requestPost(url, properties, null, Void.class);
    }

    /**
     * Get structure information
     * <p>
     * Returns information on requested structure if you are on the ACL. Otherwise, returns "Forbidden" for all inputs.<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param structure_id
     *     An Eve structure ID
     */
    public default Requested<R_get_universe_structures_structure_id> get_universe_structures(long structure_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/universe/structures/{structure_id}/".replace("{structure_id}", ""+structure_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_structures_structure_id.class));
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
    public default Requested<R_get_characters_character_id_calendar_event_id> get_characters_calendar(int character_id, int event_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_calendar_event_id.class));
    }

    /**
     * Respond to an event
     * <p>
     * Set your response status to an event<br />
     * This route is cached for up to 5 seconds
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param event_id
     *     The ID of the event requested
     * @param response
     *     response string
     */
    public default Requested<Void> put_characters_calendar(int character_id,
        int event_id,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.response response,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id));
        Map<String, Object> content = new HashMap<>();
        content.put("response", response);
        return requestPut(url, properties, content);
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
    public default Requested<R_get_characters_character_id_clones> get_characters_clones(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/clones/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_clones.class));
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
    public default Requested<R_get_characters_character_id_mail_labels> get_characters_mail_labels(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_mail_labels.class));
    }

    /**
     * Get colony layout
     * <p>
     * Returns full details on the layout of a single planetary colony, including links, pins and routes. Note: Planetary information is only recalculated when the colony is viewed through the client. Information will not update until this criteria is met.
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     * @param planet_id
     *     Planet id of the target planet
     */
    public default Requested<R_get_characters_character_id_planets_planet_id> get_characters_planets(int character_id, int planet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/planets/{planet_id}/".replace("{character_id}", ""+character_id).replace("{planet_id}", ""+planet_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets_planet_id.class));
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
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public default Requested<R_get_characters_character_id_search> get_characters(String[] categories,
        int character_id,
        String search,
        Boolean strict,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/characters/{character_id}/search/".replace("{character_id}", ""+character_id)+"?"+(categories==null?"":"&categories="+flatten(categories))+(search==null?"":"&search="+flatten(search))+(strict==null?"":"&strict="+flatten(strict)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_search.class));
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
    public default Requested<Integer[]> get_corporations_members(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/members/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * List open orders from a corporation
     * <p>
     * List open market orders placed on behalf of a corporation<br />
     * This route is cached for up to 1200 seconds<br />
     * Requires one of the following EVE corporation role(s): Accountant, Trader
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
    public default Requested<R_get_corporations_corporation_id_orders[]> get_corporations_orders(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/orders/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders[].class));
    }

    /**
     * Get corporation structures
     * <p>
     * Get a list of corporation structures. This route's version includes the changes to structures detailed in this blog: https://www.eveonline.com/article/upwell-2.0-structures-changes-coming-on-february-13th<br />
     * This route is cached for up to 3600 seconds<br />
     * Requires one of the following EVE corporation role(s): Station_Manager<br />
     * [Diff of the upcoming changes](https://esi.evetech.net/diff/latest/dev/#GET-/corporations/{corporation_id}/structures/)
     * </p>
     * <p>
     * require the roles specified {@link #GET_CORPORATIONS_CORPORATION_ID_STRUCTURES_ROLES here}
     * </p>
     * 
     * @param corporation_id
     *     An EVE corporation ID
     * @param page
     *     Which page of results to return
     */
    public default Requested<R_get_corporations_corporation_id_structures[]> get_corporations_structures(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/corporations/{corporation_id}/structures/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_structures[].class));
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
    public default Requested<Float> post__cspa(int character_id, int[] characters, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/cspa/".replace("{character_id}", ""+character_id));
        Map<String, Object> content = new HashMap<>();
        content.put("characters", characters);
        return requestPost(url, properties, content, Float.class);
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
    public default Requested<R_get_characters_character_id_skills> get_characters_skills(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/skills/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skills.class));
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
    public default Requested<R_get_corporations_corporation_id_wallets_division_journal[]> get_corporations_wallets_journal(int corporation_id,
        int division,
        Integer page,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/corporations/{corporation_id}/wallets/{division}/journal/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_journal[].class));
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
    public default Requested<R_get_characters_character_id_assets[]> get_characters_assets(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v5/characters/{character_id}/assets/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets[].class));
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
    public default Requested<R_get_characters_character_id_notifications[]> get_characters_notifications(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v5/characters/{character_id}/notifications/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_notifications[].class));
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
    public default Requested<R_get_corporations_corporation_id_assets[]> get_corporations_assets(int corporation_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v5/corporations/{corporation_id}/assets/".replace("{corporation_id}", ""+corporation_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets[].class));
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
    public default Requested<R_get_characters_character_id_wallet_journal[]> get_characters_wallet_journal(int character_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v6/characters/{character_id}/wallet/journal/".replace("{character_id}", ""+character_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_journal[].class));
    }
}
