package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_mail_lists {
    /**
     * Mailing list ID
     */
    public int mailing_list_id;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_mail_lists othersame = ((R_get_characters_character_id_mail_lists) other);
        if (mailing_list_id!= othersame.mailing_list_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (mailing_list_id +((name == null)? 0 :name.hashCode()));
    }
}
