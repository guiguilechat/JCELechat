package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

public class items {
    /**
     * Fitting location for the item. Entries placed in 'Invalid' will be discarded. If this leaves the fitting with nothing, it will cause an error.
     */
    public post_characters_character_id_fittings_flag flag;
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
        items othersame = ((items) other);
        if ((flag!= othersame.flag)&&((flag == null)||(!flag.equals(othersame.flag)))) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((flag == null)? 0 :flag.hashCode())+ quantity)+ type_id);
    }
}
