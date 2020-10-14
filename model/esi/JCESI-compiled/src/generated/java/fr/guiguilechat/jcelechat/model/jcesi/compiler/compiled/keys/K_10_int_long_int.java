package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/corporations/{corporation_id}/starbases/{starbase_id}/
 * 
 */
public class K_10_int_long_int {
    public final int corporation_id;
    public final long starbase_id;
    public final int system_id;

    public K_10_int_long_int(int corporation_id, long starbase_id, int system_id) {
        this.corporation_id = corporation_id;
        this.starbase_id = starbase_id;
        this.system_id = system_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_10_int_long_int othersame = ((K_10_int_long_int) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if (starbase_id!= othersame.starbase_id) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((corporation_id + Long.hashCode(starbase_id))+ system_id);
    }
}
