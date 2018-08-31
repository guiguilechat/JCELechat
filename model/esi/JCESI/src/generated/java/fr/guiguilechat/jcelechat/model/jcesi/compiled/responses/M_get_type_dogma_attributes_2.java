package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_type_dogma_attributes_2 {
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
        M_get_type_dogma_attributes_2 othersame = ((M_get_type_dogma_attributes_2) other);
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
