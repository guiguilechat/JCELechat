package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_dogma_dynamic_items_type_id_item_id_dogma_effects {
    /**
     * effect_id integer
     */
    public int effect_id;
    /**
     * is_default boolean
     */
    public boolean is_default;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_dogma_dynamic_items_type_id_item_id_dogma_effects othersame = ((get_dogma_dynamic_items_type_id_item_id_dogma_effects) other);
        if (effect_id!= othersame.effect_id) {
            return false;
        }
        if (is_default!= othersame.is_default) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (effect_id + Boolean.hashCode(is_default));
    }
}
