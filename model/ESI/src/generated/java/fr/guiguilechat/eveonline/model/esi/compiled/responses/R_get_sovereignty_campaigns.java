package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_sovereignty_campaigns {
    /**
     * Score for all attacking parties, only present in Defense Events.
     * 
     */
    public float attackers_score;
    /**
     * Unique ID for this campaign.
     */
    public int campaign_id;
    /**
     * The constellation in which the campaign will take place.
     * 
     */
    public int constellation_id;
    /**
     * Defending alliance, only present in Defense Events
     * 
     */
    public int defender_id;
    /**
     * Score for the defending alliance, only present in Defense Events.
     * 
     */
    public float defender_score;
    /**
     * Type of event this campaign is for. tcu_defense, ihub_defense and station_defense are referred to as "Defense Events", station_freeport as "Freeport Events".
     * 
     */
    public String event_type;
    /**
     * Alliance participating and their respective scores, only present in Freeport Events.
     * 
     */
    public R_get_sovereignty_campaigns_participants[] participants;
    /**
     * The solar system the structure is located in.
     * 
     */
    public int solar_system_id;
    /**
     * Time the event is scheduled to start.
     * 
     */
    public String start_time;
    /**
     * The structure item ID that is related to this campaign.
     * 
     */
    public long structure_id;
}
