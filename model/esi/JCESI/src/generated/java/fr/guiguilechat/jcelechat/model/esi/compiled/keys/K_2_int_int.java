package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_2_int_int other2 = ((K_2_int_int) other);
        return ((contract_id == other2 .contract_id)&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 + contract_id)+ character_id));
    }
}
