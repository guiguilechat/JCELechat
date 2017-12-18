package is.ccp.tech.esi;

import fr.guiguilechat.eveonline.model.esi.RequestHandler;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id_contacts_ok;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id_icons_ok;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id_ok;
import is.ccp.tech.esi.responses.R_get_alliances_names_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_agents_research_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_assets_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_attributes_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_blueprints_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_folders_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_attendees_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_chat_channels_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_clones_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_labels_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_bids_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_items_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_corporationhistory_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fatigue_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fittings_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fleet_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fw_stats_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_killmails_recent_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_location_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_loyalty_points_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_labels_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_lists_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_mail_id_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_medals_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mining_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_contacts_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_opportunities_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_orders_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_planets_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_planets_planet_id_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_portrait_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_search_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_ship_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_skillqueue_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_skills_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_standings_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_stats_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_titles_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_journal_ok;
import is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_transactions_ok;
import is.ccp.tech.esi.responses.R_get_characters_names_ok;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_extractions_ok;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_observer_id_ok;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_alliancehistory_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_assets_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_blueprints_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_folders_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contacts_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_containers_logs_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_bids_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_items_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_customs_offices_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_divisions_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_facilities_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_fw_stats_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_icons_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_killmails_recent_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_issued_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_titles_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_membertracking_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_orders_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_outposts_outpost_id_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_history_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_shareholders_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_standings_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_starbase_id_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_structures_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_titles_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_journal_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_transactions_ok;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_ok;
import is.ccp.tech.esi.responses.R_get_corporations_names_ok;
import is.ccp.tech.esi.responses.R_get_dogma_attributes_attribute_id_ok;
import is.ccp.tech.esi.responses.R_get_dogma_effects_effect_id_ok;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id_members_ok;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id_ok;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id_wings_ok;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards_characters_ok;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards_corporations_ok;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards_ok;
import is.ccp.tech.esi.responses.R_get_fw_stats_ok;
import is.ccp.tech.esi.responses.R_get_fw_systems_ok;
import is.ccp.tech.esi.responses.R_get_fw_wars_ok;
import is.ccp.tech.esi.responses.R_get_incursions_ok;
import is.ccp.tech.esi.responses.R_get_industry_facilities_ok;
import is.ccp.tech.esi.responses.R_get_industry_systems_ok;
import is.ccp.tech.esi.responses.R_get_insurance_prices_ok;
import is.ccp.tech.esi.responses.R_get_killmails_killmail_id_killmail_hash_ok;
import is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers_ok;
import is.ccp.tech.esi.responses.R_get_markets_groups_market_group_id_ok;
import is.ccp.tech.esi.responses.R_get_markets_prices_ok;
import is.ccp.tech.esi.responses.R_get_markets_region_id_history_ok;
import is.ccp.tech.esi.responses.R_get_markets_region_id_orders_ok;
import is.ccp.tech.esi.responses.R_get_markets_structures_structure_id_ok;
import is.ccp.tech.esi.responses.R_get_opportunities_groups_group_id_ok;
import is.ccp.tech.esi.responses.R_get_opportunities_tasks_task_id_ok;
import is.ccp.tech.esi.responses.R_get_search_ok;
import is.ccp.tech.esi.responses.R_get_sovereignty_campaigns_ok;
import is.ccp.tech.esi.responses.R_get_sovereignty_map_ok;
import is.ccp.tech.esi.responses.R_get_sovereignty_structures_ok;
import is.ccp.tech.esi.responses.R_get_status_ok;
import is.ccp.tech.esi.responses.R_get_universe_bloodlines_ok;
import is.ccp.tech.esi.responses.R_get_universe_categories_category_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_constellations_constellation_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_factions_ok;
import is.ccp.tech.esi.responses.R_get_universe_graphics_graphic_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_groups_group_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_moons_moon_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_planets_planet_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_races_ok;
import is.ccp.tech.esi.responses.R_get_universe_regions_region_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_schematics_schematic_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_stargates_stargate_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_stars_star_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_stations_station_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_structures_structure_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_system_jumps_ok;
import is.ccp.tech.esi.responses.R_get_universe_system_kills_ok;
import is.ccp.tech.esi.responses.R_get_universe_systems_system_id_ok;
import is.ccp.tech.esi.responses.R_get_universe_types_type_id_ok;
import is.ccp.tech.esi.responses.R_get_wars_war_id_killmails_ok;
import is.ccp.tech.esi.responses.R_get_wars_war_id_ok;
import is.ccp.tech.esi.responses.R_post_characters_affiliation_ok;
import is.ccp.tech.esi.responses.R_post_characters_character_id_assets_locations_ok;
import is.ccp.tech.esi.responses.R_post_characters_character_id_assets_names_ok;
import is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_locations_ok;
import is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_names_ok;
import is.ccp.tech.esi.responses.R_post_universe_names_ok;

public interface Swagger
    extends RequestHandler
{

    public default R_get_alliances_alliance_id_ok get_alliances_alliance_id(int alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id_ok.class);
    }

    public default int[] get_alliances_alliance_id_corporations(int alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/corporations/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_alliances_names_ok[] get_alliances_names() {
        String url="https://esi.tech.ccp.is/latest/alliances/names/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_names_ok[].class);
    }

    public default R_get_alliances_alliance_id_icons_ok get_alliances_alliance_id_icons(int alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/icons/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id_icons_ok.class);
    }

    public default int[] get_alliances() {
        String url="https://esi.tech.ccp.is/latest/alliances/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_characters_character_id_assets_ok[] get_characters_character_id_assets(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_assets_ok[].class);
    }

    public default R_get_corporations_corporation_id_assets_ok[] get_corporations_corporation_id_assets(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_assets_ok[].class);
    }

    public default R_post_characters_character_id_assets_names_ok[] post_characters_character_id_assets_names(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/names/".replace("{character_id}", ""+character_id);
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_character_id_assets_names_ok[].class);
    }

    public default R_post_characters_character_id_assets_locations_ok[] post_characters_character_id_assets_locations(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/locations/".replace("{character_id}", ""+character_id);
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_character_id_assets_locations_ok[].class);
    }

    public default R_post_corporations_corporation_id_assets_names_ok[] post_corporations_corporation_id_assets_names(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/names/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_names_ok[].class);
    }

    public default R_post_corporations_corporation_id_assets_locations_ok[] post_corporations_corporation_id_assets_locations(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/locations/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_locations_ok[].class);
    }

    public default R_get_characters_character_id_bookmarks_ok[] get_characters_character_id_bookmarks(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/bookmarks/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_ok[].class);
    }

    public default R_get_characters_character_id_bookmarks_folders_ok[] get_characters_character_id_bookmarks_folders(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/bookmarks/folders/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_folders_ok[].class);
    }

    public default R_get_corporations_corporation_id_bookmarks_ok[] get_corporations_corporation_id_bookmarks(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/bookmarks/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_ok[].class);
    }

    public default R_get_corporations_corporation_id_bookmarks_folders_ok[] get_corporations_corporation_id_bookmarks_folders(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/bookmarks/folders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_folders_ok[].class);
    }

    public default R_get_characters_character_id_calendar_ok[] get_characters_character_id_calendar(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_ok[].class);
    }

    public default R_get_characters_character_id_calendar_event_id_ok get_characters_character_id_calendar_event_id(int character_id, int event_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_ok.class);
    }

    public default R_get_characters_character_id_calendar_event_id_attendees_ok[] get_characters_character_id_calendar_event_id_attendees(int character_id, int event_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/{event_id}/attendees/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_attendees_ok[].class);
    }

    public default R_get_characters_character_id_stats_ok[] get_characters_character_id_stats(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/stats/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_stats_ok[].class);
    }

    public default R_get_characters_character_id_ok get_characters_character_id(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_ok.class);
    }

    public default R_post_characters_affiliation_ok[] post_characters_affiliation() {
        String url="https://esi.tech.ccp.is/latest/characters/affiliation/";
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_affiliation_ok[].class);
    }

    public default R_get_characters_names_ok[] get_characters_names() {
        String url="https://esi.tech.ccp.is/latest/characters/names/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_names_ok[].class);
    }

    public default R_get_characters_character_id_portrait_ok get_characters_character_id_portrait(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/portrait/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_portrait_ok.class);
    }

    public default R_get_characters_character_id_corporationhistory_ok[] get_characters_character_id_corporationhistory(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/corporationhistory/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_corporationhistory_ok[].class);
    }

    public default R_get_characters_character_id_chat_channels_ok[] get_characters_character_id_chat_channels(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/chat_channels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_chat_channels_ok[].class);
    }

    public default R_get_characters_character_id_medals_ok[] get_characters_character_id_medals(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/medals/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_medals_ok[].class);
    }

    public default R_get_characters_character_id_standings_ok[] get_characters_character_id_standings(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/standings/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_standings_ok[].class);
    }

    public default R_get_characters_character_id_agents_research_ok[] get_characters_character_id_agents_research(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/agents_research/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_agents_research_ok[].class);
    }

    public default R_get_characters_character_id_blueprints_ok[] get_characters_character_id_blueprints(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/blueprints/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_blueprints_ok[].class);
    }

    public default R_get_characters_character_id_fatigue_ok get_characters_character_id_fatigue(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fatigue/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fatigue_ok.class);
    }

    public default R_get_characters_character_id_notifications_contacts_ok[] get_characters_character_id_notifications_contacts(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/notifications/contacts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_contacts_ok[].class);
    }

    public default R_get_characters_character_id_notifications_ok[] get_characters_character_id_notifications(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/notifications/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_ok[].class);
    }

    public default String[] get_characters_character_id_roles(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/roles/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, java.lang.String[].class);
    }

    public default R_get_characters_character_id_titles_ok[] get_characters_character_id_titles(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/titles/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_titles_ok[].class);
    }

    public default R_get_characters_character_id_clones_ok get_characters_character_id_clones(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/clones/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_clones_ok.class);
    }

    public default int[] get_characters_character_id_implants(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/implants/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_characters_character_id_contacts_ok[] get_characters_character_id_contacts(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_ok[].class);
    }

    public default R_get_corporations_corporation_id_contacts_ok[] get_corporations_corporation_id_contacts(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contacts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contacts_ok[].class);
    }

    public default R_get_alliances_alliance_id_contacts_ok[] get_alliances_alliance_id_contacts(int alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/contacts/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id_contacts_ok[].class);
    }

    public default R_get_characters_character_id_contacts_labels_ok[] get_characters_character_id_contacts_labels(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contacts/labels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_labels_ok[].class);
    }

    public default R_get_characters_character_id_contracts_ok[] get_characters_character_id_contracts(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_ok[].class);
    }

    public default R_get_characters_character_id_contracts_contract_id_items_ok[] get_characters_character_id_contracts_contract_id_items(int character_id, int contract_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/{contract_id}/items/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_items_ok[].class);
    }

    public default R_get_characters_character_id_contracts_contract_id_bids_ok[] get_characters_character_id_contracts_contract_id_bids(int character_id, int contract_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/{contract_id}/bids/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_bids_ok[].class);
    }

    public default R_get_corporations_corporation_id_contracts_ok[] get_corporations_corporation_id_contracts(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_ok[].class);
    }

    public default R_get_corporations_corporation_id_contracts_contract_id_items_ok[] get_corporations_corporation_id_contracts_contract_id_items(int contract_id, int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/{contract_id}/items/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_items_ok[].class);
    }

    public default R_get_corporations_corporation_id_contracts_contract_id_bids_ok[] get_corporations_corporation_id_contracts_contract_id_bids(int contract_id, int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/{contract_id}/bids/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_bids_ok[].class);
    }

    public default R_get_corporations_corporation_id_shareholders_ok[] get_corporations_corporation_id_shareholders(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/shareholders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_shareholders_ok[].class);
    }

    public default R_get_corporations_corporation_id_ok get_corporations_corporation_id(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_ok.class);
    }

    public default R_get_corporations_corporation_id_alliancehistory_ok[] get_corporations_corporation_id_alliancehistory(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/alliancehistory/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_alliancehistory_ok[].class);
    }

    public default R_get_corporations_names_ok[] get_corporations_names() {
        String url="https://esi.tech.ccp.is/latest/corporations/names/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_names_ok[].class);
    }

    public default R_get_corporations_corporation_id_members_ok[] get_corporations_corporation_id_members(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_ok[].class);
    }

    public default R_get_corporations_corporation_id_roles_ok[] get_corporations_corporation_id_roles(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/roles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_ok[].class);
    }

    public default R_get_corporations_corporation_id_roles_history_ok[] get_corporations_corporation_id_roles_history(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/roles/history/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_history_ok[].class);
    }

    public default R_get_corporations_corporation_id_icons_ok get_corporations_corporation_id_icons(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/icons/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_icons_ok.class);
    }

    public default int[] get_corporations_npccorps() {
        String url="https://esi.tech.ccp.is/latest/corporations/npccorps/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_corporations_corporation_id_structures_ok[] get_corporations_corporation_id_structures(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/structures/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_structures_ok[].class);
    }

    public default R_get_corporations_corporation_id_membertracking_ok[] get_corporations_corporation_id_membertracking(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/membertracking/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_membertracking_ok[].class);
    }

    public default R_get_corporations_corporation_id_divisions_ok get_corporations_corporation_id_divisions(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/divisions/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_divisions_ok.class);
    }

    public default int get_corporations_corporation_id_members_limit(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/limit/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, int.class);
    }

    public default R_get_corporations_corporation_id_titles_ok[] get_corporations_corporation_id_titles(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/titles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_titles_ok[].class);
    }

    public default R_get_corporations_corporation_id_members_titles_ok[] get_corporations_corporation_id_members_titles(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/titles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_titles_ok[].class);
    }

    public default R_get_corporations_corporation_id_blueprints_ok[] get_corporations_corporation_id_blueprints(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/blueprints/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_blueprints_ok[].class);
    }

    public default R_get_corporations_corporation_id_standings_ok[] get_corporations_corporation_id_standings(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/standings/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_standings_ok[].class);
    }

    public default R_get_corporations_corporation_id_starbases_ok[] get_corporations_corporation_id_starbases(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/starbases/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_ok[].class);
    }

    public default R_get_corporations_corporation_id_starbases_starbase_id_ok get_corporations_corporation_id_starbases_starbase_id(int corporation_id, int starbase_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/starbases/{starbase_id}/".replace("{corporation_id}", ""+corporation_id).replace("{starbase_id}", ""+starbase_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_starbase_id_ok.class);
    }

    public default R_get_corporations_corporation_id_containers_logs_ok[] get_corporations_corporation_id_containers_logs(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/containers/logs/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_containers_logs_ok[].class);
    }

    public default R_get_corporations_corporation_id_facilities_ok[] get_corporations_corporation_id_facilities(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/facilities/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_facilities_ok[].class);
    }

    public default R_get_corporations_corporation_id_medals_ok[] get_corporations_corporation_id_medals(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/medals/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_ok[].class);
    }

    public default R_get_corporations_corporation_id_medals_issued_ok[] get_corporations_corporation_id_medals_issued(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/medals/issued/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_issued_ok[].class);
    }

    public default int[] get_corporations_corporation_id_outposts(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/outposts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_corporations_corporation_id_outposts_outpost_id_ok get_corporations_corporation_id_outposts_outpost_id(int corporation_id, int outpost_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/outposts/{outpost_id}/".replace("{corporation_id}", ""+corporation_id).replace("{outpost_id}", ""+outpost_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_outposts_outpost_id_ok.class);
    }

    public default int[] get_dogma_attributes() {
        String url="https://esi.tech.ccp.is/latest/dogma/attributes/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_dogma_attributes_attribute_id_ok get_dogma_attributes_attribute_id(int attribute_id) {
        String url="https://esi.tech.ccp.is/latest/dogma/attributes/{attribute_id}/".replace("{attribute_id}", ""+attribute_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_dogma_attributes_attribute_id_ok.class);
    }

    public default int[] get_dogma_effects() {
        String url="https://esi.tech.ccp.is/latest/dogma/effects/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_dogma_effects_effect_id_ok get_dogma_effects_effect_id(int effect_id) {
        String url="https://esi.tech.ccp.is/latest/dogma/effects/{effect_id}/".replace("{effect_id}", ""+effect_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_dogma_effects_effect_id_ok.class);
    }

    public default R_get_fw_wars_ok[] get_fw_wars() {
        String url="https://esi.tech.ccp.is/latest/fw/wars/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_wars_ok[].class);
    }

    public default R_get_fw_stats_ok[] get_fw_stats() {
        String url="https://esi.tech.ccp.is/latest/fw/stats/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_stats_ok[].class);
    }

    public default R_get_fw_systems_ok[] get_fw_systems() {
        String url="https://esi.tech.ccp.is/latest/fw/systems/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_systems_ok[].class);
    }

    public default R_get_fw_leaderboards_ok get_fw_leaderboards() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards_ok.class);
    }

    public default R_get_fw_leaderboards_characters_ok get_fw_leaderboards_characters() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/characters/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards_characters_ok.class);
    }

    public default R_get_fw_leaderboards_corporations_ok get_fw_leaderboards_corporations() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/corporations/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards_corporations_ok.class);
    }

    public default R_get_corporations_corporation_id_fw_stats_ok get_corporations_corporation_id_fw_stats(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/fw/stats/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_fw_stats_ok.class);
    }

    public default R_get_characters_character_id_fw_stats_ok get_characters_character_id_fw_stats(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fw/stats/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fw_stats_ok.class);
    }

    public default R_get_characters_character_id_fittings_ok[] get_characters_character_id_fittings(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fittings_ok[].class);
    }

    public default R_get_fleets_fleet_id_ok get_fleets_fleet_id(int fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id_ok.class);
    }

    public default R_get_characters_character_id_fleet_ok get_characters_character_id_fleet(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fleet/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fleet_ok.class);
    }

    public default R_get_fleets_fleet_id_members_ok[] get_fleets_fleet_id_members(int fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id_members_ok[].class);
    }

    public default R_get_fleets_fleet_id_wings_ok[] get_fleets_fleet_id_wings(int fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id_wings_ok[].class);
    }

    public default R_get_incursions_ok[] get_incursions() {
        String url="https://esi.tech.ccp.is/latest/incursions/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_incursions_ok[].class);
    }

    public default R_get_industry_facilities_ok[] get_industry_facilities() {
        String url="https://esi.tech.ccp.is/latest/industry/facilities/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_industry_facilities_ok[].class);
    }

    public default R_get_industry_systems_ok[] get_industry_systems() {
        String url="https://esi.tech.ccp.is/latest/industry/systems/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_industry_systems_ok[].class);
    }

    public default R_get_characters_character_id_industry_jobs_ok[] get_characters_character_id_industry_jobs(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/industry/jobs/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs_ok[].class);
    }

    public default R_get_characters_character_id_mining_ok[] get_characters_character_id_mining(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mining/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mining_ok[].class);
    }

    public default R_get_corporation_corporation_id_mining_observers_ok[] get_corporation_corporation_id_mining_observers(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/observers/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_ok[].class);
    }

    public default R_get_corporation_corporation_id_mining_observers_observer_id_ok[] get_corporation_corporation_id_mining_observers_observer_id(int corporation_id, int observer_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/observers/{observer_id}/".replace("{corporation_id}", ""+corporation_id).replace("{observer_id}", ""+observer_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_observer_id_ok[].class);
    }

    public default R_get_corporations_corporation_id_industry_jobs_ok[] get_corporations_corporation_id_industry_jobs(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/industry/jobs/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs_ok[].class);
    }

    public default R_get_corporation_corporation_id_mining_extractions_ok[] get_corporation_corporation_id_mining_extractions(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/extractions/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_extractions_ok[].class);
    }

    public default R_get_insurance_prices_ok[] get_insurance_prices() {
        String url="https://esi.tech.ccp.is/latest/insurance/prices/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_insurance_prices_ok[].class);
    }

    public default R_get_killmails_killmail_id_killmail_hash_ok get_killmails_killmail_id_killmail_hash(String killmail_hash, int killmail_id) {
        String url="https://esi.tech.ccp.is/latest/killmails/{killmail_id}/{killmail_hash}/".replace("{killmail_hash}", ""+killmail_hash).replace("{killmail_id}", ""+killmail_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_killmails_killmail_id_killmail_hash_ok.class);
    }

    public default R_get_characters_character_id_killmails_recent_ok[] get_characters_character_id_killmails_recent(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/killmails/recent/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_killmails_recent_ok[].class);
    }

    public default R_get_corporations_corporation_id_killmails_recent_ok[] get_corporations_corporation_id_killmails_recent(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/killmails/recent/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_killmails_recent_ok[].class);
    }

    public default R_get_characters_character_id_location_ok get_characters_character_id_location(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/location/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_location_ok.class);
    }

    public default R_get_characters_character_id_ship_ok get_characters_character_id_ship(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/ship/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_ship_ok.class);
    }

    public default boolean get_characters_character_id_online(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/online/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, boolean.class);
    }

    public default R_get_loyalty_stores_corporation_id_offers_ok[] get_loyalty_stores_corporation_id_offers(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/loyalty/stores/{corporation_id}/offers/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers_ok[].class);
    }

    public default R_get_characters_character_id_loyalty_points_ok[] get_characters_character_id_loyalty_points(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/loyalty/points/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_loyalty_points_ok[].class);
    }

    public default R_get_characters_character_id_mail_ok[] get_characters_character_id_mail(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_ok[].class);
    }

    public default R_get_characters_character_id_mail_labels_ok get_characters_character_id_mail_labels(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_labels_ok.class);
    }

    public default R_get_characters_character_id_mail_lists_ok[] get_characters_character_id_mail_lists(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/lists/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_lists_ok[].class);
    }

    public default R_get_characters_character_id_mail_mail_id_ok get_characters_character_id_mail_mail_id(int character_id, int mail_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_mail_id_ok.class);
    }

    public default R_get_markets_prices_ok[] get_markets_prices() {
        String url="https://esi.tech.ccp.is/latest/markets/prices/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_prices_ok[].class);
    }

    public default R_get_markets_region_id_orders_ok[] get_markets_region_id_orders(int region_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/orders/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_region_id_orders_ok[].class);
    }

    public default R_get_markets_region_id_history_ok[] get_markets_region_id_history(int region_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/history/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_region_id_history_ok[].class);
    }

    public default R_get_markets_structures_structure_id_ok[] get_markets_structures_structure_id(int structure_id) {
        String url="https://esi.tech.ccp.is/latest/markets/structures/{structure_id}/".replace("{structure_id}", ""+structure_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_structures_structure_id_ok[].class);
    }

    public default int[] get_markets_groups() {
        String url="https://esi.tech.ccp.is/latest/markets/groups/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_markets_groups_market_group_id_ok get_markets_groups_market_group_id(int market_group_id) {
        String url="https://esi.tech.ccp.is/latest/markets/groups/{market_group_id}/".replace("{market_group_id}", ""+market_group_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_groups_market_group_id_ok.class);
    }

    public default R_get_characters_character_id_orders_ok[] get_characters_character_id_orders(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/orders/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_orders_ok[].class);
    }

    public default int[] get_markets_region_id_types(int region_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/types/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_corporations_corporation_id_orders_ok[] get_corporations_corporation_id_orders(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/orders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_orders_ok[].class);
    }

    public default int[] get_opportunities_groups() {
        String url="https://esi.tech.ccp.is/latest/opportunities/groups/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_opportunities_groups_group_id_ok get_opportunities_groups_group_id(int group_id) {
        String url="https://esi.tech.ccp.is/latest/opportunities/groups/{group_id}/".replace("{group_id}", ""+group_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_opportunities_groups_group_id_ok.class);
    }

    public default int[] get_opportunities_tasks() {
        String url="https://esi.tech.ccp.is/latest/opportunities/tasks/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_opportunities_tasks_task_id_ok get_opportunities_tasks_task_id(int task_id) {
        String url="https://esi.tech.ccp.is/latest/opportunities/tasks/{task_id}/".replace("{task_id}", ""+task_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_opportunities_tasks_task_id_ok.class);
    }

    public default R_get_characters_character_id_opportunities_ok[] get_characters_character_id_opportunities(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/opportunities/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_opportunities_ok[].class);
    }

    public default R_get_characters_character_id_planets_ok[] get_characters_character_id_planets(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/planets/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_planets_ok[].class);
    }

    public default R_get_characters_character_id_planets_planet_id_ok get_characters_character_id_planets_planet_id(int character_id, int planet_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/planets/{planet_id}/".replace("{character_id}", ""+character_id).replace("{planet_id}", ""+planet_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_planets_planet_id_ok.class);
    }

    public default R_get_universe_schematics_schematic_id_ok get_universe_schematics_schematic_id(int schematic_id) {
        String url="https://esi.tech.ccp.is/latest/universe/schematics/{schematic_id}/".replace("{schematic_id}", ""+schematic_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_schematics_schematic_id_ok.class);
    }

    public default R_get_corporations_corporation_id_customs_offices_ok[] get_corporations_corporation_id_customs_offices(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/customs_offices/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_customs_offices_ok[].class);
    }

    public default int[] get_route_origin_destination(int destination, int origin) {
        String url="https://esi.tech.ccp.is/latest/route/{origin}/{destination}/".replace("{destination}", ""+destination).replace("{origin}", ""+origin);
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_characters_character_id_search_ok get_characters_character_id_search(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/search/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_search_ok.class);
    }

    public default R_get_search_ok get_search() {
        String url="https://esi.tech.ccp.is/latest/search/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_search_ok.class);
    }

    public default R_get_characters_character_id_skillqueue_ok[] get_characters_character_id_skillqueue(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/skillqueue/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_skillqueue_ok[].class);
    }

    public default R_get_characters_character_id_skills_ok get_characters_character_id_skills(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/skills/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_skills_ok.class);
    }

    public default R_get_characters_character_id_attributes_ok get_characters_character_id_attributes(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/attributes/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_attributes_ok.class);
    }

    public default R_get_sovereignty_structures_ok[] get_sovereignty_structures() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/structures/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_structures_ok[].class);
    }

    public default R_get_sovereignty_campaigns_ok[] get_sovereignty_campaigns() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/campaigns/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_campaigns_ok[].class);
    }

    public default R_get_sovereignty_map_ok[] get_sovereignty_map() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/map/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_map_ok[].class);
    }

    public default R_get_status_ok get_status() {
        String url="https://esi.tech.ccp.is/latest/status/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_status_ok.class);
    }

    public default R_get_universe_planets_planet_id_ok get_universe_planets_planet_id(int planet_id) {
        String url="https://esi.tech.ccp.is/latest/universe/planets/{planet_id}/".replace("{planet_id}", ""+planet_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_planets_planet_id_ok.class);
    }

    public default R_get_universe_stations_station_id_ok get_universe_stations_station_id(int station_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stations/{station_id}/".replace("{station_id}", ""+station_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stations_station_id_ok.class);
    }

    public default R_get_universe_structures_structure_id_ok get_universe_structures_structure_id(int structure_id) {
        String url="https://esi.tech.ccp.is/latest/universe/structures/{structure_id}/".replace("{structure_id}", ""+structure_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_structures_structure_id_ok.class);
    }

    public default R_get_universe_systems_system_id_ok get_universe_systems_system_id(int system_id) {
        String url="https://esi.tech.ccp.is/latest/universe/systems/{system_id}/".replace("{system_id}", ""+system_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_systems_system_id_ok.class);
    }

    public default int[] get_universe_systems() {
        String url="https://esi.tech.ccp.is/latest/universe/systems/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_types_type_id_ok get_universe_types_type_id(int type_id) {
        String url="https://esi.tech.ccp.is/latest/universe/types/{type_id}/".replace("{type_id}", ""+type_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_types_type_id_ok.class);
    }

    public default int[] get_universe_types() {
        String url="https://esi.tech.ccp.is/latest/universe/types/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default int[] get_universe_groups() {
        String url="https://esi.tech.ccp.is/latest/universe/groups/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_groups_group_id_ok get_universe_groups_group_id(int group_id) {
        String url="https://esi.tech.ccp.is/latest/universe/groups/{group_id}/".replace("{group_id}", ""+group_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_groups_group_id_ok.class);
    }

    public default int[] get_universe_categories() {
        String url="https://esi.tech.ccp.is/latest/universe/categories/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_categories_category_id_ok get_universe_categories_category_id(int category_id) {
        String url="https://esi.tech.ccp.is/latest/universe/categories/{category_id}/".replace("{category_id}", ""+category_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_categories_category_id_ok.class);
    }

    public default R_post_universe_names_ok[] post_universe_names() {
        String url="https://esi.tech.ccp.is/latest/universe/names/";
        String fetched=connectPost(url, null, null);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_universe_names_ok[].class);
    }

    public default int[] get_universe_structures() {
        String url="https://esi.tech.ccp.is/latest/universe/structures/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_races_ok[] get_universe_races() {
        String url="https://esi.tech.ccp.is/latest/universe/races/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_races_ok[].class);
    }

    public default R_get_universe_factions_ok[] get_universe_factions() {
        String url="https://esi.tech.ccp.is/latest/universe/factions/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_factions_ok[].class);
    }

    public default R_get_universe_bloodlines_ok[] get_universe_bloodlines() {
        String url="https://esi.tech.ccp.is/latest/universe/bloodlines/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_bloodlines_ok[].class);
    }

    public default int[] get_universe_regions() {
        String url="https://esi.tech.ccp.is/latest/universe/regions/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_regions_region_id_ok get_universe_regions_region_id(int region_id) {
        String url="https://esi.tech.ccp.is/latest/universe/regions/{region_id}/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_regions_region_id_ok.class);
    }

    public default int[] get_universe_constellations() {
        String url="https://esi.tech.ccp.is/latest/universe/constellations/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_constellations_constellation_id_ok get_universe_constellations_constellation_id(int constellation_id) {
        String url="https://esi.tech.ccp.is/latest/universe/constellations/{constellation_id}/".replace("{constellation_id}", ""+constellation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_constellations_constellation_id_ok.class);
    }

    public default R_get_universe_moons_moon_id_ok get_universe_moons_moon_id(int moon_id) {
        String url="https://esi.tech.ccp.is/latest/universe/moons/{moon_id}/".replace("{moon_id}", ""+moon_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_moons_moon_id_ok.class);
    }

    public default R_get_universe_stargates_stargate_id_ok get_universe_stargates_stargate_id(int stargate_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stargates/{stargate_id}/".replace("{stargate_id}", ""+stargate_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stargates_stargate_id_ok.class);
    }

    public default int[] get_universe_graphics() {
        String url="https://esi.tech.ccp.is/latest/universe/graphics/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_universe_graphics_graphic_id_ok get_universe_graphics_graphic_id(int graphic_id) {
        String url="https://esi.tech.ccp.is/latest/universe/graphics/{graphic_id}/".replace("{graphic_id}", ""+graphic_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_graphics_graphic_id_ok.class);
    }

    public default R_get_universe_system_jumps_ok[] get_universe_system_jumps() {
        String url="https://esi.tech.ccp.is/latest/universe/system_jumps/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_system_jumps_ok[].class);
    }

    public default R_get_universe_system_kills_ok[] get_universe_system_kills() {
        String url="https://esi.tech.ccp.is/latest/universe/system_kills/";
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_system_kills_ok[].class);
    }

    public default R_get_universe_stars_star_id_ok get_universe_stars_star_id(int star_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stars/{star_id}/".replace("{star_id}", ""+star_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stars_star_id_ok.class);
    }

    public default double get_characters_character_id_wallet(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, double.class);
    }

    public default R_get_characters_character_id_wallet_journal_ok[] get_characters_character_id_wallet_journal(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/journal/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_journal_ok[].class);
    }

    public default R_get_characters_character_id_wallet_transactions_ok[] get_characters_character_id_wallet_transactions(int character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/transactions/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_transactions_ok[].class);
    }

    public default R_get_corporations_corporation_id_wallets_ok[] get_corporations_corporation_id_wallets(int corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_ok[].class);
    }

    public default R_get_corporations_corporation_id_wallets_division_journal_ok[] get_corporations_corporation_id_wallets_division_journal(int corporation_id, int division) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/{division}/journal/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_journal_ok[].class);
    }

    public default R_get_corporations_corporation_id_wallets_division_transactions_ok[] get_corporations_corporation_id_wallets_division_transactions(int corporation_id, int division) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/{division}/transactions/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_transactions_ok[].class);
    }

    public default int[] get_wars() {
        String url="https://esi.tech.ccp.is/latest/wars/";
        String fetched=connectGet(url);
        return convert(fetched, int[].class);
    }

    public default R_get_wars_war_id_ok get_wars_war_id(int war_id) {
        String url="https://esi.tech.ccp.is/latest/wars/{war_id}/".replace("{war_id}", ""+war_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_wars_war_id_ok.class);
    }

    public default R_get_wars_war_id_killmails_ok[] get_wars_war_id_killmails(int war_id) {
        String url="https://esi.tech.ccp.is/latest/wars/{war_id}/killmails/".replace("{war_id}", ""+war_id);
        String fetched=connectGet(url);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_wars_war_id_killmails_ok[].class);
    }
}
