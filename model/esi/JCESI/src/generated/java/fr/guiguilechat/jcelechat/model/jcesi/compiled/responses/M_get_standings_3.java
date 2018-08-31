package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_standings_3 {
    /**
     * from_id integer
     */
    public int from_id;
    /**
     * from_type string
     */
    public String from_type;
    /**
     * standing number
     */
    public float standing;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_standings_3 othersame = ((M_get_standings_3) other);
        if (from_id!= othersame.from_id) {
            return false;
        }
        if ((from_type!= othersame.from_type)&&((from_type == null)||(!from_type.equals(othersame.from_type)))) {
            return false;
        }
        if (standing!= othersame.standing) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((from_id +((from_type == null)? 0 :from_type.hashCode()))+ Double.hashCode(standing));
    }
}
