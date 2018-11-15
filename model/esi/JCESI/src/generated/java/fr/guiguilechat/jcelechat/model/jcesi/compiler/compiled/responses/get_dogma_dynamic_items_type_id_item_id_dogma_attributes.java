package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_dogma_dynamic_items_type_id_item_id_dogma_attributes {
    /**
     * attribute_id integer
     */
    public int attribute_id;
    /**
     * value number
     */
    public float value;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_dogma_dynamic_items_type_id_item_id_dogma_attributes othersame = ((get_dogma_dynamic_items_type_id_item_id_dogma_attributes) other);
        if (attribute_id!= othersame.attribute_id) {
            return false;
        }
        if (value!= othersame.value) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (attribute_id + Double.hashCode(value));
    }
}
