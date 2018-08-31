package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_agents_research {
    /**
     * agent_id integer
     */
    public int agent_id;
    /**
     * points_per_day number
     */
    public float points_per_day;
    /**
     * remainder_points number
     */
    public float remainder_points;
    /**
     * skill_type_id integer
     */
    public int skill_type_id;
    /**
     * started_at string
     */
    public String started_at;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_agents_research othersame = ((R_get_characters_character_id_agents_research) other);
        if (agent_id!= othersame.agent_id) {
            return false;
        }
        if (points_per_day!= othersame.points_per_day) {
            return false;
        }
        if (remainder_points!= othersame.remainder_points) {
            return false;
        }
        if (skill_type_id!= othersame.skill_type_id) {
            return false;
        }
        if ((started_at!= othersame.started_at)&&((started_at == null)||(!started_at.equals(othersame.started_at)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((agent_id + Double.hashCode(points_per_day))+ Double.hashCode(remainder_points))+ skill_type_id)+((started_at == null)? 0 :started_at.hashCode()));
    }
}
