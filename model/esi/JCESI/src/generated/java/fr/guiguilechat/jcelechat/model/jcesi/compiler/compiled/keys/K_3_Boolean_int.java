package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/industry/jobs/
 * 
 */
public class K_3_Boolean_int {
    public final Boolean include_completed;
    public final int character_id;

    public K_3_Boolean_int(Boolean include_completed, int character_id) {
        this.include_completed = include_completed;
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
        K_3_Boolean_int othersame = ((K_3_Boolean_int) other);
        if ((include_completed!= othersame.include_completed)&&((include_completed == null)||(!include_completed.equals(othersame.include_completed)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((include_completed == null)? 0 :include_completed.hashCode())+ character_id);
    }
}
