package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_1_int_int other2 = ((K_1_int_int) other);
        return ((event_id == other2 .event_id)&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 + event_id)+ character_id));
    }
}
