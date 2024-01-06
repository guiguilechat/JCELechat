package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_contacts_contact_type;

public class R_get_corporations_corporation_id_contacts {
    /**
     * contact_id integer
     */
    public int contact_id;
    /**
     * contact_type string
     */
    public get_corporations_corporation_id_contacts_contact_type contact_type;
    /**
     * Whether this contact is being watched
     */
    public boolean is_watched;
    /**
     * label_ids array
     */
    public long[] label_ids;
    /**
     * Standing of the contact
     */
    public float standing;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_contacts othersame = ((R_get_corporations_corporation_id_contacts) other);
        if (contact_id!= othersame.contact_id) {
            return false;
        }
        if ((contact_type!= othersame.contact_type)&&((contact_type == null)||(!contact_type.equals(othersame.contact_type)))) {
            return false;
        }
        if (is_watched!= othersame.is_watched) {
            return false;
        }
        if ((label_ids!= othersame.label_ids)&&((label_ids == null)||(!label_ids.equals(othersame.label_ids)))) {
            return false;
        }
        if (standing!= othersame.standing) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((contact_id +((contact_type == null)? 0 :contact_type.hashCode()))+ Boolean.hashCode(is_watched))+((label_ids == null)? 0 :label_ids.hashCode()))+ Double.hashCode(standing));
    }
}
