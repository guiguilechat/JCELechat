package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_contracts_public_items_contract_id {
    /**
     * is_blueprint_copy boolean
     */
    public boolean is_blueprint_copy;
    /**
     * true if the contract issuer has submitted this item with the contract, false if the isser is asking for this item in the contract
     */
    public boolean is_included;
    /**
     * Unique ID for the item being sold. Not present if item is being requested by contract rather than sold with contract
     */
    public long item_id;
    /**
     * Material Efficiency Level of the blueprint
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
     * Number of runs remaining if the blueprint is a copy, -1 if it is an original
     */
    public int runs;
    /**
     * Time Efficiency Level of the blueprint
     */
    public int time_efficiency;
    /**
     * Type ID for item
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_contracts_public_items_contract_id othersame = ((R_get_contracts_public_items_contract_id) other);
        if (is_blueprint_copy!= othersame.is_blueprint_copy) {
            return false;
        }
        if (is_included!= othersame.is_included) {
            return false;
        }
        if (item_id!= othersame.item_id) {
            return false;
        }
        if (material_efficiency!= othersame.material_efficiency) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (record_id!= othersame.record_id) {
            return false;
        }
        if (runs!= othersame.runs) {
            return false;
        }
        if (time_efficiency!= othersame.time_efficiency) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((Boolean.hashCode(is_blueprint_copy)+ Boolean.hashCode(is_included))+ Long.hashCode(item_id))+ material_efficiency)+ quantity)+ Long.hashCode(record_id))+ runs)+ time_efficiency)+ type_id);
    }
}
