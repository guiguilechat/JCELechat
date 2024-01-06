package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_system_jumps {
    /**
     * ship_jumps integer
     */
    public int ship_jumps;
    /**
     * system_id integer
     */
    public int system_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_system_jumps othersame = ((R_get_universe_system_jumps) other);
        if (ship_jumps!= othersame.ship_jumps) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (ship_jumps + system_id);
    }
}
