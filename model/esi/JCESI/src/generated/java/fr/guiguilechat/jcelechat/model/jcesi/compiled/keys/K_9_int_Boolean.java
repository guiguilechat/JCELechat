package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/corporations/{corporation_id}/industry/jobs/
 * 
 */
public class K_9_int_Boolean {
    public final int corporation_id;
    public final Boolean include_completed;

    public K_9_int_Boolean(int corporation_id, Boolean include_completed) {
        this.corporation_id = corporation_id;
        this.include_completed = include_completed;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_9_int_Boolean other2 = ((K_9_int_Boolean) other);
        return ((corporation_id == other2 .corporation_id)&&((include_completed == other2 .include_completed)||((include_completed!= null)&&include_completed.equals(other2 .include_completed))));
    }

    public int hashCode() {
        return ((int)((0 + corporation_id)+((include_completed == null)? 0 :include_completed.hashCode())));
    }
}
