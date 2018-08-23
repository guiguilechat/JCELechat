package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_alliances_alliance_id {
    /**
     * ID of the corporation that created the alliance
     */
    public int creator_corporation_id;
    /**
     * ID of the character that created the alliance
     */
    public int creator_id;
    /**
     * date_founded string
     */
    public String date_founded;
    /**
     * the executor corporation ID, if this alliance is not closed
     */
    public int executor_corporation_id;
    /**
     * Faction ID this alliance is fighting for, if this alliance is enlisted in factional warfare
     */
    public int faction_id;
    /**
     * the full name of the alliance
     */
    public String name;
    /**
     * the short name of the alliance
     */
    public String ticker;
}
