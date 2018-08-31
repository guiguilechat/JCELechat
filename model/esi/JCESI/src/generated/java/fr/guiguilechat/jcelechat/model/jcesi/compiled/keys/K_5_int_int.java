package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_5_int_int othersame = ((K_5_int_int) other);
        if (mail_id!= othersame.mail_id) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (mail_id + character_id);
    }
}
