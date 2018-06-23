package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class M_get_contracts_contract_items_6 {
    /**
     * true if the contract issuer has submitted this item with the contract, false if the isser is asking for this item in the contract.
     */
    public boolean is_included;
    /**
     * is_singleton boolean
     */
    public boolean is_singleton;
    /**
     * Number of items in the stack
     */
    public int quantity;
    /**
     * -1 indicates that the item is a singleton (non-stackable). If the item happens to be a Blueprint, -1 is an Original and -2 is a Blueprint Copy
     */
    public int raw_quantity;
    /**
     * Unique ID for the item
     */
    public long record_id;
    /**
     * Type ID for item
     */
    public int type_id;
}
