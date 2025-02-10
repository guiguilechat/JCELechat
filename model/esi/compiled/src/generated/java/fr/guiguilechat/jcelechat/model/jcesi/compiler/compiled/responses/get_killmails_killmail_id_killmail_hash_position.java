package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_killmails_killmail_id_killmail_hash_position {
    /**
     * x number
     */
    public double x;
    /**
     * y number
     */
    public double y;
    /**
     * z number
     */
    public double z;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_killmails_killmail_id_killmail_hash_position othersame = ((get_killmails_killmail_id_killmail_hash_position) other);
        if (x!= othersame.x) {
            return false;
        }
        if (y!= othersame.y) {
            return false;
        }
        if (z!= othersame.z) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Double.hashCode(x)+ Double.hashCode(y))+ Double.hashCode(z));
    }
}
