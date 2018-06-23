package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_fw_stats {
    /**
     * The enlistment date of the given corporation into faction warfare. Will not be included if corporation is not enlisted in faction warfare
     */
    public String enlisted_on;
    /**
     * The faction the given corporation is enlisted to fight for. Will not be included if corporation is not enlisted in faction warfare
     */
    public int faction_id;
    /**
     * Summary of kills done by the given corporation against enemy factions
     */
    public M_get_fw_stats_3 kills;
    /**
     * How many pilots the enlisted corporation has. Will not be included if corporation is not enlisted in faction warfare
     */
    public int pilots;
    /**
     * Summary of victory points gained by the given corporation for the enlisted faction
     */
    public M_get_fw_stats_3 victory_points;
}
