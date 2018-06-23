package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_alliancehistory {
    /**
     * alliance_id integer
     */
    public int alliance_id;
    /**
     * True if the alliance has been closed
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
