package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_loyalty_points {
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * loyalty_points integer
     */
    public int loyalty_points;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_loyalty_points othersame = ((R_get_characters_character_id_loyalty_points) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if (loyalty_points!= othersame.loyalty_points) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (corporation_id + loyalty_points);
    }
}
