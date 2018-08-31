package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_post_characters_character_id_fittings_created {
    /**
     * fitting_id integer
     */
    public int fitting_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_post_characters_character_id_fittings_created othersame = ((R_post_characters_character_id_fittings_created) other);
        if (fitting_id!= othersame.fitting_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return fitting_id;
    }
}
