package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/dogma/dynamic/items/{type_id}/{item_id}/
 * 
 */
public class K_12_long_int {
    public final long item_id;
    public final int type_id;

    public K_12_long_int(long item_id, int type_id) {
        this.item_id = item_id;
        this.type_id = type_id;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_12_long_int other2 = ((K_12_long_int) other);
        return ((item_id == other2 .item_id)&&(type_id == other2 .type_id));
    }

    public int hashCode() {
        return ((int)((0 + item_id)+ type_id));
    }
}
