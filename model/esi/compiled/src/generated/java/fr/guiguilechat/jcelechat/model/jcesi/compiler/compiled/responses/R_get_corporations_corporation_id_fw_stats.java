package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

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
    public get_characters_character_id_fw_stats_kills kills;
    /**
     * How many pilots the enlisted corporation has. Will not be included if corporation is not enlisted in faction warfare
     */
    public int pilots;
    /**
     * Summary of victory points gained by the given corporation for the enlisted faction
     */
    public get_characters_character_id_fw_stats_kills victory_points;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_fw_stats othersame = ((R_get_corporations_corporation_id_fw_stats) other);
        if ((enlisted_on!= othersame.enlisted_on)&&((enlisted_on == null)||(!enlisted_on.equals(othersame.enlisted_on)))) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if ((kills!= othersame.kills)&&((kills == null)||(!kills.equals(othersame.kills)))) {
            return false;
        }
        if (pilots!= othersame.pilots) {
            return false;
        }
        if ((victory_points!= othersame.victory_points)&&((victory_points == null)||(!victory_points.equals(othersame.victory_points)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((enlisted_on == null)? 0 :enlisted_on.hashCode())+ faction_id)+((kills == null)? 0 :kills.hashCode()))+ pilots)+((victory_points == null)? 0 :victory_points.hashCode()));
    }
}
