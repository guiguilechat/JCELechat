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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_18_int_int other2 = ((K_18_int_int) other);
        return ((planet_id == other2 .planet_id)&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 + planet_id)+ character_id));
    }
}
