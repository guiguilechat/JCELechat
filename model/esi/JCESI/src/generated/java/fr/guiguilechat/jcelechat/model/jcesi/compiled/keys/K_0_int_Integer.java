package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/calendar/
 * 
 */
public class K_0_int_Integer {
    public final int character_id;
    public final Integer from_event;

    public K_0_int_Integer(int character_id, Integer from_event) {
        this.character_id = character_id;
        this.from_event = from_event;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_0_int_Integer other2 = ((K_0_int_Integer) other);
        return ((character_id == other2 .character_id)&&((from_event == other2 .from_event)||((from_event!= null)&&from_event.equals(other2 .from_event))));
    }

    public int hashCode() {
        return ((int)((0 + character_id)+((from_event == null)? 0 :from_event.hashCode())));
    }
}
