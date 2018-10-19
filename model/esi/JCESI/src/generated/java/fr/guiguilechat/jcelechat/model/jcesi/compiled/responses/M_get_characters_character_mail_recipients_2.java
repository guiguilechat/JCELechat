package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_characters_character_id_mail_recipient_type;

public class M_get_characters_character_mail_recipients_2 {
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
        M_get_characters_character_mail_recipients_2 othersame = ((M_get_characters_character_mail_recipients_2) other);
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
