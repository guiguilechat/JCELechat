package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class M_get_fw_leaderboards_2 {
    /**
     * Top 4 rankings of factions by number of kills from yesterday, last week and in total
     */
    public get_fw_leaderboards_kills kills;
    /**
     * Top 4 rankings of factions by victory points from yesterday, last week and in total
     */
    public get_fw_leaderboards_kills victory_points;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_fw_leaderboards_2 othersame = ((M_get_fw_leaderboards_2) other);
        if ((kills!= othersame.kills)&&((kills == null)||(!kills.equals(othersame.kills)))) {
            return false;
        }
        if ((victory_points!= othersame.victory_points)&&((victory_points == null)||(!victory_points.equals(othersame.victory_points)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((kills == null)? 0 :kills.hashCode())+((victory_points == null)? 0 :victory_points.hashCode()));
    }
}
