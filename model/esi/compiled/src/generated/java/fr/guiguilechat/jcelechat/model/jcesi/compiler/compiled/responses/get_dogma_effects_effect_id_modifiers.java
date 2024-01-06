package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_dogma_effects_effect_id_modifiers {
    /**
     * domain string
     */
    public String domain;
    /**
     * effect_id integer
     */
    public int effect_id;
    /**
     * func string
     */
    public String func;
    /**
     * modified_attribute_id integer
     */
    public int modified_attribute_id;
    /**
     * modifying_attribute_id integer
     */
    public int modifying_attribute_id;
    /**
     * operator integer
     */
    public int operator;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_dogma_effects_effect_id_modifiers othersame = ((get_dogma_effects_effect_id_modifiers) other);
        if ((domain!= othersame.domain)&&((domain == null)||(!domain.equals(othersame.domain)))) {
            return false;
        }
        if (effect_id!= othersame.effect_id) {
            return false;
        }
        if ((func!= othersame.func)&&((func == null)||(!func.equals(othersame.func)))) {
            return false;
        }
        if (modified_attribute_id!= othersame.modified_attribute_id) {
            return false;
        }
        if (modifying_attribute_id!= othersame.modifying_attribute_id) {
            return false;
        }
        if (operator!= othersame.operator) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((domain == null)? 0 :domain.hashCode())+ effect_id)+((func == null)? 0 :func.hashCode()))+ modified_attribute_id)+ modifying_attribute_id)+ operator);
    }
}
