package net.evetech.esi.responses;

public class R_get_dogma_effects_effect_id {
    public int effect_id;
    public String name;
    public String display_name;
    public String description;
    public int icon_id;
    public int effect_category;
    public int pre_expression;
    public int post_expression;
    public boolean is_offensive;
    public boolean is_assistance;
    public boolean disallow_auto_repeat;
    public boolean published;
    public boolean is_warp_safe;
    public boolean range_chance;
    public boolean electronic_chance;
    public int duration_attribute_id;
    public int tracking_speed_attribute_id;
    public int discharge_attribute_id;
    public int range_attribute_id;
    public int falloff_attribute_id;
    public R_get_dogma_effects_effect_id_modifiers[] modifiers;
}
