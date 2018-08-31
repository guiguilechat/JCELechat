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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_8_int_int othersame = ((K_8_int_int) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if (contract_id!= othersame.contract_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (corporation_id + contract_id);
    }
}
