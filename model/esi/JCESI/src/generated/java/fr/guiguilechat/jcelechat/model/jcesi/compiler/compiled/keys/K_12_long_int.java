package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_12_long_int othersame = ((K_12_long_int) other);
        if (item_id!= othersame.item_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(item_id)+ type_id);
    }
}
