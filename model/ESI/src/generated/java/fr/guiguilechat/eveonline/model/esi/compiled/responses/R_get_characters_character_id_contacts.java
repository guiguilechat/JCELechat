package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_contacts {
    /**
     * contact_id integer
     */
    public int contact_id;
    /**
     * contact_type string
     */
    public String contact_type;
    /**
     * Whether this contact is in the blocked list. Note a missing value denotes unknown, not true or false
     */
    public boolean is_blocked;
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
