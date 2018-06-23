package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id {
    /**
     * ID of the alliance that corporation is a member of, if any
     */
    public int alliance_id;
    /**
     * ceo_id integer
     */
    public int ceo_id;
    /**
     * creator_id integer
     */
    public int creator_id;
    /**
     * date_founded string
     */
    public String date_founded;
    /**
     * description string
     */
    public String description;
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * home_station_id integer
     */
    public int home_station_id;
    /**
     * member_count integer
     */
    public int member_count;
    /**
     * the full name of the corporation
     */
    public String name;
    /**
     * shares integer
     */
    public long shares;
    /**
     * tax_rate number
     */
    public float tax_rate;
    /**
     * the short name of the corporation
     */
    public String ticker;
    /**
     * url string
     */
    public String url;
}
