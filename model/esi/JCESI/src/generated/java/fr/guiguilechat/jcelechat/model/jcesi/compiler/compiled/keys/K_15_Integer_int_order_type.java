package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/markets/{region_id}/orders/
 * 
 */
public class K_15_Integer_int_order_type {
    public final Integer type_id;
    public final int region_id;
    public final fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type order_type;

    public K_15_Integer_int_order_type(Integer type_id, int region_id, fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type order_type) {
        this.type_id = type_id;
        this.region_id = region_id;
        this.order_type = order_type;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_15_Integer_int_order_type othersame = ((K_15_Integer_int_order_type) other);
        if ((type_id!= othersame.type_id)&&((type_id == null)||(!type_id.equals(othersame.type_id)))) {
            return false;
        }
        if (region_id!= othersame.region_id) {
            return false;
        }
        if ((order_type!= othersame.order_type)&&((order_type == null)||(!order_type.equals(othersame.order_type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((type_id == null)? 0 :type_id.hashCode())+ region_id)+((order_type == null)? 0 :order_type.hashCode()));
    }
}
