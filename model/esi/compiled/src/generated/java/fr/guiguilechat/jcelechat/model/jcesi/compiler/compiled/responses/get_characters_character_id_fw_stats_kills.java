package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_fw_stats_kills {
    /**
     * Last week's total number of kills by a given character against enemy factions
     */
    public int last_week;
    /**
     * Total number of kills by a given character against enemy factions since the character enlisted
     */
    public int total;
    /**
     * Yesterday's total number of kills by a given character against enemy factions
     */
    public int yesterday;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_fw_stats_kills othersame = ((get_characters_character_id_fw_stats_kills) other);
        if (last_week!= othersame.last_week) {
            return false;
        }
        if (total!= othersame.total) {
            return false;
        }
        if (yesterday!= othersame.yesterday) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((last_week + total)+ yesterday);
    }
}
