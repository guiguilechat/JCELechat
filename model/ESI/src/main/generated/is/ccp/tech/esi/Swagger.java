package is.ccp.tech.esi;

import fr.guiguilechat.eveonline.model.esi.RequestHandler;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id_contacts;
import is.ccp.tech.esi.responses.R_get_alliances_alliance_id_icons;
import is.ccp.tech.esi.responses.R_get_alliances_names;
import is.ccp.tech.esi.responses.R_get_characters_character_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_agents_research;
import is.ccp.tech.esi.responses.R_get_characters_character_id_assets;
import is.ccp.tech.esi.responses.R_get_characters_character_id_attributes;
import is.ccp.tech.esi.responses.R_get_characters_character_id_blueprints;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks;
import is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_folders;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_attendees;
import is.ccp.tech.esi.responses.R_get_characters_character_id_chat_channels;
import is.ccp.tech.esi.responses.R_get_characters_character_id_clones;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contacts;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_labels;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_bids;
import is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_items;
import is.ccp.tech.esi.responses.R_get_characters_character_id_corporationhistory;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fatigue;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fittings;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fleet;
import is.ccp.tech.esi.responses.R_get_characters_character_id_fw_stats;
import is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs;
import is.ccp.tech.esi.responses.R_get_characters_character_id_killmails_recent;
import is.ccp.tech.esi.responses.R_get_characters_character_id_location;
import is.ccp.tech.esi.responses.R_get_characters_character_id_loyalty_points;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_labels;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_lists;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mail_mail_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_medals;
import is.ccp.tech.esi.responses.R_get_characters_character_id_mining;
import is.ccp.tech.esi.responses.R_get_characters_character_id_notifications;
import is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_contacts;
import is.ccp.tech.esi.responses.R_get_characters_character_id_online;
import is.ccp.tech.esi.responses.R_get_characters_character_id_opportunities;
import is.ccp.tech.esi.responses.R_get_characters_character_id_orders;
import is.ccp.tech.esi.responses.R_get_characters_character_id_planets;
import is.ccp.tech.esi.responses.R_get_characters_character_id_planets_planet_id;
import is.ccp.tech.esi.responses.R_get_characters_character_id_portrait;
import is.ccp.tech.esi.responses.R_get_characters_character_id_roles;
import is.ccp.tech.esi.responses.R_get_characters_character_id_search;
import is.ccp.tech.esi.responses.R_get_characters_character_id_ship;
import is.ccp.tech.esi.responses.R_get_characters_character_id_skillqueue;
import is.ccp.tech.esi.responses.R_get_characters_character_id_skills;
import is.ccp.tech.esi.responses.R_get_characters_character_id_standings;
import is.ccp.tech.esi.responses.R_get_characters_character_id_stats;
import is.ccp.tech.esi.responses.R_get_characters_character_id_titles;
import is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_journal;
import is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_transactions;
import is.ccp.tech.esi.responses.R_get_characters_names;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_extractions;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers;
import is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_observer_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_alliancehistory;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_assets;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_blueprints;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_folders;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contacts;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_containers_logs;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_bids;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_items;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_customs_offices;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_divisions;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_facilities;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_fw_stats;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_icons;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_killmails_recent;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_issued;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_titles;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_membertracking;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_orders;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_outposts_outpost_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_history;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_shareholders;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_standings;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_starbase_id;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_structures;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_titles;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_journal;
import is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import is.ccp.tech.esi.responses.R_get_corporations_names;
import is.ccp.tech.esi.responses.R_get_dogma_attributes_attribute_id;
import is.ccp.tech.esi.responses.R_get_dogma_effects_effect_id;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id_members;
import is.ccp.tech.esi.responses.R_get_fleets_fleet_id_wings;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards_characters;
import is.ccp.tech.esi.responses.R_get_fw_leaderboards_corporations;
import is.ccp.tech.esi.responses.R_get_fw_stats;
import is.ccp.tech.esi.responses.R_get_fw_systems;
import is.ccp.tech.esi.responses.R_get_fw_wars;
import is.ccp.tech.esi.responses.R_get_incursions;
import is.ccp.tech.esi.responses.R_get_industry_facilities;
import is.ccp.tech.esi.responses.R_get_industry_systems;
import is.ccp.tech.esi.responses.R_get_insurance_prices;
import is.ccp.tech.esi.responses.R_get_killmails_killmail_id_killmail_hash;
import is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers;
import is.ccp.tech.esi.responses.R_get_markets_groups_market_group_id;
import is.ccp.tech.esi.responses.R_get_markets_prices;
import is.ccp.tech.esi.responses.R_get_markets_region_id_history;
import is.ccp.tech.esi.responses.R_get_markets_region_id_orders;
import is.ccp.tech.esi.responses.R_get_markets_structures_structure_id;
import is.ccp.tech.esi.responses.R_get_opportunities_groups_group_id;
import is.ccp.tech.esi.responses.R_get_opportunities_tasks_task_id;
import is.ccp.tech.esi.responses.R_get_search;
import is.ccp.tech.esi.responses.R_get_sovereignty_campaigns;
import is.ccp.tech.esi.responses.R_get_sovereignty_map;
import is.ccp.tech.esi.responses.R_get_sovereignty_structures;
import is.ccp.tech.esi.responses.R_get_status;
import is.ccp.tech.esi.responses.R_get_universe_bloodlines;
import is.ccp.tech.esi.responses.R_get_universe_categories_category_id;
import is.ccp.tech.esi.responses.R_get_universe_constellations_constellation_id;
import is.ccp.tech.esi.responses.R_get_universe_factions;
import is.ccp.tech.esi.responses.R_get_universe_graphics_graphic_id;
import is.ccp.tech.esi.responses.R_get_universe_groups_group_id;
import is.ccp.tech.esi.responses.R_get_universe_moons_moon_id;
import is.ccp.tech.esi.responses.R_get_universe_planets_planet_id;
import is.ccp.tech.esi.responses.R_get_universe_races;
import is.ccp.tech.esi.responses.R_get_universe_regions_region_id;
import is.ccp.tech.esi.responses.R_get_universe_schematics_schematic_id;
import is.ccp.tech.esi.responses.R_get_universe_stargates_stargate_id;
import is.ccp.tech.esi.responses.R_get_universe_stars_star_id;
import is.ccp.tech.esi.responses.R_get_universe_stations_station_id;
import is.ccp.tech.esi.responses.R_get_universe_structures_structure_id;
import is.ccp.tech.esi.responses.R_get_universe_system_jumps;
import is.ccp.tech.esi.responses.R_get_universe_system_kills;
import is.ccp.tech.esi.responses.R_get_universe_systems_system_id;
import is.ccp.tech.esi.responses.R_get_universe_types_type_id;
import is.ccp.tech.esi.responses.R_get_wars_war_id;
import is.ccp.tech.esi.responses.R_get_wars_war_id_killmails;
import is.ccp.tech.esi.responses.R_post_characters_affiliation;
import is.ccp.tech.esi.responses.R_post_characters_character_id_assets_locations;
import is.ccp.tech.esi.responses.R_post_characters_character_id_assets_names;
import is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_locations;
import is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_names;
import is.ccp.tech.esi.responses.R_post_universe_ids;
import is.ccp.tech.esi.responses.R_post_universe_names;

public interface Swagger
    extends RequestHandler
{

    public default R_get_alliances_alliance_id get_alliances_alliance_id(long alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id.class);
    }

    public default long[] get_alliances_alliance_id_corporations(long alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/corporations/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_alliances_names[] get_alliances_names(long[] alliance_ids) {
        String url="https://esi.tech.ccp.is/latest/alliances/names/"+"?alliance_ids="+flatten(alliance_ids);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_names[].class);
    }

    public default R_get_alliances_alliance_id_icons get_alliances_alliance_id_icons(long alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/icons/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id_icons.class);
    }

    public default long[] get_alliances() {
        String url="https://esi.tech.ccp.is/latest/alliances/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_characters_character_id_assets[] get_characters_character_id_assets(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_assets[].class);
    }

    public default R_get_corporations_corporation_id_assets[] get_corporations_corporation_id_assets(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_assets[].class);
    }

    public default R_post_characters_character_id_assets_names[] post_characters_character_id_assets_names(long character_id, long[] item_ids) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/names/".replace("{character_id}", ""+character_id);
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("item_ids", flatten(item_ids));
        String fetched=connectPost(url, content,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_character_id_assets_names[].class);
    }

    public default R_post_characters_character_id_assets_locations[] post_characters_character_id_assets_locations(long character_id, long[] item_ids) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/assets/locations/".replace("{character_id}", ""+character_id);
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("item_ids", flatten(item_ids));
        String fetched=connectPost(url, content,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_character_id_assets_locations[].class);
    }

    public default R_post_corporations_corporation_id_assets_names[] post_corporations_corporation_id_assets_names(long corporation_id, long[] item_ids) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/names/".replace("{corporation_id}", ""+corporation_id);
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("item_ids", flatten(item_ids));
        String fetched=connectPost(url, content,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_names[].class);
    }

    public default R_post_corporations_corporation_id_assets_locations[] post_corporations_corporation_id_assets_locations(long corporation_id, long[] item_ids) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/assets/locations/".replace("{corporation_id}", ""+corporation_id);
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("item_ids", flatten(item_ids));
        String fetched=connectPost(url, content,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_corporations_corporation_id_assets_locations[].class);
    }

    public default R_get_characters_character_id_bookmarks[] get_characters_character_id_bookmarks(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/bookmarks/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks[].class);
    }

    public default R_get_characters_character_id_bookmarks_folders[] get_characters_character_id_bookmarks_folders(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/bookmarks/folders/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_bookmarks_folders[].class);
    }

    public default R_get_corporations_corporation_id_bookmarks[] get_corporations_corporation_id_bookmarks(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/bookmarks/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks[].class);
    }

    public default R_get_corporations_corporation_id_bookmarks_folders[] get_corporations_corporation_id_bookmarks_folders(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/bookmarks/folders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_bookmarks_folders[].class);
    }

    public default R_get_characters_character_id_calendar[] get_characters_character_id_calendar(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar[].class);
    }

    public default R_get_characters_character_id_calendar_event_id get_characters_character_id_calendar_event_id(long character_id, long event_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/{event_id}/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id.class);
    }

    public default R_get_characters_character_id_calendar_event_id_attendees[] get_characters_character_id_calendar_event_id_attendees(long character_id, long event_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/calendar/{event_id}/attendees/".replace("{character_id}", ""+character_id).replace("{event_id}", ""+event_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_calendar_event_id_attendees[].class);
    }

    public default R_get_characters_character_id get_characters_character_id(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id.class);
    }

    public default R_post_characters_affiliation[] post_characters_affiliation(long[] characters) {
        String url="https://esi.tech.ccp.is/latest/characters/affiliation/";
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("characters", flatten(characters));
        String fetched=connectPost(url, content,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_characters_affiliation[].class);
    }

    public default R_get_characters_names[] get_characters_names(long[] character_ids) {
        String url="https://esi.tech.ccp.is/latest/characters/names/"+"?character_ids="+flatten(character_ids);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_names[].class);
    }

    public default R_get_characters_character_id_portrait get_characters_character_id_portrait(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/portrait/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_portrait.class);
    }

    public default R_get_characters_character_id_corporationhistory[] get_characters_character_id_corporationhistory(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/corporationhistory/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_corporationhistory[].class);
    }

    public default R_get_characters_character_id_chat_channels[] get_characters_character_id_chat_channels(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/chat_channels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_chat_channels[].class);
    }

    public default R_get_characters_character_id_medals[] get_characters_character_id_medals(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/medals/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_medals[].class);
    }

    public default R_get_characters_character_id_standings[] get_characters_character_id_standings(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/standings/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_standings[].class);
    }

    public default R_get_characters_character_id_agents_research[] get_characters_character_id_agents_research(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/agents_research/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_agents_research[].class);
    }

    public default R_get_characters_character_id_blueprints[] get_characters_character_id_blueprints(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/blueprints/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_blueprints[].class);
    }

    public default R_get_characters_character_id_fatigue get_characters_character_id_fatigue(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fatigue/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fatigue.class);
    }

    public default R_get_characters_character_id_notifications_contacts[] get_characters_character_id_notifications_contacts(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/notifications/contacts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_notifications_contacts[].class);
    }

    public default R_get_characters_character_id_notifications[] get_characters_character_id_notifications(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/notifications/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_notifications[].class);
    }

    public default R_get_characters_character_id_roles get_characters_character_id_roles(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/roles/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_roles.class);
    }

    public default R_get_characters_character_id_titles[] get_characters_character_id_titles(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/titles/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_titles[].class);
    }

    public default R_get_characters_character_id_stats[] get_characters_character_id_stats(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/stats/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_stats[].class);
    }

    public default R_get_characters_character_id_clones get_characters_character_id_clones(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/clones/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_clones.class);
    }

    public default long[] get_characters_character_id_implants(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/implants/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, long[].class);
    }

    public default R_get_characters_character_id_contacts[] get_characters_character_id_contacts(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contacts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contacts[].class);
    }

    public default R_get_corporations_corporation_id_contacts[] get_corporations_corporation_id_contacts(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contacts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contacts[].class);
    }

    public default R_get_alliances_alliance_id_contacts[] get_alliances_alliance_id_contacts(long alliance_id) {
        String url="https://esi.tech.ccp.is/latest/alliances/{alliance_id}/contacts/".replace("{alliance_id}", ""+alliance_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_alliances_alliance_id_contacts[].class);
    }

    public default R_get_characters_character_id_contacts_labels[] get_characters_character_id_contacts_labels(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contacts/labels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contacts_labels[].class);
    }

    public default R_get_characters_character_id_contracts[] get_characters_character_id_contracts(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts[].class);
    }

    public default R_get_characters_character_id_contracts_contract_id_items[] get_characters_character_id_contracts_contract_id_items(long character_id, long contract_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/{contract_id}/items/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_items[].class);
    }

    public default R_get_characters_character_id_contracts_contract_id_bids[] get_characters_character_id_contracts_contract_id_bids(long character_id, long contract_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/contracts/{contract_id}/bids/".replace("{character_id}", ""+character_id).replace("{contract_id}", ""+contract_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_contracts_contract_id_bids[].class);
    }

    public default R_get_corporations_corporation_id_contracts[] get_corporations_corporation_id_contracts(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts[].class);
    }

    public default R_get_corporations_corporation_id_contracts_contract_id_items[] get_corporations_corporation_id_contracts_contract_id_items(long contract_id, long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/{contract_id}/items/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_items[].class);
    }

    public default R_get_corporations_corporation_id_contracts_contract_id_bids[] get_corporations_corporation_id_contracts_contract_id_bids(long contract_id, long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/contracts/{contract_id}/bids/".replace("{contract_id}", ""+contract_id).replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_contracts_contract_id_bids[].class);
    }

    public default R_get_corporations_corporation_id_shareholders[] get_corporations_corporation_id_shareholders(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/shareholders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_shareholders[].class);
    }

    public default R_get_corporations_corporation_id get_corporations_corporation_id(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id.class);
    }

    public default R_get_corporations_corporation_id_alliancehistory[] get_corporations_corporation_id_alliancehistory(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/alliancehistory/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_alliancehistory[].class);
    }

    public default R_get_corporations_names[] get_corporations_names(long[] corporation_ids) {
        String url="https://esi.tech.ccp.is/latest/corporations/names/"+"?corporation_ids="+flatten(corporation_ids);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_names[].class);
    }

    public default long[] get_corporations_corporation_id_members(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, long[].class);
    }

    public default R_get_corporations_corporation_id_roles[] get_corporations_corporation_id_roles(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/roles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles[].class);
    }

    public default R_get_corporations_corporation_id_roles_history[] get_corporations_corporation_id_roles_history(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/roles/history/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_roles_history[].class);
    }

    public default R_get_corporations_corporation_id_icons get_corporations_corporation_id_icons(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/icons/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_icons.class);
    }

    public default long[] get_corporations_npccorps() {
        String url="https://esi.tech.ccp.is/latest/corporations/npccorps/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_corporations_corporation_id_structures[] get_corporations_corporation_id_structures(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/structures/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_structures[].class);
    }

    public default R_get_corporations_corporation_id_membertracking[] get_corporations_corporation_id_membertracking(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/membertracking/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_membertracking[].class);
    }

    public default R_get_corporations_corporation_id_divisions get_corporations_corporation_id_divisions(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/divisions/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_divisions.class);
    }

    public default long get_corporations_corporation_id_members_limit(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/limit/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, long.class);
    }

    public default R_get_corporations_corporation_id_titles[] get_corporations_corporation_id_titles(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/titles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_titles[].class);
    }

    public default R_get_corporations_corporation_id_members_titles[] get_corporations_corporation_id_members_titles(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/members/titles/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_members_titles[].class);
    }

    public default R_get_corporations_corporation_id_blueprints[] get_corporations_corporation_id_blueprints(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/blueprints/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_blueprints[].class);
    }

    public default R_get_corporations_corporation_id_standings[] get_corporations_corporation_id_standings(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/standings/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_standings[].class);
    }

    public default R_get_corporations_corporation_id_starbases[] get_corporations_corporation_id_starbases(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/starbases/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases[].class);
    }

    public default R_get_corporations_corporation_id_starbases_starbase_id get_corporations_corporation_id_starbases_starbase_id(long corporation_id, long starbase_id, long system_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/starbases/{starbase_id}/".replace("{corporation_id}", ""+corporation_id).replace("{starbase_id}", ""+starbase_id)+"?system_id="+flatten(system_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_starbases_starbase_id.class);
    }

    public default R_get_corporations_corporation_id_containers_logs[] get_corporations_corporation_id_containers_logs(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/containers/logs/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_containers_logs[].class);
    }

    public default R_get_corporations_corporation_id_facilities[] get_corporations_corporation_id_facilities(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/facilities/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_facilities[].class);
    }

    public default R_get_corporations_corporation_id_medals[] get_corporations_corporation_id_medals(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/medals/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals[].class);
    }

    public default R_get_corporations_corporation_id_medals_issued[] get_corporations_corporation_id_medals_issued(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/medals/issued/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_medals_issued[].class);
    }

    public default long[] get_corporations_corporation_id_outposts(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/outposts/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, long[].class);
    }

    public default R_get_corporations_corporation_id_outposts_outpost_id get_corporations_corporation_id_outposts_outpost_id(long corporation_id, long outpost_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/outposts/{outpost_id}/".replace("{corporation_id}", ""+corporation_id).replace("{outpost_id}", ""+outpost_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_outposts_outpost_id.class);
    }

    public default long[] get_dogma_attributes() {
        String url="https://esi.tech.ccp.is/latest/dogma/attributes/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_dogma_attributes_attribute_id get_dogma_attributes_attribute_id(long attribute_id) {
        String url="https://esi.tech.ccp.is/latest/dogma/attributes/{attribute_id}/".replace("{attribute_id}", ""+attribute_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_dogma_attributes_attribute_id.class);
    }

    public default long[] get_dogma_effects() {
        String url="https://esi.tech.ccp.is/latest/dogma/effects/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_dogma_effects_effect_id get_dogma_effects_effect_id(long effect_id) {
        String url="https://esi.tech.ccp.is/latest/dogma/effects/{effect_id}/".replace("{effect_id}", ""+effect_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_dogma_effects_effect_id.class);
    }

    public default R_get_fw_wars[] get_fw_wars() {
        String url="https://esi.tech.ccp.is/latest/fw/wars/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_wars[].class);
    }

    public default R_get_fw_stats[] get_fw_stats() {
        String url="https://esi.tech.ccp.is/latest/fw/stats/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_stats[].class);
    }

    public default R_get_fw_systems[] get_fw_systems() {
        String url="https://esi.tech.ccp.is/latest/fw/systems/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_systems[].class);
    }

    public default R_get_fw_leaderboards get_fw_leaderboards() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards.class);
    }

    public default R_get_fw_leaderboards_characters get_fw_leaderboards_characters() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/characters/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards_characters.class);
    }

    public default R_get_fw_leaderboards_corporations get_fw_leaderboards_corporations() {
        String url="https://esi.tech.ccp.is/latest/fw/leaderboards/corporations/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fw_leaderboards_corporations.class);
    }

    public default R_get_corporations_corporation_id_fw_stats get_corporations_corporation_id_fw_stats(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/fw/stats/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_fw_stats.class);
    }

    public default R_get_characters_character_id_fw_stats get_characters_character_id_fw_stats(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fw/stats/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fw_stats.class);
    }

    public default R_get_characters_character_id_fittings[] get_characters_character_id_fittings(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fittings/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fittings[].class);
    }

    public default R_get_fleets_fleet_id get_fleets_fleet_id(long fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id.class);
    }

    public default R_get_characters_character_id_fleet get_characters_character_id_fleet(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/fleet/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_fleet.class);
    }

    public default R_get_fleets_fleet_id_members[] get_fleets_fleet_id_members(long fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/members/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id_members[].class);
    }

    public default R_get_fleets_fleet_id_wings[] get_fleets_fleet_id_wings(long fleet_id) {
        String url="https://esi.tech.ccp.is/latest/fleets/{fleet_id}/wings/".replace("{fleet_id}", ""+fleet_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_fleets_fleet_id_wings[].class);
    }

    public default R_get_incursions[] get_incursions() {
        String url="https://esi.tech.ccp.is/latest/incursions/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_incursions[].class);
    }

    public default R_get_industry_facilities[] get_industry_facilities() {
        String url="https://esi.tech.ccp.is/latest/industry/facilities/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_industry_facilities[].class);
    }

    public default R_get_industry_systems[] get_industry_systems() {
        String url="https://esi.tech.ccp.is/latest/industry/systems/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_industry_systems[].class);
    }

    public default R_get_characters_character_id_industry_jobs[] get_characters_character_id_industry_jobs(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/industry/jobs/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_industry_jobs[].class);
    }

    public default R_get_characters_character_id_mining[] get_characters_character_id_mining(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mining/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mining[].class);
    }

    public default R_get_corporation_corporation_id_mining_observers[] get_corporation_corporation_id_mining_observers(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/observers/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers[].class);
    }

    public default R_get_corporation_corporation_id_mining_observers_observer_id[] get_corporation_corporation_id_mining_observers_observer_id(long corporation_id, long observer_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/observers/{observer_id}/".replace("{corporation_id}", ""+corporation_id).replace("{observer_id}", ""+observer_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_observers_observer_id[].class);
    }

    public default R_get_corporations_corporation_id_industry_jobs[] get_corporations_corporation_id_industry_jobs(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/industry/jobs/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_industry_jobs[].class);
    }

    public default R_get_corporation_corporation_id_mining_extractions[] get_corporation_corporation_id_mining_extractions(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporation/{corporation_id}/mining/extractions/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporation_corporation_id_mining_extractions[].class);
    }

    public default R_get_insurance_prices[] get_insurance_prices() {
        String url="https://esi.tech.ccp.is/latest/insurance/prices/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_insurance_prices[].class);
    }

    public default R_get_killmails_killmail_id_killmail_hash get_killmails_killmail_id_killmail_hash(String killmail_hash, long killmail_id) {
        String url="https://esi.tech.ccp.is/latest/killmails/{killmail_id}/{killmail_hash}/".replace("{killmail_hash}", ""+killmail_hash).replace("{killmail_id}", ""+killmail_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_killmails_killmail_id_killmail_hash.class);
    }

    public default R_get_characters_character_id_killmails_recent[] get_characters_character_id_killmails_recent(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/killmails/recent/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_killmails_recent[].class);
    }

    public default R_get_corporations_corporation_id_killmails_recent[] get_corporations_corporation_id_killmails_recent(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/killmails/recent/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_killmails_recent[].class);
    }

    public default R_get_characters_character_id_location get_characters_character_id_location(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/location/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_location.class);
    }

    public default R_get_characters_character_id_ship get_characters_character_id_ship(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/ship/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_ship.class);
    }

    public default R_get_characters_character_id_online get_characters_character_id_online(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/online/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_online.class);
    }

    public default R_get_loyalty_stores_corporation_id_offers[] get_loyalty_stores_corporation_id_offers(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/loyalty/stores/{corporation_id}/offers/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_loyalty_stores_corporation_id_offers[].class);
    }

    public default R_get_characters_character_id_loyalty_points[] get_characters_character_id_loyalty_points(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/loyalty/points/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_loyalty_points[].class);
    }

    public default R_get_characters_character_id_mail[] get_characters_character_id_mail(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail[].class);
    }

    public default R_get_characters_character_id_mail_labels get_characters_character_id_mail_labels(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/labels/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_labels.class);
    }

    public default R_get_characters_character_id_mail_lists[] get_characters_character_id_mail_lists(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/lists/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_lists[].class);
    }

    public default R_get_characters_character_id_mail_mail_id get_characters_character_id_mail_mail_id(long character_id, long mail_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/mail/{mail_id}/".replace("{character_id}", ""+character_id).replace("{mail_id}", ""+mail_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_mail_mail_id.class);
    }

    public default R_get_markets_prices[] get_markets_prices() {
        String url="https://esi.tech.ccp.is/latest/markets/prices/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_prices[].class);
    }

    public default R_get_markets_region_id_orders[] get_markets_region_id_orders(String order_type, long region_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/orders/".replace("{region_id}", ""+region_id)+"?order_type="+flatten(order_type);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_region_id_orders[].class);
    }

    public default R_get_markets_region_id_history[] get_markets_region_id_history(long region_id, long type_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/history/".replace("{region_id}", ""+region_id)+"?type_id="+flatten(type_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_region_id_history[].class);
    }

    public default R_get_markets_structures_structure_id[] get_markets_structures_structure_id(long structure_id) {
        String url="https://esi.tech.ccp.is/latest/markets/structures/{structure_id}/".replace("{structure_id}", ""+structure_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_structures_structure_id[].class);
    }

    public default long[] get_markets_groups() {
        String url="https://esi.tech.ccp.is/latest/markets/groups/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_markets_groups_market_group_id get_markets_groups_market_group_id(long market_group_id) {
        String url="https://esi.tech.ccp.is/latest/markets/groups/{market_group_id}/".replace("{market_group_id}", ""+market_group_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_markets_groups_market_group_id.class);
    }

    public default R_get_characters_character_id_orders[] get_characters_character_id_orders(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/orders/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_orders[].class);
    }

    public default long[] get_markets_region_id_types(long region_id) {
        String url="https://esi.tech.ccp.is/latest/markets/{region_id}/types/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_corporations_corporation_id_orders[] get_corporations_corporation_id_orders(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/orders/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_orders[].class);
    }

    public default long[] get_opportunities_groups() {
        String url="https://esi.tech.ccp.is/latest/opportunities/groups/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_opportunities_groups_group_id get_opportunities_groups_group_id(long group_id) {
        String url="https://esi.tech.ccp.is/latest/opportunities/groups/{group_id}/".replace("{group_id}", ""+group_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_opportunities_groups_group_id.class);
    }

    public default long[] get_opportunities_tasks() {
        String url="https://esi.tech.ccp.is/latest/opportunities/tasks/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_opportunities_tasks_task_id get_opportunities_tasks_task_id(long task_id) {
        String url="https://esi.tech.ccp.is/latest/opportunities/tasks/{task_id}/".replace("{task_id}", ""+task_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_opportunities_tasks_task_id.class);
    }

    public default R_get_characters_character_id_opportunities[] get_characters_character_id_opportunities(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/opportunities/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_opportunities[].class);
    }

    public default R_get_characters_character_id_planets[] get_characters_character_id_planets(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/planets/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_planets[].class);
    }

    public default R_get_characters_character_id_planets_planet_id get_characters_character_id_planets_planet_id(long character_id, long planet_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/planets/{planet_id}/".replace("{character_id}", ""+character_id).replace("{planet_id}", ""+planet_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_planets_planet_id.class);
    }

    public default R_get_universe_schematics_schematic_id get_universe_schematics_schematic_id(long schematic_id) {
        String url="https://esi.tech.ccp.is/latest/universe/schematics/{schematic_id}/".replace("{schematic_id}", ""+schematic_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_schematics_schematic_id.class);
    }

    public default R_get_corporations_corporation_id_customs_offices[] get_corporations_corporation_id_customs_offices(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/customs_offices/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_customs_offices[].class);
    }

    public default long[] get_route_origin_destination(long destination, long origin) {
        String url="https://esi.tech.ccp.is/latest/route/{origin}/{destination}/".replace("{destination}", ""+destination).replace("{origin}", ""+origin);
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_characters_character_id_search get_characters_character_id_search(String[] categories, long character_id, String search) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/search/".replace("{character_id}", ""+character_id)+"?categories="+flatten(categories)+"&search="+flatten(search);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_search.class);
    }

    public default R_get_search get_search(String[] categories, String search) {
        String url="https://esi.tech.ccp.is/latest/search/"+"?categories="+flatten(categories)+"&search="+flatten(search);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_search.class);
    }

    public default R_get_characters_character_id_skillqueue[] get_characters_character_id_skillqueue(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/skillqueue/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_skillqueue[].class);
    }

    public default R_get_characters_character_id_skills get_characters_character_id_skills(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/skills/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_skills.class);
    }

    public default R_get_characters_character_id_attributes get_characters_character_id_attributes(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/attributes/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_attributes.class);
    }

    public default R_get_sovereignty_structures[] get_sovereignty_structures() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/structures/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_structures[].class);
    }

    public default R_get_sovereignty_campaigns[] get_sovereignty_campaigns() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/campaigns/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_campaigns[].class);
    }

    public default R_get_sovereignty_map[] get_sovereignty_map() {
        String url="https://esi.tech.ccp.is/latest/sovereignty/map/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_sovereignty_map[].class);
    }

    public default R_get_status get_status() {
        String url="https://esi.tech.ccp.is/latest/status/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_status.class);
    }

    public default R_post_universe_ids post_universe_ids(String[] names) {
        String url="https://esi.tech.ccp.is/latest/universe/ids/";
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("names", flatten(names));
        String fetched=connectPost(url, content,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_universe_ids.class);
    }

    public default R_get_universe_planets_planet_id get_universe_planets_planet_id(long planet_id) {
        String url="https://esi.tech.ccp.is/latest/universe/planets/{planet_id}/".replace("{planet_id}", ""+planet_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_planets_planet_id.class);
    }

    public default R_get_universe_stations_station_id get_universe_stations_station_id(long station_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stations/{station_id}/".replace("{station_id}", ""+station_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stations_station_id.class);
    }

    public default R_get_universe_structures_structure_id get_universe_structures_structure_id(long structure_id) {
        String url="https://esi.tech.ccp.is/latest/universe/structures/{structure_id}/".replace("{structure_id}", ""+structure_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_structures_structure_id.class);
    }

    public default R_get_universe_systems_system_id get_universe_systems_system_id(long system_id) {
        String url="https://esi.tech.ccp.is/latest/universe/systems/{system_id}/".replace("{system_id}", ""+system_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_systems_system_id.class);
    }

    public default long[] get_universe_systems() {
        String url="https://esi.tech.ccp.is/latest/universe/systems/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_types_type_id get_universe_types_type_id(long type_id) {
        String url="https://esi.tech.ccp.is/latest/universe/types/{type_id}/".replace("{type_id}", ""+type_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_types_type_id.class);
    }

    public default long[] get_universe_types() {
        String url="https://esi.tech.ccp.is/latest/universe/types/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default long[] get_universe_groups() {
        String url="https://esi.tech.ccp.is/latest/universe/groups/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_groups_group_id get_universe_groups_group_id(long group_id) {
        String url="https://esi.tech.ccp.is/latest/universe/groups/{group_id}/".replace("{group_id}", ""+group_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_groups_group_id.class);
    }

    public default long[] get_universe_categories() {
        String url="https://esi.tech.ccp.is/latest/universe/categories/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_categories_category_id get_universe_categories_category_id(long category_id) {
        String url="https://esi.tech.ccp.is/latest/universe/categories/{category_id}/".replace("{category_id}", ""+category_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_categories_category_id.class);
    }

    public default R_post_universe_names[] post_universe_names(long[] ids) {
        String url="https://esi.tech.ccp.is/latest/universe/names/";
        java.util.Map<String, String> content = new java.util.HashMap<>();
        content.put("ids", flatten(ids));
        String fetched=connectPost(url, content,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_post_universe_names[].class);
    }

    public default long[] get_universe_structures() {
        String url="https://esi.tech.ccp.is/latest/universe/structures/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_races[] get_universe_races() {
        String url="https://esi.tech.ccp.is/latest/universe/races/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_races[].class);
    }

    public default R_get_universe_factions[] get_universe_factions() {
        String url="https://esi.tech.ccp.is/latest/universe/factions/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_factions[].class);
    }

    public default R_get_universe_bloodlines[] get_universe_bloodlines() {
        String url="https://esi.tech.ccp.is/latest/universe/bloodlines/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_bloodlines[].class);
    }

    public default long[] get_universe_regions() {
        String url="https://esi.tech.ccp.is/latest/universe/regions/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_regions_region_id get_universe_regions_region_id(long region_id) {
        String url="https://esi.tech.ccp.is/latest/universe/regions/{region_id}/".replace("{region_id}", ""+region_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_regions_region_id.class);
    }

    public default long[] get_universe_constellations() {
        String url="https://esi.tech.ccp.is/latest/universe/constellations/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_constellations_constellation_id get_universe_constellations_constellation_id(long constellation_id) {
        String url="https://esi.tech.ccp.is/latest/universe/constellations/{constellation_id}/".replace("{constellation_id}", ""+constellation_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_constellations_constellation_id.class);
    }

    public default R_get_universe_moons_moon_id get_universe_moons_moon_id(long moon_id) {
        String url="https://esi.tech.ccp.is/latest/universe/moons/{moon_id}/".replace("{moon_id}", ""+moon_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_moons_moon_id.class);
    }

    public default R_get_universe_stargates_stargate_id get_universe_stargates_stargate_id(long stargate_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stargates/{stargate_id}/".replace("{stargate_id}", ""+stargate_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stargates_stargate_id.class);
    }

    public default long[] get_universe_graphics() {
        String url="https://esi.tech.ccp.is/latest/universe/graphics/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_universe_graphics_graphic_id get_universe_graphics_graphic_id(long graphic_id) {
        String url="https://esi.tech.ccp.is/latest/universe/graphics/{graphic_id}/".replace("{graphic_id}", ""+graphic_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_graphics_graphic_id.class);
    }

    public default R_get_universe_system_jumps[] get_universe_system_jumps() {
        String url="https://esi.tech.ccp.is/latest/universe/system_jumps/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_system_jumps[].class);
    }

    public default R_get_universe_system_kills[] get_universe_system_kills() {
        String url="https://esi.tech.ccp.is/latest/universe/system_kills/";
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_system_kills[].class);
    }

    public default R_get_universe_stars_star_id get_universe_stars_star_id(long star_id) {
        String url="https://esi.tech.ccp.is/latest/universe/stars/{star_id}/".replace("{star_id}", ""+star_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_universe_stars_star_id.class);
    }

    public default double get_characters_character_id_wallet(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, double.class);
    }

    public default R_get_characters_character_id_wallet_journal[] get_characters_character_id_wallet_journal(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/journal/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_journal[].class);
    }

    public default R_get_characters_character_id_wallet_transactions[] get_characters_character_id_wallet_transactions(long character_id) {
        String url="https://esi.tech.ccp.is/latest/characters/{character_id}/wallet/transactions/".replace("{character_id}", ""+character_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_characters_character_id_wallet_transactions[].class);
    }

    public default R_get_corporations_corporation_id_wallets[] get_corporations_corporation_id_wallets(long corporation_id) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/".replace("{corporation_id}", ""+corporation_id);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets[].class);
    }

    public default R_get_corporations_corporation_id_wallets_division_journal[] get_corporations_corporation_id_wallets_division_journal(long corporation_id, long division) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/{division}/journal/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_journal[].class);
    }

    public default R_get_corporations_corporation_id_wallets_division_transactions[] get_corporations_corporation_id_wallets_division_transactions(long corporation_id, long division) {
        String url="https://esi.tech.ccp.is/latest/corporations/{corporation_id}/wallets/{division}/transactions/".replace("{corporation_id}", ""+corporation_id).replace("{division}", ""+division);
        String fetched=connectGet(url,true);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_corporations_corporation_id_wallets_division_transactions[].class);
    }

    public default long[] get_wars() {
        String url="https://esi.tech.ccp.is/latest/wars/";
        String fetched=connectGet(url,false);
        return convert(fetched, long[].class);
    }

    public default R_get_wars_war_id get_wars_war_id(long war_id) {
        String url="https://esi.tech.ccp.is/latest/wars/{war_id}/".replace("{war_id}", ""+war_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_wars_war_id.class);
    }

    public default R_get_wars_war_id_killmails[] get_wars_war_id_killmails(long war_id) {
        String url="https://esi.tech.ccp.is/latest/wars/{war_id}/killmails/".replace("{war_id}", ""+war_id);
        String fetched=connectGet(url,false);
        return convert(fetched, is.ccp.tech.esi.responses.R_get_wars_war_id_killmails[].class);
    }
}
