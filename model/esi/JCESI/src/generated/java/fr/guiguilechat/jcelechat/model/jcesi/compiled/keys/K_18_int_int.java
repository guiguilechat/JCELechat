package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v3/characters/{character_id}/planets/{planet_id}/
 * 
 */
public class K_18_int_int {
    public final int planet_id;
    public final int character_id;

    public K_18_int_int(int planet_id, int character_id) {
        this.planet_id = planet_id;
        this.character_id = character_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_18_int_int othersame = ((K_18_int_int) other);
        if (planet_id!= othersame.planet_id) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (planet_id + character_id);
    }
}
