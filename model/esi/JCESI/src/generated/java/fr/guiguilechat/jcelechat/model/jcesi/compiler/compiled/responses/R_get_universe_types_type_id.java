package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_types_type_id {
    /**
     * capacity number
     */
    public float capacity;
    /**
     * description string
     */
    public String description;
    /**
     * dogma_attributes array
     */
    public get_dogma_dynamic_items_type_id_item_id_dogma_attributes[] dogma_attributes;
    /**
     * dogma_effects array
     */
    public get_dogma_dynamic_items_type_id_item_id_dogma_effects[] dogma_effects;
    /**
     * graphic_id integer
     */
    public int graphic_id;
    /**
     * group_id integer
     */
    public int group_id;
    /**
     * icon_id integer
     */
    public int icon_id;
    /**
     * This only exists for types that can be put on the market
     */
    public int market_group_id;
    /**
     * mass number
     */
    public float mass;
    /**
     * name string
     */
    public String name;
    /**
     * packaged_volume number
     */
    public float packaged_volume;
    /**
     * portion_size integer
     */
    public int portion_size;
    /**
     * published boolean
     */
    public boolean published;
    /**
     * radius number
     */
    public float radius;
    /**
     * type_id integer
     */
    public int type_id;
    /**
     * volume number
     */
    public float volume;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_types_type_id othersame = ((R_get_universe_types_type_id) other);
        if (capacity!= othersame.capacity) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if ((dogma_attributes!= othersame.dogma_attributes)&&((dogma_attributes == null)||(!dogma_attributes.equals(othersame.dogma_attributes)))) {
            return false;
        }
        if ((dogma_effects!= othersame.dogma_effects)&&((dogma_effects == null)||(!dogma_effects.equals(othersame.dogma_effects)))) {
            return false;
        }
        if (graphic_id!= othersame.graphic_id) {
            return false;
        }
        if (group_id!= othersame.group_id) {
            return false;
        }
        if (icon_id!= othersame.icon_id) {
            return false;
        }
        if (market_group_id!= othersame.market_group_id) {
            return false;
        }
        if (mass!= othersame.mass) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (packaged_volume!= othersame.packaged_volume) {
            return false;
        }
        if (portion_size!= othersame.portion_size) {
            return false;
        }
        if (published!= othersame.published) {
            return false;
        }
        if (radius!= othersame.radius) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        if (volume!= othersame.volume) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((((Double.hashCode(capacity)+((description == null)? 0 :description.hashCode()))+((dogma_attributes == null)? 0 :dogma_attributes.hashCode()))+((dogma_effects == null)? 0 :dogma_effects.hashCode()))+ graphic_id)+ group_id)+ icon_id)+ market_group_id)+ Double.hashCode(mass))+((name == null)? 0 :name.hashCode()))+ Double.hashCode(packaged_volume))+ portion_size)+ Boolean.hashCode(published))+ Double.hashCode(radius))+ type_id)+ Double.hashCode(volume));
    }
}
