package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_6_Long_int other2 = ((K_6_Long_int) other);
        return (((from_id == other2 .from_id)||((from_id!= null)&&from_id.equals(other2 .from_id)))&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 +((from_id == null)? 0 :from_id.hashCode()))+ character_id));
    }
}
