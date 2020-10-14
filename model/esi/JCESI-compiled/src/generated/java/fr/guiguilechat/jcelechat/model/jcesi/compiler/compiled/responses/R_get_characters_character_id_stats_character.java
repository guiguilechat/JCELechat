package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_stats_character {
    /**
     * days_of_activity integer
     */
    public long days_of_activity;
    /**
     * minutes integer
     */
    public long minutes;
    /**
     * sessions_started integer
     */
    public long sessions_started;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_character othersame = ((R_get_characters_character_id_stats_character) other);
        if (days_of_activity!= othersame.days_of_activity) {
            return false;
        }
        if (minutes!= othersame.minutes) {
            return false;
        }
        if (sessions_started!= othersame.sessions_started) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Long.hashCode(days_of_activity)+ Long.hashCode(minutes))+ Long.hashCode(sessions_started));
    }
}
