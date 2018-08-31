package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_dogma_effects_effect_id {
    /**
     * description string
     */
    public String description;
    /**
     * disallow_auto_repeat boolean
     */
    public boolean disallow_auto_repeat;
    /**
     * discharge_attribute_id integer
     */
    public int discharge_attribute_id;
    /**
     * display_name string
     */
    public String display_name;
    /**
     * duration_attribute_id integer
     */
    public int duration_attribute_id;
    /**
     * effect_category integer
     */
    public int effect_category;
    /**
     * effect_id integer
     */
    public int effect_id;
    /**
     * electronic_chance boolean
     */
    public boolean electronic_chance;
    /**
     * falloff_attribute_id integer
     */
    public int falloff_attribute_id;
    /**
     * icon_id integer
     */
    public int icon_id;
    /**
     * is_assistance boolean
     */
    public boolean is_assistance;
    /**
     * is_offensive boolean
     */
    public boolean is_offensive;
    /**
     * is_warp_safe boolean
     */
    public boolean is_warp_safe;
    /**
     * modifiers array
     */
    public R_get_dogma_effects_effect_id_modifiers[] modifiers;
    /**
     * name string
     */
    public String name;
    /**
     * post_expression integer
     */
    public int post_expression;
    /**
     * pre_expression integer
     */
    public int pre_expression;
    /**
     * published boolean
     */
    public boolean published;
    /**
     * range_attribute_id integer
     */
    public int range_attribute_id;
    /**
     * range_chance boolean
     */
    public boolean range_chance;
    /**
     * tracking_speed_attribute_id integer
     */
    public int tracking_speed_attribute_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_dogma_effects_effect_id othersame = ((R_get_dogma_effects_effect_id) other);
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (disallow_auto_repeat!= othersame.disallow_auto_repeat) {
            return false;
        }
        if (discharge_attribute_id!= othersame.discharge_attribute_id) {
            return false;
        }
        if ((display_name!= othersame.display_name)&&((display_name == null)||(!display_name.equals(othersame.display_name)))) {
            return false;
        }
        if (duration_attribute_id!= othersame.duration_attribute_id) {
            return false;
        }
        if (effect_category!= othersame.effect_category) {
            return false;
        }
        if (effect_id!= othersame.effect_id) {
            return false;
        }
        if (electronic_chance!= othersame.electronic_chance) {
            return false;
        }
        if (falloff_attribute_id!= othersame.falloff_attribute_id) {
            return false;
        }
        if (icon_id!= othersame.icon_id) {
            return false;
        }
        if (is_assistance!= othersame.is_assistance) {
            return false;
        }
        if (is_offensive!= othersame.is_offensive) {
            return false;
        }
        if (is_warp_safe!= othersame.is_warp_safe) {
            return false;
        }
        if ((modifiers!= othersame.modifiers)&&((modifiers == null)||(!modifiers.equals(othersame.modifiers)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (post_expression!= othersame.post_expression) {
            return false;
        }
        if (pre_expression!= othersame.pre_expression) {
            return false;
        }
        if (published!= othersame.published) {
            return false;
        }
        if (range_attribute_id!= othersame.range_attribute_id) {
            return false;
        }
        if (range_chance!= othersame.range_chance) {
            return false;
        }
        if (tracking_speed_attribute_id!= othersame.tracking_speed_attribute_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((((((description == null)? 0 :description.hashCode())+ Boolean.hashCode(disallow_auto_repeat))+ discharge_attribute_id)+((display_name == null)? 0 :display_name.hashCode()))+ duration_attribute_id)+ effect_category)+ effect_id)+ Boolean.hashCode(electronic_chance))+ falloff_attribute_id)+ icon_id)+ Boolean.hashCode(is_assistance))+ Boolean.hashCode(is_offensive))+ Boolean.hashCode(is_warp_safe))+((modifiers == null)? 0 :modifiers.hashCode()))+((name == null)? 0 :name.hashCode()))+ post_expression)+ pre_expression)+ Boolean.hashCode(published))+ range_attribute_id)+ Boolean.hashCode(range_chance))+ tracking_speed_attribute_id);
    }
}
