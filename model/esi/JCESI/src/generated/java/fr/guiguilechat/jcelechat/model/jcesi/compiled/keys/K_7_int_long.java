package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/corporation/{corporation_id}/mining/observers/{observer_id}/
 * 
 */
public class K_7_int_long {
    public final int corporation_id;
    public final long observer_id;

    public K_7_int_long(int corporation_id, long observer_id) {
        this.corporation_id = corporation_id;
        this.observer_id = observer_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_7_int_long othersame = ((K_7_int_long) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if (observer_id!= othersame.observer_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (corporation_id + Long.hashCode(observer_id));
    }
}
