package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_planets_planet_id_contents {
    /**
     * amount integer
     */
    public long amount;
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
        get_characters_character_id_planets_planet_id_contents othersame = ((get_characters_character_id_planets_planet_id_contents) other);
        if (amount!= othersame.amount) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(amount)+ type_id);
    }
}
