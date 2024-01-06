package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_fw_leaderboards_kills {
    /**
     * Top 4 ranking of factions active in faction warfare by total kills. A faction is considered "active" if they have participated in faction warfare in the past 14 days
     */
    public get_fw_leaderboards_active_total[] active_total;
    /**
     * Top 4 ranking of factions by kills in the past week
     */
    public get_fw_leaderboards_active_total[] last_week;
    /**
     * Top 4 ranking of factions by kills in the past day
     */
    public get_fw_leaderboards_active_total[] yesterday;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_fw_leaderboards_kills othersame = ((get_fw_leaderboards_kills) other);
        if ((active_total!= othersame.active_total)&&((active_total == null)||(!active_total.equals(othersame.active_total)))) {
            return false;
        }
        if ((last_week!= othersame.last_week)&&((last_week == null)||(!last_week.equals(othersame.last_week)))) {
            return false;
        }
        if ((yesterday!= othersame.yesterday)&&((yesterday == null)||(!yesterday.equals(othersame.yesterday)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((active_total == null)? 0 :active_total.hashCode())+((last_week == null)? 0 :last_week.hashCode()))+((yesterday == null)? 0 :yesterday.hashCode()));
    }
}
