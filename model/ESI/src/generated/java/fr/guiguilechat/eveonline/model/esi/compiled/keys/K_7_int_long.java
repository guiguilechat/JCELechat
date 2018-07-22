package fr.guiguilechat.eveonline.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_7_int_long other2 = ((K_7_int_long) other);
        return ((corporation_id == other2 .corporation_id)&&(observer_id == other2 .observer_id));
    }

    public int hashCode() {
        return ((int)((0 + corporation_id)+ observer_id));
    }
}
