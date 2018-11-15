package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class M_get_contracts_contract_items_6 {
    /**
     * true if the contract issuer has submitted this item with the contract, false if the isser is asking for this item in the contract
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_contracts_contract_items_6 othersame = ((M_get_contracts_contract_items_6) other);
        if (is_included!= othersame.is_included) {
            return false;
        }
        if (is_singleton!= othersame.is_singleton) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (raw_quantity!= othersame.raw_quantity) {
            return false;
        }
        if (record_id!= othersame.record_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(is_included)+ Boolean.hashCode(is_singleton))+ quantity)+ raw_quantity)+ Long.hashCode(record_id))+ type_id);
    }
}
