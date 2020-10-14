package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_planets_planet_id_heads {
    /**
     * head_id integer
     */
    public int head_id;
    /**
     * latitude number
     */
    public float latitude;
    /**
     * longitude number
     */
    public float longitude;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_planets_planet_id_heads othersame = ((get_characters_character_id_planets_planet_id_heads) other);
        if (head_id!= othersame.head_id) {
            return false;
        }
        if (latitude!= othersame.latitude) {
            return false;
        }
        if (longitude!= othersame.longitude) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((head_id + Double.hashCode(latitude))+ Double.hashCode(longitude));
    }
}
