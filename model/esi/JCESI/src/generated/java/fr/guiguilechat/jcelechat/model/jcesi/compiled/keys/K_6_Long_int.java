package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/wallet/transactions/
 * 
 */
public class K_6_Long_int {
    public final Long from_id;
    public final int character_id;

    public K_6_Long_int(Long from_id, int character_id) {
        this.from_id = from_id;
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
        K_6_Long_int othersame = ((K_6_Long_int) other);
        if ((from_id!= othersame.from_id)&&((from_id == null)||(!from_id.equals(othersame.from_id)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((from_id == null)? 0 :from_id.hashCode())+ character_id);
    }
}
