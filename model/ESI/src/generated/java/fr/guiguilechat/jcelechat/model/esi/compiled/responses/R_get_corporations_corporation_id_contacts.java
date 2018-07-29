package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_contacts {
    /**
     * contact_id integer
     */
    public int contact_id;
    /**
     * contact_type string
     */
    public String contact_type;
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
}
