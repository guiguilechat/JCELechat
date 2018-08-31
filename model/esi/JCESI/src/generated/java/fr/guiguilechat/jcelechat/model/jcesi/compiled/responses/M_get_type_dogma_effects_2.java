package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_type_dogma_effects_2 {
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
        M_get_type_dogma_effects_2 othersame = ((M_get_type_dogma_effects_2) other);
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
