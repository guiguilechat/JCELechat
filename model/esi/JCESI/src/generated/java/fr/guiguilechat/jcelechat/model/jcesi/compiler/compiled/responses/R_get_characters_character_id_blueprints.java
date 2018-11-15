package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_blueprints_location_flag;

public class R_get_characters_character_id_blueprints {
    /**
     * Unique ID for this item.
     */
    public long item_id;
    /**
     * Type of the location_id
     */
    public get_characters_character_id_blueprints_location_flag location_flag;
    /**
     * References a solar system, station or item_id if this blueprint is located within a container. If the return value is an item_id, then the Character AssetList API must be queried to find the container using the given item_id to determine the correct location of the Blueprint.
     */
    public long location_id;
    /**
     * Material Efficiency Level of the blueprint.
     */
    public int material_efficiency;
    /**
     * A range of numbers with a minimum of -2 and no maximum value where -1 is an original and -2 is a copy. It can be a positive integer if it is a stack of blueprint originals fresh from the market (e.g. no activities performed on them yet).
     */
    public int quantity;
    /**
     * Number of runs remaining if the blueprint is a copy, -1 if it is an original.
     */
    public int runs;
    /**
     * Time Efficiency Level of the blueprint.
     */
    public int time_efficiency;
    /**
     * type_id integer
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_blueprints othersame = ((R_get_characters_character_id_blueprints) other);
        if (item_id!= othersame.item_id) {
            return false;
        }
        if ((location_flag!= othersame.location_flag)&&((location_flag == null)||(!location_flag.equals(othersame.location_flag)))) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if (material_efficiency!= othersame.material_efficiency) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (runs!= othersame.runs) {
            return false;
        }
        if (time_efficiency!= othersame.time_efficiency) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((Long.hashCode(item_id)+((location_flag == null)? 0 :location_flag.hashCode()))+ Long.hashCode(location_id))+ material_efficiency)+ quantity)+ runs)+ time_efficiency)+ type_id);
    }
}
