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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_14_int_int othersame = ((K_14_int_int) other);
        if (type_id!= othersame.type_id) {
            return false;
        }
        if (region_id!= othersame.region_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (type_id + region_id);
    }
}
