package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/bids/
 * @see https://esi.evetech.net/v1/characters/{character_id}/contracts/{contract_id}/items/
 * 
 */
public class K_2_int_int {
    public final int contract_id;
    public final int character_id;

    public K_2_int_int(int contract_id, int character_id) {
        this.contract_id = contract_id;
        this.character_id = character_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_2_int_int othersame = ((K_2_int_int) other);
        if (contract_id!= othersame.contract_id) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (contract_id + character_id);
    }
}
