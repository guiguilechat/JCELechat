package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_characters_character_id_fw_stats {
    /**
     * The given character's current faction rank
     */
    public int current_rank;
    /**
     * The enlistment date of the given character into faction warfare. Will not be included if character is not enlisted in faction warfare
     */
    public String enlisted_on;
    /**
     * The faction the given character is enlisted to fight for. Will not be included if character is not enlisted in faction warfare
     */
    public int faction_id;
    /**
     * The given character's highest faction rank achieved
     */
    public int highest_rank;
    /**
     * Summary of kills done by the given character against enemy factions
     */
    public M_get_fw_stats_3 kills;
    /**
     * Summary of victory points gained by the given character for the enlisted faction
     */
    public M_get_fw_stats_3 victory_points;
}
