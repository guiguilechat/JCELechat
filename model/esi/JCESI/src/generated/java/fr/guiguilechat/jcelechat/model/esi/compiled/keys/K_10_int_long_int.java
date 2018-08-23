package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_10_int_long_int other2 = ((K_10_int_long_int) other);
        return (((corporation_id == other2 .corporation_id)&&(starbase_id == other2 .starbase_id))&&(system_id == other2 .system_id));
    }

    public int hashCode() {
        return ((int)(((0 + corporation_id)+ starbase_id)+ system_id));
    }
}
