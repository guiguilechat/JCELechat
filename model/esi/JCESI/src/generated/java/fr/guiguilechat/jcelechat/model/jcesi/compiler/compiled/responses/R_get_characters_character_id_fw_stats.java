package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

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
    public get_characters_character_id_fw_stats_kills kills;
    /**
     * Summary of victory points gained by the given character for the enlisted faction
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
        R_get_characters_character_id_fw_stats othersame = ((R_get_characters_character_id_fw_stats) other);
        if (current_rank!= othersame.current_rank) {
            return false;
        }
        if ((enlisted_on!= othersame.enlisted_on)&&((enlisted_on == null)||(!enlisted_on.equals(othersame.enlisted_on)))) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if (highest_rank!= othersame.highest_rank) {
            return false;
        }
        if ((kills!= othersame.kills)&&((kills == null)||(!kills.equals(othersame.kills)))) {
            return false;
        }
        if ((victory_points!= othersame.victory_points)&&((victory_points == null)||(!victory_points.equals(othersame.victory_points)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((current_rank +((enlisted_on == null)? 0 :enlisted_on.hashCode()))+ faction_id)+ highest_rank)+((kills == null)? 0 :kills.hashCode()))+((victory_points == null)? 0 :victory_points.hashCode()));
    }
}
