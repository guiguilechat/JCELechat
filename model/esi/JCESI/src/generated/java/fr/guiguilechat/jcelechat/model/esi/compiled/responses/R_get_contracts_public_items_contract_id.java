package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_contracts_public_items_contract_id {
    /**
     * is_blueprint_copy boolean
     */
    public boolean is_blueprint_copy;
    /**
     * true if the contract issuer has submitted this item with the contract, false if the isser is asking for this item in the contract.
     */
    public boolean is_included;
    /**
     * Unique ID for the item being sold. Not present if item is being requested by contract rather than sold with contract.
     */
    public long item_id;
    /**
     * Material Efficiency Level of the blueprint.
     */
    public int material_efficiency;
    /**
     * Number of items in the stack
     */
    public int quantity;
    /**
     * Unique ID for the item, used by the contract system
     */
    public long record_id;
    /**
     * Number of runs remaining if the blueprint is a copy, -1 if it is an original.
     */
    public int runs;
    /**
     * Time Efficiency Level of the blueprint.
     */
    public int time_efficiency;
    /**
     * Type ID for item
     */
    public int type_id;
}
