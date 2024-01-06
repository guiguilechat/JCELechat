package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_planets_planet_id_factory_details {
    /**
     * schematic_id integer
     */
    public int schematic_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_planets_planet_id_factory_details othersame = ((get_characters_character_id_planets_planet_id_factory_details) other);
        if (schematic_id!= othersame.schematic_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return schematic_id;
    }
}
