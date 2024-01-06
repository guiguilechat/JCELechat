package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_9_int_Boolean othersame = ((K_9_int_Boolean) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((include_completed!= othersame.include_completed)&&((include_completed == null)||(!include_completed.equals(othersame.include_completed)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (corporation_id +((include_completed == null)? 0 :include_completed.hashCode()));
    }
}
