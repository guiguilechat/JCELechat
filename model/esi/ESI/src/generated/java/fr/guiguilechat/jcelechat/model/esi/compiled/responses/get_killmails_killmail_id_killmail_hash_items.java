package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class get_killmails_killmail_id_killmail_hash_items {
    /**
     * Flag for the location of the item
     * 
     */
    public int flag;
    /**
     * item_type_id integer
     */
    public int item_type_id;
    /**
     * items array
     */
    public get_killmails_killmail_id_killmail_hash_item_items[] items;
    /**
     * How many of the item were destroyed if any
     * 
     */
    public long quantity_destroyed;
    /**
     * How many of the item were dropped if any
     * 
     */
    public long quantity_dropped;
    /**
     * singleton integer
     */
    public int singleton;
}
