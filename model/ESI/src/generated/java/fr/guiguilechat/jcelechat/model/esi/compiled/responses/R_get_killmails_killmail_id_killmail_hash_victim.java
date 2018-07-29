package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_killmails_killmail_id_killmail_hash_victim {
    /**
     * alliance_id integer
     */
    public int alliance_id;
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * How much total damage was taken by the victim
     * 
     */
    public int damage_taken;
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * items array
     */
    public get_killmails_killmail_id_killmail_hash_items[] items;
    /**
     * Coordinates of the victim in Cartesian space relative to the Sun
     * 
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * The ship that the victim was piloting and was destroyed
     * 
     */
    public int ship_type_id;
}
