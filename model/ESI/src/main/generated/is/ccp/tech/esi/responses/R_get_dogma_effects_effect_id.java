package is.ccp.tech.esi.responses;

public class R_get_dogma_effects_effect_id {
    public long effect_id;
    public String name;
    public String display_name;
    public String description;
    public long icon_id;
    public long effect_category;
    public long pre_expression;
    public long post_expression;
    public boolean is_offensive;
    public boolean is_assistance;
    public boolean disallow_auto_repeat;
    public boolean published;
    public boolean is_warp_safe;
    public boolean range_chance;
    public boolean electronic_chance;
    public long duration_attribute_id;
    public long tracking_speed_attribute_id;
    public long discharge_attribute_id;
    public long range_attribute_id;
    public long falloff_attribute_id;
    public R_get_dogma_effects_effect_id_modifiers[] modifiers;
}
