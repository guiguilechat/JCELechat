package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_characters_character_id_clones_jump_clone_location_type;

public class R_get_characters_character_id_clones_jump_clones {
    /**
     * implants array
     */
    public int[] implants;
    /**
     * jump_clone_id integer
     */
    public int jump_clone_id;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * location_type string
     */
    public get_characters_character_id_clones_jump_clone_location_type location_type;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_clones_jump_clones othersame = ((R_get_characters_character_id_clones_jump_clones) other);
        if ((implants!= othersame.implants)&&((implants == null)||(!implants.equals(othersame.implants)))) {
            return false;
        }
        if (jump_clone_id!= othersame.jump_clone_id) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((location_type!= othersame.location_type)&&((location_type == null)||(!location_type.equals(othersame.location_type)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((implants == null)? 0 :implants.hashCode())+ jump_clone_id)+ Long.hashCode(location_id))+((location_type == null)? 0 :location_type.hashCode()))+((name == null)? 0 :name.hashCode()));
    }
}
