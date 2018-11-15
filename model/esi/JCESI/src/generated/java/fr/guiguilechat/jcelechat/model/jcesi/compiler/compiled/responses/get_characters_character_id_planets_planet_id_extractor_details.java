package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_planets_planet_id_extractor_details {
    /**
     * in seconds
     */
    public int cycle_time;
    /**
     * head_radius number
     */
    public float head_radius;
    /**
     * heads array
     */
    public get_characters_character_id_planets_planet_id_heads[] heads;
    /**
     * product_type_id integer
     */
    public int product_type_id;
    /**
     * qty_per_cycle integer
     */
    public int qty_per_cycle;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_planets_planet_id_extractor_details othersame = ((get_characters_character_id_planets_planet_id_extractor_details) other);
        if (cycle_time!= othersame.cycle_time) {
            return false;
        }
        if (head_radius!= othersame.head_radius) {
            return false;
        }
        if ((heads!= othersame.heads)&&((heads == null)||(!heads.equals(othersame.heads)))) {
            return false;
        }
        if (product_type_id!= othersame.product_type_id) {
            return false;
        }
        if (qty_per_cycle!= othersame.qty_per_cycle) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((cycle_time + Double.hashCode(head_radius))+((heads == null)? 0 :heads.hashCode()))+ product_type_id)+ qty_per_cycle);
    }
}
