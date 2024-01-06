package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v4/corporations/{corporation_id}/wallets/{division}/journal/
 * 
 */
public class K_19_int_int {
    public final int division;
    public final int corporation_id;

    public K_19_int_int(int division, int corporation_id) {
        this.division = division;
        this.corporation_id = corporation_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_19_int_int othersame = ((K_19_int_int) other);
        if (division!= othersame.division) {
            return false;
        }
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (division + corporation_id);
    }
}
