package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_corporationhistory {
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * True if the corporation has been deleted
     */
    public boolean is_deleted;
    /**
     * An incrementing ID that can be used to canonically establish order of records in cases where dates may be ambiguous
     */
    public int record_id;
    /**
     * start_date string
     */
    public String start_date;
}
