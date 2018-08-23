package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/markets/{region_id}/history/
 * 
 */
public class K_14_int_int {
    public final int type_id;
    public final int region_id;

    public K_14_int_int(int type_id, int region_id) {
        this.type_id = type_id;
        this.region_id = region_id;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_14_int_int other2 = ((K_14_int_int) other);
        return ((type_id == other2 .type_id)&&(region_id == other2 .region_id));
    }

    public int hashCode() {
        return ((int)((0 + type_id)+ region_id));
    }
}
