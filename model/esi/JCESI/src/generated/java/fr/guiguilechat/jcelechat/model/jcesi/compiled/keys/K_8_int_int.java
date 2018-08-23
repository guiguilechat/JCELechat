package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/bids/
 * @see https://esi.evetech.net/v1/corporations/{corporation_id}/contracts/{contract_id}/items/
 * 
 */
public class K_8_int_int {
    public final int corporation_id;
    public final int contract_id;

    public K_8_int_int(int corporation_id, int contract_id) {
        this.corporation_id = corporation_id;
        this.contract_id = contract_id;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_8_int_int other2 = ((K_8_int_int) other);
        return ((corporation_id == other2 .corporation_id)&&(contract_id == other2 .contract_id));
    }

    public int hashCode() {
        return ((int)((0 + corporation_id)+ contract_id));
    }
}
