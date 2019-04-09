package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ITransfer;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_icons;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_corporationhistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_portrait;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_alliancehistory;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_icons;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_wars;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_incursions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_search;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_campaigns;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_structures;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_ancestries;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_bloodlines;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_races;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_ids;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_universe_names;


/**
 * interface to access the ESI without an account.<br />This gives access to static data, eg items, markets, etc.
 */
public interface G_IDCAccess
    extends ITransfer
{

    /**
     * List all alliances
     * <p>
     * List all active player alliances<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<Integer[]> get_alliances(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/alliances/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<Integer[]> get_alliances_corporations(int alliance_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/corporations/".replace("{alliance_id}", ""+alliance_id));
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * Get alliance icon
     * <p>
     * Get the icon urls for a alliance<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public default Requested<R_get_alliances_alliance_id_icons> get_alliances_icons(int alliance_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/alliances/{alliance_id}/icons/".replace("{alliance_id}", ""+alliance_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id_icons.class));
    }

    /**
     * Character affiliation
     * <p>
     * Bulk lookup of character IDs to corporation, alliance and faction<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param characters
     *     The character IDs to fetch affiliations for. All characters must exist, or none will be returned
     */
    public default Requested<R_post_characters_affiliation[]> post_affiliation(int[] characters, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/affiliation/");
        Map<String, Object> content = new HashMap<>();
        content.put("characters", characters);
        return requestPost(url, properties, content, R_post_characters_affiliation[].class);
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
    public default Requested<R_get_characters_character_id_corporationhistory[]> get_characters_corporationhistory(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/characters/{character_id}/corporationhistory/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_corporationhistory[].class));
    }

    /**
     * Get public contract bids
     * <p>
     * Lists bids on a public auction contract<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param contract_id
     *     ID of a contract
     * @param page
     *     Which page of results to return
     */
    public default Requested<R_get_contracts_public_bids_contract_id[]> get_contracts_public_bids(int contract_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/contracts/public/bids/{contract_id}/".replace("{contract_id}", ""+contract_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_bids_contract_id[].class));
    }

    /**
     * Get public contract items
     * <p>
     * Lists items of a public contract<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param contract_id
     *     ID of a contract
     * @param page
     *     Which page of results to return
     */
    public default Requested<R_get_contracts_public_items_contract_id[]> get_contracts_public_items(int contract_id, Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/contracts/public/items/{contract_id}/".replace("{contract_id}", ""+contract_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id[].class));
    }

    /**
     * Get public contracts
     * <p>
     * Returns a paginated list of all public contracts in the given region<br />
     * This route is cached for up to 1800 seconds
     * </p>
     * 
     * @param page
     *     Which page of results to return
     * @param region_id
     *     An EVE region id
     */
    public default Requested<R_get_contracts_public_region_id[]> get_contracts_public(Integer page, int region_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/contracts/public/{region_id}/".replace("{region_id}", ""+region_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id[].class));
    }

    /**
     * Get npc corporations
     * <p>
     * Get a list of npc corporations<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_corporations_npccorps(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/npccorps/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<R_get_corporations_corporation_id_icons> get_corporations_icons(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/corporations/{corporation_id}/icons/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_icons.class));
    }

    /**
     * Get attributes
     * <p>
     * Get a list of dogma attribute ids<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_dogma_attributes(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/dogma/attributes/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<R_get_dogma_attributes_attribute_id> get_dogma_attributes(int attribute_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/dogma/attributes/{attribute_id}/".replace("{attribute_id}", ""+attribute_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id.class));
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
    public default Requested<R_get_dogma_dynamic_items_type_id_item_id> get_dogma_dynamic_items(long item_id, int type_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/dogma/dynamic/items/{type_id}/{item_id}/".replace("{item_id}", ""+item_id).replace("{type_id}", ""+type_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_dynamic_items_type_id_item_id.class));
    }

    /**
     * Get effects
     * <p>
     * Get a list of dogma effect ids<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_dogma_effects(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/dogma/effects/");
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * List of the top factions in faction warfare
     * <p>
     * Top 4 leaderboard of factions for kills and victory points separated by total, last week and yesterday<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<M_get_fw_leaderboards_2> get_fw_leaderboards(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * List of the top pilots in faction warfare
     * <p>
     * Top 100 leaderboard of pilots for kills and victory points separated by total, last week and yesterday<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<M_get_fw_leaderboards_2> get_fw_leaderboards_characters(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/characters/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * List of the top corporations in faction warfare
     * <p>
     * Top 10 leaderboard of corporations for kills and victory points separated by total, last week and yesterday<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fw/leaderboards/corporations/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_fw_leaderboards_2.class));
    }

    /**
     * An overview of statistics about factions involved in faction warfare
     * <p>
     * Statistical overviews of factions involved in faction warfare<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_fw_stats[]> get_fw_stats(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fw/stats/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_stats[].class));
    }

    /**
     * Data about which NPC factions are at war
     * <p>
     * Data about which NPC factions are at war<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_fw_wars[]> get_fw_wars(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/fw/wars/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_wars[].class));
    }

    /**
     * List incursions
     * <p>
     * Return a list of current incursions<br />
     * This route is cached for up to 300 seconds
     * </p>
     */
    public default Requested<R_get_incursions[]> get_incursions(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/incursions/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_incursions[].class));
    }

    /**
     * List industry facilities
     * <p>
     * Return a list of industry facilities<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_industry_facilities[]> get_industry_facilities(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/industry/facilities/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_facilities[].class));
    }

    /**
     * List solar system cost indices
     * <p>
     * Return cost indices for solar systems<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_industry_systems[]> get_industry_systems(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/industry/systems/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems[].class));
    }

    /**
     * List insurance levels
     * <p>
     * Return available insurance levels for all ship types<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_insurance_prices[]> get_insurance_prices(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/insurance/prices/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices[].class));
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
    public default Requested<R_get_killmails_killmail_id_killmail_hash> get_killmails(String killmail_hash, int killmail_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/killmails/{killmail_id}/{killmail_hash}/".replace("{killmail_hash}", ""+killmail_hash).replace("{killmail_id}", ""+killmail_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_killmails_killmail_id_killmail_hash.class));
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
    public default Requested<R_get_loyalty_stores_corporation_id_offers[]> get_loyalty_stores_offers(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/loyalty/stores/{corporation_id}/offers/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers[].class));
    }

    /**
     * Get item groups
     * <p>
     * Get a list of item groups<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_markets_groups(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/groups/");
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * Get item group information
     * <p>
     * Get information on an item group<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param market_group_id
     *     An Eve item group ID
     */
    public default Requested<R_get_markets_groups_market_group_id> get_markets_groups(int market_group_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/groups/{market_group_id}/".replace("{market_group_id}", ""+market_group_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id.class));
    }

    /**
     * List market prices
     * <p>
     * Return a list of prices<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_markets_prices[]> get_markets_prices(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/prices/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_prices[].class));
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
    public default Requested<R_get_markets_region_id_history[]> get_markets_history(int region_id, int type_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/history/".replace("{region_id}", ""+region_id)+"?"+"&type_id="+flatten(type_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history[].class));
    }

    /**
     * List orders in a region
     * <p>
     * Return a list of orders in a region<br />
     * This route is cached for up to 300 seconds
     * </p>
     * 
     * @param order_type
     *     Filter buy/sell orders, return all orders by default. If you query without type_id, we always return both buy and sell orders
     * @param page
     *     Which page of results to return
     * @param region_id
     *     Return orders in this region
     * @param type_id
     *     Return orders only for this type
     */
    public default Requested<R_get_markets_region_id_orders[]> get_markets_orders(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type order_type,
        Integer page,
        int region_id,
        Integer type_id,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/orders/".replace("{region_id}", ""+region_id)+"?"+(order_type==null?"":"&order_type="+flatten(order_type))+(page==null?"":"&page="+flatten(page))+(type_id==null?"":"&type_id="+flatten(type_id)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders[].class));
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
    public default Requested<Integer[]> get_markets_types(Integer page, int region_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/markets/{region_id}/types/".replace("{region_id}", ""+region_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * Get opportunities groups
     * <p>
     * Return a list of opportunities groups<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_opportunities_groups(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/opportunities/groups/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
     */
    public default Requested<R_get_opportunities_groups_group_id> get_opportunities_groups(int group_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/opportunities/groups/{group_id}/".replace("{group_id}", ""+group_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_groups_group_id.class));
    }

    /**
     * Get opportunities tasks
     * <p>
     * Return a list of opportunities tasks<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_opportunities_tasks(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/opportunities/tasks/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<R_get_opportunities_tasks_task_id> get_opportunities_tasks(int task_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/opportunities/tasks/{task_id}/".replace("{task_id}", ""+task_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_tasks_task_id.class));
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
    public default Requested<Integer[]> get_route(int[] avoid,
        int[][] connections,
        int destination,
        fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag flag,
        int origin,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/route/{origin}/{destination}/".replace("{destination}", ""+destination).replace("{origin}", ""+origin)+"?"+(avoid==null?"":"&avoid="+flatten(avoid))+(connections==null?"":"&connections="+flatten(connections))+(flag==null?"":"&flag="+flatten(flag)));
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * List sovereignty campaigns
     * <p>
     * Shows sovereignty data for campaigns.<br />
     * This route is cached for up to 5 seconds
     * </p>
     */
    public default Requested<R_get_sovereignty_campaigns[]> get_sovereignty_campaigns(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/sovereignty/campaigns/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_campaigns[].class));
    }

    /**
     * List sovereignty of systems
     * <p>
     * Shows sovereignty information for solar systems<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_sovereignty_map[]> get_sovereignty_map(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/sovereignty/map/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_map[].class));
    }

    /**
     * List sovereignty structures
     * <p>
     * Shows sovereignty data for structures.<br />
     * This route is cached for up to 120 seconds
     * </p>
     */
    public default Requested<R_get_sovereignty_structures[]> get_sovereignty_structures(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/sovereignty/structures/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_sovereignty_structures[].class));
    }

    /**
     * Retrieve the uptime and player counts
     * <p>
     * EVE Server status<br />
     * This route is cached for up to 30 seconds
     * </p>
     */
    public default Requested<R_get_status> get_status(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/status/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status.class));
    }

    /**
     * Get ancestries
     * <p>
     * Get all character ancestries<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_universe_ancestries[]> get_universe_ancestries(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/ancestries/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_ancestries[].class));
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
    public default Requested<R_get_universe_asteroid_belts_asteroid_belt_id> get_universe_asteroid_belts(int asteroid_belt_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/asteroid_belts/{asteroid_belt_id}/".replace("{asteroid_belt_id}", ""+asteroid_belt_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id.class));
    }

    /**
     * Get bloodlines
     * <p>
     * Get a list of bloodlines<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_universe_bloodlines[]> get_universe_bloodlines(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/bloodlines/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_bloodlines[].class));
    }

    /**
     * Get item categories
     * <p>
     * Get a list of item categories<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_universe_categories(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/categories/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
     */
    public default Requested<R_get_universe_categories_category_id> get_universe_categories(int category_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/categories/{category_id}/".replace("{category_id}", ""+category_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id.class));
    }

    /**
     * Get constellations
     * <p>
     * Get a list of constellations<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_universe_constellations(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/constellations/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
     */
    public default Requested<R_get_universe_constellations_constellation_id> get_universe_constellations(int constellation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/constellations/{constellation_id}/".replace("{constellation_id}", ""+constellation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id.class));
    }

    /**
     * Get graphics
     * <p>
     * Get a list of graphics<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_universe_graphics(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/graphics/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<R_get_universe_graphics_graphic_id> get_universe_graphics(int graphic_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/graphics/{graphic_id}/".replace("{graphic_id}", ""+graphic_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_graphics_graphic_id.class));
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
    public default Requested<Integer[]> get_universe_groups(Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/groups/"+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,java.lang.Integer[].class));
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
     */
    public default Requested<R_get_universe_groups_group_id> get_universe_groups(int group_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/groups/{group_id}/".replace("{group_id}", ""+group_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id.class));
    }

    /**
     * Bulk names to IDs
     * <p>
     * Resolve a set of names to IDs in the following categories: agents, alliances, characters, constellations, corporations factions, inventory_types, regions, stations, and systems. Only exact matches will be returned. All names searched for are cached for 12 hours
     * </p>
     * 
     * @param names
     *     The names to resolve
     */
    public default Requested<R_post_universe_ids> post_universe_ids(String[] names, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/ids/");
        Map<String, Object> content = new HashMap<>();
        content.put("names", names);
        return requestPost(url, properties, content, R_post_universe_ids.class);
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
    public default Requested<R_get_universe_moons_moon_id> get_universe_moons(int moon_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/moons/{moon_id}/".replace("{moon_id}", ""+moon_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_moons_moon_id.class));
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
    public default Requested<R_get_universe_planets_planet_id> get_universe_planets(int planet_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/planets/{planet_id}/".replace("{planet_id}", ""+planet_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id.class));
    }

    /**
     * Get character races
     * <p>
     * Get a list of character races<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_universe_races[]> get_universe_races(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/races/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_races[].class));
    }

    /**
     * Get regions
     * <p>
     * Get a list of regions<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_universe_regions(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/regions/");
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * Get region information
     * <p>
     * Get information on a region<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param region_id
     *     region_id integer
     */
    public default Requested<R_get_universe_regions_region_id> get_universe_regions(int region_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/regions/{region_id}/".replace("{region_id}", ""+region_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id.class));
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
    public default Requested<R_get_universe_schematics_schematic_id> get_universe_schematics(int schematic_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/schematics/{schematic_id}/".replace("{schematic_id}", ""+schematic_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_schematics_schematic_id.class));
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
    public default Requested<R_get_universe_stargates_stargate_id> get_universe_stargates(int stargate_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/stargates/{stargate_id}/".replace("{stargate_id}", ""+stargate_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id.class));
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
    public default Requested<R_get_universe_stars_star_id> get_universe_stars(int star_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/stars/{star_id}/".replace("{star_id}", ""+star_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stars_star_id.class));
    }

    /**
     * List all public structures
     * <p>
     * List all public structures<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param filter
     *     Only list public structures that have this service online
     */
    public default Requested<Long[]> get_universe_structures(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter filter, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/structures/"+"?"+(filter==null?"":"&filter="+flatten(filter)));
        return (requestGet(url, properties,java.lang.Long[].class));
    }

    /**
     * Get system jumps
     * <p>
     * Get the number of jumps in solar systems within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with jumps will be listed<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_universe_system_jumps[]> get_universe_system_jumps(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/system_jumps/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps[].class));
    }

    /**
     * Get solar systems
     * <p>
     * Get a list of solar systems<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<Integer[]> get_universe_systems(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/systems/");
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<Integer[]> get_universe_types(Integer page, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/universe/types/"+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,java.lang.Integer[].class));
    }

    /**
     * List wars
     * <p>
     * Return a list of wars<br />
     * This route is cached for up to 3600 seconds
     * </p>
     * 
     * @param max_war_id
     *     Only return wars with ID smaller than this
     */
    public default Requested<Integer[]> get_wars(Integer max_war_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/wars/"+"?"+(max_war_id==null?"":"&max_war_id="+flatten(max_war_id)));
        return (requestGet(url, properties,java.lang.Integer[].class));
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
    public default Requested<R_get_wars_war_id> get_wars(int war_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/wars/{war_id}/".replace("{war_id}", ""+war_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id.class));
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
    public default Requested<M_get_killmails_2 []> get_wars_killmails(Integer page, int war_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v1/wars/{war_id}/killmails/".replace("{war_id}", ""+war_id)+"?"+(page==null?"":"&page="+flatten(page)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_killmails_2[].class));
    }

    /**
     * Get character portraits
     * <p>
     * Get portrait urls for a character<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param character_id
     *     An EVE character ID
     */
    public default Requested<R_get_characters_character_id_portrait> get_characters_portrait(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/characters/{character_id}/portrait/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_portrait.class));
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
    public default Requested<R_get_corporations_corporation_id_alliancehistory[]> get_corporations_alliancehistory(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/corporations/{corporation_id}/alliancehistory/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_alliancehistory[].class));
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
    public default Requested<R_get_dogma_effects_effect_id> get_dogma_effects(int effect_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/dogma/effects/{effect_id}/".replace("{effect_id}", ""+effect_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id.class));
    }

    /**
     * Ownership of faction warfare systems
     * <p>
     * An overview of the current ownership of faction warfare solar systems<br />
     * This route is cached for up to 1800 seconds
     * </p>
     */
    public default Requested<R_get_fw_systems[]> get_fw_systems(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/fw/systems/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_systems[].class));
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
     * @param search
     *     The string to search on
     * @param strict
     *     Whether the search should be a strict match
     */
    public default Requested<R_get_search> get(String[] categories,
        String search,
        Boolean strict,
        Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/search/"+"?"+(categories==null?"":"&categories="+flatten(categories))+(search==null?"":"&search="+flatten(search))+(strict==null?"":"&strict="+flatten(strict)));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_search.class));
    }

    /**
     * Get factions
     * <p>
     * Get a list of factions<br />
     * This route expires daily at 11:05
     * </p>
     */
    public default Requested<R_get_universe_factions[]> get_universe_factions(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/universe/factions/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions[].class));
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
    public default Requested<R_get_universe_stations_station_id> get_universe_stations(int station_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/universe/stations/{station_id}/".replace("{station_id}", ""+station_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id.class));
    }

    /**
     * Get system kills
     * <p>
     * Get the number of ship, pod and NPC kills per solar system within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with kills will be listed<br />
     * This route is cached for up to 3600 seconds
     * </p>
     */
    public default Requested<R_get_universe_system_kills[]> get_universe_system_kills(Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v2/universe/system_kills/");
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills[].class));
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
    public default Requested<R_get_alliances_alliance_id> get_alliances(int alliance_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/alliances/{alliance_id}/".replace("{alliance_id}", ""+alliance_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id.class));
    }

    /**
     * Get names and categories for a set of IDs
     * <p>
     * Resolve a set of IDs to names and categories. Supported ID's for resolving are: Characters, Corporations, Alliances, Stations, Solar Systems, Constellations, Regions, Types, Factions
     * </p>
     * 
     * @param ids
     *     The ids to resolve
     */
    public default Requested<R_post_universe_names[]> post_universe_names(int[] ids, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/universe/names/");
        Map<String, Object> content = new HashMap<>();
        content.put("ids", ids);
        return requestPost(url, properties, content, R_post_universe_names[].class);
    }

    /**
     * Get type information
     * <p>
     * Get information on a type<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param type_id
     *     An Eve item type ID
     */
    public default Requested<R_get_universe_types_type_id> get_universe_types(int type_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v3/universe/types/{type_id}/".replace("{type_id}", ""+type_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id.class));
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
    public default Requested<R_get_characters_character_id> get_characters(int character_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/characters/{character_id}/".replace("{character_id}", ""+character_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id.class));
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
    public default Requested<R_get_corporations_corporation_id> get_corporations(int corporation_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/corporations/{corporation_id}/".replace("{corporation_id}", ""+corporation_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id.class));
    }

    /**
     * Get solar system information
     * <p>
     * Get information on a solar system.<br />
     * This route expires daily at 11:05
     * </p>
     * 
     * @param system_id
     *     system_id integer
     */
    public default Requested<R_get_universe_systems_system_id> get_universe_systems(int system_id, Map<String, String> properties) {
        String url = ("https://esi.evetech.net/v4/universe/systems/{system_id}/".replace("{system_id}", ""+system_id));
        return (requestGet(url, properties,fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id.class));
    }
}
