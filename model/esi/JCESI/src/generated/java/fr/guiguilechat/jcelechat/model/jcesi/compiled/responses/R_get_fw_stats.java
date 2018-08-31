package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_fw_stats othersame = ((R_get_fw_stats) other);
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if ((kills!= othersame.kills)&&((kills == null)||(!kills.equals(othersame.kills)))) {
            return false;
        }
        if (pilots!= othersame.pilots) {
            return false;
        }
        if (systems_controlled!= othersame.systems_controlled) {
            return false;
        }
        if ((victory_points!= othersame.victory_points)&&((victory_points == null)||(!victory_points.equals(othersame.victory_points)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((faction_id +((kills == null)? 0 :kills.hashCode()))+ pilots)+ systems_controlled)+((victory_points == null)? 0 :victory_points.hashCode()));
    }
}
