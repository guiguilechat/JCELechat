package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_fw_stats_3 {
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
        M_get_fw_stats_3 othersame = ((M_get_fw_stats_3) other);
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
