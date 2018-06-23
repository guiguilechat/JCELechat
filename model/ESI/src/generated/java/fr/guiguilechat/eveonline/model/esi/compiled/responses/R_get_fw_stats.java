package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_fw_stats {
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * Summary of kills against an enemy faction for the given faction
     */
    public M_get_fw_stats_3 kills;
    /**
     * How many pilots fight for the given faction
     */
    public int pilots;
    /**
     * The number of solar systems controlled by the given faction
     */
    public int systems_controlled;
    /**
     * Summary of victory points gained for the given faction
     */
    public M_get_fw_stats_3 victory_points;
}
