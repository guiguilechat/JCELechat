package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_corporations_corporation_id_starbases_starbase_id_fuels {
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
        get_corporations_corporation_id_starbases_starbase_id_fuels othersame = ((get_corporations_corporation_id_starbases_starbase_id_fuels) other);
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
