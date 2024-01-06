package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_loyalty_stores_corporation_id_offers_required_items {
    /**
     * quantity integer
     */
    public int quantity;
    /**
     * type_id integer
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
        R_get_loyalty_stores_corporation_id_offers_required_items othersame = ((R_get_loyalty_stores_corporation_id_offers_required_items) other);
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (quantity + type_id);
    }
}
