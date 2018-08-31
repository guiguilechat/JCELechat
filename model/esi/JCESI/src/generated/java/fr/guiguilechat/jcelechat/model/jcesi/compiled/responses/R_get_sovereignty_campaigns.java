package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_sovereignty_campaigns othersame = ((R_get_sovereignty_campaigns) other);
        if (attackers_score!= othersame.attackers_score) {
            return false;
        }
        if (campaign_id!= othersame.campaign_id) {
            return false;
        }
        if (constellation_id!= othersame.constellation_id) {
            return false;
        }
        if (defender_id!= othersame.defender_id) {
            return false;
        }
        if (defender_score!= othersame.defender_score) {
            return false;
        }
        if ((event_type!= othersame.event_type)&&((event_type == null)||(!event_type.equals(othersame.event_type)))) {
            return false;
        }
        if ((participants!= othersame.participants)&&((participants == null)||(!participants.equals(othersame.participants)))) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if ((start_time!= othersame.start_time)&&((start_time == null)||(!start_time.equals(othersame.start_time)))) {
            return false;
        }
        if (structure_id!= othersame.structure_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((Double.hashCode(attackers_score)+ campaign_id)+ constellation_id)+ defender_id)+ Double.hashCode(defender_score))+((event_type == null)? 0 :event_type.hashCode()))+((participants == null)? 0 :participants.hashCode()))+ solar_system_id)+((start_time == null)? 0 :start_time.hashCode()))+ Long.hashCode(structure_id));
    }
}
