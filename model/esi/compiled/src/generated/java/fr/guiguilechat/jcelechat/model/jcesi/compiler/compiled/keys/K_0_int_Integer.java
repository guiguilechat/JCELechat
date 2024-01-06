package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_0_int_Integer othersame = ((K_0_int_Integer) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((from_event!= othersame.from_event)&&((from_event == null)||(!from_event.equals(othersame.from_event)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (character_id +((from_event == null)? 0 :from_event.hashCode()));
    }
}
