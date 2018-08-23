package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/mail/{mail_id}/
 * 
 */
public class K_5_int_int {
    public final int mail_id;
    public final int character_id;

    public K_5_int_int(int mail_id, int character_id) {
        this.mail_id = mail_id;
        this.character_id = character_id;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_5_int_int other2 = ((K_5_int_int) other);
        return ((mail_id == other2 .mail_id)&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 + mail_id)+ character_id));
    }
}
