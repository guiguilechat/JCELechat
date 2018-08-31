package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/corporations/{corporation_id}/wallets/{division}/transactions/
 * 
 */
public class K_11_int_int_Long {
    public final int division;
    public final int corporation_id;
    public final Long from_id;

    public K_11_int_int_Long(int division, int corporation_id, Long from_id) {
        this.division = division;
        this.corporation_id = corporation_id;
        this.from_id = from_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_11_int_int_Long othersame = ((K_11_int_int_Long) other);
        if (division!= othersame.division) {
            return false;
        }
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((from_id!= othersame.from_id)&&((from_id == null)||(!from_id.equals(othersame.from_id)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((division + corporation_id)+((from_id == null)? 0 :from_id.hashCode()));
    }
}
