package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_3_Boolean_int other2 = ((K_3_Boolean_int) other);
        return (((include_completed == other2 .include_completed)||((include_completed!= null)&&include_completed.equals(other2 .include_completed)))&&(character_id == other2 .character_id));
    }

    public int hashCode() {
        return ((int)((0 +((include_completed == null)? 0 :include_completed.hashCode()))+ character_id));
    }
}
