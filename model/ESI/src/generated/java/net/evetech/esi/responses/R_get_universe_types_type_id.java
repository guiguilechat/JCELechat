package net.evetech.esi.responses;

public class R_get_universe_types_type_id {
    public int type_id;
    public String name;
    public String description;
    public boolean published;
    public int group_id;
    public int market_group_id;
    public float radius;
    public float volume;
    public float packaged_volume;
    public int icon_id;
    public float capacity;
    public int portion_size;
    public float mass;
    public int graphic_id;
    public R_get_universe_types_type_id_dogma_attributes[] dogma_attributes;
    public R_get_universe_types_type_id_dogma_effects[] dogma_effects;
}
