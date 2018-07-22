package fr.guiguilechat.eveonline.model.esi.compiled.keys;


/**
 * @see https://esi.evetech.net/v3/corporations/{corporation_id}/wallets/{division}/journal/
 * 
 */
public class K_20_int_int {
    public final int division;
    public final int corporation_id;

    public K_20_int_int(int division, int corporation_id) {
        this.division = division;
        this.corporation_id = corporation_id;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_20_int_int other2 = ((K_20_int_int) other);
        return ((division == other2 .division)&&(corporation_id == other2 .corporation_id));
    }

    public int hashCode() {
        return ((int)((0 + division)+ corporation_id));
    }
}
