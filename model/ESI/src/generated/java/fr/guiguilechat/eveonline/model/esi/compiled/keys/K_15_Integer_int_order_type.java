package fr.guiguilechat.eveonline.model.esi.compiled.keys;

import fr.guiguilechat.eveonline.model.esi.compiled.Swagger;


/**
 * @see https://esi.evetech.net/v1/markets/{region_id}/orders/
 * 
 */
public class K_15_Integer_int_order_type {
    public final Integer type_id;
    public final int region_id;
    public final Swagger.order_type order_type;

    public K_15_Integer_int_order_type(Integer type_id, int region_id, Swagger.order_type order_type) {
        this.type_id = type_id;
        this.region_id = region_id;
        this.order_type = order_type;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_15_Integer_int_order_type other2 = ((K_15_Integer_int_order_type) other);
        return ((((type_id == other2 .type_id)||((type_id!= null)&&type_id.equals(other2 .type_id)))&&(region_id == other2 .region_id))&&((order_type == other2 .order_type)||((order_type!= null)&&order_type.equals(other2 .order_type))));
    }

    public int hashCode() {
        return ((int)(((0 +((type_id == null)? 0 :type_id.hashCode()))+ region_id)+((order_type == null)? 0 :order_type.hashCode())));
    }
}
