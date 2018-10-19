package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_characters_character_id_planets_planet_type;

public class R_get_characters_character_id_planets {
    /**
     * last_update string
     */
    public String last_update;
    /**
     * num_pins integer
     */
    public int num_pins;
    /**
     * owner_id integer
     */
    public int owner_id;
    /**
     * planet_id integer
     */
    public int planet_id;
    /**
     * planet_type string
     */
    public get_characters_character_id_planets_planet_type planet_type;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;
    /**
     * upgrade_level integer
     */
    public int upgrade_level;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_planets othersame = ((R_get_characters_character_id_planets) other);
        if ((last_update!= othersame.last_update)&&((last_update == null)||(!last_update.equals(othersame.last_update)))) {
            return false;
        }
        if (num_pins!= othersame.num_pins) {
            return false;
        }
        if (owner_id!= othersame.owner_id) {
            return false;
        }
        if (planet_id!= othersame.planet_id) {
            return false;
        }
        if ((planet_type!= othersame.planet_type)&&((planet_type == null)||(!planet_type.equals(othersame.planet_type)))) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (upgrade_level!= othersame.upgrade_level) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((last_update == null)? 0 :last_update.hashCode())+ num_pins)+ owner_id)+ planet_id)+((planet_type == null)? 0 :planet_type.hashCode()))+ solar_system_id)+ upgrade_level);
    }
}
