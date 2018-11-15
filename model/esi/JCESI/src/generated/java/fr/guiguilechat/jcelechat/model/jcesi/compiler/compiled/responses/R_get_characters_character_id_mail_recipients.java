package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_mail_recipient_type;

public class R_get_characters_character_id_mail_recipients {
    /**
     * recipient_id integer
     */
    public int recipient_id;
    /**
     * recipient_type string
     */
    public get_characters_character_id_mail_recipient_type recipient_type;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_mail_recipients othersame = ((R_get_characters_character_id_mail_recipients) other);
        if (recipient_id!= othersame.recipient_id) {
            return false;
        }
        if ((recipient_type!= othersame.recipient_type)&&((recipient_type == null)||(!recipient_type.equals(othersame.recipient_type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (recipient_id +((recipient_type == null)? 0 :recipient_type.hashCode()));
    }
}
