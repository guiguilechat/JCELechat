package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_planets_planet_id_links {
    /**
     * destination_pin_id integer
     */
    public long destination_pin_id;
    /**
     * link_level integer
     */
    public int link_level;
    /**
     * source_pin_id integer
     */
    public long source_pin_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_planets_planet_id_links othersame = ((R_get_characters_character_id_planets_planet_id_links) other);
        if (destination_pin_id!= othersame.destination_pin_id) {
            return false;
        }
        if (link_level!= othersame.link_level) {
            return false;
        }
        if (source_pin_id!= othersame.source_pin_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Long.hashCode(destination_pin_id)+ link_level)+ Long.hashCode(source_pin_id));
    }
}
