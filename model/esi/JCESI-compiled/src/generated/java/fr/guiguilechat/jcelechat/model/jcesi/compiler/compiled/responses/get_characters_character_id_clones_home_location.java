package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_clones_location_type;

public class get_characters_character_id_clones_home_location {
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * location_type string
     */
    public get_characters_character_id_clones_location_type location_type;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_clones_home_location othersame = ((get_characters_character_id_clones_home_location) other);
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((location_type!= othersame.location_type)&&((location_type == null)||(!location_type.equals(othersame.location_type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(location_id)+((location_type == null)? 0 :location_type.hashCode()));
    }
}
