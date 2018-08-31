package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/calendar/{event_id}/attendees/
 * @see https://esi.evetech.net/v3/characters/{character_id}/calendar/{event_id}/
 * 
 */
public class K_1_int_int {
    public final int event_id;
    public final int character_id;

    public K_1_int_int(int event_id, int character_id) {
        this.event_id = event_id;
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
        K_1_int_int othersame = ((K_1_int_int) other);
        if (event_id!= othersame.event_id) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (event_id + character_id);
    }
}
