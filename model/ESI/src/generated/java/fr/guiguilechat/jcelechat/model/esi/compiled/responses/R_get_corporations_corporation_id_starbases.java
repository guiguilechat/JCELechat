package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_starbases {
    /**
     * The moon this starbase (POS) is anchored on, unanchored POSes do not have this information
     */
    public int moon_id;
    /**
     * When the POS onlined, for starbases (POSes) in online state
     */
    public String onlined_since;
    /**
     * When the POS will be out of reinforcement, for starbases (POSes) in reinforced state
     */
    public String reinforced_until;
    /**
     * Unique ID for this starbase (POS)
     */
    public long starbase_id;
    /**
     * state string
     */
    public String state;
    /**
     * The solar system this starbase (POS) is in, unanchored POSes have this information
     */
    public int system_id;
    /**
     * Starbase (POS) type
     */
    public int type_id;
    /**
     * When the POS started unanchoring, for starbases (POSes) in unanchoring state
     */
    public String unanchor_at;
}
