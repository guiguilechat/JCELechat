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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_11_int_int_Long other2 = ((K_11_int_int_Long) other);
        return (((division == other2 .division)&&(corporation_id == other2 .corporation_id))&&((from_id == other2 .from_id)||((from_id!= null)&&from_id.equals(other2 .from_id))));
    }

    public int hashCode() {
        return ((int)(((0 + division)+ corporation_id)+((from_id == null)? 0 :from_id.hashCode())));
    }
}
